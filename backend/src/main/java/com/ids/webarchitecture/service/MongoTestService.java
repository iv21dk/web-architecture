package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductMongo;
import com.ids.webarchitecture.repository.mongo.ProductAuthorMongoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;

@Service
public class MongoTestService extends AbstractTestService {

    @Autowired
    private ProductAuthorMongoRepository productAuthorMongoRepository;

    private ModelMapper mapper = new ModelMapper();

    @PostConstruct
    private void init() {
        authorIds = productAuthorMongoRepository.findAll().stream().map(i -> i.getId()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createAuthorAndProduct(DataTemplateMongo dataTemplate) {
        ProductAuthorTemplateMongo authorTemplate = dataTemplate.getAuthor();

        ProductAuthorMongo author = new ProductAuthorMongo();
        author.setAuthorTemplateId(authorTemplate.getId());
        author.setName(authorTemplate.getName() + "-" + UUID.randomUUID());

        author.setProducts(Arrays.asList(getProductFromTemplate(dataTemplate)));

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
        StringBuilder sb = new StringBuilder(product.getText());
        sb.append("\n").append(substring);
        product.setText(sb.toString());
        productAuthorMongoRepository.save(author);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public void findByNoIndexedFieldLike(String substring) {
        productAuthorMongoRepository.findByProductsTextLike(substring);
    }

    @Override
    @Transactional(readOnly = true)
    public void findByIndexedField(String value) {
        productAuthorMongoRepository.findByAuthorTemplateId(value);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        productAuthorMongoRepository.deleteById(id);
    }

}
