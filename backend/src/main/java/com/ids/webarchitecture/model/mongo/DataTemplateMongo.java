package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.NamedEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "data_template")
public class DataTemplateMongo extends NamedEntity {

    private ProductAuthorTemplateMongo author;
    private String text;

    public DataTemplateMongo(@NotNull String id) {
        super(id);
    }

    public DataTemplateMongo() {
        super();
    }

    public ProductAuthorTemplateMongo getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull ProductAuthorTemplateMongo author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }
}
