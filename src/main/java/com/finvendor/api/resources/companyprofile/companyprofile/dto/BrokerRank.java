package com.finvendor.api.resources.companyprofile.companyprofile.dto;

public class BrokerRank {
    private int totalBuyRecomm;
    private int totalSellRecomm;
    private int totalNeutralRecomm;
    private float averageTargetPrice;
    private float upside;

    public BrokerRank(int totalBuyRecomm, int totalSellRecomm, int totalNeutralRecomm, float averageTargetPrice, float upside) {
        this.totalBuyRecomm = totalBuyRecomm;
        this.totalSellRecomm = totalSellRecomm;
        this.totalNeutralRecomm = totalNeutralRecomm;
        this.averageTargetPrice = averageTargetPrice;
        this.upside = upside;
    }

    public int getTotalBuyRecomm() {
        return totalBuyRecomm;
    }

    public void setTotalBuyRecomm(int totalBuyRecomm) {
        this.totalBuyRecomm = totalBuyRecomm;
    }

    public int getTotalSellRecomm() {
        return totalSellRecomm;
    }

    public void setTotalSellRecomm(int totalSellRecomm) {
        this.totalSellRecomm = totalSellRecomm;
    }

    public int getTotalNeutralRecomm() {
        return totalNeutralRecomm;
    }

    public void setTotalNeutralRecomm(int totalNeutralRecomm) {
        this.totalNeutralRecomm = totalNeutralRecomm;
    }

    public float getUpside() {
        return upside;
    }

    public void setUpside(float upside) {
        this.upside = upside;
    }

    public float getAverageTargetPrice() {
        return averageTargetPrice;
    }

    public void setAverageTargetPrice(float averageTargetPrice) {
        this.averageTargetPrice = averageTargetPrice;
    }
}
