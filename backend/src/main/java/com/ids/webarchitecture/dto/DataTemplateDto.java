package com.ids.webarchitecture.dto;

public class DataTemplateDto extends NamedDto {
    private String text;
    private ProductAuthorDto author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProductAuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(ProductAuthorDto author) {
        this.author = author;
    }
}
