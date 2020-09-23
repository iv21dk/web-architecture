package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.ChartMeasurementsDto;

import java.util.List;

public interface ChartService {
    public List<ChartMeasurementsDto> getMeasurements();
}
