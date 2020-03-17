package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTemplateMongoRepository extends MongoRepository<DataTemplateMongo, String> {
}
