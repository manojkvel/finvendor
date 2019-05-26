package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

public class Filters{

    private String[] mcap;
    private String[] industry;
    private String[] pe;
    private String[] pb;
    private String[] debtToEquityRatio;
    private String[] currentRatio;
    private String[] netOperatingCashFlow;
    private String[] roeInPercentage;
    private String[] operatingProfitMargin;
    private String[] patGrowthInPercentage;
    private String[] epsGrowthInPercentage;
    private String[] revenueGrowthInPercentage;
    private String[] totalFreeCashFlow;
    private String[] returnOnAssetInPercentage;
    private String[] divYield;
    private String[] rotcInPercentage;

    public Filters(String[] mcap, String[] industry, String[] pe, String[] pb, String[] debtToEquityRatio, String[] currentRatio,
            String[] netOperatingCashFlow, String[] roeInPercentage, String[] operatingProfitMargin, String[] patGrowthInPercentage,
            String[] epsGrowthInPercentage, String[] revenueGrowthInPercentage, String[] totalFreeCashFlow,
            String[] returnOnAssetInPercentage, String[] divYield, String[] rotcInPercentage) {
        this.mcap = mcap;
        this.industry = industry;
        this.pe = pe;
        this.pb = pb;
        this.debtToEquityRatio = debtToEquityRatio;
        this.currentRatio = currentRatio;
        this.netOperatingCashFlow = netOperatingCashFlow;
        this.roeInPercentage = roeInPercentage;
        this.operatingProfitMargin = operatingProfitMargin;
        this.patGrowthInPercentage = patGrowthInPercentage;
        this.epsGrowthInPercentage = epsGrowthInPercentage;
        this.revenueGrowthInPercentage = revenueGrowthInPercentage;
        this.totalFreeCashFlow = totalFreeCashFlow;
        this.returnOnAssetInPercentage = returnOnAssetInPercentage;
        this.divYield = divYield;
        this.rotcInPercentage = rotcInPercentage;
    }

    public String[] getMcap() {
        return mcap;
    }

    public void setMcap(String[] mcap) {
        this.mcap = mcap;
    }

    public String[] getIndustry() {
        return industry;
    }

    public void setIndustry(String[] industry) {
        this.industry = industry;
    }

    public String[] getPe() {
        return pe;
    }

    public void setPe(String[] pe) {
        this.pe = pe;
    }

    public String[] getPb() {
        return pb;
    }

    public void setPb(String[] pb) {
        this.pb = pb;
    }

    public String[] getDebtToEquityRatio() {
        return debtToEquityRatio;
    }

    public void setDebtToEquityRatio(String[] debtToEquityRatio) {
        this.debtToEquityRatio = debtToEquityRatio;
    }

    public String[] getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(String[] currentRatio) {
        this.currentRatio = currentRatio;
    }

    public String[] getNetOperatingCashFlow() {
        return netOperatingCashFlow;
    }

    public void setNetOperatingCashFlow(String[] netOperatingCashFlow) {
        this.netOperatingCashFlow = netOperatingCashFlow;
    }

    public String[] getRoeInPercentage() {
        return roeInPercentage;
    }

    public void setRoeInPercentage(String[] roeInPercentage) {
        this.roeInPercentage = roeInPercentage;
    }

    public String[] getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public void setOperatingProfitMargin(String[] operatingProfitMargin) {
        this.operatingProfitMargin = operatingProfitMargin;
    }

    public String[] getPatGrowthInPercentage() {
        return patGrowthInPercentage;
    }

    public void setPatGrowthInPercentage(String[] patGrowthInPercentage) {
        this.patGrowthInPercentage = patGrowthInPercentage;
    }

    public String[] getEpsGrowthInPercentage() {
        return epsGrowthInPercentage;
    }

    public void setEpsGrowthInPercentage(String[] epsGrowthInPercentage) {
        this.epsGrowthInPercentage = epsGrowthInPercentage;
    }

    public String[] getRevenueGrowthInPercentage() {
        return revenueGrowthInPercentage;
    }

    public void setRevenueGrowthInPercentage(String[] revenueGrowthInPercentage) {
        this.revenueGrowthInPercentage = revenueGrowthInPercentage;
    }

    public String[] getTotalFreeCashFlow() {
        return totalFreeCashFlow;
    }

    public void setTotalFreeCashFlow(String[] totalFreeCashFlow) {
        this.totalFreeCashFlow = totalFreeCashFlow;
    }

    public String[] getReturnOnAssetInPercentage() {
        return returnOnAssetInPercentage;
    }

    public void setReturnOnAssetInPercentage(String[] returnOnAssetInPercentage) {
        this.returnOnAssetInPercentage = returnOnAssetInPercentage;
    }

    public String[] getDivYield() {
        return divYield;
    }

    public void setDivYield(String[] divYield) {
        this.divYield = divYield;
    }

    public String[] getRotcInPercentage() {
        return rotcInPercentage;
    }

    public void setRotcInPercentage(String[] rotcInPercentage) {
        this.rotcInPercentage = rotcInPercentage;
    }
}
