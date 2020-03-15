package com.ids.webarchitecture.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data_template")
public class DataTemplate extends Named {

    public DataTemplate(String id) {
        super(id);
    }

    public DataTemplate() {
        super();
    }

    private ProductAuthor author;
    private String text;

    public ProductAuthor getAuthor() {
        return author;
    }

    public void setAuthor(ProductAuthor author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
