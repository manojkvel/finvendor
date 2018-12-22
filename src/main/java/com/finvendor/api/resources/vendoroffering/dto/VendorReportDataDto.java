package com.finvendor.api.resources.vendoroffering.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Blob;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendorReportDataDto implements Serializable {
    private String reportName;
    private String productId;
    private String productName;
    private String productDescription;
    private String repeortDescription;
    private String launchedYear;
    private String researchAreaId;
    private String researchSubAreaId;
    private String suitability;
    private String subscriptionCostPerAnnum;
    private String researchReportForId;
    private String recommType;
    private String reportDate;
    private String researchTargetPrice;
    private String reportFrequency;
    private String priceAtRecomm;
    private String reportDescription;
    private String reportAccess;
    private String analystName;
    private String analystCfaCharter;
    private String analystAwarded;
    private String vendorId;
    private String vendorName;
    private String vendorCompany;
    private String vendorAnalystType;
    private Blob reportFile;
    private String reportFormat;
    private String stockFundIssueCovered;
    private String accessibility;


    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getRepeortDescription() {
        return repeortDescription;
    }

    public void setRepeortDescription(String repeortDescription) {
        this.repeortDescription = repeortDescription;
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

    public String getSuitability() {
        return suitability;
    }

    public void setSuitability(String suitability) {
        this.suitability = suitability;
    }

    public String getSubscriptionCostPerAnnum() {
        return subscriptionCostPerAnnum;
    }

    public void setSubscriptionCostPerAnnum(String subscriptionCostPerAnnum) {
        this.subscriptionCostPerAnnum = subscriptionCostPerAnnum;
    }

    public String getResearchReportForId() {
        return researchReportForId;
    }

    public void setResearchReportForId(String researchReportForId) {
        this.researchReportForId = researchReportForId;
    }

    public String getRecommType() {
        return recommType;
    }

    public void setRecommType(String recommType) {
        this.recommType = recommType;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportAccess() {
        return reportAccess;
    }

    public void setReportAccess(String reportAccess) {
        this.reportAccess = reportAccess;
    }

    public String getAnalystName() {
        return analystName;
    }

    public void setAnalystName(String analystName) {
        this.analystName = analystName;
    }

    public String getAnalystCfaCharter() {
        return analystCfaCharter;
    }

    public void setAnalystCfaCharter(String analystCfaCharter) {
        this.analystCfaCharter = analystCfaCharter;
    }

    public String getAnalystAwarded() {
        return analystAwarded;
    }

    public void setAnalystAwarded(String analystAwarded) {
        this.analystAwarded = analystAwarded;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getResearchTargetPrice() {
        return researchTargetPrice;
    }

    public void setResearchTargetPrice(String researchTargetPrice) {
        this.researchTargetPrice = researchTargetPrice;
    }

    public String getPriceAtRecomm() {
        return priceAtRecomm;
    }

    public void setPriceAtRecomm(String priceAtRecomm) {
        this.priceAtRecomm = priceAtRecomm;
    }

    public Blob getReportFile() {
        return reportFile;
    }

    public void setReportFile(Blob reportFile) {
        this.reportFile = reportFile;
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

    public void setVendorAnalystType(String vendorAnalystType) {
        this.vendorAnalystType = vendorAnalystType;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setStockFundIssueCovered(String stockFundIssueCovered) {
        this.stockFundIssueCovered = stockFundIssueCovered;
    }

    public String getStockFundIssueCovered() {
        return stockFundIssueCovered;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public String getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(String reportFrequency) {
        this.reportFrequency = reportFrequency;
    }
}
