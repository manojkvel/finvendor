package com.finvendor.api.resources.screener.stock.strategies.celebrity.dto;

public class JeolGreenblattStrategyDto extends AbstractStrategyDto {
    private String pat;
    private String totalCapital;
    private String revenue;
    private String operatingProfitMargin;
    private String mcap;
    private String totalDebt;
    private String cashAndCashEquiv;

    public JeolGreenblattStrategyDto(String stockId, String companyName, String pat, String totalCapital, String revenue,
            String operatingProfitMargin, String mcap, String totalDebt, String cashAndCashEquiv) {
        super(stockId, companyName);
        this.pat = pat;
        this.totalCapital = totalCapital;
        this.revenue = revenue;
        this.operatingProfitMargin = operatingProfitMargin;
        this.mcap = mcap;
        this.totalDebt = totalDebt;
        this.cashAndCashEquiv = cashAndCashEquiv;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
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
