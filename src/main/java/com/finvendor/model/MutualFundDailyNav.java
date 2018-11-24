package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="mutual_fund_daily_nav")
public class MutualFundDailyNav implements Serializable {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="amfi_code")
    private String amfiCode;

    @Column(name="date")
    private String date;

    @Column(name="net_asset_value")
    private String netAssetValue;

    @Column(name="re_purchase_price")
    private String rePurchasePrice;

    @Column(name="sale_price")
    private String salePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getAmfiCode() {
        return amfiCode;
    }

    public void setAmfiCode(String amfiCode) {
        this.amfiCode = amfiCode;
    }


}
