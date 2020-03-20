package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test, String> {
}
