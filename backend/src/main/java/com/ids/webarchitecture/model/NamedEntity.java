package com.ids.webarchitecture.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter @Setter
public abstract class NamedEntity extends IdentifiableEntity {
    @Column(nullable = false)
    @NotNull
    private String name;
}
