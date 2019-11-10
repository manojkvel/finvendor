package com.finvendor.api.report.dto.marketdatacontent;

import java.util.List;

public class AllMarketIndexData extends MarketIndexData {
    public AllMarketIndexData(String userName, String currentDate, String nifty50MappingKeyword, String consecutiveMsg, String upDown,
            String closeBy, String closeAt, String percentChangeStr, String indexOpen, String dayHigh, String dayLow, String gainer,
            String looser,
            String unchanged, List<TopGainers> topGainers, List<TopLoosers> topLosers, String pe) {
        super(userName, currentDate, nifty50MappingKeyword, consecutiveMsg, upDown, closeBy, closeAt, percentChangeStr, indexOpen, dayHigh,
                dayLow, gainer, looser, unchanged, topGainers, topLosers, pe);
    }
}
