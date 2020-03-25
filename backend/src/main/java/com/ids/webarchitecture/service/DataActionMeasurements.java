package com.ids.webarchitecture.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataActionMeasurements {
    private Integer createTimeMs;
    private Integer findByIndexedFieldTimeMs;
    private Integer findByNoIndexedFieldTimeMs;
    private Integer updateTimeMs;
    private Integer deleteTimeMs;
}
