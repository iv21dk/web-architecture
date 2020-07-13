package com.ids.webarchitecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestMeasurementsDto {
    private Integer avgCreateTimeMs = 0;
    private Integer avgFindByIndexedFieldTimeMs = 0;
    private Integer avgFindByNoIndexedFieldTimeMs = 0;
    private Integer avgRetrieveFullData = 0;
    private Integer avgUpdateTimeMs = 0;
    private Integer avgDeleteTimeMs = 0;
}
