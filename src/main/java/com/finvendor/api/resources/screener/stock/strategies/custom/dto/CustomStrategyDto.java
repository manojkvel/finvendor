package com.finvendor.api.resources.screener.stock.strategies.custom.dto;

import java.io.Serializable;

public class CustomStrategyDto implements Serializable {
    private String stockId;
    private String companyName;
    private String mcap;
    private String industry;
    private String pe;
    private String pb;
    private String debtToEquityRatio;
    private String currentRatio;
    private String netOperatingCashFlow;
    private String roeInPercentage;
    private String operatingProfitMargin;
    private String patGrowthInPercentage;
    private String epsGrowthInPercentage;
    private String revenueGrowthInPercentage;
    private String totalFreeCashFlow;
    private String returnOnAssetInPercentage;
    private String divYield;
    private String rotcInPercentage;

    public CustomStrategyDto(String stockId, String companyName, String mcap, String industry, String pe, String pb,
            String debtToEquityRatio, String currentRatio, String netOperatingCashFlow, String roeInPercentage,
            String operatingProfitMargin, String patGrowthInPercentage, String epsGrowthInPercentage,
            String revenueGrowthInPercentage, String totalFreeCashFlow, String returnOnAssetInPercentage, String divYield,
            String rotcInPercentage) {
        this.stockId = stockId;
        this.companyName = companyName;
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

    public String getStockId() {
        return stockId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getMcap() {
        return mcap;
    }

    public String getIndustry() {
        return industry;
    }

    public String getPe() {
        return pe;
    }

    public String getPb() {
        return pb;
    }

    public String getDebtToEquityRatio() {
        return debtToEquityRatio;
    }

    public String getCurrentRatio() {
        return currentRatio;
    }

    public String getNetOperatingCashFlow() {
        return netOperatingCashFlow;
    }

    public String getRoeInPercentage() {
        return roeInPercentage;
    }

    public String getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public String getPatGrowthInPercentage() {
        return patGrowthInPercentage;
    }

    public String getEpsGrowthInPercentage() {
        return epsGrowthInPercentage;
    }

    public String getRevenueGrowthInPercentage() {
        return revenueGrowthInPercentage;
    }

    public String getTotalFreeCashFlow() {
        return totalFreeCashFlow;
    }

    public String getReturnOnAssetInPercentage() {
        return returnOnAssetInPercentage;
    }

    public String getDivYield() {
        return divYield;
    }

    public String getRotcInPercentage() {
        return rotcInPercentage;
    }
}
