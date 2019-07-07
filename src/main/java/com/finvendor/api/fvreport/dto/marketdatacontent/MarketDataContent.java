package com.finvendor.api.fvreport.dto.marketdatacontent;

import com.finvendor.common.infra.pdf.PDFContent;

public class MarketDataContent extends PDFContent {

    private final MarketIndexData broaderIndexData;
    private final MarketIndexData midCapIndexData;
    private final MarketIndexData smallCapIndexData;

    public MarketDataContent(MarketIndexData broaderIndexData, MarketIndexData midCapIndexData, MarketIndexData smallCapIndexData) {
        this.broaderIndexData = broaderIndexData;
        this.midCapIndexData = midCapIndexData;
        this.smallCapIndexData = smallCapIndexData;
    }

    public MarketIndexData getBroaderIndexData() {
        return broaderIndexData;
    }

    public MarketIndexData getMidCapIndexData() {
        return midCapIndexData;
    }

    public MarketIndexData getSmallCapIndexData() {
        return smallCapIndexData;
    }
}
