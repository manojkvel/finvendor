package com.finvendor.api.report.dto.marketdatacontent;

import java.util.List;

public class MidCapIndexData extends MarketIndexData {
    public MidCapIndexData(String userName, String currentDate, String nifty50Index, String consecutiveNumber, String upDown,
            String closeBy, String closeAt, String indexOpen, String dayHigh, String dayLow, String gainer, String looser,
            String unchanged, List<TopGainers> topGainers, List<TopLoosers> topLoosers) {
        super(userName,currentDate,nifty50Index,consecutiveNumber,upDown,closeBy,closeAt,indexOpen,dayHigh,dayLow,gainer,looser,unchanged,topGainers,topLoosers);
    }
}
