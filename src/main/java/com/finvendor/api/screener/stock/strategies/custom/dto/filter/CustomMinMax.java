package com.finvendor.api.screener.stock.strategies.custom.dto.filter;

import java.io.Serializable;

public abstract class CustomMinMax implements Serializable {
    protected String min;
    protected String max;

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
