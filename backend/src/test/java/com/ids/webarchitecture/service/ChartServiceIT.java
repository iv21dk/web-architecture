package com.ids.webarchitecture.service;

import com.ids.webarchitecture.config.AppTestConfig;
import com.ids.webarchitecture.dto.ChartMeasurementsDto;
import com.ids.webarchitecture.dto.TestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { AppTestConfig.class })
public class ChartServiceIT {

    @Autowired
    private ChartService chartService;

    @MockBean
    private TestService testService;

    @Test
    //@Ignore
    public void test_getMeasurements() {
        List<TestDto> tests = new ArrayList<>();
        when(testService.getTests()).thenReturn(tests);
        List<ChartMeasurementsDto> result = chartService.getMeasurements();
    }

}
