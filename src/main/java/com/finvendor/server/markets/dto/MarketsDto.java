package com.finvendor.server.markets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MarketsDto implements Serializable {

    private Long id;

    private String symbol;

    private String series;

    protected String open;

    protected String high;

    protected String low;

    protected String close;

    protected String last;

    protected String prevColse;

    private String totalTradeQuantity;

    private String totalTradeValue;

    private String totalTrades;

    private String timeStamp;
    private String isin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPrevColse() {
        return prevColse;
    }

    public void setPrevColse(String prevColse) {
        this.prevColse = prevColse;
    }

    public String getTotalTradeQuantity() {
        return totalTradeQuantity;
    }

    public void setTotalTradeQuantity(String totalTradeQuantity) {
        this.totalTradeQuantity = totalTradeQuantity;
    }

    public String getTotalTradeValue() {
        return totalTradeValue;
    }

    public void setTotalTradeValue(String totalTradeValue) {
        this.totalTradeValue = totalTradeValue;
    }

    public String getTotalTrades() {
        return totalTrades;
    }

    public void setTotalTrades(String totalTrades) {
        this.totalTrades = totalTrades;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}
