package com.ids.webarchitecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackendTestDataDto {
    private String name;
    private Integer requestsCount;
    private Integer avgCpuLoading;
}
