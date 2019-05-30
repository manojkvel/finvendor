package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

import java.io.Serializable;
import java.util.List;

//@JsonPropertyOrder({ "mcap", "industry", "pe", "pb", "debtToEquityRatio","currentRatio",
//"netOperatingCashFlow","roeInPercentage","operatingProfitMargin","patGrowthInPercentage",
//"epsGrowthInPercentage","revenueGrowthInPercentage","totalFreeCashFlow","returnOnAssetInPercentage",
//"divYield","rotcInPercentage"})
public class Filters implements Serializable {

    List<ListData> listData;
    List<SliderData> sliderData;

    public Filters(List<ListData> listData, List<SliderData> sliderData) {
        this.listData = listData;
        this.sliderData = sliderData;
    }

    public List<ListData> getListData() {
        return listData;
    }

    public void setListData(List<ListData> listData) {
        this.listData = listData;
    }

    public List<SliderData> getSliderData() {
        return sliderData;
    }

    public void setSliderData(List<SliderData> sliderData) {
        this.sliderData = sliderData;
    }
}
