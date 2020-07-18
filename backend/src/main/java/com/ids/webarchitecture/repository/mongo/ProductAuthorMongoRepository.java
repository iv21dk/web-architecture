package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.ProductAuthorMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductAuthorMongoRepository extends MongoRepository<ProductAuthorMongo, String> {

    <T> List<T> findByProductsTextLike(String substring, Class<T> projection);
    <T> List<T> findByAuthorTemplateId(String authorTemplateId, Class<T> projection);
}
