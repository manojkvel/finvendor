package com.finvendor.api.resources.screener.stock.strategies.celebrity.dto;

public class JamesShaughnessyStrategyDto extends AbstractStrategyDto {
    private String cmp;
    private String revenue;
    private String eps;
    private String pb;
    private String mcap;
    private String netOperatingCashFlow;

    public JamesShaughnessyStrategyDto(String stockId, String companyName, String cmp, String revenue, String eps, String pb,
            String mcap, String netOperatingCashFlow) {
        super(stockId, companyName);
        this.cmp = cmp;
        this.revenue = revenue;
        this.eps = eps;
        this.pb = pb;
        this.mcap = mcap;
        this.netOperatingCashFlow = netOperatingCashFlow;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public String getMcap() {
        return mcap;
    }

    public void setMcap(String mcap) {
        this.mcap = mcap;
    }

    public String getNetOperatingCashFlow() {
        return netOperatingCashFlow;
    }

    public void setNetOperatingCashFlow(String netOperatingCashFlow) {
        this.netOperatingCashFlow = netOperatingCashFlow;
    }
}
