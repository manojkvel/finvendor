package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

import java.io.Serializable;
import java.util.List;

//@JsonPropertyOrder({ "mcap", "industry", "pe", "pb", "debtToEquityRatio","currentRatio",
//"netOperatingCashFlow","roeInPercentage","operatingProfitMargin","patGrowthInPercentage",
//"epsGrowthInPercentage","revenueGrowthInPercentage","totalFreeCashFlow","returnOnAssetInPercentage",
//"divYield","rotcInPercentage"})
public class Filters implements Serializable {

    List<String> industry;
    List<Others> others;

    public Filters(List<String> industry, List<Others> others) {
        this.industry = industry;
        this.others = others;
    }

    public List<Others> getOthers() {
        return others;
    }

    public void setOthers(List<Others> others) {
        this.others = others;
    }

    public List<String> getIndustry() {
        return industry;
    }

    public void setIndustry(List<String> industry) {
        this.industry = industry;
    }
}
