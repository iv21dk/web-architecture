package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.LockedTest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LockedTestMongoRepository extends MongoRepository<LockedTest, String> {
    LockedTest findOneByTestId(String testId);
    void deleteByTestId(String testId);
}
