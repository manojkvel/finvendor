package com.finvendor.server.common.infra.parser;

import java.util.Objects;
/**
 *
 * Ayush on 8-Aug-2018
 * */
public class StockPrice {
    private String open;
    private String high;
    private String low;
    private String close;
    private String ltp; /**last traded price*/
    public StockPrice(String open, String high, String low, String close, String ltp) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.ltp = ltp;
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

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPrice that = (StockPrice) o;
        return Objects.equals(open, that.open) &&
                Objects.equals(high, that.high) &&
                Objects.equals(low, that.low) &&
                Objects.equals(close, that.close) &&
                Objects.equals(ltp, that.ltp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(open, high, low, close, ltp);
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", ltp='" + ltp + '\'' +
                '}';
    }
}
