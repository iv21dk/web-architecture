package com.ids.webarchitecture.model.hibernate;

import com.ids.webarchitecture.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity that fills the sql table by template data.
 *
 */
@Entity
@Table(name = "product", indexes = {
        @Index(columnList = "author_id", name = "idx_product_author_id")})
@Getter
@Setter
public class Product extends NamedEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    private ProductAuthor author;

    @Column(columnDefinition = "text", nullable = false)
    @NotNull
    private String text;
}
