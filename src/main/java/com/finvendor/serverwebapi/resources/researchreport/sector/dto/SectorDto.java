package com.finvendor.serverwebapi.resources.researchreport.sector.dto;

import java.io.Serializable;

public class SectorDto implements Serializable {
    private String productId;
    private String sectorType;
    private String sectorSubType;
    private String researchedBy;
    private String analystType;
    private String reportTone;
    private String reportFrequency;
    private String reportName;
    private String reportDate;
    private String analystName;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSectorType() {
        return sectorType;
    }

    public void setSectorType(String sectorType) {
        this.sectorType = sectorType;
    }

    public String getSectorSubType() {
        return sectorSubType;
    }

    public void setSectorSubType(String sectorSubType) {
        this.sectorSubType = sectorSubType;
    }

    public String getResearchedBy() {
        return researchedBy;
    }

    public void setResearchedBy(String researchedBy) {
        this.researchedBy = researchedBy;
    }

    public String getAnalystType() {
        return analystType;
    }

    public void setAnalystType(String analystType) {
        this.analystType = analystType;
    }

    public String getReportTone() {
        return reportTone;
    }

    public void setReportTone(String reportTone) {
        this.reportTone = reportTone;
    }

    public String getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(String reportFrequency) {
        this.reportFrequency = reportFrequency;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getAnalystName() {
        return analystName;
    }

    public void setAnalystName(String analystName) {
        this.analystName = analystName;
    }
}
