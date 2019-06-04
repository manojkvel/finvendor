package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

import java.util.List;

public class IndustryData {
    private String id;
    private String label;
    private List<String> value;

    public IndustryData(String id, String label, List<String> value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}

