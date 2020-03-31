package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.hibernate.ProductAuthor;
import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import com.ids.webarchitecture.repository.ProductAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TestSqlService implements TestActionService {

    @Autowired
    private ProductAuthorRepository productAuthorRepository;

    @Override
    public long getAuthorsCount() {
        return 0;
    }

    @Override
    public String getRandomAuthorId() {
        return null;
    }

    @Override
    public void createAuthorAndProduct(DataTemplateMongo dataTemplate) {
        ProductAuthorTemplateMongo authorTemplate = dataTemplate.getAuthor();

        ProductAuthor author = new ProductAuthor();
        author.setAuthorTemplateId(authorTemplate.getId());
        author.setName(authorTemplate.getName() + "-" + UUID.randomUUID());
        productAuthorRepository.save(author);

        //TODO: create product
    }

    @Override
    public void addProductToAuthor(String authorId, DataTemplateMongo dataTemplate) {

    }

    @Override
    public boolean updateProductText(String authorId, String substring) {
        return false;
    }

    @Override
    public void findByNoIndexedFieldLike(String substring) {

    }

    @Override
    public void findByIndexedField(String value) {

    }

    @Override
    public void deleteById(String id) {

    }
}
