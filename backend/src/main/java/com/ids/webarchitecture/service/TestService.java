package com.ids.webarchitecture.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ids.webarchitecture.dto.TestDto;
import com.ids.webarchitecture.model.mongo.Test;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private ObjectMapper mapper = new ObjectMapper();

    public TestDto createTest() {
        Test test = new Test();
        test.setBackendClusterSize((short) 1); //TODO: read from ZooKeeper
        test.setMongoDbClusterSize((short) 1); //TODO: read from property
        test.setSqlClusterSize((short) 1); //TODO: read from property
        return testBoToDto(test);
    }

    private TestDto testBoToDto(Test test) {
        return mapper.convertValue(test, TestDto.class);
    }



}
