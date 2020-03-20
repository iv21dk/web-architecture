package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "data_template")
@Getter
@Setter
public class DataTemplateMongo extends NamedEntity {
    @NotNull
    private ProductAuthorTemplateMongo author;
    @NotNull
    private String text;
}
