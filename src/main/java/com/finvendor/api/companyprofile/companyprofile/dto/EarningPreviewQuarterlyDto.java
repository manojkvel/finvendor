package com.finvendor.api.companyprofile.companyprofile.dto;

public class EarningPreviewQuarterlyDto {
    private String period;
    private String revenue;
    private String operatingProfitMargin;
    private String profitAfterTax;
    private String eps;

    public EarningPreviewQuarterlyDto(String period, String revenue, String operatingProfitMargin, String profitAfterTax, String eps) {
        this.period = period;
        this.revenue = revenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.profitAfterTax = profitAfterTax;
        this.eps = eps;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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

    public String getProfitAfterTax() {
        return profitAfterTax;
    }

    public void setProfitAfterTax(String profitAfterTax) {
        this.profitAfterTax = profitAfterTax;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
}
