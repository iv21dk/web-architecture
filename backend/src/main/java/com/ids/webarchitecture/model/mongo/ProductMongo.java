package com.ids.webarchitecture.model.mongo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

//@Document(collection = "product")
@Getter
@Setter
public class ProductMongo {
    @NotNull
    private String name;
    @NotNull
    private String text;
}
