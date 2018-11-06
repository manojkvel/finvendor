//package com.finvendor.server.markets.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonPropertyOrder({ "companyId", "companyName", "open", "high", "low", "close",
//        "prevColse", "volume", "trades",
//        "change", "percentChange", "_52wLow", "_52wHigh", "indexName" })
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
//public class CustomMarketsDto extends MarketsDto {
//    private String companyId;
//    private String companyName;
//    private String volume;
//    private String trades;
//    private String change;
//    private String percentChange;
//    private String _52wLow;
//    private String _52wHigh;
//    private String indexName;
//    private String date;
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public String getVolume() {
//        return volume;
//    }
//
//    public void setVolume(String volume) {
//        this.volume = volume;
//    }
//
//    public String getTrades() {
//        return trades;
//    }
//
//    public void setTrades(String trades) {
//        this.trades = trades;
//    }
//
//    public String getChange() {
//        return change;
//    }
//
//    public void setChange(String change) {
//        this.change = change;
//    }
//
//    public String getPercentChange() {
//        return percentChange;
//    }
//
//    public void setPercentChange(String percentChange) {
//        this.percentChange = percentChange;
//    }
//
//    public String get_52wLow() {
//        return _52wLow;
//    }
//
//    public void set_52wLow(String _52wLow) {
//        this._52wLow = _52wLow;
//    }
//
//    public String get_52wHigh() {
//        return _52wHigh;
//    }
//
//    public void set_52wHigh(String _52wHigh) {
//        this._52wHigh = _52wHigh;
//    }
//
//    public String getIndexName() {
//        return indexName;
//    }
//
//    public void setIndexName(String indexName) {
//        this.indexName = indexName;
//    }
//
//    public String getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(String companyId) {
//        this.companyId = companyId;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//}
