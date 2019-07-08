package com.finvendor.api.fvreport.dto.financials;

import java.io.Serializable;

public class FinancialsQuarterly implements Serializable {
    private String companyName;
    private String period;
    private String revenue;
    private String operatingProfitMargin;
    private String profitAfterTax;
    private String eps;

    public FinancialsQuarterly(String companyName, String period, String revenue, String operatingProfitMargin,
            String profitAfterTax, String eps) {
        this.companyName = companyName;
        this.period = period;
        this.revenue = revenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.profitAfterTax = profitAfterTax;
        this.eps = eps;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPeriod() {
        return period;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public String getProfitAfterTax() {
        return profitAfterTax;
    }

    public String getEps() {
        return eps;
    }
}
