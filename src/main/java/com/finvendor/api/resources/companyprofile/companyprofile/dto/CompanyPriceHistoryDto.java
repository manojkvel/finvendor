package com.finvendor.api.resources.companyprofile.companyprofile.dto;

public class CompanyPriceHistoryDto {

    private String stockId;
    private String priceSourceCode;
    private String priceDate;
    private String openPrice;
    private String highPrice;
    private String lowPrice;
    private String closePrice;
    private String lastTracePrice;

    public CompanyPriceHistoryDto(String stockId, String priceSourceCode, String priceDate, String openPrice, String highPrice, String lowPrice, String closePrice, String lastTracePrice) {
        this.stockId = stockId;
        this.priceSourceCode = priceSourceCode;
        this.priceDate = priceDate;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.lastTracePrice = lastTracePrice;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(String priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public String getLastTracePrice() {
        return lastTracePrice;
    }

    public void setLastTracePrice(String lastTracePrice) {
        this.lastTracePrice = lastTracePrice;
    }
}
