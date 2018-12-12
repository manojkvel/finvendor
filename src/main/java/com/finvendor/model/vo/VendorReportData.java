package com.finvendor.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor_report_data")
public class VendorReportData {

    @Id
    @Column(name="product_id")
    private String productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="vendor_id")
    private String vendorId;

    @Column(name="vendor_name")
    private String vendorName;

    @Column(name="vendor_company")
    private String vendorCompany;

    @Column(name="vendor_analyst_type")
    private String vendorAnalystType;

    @Column(name="launched_year")
    private String launchedYear;

    @Column(name="research_area_id")
    private String researchAreaId;

    @Column(name="research_sub_area_id")
    private String researchSubAreaId;

    @Column(name="stock_fund_issue_covered")
    private String stockFundIssueCovered;

    @Column(name="accessbility")
    private String accessibility;

    @Column(name="suitability")
    private String suitability;

    @Column(name="sub_cost_py")
    private String subCostPy;

    @Column(name="rep_format")
    private String reportFormat;

    @Column(name="research_report_for_id")
    private String researchReportForId;

    @Column(name="report_date")
    private String reportDate;

    @Column(name="target_price")
    private String targetPrice;

    @Column(name="rsrch_recomm_type")
    private String researchRecommType;

    @Column(name="price_at_recomm")
    private String priceAtRecomm;

    @Column(name="rsrch_report_desc")
    private String researchReportDesc;

    @Column(name="rsrch_report_access")
    private String researchReportAccess;

    @Column(name="report_name")
    private String reportName;

    @Column(name="analyst_name")
    private String analystName;

    @Column(name="analyst_awards")
    private String analystAward;

    @Column(name="anayst_cfa_charter")
    private String analystCfaCharter;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorCompany() {
        return vendorCompany;
    }

    public void setVendorCompany(String vendorCompany) {
        this.vendorCompany = vendorCompany;
    }

    public String getVendorAnalystType() {
        return vendorAnalystType;
    }

    public void setVendorAnalystType(String vendor_analyst_type) {
        this.vendorAnalystType = vendor_analyst_type;
    }

    public String getLaunchedYear() {
        return launchedYear;
    }

    public void setLaunchedYear(String launchedYear) {
        this.launchedYear = launchedYear;
    }

    public String getResearchAreaId() {
        return researchAreaId;
    }

    public void setResearchAreaId(String researchAreaId) {
        this.researchAreaId = researchAreaId;
    }

    public String getResearchSubAreaId() {
        return researchSubAreaId;
    }

    public void setResearchSubAreaId(String researchSubAreaId) {
        this.researchSubAreaId = researchSubAreaId;
    }

    public String getStockFundIssueCovered() {
        return stockFundIssueCovered;
    }

    public void setStockFundIssueCovered(String stockFundIssueCovered) {
        this.stockFundIssueCovered = stockFundIssueCovered;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getSuitability() {
        return suitability;
    }

    public void setSuitability(String suitability) {
        this.suitability = suitability;
    }

    public String getSubCostPy() {
        return subCostPy;
    }

    public void setSubCostPy(String subCostPy) {
        this.subCostPy = subCostPy;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public String getResearchReportForId() {
        return researchReportForId;
    }

    public void setResearchReportForId(String researchReportForId) {
        this.researchReportForId = researchReportForId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(String targetPrice) {
        this.targetPrice = targetPrice;
    }

    public String getResearchRecommType() {
        return researchRecommType;
    }

    public void setResearchRecommType(String researchRecommType) {
        this.researchRecommType = researchRecommType;
    }

    public String getPriceAtRecomm() {
        return priceAtRecomm;
    }

    public void setPriceAtRecomm(String priceAtRecomm) {
        this.priceAtRecomm = priceAtRecomm;
    }

    public String getResearchReportDesc() {
        return researchReportDesc;
    }

    public void setResearchReportDesc(String researchReportDesc) {
        this.researchReportDesc = researchReportDesc;
    }

    public String getResearchReportAccess() {
        return researchReportAccess;
    }

    public void setResearchReportAccess(String researchReportAccess) {
        this.researchReportAccess = researchReportAccess;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getAnalystName() {
        return analystName;
    }

    public void setAnalystName(String analystName) {
        this.analystName = analystName;
    }

    public String getAnalystAward() {
        return analystAward;
    }

    public void setAnalystAward(String analystAward) {
        this.analystAward = analystAward;
    }

    public String getAnalystCfaCharter() {
        return analystCfaCharter;
    }

    public void setAnalystCfaCharter(String analystCfaCharter) {
        this.analystCfaCharter = analystCfaCharter;
    }
}
