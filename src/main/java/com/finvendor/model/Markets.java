package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="markets")
public class Markets implements Serializable {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="company_id")
    private String companyId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="isin")
    private String isin;

    @Column(name="open")
    private String open;

    @Column(name="high")
    private String high;

    @Column(name="low")
    private String low;

    @Column(name="close")
    private String close;

    @Column(name="prev_close")
    private String prevColse;

    @Column(name="price_change")
    private Double priceChange;

    @Column(name="price_percent_change")
    private Double pricePercentChange;

    @Column(name="52w_low")
    private Double _52wLow;

    @Column(name="52w_high")
    private Double _52wHigh;

    @Column(name="tot_trd_qty")
    private Integer totalTradeQty;

    @Column(name="date")
    private String date;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
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

    public Double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = priceChange;
    }

    public Double getPricePercentChange() {
        return pricePercentChange;
    }

    public void setPricePercentChange(Double pricePercentChange) {
        this.pricePercentChange = pricePercentChange;
    }

    public Double get_52wLow() {
        return _52wLow;
    }

    public void set_52wLow(Double _52wLow) {
        this._52wLow = _52wLow;
    }

    public Double get_52wHigh() {
        return _52wHigh;
    }

    public void set_52wHigh(Double _52wHigh) {
        this._52wHigh = _52wHigh;
    }

    public Integer getTotalTradeQty() {
        return totalTradeQty;
    }

    public void setTotalTradeQty(Integer totalTradeQty) {
        this.totalTradeQty = totalTradeQty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
