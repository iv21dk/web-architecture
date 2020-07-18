package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.IdentifiableEntity;
import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductMongo;
import com.ids.webarchitecture.repository.mongo.ProductAuthorMongoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;
import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class MongoTestService extends AbstractTestService {
    Logger log = LoggerFactory.getLogger(MongoTestService.class);

    @Autowired
    private ProductAuthorMongoRepository productAuthorMongoRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public void createAuthorAndProduct(DataTemplateMongo dataTemplate) {
        ProductAuthorTemplateMongo authorTemplate = dataTemplate.getAuthor();

        ProductAuthorMongo author = new ProductAuthorMongo();
        author.setAuthorTemplateId(authorTemplate.getId());
        author.setName(authorTemplate.getName() + "-" + UUID.randomUUID());

        author.setProducts(Collections.singletonList(getProductFromTemplate(dataTemplate)));

        productAuthorMongoRepository.save(author);
    }

    @Override
    @Transactional
    public void addProductToAuthor(String authorId, DataTemplateMongo dataTemplate) {
        ProductAuthorMongo author = checkFound(productAuthorMongoRepository.findById(authorId));
        if (author.getProducts() == null) {
            author.setProducts(new ArrayList<>());
        }
        author.getProducts().add(getProductFromTemplate(dataTemplate));
        productAuthorMongoRepository.save(author);
    }

    private ProductMongo getProductFromTemplate(DataTemplateMongo dataTemplate) {
        return mapper.map(dataTemplate, ProductMongo.class);
    }

    @Override
    @Transactional
    public boolean updateProductText(String authorId, String substring) {
        ProductAuthorMongo author = checkFound(productAuthorMongoRepository.findById(authorId));
        if (author.getProducts() == null) {
            return false;
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(author.getProducts().size());
        ProductMongo product = author.getProducts().get(randomIndex);
        product.setText(product.getText() + "\n" + substring);
        productAuthorMongoRepository.save(author);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public void findByNoIndexedFieldLike(String substring) {
        List<IdentifiableEntity> result = productAuthorMongoRepository.findByProductsTextLike(
                substring, IdentifiableEntity.class);
        String action = "Find by no indexed field by value=" + substring + ". ";
        notEmpty(result, action + "Empty result");
        notNull(result.get(0).getId(), action + "Incorrect result item.");
//        notEmpty(result.get(0).getProducts(), action + "Empty products list");
//        notNull(result.get(0).getProducts().get(0).getName(), action + "Incorrect product text");
    }

    @Override
    @Transactional(readOnly = true)
    public void findByIndexedField(String value) {
        List<IdentifiableEntity> result = productAuthorMongoRepository.findByAuthorTemplateId(
                value, IdentifiableEntity.class);
        String action = "Find by indexed field by value=" + value + ". ";
        notEmpty(result, action + "Empty result.");
        notNull(result.get(0).getId(), action + "Incorrect result item.");
//        notEmpty(result.get(0).getProducts(), action + "Empty products list.");
//        notNull(result.get(0).getProducts().get(0).getName(), action + "Incorrect product text");
    }

    @Override
    public void retrieveFullData(String authorId) {
        ProductAuthorMongo result = checkFound(productAuthorMongoRepository.findById(authorId));
        String action = "Retrieve full author data by id=" + authorId + ". ";
        notNull(result.getAuthorTemplateId(), action + "Incorrect template id.");
        notNull(result.getName(), action + "Incorrect name.");
        notEmpty(result.getProducts(), action + "Empty products list.");
        notNull(result.getProducts().get(0).getName(), action + "Incorrect product name");
        notNull(result.getProducts().get(0).getText(), action + "Incorrect product text");
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        super.deleteById(id);
        productAuthorMongoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    protected List<String> getAuthorIds() {
        long startMs = System.currentTimeMillis();
        List<String> authorIds = productAuthorMongoRepository.findAll()
                .stream().map(IdentifiableEntity::getId).collect(Collectors.toList());
        long endMs = System.currentTimeMillis();
        log.info("Read all author ids in {} ms. Count {}.", endMs - startMs, authorIds.size());
        return authorIds;
    }

    @Override
    protected long readAuthorsCount() {
        return productAuthorMongoRepository.count();
    }
}
