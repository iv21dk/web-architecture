package com.ids.webarchitecture.model.mongo;

import org.springframework.data.annotation.Id;

public class Identifiable {

    @Id
    private String id;

    public Identifiable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
