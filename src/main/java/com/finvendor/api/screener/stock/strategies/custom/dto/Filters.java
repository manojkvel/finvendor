package com.finvendor.api.screener.stock.strategies.custom.dto;

import java.io.Serializable;
import java.util.List;

//@JsonPropertyOrder({ "mcap", "industry", "pe", "pb", "debtToEquityRatio","currentRatio",
//"netOperatingCashFlow","roeInPercentage","operatingProfitMargin","patGrowthInPercentage",
//"epsGrowthInPercentage","revenueGrowthInPercentage","totalFreeCashFlow","returnOnAssetInPercentage",
//"divYield","rotcInPercentage"})
public class Filters implements Serializable {

    List<IndustryData> industryData;
    List<SliderData> sliderData;

    public Filters(List<IndustryData> industryData, List<SliderData> sliderData) {
        this.industryData = industryData;
        this.sliderData = sliderData;
    }

    public List<IndustryData> getIndustryData() {
        return industryData;
    }

    public void setIndustryData(List<IndustryData> industryData) {
        this.industryData = industryData;
    }

    public List<SliderData> getSliderData() {
        return sliderData;
    }

    public void setSliderData(List<SliderData> sliderData) {
        this.sliderData = sliderData;
    }
}
