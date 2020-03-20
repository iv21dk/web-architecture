package com.ids.webarchitecture.model.hibernate;

import com.ids.webarchitecture.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity that fills the sql table by template data.
 *
 */
@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product extends NamedEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
    @NotNull
    private ProductAuthor author;

    @Column(columnDefinition = "text", nullable = false)
    @NotNull
    private String text;
}
