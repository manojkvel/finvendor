package com.finvendor.api.report.dto.marketdatacontent;

import java.io.Serializable;
import java.util.List;

public class MarketData implements Serializable {
    private String title;
    private List<MarketDataDetails> marketData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MarketDataDetails> getMarketData() {
        return marketData;
    }

    public void setMarketData(List<MarketDataDetails> marketData) {
        this.marketData = marketData;
    }
}
