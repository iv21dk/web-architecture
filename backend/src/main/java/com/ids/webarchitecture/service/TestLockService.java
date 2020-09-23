package com.ids.webarchitecture.service;

import com.ids.webarchitecture.model.mongo.LockedTest;

import java.util.Optional;

public interface TestLockService {
    boolean locked();
    boolean lockedByTest(String testId);
    void lock(String testId);
    void unlock(String testId);
    Optional<LockedTest> getLockedTest();
}
