package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.Test;
import com.ids.webarchitecture.model.mongo.TestStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<Test, String>, TestRepositoryCustom {
    List<Test> findAllByStatusOrderByStartDate(TestStatus status);
    List<Test> findAllByStatusOrderByStartDate(TestStatus status, Pageable pageable);
    Long countByStatus(TestStatus completed);
}
