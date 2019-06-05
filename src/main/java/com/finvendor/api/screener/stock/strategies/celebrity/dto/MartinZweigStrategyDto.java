package com.finvendor.api.screener.stock.strategies.celebrity.dto;

public class MartinZweigStrategyDto extends AbstractStrategyDto {
    private String pe;
    private String nifty50Pe;
    private String latestRevenueGrowth;
    private String yearWiseEpsGrowth;
    private String allYearEpsGrowth;
    private String deRatio;

    public MartinZweigStrategyDto(String stockId, String companyName) {
        super(stockId, companyName);
    }

    public MartinZweigStrategyDto(String stockId, String companyName, String pe, String nifty50Pe, String latestRevenueGrowth, String yearWiseEpsGrowth, String allYearEpsGrowth, String deRatio) {
        super(stockId, companyName);
        this.pe = pe;
        this.nifty50Pe = nifty50Pe;
        this.latestRevenueGrowth = latestRevenueGrowth;
        this.yearWiseEpsGrowth = yearWiseEpsGrowth;
        this.allYearEpsGrowth = allYearEpsGrowth;
        this.deRatio = deRatio;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getNifty50Pe() {
        return nifty50Pe;
    }

    public void setNifty50Pe(String nifty50Pe) {
        this.nifty50Pe = nifty50Pe;
    }

    public String getLatestRevenueGrowth() {
        return latestRevenueGrowth;
    }

    public void setLatestRevenueGrowth(String latestRevenueGrowth) {
        this.latestRevenueGrowth = latestRevenueGrowth;
    }

    public String getYearWiseEpsGrowth() {
        return yearWiseEpsGrowth;
    }

    public void setYearWiseEpsGrowth(String yearWiseEpsGrowth) {
        this.yearWiseEpsGrowth = yearWiseEpsGrowth;
    }

    public String getAllYearEpsGrowth() {
        return allYearEpsGrowth;
    }

    public void setAllYearEpsGrowth(String allYearEpsGrowth) {
        this.allYearEpsGrowth = allYearEpsGrowth;
    }

    public String getDeRatio() {
        return deRatio;
    }

    public void setDeRatio(String deRatio) {
        this.deRatio = deRatio;
    }
}
