package com.ids.webarchitecture.model.mongo;

public class Named extends Identifiable {
    private String name;

    public Named(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
