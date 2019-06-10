package com.finvendor.api.companyprofile.companyprofile.dto;

import java.util.Map;

public class PriceHistoryDto {
    private Map<String,String> stock;
    private Map<String,String> nifty50;


    public PriceHistoryDto() {

    }

    public PriceHistoryDto(Map<String, String> stock, Map<String, String> nifty50) {
        this.stock = stock;
        this.nifty50 = nifty50;
    }

    public Map<String, String> getStock() {
        return stock;
    }

    public void setStock(Map<String, String> stock) {
        this.stock = stock;
    }

    public Map<String, String> getNifty50() {
        return nifty50;
    }

    public void setNifty50(Map<String, String> nifty50) {
        this.nifty50 = nifty50;
    }
}