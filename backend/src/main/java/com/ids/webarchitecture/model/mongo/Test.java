package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.IdentifiableEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "test")
@Getter
@Setter
public class Test extends IdentifiableEntity {
    private Date startDate = new Date();
    private Date endDate;
    private Long initialDataCount;
    private Integer requestsCount = 0;
    private Short mongoDbClusterSize = 0;
    private Short sqlClusterSize = 0;
    private Short backendClusterSize = 0;
    private TestMeasurements mongoDbMeasurements = new TestMeasurements();
    private TestMeasurements sqlMeasurements = new TestMeasurements();
    private List<BackendTestData> backends = new ArrayList<>();
}
