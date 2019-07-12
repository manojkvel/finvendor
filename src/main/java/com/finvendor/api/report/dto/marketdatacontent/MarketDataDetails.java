package com.finvendor.api.report.dto.marketdatacontent;

import java.io.Serializable;

public class MarketDataDetails implements Serializable {
    private String companyId;
    private String companyName;
    private String open;
    private String high;
    private String low;
    private String close;
    private String prevColse;
    private String change;
    private String percentChange;
    private String _52wLow;
    private String _52wHigh;
    private String volume;
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

    public String getPrevColse() {
        return prevColse;
    }

    public void setPrevColse(String prevColse) {
        this.prevColse = prevColse;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
}
