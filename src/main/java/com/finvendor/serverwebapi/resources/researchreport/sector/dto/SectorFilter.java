package com.finvendor.serverwebapi.resources.researchreport.sector.dto;

import java.io.Serializable;
import java.util.List;

public class SectorFilter implements Serializable {
    private String geo;
    private List<String> sectorTypes;
    private List<String> sectorSubTypes;
    private List<String> analystTypes;
    private List<String> researchedBy;
    private List<String> researchDates;
    private List<String> reportTones;
    private List<String> reportFrequency;

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public List<String> getSectorTypes() {
        return sectorTypes;
    }

    public void setSectorTypes(List<String> sectorTypes) {
        this.sectorTypes = sectorTypes;
    }

    public List<String> getSectorSubTypes() {
        return sectorSubTypes;
    }

    public void setSectorSubTypes(List<String> sectorSubTypes) {
        this.sectorSubTypes = sectorSubTypes;
    }

    public List<String> getAnalystTypes() {
        return analystTypes;
    }

    public void setAnalystTypes(List<String> analystTypes) {
        this.analystTypes = analystTypes;
    }

    public List<String> getResearchedBy() {
        return researchedBy;
    }

    public void setResearchedBy(List<String> researchedBy) {
        this.researchedBy = researchedBy;
    }

    public List<String> getResearchDates() {
        return researchDates;
    }

    public void setResearchDates(List<String> researchDates) {
        this.researchDates = researchDates;
    }

    public List<String> getReportTones() {
        return reportTones;
    }

    public void setReportTones(List<String> reportTones) {
        this.reportTones = reportTones;
    }

    public List<String> getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(List<String> reportFrequency) {
        this.reportFrequency = reportFrequency;
    }
}
