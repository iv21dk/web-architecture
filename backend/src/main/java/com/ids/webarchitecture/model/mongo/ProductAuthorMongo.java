package com.ids.webarchitecture.model.mongo;

import com.ids.webarchitecture.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This is an entity that fills the collection by template data.
 *
 */
@Document(collection = "product_author")
@Getter
@Setter
public class ProductAuthorMongo extends NamedEntity {
    @NotNull
    @Indexed
    private String authorTemplateId;
    private List<ProductMongo> products;
}
