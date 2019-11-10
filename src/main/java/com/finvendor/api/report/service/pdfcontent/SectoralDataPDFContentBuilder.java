package com.finvendor.api.report.service.pdfcontent;

import com.finvendor.api.report.dto.marketdatacontent.*;
import com.finvendor.api.report.dto.sectoral.Sectoral;
import com.finvendor.api.report.dto.sectoral.SectoralDataPDFContent;
import com.finvendor.api.markets.dao.MarketsDao;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
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

import static com.finvendor.api.common.CommonAlgorithm.getNifty50MappingKeyword;

@Service
@Transactional
public class SectoralDataPDFContentBuilder implements IPDFContentBuilder<String, SectoralDataPDFContent> {

    private static final String NIFTY_BANK = "Nifty Bank";
    private static final String NIFTY_IT = "Nifty IT";
    private static final String NIFTY_METAL = "Nifty Metal";
    private static final String NIFTY_AUTO = "Nifty Auto";
    private static final String NIFTY_ENERGY = "Nifty Energy";
    private static final String NIFTY_FMCG = "Nifty FMCG";
    private static final String NIFTY_PHARMA = "Nifty Pharma";
    private static final String NIFTY_REALITY = "Nifty Realty";

    @Autowired
    private MarketsDao dao;

    @Override
    public SectoralDataPDFContent buildContent(String userName) throws IOException {
        MarketIndexData bankIndexData = getMarketIndexData(userName, NIFTY_BANK);
        MarketIndexData itIndexData = getMarketIndexData(userName, NIFTY_IT);
        MarketIndexData metalIndexData = getMarketIndexData(userName, NIFTY_METAL);
        MarketIndexData autoIndexData = getMarketIndexData(userName, NIFTY_AUTO);
        MarketIndexData energyIndexData = getMarketIndexData(userName, NIFTY_ENERGY);
        MarketIndexData fmcgIndexData = getMarketIndexData(userName, NIFTY_FMCG);
        MarketIndexData pharmaIndexData = getMarketIndexData(userName, NIFTY_PHARMA);
        MarketIndexData realtyIndexData = getMarketIndexData(userName, NIFTY_REALITY);

        return new SectoralDataPDFContent(new Sectoral(bankIndexData,itIndexData,metalIndexData,autoIndexData,energyIndexData,
                fmcgIndexData,pharmaIndexData,realtyIndexData));
    }

    private MarketIndexData getMarketIndexData(String userName, String indexType) throws IOException {
        String currentDate = DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format();
        String indexSummaryJson = dao.getIndexSummary(indexType);
        String nifty50MappingKeyword = getNifty50MappingKeyword(indexSummaryJson);
        String percentChangeStr = JsonUtil.getValue(indexSummaryJson, "percentChange");
        String peStr = JsonUtil.getValue(indexSummaryJson, "pe");
        Pair<String, String> indexClosedByUpOrDownValuePair = getIndexClosedByUpOrDownValue(indexSummaryJson);
        String closing = JsonUtil.getValue(indexSummaryJson, "closing");
        String open = JsonUtil.getValue(indexSummaryJson, "open");
        String high = JsonUtil.getValue(indexSummaryJson, "high");
        String low = JsonUtil.getValue(indexSummaryJson, "low");

        String marketsAnalytics = dao.getMarketsAnalytics(indexType, "");
        String gainer = JsonUtil.getValue(marketsAnalytics, "gainer");
        String looser = JsonUtil.getValue(marketsAnalytics, "looser");
        String unchanged = JsonUtil.getValue(marketsAnalytics, "unchanged");

        String markets = dao.getMarkets(indexType, "", "1", "5", "percentChange", "desc");
        MarketData marketDataDetailsDetailsForGainer = (MarketData) JsonUtil.convertJsonToPojo(markets, MarketData.class);
        List<TopGainers> topGainers = new ArrayList<>();
        for (MarketDataDetails mdd : marketDataDetailsDetailsForGainer.getMarketData()) {
            String companyName = mdd.getCompanyName();
            String percentChange = mdd.getPercentChange();
            topGainers.add(new TopGainers(companyName, percentChange));
        }

        markets = dao.getMarkets(indexType, "", "1", "5", "percentChange", "asc");
        MarketData marketDataDetailsForLooser = (MarketData) JsonUtil.convertJsonToPojo(markets, MarketData.class);
        List<TopLoosers> topLoosers = new ArrayList<>();
        for (MarketDataDetails mdd : marketDataDetailsForLooser.getMarketData()) {
            String companyName = mdd.getCompanyName();
            String percentChange = mdd.getPercentChange();
            topLoosers.add(new TopLoosers(companyName, percentChange));
        }
        String consecutiveNumber="";
        String closedBy = indexClosedByUpOrDownValuePair.getElement1();
        String closedAt = indexClosedByUpOrDownValuePair.getElement2();
        return new AllMarketIndexData(userName, currentDate, nifty50MappingKeyword, consecutiveNumber,
                closedBy, closedAt,percentChangeStr, closing, open, high, low, gainer, looser, unchanged, topGainers, topLoosers,peStr);
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
