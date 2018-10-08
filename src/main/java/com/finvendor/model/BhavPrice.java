package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="bhav_price_details")
public class BhavPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="series")
    private String series;

    @Column(name="open")
    private String open;

    @Column(name="high")
    private String high;

    @Column(name="low")
    private String low;

    @Column(name="close")
    private String close;

    @Column(name="last")
    private String last;

    @Column(name="prev_close")
    private String prevColse;

    @Column(name="tot_trd_qty")
    private String totalTradeQuantity;

    @Column(name="tot_trd_val")
    private String totalTradeValue;

    @Column(name="timestamp")
    private String timeStamp;

    @Column(name="total_trades")
    private String totalTrades;

    @Column(name="isin")
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
