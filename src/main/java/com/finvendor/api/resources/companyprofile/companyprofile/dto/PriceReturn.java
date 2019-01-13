package com.finvendor.api.resources.companyprofile.companyprofile.dto;

public class PriceReturn {
    private  StockPriceReturn stockPriceReturn;
    private Nifty50PriceReturn nifty50PriceReturn;

    public PriceReturn(StockPriceReturn stockPriceReturn, Nifty50PriceReturn nifty50PriceReturn) {
        this.stockPriceReturn = stockPriceReturn;
        this.nifty50PriceReturn = nifty50PriceReturn;
    }

    public StockPriceReturn getStockPriceReturn() {
        return stockPriceReturn;
    }

    public void setStockPriceReturn(StockPriceReturn stockPriceReturn) {
        this.stockPriceReturn = stockPriceReturn;
    }

    public Nifty50PriceReturn getNifty50PriceReturn() {
        return nifty50PriceReturn;
    }

    public void setNifty50PriceReturn(Nifty50PriceReturn nifty50PriceReturn) {
        this.nifty50PriceReturn = nifty50PriceReturn;
    }
}
