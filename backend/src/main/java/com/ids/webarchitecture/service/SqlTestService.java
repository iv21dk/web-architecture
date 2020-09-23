package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.IdentifiableEntity;
import com.ids.webarchitecture.model.hibernate.Product;
import com.ids.webarchitecture.model.hibernate.ProductAuthor;
import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import com.ids.webarchitecture.repository.ProductAuthorRepository;
import com.ids.webarchitecture.repository.ProductRepository;
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
public class SqlTestService extends AbstractTestService {
    Logger log = LoggerFactory.getLogger(SqlTestService.class);

    @Autowired
    private ProductAuthorRepository productAuthorRepository;
    @Autowired
    private ProductRepository productRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public void createAuthorAndProduct(DataTemplateMongo dataTemplate) {
        ProductAuthorTemplateMongo authorTemplate = dataTemplate.getAuthor();

        ProductAuthor author = new ProductAuthor();
        author.setAuthorTemplateId(authorTemplate.getId());
        author.setName(authorTemplate.getName() + "-" + UUID.randomUUID());
        author = productAuthorRepository.save(author);

        Product product = getProductFromTemplate(dataTemplate);
        product.setAuthor(author);
        productRepository.save(product);
    }

    private Product getProductFromTemplate(DataTemplateMongo dataTemplate) {
        Product product = mapper.map(dataTemplate, Product.class);
        product.setId(null);
        return product;
    }

    @Override
    @Transactional
    public void addProductToAuthor(String authorId, DataTemplateMongo dataTemplate) {
        ProductAuthor author = checkFound(productAuthorRepository.findById(authorId));
        Product product = getProductFromTemplate(dataTemplate);
        product.setAuthor(author);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public boolean updateProductText(String authorId, String substring) {
        List<Product> products = productRepository.findByAuthorId(authorId);
        Random rand = new Random();
        int randomIndex = rand.nextInt(products.size());
        Product product = products.get(randomIndex);
        product.setText(product.getText() + "\n" + substring);
        productRepository.save(product);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public void findByNoIndexedFieldLike(String substring) {
        List<Product> result = productRepository.findByTextLike("%" + substring + "%");
        String action = "Find by no indexed field by value=" + substring + ". ";
        notEmpty(result, action + "Empty result");
        notNull(result.get(0).getText(), action + "Incorrect item");
    }

    @Override
    @Transactional(readOnly = true)
    public void findByIndexedField(String value) {
        List<IdentifiableEntity> result = productAuthorRepository.findByAuthorTemplateId(
                value, IdentifiableEntity.class);
        String action = "Find by indexed field by value=" + value + ". ";
        notEmpty(result, action + "Empty result");
        notNull(result.get(0).getId(), action + "Incorrect item");
//        notEmpty(result.get(0).getProducts(), action + "Empty products list.");
//        notNull(result.get(0).getProducts().get(0).getName(), action + "Incorrect product text");
    }

    @Override
    @Transactional(readOnly = true)
    public void retrieveFullData(String authorId) {
        ProductAuthor result = checkFound(productAuthorRepository.findByIdFull(authorId));
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
        productAuthorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    protected List<String> getAuthorIds() {
        long startMs = System.currentTimeMillis();
        List<String> authorIds = productAuthorRepository.findAll()
                .stream().map(IdentifiableEntity::getId).collect(Collectors.toList());
        long endMs = System.currentTimeMillis();
        log.info("Read all author ids in {} ms. Count {}.", endMs - startMs, authorIds.size());
        return authorIds;
    }

    @Override
    public long readAuthorsCount() {
        return productAuthorRepository.count();
    }
}
