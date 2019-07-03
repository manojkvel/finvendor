package com.finvendor.api.fvreport.service.pdfcontent;

import com.finvendor.api.fvreport.dao.BroaderBenchmarkIndexData;
import com.finvendor.api.fvreport.dto.*;
import com.finvendor.api.markets.dao.MarketsDao;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MktDataContentServiceImpl implements IPdfContentService<BroaderBenchmarkIndexData> {

    private static final String NIFTY_50 = "Nifty 50";

    @Autowired
    private MarketsDao dao;

    @Override
    public BroaderBenchmarkIndexData getContent(UserWatchListData userWatchListData) throws IOException {
        String userName = userWatchListData.getUserName();
        String currentDate = DateUtils.getCurrentDate();
        String indexSummary = dao.getIndexSummary(NIFTY_50);
        String nifty50IndexValue = getNifty50Value(indexSummary);

        Pair<String, String> indexClosedByUpOrDownValuePair = getIndexClosedByUpOrDownValue(indexSummary);
        String closing = JsonUtil.getValue(indexSummary, "closing");
        String open = JsonUtil.getValue(indexSummary, "open");
        String high = JsonUtil.getValue(indexSummary, "high");
        String low = JsonUtil.getValue(indexSummary, "low");

        String marketsAnalytics = dao.getMarketsAnalytics(NIFTY_50, "");
        String gainer = JsonUtil.getValue(marketsAnalytics, "gainer");
        String looser = JsonUtil.getValue(marketsAnalytics, "looser");
        String unchanged = JsonUtil.getValue(marketsAnalytics, "unchanged");

        String markets = dao.getMarkets(NIFTY_50, "", "1", "5", "percentChange", "desc");
        MarketData marketDataDetailsDetailsForGainer = (MarketData) JsonUtil.convertJsonToPojo(markets, MarketData.class);
        List<TopGainers> topGainers = new ArrayList<>();
        for (MarketDataDetails mdd : marketDataDetailsDetailsForGainer.getMarketData()) {
            String companyName = mdd.getCompanyName();
            String percentChange = mdd.getPercentChange();
            topGainers.add(new TopGainers(companyName, percentChange));
        }

        markets = dao.getMarkets(NIFTY_50, "", "1", "5", "percentChange", "asc");
        MarketData marketDataDetailsForLooser = (MarketData) JsonUtil.convertJsonToPojo(markets, MarketData.class);
        List<TopLoosers> topLoosers = new ArrayList<>();
        for (MarketDataDetails mdd : marketDataDetailsForLooser.getMarketData()) {
            String companyName = mdd.getCompanyName();
            String percentChange = mdd.getPercentChange();
            topLoosers.add(new TopLoosers(companyName, percentChange));
        }
        String consecutiveNumber="";
        return new BroaderBenchmarkIndexData(userName, currentDate, nifty50IndexValue, consecutiveNumber, indexClosedByUpOrDownValuePair.getElement1(),
                indexClosedByUpOrDownValuePair.getElement2(),
                closing, open, high, low, gainer, looser, unchanged, topGainers, topLoosers);
    }

    private String getNifty50Value(String indexSummary) throws IOException {
        String nifty50IndexStr = JsonUtil.getValue(indexSummary, "percentChange");
        String nifty50IndexValue = "NA";
        if (StringUtils.isNotEmpty(nifty50IndexStr)) {
            float percentChangeFloat = Float.parseFloat(nifty50IndexStr);
            if (percentChangeFloat > 0.25F && percentChangeFloat < 1.5F) {
                nifty50IndexValue = "CLOSED HIGHER";
            }
            else if (percentChangeFloat > -1.5F && percentChangeFloat < -0.25F) {
                nifty50IndexValue = "REMAINED SUBDUED";
            }
            else if (percentChangeFloat > -1.5F && percentChangeFloat < 0.25) {
                nifty50IndexValue = "CLOSED FLAT";
            }
            else if (percentChangeFloat > 1.5) {
                nifty50IndexValue = "ENDED SHARPLY HIGHER";
            }
            else if (percentChangeFloat < 1.5) {
                nifty50IndexValue = "PLUNGED SHARPLY";
            }
        }
        return nifty50IndexValue;
    }

    private Pair<String, String> getIndexClosedByUpOrDownValue(String indexSummary) throws IOException {
        String pointChangeStr = JsonUtil.getValue(indexSummary, "pointChange");
        String isUpOrDown;
        if (StringUtils.isNotEmpty(pointChangeStr)) {
            float pointChange = Float.parseFloat(pointChangeStr);
            isUpOrDown = pointChange > 0.0F ? "Up" : "Down";
        }
        else {
            isUpOrDown = "NA";
        }
        return new Pair<>(isUpOrDown, pointChangeStr);
    }
}
