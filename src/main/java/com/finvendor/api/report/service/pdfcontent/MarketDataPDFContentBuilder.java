package com.finvendor.api.report.service.pdfcontent;

import com.finvendor.api.markets.dao.MarketsDao;
import com.finvendor.api.report.dto.marketdatacontent.*;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.finvendor.api.common.CommonAlgorithm.*;

@Service
@Transactional
public class MarketDataPDFContentBuilder implements IPDFContentBuilder<String, MarketDataContent> {
    private static final Logger LOG = LoggerFactory.getLogger(MarketDataPDFContentBuilder.class.getName());
    private static final String NIFTY_50 = "Nifty 50";
    private static final String NIFTY_MID_CAP_100 = "NIFTY Midcap 100";
    private static final String NIFTY_SMALL_CAP_100 = "NIFTY Smallcap 100";

    private final MarketsDao marketsDao;

    @Autowired
    public MarketDataPDFContentBuilder(MarketsDao marketsDao) {
        this.marketsDao = marketsDao;
    }

    @Override
    public MarketDataContent buildContent(String userName) throws IOException {
        MarketIndexData broaderIndexData = getMarketIndexData(userName, NIFTY_50);
        MarketIndexData midCapIndexData = getMarketIndexData(userName, NIFTY_MID_CAP_100);
        MarketIndexData smallCapIndexData = getMarketIndexData(userName, NIFTY_SMALL_CAP_100);

        return new MarketDataContent(broaderIndexData, midCapIndexData, smallCapIndexData);
    }

    private MarketIndexData getMarketIndexData(String userName, String indexType) throws IOException {
        String currentDate = DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format();
        String indexSummaryJson = marketsDao.getIndexSummary(indexType);
        String percentChangeStr = JsonUtil.getValue(indexSummaryJson, "percentChange");
        String peStr = JsonUtil.getValue(indexSummaryJson, "pe");
        String nifty50MappingKeyword = getNifty50MappingKeyword(percentChangeStr);

        String closing = JsonUtil.getValue(indexSummaryJson, "closing");
        String open = JsonUtil.getValue(indexSummaryJson, "open");
        String high = JsonUtil.getValue(indexSummaryJson, "high");
        String low = JsonUtil.getValue(indexSummaryJson, "low");

        String marketsAnalyticsJson = marketsDao.getMarketsAnalytics(indexType, "");
        String gainer = JsonUtil.getValue(marketsAnalyticsJson, "gainer");
        String looser = JsonUtil.getValue(marketsAnalyticsJson, "looser");
        String unchanged = JsonUtil.getValue(marketsAnalyticsJson, "unchanged");

        List<TopGainers> topGainers = getTopGainers(indexType);
        List<TopLoosers> topLosers = getTopLosers(indexType);
        String consecutiveMsg = getLast3ConsecutiveNumberMessage();
        String pointChangeStr = JsonUtil.getValue(indexSummaryJson, "pointChange");
        Pair<String, String> indexClosedByUpOrDownValuePair = getIndexClosedByUpOrDownValue(pointChangeStr);
        String closedBy = indexClosedByUpOrDownValuePair.getElement1();
        String closedAt = indexClosedByUpOrDownValuePair.getElement2();
        return new AllMarketIndexData(userName, currentDate, nifty50MappingKeyword, consecutiveMsg, closedBy, closedAt, percentChangeStr, closing, open, high,
                low, gainer, looser, unchanged, topGainers, topLosers,peStr);
    }

    private List<TopLoosers> getTopLosers(String indexType) throws IOException {
        String markets = marketsDao.getMarkets(indexType, "", "1", "5", "percentChange", "asc");
        MarketData marketDataDetailsForLooser = (MarketData) JsonUtil.convertJsonToPojo(markets, MarketData.class);
        List<TopLoosers> topLosers = new ArrayList<>();
        for (MarketDataDetails mdd : marketDataDetailsForLooser.getMarketData()) {
            String companyName = mdd.getCompanyName();
            String percentChange = mdd.getPercentChange();
            topLosers.add(new TopLoosers(companyName, percentChange));
        }
        return topLosers;
    }

    private List<TopGainers> getTopGainers(String indexType) throws IOException {
        String markets = marketsDao.getMarkets(indexType, "", "1", "5", "percentChange", "desc");
        MarketData marketDataDetailsDetailsForGainer = (MarketData) JsonUtil.convertJsonToPojo(markets, MarketData.class);
        List<TopGainers> topGainers = new ArrayList<>();
        for (MarketDataDetails mdd : marketDataDetailsDetailsForGainer.getMarketData()) {
            String companyName = mdd.getCompanyName();
            String percentChange = mdd.getPercentChange();
            topGainers.add(new TopGainers(companyName, percentChange));
        }
        return topGainers;
    }

    private String getLast3ConsecutiveNumberMessage() {
        LOG.info("## getLast3ConsecutiveNumberMessage - START");
        List<Float> last3DaysNifty50Prices = marketsDao.findLast3DaysNifty50Prices();
        return findNifty503DaysClosePriceMessage(last3DaysNifty50Prices);
    }

//    private String getNifty50Value(String indexSummary) throws IOException {
//        String nifty50IndexStr = JsonUtil.getValue(indexSummary, "percentChange");
//        String nifty50IndexValue = "NA";
//        if (StringUtils.isNotEmpty(nifty50IndexStr)) {
//            float percentChangeFloat = Float.parseFloat(nifty50IndexStr);
//            if (percentChangeFloat > 0.25F && percentChangeFloat < 1.5F) {
//                nifty50IndexValue = "CLOSED HIGHER";
//            }
//            else if (percentChangeFloat > -1.5F && percentChangeFloat < -0.25F) {
//                nifty50IndexValue = "REMAINED SUBDUED";
//            }
//            else if (percentChangeFloat > -1.5F && percentChangeFloat < 0.25) {
//                nifty50IndexValue = "CLOSED FLAT";
//            }
//            else if (percentChangeFloat > 1.5) {
//                nifty50IndexValue = "ENDED SHARPLY HIGHER";
//            }
//            else if (percentChangeFloat < 1.5) {
//                nifty50IndexValue = "PLUNGED SHARPLY";
//            }
//        }
//        return nifty50IndexValue;
//    }

//    private Pair<String, String> getIndexClosedByUpOrDownValue(String indexSummary) throws IOException {
//        String pointChangeStr = JsonUtil.getValue(indexSummary, "pointChange");
//        String isUpOrDown;
//        if (StringUtils.isNotEmpty(pointChangeStr)) {
//            float pointChange = Float.parseFloat(pointChangeStr);
//            isUpOrDown = pointChange > 0.0F ? "Up" : "Down";
//        }
//        else {
//            isUpOrDown = "NA";
//        }
//        return new Pair<>(isUpOrDown, pointChangeStr);
//    }

}
