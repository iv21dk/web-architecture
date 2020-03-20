package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.NamedEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "product_author")
public class ProductAuthorMongo extends NamedEntity {

    private String authorTemplateId;
    private List<ProductMongo> products;

    public String getAuthorTemplateId() {
        return authorTemplateId;
    }

    public void setAuthorTemplateId(String authorTemplateId) {
        this.authorTemplateId = authorTemplateId;
    }

    public List<ProductMongo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMongo> products) {
        this.products = products;
    }
}
