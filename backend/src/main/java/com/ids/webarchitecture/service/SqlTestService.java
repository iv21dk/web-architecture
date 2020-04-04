package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.hibernate.Product;
import com.ids.webarchitecture.model.hibernate.ProductAuthor;
import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import com.ids.webarchitecture.repository.ProductAuthorRepository;
import com.ids.webarchitecture.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;

@Service
public class SqlTestService extends AbstractTestService {

    @Autowired
    private ProductAuthorRepository productAuthorRepository;
    @Autowired
    private ProductRepository productRepository;

    private ModelMapper mapper = new ModelMapper();

    @PostConstruct
    private void init() {
        authorIds = productAuthorRepository.findAll().stream().map(i -> i.getId()).collect(Collectors.toList());
    }

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
        //ProductAuthor author = checkFound(productAuthorRepository.findById(authorId));
        List<Product> products = productRepository.findByAuthorId(authorId);
        Random rand = new Random();
        int randomIndex = rand.nextInt(products.size());
        Product product = products.get(randomIndex);
        StringBuilder sb = new StringBuilder(product.getText());
        sb.append("\n").append(substring);
        product.setText(sb.toString());
        productRepository.save(product);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public void findByNoIndexedFieldLike(String substring) {
        productRepository.findByTextLike(substring);
    }

    @Override
    @Transactional(readOnly = true)
    public void findByIndexedField(String value) {
        productAuthorRepository.findByAuthorTemplateId(value);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        productAuthorRepository.deleteById(id);
    }
}
