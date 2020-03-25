package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.IdentifiableEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "locked_test")
public class LockedTest extends IdentifiableEntity {
    private String testId;

    public LockedTest(String testId) {
        this.testId = testId;
    }
}
