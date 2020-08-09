package com.ids.webarchitecture.controller;

import com.ids.webarchitecture.dto.ChartMeasurementsDto;
import com.ids.webarchitecture.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/api/charts/measurements")
    @ResponseStatus(HttpStatus.OK)
    public List<ChartMeasurementsDto> getMeasurements(){
        return chartService.getMeasurements();
    }

}
