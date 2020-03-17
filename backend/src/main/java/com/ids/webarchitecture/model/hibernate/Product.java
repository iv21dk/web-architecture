package com.ids.webarchitecture.model.hibernate;

import com.ids.webarchitecture.model.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity that fills the collection by template data.
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends NamedEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
    private ProductAuthor author;

    @Column(columnDefinition = "text", nullable = false)
    private String text;

    public Product(String id) {
        super(id);
    }

    public Product() {
        super();
    }

    public ProductAuthor getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull ProductAuthor author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

}
