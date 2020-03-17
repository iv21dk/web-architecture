package com.ids.webarchitecture.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class NamedEntity extends IdentifiableEntity {

    @Column(nullable = false)
    private String name;

    public NamedEntity(@NotNull String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public NamedEntity() {
        super();
    }
}
