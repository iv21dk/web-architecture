package com.ids.webarchitecture.dto;

import com.ids.webarchitecture.model.mongo.Identifiable;

public class NamedDto extends IdentifiableDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
