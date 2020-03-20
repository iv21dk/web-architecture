package com.ids.webarchitecture.controller;

import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.TestDto;
import com.ids.webarchitecture.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/api/tests")
    @ResponseStatus(HttpStatus.CREATED)
    public TestDto createTest() {
        return testService.createTest();
    }

    @PutMapping("/api/tests/{test-id}/data/{data-template-id}")
    @ResponseStatus(HttpStatus.OK)
    public void putTestData(
            @PathVariable("test-id") String testId,
            @PathVariable("data-template-id") String dataTemplateId) {
        testService.putTestData(testId, dataTemplateId);
    }

}
