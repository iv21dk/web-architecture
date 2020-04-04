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
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Supplier;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;

@Service
public class TestService {

    Logger log = LoggerFactory.getLogger(TestService.class);

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

    @PostConstruct
    private void init() {
    }

    @Transactional
    public TestDto createTest() {
        if (testLockService.locked()) {
            throw new LockedException("Test service already locked");
        }

        Test test = new Test();
        test.setBackendClusterSize((short) 1); //TODO: read from ZooKeeper
        test.setMongoDbClusterSize((short) 1); //TODO: read from property
        test.setSqlClusterSize((short) 1); //TODO: read from property

        List<BackendTestData> backends = Arrays.asList(new BackendTestData());
        test.setBackends(backends);

        test.setMongoInitialDataCount(testMongoService.getAuthorsCount());
        test.setSqlInitialDataCount(testSqlService.getAuthorsCount());
        test = testRepository.save(test);

        testLockService.lock(test.getId());
        return testBoToDto(test);
    }

    private TestDto testBoToDto(Test test) {
        return mapper.map(test, TestDto.class);
    }

    public void putTestData(String testId, String dataTemplateId) {
        if (!testLockService.lockedByTest(testId)) {
            throw new LockedException("Test service is not locked or locked by another test");
        }
        DataTemplateMongo dataTemplate = checkFound(dataTemplateMongoRepository.findById(dataTemplateId));
        if (dataTemplate.getAuthor() == null) {
            throw new RequestParameterException("Incorrect data template. Field 'author' should be defined");
        }
        if (dataTemplate.getText() == null
                || dataTemplate.getText().length() < DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH) {
            throw new RequestParameterException("Incorrect text of the data template");
        }

        try {
            DataActionMeasurements mongoMeasurements = executeAllAndMeasure(testMongoService, dataTemplate);
            DataActionMeasurements sqlMeasurements = executeAllAndMeasure(testSqlService, dataTemplate);
            saveTestDataPersistResult(testId, mongoMeasurements, sqlMeasurements);
        } catch (Exception e) {
            saveTestDataError(testId, e.toString());
            throw e;
        }

    }

    private DataActionMeasurements executeAllAndMeasure(TestActionService actionService, DataTemplateMongo dataTemplate) {
        DataActionMeasurements measurements = new DataActionMeasurements();

        int createElapsedTime = executeMeasured(() -> {
            actionService.createAuthorAndProduct(dataTemplate);
            actionService.createAuthorAndProduct(dataTemplate);
            return null;
        });
        measurements.setCreateTimeMs(createElapsedTime / 2);

        String subtext = getRandomChunckFromText(dataTemplate.getText());

        if (actionService.getAuthorsCount() == 0) {
            return measurements;
        }

        Optional<String> authorId1 = actionService.getRandomAuthorId();
        Optional<String> authorId2 = actionService.getRandomAuthorId();
        Optional<String> authorId3 = actionService.getRandomAuthorId();
        Optional<String> authorId4 = actionService.getRandomAuthorId();

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

        measurements.setDeleteTimeMs(
                executeMeasured(() -> {
                    actionService.deleteById(authorId4.get());
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
        return Pair.of(result, Integer.valueOf((int) (endMs - startMs)));
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

    private void saveTestDataPersistResult(String testId, DataActionMeasurements mongoMeasurements,
                                           DataActionMeasurements sqlMeasurements) {
        //TODO: save async on different thread with block document from other threads/nodes
        Test test = checkFound(testRepository.findById(testId));
        if (test.getMongoDbMeasurements() == null) {
            test.setMongoDbMeasurements(new TestMeasurements());
        }
        if (test.getSqlMeasurements() == null) {
            test.setSqlMeasurements(new TestMeasurements());
        }
        test.getMongoDbMeasurements().addMeasurements(mongoMeasurements);
        test.getSqlMeasurements().addMeasurements(sqlMeasurements);
        test.setRequestsCount(test.getRequestsCount() + 1);
        testRepository.save(test);
    }

    private void saveTestDataError(String testId, String trace) {
        //TODO: save async on different thread with block document from other threads/nodes
        Test test = checkFound(testRepository.findById(testId));
        test.setRequestsCount(test.getRequestsCount() + 1);
        test.getErrors().add(trace);
        testRepository.save(test);
    }

    public void closeTest(String testId) {
        if (testLockService.lockedByTest(testId)) {
            testLockService.unlock(testId);
        }
        Test test = checkFound(testRepository.findById(testId));
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
        test.setEndDate(new Date());
        testRepository.save(test);
    }

}
