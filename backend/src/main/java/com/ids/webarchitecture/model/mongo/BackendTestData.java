package com.ids.webarchitecture.model.mongo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BackendTestData {
    private String name;
    private Integer requestsCount = 0;
    private List<Integer> cpuLoading = new ArrayList<>();
    private Integer avgCpuLoading;
}
