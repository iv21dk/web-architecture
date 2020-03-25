package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.ProductAuthorMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductAuthorMongoRepository extends MongoRepository<ProductAuthorMongo, String> {

    List<ProductAuthorMongo> findByProductsTextLike(String substring);
    List<ProductAuthorMongo> findByAuthorTemplateId(String authorTemplateId);
}
