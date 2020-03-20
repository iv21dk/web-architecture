package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.ProductAuthorMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductAuthorMongoRepository extends MongoRepository<ProductAuthorMongo, String> {
}
