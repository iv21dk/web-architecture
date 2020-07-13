package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.service.DataActionMeasurements;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestMeasurements {

    private Integer avgCreateTimeMs;
    private Integer avgFindByIndexedFieldTimeMs;
    private Integer avgFindByNoIndexedFieldTimeMs;
    private Integer avgRetrieveFullData;
    private Integer avgUpdateTimeMs;
    private Integer avgDeleteTimeMs;

    private List<Integer> createTimeMs = new ArrayList<>();
    private List<Integer> findByIndexedFieldTimeMs = new ArrayList<>();
    private List<Integer> findByNoIndexedFieldTimeMs = new ArrayList<>();
    private List<Integer> getRetrieveDataMs = new ArrayList<>();
    private List<Integer> updateTimeMs = new ArrayList<>();
    private List<Integer> deleteTimeMs = new ArrayList<>();

    public void addMeasurements(DataActionMeasurements measurements) {
        createTimeMs.add(measurements.getCreateTimeMs());
        findByIndexedFieldTimeMs.add(measurements.getFindByIndexedFieldTimeMs());
        findByNoIndexedFieldTimeMs.add(measurements.getFindByNoIndexedFieldTimeMs());
        updateTimeMs.add(measurements.getUpdateTimeMs());
        deleteTimeMs.add(measurements.getDeleteTimeMs());
        getRetrieveDataMs.add(measurements.getRetrieveFullData());
    }

    public void calculateAvgValues() {
        avgCreateTimeMs = calculateAvgValue(this.createTimeMs);
        avgFindByIndexedFieldTimeMs = calculateAvgValue(this.findByIndexedFieldTimeMs);
        avgFindByNoIndexedFieldTimeMs = calculateAvgValue(this.findByNoIndexedFieldTimeMs);
        avgRetrieveFullData = calculateAvgValue(this.getRetrieveDataMs);
        avgUpdateTimeMs = calculateAvgValue(this.updateTimeMs);
        avgDeleteTimeMs = calculateAvgValue(this.deleteTimeMs);
    }

    private Integer calculateAvgValue(List<Integer> measurements) {
        int fullTime = 0;
        int measurementsCount = 0;
        for (Integer measurement : measurements) {
            if (measurement != null) {
                fullTime += measurement;
                measurementsCount++;
            }
        }
        return measurementsCount == 0 ? 0 : fullTime / measurementsCount;
    }
}
