package com.finvendor.api.markets.dto.indexstock;

import com.finvendor.api.markets.dto.CustomMarketsDto;

import java.util.List;

public class StockData {
    private String title;
    private List<CustomMarketsDto> stocks;

    public StockData(String title, List<CustomMarketsDto> stocks) {
        this.title = title;
        this.stocks = stocks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CustomMarketsDto> getStocks() {
        return stocks;
    }

    public void setStocks(List<CustomMarketsDto> stocks) {
        this.stocks = stocks;
    }
}
