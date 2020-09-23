package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.ChartMeasurementsDto;
import com.ids.webarchitecture.dto.TestDto;
import com.ids.webarchitecture.dto.TestMeasurementsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.ids.webarchitecture.service.ChartServiceImpl.CHART_X_AXIS_ITEMS_COUNT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChartServiceTest {

    @InjectMocks
    private ChartServiceImpl chartService;

    @Mock
    private TestService testService;

    @Test
    public void test_getMeasurements() {
        List<TestDto> tests = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            //max = 10 900
            //min = 52
            //step = 108.48
            //first step: (mongo) 52, 104
            //last step: (sql) 10 848, 10 900
            TestDto test = new TestDto();
            test.setSqlInitialDataCount((long) ((52 * i) + 500));
            test.setMongoInitialDataCount((long) (52 * i));

            TestMeasurementsDto measurementsSql = new TestMeasurementsDto();
            measurementsSql.setAvgCreateTimeMs(1 * i);
            measurementsSql.setAvgUpdateTimeMs(2 * i);
            measurementsSql.setAvgDeleteTimeMs(3 * i);
            measurementsSql.setAvgFindByIndexedFieldTimeMs(4 * i);
            measurementsSql.setAvgFindByNoIndexedFieldTimeMs(5 * i);
            measurementsSql.setAvgRetrieveFullData(6 * i);
            test.setSqlMeasurements(measurementsSql);

            TestMeasurementsDto measurementsMongo = new TestMeasurementsDto();
            measurementsMongo.setAvgCreateTimeMs(7 * i);
            measurementsMongo.setAvgUpdateTimeMs(8 * i);
            measurementsMongo.setAvgDeleteTimeMs(9 * i);
            measurementsMongo.setAvgFindByIndexedFieldTimeMs(10 * i);
            measurementsMongo.setAvgFindByNoIndexedFieldTimeMs(11 * i);
            measurementsMongo.setAvgRetrieveFullData(12 * i);
            test.setMongoDbMeasurements(measurementsMongo);

            tests.add(test);
        }

        when(testService.getTests()).thenReturn(tests);

        List<ChartMeasurementsDto> result = chartService.getMeasurements();
        assertEquals(CHART_X_AXIS_ITEMS_COUNT, result.size());
        // grouped 1 and 2 tests
        assertEquals("104", result.get(0).getDisplayDataCount());
        assertEquals(10, result.get(0).getMongoDbMeasurements().getAvgCreateTimeMs(), 0);
        assertEquals(18, result.get(0).getMongoDbMeasurements().getAvgRetrieveFullData(), 0);

        // grouped 40 and 41 tests
        assertEquals("2.2K", result.get(19).getDisplayDataCount());
        assertEquals(283, result.get(19).getMongoDbMeasurements().getAvgCreateTimeMs(), 0);
        assertEquals(486, result.get(19).getMongoDbMeasurements().getAvgRetrieveFullData(), 0);
    }

}
