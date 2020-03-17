package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAuthorMongoRepository extends MongoRepository<ProductAuthorTemplateMongo, String> {
}
