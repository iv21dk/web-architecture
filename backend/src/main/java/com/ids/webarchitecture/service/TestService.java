package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.TestDto;
import com.ids.webarchitecture.exception.LockedException;
import com.ids.webarchitecture.exception.RequestParameterException;
import com.ids.webarchitecture.model.mongo.*;
import com.ids.webarchitecture.repository.mongo.DataTemplateMongoRepository;
import com.ids.webarchitecture.repository.mongo.LockedTestMongoRepository;
import com.ids.webarchitecture.repository.mongo.ProductAuthorMongoRepository;
import com.ids.webarchitecture.repository.mongo.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Supplier;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private DataTemplateMongoRepository dataTemplateMongoRepository;
    @Autowired
    private ProductAuthorMongoRepository productAuthorMongoRepository;
    @Autowired
    private LockedTestMongoRepository lockedTestRepository;

    private ModelMapper mapper = new ModelMapper();

    @PostConstruct
    private void init() {
    }

    @Transactional
    public TestDto createTest() {
        //TODO: lock collection from other processes/threads/servers
        if (locked()) {
            throw new LockedException("Test service already locked");
        }

        Test test = new Test();
        test.setBackendClusterSize((short) 1); //TODO: read from ZooKeeper
        test.setMongoDbClusterSize((short) 1); //TODO: read from property
        test.setSqlClusterSize((short) 1); //TODO: read from property

        List<BackendTestData> backends = Arrays.asList(new BackendTestData());
        test.setBackends(backends);

        test.setInitialDataCount(productAuthorMongoRepository.count());
        test = testRepository.save(test);

        lock(test.getId());
        return testBoToDto(test);
    }

    private boolean locked() {
        return !lockedTestRepository.findAll().isEmpty();
    }

    private boolean lockedByTest(String testId) {
        return lockedTestRepository.findOneByTestId(testId) != null;
    }

    private void lock(String testId) {
        lockedTestRepository.save(new LockedTest(testId));
    }

    private TestDto testBoToDto(Test test) {
        return mapper.map(test, TestDto.class);
    }

    @Transactional
    public void putTestData(String testId, String dataTemplateId) {
        if (!lockedByTest(testId)) {
            throw new LockedException("Test service is not locked or locked by another test");
        }
        DataTemplateMongo dataTemplate = checkFound(dataTemplateMongoRepository.findById(dataTemplateId));
        if  (dataTemplate.getAuthor() == null) {
            throw new RequestParameterException("Incorrect data template. Field 'author' should be defined");
        }
        if  (dataTemplate.getText() == null
                || dataTemplate.getText().length() < DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH) {
            throw new RequestParameterException("Incorrect text of the data template");
        }

        DataActionMeasurements mongoMeasurements = putTestDataMongo(dataTemplate);
        saveTestDataPersistResult(testId, mongoMeasurements);
    }

    private DataActionMeasurements putTestDataMongo(DataTemplateMongo dataTemplate) {
        DataActionMeasurements mongoMeasurements = new DataActionMeasurements();

        int createElapsedTime = executeMeasured(() -> {
            createAuthorAndProductMongo(dataTemplate);
            createAuthorAndProductMongo(dataTemplate);
            return null;
        });
        mongoMeasurements.setCreateTimeMs((Integer) (createElapsedTime / 2));

        List<ProductAuthorMongo> authors = productAuthorMongoRepository.findAll();
        String subtext = getRandomChunckFromText(dataTemplate.getText());

        String authorId1 = getRandomAuthorId(authors);
        String authorId2 = getRandomAuthorId(authors);
        String authorId3 = getRandomAuthorId(authors);

        int updateElapsedTime = executeMeasured(() -> {
            addProductToAuthorMongo(authorId1, dataTemplate);
            addProductToAuthorMongo(authorId2, dataTemplate);
            updateProductText(authorId3, subtext);
            return null;
        });
        mongoMeasurements.setUpdateTimeMs((Integer)(updateElapsedTime / 3));

        mongoMeasurements.setFindByNoIndexedFieldTimeMs(
                executeMeasured(() -> {
                    productAuthorMongoRepository.findByProductsTextLike(subtext);
                    return null;
                }));

        mongoMeasurements.setFindByIndexedFieldTimeMs(
                executeMeasured(() -> {
                    productAuthorMongoRepository.findByAuthorTemplateId(dataTemplate.getAuthor().getId());
                    return null;
                }));

        String authorId4 = getRandomAuthorId(authors);
        mongoMeasurements.setDeleteTimeMs(
                executeMeasured(() -> {
                    productAuthorMongoRepository.deleteById(authorId4);
                    return null;
                }));

        return mongoMeasurements;
    }

    private String getRandomAuthorId(List<ProductAuthorMongo> authors) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(authors.size());
        return authors.get(randomIndex).getId();
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

    private void createAuthorAndProductMongo(DataTemplateMongo dataTemplate) {
        ProductAuthorTemplateMongo authorTemplate = dataTemplate.getAuthor();

        ProductAuthorMongo author = new ProductAuthorMongo();
        author.setAuthorTemplateId(authorTemplate.getId());
        author.setName(authorTemplate.getName() + "-" + UUID.randomUUID());

        author.setProducts(Arrays.asList(getProductFromTemplateMongo(dataTemplate)));

        productAuthorMongoRepository.save(author);
    }

    private ProductMongo getProductFromTemplateMongo(DataTemplateMongo dataTemplate) {
        return mapper.map(dataTemplate, ProductMongo.class);
    }

    private void addProductToAuthorMongo(String authorId, DataTemplateMongo dataTemplate) {
        ProductAuthorMongo author = checkFound(productAuthorMongoRepository.findById(authorId));
        if (author.getProducts() == null) {
            author.setProducts(new ArrayList<>());
        }
        author.getProducts().add(getProductFromTemplateMongo(dataTemplate));
        productAuthorMongoRepository.save(author);
    }

    private String getRandomChunckFromText(String text) {
        Random rand = new Random();
        int begin = rand.nextInt(text.length() - DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH);
        return text.substring(begin, begin + DataTemplateService.MIN_TEMPLATE_TEXT_LENGTH);
    }

    private boolean updateProductText(String authorId, String substring) {
        ProductAuthorMongo author = checkFound(productAuthorMongoRepository.findById(authorId));
        if (author.getProducts() == null) {
            return false;
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(author.getProducts().size());
        ProductMongo product = author.getProducts().get(randomIndex);
        StringBuilder sb = new StringBuilder(product.getText());
        sb.append("\n").append(substring);
        product.setText(sb.toString());
        productAuthorMongoRepository.save(author);
        return true;
    }

    private void saveTestDataPersistResult(String testId, DataActionMeasurements mongoMeasurements) {
        //TODO: block required test from read from other treads
        Test test = checkFound(testRepository.findById(testId));
        if (test.getMongoDbMeasurements() == null) {
            test.setMongoDbMeasurements(new TestMeasurements());
        }
        test.getMongoDbMeasurements().addMeasurements(mongoMeasurements);
        test.setRequestsCount(test.getRequestsCount()+1);
        testRepository.save(test);
    }
}
