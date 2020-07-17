package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.TestDto;
import com.ids.webarchitecture.exception.LockedException;
import com.ids.webarchitecture.exception.RequestParameterException;
import com.ids.webarchitecture.model.mongo.*;
import com.ids.webarchitecture.repository.mongo.DataTemplateMongoRepository;
import com.ids.webarchitecture.repository.mongo.TestRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;
import static java.lang.String.format;

@Service
public class TestService {
    Logger log = LoggerFactory.getLogger(TestService.class);

    private static final int TEST_DURATION_MS = 60 * 1000;

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private DataTemplateMongoRepository dataTemplateMongoRepository;
    @Autowired
    private TestLockService testLockService;
    @Autowired
    private MongoTestService testMongoService;
    @Autowired
    private SqlTestService testSqlService;

    private ModelMapper mapper = new ModelMapper();

    // tests measurements where key is testId, value is list of measurements
    private Map <String, List<Pair<DataActionMeasurements, DataActionMeasurements>>> testMeasurements = new ConcurrentHashMap<>();
    private Map <String, List<String>> testErrors = new ConcurrentHashMap<>();
    private Map <String, DataTemplateMongo> dataTemplates = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        readDataTemplates();
    }

    private void readDataTemplates() {
        this.dataTemplates = dataTemplateMongoRepository.findAll().stream()
                .collect(Collectors.toMap(DataTemplateMongo::getId, i -> i));
    }

    @Transactional
    public TestDto createTest() {
        if (testLockService.locked()) {
            throw new LockedException("Test service already locked");
        }

        readDataTemplates();

        Test test = new Test();
        test.setBackendClusterSize((short) 1); //TODO: read from ZooKeeper
        test.setMongoDbClusterSize((short) 1); //TODO: read from property
        test.setSqlClusterSize((short) 1); //TODO: read from property

        List<BackendTestData> backends = Collections.singletonList(new BackendTestData());
        test.setBackends(backends);

        test.setMongoInitialDataCount(testMongoService.readAuthorsCount());
        test.setSqlInitialDataCount(testSqlService.readAuthorsCount());
        test.setStatus(TestStatus.STARTED);
        test = testRepository.save(test);

        log.info("Test is created. Test id = {}", test.getId());

        testLockService.lock(test.getId());
        return testBoToDto(test);
    }

    private TestDto testBoToDto(Test test) {
        TestDto result = mapper.map(test, TestDto.class);
        //use mapper config instead
        result.setDurationSec(test.getDuration() != null ? test.getDuration() / 1000 : 0);
        return result;
    }

    public void putTestData(String testId, String dataTemplateId) {
        if (!testLockService.lockedByTest(testId)) {
            throw new LockedException("Test service is not locked or locked by another test");
        }
        Test test = checkFound(testRepository.findById(testId));
        if (isTestExpired(test)) {
            closeTest(test.getId());
            throw new LockedException("Test is expired");
        }
        DataTemplateMongo dataTemplate = this.dataTemplates.get(dataTemplateId);
        if (dataTemplate.getAuthor() == null) {
            throw new RequestParameterException("Incorrect data template. Field 'author' should be defined");
        }
        if (dataTemplate.getText() == null
                || dataTemplate.getText().length() < DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH) {
            throw new RequestParameterException(
                    "Incorrect text of the data template. Min text length should be "
                            + DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH);
        }

        try {
            DataActionMeasurements mongoMeasurements = executeAllAndMeasure(testMongoService, dataTemplate);
            DataActionMeasurements sqlMeasurements = executeAllAndMeasure(testSqlService, dataTemplate);
            putTestMeasurements(testId, mongoMeasurements, sqlMeasurements);
        } catch (Exception e) {
            putTestError(testId, e.toString());
            log.error("An error occurred on test execute", e);
            throw e;
        }
    }

    private DataActionMeasurements executeAllAndMeasure(TestActionService actionService, DataTemplateMongo dataTemplate) {
        DataActionMeasurements measurements = new DataActionMeasurements();

        int createElapsedTime = executeMeasured(() -> {
            actionService.createAuthorAndProduct(dataTemplate);
            actionService.createAuthorAndProduct(dataTemplate);
            actionService.createAuthorAndProduct(dataTemplate);
            return null;
        });
        measurements.setCreateTimeMs(createElapsedTime / 3);

        String subtext = getRandomChunckFromText(dataTemplate.getText());

        if (actionService.getAuthorsCount() == 0) {
            return measurements;
        }

        Optional<String> authorId1 = actionService.getRandomAuthorId();
        Optional<String> authorId2 = actionService.getRandomAuthorId();
        Optional<String> authorId3 = actionService.getRandomAuthorId();
        Optional<String> authorId4 = actionService.getRandomAuthorId();
        Optional<String> authorId5 = actionService.getRandomAuthorId();

        int updateElapsedTime = executeMeasured(() -> {
            actionService.addProductToAuthor(authorId1.get(), dataTemplate);
            actionService.addProductToAuthor(authorId2.get(), dataTemplate);
            actionService.updateProductText(authorId3.get(), subtext);
            return null;
        });
        measurements.setUpdateTimeMs(updateElapsedTime / 3);

        measurements.setFindByNoIndexedFieldTimeMs(
                executeMeasured(() -> {
                    actionService.findByNoIndexedFieldLike(subtext);
                    return null;
                }));

        measurements.setFindByIndexedFieldTimeMs(
                executeMeasured(() -> {
                    actionService.findByIndexedField(dataTemplate.getAuthor().getId());
                    return null;
                }));

        measurements.setRetrieveFullData(
                executeMeasured(() -> {
                    actionService.retrieveFullData(authorId4.get());
                    return null;
                }));

        measurements.setDeleteTimeMs(
                executeMeasured(() -> {
                    actionService.deleteById(authorId5.get());
                    return null;
                }));

        return measurements;
    }

    /**
     * Execute function and return pair, where left is function result, right is elapsed time in ms
     *
     * @param function executable function
     * @return pair, where left is function result, right is elapsed time in ms
     */
    private <T> Pair<T, Integer> executeAndReturnMeasured(Supplier<T> function) {
        long startMs = System.currentTimeMillis();
        T result = function.get();
        long endMs = System.currentTimeMillis();
        return Pair.of(result, (int) (endMs - startMs));
    }

    /**
     * Execute function and return elapsed time in ms
     *
     * @param function executable function
     * @return elapsed time in ms
     */
    private int executeMeasured(Supplier function) {
        long startMs = System.currentTimeMillis();
        function.get();
        long endMs = System.currentTimeMillis();
        return (int) (endMs - startMs);
    }

    private String getRandomChunckFromText(String text) {
        Random rand = new Random();
        int begin = rand.nextInt(text.length() - DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH);
        return text.substring(begin, begin + DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH);
    }

    private void putTestMeasurements(String testId, DataActionMeasurements mongoMeasurements,
                                      DataActionMeasurements sqlMeasurements) {
        testMeasurements.putIfAbsent(testId, new ArrayList<>());
        testMeasurements.get(testId).add(Pair.of(mongoMeasurements, sqlMeasurements));
    }

    private void putTestError(String testId, String error) {
        testErrors.putIfAbsent(testId, new ArrayList<>());
        testErrors.get(testId).add(error);
    }

    private synchronized void setTestMeasurementsToTest(Test test) {

        String testId = test.getId();
        int requests = 0;
        int success = 0;
        int errors = 0;
        if (testMeasurements.get(testId) != null) {
            requests = testMeasurements.get(testId).size();
            success = testMeasurements.get(testId).size();

            if (test.getMongoDbMeasurements() == null) {
                test.setMongoDbMeasurements(new TestMeasurements());
            }
            if (test.getSqlMeasurements() == null) {
                test.setSqlMeasurements(new TestMeasurements());
            }

            testMeasurements.get(testId).forEach(p -> {
                test.getMongoDbMeasurements().addMeasurements(p.getFirst());
                test.getSqlMeasurements().addMeasurements(p.getSecond());
            });

        } else {
            log.warn("Test measurements is missing");
        }

        if (testErrors.get(testId) != null) {
            errors = testErrors.get(testId).size();
            requests += testErrors.get(testId).size();
            if (test.getErrors() == null) {
                test.setErrors(new ArrayList<>());
            }
            test.getErrors().addAll(testErrors.get(testId));
        }

        test.setRequestsCount(test.getRequestsCount() + requests);
        test.setSuccessCount(test.getSuccessCount() + success);
        test.setFailedCount(test.getFailedCount() + errors);

        testMeasurements.remove(testId);
        testErrors.remove(testId);

        log.info("Save test measurements. Test id = {}, requests = {}, success = {} errors = {}",
                testId, requests, success, errors);
    }

    public synchronized void closeTest(String testId) {
        //TODO: save with block document from other threads/nodes
        Test test = checkFound(testRepository.findById(testId));
        if (testLockService.lockedByTest(testId)) {
            testLockService.unlock(testId);
        }

        test.setEndDate(new Date());
        test.setStatus(TestStatus.COMPLETED);

        test.setDuration((int) (test.getEndDate().getTime() - test.getStartDate().getTime()));

        setTestMeasurementsToTest(test);

        if (test.getMongoDbMeasurements() == null) {
            log.warn("Mongo DB measurements is null. test id = {}", testId);
        } else {
            test.getMongoDbMeasurements().calculateAvgValues();
        }
        if (test.getSqlMeasurements() == null) {
            log.warn("SQL DB measurements is null. test id = {}", testId);
        } else {
            test.getSqlMeasurements().calculateAvgValues();
        }
        testRepository.save(test);
        log.info("Test is stopped. Test id = {}", testId);
    }

    public void cancelTest(String testId) {
        Test test = checkFound(testRepository.findById(testId));
        if (!testLockService.lockedByTest(testId) || !TestStatus.STARTED.equals(test.getStatus())) {
            throw new RequestParameterException(format("The test isn't active. Test id=%s", testId));
        }
        testLockService.unlock(testId);
        test.setStatus(TestStatus.CANCELED);
        testRepository.save(test);
    }

    public TestDto getActiveTest() {
        Optional<LockedTest> lockedTest = testLockService.getLockedTest();
        if (lockedTest.isEmpty()) {
            return null;
        } else {
            return testBoToDto(checkFound(testRepository.findById(lockedTest.get().getTestId())));
        }
    }

    private synchronized boolean isTestExpired(Test test) {
        return test.getStartDate().getTime() + TEST_DURATION_MS < new Date().getTime();
    }

    public List<TestDto> getTests() {
        return testRepository.findAll().stream().map(this::testBoToDto).collect(Collectors.toList());
    }

    public List<TestDto> getTests(Integer page, Integer pageSize) {
        return testRepository.findAll(PageRequest.of(page-1, pageSize)).getContent().stream()
                .map(this::testBoToDto).collect(Collectors.toList());
    }

    public Long getTestsCount() {
        return testRepository.count();
    }

    public TestDto getTest(String testId) {
        return testBoToDto(checkFound(testRepository.findById(testId)));
    }
}
