package com.ids.webarchitecture.dto;

import java.util.ArrayList;
import java.util.List;

public class BackendTestDataDto {

    private String name;
    private Integer requestsCount;
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

    public Integer getAvgCpuLoading() {
        return avgCpuLoading;
    }

    public void setAvgCpuLoading(Integer avgCpuLoading) {
        this.avgCpuLoading = avgCpuLoading;
    }
}
