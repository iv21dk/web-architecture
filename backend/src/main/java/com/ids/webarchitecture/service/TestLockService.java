package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.mongo.LockedTest;
import com.ids.webarchitecture.repository.mongo.LockedTestMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestLockService {

    @Autowired
    private LockedTestMongoRepository lockedTestRepository;

    public boolean locked() {
        return !lockedTestRepository.findAll().isEmpty();
    }

    public boolean lockedByTest(String testId) {
        return lockedTestRepository.findOneByTestId(testId) != null;
    }

    public void lock(String testId) {
        lockedTestRepository.save(new LockedTest(testId));
    }

    public void unlock(String testId) {
        lockedTestRepository.deleteByTestId(testId);
    }

}
