package com.finvendor.api.dff.service.internalfeed.screener.dto;

public class JoelEbitAndEnterpriseDto {
    private String companyId;
    private String companyName;
    private String ticker;

    private Float ebitDividedByEnterprice_value;
    private String revenue;
    private String operatingProfitMargin;
    private String mcap;
    private String totalDebt;
    private String cashAndCashEquiv;

    public JoelEbitAndEnterpriseDto(String companyId, String companyName, String ticker, Float ebitDividedByEnterprice_value,
            String revenue,
            String operatingProfitMargin, String mcap, String totalDebt, String cashAndCashEquiv) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.ticker = ticker;
        this.ebitDividedByEnterprice_value = ebitDividedByEnterprice_value;
        this.revenue = revenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.mcap = mcap;
        this.totalDebt = totalDebt;
        this.cashAndCashEquiv = cashAndCashEquiv;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}

