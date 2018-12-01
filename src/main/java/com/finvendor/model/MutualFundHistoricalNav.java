package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="mutual_fund_historical_nav")
public class MutualFundHistoricalNav implements Serializable {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="scheme_code")
    private String schemeCode;

    @Column(name="net_asset_value")
    private String netAssetValue;

    @Column(name="re_purchase_price")
    private String rePurchasePrice;

    @Column(name="sale_price")
    private String salePrice;

    @Column(name="date")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(String netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public String getRePurchasePrice() {
        return rePurchasePrice;
    }

    public void setRePurchasePrice(String rePurchasePrice) {
        this.rePurchasePrice = rePurchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
