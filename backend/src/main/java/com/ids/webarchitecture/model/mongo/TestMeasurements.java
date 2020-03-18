package com.ids.webarchitecture.model.mongo;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> getCreateTimeMs() {
        return createTimeMs;
    }

    public void setCreateTimeMs(List<Integer> createTimeMs) {
        this.createTimeMs = createTimeMs;
    }

    public List<Integer> getFindByIndexedFieldTimeMs() {
        return findByIndexedFieldTimeMs;
    }

    public void setFindByIndexedFieldTimeMs(List<Integer> findByIndexedFieldTimeMs) {
        this.findByIndexedFieldTimeMs = findByIndexedFieldTimeMs;
    }

    public List<Integer> getFindByNoIndexedFieldTimeMs() {
        return findByNoIndexedFieldTimeMs;
    }

    public void setFindByNoIndexedFieldTimeMs(List<Integer> findByNoIndexedFieldTimeMs) {
        this.findByNoIndexedFieldTimeMs = findByNoIndexedFieldTimeMs;
    }

    public List<Integer> getUpdateTimeMs() {
        return updateTimeMs;
    }

    public void setUpdateTimeMs(List<Integer> updateTimeMs) {
        this.updateTimeMs = updateTimeMs;
    }

    public List<Integer> getDeleteTimeMs() {
        return deleteTimeMs;
    }

    public void setDeleteTimeMs(List<Integer> deleteTimeMs) {
        this.deleteTimeMs = deleteTimeMs;
    }
}
