package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.NamedEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_author_template")
public class ProductAuthorTemplateMongo extends NamedEntity {

    public ProductAuthorTemplateMongo(String id) {
        super(id);
    }

    public ProductAuthorTemplateMongo() {
        super();
    }
}
