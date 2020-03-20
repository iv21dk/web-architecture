package com.ids.webarchitecture.model.mongo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestMeasurements {
    private Integer avgCreateTimeMs;
    private Integer avgFindByIndexedFieldTimeMs;
    private Integer avgFindByNoIndexedFieldTimeMs;
    private Integer avgUpdateTimeMs;
    private Integer avgDeleteTimeMs;

    private List<Integer> createTimeMs = new ArrayList<>();
    private List<Integer> findByIndexedFieldTimeMs = new ArrayList<>();
    private List<Integer> findByNoIndexedFieldTimeMs = new ArrayList<>();
    private List<Integer> updateTimeMs = new ArrayList<>();
    private List<Integer> deleteTimeMs = new ArrayList<>();
}
