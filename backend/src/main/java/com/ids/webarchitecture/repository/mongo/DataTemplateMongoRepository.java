package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataTemplateMongoRepository extends MongoRepository<DataTemplate, String> {
}
