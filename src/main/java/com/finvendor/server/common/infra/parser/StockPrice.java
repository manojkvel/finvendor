package com.finvendor.server.common.infra.parser;

/**
 *
 * Ayush on 8-Aug-2018
 * */
public class StockPrice {
    private String symbol;
    private String series;

    private String open;
    private String high;
    private String low;
    private String close;
    private String ltp; /**last traded price*/

    private String prevclose;
    private String totalTrdQty;
    private String totalTrdVal;
    private String timestamp;
    private String totalTrades;
    private String isin;

    public StockPrice(String symbol, String series, String open, String high, String low, String close, String ltp, String prevclose, String totalTrdQty, String totalTrdVal, String timestamp, String totalTrades, String isin) {
        this.symbol = symbol;
        this.series = series;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.ltp = ltp;
        this.prevclose = prevclose;
        this.totalTrdQty = totalTrdQty;
        this.totalTrdVal = totalTrdVal;
        this.timestamp = timestamp;
        this.totalTrades = totalTrades;
        this.isin = isin;
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

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    public String getPrevclose() {
        return prevclose;
    }

    public void setPrevclose(String prevclose) {
        this.prevclose = prevclose;
    }

    public String getTotalTrdQty() {
        return totalTrdQty;
    }

    public void setTotalTrdQty(String totalTrdQty) {
        this.totalTrdQty = totalTrdQty;
    }

    public String getTotalTrdVal() {
        return totalTrdVal;
    }

    public void setTotalTrdVal(String totalTrdVal) {
        this.totalTrdVal = totalTrdVal;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotalTrades() {
        return totalTrades;
    }

    public void setTotalTrades(String totalTrades) {
        this.totalTrades = totalTrades;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "symbol='" + symbol + '\'' +
                ", series='" + series + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", ltp='" + ltp + '\'' +
                ", prevclose='" + prevclose + '\'' +
                ", totalTrdQty='" + totalTrdQty + '\'' +
                ", totalTrdVal='" + totalTrdVal + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", totalTrades='" + totalTrades + '\'' +
                ", isin='" + isin + '\'' +
                '}';
    }
}
