package com.ids.webarchitecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestMeasurementsDto {
    private Integer avgCreateTimeMs;
    private Integer avgFindByIndexedFieldTimeMs;
    private Integer avgFindByNoIndexedFieldTimeMs;
    private Integer avgUpdateTimeMs;
    private Integer avgDeleteTimeMs;
}
