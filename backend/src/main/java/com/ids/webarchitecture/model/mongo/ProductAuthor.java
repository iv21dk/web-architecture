package com.ids.webarchitecture.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_author")
public class ProductAuthor extends Named {

    public ProductAuthor(String id) {
        super(id);
    }

    public ProductAuthor() {
        super();
    }
}
