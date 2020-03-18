package com.ids.webarchitecture.dto;

import java.util.ArrayList;
import java.util.List;

public class TestMeasurementsDto {
    private Integer avgCreateTimeMs;
    private Integer avgFindByIndexedFieldTimeMs;
    private Integer avgFindByNoIndexedFieldTimeMs;
    private Integer avgUpdateTimeMs;
    private Integer avgDeleteTimeMs;

    public Integer getAvgCreateTimeMs() {
        return avgCreateTimeMs;
    }

    public Integer getAvgFindByIndexedFieldTimeMs() {
        return avgFindByIndexedFieldTimeMs;
    }

    public Integer getAvgFindByNoIndexedFieldTimeMs() {
        return avgFindByNoIndexedFieldTimeMs;
    }

    public Integer getAvgUpdateTimeMs() {
        return avgUpdateTimeMs;
    }

    public Integer getAvgDeleteTimeMs() {
        return avgDeleteTimeMs;
    }

    public void setAvgCreateTimeMs(Integer avgCreateTimeMs) {
        this.avgCreateTimeMs = avgCreateTimeMs;
    }

    public void setAvgFindByIndexedFieldTimeMs(Integer avgFindByIndexedFieldTimeMs) {
        this.avgFindByIndexedFieldTimeMs = avgFindByIndexedFieldTimeMs;
    }

    public void setAvgFindByNoIndexedFieldTimeMs(Integer avgFindByNoIndexedFieldTimeMs) {
        this.avgFindByNoIndexedFieldTimeMs = avgFindByNoIndexedFieldTimeMs;
    }

    public void setAvgUpdateTimeMs(Integer avgUpdateTimeMs) {
        this.avgUpdateTimeMs = avgUpdateTimeMs;
    }

    public void setAvgDeleteTimeMs(Integer avgDeleteTimeMs) {
        this.avgDeleteTimeMs = avgDeleteTimeMs;
    }

}
