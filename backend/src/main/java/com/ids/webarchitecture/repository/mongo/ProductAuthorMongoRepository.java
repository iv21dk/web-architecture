package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplate;
import com.ids.webarchitecture.model.mongo.ProductAuthor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductAuthorMongoRepository extends MongoRepository<ProductAuthor, String> {
}
