package com.finvendor.api.dff.service.internalfeed.screener.dto;

public class InflationRate {
    private String inflationRate;
    private float inflationRateFloat;

    public InflationRate() {
    }

    public String getInflationRate() {
        return inflationRate;
    }

    public void setInflationRate(String inflationRate) {
        this.inflationRate = inflationRate;
    }

    public float getInflationRateFloat() {
        return inflationRateFloat;
    }

    public void setInflationRateFloat(float inflationRateFloat) {
        this.inflationRateFloat = inflationRateFloat;
    }
}
