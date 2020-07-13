package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;

import java.util.Optional;

public interface TestActionService {
    int getAuthorsCount();
    Optional<String> getRandomAuthorId();
    void createAuthorAndProduct(DataTemplateMongo dataTemplate);
    void addProductToAuthor(String authorId, DataTemplateMongo dataTemplate);
    boolean updateProductText(String authorId, String substring);
    void findByNoIndexedFieldLike(String substring);
    void findByIndexedField(String value);
    void retrieveFullData(String authorId);
    void deleteById(String id);
}
