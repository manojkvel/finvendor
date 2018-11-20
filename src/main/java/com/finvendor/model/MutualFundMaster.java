package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="mutual_fund_master")
public class MutualFundMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="amc_name")
    private String amcName;

    @Column(name="amfi_code")
    private String amfiCode;

    @Column(name="scheme_name")
    private String schemeName;

    @Column(name="scheme_type")
    private String schemeType;

    @Column(name="scheme_category")
    private String schemeCategory;

    @Column(name="scheme_sub_category")
    private String schemeSubCategory;

    @Column(name="benchmark_index")
    private String benchMarkIndex;

    @Column(name="scheme_nav_name")
    private String schemeNavName;

    @Column(name="scheme_min_amount")
    private String schemeMinAmount;

    @Column(name="scheme_launch_dt")
    private String schemeLauchDate;

    @Column(name="scheme_closure_dt")
    private String schemeClosureDt;

    @Column(name="isin_div_payout")
    private String isinDivPayout;

    @Column(name="isin_div_reinvest")
    private String isinDivReInvest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmcName() {
        return amcName;
    }

    public void setAmcName(String amcName) {
        this.amcName = amcName;
    }

    public String getAmfiCode() {
        return amfiCode;
    }

    public void setAmfiCode(String amfiCode) {
        this.amfiCode = amfiCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getSchemeCategory() {
        return schemeCategory;
    }

    public void setSchemeCategory(String schemeCategory) {
        this.schemeCategory = schemeCategory;
    }

    public String getSchemeSubCategory() {
        return schemeSubCategory;
    }

    public void setSchemeSubCategory(String schemeSubCategory) {
        this.schemeSubCategory = schemeSubCategory;
    }

    public String getSchemeNavName() {
        return schemeNavName;
    }

    public void setSchemeNavName(String schemeNavName) {
        this.schemeNavName = schemeNavName;
    }

    public String getSchemeMinAmount() {
        return schemeMinAmount;
    }

    public void setSchemeMinAmount(String schemeMinAmount) {
        this.schemeMinAmount = schemeMinAmount;
    }

    public String getSchemeLauchDate() {
        return schemeLauchDate;
    }

    public void setSchemeLauchDate(String schemeLauchDate) {
        this.schemeLauchDate = schemeLauchDate;
    }

    public String getSchemeClosureDt() {
        return schemeClosureDt;
    }

    public void setSchemeClosureDt(String schemeClosureDt) {
        this.schemeClosureDt = schemeClosureDt;
    }

    public String getIsinDivPayout() {
        return isinDivPayout;
    }

    public void setIsinDivPayout(String isinDivPayout) {
        this.isinDivPayout = isinDivPayout;
    }

    public String getIsinDivReInvest() {
        return isinDivReInvest;
    }

    public void setIsinDivReInvest(String isinDivReInvest) {
        this.isinDivReInvest = isinDivReInvest;
    }

    public String getBenchMarkIndex() {
        return benchMarkIndex;
    }

    public void setBenchMarkIndex(String benchMarkIndex) {
        this.benchMarkIndex = benchMarkIndex;
    }
}
