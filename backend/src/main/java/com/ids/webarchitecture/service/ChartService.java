package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.ChartMeasurementsDto;
import com.ids.webarchitecture.dto.TestDto;
import com.ids.webarchitecture.dto.TestMeasurementsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ChartService {

    public static final int CHART_X_AXIS_ITEMS_COUNT = 100;

    @Autowired
    private TestService testService;

    private class ChartStepData {
        private long dataCountMax;
        private List<TestMeasurementsDto> mongoDbMeasurements = new ArrayList<>();
        private List<TestMeasurementsDto> sqlMeasurements = new ArrayList<>();
    }

    public List<ChartMeasurementsDto> getMeasurements() {
        List<TestDto> tests = testService.getTests();
        List<ChartMeasurementsDto> result = new ArrayList<>();

        if (tests.size() == 0) {
            return result;
        } else if (tests.size() < CHART_X_AXIS_ITEMS_COUNT) {
            tests.forEach(test -> {
                ChartMeasurementsDto resultItem = new ChartMeasurementsDto();
                resultItem.setInitialDataCount(test.getMongoInitialDataCount());
                resultItem.setDisplayDataCount(buildDisplayDataCount(test.getMongoInitialDataCount()));
                resultItem.setMongoDbMeasurements(test.getMongoDbMeasurements());
                resultItem.setSqlMeasurements(test.getSqlMeasurements());
                result.add(resultItem);
            });
            return result;
        }

        TestDto firstTest = tests.get(0);
        TestDto lastTest = tests.get(tests.size()-1);
        long minInitCount = Math.min(firstTest.getMongoInitialDataCount(), firstTest.getSqlInitialDataCount());
        long maxInitCount = Math.max(lastTest.getMongoInitialDataCount(), lastTest.getSqlInitialDataCount());

        float oneStepByAxisX = ((float)(maxInitCount - minInitCount)) / CHART_X_AXIS_ITEMS_COUNT;
        ChartStepData[] steps = new ChartStepData[CHART_X_AXIS_ITEMS_COUNT];
        
        tests.forEach(test -> {
            // group mongodb measurements
            int index = (int) ((test.getMongoInitialDataCount()) / oneStepByAxisX);
            index = Math.min(index, CHART_X_AXIS_ITEMS_COUNT - 1);
            ChartStepData stepData = steps[index];
            if (stepData == null) {
                stepData = new ChartStepData();
                steps[index] = stepData;
            }
            stepData.mongoDbMeasurements.add(test.getMongoDbMeasurements());
            if (stepData.dataCountMax < test.getMongoInitialDataCount()) {
                stepData.dataCountMax = test.getMongoInitialDataCount();
            }

            //group sql measurements
            index = (int) ((test.getSqlInitialDataCount()) / oneStepByAxisX);
            index = Math.min(index, CHART_X_AXIS_ITEMS_COUNT - 1);
            stepData = steps[index];
            if (stepData == null) {
                stepData = new ChartStepData();
                steps[index] = stepData;
            }
            stepData.sqlMeasurements.add(test.getSqlMeasurements());
            if (stepData.dataCountMax < test.getSqlInitialDataCount()) {
                stepData.dataCountMax = test.getSqlInitialDataCount();
            }
        });

        for (int i = 0; i < CHART_X_AXIS_ITEMS_COUNT; i++) {
            ChartStepData stepData = steps[i];
            if (stepData == null) {
                continue;
            }
            ChartMeasurementsDto resultItem = new ChartMeasurementsDto();
            resultItem.setInitialDataCount(stepData.dataCountMax);
            resultItem.setDisplayDataCount(buildDisplayDataCount(stepData.dataCountMax));
            resultItem.setMongoDbMeasurements(calculateAvgMeasurements(stepData.mongoDbMeasurements));
            resultItem.setSqlMeasurements(calculateAvgMeasurements(stepData.sqlMeasurements));
            result.add(resultItem);
        }
        return result;
    }

    private String buildDisplayDataCount(long dataCount) {
        float coefficient;
        int round = 1;
        String suffix;
        if (dataCount < 1000) {
            coefficient = 1;
            round = 3;
            suffix = "";
        } else if (dataCount < 1000000) {
            coefficient = (float) 0.001;
            suffix = "K";
        } else {
            coefficient = (float) 0.000001;
            suffix = "M";
        }

        if (coefficient == 1) {
            return Objects.toString(dataCount);
        } else {
            BigDecimal dataCountBD = new BigDecimal(dataCount * coefficient);
            return dataCountBD.setScale(round, RoundingMode.HALF_DOWN).toString() + suffix;
        }
    }

    private TestMeasurementsDto calculateAvgMeasurements(List<TestMeasurementsDto> measurements) {
        TestMeasurementsDto avg = new TestMeasurementsDto();
        if (measurements.size() == 0) {
            return avg;
        } else if (measurements.size() == 1) {
            return measurements.get(0);
        }

        int createTimeMs = 0;
        int findByIndexedFieldTimeMs = 0;
        int findByNoIndexedFieldTimeMs = 0;
        int retrieveFullData = 0;
        int updateTimeMs = 0;
        int deleteTimeMs = 0;
        for (TestMeasurementsDto measurement : measurements) {
            createTimeMs += measurement.getAvgCreateTimeMs();
            findByIndexedFieldTimeMs += measurement.getAvgFindByIndexedFieldTimeMs();
            findByNoIndexedFieldTimeMs += measurement.getAvgFindByNoIndexedFieldTimeMs();
            retrieveFullData += measurement.getAvgRetrieveFullData();
            updateTimeMs += measurement.getAvgUpdateTimeMs();
            deleteTimeMs += measurement.getAvgDeleteTimeMs();
        }
        avg.setAvgCreateTimeMs(createTimeMs / measurements.size());
        avg.setAvgFindByIndexedFieldTimeMs(findByIndexedFieldTimeMs / measurements.size());
        avg.setAvgFindByNoIndexedFieldTimeMs(findByNoIndexedFieldTimeMs / measurements.size());
        avg.setAvgRetrieveFullData(retrieveFullData / measurements.size());
        avg.setAvgUpdateTimeMs(updateTimeMs / measurements.size());
        avg.setAvgDeleteTimeMs(deleteTimeMs / measurements.size());
        return avg;
    }

}
