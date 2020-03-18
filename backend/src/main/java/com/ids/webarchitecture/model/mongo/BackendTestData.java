package com.ids.webarchitecture.model.mongo;

import java.util.ArrayList;
import java.util.List;

public class BackendTestData {

    private String name;
    private Integer requestsCount = 0;
    private List<Integer> cpuLoading = new ArrayList<>();
    private Integer avgCpuLoading;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(Integer requestsCount) {
        this.requestsCount = requestsCount;
    }

    public List<Integer> getCpuLoading() {
        return cpuLoading;
    }

    public void setCpuLoading(List<Integer> cpuLoading) {
        this.cpuLoading = cpuLoading;
    }

    public Integer getAvgCpuLoading() {
        return avgCpuLoading;
    }

    public void setAvgCpuLoading(Integer avgCpuLoading) {
        this.avgCpuLoading = avgCpuLoading;
    }
}
