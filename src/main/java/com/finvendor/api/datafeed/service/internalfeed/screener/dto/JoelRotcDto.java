package com.finvendor.api.datafeed.service.internalfeed.screener.dto;

public class JoelRotcDto {
    private String companyId;
    private String companyName;
    private String ticker;
    private Float rotc;
    private String recentYrPat;
    private String totalCapital;

    public JoelRotcDto(String companyId, String companyName, String ticker, Float rotc, String recentYrPat, String totalCapital) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.ticker = ticker;
        this.rotc = rotc;
        this.recentYrPat = recentYrPat;
        this.totalCapital = totalCapital;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Float getRotc() {
        return rotc;
    }

    public void setRotc(Float rotc) {
        this.rotc = rotc;
    }

    public String getRecentYrPat() {
        return recentYrPat;
    }

    public void setRecentYrPat(String recentYrPat) {
        this.recentYrPat = recentYrPat;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
