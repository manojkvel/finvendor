package com.finvendor.api.datafeed.service.internalfeed.screener.dto;

public class JoelDto {
    private Float rotc;
    private String recentYrPat;
    private String totalCapital;

    private String companyName;
    private String ticker;

    private Float ebitDividedByEnterprice_value;
    private String revenue;
    private String operatingProfitMargin;
    private String mcap;
    private String totalDebt;
    private String cashAndCashEquiv;

    public JoelDto(Float rotc, String recentYrPat, String totalCapital, String companyName, String ticker,
            Float ebitDividedByEnterprice_value, String revenue, String operatingProfitMargin, String mcap, String totalDebt,
            String cashAndCashEquiv) {
        this.rotc = rotc;
        this.recentYrPat = recentYrPat;
        this.totalCapital = totalCapital;
        this.companyName = companyName;
        this.ticker = ticker;
        this.ebitDividedByEnterprice_value = ebitDividedByEnterprice_value;
        this.revenue = revenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.mcap = mcap;
        this.totalDebt = totalDebt;
        this.cashAndCashEquiv = cashAndCashEquiv;
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

    public Float getEbitDividedByEnterprice_value() {
        return ebitDividedByEnterprice_value;
    }

    public void setEbitDividedByEnterprice_value(Float ebitDividedByEnterprice_value) {
        this.ebitDividedByEnterprice_value = ebitDividedByEnterprice_value;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public void setOperatingProfitMargin(String operatingProfitMargin) {
        this.operatingProfitMargin = operatingProfitMargin;
    }

    public String getMcap() {
        return mcap;
    }

    public void setMcap(String mcap) {
        this.mcap = mcap;
    }

    public String getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(String totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getCashAndCashEquiv() {
        return cashAndCashEquiv;
    }

    public void setCashAndCashEquiv(String cashAndCashEquiv) {
        this.cashAndCashEquiv = cashAndCashEquiv;
    }
}
