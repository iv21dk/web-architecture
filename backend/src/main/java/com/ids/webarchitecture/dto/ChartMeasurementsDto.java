package com.ids.webarchitecture.dto;

import lombok.Data;

@Data
public class ChartMeasurementsDto {
    private Long initialDataCount = 0L;
    private String displayDataCount = "";
    private TestMeasurementsDto mongoDbMeasurements = new TestMeasurementsDto();
    private TestMeasurementsDto sqlMeasurements = new TestMeasurementsDto();
}
