package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.mongo.LockedTest;
import com.ids.webarchitecture.repository.mongo.LockedTestMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TestLockServiceImpl implements TestLockService {

    @Autowired
    private LockedTestMongoRepository lockedTestRepository;

    @Override
    @Transactional
    public boolean locked() {
        return !lockedTestRepository.findAll().isEmpty();
    }

    @Override
    @Transactional
    public synchronized boolean lockedByTest(String testId) {
        return lockedTestRepository.findOneByTestId(testId) != null;
    }

    @Override
    @Transactional
    public void lock(String testId) {
        lockedTestRepository.save(new LockedTest(testId));
    }

    @Override
    @Transactional
    public void unlock(String testId) {
        lockedTestRepository.deleteByTestId(testId);
    }

    @Override
    @Transactional
    public Optional<LockedTest> getLockedTest() {
        return lockedTestRepository.findAll().stream().findFirst();
    }

}
