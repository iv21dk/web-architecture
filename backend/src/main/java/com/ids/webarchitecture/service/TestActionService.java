package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;

import java.util.List;
import java.util.Set;

public interface TestActionService {
    long getAuthorsCount();
    String getRandomAuthorId();
    void createAuthorAndProduct(DataTemplateMongo dataTemplate);
    void addProductToAuthor(String authorId, DataTemplateMongo dataTemplate);
    boolean updateProductText(String authorId, String substring);
    void findByNoIndexedFieldLike(String substring);
    void findByIndexedField(String value);
    void deleteById(String id);
}
