package com.ids.webarchitecture.model.hibernate;

import com.ids.webarchitecture.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT_AUTHOR"
//        indexes =
//            {@Index(columnList = "author_template_id", name = "idx_product_author_template_id")}
            )
@Getter
@Setter
public class ProductAuthor extends NamedEntity {

    @Column(name="author_template_id", nullable = false)
    @NotNull
    private String authorTemplateId;

}
