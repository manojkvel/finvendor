package com.finvendor.api.resources.screener.stock.strategies.celebrity.dto;

public class BenjaminGrahamStrategyDto extends AbstractStrategyDto {
    private String totalDebt;
    private String currentAssets;
    private String currentLiabilities;
    private String is5YrEPSGrowthPositive;
    private String pe;
    private String pb;
    private String dividenYield;

    public BenjaminGrahamStrategyDto(String stockId, String companyName, String totalDebt, String currentAssets,
            String currentLiabilities, String is5YrEPSGrowthPositive, String pe, String pb, String dividenYield) {
        super(stockId, companyName);
        this.totalDebt = totalDebt;
        this.currentAssets = currentAssets;
        this.currentLiabilities = currentLiabilities;
        this.is5YrEPSGrowthPositive = is5YrEPSGrowthPositive;
        this.pe = pe;
        this.pb = pb;
        this.dividenYield = dividenYield;
    }

    public String getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(String totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(String currentAssets) {
        this.currentAssets = currentAssets;
    }

    public String getCurrentLiabilities() {
        return currentLiabilities;
    }

    public void setCurrentLiabilities(String currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    public String isIs5YrEPSGrowthPositive() {
        return is5YrEPSGrowthPositive;
    }

    public void setIs5YrEPSGrowthPositive(String is5YrEPSGrowthPositive) {
        this.is5YrEPSGrowthPositive = is5YrEPSGrowthPositive;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public String getDividenYield() {
        return dividenYield;
    }

    public void setDividenYield(String dividenYield) {
        this.dividenYield = dividenYield;
    }

    public String getIs5YrEPSGrowthPositive() {
        return is5YrEPSGrowthPositive;
    }
}

