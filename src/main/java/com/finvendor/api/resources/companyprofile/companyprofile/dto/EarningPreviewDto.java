package com.finvendor.api.resources.companyprofile.companyprofile.dto;

import java.util.List;

public class EarningPreviewDto {

    private List<EarningPreviewResultDto> quarterly;
    private List<EarningPreviewResultDto> yearly;

    public EarningPreviewDto() {
    }

    public EarningPreviewDto(List<EarningPreviewResultDto> quarterly, List<EarningPreviewResultDto> yearly) {
        this.quarterly = quarterly;
        this.yearly = yearly;
    }

    public List<EarningPreviewResultDto> getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(List<EarningPreviewResultDto> quarterly) {
        this.quarterly = quarterly;
    }

    public List<EarningPreviewResultDto> getYearly() {
        return yearly;
    }

    public void setYearly(List<EarningPreviewResultDto> yearly) {
        this.yearly = yearly;
    }

    public static class EarningPreviewResultDto {
        private String period;
        private String revenue;
        private String operatingProfitMargin;
        private String profitAfterTax;
        private String eps;
        private String netOperatingCashFlow;

        public EarningPreviewResultDto(String period, String revenue, String operatingProfitMargin, String profitAfterTax, String eps, String netOperatingCashFlow) {
            this.period = period;
            this.revenue = revenue;
            this.operatingProfitMargin = operatingProfitMargin;
            this.profitAfterTax = profitAfterTax;
            this.eps = eps;
            this.netOperatingCashFlow = netOperatingCashFlow;
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

        public String getNetOperatingCashFlow() {
            return netOperatingCashFlow;
        }

        public void setNetOperatingCashFlow(String netOperatingCashFlow) {
            this.netOperatingCashFlow = netOperatingCashFlow;
        }
    }

}
