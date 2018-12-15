package com.finvendor.api.resources.markets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"companyId", "companyName", "open", "high", "low", "close",
        "prevColse", "change", "percentChange", "_52wLow", "_52wHigh", "totalTradeQuantity"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CustomMarketsDto extends MarketsDto {
    private String companyId;
    private String companyName;
    private String change;
    private Double percentChange;
    private String _52wLow;
    private String _52wHigh;
    private String indexName;
    private String isinCode;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public Double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public String get_52wLow() {
        return _52wLow;
    }

    public void set_52wLow(String _52wLow) {
        this._52wLow = _52wLow;
    }

    public String get_52wHigh() {
        return _52wHigh;
    }

    public void set_52wHigh(String _52wHigh) {
        this._52wHigh = _52wHigh;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
}
