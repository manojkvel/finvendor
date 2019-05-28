package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@JsonPropertyOrder({ "mcap", "industry", "pe", "pb", "debtToEquityRatio","currentRatio",
//"netOperatingCashFlow","roeInPercentage","operatingProfitMargin","patGrowthInPercentage",
//"epsGrowthInPercentage","revenueGrowthInPercentage","totalFreeCashFlow","returnOnAssetInPercentage",
//"divYield","rotcInPercentage"})
public class Filters implements Serializable {

    List<Filter> filters;

    public Filters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
