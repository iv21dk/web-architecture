package com.ids.webarchitecture.dto;

import com.ids.webarchitecture.model.mongo.BackendTestData;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TestDto extends IdentifiableDto {
    private Date startDate;
    private Date endDate;
    private Long mongoInitialDataCount;
    private Long sqlInitialDataCount;
    private Integer requestsCount;
    private Short mongoDbClusterSize;
    private Short sqlClusterSize;
    private Short backendClusterSize;
    private TestMeasurementsDto mongoDbMeasurements;
    private TestMeasurementsDto sqlMeasurements;
    private List<BackendTestData> backends = new ArrayList<>();
}
