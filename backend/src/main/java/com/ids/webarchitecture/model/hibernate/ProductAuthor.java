package com.ids.webarchitecture.model.hibernate;

import com.ids.webarchitecture.model.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT_AUTHOR")
public class ProductAuthor extends NamedEntity {

    @Column(name="author_template_id", nullable = false)
    private String authorTemplateId;

    public ProductAuthor(String id) {
        super(id);
    }

    public ProductAuthor() {
        super();
    }

    public String getAuthorTemplateId() {
        return authorTemplateId;
    }

    public void setAuthorTemplateId(@NotNull String authorTemplateId) {
        this.authorTemplateId = authorTemplateId;
    }
}
