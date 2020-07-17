package com.ids.webarchitecture.dto;

import com.ids.webarchitecture.model.mongo.BackendTestData;
import com.ids.webarchitecture.model.mongo.TestStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TestDto extends IdentifiableDto {
    private Date startDate;
    private Date endDate;
    private Long mongoInitialDataCount = 0L;
    private Long sqlInitialDataCount = 0L;
    private Integer requestsCount  = 0;
    private Integer successCount  = 0;
    private Integer failedCount  = 0;
    private Short mongoDbClusterSize  = 0;
    private Short sqlClusterSize  = 0;
    private Short backendClusterSize  = 0;
    private TestMeasurementsDto mongoDbMeasurements = new TestMeasurementsDto();
    private TestMeasurementsDto sqlMeasurements = new TestMeasurementsDto();
    private List<BackendTestData> backends = new ArrayList<>();
    private Integer durationSec = 0;
    private TestStatus status;
}
