package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

public class Filter {
    private String label;
    private String min;
    private String max;

    public Filter(String label, String min, String max) {
        this.label=label;
        this.min = min;
        this.max = max;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
