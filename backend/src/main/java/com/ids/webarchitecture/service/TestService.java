package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.TestDto;

import java.util.List;

public interface TestService {
    TestDto createTest();
    void putTestData(String testId, String dataTemplateId);
    void closeTest(String testId);
    void cancelTest(String testId);
    TestDto getActiveTest();
    List<TestDto> getTests();
    List<TestDto> getTests(Integer page, Integer pageSize);
    Long getTestsCount();
    TestDto getTest(String testId);
}
