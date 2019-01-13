package com.finvendor.api.resources.companyprofile.companyprofile.dto;

import java.util.Map;

public class StockPriceReturn {
    private Map<String,String> stockPriceReturn;

    public StockPriceReturn(Map<String, String> stockPriceReturn) {
        this.stockPriceReturn = stockPriceReturn;
    }

    public Map<String, String> getStockPriceReturn() {
        return stockPriceReturn;
    }

    public void setStockPriceReturn(Map<String, String> stockPriceReturn) {
        this.stockPriceReturn = stockPriceReturn;
    }
}
