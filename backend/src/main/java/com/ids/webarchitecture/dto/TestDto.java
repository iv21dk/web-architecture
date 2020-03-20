package com.ids.webarchitecture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ids.webarchitecture.model.mongo.BackendTestData;
import com.ids.webarchitecture.model.mongo.TestMeasurements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDto extends IdentifiableDto {
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date startDate;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date endDate;
    private Long initialDataCount;
    private Integer requestsCount;
    private Short mongoDbClusterSize;
    private Short sqlClusterSize;
    private Short backendClusterSize;
    private TestMeasurementsDto mongoDbMeasurements;
    private TestMeasurementsDto sqlMeasurements;
    private List<BackendTestData> backends = new ArrayList<>();

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(Integer requestsCount) {
        this.requestsCount = requestsCount;
    }

    public Short getMongoDbClusterSize() {
        return mongoDbClusterSize;
    }

    public void setMongoDbClusterSize(Short mongoDbClusterSize) {
        this.mongoDbClusterSize = mongoDbClusterSize;
    }

    public Short getSqlClusterSize() {
        return sqlClusterSize;
    }

    public void setSqlClusterSize(Short sqlClusterSize) {
        this.sqlClusterSize = sqlClusterSize;
    }

    public Short getBackendClusterSize() {
        return backendClusterSize;
    }

    public void setBackendClusterSize(Short backendClusterSize) {
        this.backendClusterSize = backendClusterSize;
    }

    public TestMeasurementsDto getMongoDbMeasurements() {
        return mongoDbMeasurements;
    }

    public void setMongoDbMeasurements(TestMeasurementsDto mongoDbMeasurements) {
        this.mongoDbMeasurements = mongoDbMeasurements;
    }

    public TestMeasurementsDto getSqlMeasurements() {
        return sqlMeasurements;
    }

    public void setSqlMeasurements(TestMeasurementsDto sqlMeasurements) {
        this.sqlMeasurements = sqlMeasurements;
    }

    public List<BackendTestData> getBackends() {
        return backends;
    }

    public void setBackends(List<BackendTestData> backends) {
        this.backends = backends;
    }

    public Long getInitialDataCount() {
        return initialDataCount;
    }

    public void setInitialDataCount(Long initialDataCount) {
        this.initialDataCount = initialDataCount;
    }
}
