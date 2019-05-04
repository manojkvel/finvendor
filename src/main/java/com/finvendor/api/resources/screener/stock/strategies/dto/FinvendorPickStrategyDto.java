package com.finvendor.api.resources.screener.stock.strategies.dto;

public class FinvendorPickStrategyDto extends AbstractStrategyDto {
    private String totalFreeCashFlow;
    private String salesGrowth;
    private String oneYearEpsGrowth;
    private String peRatio;
    private String currentRatio;
    private String currentRoe;
    private String currentOperatingProfitMargin;
    private String de;
    private String allYearAverageRoe;
    private String longTermDebt;
    private String annualPat;
    private String isAllYearEpsGrowthPositive;
    private String rotc;
    private String earningYield;
    private String retainedEarning;
    private String outstandingShare;

    public FinvendorPickStrategyDto(String stockId, String companyName, String totalFreeCashFlow, String salesGrowth,
            String oneYearEpsGrowth, String peRatio, String currentRatio, String currentRoe, String currentOperatingProfitMargin,
            String de, String allYearAverageRoe, String longTermDebt, String annualPat, String isAllYearEpsGrowthPositive,
            String rotc, String earningYield, String retainedEarning, String outstandingShare) {
        super(stockId, companyName);
        this.totalFreeCashFlow = totalFreeCashFlow;
        this.salesGrowth = salesGrowth;
        this.oneYearEpsGrowth = oneYearEpsGrowth;
        this.peRatio = peRatio;
        this.currentRatio = currentRatio;
        this.currentRoe = currentRoe;
        this.currentOperatingProfitMargin = currentOperatingProfitMargin;
        this.de = de;
        this.allYearAverageRoe = allYearAverageRoe;
        this.longTermDebt = longTermDebt;
        this.annualPat = annualPat;
        this.isAllYearEpsGrowthPositive = isAllYearEpsGrowthPositive;
        this.rotc = rotc;
        this.earningYield = earningYield;
        this.retainedEarning = retainedEarning;
        this.outstandingShare = outstandingShare;
    }

    public String getTotalFreeCashFlow() {
        return totalFreeCashFlow;
    }

    public void setTotalFreeCashFlow(String totalFreeCashFlow) {
        this.totalFreeCashFlow = totalFreeCashFlow;
    }

    public String getSalesGrowth() {
        return salesGrowth;
    }

    public void setSalesGrowth(String salesGrowth) {
        this.salesGrowth = salesGrowth;
    }

    public String getOneYearEpsGrowth() {
        return oneYearEpsGrowth;
    }

    public void setOneYearEpsGrowth(String oneYearEpsGrowth) {
        this.oneYearEpsGrowth = oneYearEpsGrowth;
    }

    public String getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(String peRatio) {
        this.peRatio = peRatio;
    }

    public String getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(String currentRatio) {
        this.currentRatio = currentRatio;
    }

    public String getCurrentRoe() {
        return currentRoe;
    }

    public void setCurrentRoe(String currentRoe) {
        this.currentRoe = currentRoe;
    }

    public String getCurrentOperatingProfitMargin() {
        return currentOperatingProfitMargin;
    }

    public void setCurrentOperatingProfitMargin(String currentOperatingProfitMargin) {
        this.currentOperatingProfitMargin = currentOperatingProfitMargin;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getAllYearAverageRoe() {
        return allYearAverageRoe;
    }

    public void setAllYearAverageRoe(String allYearAverageRoe) {
        this.allYearAverageRoe = allYearAverageRoe;
    }

    public String getLongTermDebt() {
        return longTermDebt;
    }

    public void setLongTermDebt(String longTermDebt) {
        this.longTermDebt = longTermDebt;
    }

    public String getAnnualPat() {
        return annualPat;
    }

    public void setAnnualPat(String annualPat) {
        this.annualPat = annualPat;
    }

    public String getIsAllYearEpsGrowthPositive() {
        return isAllYearEpsGrowthPositive;
    }

    public void setIsAllYearEpsGrowthPositive(String isAllYearEpsGrowthPositive) {
        this.isAllYearEpsGrowthPositive = isAllYearEpsGrowthPositive;
    }

    public String getRotc() {
        return rotc;
    }

    public void setRotc(String rotc) {
        this.rotc = rotc;
    }

    public String getEarningYield() {
        return earningYield;
    }

    public void setEarningYield(String earningYield) {
        this.earningYield = earningYield;
    }

    public String getRetainedEarning() {
        return retainedEarning;
    }

    public void setRetainedEarning(String retainedEarning) {
        this.retainedEarning = retainedEarning;
    }

    public String getOutstandingShare() {
        return outstandingShare;
    }

    public void setOutstandingShare(String outstandingShare) {
        this.outstandingShare = outstandingShare;
    }
}
