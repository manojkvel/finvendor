package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

import java.util.List;

public class ListData {
    private String label;
    private List<String> value;

    public ListData(String label, List<String> value) {
        this.label = label;
        this.value = value;
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

