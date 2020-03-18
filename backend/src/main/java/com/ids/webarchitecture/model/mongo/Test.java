package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.IdentifiableEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Test extends IdentifiableEntity {
    private Date startDate = new Date();
    private Integer requestsCount = 0;
    private Short mongoDbClusterSize = 0;
    private Short sqlClusterSize = 0;
    private Short backendClusterSize = 0;
    private TestMeasurements mongoDbMeasurements = new TestMeasurements();
    private TestMeasurements sqlMeasurements = new TestMeasurements();
    private List<BackendTestData> backends = new ArrayList<>();

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public TestMeasurements getMongoDbMeasurements() {
        return mongoDbMeasurements;
    }

    public void setMongoDbMeasurements(TestMeasurements mongoDbMeasurements) {
        this.mongoDbMeasurements = mongoDbMeasurements;
    }

    public TestMeasurements getSqlMeasurements() {
        return sqlMeasurements;
    }

    public void setSqlMeasurements(TestMeasurements sqlMeasurements) {
        this.sqlMeasurements = sqlMeasurements;
    }

    public List<BackendTestData> getBackends() {
        return backends;
    }

    public void setBackends(List<BackendTestData> backends) {
        this.backends = backends;
    }
}
