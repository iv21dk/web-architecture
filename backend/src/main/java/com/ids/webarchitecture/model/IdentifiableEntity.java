package com.ids.webarchitecture.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class IdentifiableEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    public IdentifiableEntity(@NotNull String id) {
        this.id = id;
    }

}
