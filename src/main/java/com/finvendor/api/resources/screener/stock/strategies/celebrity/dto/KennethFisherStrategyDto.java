package com.finvendor.api.resources.screener.stock.strategies.celebrity.dto;

public class KennethFisherStrategyDto extends AbstractStrategyDto {

    private String psr;
    private String mcap;
    private String annualRevenue;
    private String de;
    private String _1yrEpsGrowth;
    private String inflationRate;
    private String _3YrAvgNetProfitMargin;
    private String rndExpenditures;

    public KennethFisherStrategyDto(String stockId, String companyName, String psr, String mcap, String annualRevenue, String de,
            String _1yrEpsGrowth, String inflationRate, String _3YrAvgNetProfitMargin, String rndExpenditures) {
        super(stockId, companyName);
        this.psr = psr;
        this.mcap = mcap;
        this.annualRevenue = annualRevenue;
        this.de = de;
        this._1yrEpsGrowth = _1yrEpsGrowth;
        this.inflationRate = inflationRate;
        this._3YrAvgNetProfitMargin = _3YrAvgNetProfitMargin;
        this.rndExpenditures = rndExpenditures;
    }

    public String getPsr() {
        return psr;
    }

    public void setPsr(String psr) {
        this.psr = psr;
    }

    public String getMcap() {
        return mcap;
    }

    public void setMcap(String mcap) {
        this.mcap = mcap;
    }

    public String getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(String annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String get_1yrEpsGrowth() {
        return _1yrEpsGrowth;
    }

    public void set_1yrEpsGrowth(String _1yrEpsGrowth) {
        this._1yrEpsGrowth = _1yrEpsGrowth;
    }

    public String getInflationRate() {
        return inflationRate;
    }

    public void setInflationRate(String inflationRate) {
        this.inflationRate = inflationRate;
    }

    public String get_3YrAvgNetProfitMargin() {
        return _3YrAvgNetProfitMargin;
    }

    public void set_3YrAvgNetProfitMargin(String _3YrAvgNetProfitMargin) {
        this._3YrAvgNetProfitMargin = _3YrAvgNetProfitMargin;
    }

    public String getRndExpenditures() {
        return rndExpenditures;
    }

    public void setRndExpenditures(String rndExpenditures) {
        this.rndExpenditures = rndExpenditures;
    }
}
