package com.finvendor.api.resources.researchreport.sector.dto;

import java.io.Serializable;
import java.util.List;

public class SectorReportFilter implements Serializable {
    private String geo;
    private List<String> sectorType;
    private List<String> sectorSubType;
    private List<String> analystType;
    private List<String> researchedBy;
    private List<String> researchDate;
    private List<String> reportTone;
    private List<String> reportFrequency;
    private List<String> analystNames;
    private List<String> productIds;
    private List<String> others;

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public List<String> getSectorType() {
        return sectorType;
    }

    public void setSectorType(List<String> sectorType) {
        this.sectorType = sectorType;
    }

    public List<String> getSectorSubType() {
        return sectorSubType;
    }

    public void setSectorSubType(List<String> sectorSubType) {
        this.sectorSubType = sectorSubType;
    }

    public List<String> getAnalystType() {
        return analystType;
    }

    public void setAnalystType(List<String> analystType) {
        this.analystType = analystType;
    }

    public List<String> getResearchedBy() {
        return researchedBy;
    }

    public void setResearchedBy(List<String> researchedBy) {
        this.researchedBy = researchedBy;
    }

    public List<String> getResearchDate() {
        return researchDate;
    }

    public void setResearchDate(List<String> researchDate) {
        this.researchDate = researchDate;
    }

    public List<String> getReportTone() {
        return reportTone;
    }

    public void setReportTone(List<String> reportTone) {
        this.reportTone = reportTone;
    }

    public List<String> getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(List<String> reportFrequency) {
        this.reportFrequency = reportFrequency;
    }

    public List<String> getAnalystNames() {
        return analystNames;
    }

    public void setAnalystNames(List<String> analystNames) {
        this.analystNames = analystNames;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }
}
