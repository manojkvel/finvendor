package com.finvendor.api.dff.service.internalfeed.screener.dto;

public class EarningPreviewDetails {
    private final String period;
    private final String revenue;
    private final float revenueFloat;
    private final String operatingProfitMargin;
    private final float operatingProfitMarginFloat;
    private final String pat;
    private final float patFloat;
    private final String eps;
    private final float epsFloat;
    private final String netOperatingCashFlow;
    private final float netOperatingCashFlowFloat;
    private final String roe;
    private final float roeFloat;
    private final String de;
    private final float deFloat;
    private final String currentAsset;
    private final float currentAssetFloat;
    private final String currentLiabilities;
    private final float currentLiabilitiesFloat;
    private final String bvShare;
    private final float bvShareFloat;
    private final String totalFreeCashflow;
    private final float totalFreeCashflowFloat;
    private final float rndExpenseFloat;
    private final String totalDebt;
    private final float totalDebtFloat;
    private final String longTermDebt;
    private final float longTermDebtFloat;
    private final String retainedEarning;
    private final float retainedEarningFloat;
    private final String totalCapital;
    private final float totalCapitalFloat;
    private final String cashAndCashEquiv;
    private final float cashAndCashEquivFloat;
    private final String totalAssets;
    private final float totalAssetsFloat;
    private final String avgTotalAssets;
    private final float avgTotalAssetsFloat;
    private String rndExpense;
    private float latestEpsGrowthInPercentageAsFloat;

    public EarningPreviewDetails(String period, String revenue, float revenueFloat, String operatingProfitMargin,
            float operatingProfitMarginFloat, String pat, float patFloat, String eps, float epsFloat, String netOperatingCashFlow,
            float netOperatingCashFlowFloat, String roe, float roeFloat, String de, float deFloat, String currentAsset,
            float currentAssetFloat,
            String currentLiabilities, float currentLiabilitiesFloat, String bvShare, float bvShareFloat, String totalFreeCashflow,
            float totalFreeCashflowFloat, float rndExpenseFloat, String totalDebt, float totalDebtFloat, String longTermDebt,
            float longTermDebtFloat, String retainedEarning, float retainedEarningFloat, String totalCapital, float totalCapitalFloat,
            String cashAndCashEquiv, float cashAndCashEquivFloat, String totalAssets, float totalAssetsFloat, String avgTotalAssets,
            float avgTotalAssetsFloat, String rndExpense, float latestEpsGrowthInPercentageAsFloat) {
        this.period = period;
        this.revenue = revenue;
        this.revenueFloat = revenueFloat;
        this.operatingProfitMargin = operatingProfitMargin;
        this.operatingProfitMarginFloat = operatingProfitMarginFloat;
        this.pat = pat;
        this.patFloat = patFloat;
        this.eps = eps;
        this.epsFloat = epsFloat;
        this.netOperatingCashFlow = netOperatingCashFlow;
        this.netOperatingCashFlowFloat = netOperatingCashFlowFloat;
        this.roe = roe;
        this.roeFloat = roeFloat;
        this.de = de;
        this.deFloat = deFloat;
        this.currentAsset = currentAsset;
        this.currentAssetFloat = currentAssetFloat;
        this.currentLiabilities = currentLiabilities;
        this.currentLiabilitiesFloat = currentLiabilitiesFloat;
        this.bvShare = bvShare;
        this.bvShareFloat = bvShareFloat;
        this.totalFreeCashflow = totalFreeCashflow;
        this.totalFreeCashflowFloat = totalFreeCashflowFloat;
        this.rndExpenseFloat = rndExpenseFloat;
        this.totalDebt = totalDebt;
        this.totalDebtFloat = totalDebtFloat;
        this.longTermDebt = longTermDebt;
        this.longTermDebtFloat = longTermDebtFloat;
        this.retainedEarning = retainedEarning;
        this.retainedEarningFloat = retainedEarningFloat;
        this.totalCapital = totalCapital;
        this.totalCapitalFloat = totalCapitalFloat;
        this.cashAndCashEquiv = cashAndCashEquiv;
        this.cashAndCashEquivFloat = cashAndCashEquivFloat;
        this.totalAssets = totalAssets;
        this.totalAssetsFloat = totalAssetsFloat;
        this.avgTotalAssets = avgTotalAssets;
        this.avgTotalAssetsFloat = avgTotalAssetsFloat;
        this.rndExpense = rndExpense;
        this.latestEpsGrowthInPercentageAsFloat = latestEpsGrowthInPercentageAsFloat;
    }

    public String getPeriod() {
        return period;
    }

    public String getRevenue() {
        return revenue;
    }

    public float getRevenueFloat() {
        return revenueFloat;
    }

    public String getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public float getOperatingProfitMarginFloat() {
        return operatingProfitMarginFloat;
    }

    public String getPat() {
        return pat;
    }

    public float getPatFloat() {
        return patFloat;
    }

    public String getEps() {
        return eps;
    }

    public float getEpsFloat() {
        return epsFloat;
    }

    public String getNetOperatingCashFlow() {
        return netOperatingCashFlow;
    }

    public float getNetOperatingCashFlowFloat() {
        return netOperatingCashFlowFloat;
    }

    public String getRoe() {
        return roe;
    }

    public float getRoeFloat() {
        return roeFloat;
    }

    public String getDe() {
        return de;
    }

    public float getDeFloat() {
        return deFloat;
    }

    public String getCurrentAsset() {
        return currentAsset;
    }

    public float getCurrentAssetFloat() {
        return currentAssetFloat;
    }

    public String getCurrentLiabilities() {
        return currentLiabilities;
    }

    public float getCurrentLiabilitiesFloat() {
        return currentLiabilitiesFloat;
    }

    public String getBvShare() {
        return bvShare;
    }

    public float getBvShareFloat() {
        return bvShareFloat;
    }

    public String getTotalFreeCashflow() {
        return totalFreeCashflow;
    }

    public float getTotalFreeCashflowFloat() {
        return totalFreeCashflowFloat;
    }

    public float getRndExpenseFloat() {
        return rndExpenseFloat;
    }

    public String getTotalDebt() {
        return totalDebt;
    }

    public float getTotalDebtFloat() {
        return totalDebtFloat;
    }

    public String getLongTermDebt() {
        return longTermDebt;
    }

    public float getLongTermDebtFloat() {
        return longTermDebtFloat;
    }

    public String getRetainedEarning() {
        return retainedEarning;
    }

    public float getRetainedEarningFloat() {
        return retainedEarningFloat;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public float getTotalCapitalFloat() {
        return totalCapitalFloat;
    }

    public String getCashAndCashEquiv() {
        return cashAndCashEquiv;
    }

    public float getCashAndCashEquivFloat() {
        return cashAndCashEquivFloat;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public float getTotalAssetsFloat() {
        return totalAssetsFloat;
    }

    public String getAvgTotalAssets() {
        return avgTotalAssets;
    }

    public float getAvgTotalAssetsFloat() {
        return avgTotalAssetsFloat;
    }

    public String getRndExpense() {
        return rndExpense;
    }

    public void setRndExpense(String rndExpense) {
        this.rndExpense = rndExpense;
    }

    public float getLatestEpsGrowthInPercentageAsFloat() {
        return latestEpsGrowthInPercentageAsFloat;
    }

    public void setLatestEpsGrowthInPercentageAsFloat(float latestEpsGrowthInPercentageAsFloat) {
        this.latestEpsGrowthInPercentageAsFloat = latestEpsGrowthInPercentageAsFloat;
    }
}
