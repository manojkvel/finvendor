package com.finvendor.api.screener.stock.strategies.custom.dto;

public class SliderData {

    private String id;
    private String label;
    private String min;
    private String max;

    public SliderData(String id, String label, String min, String max) {
        this.id = id;
        this.label = label;
        this.min = min;
        this.max = max;
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
