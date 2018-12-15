package com.finvendor.api.resources.example.dto;

import java.io.Serializable;

public class ExampleDto implements Serializable {
    private static final long serialVersionUID = 7813880151499503234L;

    private Integer id;

    private String name;

    public ExampleDto() {
    }

    public ExampleDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
