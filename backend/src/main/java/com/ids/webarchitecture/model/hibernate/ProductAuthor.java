package com.ids.webarchitecture.model.hibernate;

import com.ids.webarchitecture.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "product_author", indexes = {
        @Index(columnList = "author_template_id", name = "idx_product_author_template_id")})
@Getter
@Setter
public class ProductAuthor extends NamedEntity {

    @Column(name="author_template_id", nullable = false)
    @NotNull
    private String authorTemplateId;

    @OneToMany(mappedBy = "author")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private List<Product> products;

}
