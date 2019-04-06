package com.finvendor.api.resources.companyprofile.companyprofile.dao;

import com.finvendor.api.resources.companyprofile.companyprofile.dto.*;
import com.finvendor.api.resources.markets.dao.MarketsDao;
import com.finvendor.api.resources.screener.stock.recommendation.dao.EquityReportDao;
import com.finvendor.api.resources.screener.stock.recommendation.dto.filter.ResearchReportFilter;
import com.finvendor.api.resources.screener.stock.recommendation.dto.filter.impl.EquityResearchFilter;
import com.finvendor.api.resources.screener.stock.recommendation.dto.result.AbsResearchReportResult;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.EarningPreview;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * @author ayush on May 01, 2018
 */
@SuppressWarnings("MagicConstant") @Repository
public class CompanyProfileDao extends GenericDao<EarningPreview> {
    private static final Logger logger = LoggerFactory.getLogger(CompanyProfileDao.class.getName());

    @Autowired
    private MarketsDao marketsDao;

    /**
     * Company Profile Query
     */
    public static final String companyProfileDataQuery = "SELECT distinct rsch_sub_area_company_dtls.company_id companyId,rsch_sub_area_company_dtls.company_name companyName, market_cap_def.market_cap_name mcap, research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.last_trade_price ltp,stock_current_info.pe,stock_current_info.pb,stock_current_info.dividend_yield,stock_current_info.eps_ttm,stock_current_info.52w_high,stock_current_info.52w_low,stock_current_info.beta,stock_current_info.as_of_date,stock_current_info.shares_outstanding,stock_current_info.mkt_cap,stock_current_info.revenue,stock_current_info.face_value,stock_current_info.bv_share,stock_current_info.roe,stock_current_info.pat,stock_current_info.recent_qtr,stock_current_prices.price_date, stock_current_prices.price_src_code,rsch_sub_area_company_dtls.company_desc,stock_current_info.3_yr_eps_growth, rsch_sub_area_company_dtls.currency  FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices,stock_current_info,country,ven_rsrch_rpt_dtls WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = %1$d AND country.country_id = %2$d AND rsch_sub_area_company_dtls.isin_code = '%3$s'";

    /**
     * Company Profile Other Query
     */
    private static final String avgCountQuery = "select CAST(avg(vendor_report_data.target_price) as CHAR(10)),  CAST(avg(vendor_report_data.target_price) as char(10)) FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, vendor_report_data WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=?";

    /**
     * Company Research Report Query
     */
    // Complex Query - Do not refactor it
    public static final String mainQuery = "SELECT rsch_sub_area_company_dtls.company_id comapanyId, rsch_sub_area_company_dtls.company_name companyName, rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style, market_cap_def.market_cap_name mcap, research_sub_area.description sector, stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt, stock_current_info.pe pe, stock_current_info.3_yr_pat_growth patGrth, stock_current_info.3_yr_eps_growth epsGrth, stock_current_info.eps_ttm epsttm, vendor_report_data.research_report_for_id companyId, vendor_report_data.product_id prdId, vendor_report_data.vendor_company broker, vendor_report_data.rsrch_recomm_type recommType, vendor_report_data.target_price tgtPrice, vendor_report_data.price_at_recomm prcAtRecomm, ((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100 upside, vendor_report_data.report_name rptName, vendor_report_data.report_date rsrchDt, vendor_report_data.analyst_awards award, vendor_report_data.anayst_cfa_charter cfa, vendor_report_data.analyst_name analystName, vendor_report_data.vendor_analyst_type analystType, vendor_report_data.vendor_id vendorId, vendor_report_data.launched_year ly, vendor_report_data.vendor_name userName, vendor_report_data.rsrch_report_desc rptDesc, vendor_report_data.product_name productNameAsReportName FROM rsch_sub_area_company_dtls,      rsch_area_stock_class,      market_cap_def,      comp_mkt_cap_type,      research_sub_area,      stock_current_prices,      stock_current_info,      vendor_report_data WHERE   rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = COUNTRYID   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=?";
    public static final String mainQueryForRecommType = "SELECT vendor_report_data.product_id prdId, vendor_report_data.rsrch_recomm_type recommType FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type,      research_sub_area,      stock_current_prices,      stock_current_info,      vendor_report_data WHERE   rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = COUNTRYID   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=?";

    /**
     * Earning Preview Query
     */
    private static final String EARNING_PREVIEW_QUARTERLY = "select b.period,b.revenue,b.operating_profit_margin,b.profit_after_tax,b.eps from earning_preview a, earning_preview_quarterly b where a.stock_id=b.stock_id and a.isin=?";
    private static final String EARNING_PREVIEW_YEARLY = "select b.period,b.revenue,b.operating_profit_margin,b.profit_after_tax,b.eps,b.net_operating_cash_flow,b.roe from earning_preview a, earning_preview_yearly b where a.stock_id=b.stock_id and a.isin=?";

    /**
     * Company News Query
     */
    private static final String NEWS_QUERY = "select b.subject,b.broadcast_date from company_news a,company_news_history b where a.ticker=b.ticker and a.ticker=? order by STR_TO_DATE(b.broadcast_date,  \"%d-%b-%Y\") desc";

    /**
     * Corporate Action Query
     */
    private static final String CA_QUERY = "select b.purpose,b.face_value,b.ex_date,b.record_date from corp_action a,corp_action_history b where a.ticker=b.ticker and a.ticker=? order by STR_TO_DATE(b.record_date,  \"%d-%b-%Y\") desc";

    /**
     * Company Calendar Query
     */
    private static final String CAL_QUERY = "select b.purpose,b.board_meeting_date from company_calendar a,company_calendar_history b where a.ticker=b.ticker and a.ticker=? order by STR_TO_DATE(b.board_meeting_date,  \"%d-%b-%Y\") desc";

    /**
     * Price History Query
     */
    private static final String PRICE_HISTORY_QUERY = "select a.* from stock_historical_prices a where a.stock_id=(select a.company_id from rsch_sub_area_company_dtls a where a.isin_code=?) order by STR_TO_DATE(a.price_date,  \"%d/%b/%Y\") desc";

    private static final String FIND_TICKER_QUERY = "select a.company_id,a.ticker from rsch_sub_area_company_dtls a where a.isin_code=?";

    @Autowired
    private ICommonDao commonDao;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    private EquityReportDao equityReportDao;

    /**
     * upside = ((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100
     */
    @SuppressWarnings("unchecked")
    public String findCompanyProfile(String query, String isinCode) throws RuntimeException {
        SQLQuery query1 = commonDao.getNativeQuery(query, null);
        List<Object[]> rows = query1.list();
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        String companyProfile = "NA";
        String summary;
        String valuationScoreStr;
        try {
            for (Object[] row : rows) {
                String companyId = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                String mcap = row[2] != null ? row[2].toString().trim() : "";
                String industry = row[3] != null ? row[3].toString().trim() : "";
                String cmp = row[4] != null ? row[4].toString().trim() : "";

                // ltp:LastTradedPrice
                String ltp = row[5] != null ? row[5].toString().trim() : "";
                float cmpAsFloat = Float.parseFloat(cmp);
                float ltpAsFloat = Float.parseFloat(ltp);

                String absoluteLastChangedCmp = String.valueOf(cmpAsFloat - ltpAsFloat);
                String lastChangedCmpInPercentage = String.valueOf((cmpAsFloat - ltpAsFloat) * 100 / ltpAsFloat);

                // cmp/epsttm if eps is 0 then N/A
                String pe = row[6] != null ? row[6].toString().trim() : "";

                // cmp/bv_share if bv_share is 0 then na
                String pb = row[7] != null ? row[7].toString().trim() : "";

                String dividen_yield = row[8] != null ? row[8].toString().trim() : "";

                // static and it will updated quaterly
                String eps_ttm = row[9] != null ? row[9].toString().trim() : "";
                float eps_ttm_as_float = Float.parseFloat(eps_ttm);

                // temp and we will get from vendor feed
                String _52w_high = row[10] != null ? row[10].toString().trim() : "";
                // temp and we will get from vendor feed
                String _52w_low = row[11] != null ? row[11].toString().trim() : "";

                // static and it will updated quaterly
                String beta = row[12] != null ? row[12].toString().trim() : "";
                // String as_of_date = row[13] != null ? row[13].toString().trim() : "";

                // static and it will updated quaterly
                String share_outstanding = row[14] != null ? row[14].toString().trim() : "";// static
                float shareOutStandingAsFloat = Float.parseFloat(share_outstanding);

                // market cap needs to be calculated daily using below formula = shares_outstanding * today's market price
                String mkt_cap = String.valueOf(shareOutStandingAsFloat * cmpAsFloat);

                // static and it will updated quaterly
                String revenue = row[16] != null ? row[16].toString().trim() : "";
                revenue = StringUtils.remove(revenue, ",");

                // static and it will updated quaterly
                String face_value = row[17] != null ? row[17].toString().trim() : "";

                // static and it will updated quaterly
                //String bv_share = row[18] != null ? row[18].toString().trim() : "";
                float bv_share_as_float = cmpAsFloat / Float.parseFloat(pb);

                // static and it will updated quaterly
                String roe = row[19] != null ? row[19].toString().trim() : "";

                // static and it will updated quaterly
                String pat = row[20] != null ? row[20].toString().trim() : "";
                pat = StringUtils.remove(pat, ",");
                String recent_qtr = row[21] != null ? row[21].toString().trim() : "";

                // date is comming in this format "dd/MMM/yy HH:mm:ss"
                String price_date = row[22] != null ? row[22].toString().trim() : "";
                String price_date_in_millis = String.valueOf(DateUtils.convertFvPriceDateToTimestamp(price_date));
                String price_src_code = row[23] != null ? row[23].toString().trim() : "";
                summary = row[24] != null ? row[24].toString().trim() : "";

                String _3yrEpsGrowth = row[25] != null ? row[25].toString().trim() : "";
                float _3yrEpsGrowthAsFloat = Float.parseFloat(_3yrEpsGrowth);
                String currency = row[26] != null ? row[26].toString().trim() : "";

                // PE calculation PE=cmp/eps-ttm
                String newPeStr = "";
                float newPe = 0.0f;
                if (eps_ttm_as_float == 0.0f) {
                    newPeStr = "N/A";
                }
                else {
                    newPe = cmpAsFloat / eps_ttm_as_float;
                    newPeStr = String.valueOf(newPe);
                }

                // PB Calculation PB=cmp/bv_share
                String newPBStr = "";
                float newPB = 0.0f;
                if (bv_share_as_float == 0.0f) {
                    newPBStr = "N/A";
                }
                else {
                    newPB = cmpAsFloat / bv_share_as_float;
                    newPBStr = String.valueOf(newPB);
                }

                //Calculate Valuation Score
                valuationScoreStr = calculateAndGetValucationScore(_3yrEpsGrowthAsFloat, newPe);

                //find ticker
                String ticker = findTicker(isinCode);
                CompanyProfileDataDto companyProfileDataDto = new CompanyProfileDataDto(companyId, companyName, industry, mcap, cmp,
                        absoluteLastChangedCmp,
                        lastChangedCmpInPercentage, newPeStr, "-", dividen_yield, eps_ttm, _52w_high,
                        _52w_low, beta, share_outstanding, mkt_cap, revenue.trim(), face_value, "-", roe, pat,
                        recent_qtr, price_date_in_millis, price_src_code, valuationScoreStr, currency,ticker);

                //Stock Historical price calculation
                Map<String, String> stockHistoricalPrices = findStockHistoricalPrices(cmpAsFloat, isinCode);

                //Nifty50 Historical price calculation
                String indexSummary = marketsDao.getIndexSummary("Nifty 50");
                float closing = Float.parseFloat(JsonUtil.getValue(indexSummary, "closing").trim());
                Map<String, String> nifty50HistoricalPrices = findNifty50HistoricalPrices(closing);

                //Nifty50 Handling
                PriceHistoryDto priceHistoryDto = new PriceHistoryDto();
                priceHistoryDto.setNifty50(nifty50HistoricalPrices);
                priceHistoryDto.setStock(stockHistoricalPrices);

                //Company Profile
                paramsMap.put("companyProfileData", companyProfileDataDto);

                //Summary
                paramsMap.put("summary", summary);

                //Price History
                paramsMap.put("priceHistory", priceHistoryDto);

                //Broker Rank
                List<Integer> brokerRankList = getBrokerRank(isinCode);
                float averageTargetPrice = Float.parseFloat(getResearchReportAggregatedData(avgCountQuery, isinCode).trim());
                float upside = averageTargetPrice == 0.0f ? 0.0f : ((averageTargetPrice - cmpAsFloat) / cmpAsFloat) * 100;
                int totalBuyRecomm = brokerRankList.get(0);
                int totalSellRecomm = brokerRankList.get(1);
                int totalNeutralRecomm = brokerRankList.get(2);
                paramsMap.put("brokerRank",
                        new BrokerRankDto(totalBuyRecomm, totalSellRecomm, totalNeutralRecomm, averageTargetPrice, upside));

                companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occured while creating json for company profile data", e);
        }
        return companyProfile;
    }

    private List<Integer> getBrokerRank(String isinCode) {
        int countryId = CommonCodeUtils.getCountryId(isinCode);
        String mainQuery1 = StringUtils.replace(CompanyProfileDao.mainQueryForRecommType, "COUNTRYID", "" + countryId);
        String mainQuery = StringUtils.replace(mainQuery1, "?", "'" + isinCode + "'");
        return equityReportDao.findBrokerRank(mainQuery, new EquityResearchFilter());
    }

    private String calculateAndGetValucationScore(float _3yrEpsGrowthAsFloat, float newPe) {
        /*
         * Valuation score
         *           Logic should be implemented in below order (if logic-1 meets the condition , no need to go to
         *            logic-2 or 3; if logic-2 meets the condition , no need to go to logic-3)
         *
         *           Logic-1 : if [3 YR EPS growth] <= 0 then Strong Sell (Deep RED)
         *
         *           Logic-2 : if [P/E] <= 0 then Strong Sell (Deep RED)
         *
         *           Logic-3 :
         *           [(3 YR EPS growth - P/E ) / 3 YR EPS growth] * 100 = V
         *           IF V > 15%  	        => Strong Buy (Deep Green)
         *           IF 15% > V > 5%  	=> Buy (Light Green)
         *           if -5% > V > 5% 	        => Neutral (Orange)
         *           if -15% < V < -5% 	=> Sell (Light RED)
         *           if V < -15% 	        => STRONG Sell (Deep RED)
         */
        String valuationScoreStr = "NA";

        if (_3yrEpsGrowthAsFloat <= 0.0F || newPe <= 0.0F) {
            valuationScoreStr = "Very Overpriced";
        }
        else {
            float valuationScore = (_3yrEpsGrowthAsFloat - newPe) / _3yrEpsGrowthAsFloat * 100.0F;
            logger.info("valuationScore:{}", valuationScore);
            if (valuationScore >= -15.0f && valuationScore < -5.0f) {
                valuationScoreStr = "Overpriced";//"Sell";
            }
            if (valuationScore >= -5.0f || valuationScore < 5.0f) {
                valuationScoreStr = "Reasonable";//"Neutral";
            }
            if (valuationScore >= 5.0f && valuationScore < 15.0f) {
                valuationScoreStr = "Pleasing";//"Buy";
            }
            if (valuationScore >= 15.0f) {
                valuationScoreStr = "Very Pleasing";//"Strong Buy";
            }
            if (valuationScore < -15.0) {
                valuationScoreStr = "Very Overpriced";//"Strong Sell";
            }
        }
        return valuationScoreStr;
    }

    private Map<String, String> findStockHistoricalPrices(float todaysCmp, String isinCode) throws Exception {
        float stock_WeekPrice;
        float stock_1M_Price;
        float stock_3M_Price;
        float stock_6M_Price;
        float stock_1Y_Price;
        String stockId = getCompanyId("select a.company_id,a.isin_code from rsch_sub_area_company_dtls a where a.isin_code=?", isinCode);
        String stockTodaysDateFromDB = getStockTodaysDateFromDB(stockId);
        if (stockTodaysDateFromDB.isEmpty()) {
            throw new Exception("Unble to find today's stock date");
        }
        Map<String, String> stockHistoricalPriceMap = new LinkedHashMap<>();
        //Week Price
        String query =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        String nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        stock_WeekPrice = getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);

        //1M Price
        query = "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH),\"%d/%b/%y\")) and stock_id="
                + stockId;
        nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        stock_1M_Price = getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);

        //3M Price
        query = "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH),\"%d/%b/%y\")) and stock_id="
                + stockId;
        nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        stock_3M_Price = getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);

        //6M Price
        query = "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH),\"%d/%b/%y\")) and stock_id="
                + stockId;
        nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        stock_6M_Price = getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);

        //1Y Price
        query = "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR),\"%d/%b/%y\")) and stock_id="
                + stockId;
        nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        stock_1Y_Price = getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);

        stockHistoricalPriceMap
                .put("1W", stock_WeekPrice == 0.0F ? "-" : String.valueOf((todaysCmp - stock_WeekPrice) * 100 / stock_WeekPrice));
        stockHistoricalPriceMap
                .put("1M", stock_1M_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_1M_Price) * 100 / stock_1M_Price));
        stockHistoricalPriceMap
                .put("3M", stock_3M_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_3M_Price) * 100 / stock_3M_Price));
        stockHistoricalPriceMap
                .put("6M", stock_6M_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_6M_Price) * 100 / stock_6M_Price));
        stockHistoricalPriceMap
                .put("1Y", stock_1Y_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_1Y_Price) * 100 / stock_1Y_Price));
        stockHistoricalPriceMap.put("2Y", "-");
        stockHistoricalPriceMap.put("5Y", "-");
        return stockHistoricalPriceMap;
    }

    private Map<String, String> findNifty50HistoricalPrices(float nifty50Closing) throws Exception {
        float nifty50_WeekPrice;
        float nifty50_1M_Price;
        float nifty50_3M_Price;
        float nifty50_6M_Price;
        float nifty50_1Y_Price;
        String nifty50TodaysDateFromDB = getNifty50TodaysDateFromDB();
        if (nifty50TodaysDateFromDB.isEmpty()) {
            throw new Exception("Unble to find today's date from nifty50History table, cause: Batch process would have failed!!");
        }

        Map<String, String> nifty50HistoricalPriceMap = new LinkedHashMap<>();
        //Week Price
        String query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY),\"%e-%b-%y\"))";
        String nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY), INTERVAL COUNT DAY),\"%d-%b-%y\"))";
        nifty50_WeekPrice = getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);

        //1M Price
        query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH),\"%e-%b-%y\"))";
        nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        nifty50_1M_Price = getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);

        //3M Price
        query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH),\"%e-%b-%y\"))";
        nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        nifty50_3M_Price = getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);

        //6M Price
        query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH),\"%e-%b-%y\"))";
        nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        nifty50_6M_Price = getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);

        //1Y Price
        query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR),\"%e-%b-%y\"))";
        nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        nifty50_1Y_Price = getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);

        nifty50HistoricalPriceMap.put("1W",
                nifty50_WeekPrice == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_WeekPrice) * 100 / nifty50_WeekPrice));
        nifty50HistoricalPriceMap
                .put("1M", nifty50_1M_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_1M_Price) * 100 / nifty50_1M_Price));
        nifty50HistoricalPriceMap
                .put("3M", nifty50_3M_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_3M_Price) * 100 / nifty50_3M_Price));
        nifty50HistoricalPriceMap
                .put("6M", nifty50_6M_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_6M_Price) * 100 / nifty50_6M_Price));
        nifty50HistoricalPriceMap
                .put("1Y", nifty50_1Y_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_1Y_Price) * 100 / nifty50_1Y_Price));
        nifty50HistoricalPriceMap.put("2Y", "-");
        nifty50HistoricalPriceMap.put("5Y", "-");
        return nifty50HistoricalPriceMap;
    }

    private float getHistoricalPrice(String nifty50TodayPrice, String query, String nextQuery) throws Exception {
        query = StringUtils.replace(query, "CURR_DATE", nifty50TodayPrice);
        nextQuery = StringUtils.replace(nextQuery, "CURR_DATE", nifty50TodayPrice);
        logger.info("***query: {}", query);

        float historicalPrice;
        SQLQuery query1 = commonDao.getNativeQuery(query, null);
        List<Object[]> rows = query1.list();
        String date = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        String price = !rows.isEmpty() ? (rows.get(0)[1] != null ? rows.get(0)[1].toString().trim() : "") : "";
        String count = "1";
        if (date.isEmpty()) {
            String nextPrice;
            while (true) {
                String nextQuery1 = StringUtils.replace(nextQuery, "COUNT", count);
                logger.info("***count: {} nextQuery: {}", count, nextQuery1);
                query1 = commonDao.getNativeQuery(nextQuery1, null);
                rows = query1.list();
                String nextDate = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
                nextPrice = !rows.isEmpty() ? (rows.get(0)[1] != null ? rows.get(0)[1].toString().trim() : "") : "";
                int countAsInt = Integer.parseInt(count);
                if (nextDate.isEmpty()) {
                    countAsInt++;
                }
                else {
                    break;
                }
                if (countAsInt == 6) {
                    break;
                }
                count = String.valueOf(countAsInt);
            }
            if (nextPrice.isEmpty()) {
                nextPrice = "0.0";
            }
            historicalPrice = Float.parseFloat(nextPrice);
        }
        else {
            historicalPrice = Float.parseFloat(price);
        }
        return historicalPrice;
    }

    private String getStockTodaysDateFromDB(String stockId) {
        SQLQuery query1;
        List<Object[]> rows;
        String currentDateQuery = "SELECT DATE_FORMAT(STR_TO_DATE(b.price_date,  \"%d/%b/%y\" ),\"%Y-%c-%d\") date,close_price FROM  stock_current_prices b WHERE  b.stock_id=? ORDER BY STR_TO_DATE(b.price_date,  \"%d/%b/%y\" ) DESC limit 1 offset 0";
        query1 = commonDao.getNativeQuery(currentDateQuery, new Object[] { stockId });
        rows = query1.list();
        String stockTodaysDate = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        return stockTodaysDate;
    }

    private String getNifty50TodaysDateFromDB() {
        SQLQuery query1;
        List<Object[]> rows;
        String currentDateQuery = "SELECT DATE_FORMAT(STR_TO_DATE(date,  \"%d-%b-%y\" ),\"%Y-%c-%d\"), close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  \"%d-%b-%y\" ) DESC limit 1 offset 0;";
        query1 = commonDao.getNativeQuery(currentDateQuery, null);
        rows = query1.list();
        String nifty50TodaysDate = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        return nifty50TodaysDate;
    }

    private String getCompanyId(String query, String isinCode) {
        SQLQuery query1;
        List<Object[]> rows;
        query1 = commonDao.getNativeQuery(query, isinCode != null ? new String[] { isinCode } : null);
        rows = query1.list();
        String companyId = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        return companyId;
    }

    public String getCompanyProfileReasearchReport(String mainQuery, String isinCode, ResearchReportFilter filter,
            String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        String companyProfile;
        try {
            Map<String, ? extends AbsResearchReportResult> equityData = equityReportDao
                    .findResearchReportTableData(mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);

            Collection<? extends AbsResearchReportResult> equityList = equityData.values();

            paramsMap.put("noOfAnalystReport", equityList.size());
            List<Integer> brokerRankList = getBrokerRank(isinCode);
            // Total Buy Recomm
            String value = String.valueOf(brokerRankList.get(0));
            paramsMap.put("totalBuyRecomm", value);

            // Total Sell Recomm
            value = String.valueOf(brokerRankList.get(1));
            paramsMap.put("totalSellRecomm", value);

            // Total Neutral Recomm
            value = String.valueOf(brokerRankList.get(2));
            paramsMap.put("totalNeutralRecomm", value);

            // Average Target Price
            value = getResearchReportAggregatedData(avgCountQuery, isinCode);
            paramsMap.put("averageTargetPrice", value);

            paramsMap.put("equity", equityList);
            companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException("Error has occured getReasearchReportData(), DAO Error::", e);
        }
        return companyProfile;
    }

    /**
     * Find Earning Preview
     */
    public EarningPreviewDto findEarningPreview(String type, String isin) {
        SQLQuery sqlQuery;
        EarningPreviewDto earningPreviewDto;
        try {
            if ("quarterly".equalsIgnoreCase(type)) {
                sqlQuery = commonDao.getNativeQuery(EARNING_PREVIEW_QUARTERLY, new String[] { isin });
                List<Object[]> rows = sqlQuery.list();
                earningPreviewDto = constructEarningPreviewResultQuartlery(rows);
            }
            else {
                sqlQuery = commonDao.getNativeQuery(EARNING_PREVIEW_YEARLY, new String[] { isin });
                List<Object[]> rows = sqlQuery.list();
                earningPreviewDto = constructEarningPreviewResultYearly(rows);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return earningPreviewDto;
    }

    /**
     * Quarterly Earning Preview
     */
    private EarningPreviewDto constructEarningPreviewResultQuartlery(List<Object[]> rows) {
        EarningPreviewDto earningPreviewDto = new EarningPreviewDto();
        List<EarningPreviewQuarterlyDto> earningPreviewResultList = new ArrayList<>();
        for (Object[] row : rows) {
            String period = row[0] != null ? row[0].toString().trim() : "";
            String periodAsTimeStamp = DateUtils.getTimeStamp_MMM_yy(period.trim());
            String revenue = row[1] != null ? row[1].toString().trim() : "";
            String operatingProfitMargin = row[2] != null ? row[2].toString().trim() : "";
            String profitAfterTax = row[3] != null ? row[3].toString().trim() : "";
            String eps = row[4] != null ? row[4].toString().trim() : "";
            earningPreviewResultList
                    .add(new EarningPreviewQuarterlyDto(periodAsTimeStamp, revenue, operatingProfitMargin, profitAfterTax, eps));
        }
        earningPreviewDto.setEarningPreviewResult(earningPreviewResultList);
        return earningPreviewDto;
    }

    /**
     * Yearly Earning Preview
     */
    private EarningPreviewDto constructEarningPreviewResultYearly(List<Object[]> rows) {
        EarningPreviewDto earningPreviewDto = new EarningPreviewDto();
        List<EarningPreviewYearlyDto> earningPreviewResultList = new ArrayList<>();
        for (Object[] row : rows) {
            String period = row[0] != null ? row[0].toString().trim() : "";
            String periodAsTimeStamp = DateUtils.getTimeStamp_MMM_yy(period.trim());
            String revenue = row[1] != null ? row[1].toString().trim() : "";
            String operatingProfitMargin = row[2] != null ? row[2].toString().trim() : "";
            String profitAfterTax = row[3] != null ? row[3].toString().trim() : "";
            String eps = row[4] != null ? row[4].toString().trim() : "";
            String netOperatingCashFlow = row[5] != null ? row[5].toString().trim() : "";
            String roe = row[6] != null ? row[6].toString().trim() : "";
            earningPreviewResultList.add(new EarningPreviewYearlyDto(periodAsTimeStamp, revenue, operatingProfitMargin, profitAfterTax, eps,
                    netOperatingCashFlow, roe));
        }
        earningPreviewDto.setEarningPreviewResult(earningPreviewResultList);
        return earningPreviewDto;
    }

    /**
     * Find Company News RECORD STATS
     */
    public String findCompanyNewsRecordStats(String ticker, String perPageMaxRecords)
            throws RuntimeException {
        try {
            return buildRecordStats(ticker, perPageMaxRecords, NEWS_QUERY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find Company News
     */
    public String findCompanyNews(String ticker, String pageNumber, String perPageMaxRecords) {
        String result;
        List<CompanyNewsDto> newsList = new ArrayList<>();
        try {
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
            String newQuery = NEWS_QUERY + applyPagination;
            SQLQuery sqlQuery = commonDao.getNativeQuery(newQuery, new String[] { ticker });
            List<Object[]> rows = sqlQuery.list();

            for (Object[] row : rows) {
                String subject = row[0] != null ? row[0].toString().trim() : "";
                String broadcastDate = row[1] != null ? row[1].toString().trim() : "";
                broadcastDate = DateUtils.convertStringToTimestamp(DateUtils.dd_MMM_yyyy_hh_mmformatter, broadcastDate);
                newsList.add(new CompanyNewsDto(broadcastDate, subject));
            }

            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("companyNews", newsList);
            result = JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Find Corporate Action RECORD STATS
     */
    public String findCorporateActionRecordStats(String ticker, String perPageMaxRecords)
            throws RuntimeException {
        try {
            return buildRecordStats(ticker, perPageMaxRecords, CA_QUERY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find Corporate Action
     */
    public String findCorporateAction(String ticker, String pageNumber, String perPageMaxRecords) {
        String result;
        List<CorpActionDto> newsList = new ArrayList<>();
        try {
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
            String newQuery = CA_QUERY + applyPagination;
            SQLQuery sqlQuery = commonDao.getNativeQuery(newQuery, new String[] { ticker });
            List<Object[]> rows = sqlQuery.list();

            for (Object[] row : rows) {
                String purpose = row[0] != null ? row[0].toString().trim() : "";
                String faceValue = row[1] != null ? row[1].toString().trim() : "";

                String exDate = row[2] != null ? row[2].toString().trim() : "";
                exDate = DateUtils.convertStringToTimestamp(DateUtils.dd_MMM_yyyy_formatter, exDate);

                String recordDate = row[3] != null ? row[3].toString().trim() : "";
                recordDate = "-".equals(recordDate) ? "0" : DateUtils.convertStringToTimestamp(DateUtils.dd_MMM_yyyy_formatter, recordDate);
                newsList.add(new CorpActionDto(purpose, faceValue, exDate, recordDate));
            }
            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("corpAction", newsList);
            result = JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Find Calendar RECORD STATS
     */
    public String findCalendarRecordStats(String ticker, String perPageMaxRecords)
            throws RuntimeException {
        try {
            return buildRecordStats(ticker, perPageMaxRecords, CAL_QUERY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find Calendar
     */
    public String findCalendar(String ticker, String pageNumber, String perPageMaxRecords) {
        String result;
        List<CompanyCalendarDto> newsList = new ArrayList<>();
        try {
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
            String newQuery = CAL_QUERY + applyPagination;
            SQLQuery sqlQuery = commonDao.getNativeQuery(newQuery, new String[] { ticker });
            List<Object[]> rows = sqlQuery.list();

            for (Object[] row : rows) {
                String purpose = row[0] != null ? row[0].toString().trim() : "";
                String boardMeetingDate = row[1] != null ? row[1].toString().trim() : "";
                boardMeetingDate = DateUtils.convertStringToTimestamp(DateUtils.dd_MMM_yyyy_formatter, boardMeetingDate);
                newsList.add(new CompanyCalendarDto(purpose, boardMeetingDate));
            }

            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("calendar", newsList);
            result = JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Find Price History RECORD STATS
     */
    public String findPriceHistoryRecordStats(String isin, String perPageMaxRecords)
            throws RuntimeException {
        try {
            return buildRecordStats(isin, perPageMaxRecords, PRICE_HISTORY_QUERY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find Price History
     */
    public String findPriceHistory(String isin, String pageNumber, String perPageMaxRecords) {
        String result;
        List<CompanyPriceHistoryDto> priceHistoryList = new ArrayList<>();
        try {
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
            String newQuery = PRICE_HISTORY_QUERY + applyPagination;
            SQLQuery sqlQuery = commonDao.getNativeQuery(newQuery, new String[] { isin });
            List<Object[]> rows = sqlQuery.list();

            for (Object[] row : rows) {
                String stockId = row[0] != null ? row[0].toString().trim() : "";
                String priceSourceCode = row[1] != null ? row[1].toString().trim() : "";

                String priceDate = row[2] != null ? row[2].toString().trim() : "";
                priceDate = DateUtils.convertStringToTimestamp(DateUtils.dd_MMM_yyyy_formatter1, priceDate);

                String openPrice = row[3] != null ? row[3].toString().trim() : "";
                String highPrice = row[4] != null ? row[4].toString().trim() : "";
                String lowPrice = row[5] != null ? row[5].toString().trim() : "";
                String closePrice = row[6] != null ? row[6].toString().trim() : "";
                String lastTradePrice = row[7] != null ? row[7].toString().trim() : "";

                priceHistoryList.add(new CompanyPriceHistoryDto(stockId, priceSourceCode, priceDate,
                        openPrice, highPrice, lowPrice, closePrice, lastTradePrice));
            }

            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("priceHistory", priceHistoryList);
            result = JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private String getResearchReportAggregatedData(String query, String isinCode) {
        String value = "0.0";
        SQLQuery sqlQuery = commonDao.getNativeQuery(query, new String[] { isinCode });
        List<Object[]> rows = sqlQuery.list();
        for (Object[] row : rows) {
            value = row[0] != null ? row[0].toString().trim() : "0.0";
        }
        return value;
    }

    private String buildRecordStats(String ticker, String perPageMaxRecords, String newsQuery) throws IOException {
        SQLQuery sqlQuery = commonDao.getNativeQuery(newsQuery, new String[] { ticker });
        List<Object[]> rows = sqlQuery.list();

        int totalRecords = rows.size();

        // Calculate Last page number
        long lastPageNumber = CommonCodeUtils.calculatePaginationLastPage(perPageMaxRecords, totalRecords);

        // Prepare Json result
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("firstPageNumber", 1);
        paramsMap.put("lastPageNumber", lastPageNumber);
        paramsMap.put("totalRecords", totalRecords);

        return JsonUtil.createJsonFromObject(paramsMap);
    }

    private static String readUri(String uri) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(uri).openStream()))) {
            return reader.readLine();
        }
    }

    private String findTicker(String isinCode) {
        String ticker = "0.0";
        SQLQuery sqlQuery = commonDao.getNativeQuery(FIND_TICKER_QUERY, new String[] { isinCode });
        List<Object[]> rows = sqlQuery.list();
        for (Object[] row : rows) {
            ticker = row[1] != null ? row[1].toString().trim() : "";
        }
        return ticker;
    }

    public static void main(String[] args) {
        String html = "<!DOCTYPEhtml><htmllang=\"en\"><head><!--Pagetitle--><title>Search|WorldTradingData</title><!--Pagedescription--><metaname=\"description\"content=\"\"><metaname=\"author\"content=\"World Trading Data\"><metaname=\"keywords\"content=\"stock API,digital currency,crypto currency,free stock API,free stock API json,free stock API csv,stock quotes,json API,csv API,finance API,equity quotes,RESTful api,RESTful json API,realtime stock data,real time stock data API,historical stock data,historical stock data API,download stock data,download stock market data\"><metaname=\"google-site-verification\"content=\"tqVaf43SHcmJws6l4fn4TI2Hx1Bn7stGqcHVWEAqvy4\"/><metaname=\"msvalidate.01\"content=\"51FDDA1D91C5791674F529CB6272CB81\"/><!--Meta--><metacharset=\"UTF-8\"><metahttp-equiv=\"X-UA-Compatible\"content=\"IE=edge\"><metaname=\"viewport\"content=\"width=device-width, initial-scale=1\"><!--CSRFToken--><metaname=\"csrf-token\"content=\"iCT1iXv7I6FgXNNroBi45LLNvtvP7Q3NL8RHIcEZ\"><!--Favicon--><linkrel=\"icon\"type=\"image/png\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/favicon-16x16.png\"sizes=\"16x16\"><linkrel=\"icon\"type=\"image/png\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/favicon-32x32.png\"sizes=\"32x32\"><linkrel=\"icon\"type=\"image/png\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/favicon-96x96.png\"sizes=\"96x96\"><linkrel=\"apple-touch-icon\"sizes=\"57x57\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-57x57.png\"><linkrel=\"apple-touch-icon\"sizes=\"60x60\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-60x60.png\"><linkrel=\"apple-touch-icon\"sizes=\"72x72\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-72x72.png\"><linkrel=\"apple-touch-icon\"sizes=\"76x76\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-76x76.png\"><linkrel=\"apple-touch-icon\"sizes=\"114x114\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-114x114.png\"><linkrel=\"apple-touch-icon\"sizes=\"120x120\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-120x120.png\"><linkrel=\"apple-touch-icon\"sizes=\"144x144\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-144x144.png\"><linkrel=\"apple-touch-icon\"sizes=\"152x152\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-152x152.png\"><linkrel=\"apple-touch-icon\"sizes=\"180x180\"href=\"https://www.worldtradingdata.com/assets/main/img/favicons/apple-touch-icon-180x180.png\"><!--IncludeStyleSheets--><linkrel=\"stylesheet\"href=\"https://www.worldtradingdata.com/assets/main/css/style.min.css\"><linkrel=\"stylesheet\"href=\"https://www.worldtradingdata.com/assets/main/css/custom.css\"><scriptsrc=\"//cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js\"></script></head><bodyclass=\"\"><!--EndofPreloader--><divclass=\"page js-page \"><!--Header--><divclass=\"header header-over large\"><divclass=\"container\"><divclass=\"row\"><divclass=\"col-md-3 col-sm-6 col-xs-6\"><!--LogoImage--><ahref=\"https://www.worldtradingdata.com\"class=\"logo-image logo-animated\"><imgsrc=\"https://www.worldtradingdata.com/assets/main/img/logos/logo-light.png\"alt=\"logo\"></a><!--EndofLogoImage--><!--Languages--><divclass=\"languages languages-light js-languages\"><spanclass=\"language-active js-language-active\">USD<iclass=\"fa fa-angle-down\"></i></span><ulclass=\"languages-list js-languages-list\"><li><ahref=\"https://www.worldtradingdata.com/currency?c=usd\">USD</a></li><li><ahref=\"https://www.worldtradingdata.com/currency?c=gbp\">GBP</a></li></ul></div><!--EndofLanguages--></div><divclass=\"col-md-9 col-sm-6 col-xs-6\"><!--Menu--><navclass=\"right helper\"><ulclass=\"menu sf-menu js-menu menu-light\"><li><ahref=\"https://www.worldtradingdata.com\">Home</a></li><li><ahref=\"https://www.worldtradingdata.com/services\">Services</a></li><li><ahref=\"https://www.worldtradingdata.com/pricing\">Pricing</a></li><li><ahref=\"https://www.worldtradingdata.com/documentation\">Documentation</a></li><li><ahref=\"#\">About</a><ul><li><ahref=\"https://www.worldtradingdata.com/faq\">FAQ</a></li><li><ahref=\"https://www.worldtradingdata.com/contact\">Contact</a></li></ul></li><li><ahref=\"https://www.worldtradingdata.com/login\">Login</a></li><li><ahref=\"https://www.worldtradingdata.com/register\">Register</a></li></ul></nav><!--EndofMenu--></div></div></div></div><!--EndofHeader--><!--HeaderBack--><divclass=\"header-back header-back-simple header-back-small\"><divclass=\"header-back-container\"><divclass=\"container\"><divclass=\"row\"><divclass=\"col-md-12\"><!--PageInfo--><divclass=\"page-info page-info-simple\"><h1class=\"page-title\">Search</h1><h2class=\"page-description\">Searchforastockinourdatabase.</h2><ahref=\"https://www.worldtradingdata.com/search/mutualfunds\">Clickheresearchformutualfundsinstead.</a></div><!--EndPageInfo--></div></div></div></div></div><!--EndofHeaderBack--><divid=\"content\"><divclass=\"container\"><divclass=\"row\"><divclass=\"col-md-12\"><divclass=\"table-responsive\"><divclass=\"pull-left\"><divclass=\"wrap\"><divclass=\"search\"><formaction=\"https://www.worldtradingdata.com/search\"><inputtype=\"text\"name=\"q\"class=\"searchTerm\"placeholder=\"Example: 'AAPL' or 'Apple'\"value=\"40B.SI\"><buttontype=\"submit\"class=\"searchButton\"><iclass=\"fa fa-search\"></i></button><br><br><ahref=\"#\"onclick=\"event.preventDefault();$('#exchanges').toggle();\">Filterbystockexchange(0)</a><br><ahref=\"#\"onclick=\"event.preventDefault();$('#currency').toggle();\">Filterbycurrency(0)</a><br><br><divid=\"exchanges\"style=\"display:none;\"><h5><strong>StockExchange</strong></h5><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"AEX\"><spanclass=\"label-text\">AEX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"AMEX\"><spanclass=\"label-text\">AMEX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"ASX\"><spanclass=\"label-text\">ASX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"BATS\"><spanclass=\"label-text\">BATS</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"BCBA\"><spanclass=\"label-text\">BCBA</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"BIST\"><spanclass=\"label-text\">BIST</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"BOM\"><spanclass=\"label-text\">BOM</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"BSE\"><spanclass=\"label-text\">BSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"CNQ\"><spanclass=\"label-text\">CNQ</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"FRA\"><spanclass=\"label-text\">FRA</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"GER\"><spanclass=\"label-text\">GER</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"HKEX\"><spanclass=\"label-text\">HKEX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXASX\"><spanclass=\"label-text\">INDEXASX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXBIT\"><spanclass=\"label-text\">INDEXBIT</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXBME\"><spanclass=\"label-text\">INDEXBME</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXBOM\"><spanclass=\"label-text\">INDEXBOM</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXCBOE\"><spanclass=\"label-text\">INDEXCBOE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXCME\"><spanclass=\"label-text\">INDEXCME</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXDB\"><spanclass=\"label-text\">INDEXDB</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXDJX\"><spanclass=\"label-text\">INDEXDJX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXEURO\"><spanclass=\"label-text\">INDEXEURO</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXFTSE\"><spanclass=\"label-text\">INDEXFTSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXHANGSENG\"><spanclass=\"label-text\">INDEXHANGSENG</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXNASDAQ\"><spanclass=\"label-text\">INDEXNASDAQ</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXNIKKEI\"><spanclass=\"label-text\">INDEXNIKKEI</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXNYSEGIS\"><spanclass=\"label-text\">INDEXNYSEGIS</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXRUSSELL\"><spanclass=\"label-text\">INDEXRUSSELL</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXSHE\"><spanclass=\"label-text\">INDEXSHE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXSP\"><spanclass=\"label-text\">INDEXSP</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXSTOXX\"><spanclass=\"label-text\">INDEXSTOXX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXSWX\"><spanclass=\"label-text\">INDEXSWX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"INDEXTSI\"><spanclass=\"label-text\">INDEXTSI</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"JSE\"><spanclass=\"label-text\">JSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"JSX\"><spanclass=\"label-text\">JSX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"KSC\"><spanclass=\"label-text\">KSC</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"LSE\"><spanclass=\"label-text\">LSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"MAD\"><spanclass=\"label-text\">MAD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"MCX\"><spanclass=\"label-text\">MCX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"MEX\"><spanclass=\"label-text\">MEX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"MIL\"><spanclass=\"label-text\">MIL</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"NASDAQ\"><spanclass=\"label-text\">NASDAQ</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"NSE\"><spanclass=\"label-text\">NSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"NYSE\"><spanclass=\"label-text\">NYSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"NYSEARCA\"><spanclass=\"label-text\">NYSEARCA</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"NZX\"><spanclass=\"label-text\">NZX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"OMX\"><spanclass=\"label-text\">OMX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"OMXC\"><spanclass=\"label-text\">OMXC</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"OMXT\"><spanclass=\"label-text\">OMXT</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"OTCMKTS\"><spanclass=\"label-text\">OTCMKTS</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"PAR\"><spanclass=\"label-text\">PAR</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"SAO\"><spanclass=\"label-text\">SAO</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"SIX\"><spanclass=\"label-text\">SIX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"SZCE\"><spanclass=\"label-text\">SZCE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"TADAWUL\"><spanclass=\"label-text\">TADAWUL</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"TASE\"><spanclass=\"label-text\">TASE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"TSX\"><spanclass=\"label-text\">TSX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"TSXV\"><spanclass=\"label-text\">TSXV</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"TWSE\"><spanclass=\"label-text\">TWSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"VSE\"><spanclass=\"label-text\">VSE</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"se[]\"value=\"WSE\"><spanclass=\"label-text\">WSE</span></label></div></div></div><br><divid=\"currency\"style=\"display:none;\"><h5><strong>Currency</strong></h5><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"ARS\"><spanclass=\"label-text\">ARS</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"AUD\"><spanclass=\"label-text\">AUD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"BRL\"><spanclass=\"label-text\">BRL</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"CAD\"><spanclass=\"label-text\">CAD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"CHF\"><spanclass=\"label-text\">CHF</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"CNY\"><spanclass=\"label-text\">CNY</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"DKK\"><spanclass=\"label-text\">DKK</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"EUR\"><spanclass=\"label-text\">EUR</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"GBP\"><spanclass=\"label-text\">GBP</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"GBX\"><spanclass=\"label-text\">GBX</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"HKD\"><spanclass=\"label-text\">HKD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"IDR\"><spanclass=\"label-text\">IDR</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"ILA\"><spanclass=\"label-text\">ILA</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"INR\"><spanclass=\"label-text\">INR</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"ISK\"><spanclass=\"label-text\">ISK</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"JPY\"><spanclass=\"label-text\">JPY</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"KRW\"><spanclass=\"label-text\">KRW</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"MXN\"><spanclass=\"label-text\">MXN</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"MYR\"><spanclass=\"label-text\">MYR</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"NZD\"><spanclass=\"label-text\">NZD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"PLN\"><spanclass=\"label-text\">PLN</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"RUB\"><spanclass=\"label-text\">RUB</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"SAR\"><spanclass=\"label-text\">SAR</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"SEK\"><spanclass=\"label-text\">SEK</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"SGD\"><spanclass=\"label-text\">SGD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"THB\"><spanclass=\"label-text\">THB</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"TRY\"><spanclass=\"label-text\">TRY</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"TWD\"><spanclass=\"label-text\">TWD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"USD\"><spanclass=\"label-text\">USD</span></label></div></div><divclass=\"col-md-3\"><divclass=\"form-check\"><label><inputtype=\"checkbox\"name=\"c[]\"value=\"ZAC\"><spanclass=\"label-text\">ZAC</span></label></div></div></div></form></div></div></div><divclass=\"pull-right\">Page1of1|Totalresults:1</div><tableclass=\"table table-striped\"><thead><tr><th>Ticker</th><th>Name</th><th>StockExchange</th><th>Currency</th><th>Price</th></tr></thead><tbody><tronclick=\"openStock('40B.SI')\"style=\"cursor: pointer;\"><td><ahref=\"https://www.worldtradingdata.com/stock/40B.SI\">40B.SI</a></td><td>SMJInternationalHoldingsLtd.</td><td></td><td>SGD</td><td>0.08</td></tr></tbody></table></div><divclass=\"pull-right\">Page1of1|Totalresults:1</div></div></div></div></div><!--CalltoAction--><divclass=\"call-to-action helper mt60\"><divclass=\"container\"><divclass=\"row\"><divclass=\"col-md-12\"><h3class=\"call-to-action-title\">TryourstockdataAPI,it's free! </h3>                         <p class=\"call-to-action-description\"> Sign up to get your free API key and begin using the API. </p>                         <div class=\"call-to-action-buttons\">                             <a href=\"https://www.worldtradingdata.com/register\" class=\"call-to-action-button\">Get Started</a>                         </div>                     </div>                 </div>             </div>         </div>         <!-- End of Call to Action -->          <script>             function openStock(ticker){                 document.location.href = \"https://www.worldtradingdata.com/stock\" + \"/\" + ticker;             }         </script>          <!-- Footer -->         <footer class=\"js-footer-is-fixed\">             <!-- Footer Default -->             <div class=\"footer\">                 <div class=\"container\">                     <div class=\"row\">                         <div class=\"col-md-3 col-sm-3 col-xs-12\">                             <div class=\"footer-logo-wrapper\">                                 <!-- Logo Image -->                                 <a href=\"https://www.worldtradingdata.com\" class=\"logo-image \">                                     <img src=\"https://www.worldtradingdata.com/assets/main/img/logos/logo.png\" alt=\"logo\">                                 </a>                                 <!-- End of Logo Image -->                             </div>                         </div>                         <div class=\"col-md-9 col-sm-9 col-xs-12\">                             <div class=\"footer-wrapper\">                                 <!-- Scroll top -->                                 <span class=\"scroll-top js-scroll-top\">                                     <i class=\"fa fa-angle-up\"></i>                                 </span>                                 <!-- End of Scroll top -->                                 <!-- Footer Menu -->                                 <ul class=\"footer-menu helper right\">                                     <li>                                         <a href=\"https://www.worldtradingdata.com/privacy\"> Privacy Policy </a>                                     </li>                                     <li>                                         <a href=\"https://www.worldtradingdata.com/tos\"> Terms & Conditions </a>                                     </li>                                     <li>                                         <a href=\"https://www.worldtradingdata.com/login\"> My Account </a>                                     </li>                                     <li>                                         <a href=\"https://www.worldtradingdata.com/faq\"> Support </a>                                     </li>                                 </ul>                                 <!-- End of Footer Menu -->                                 <!-- Copyright -->                                 <p class=\"copyright helper right\">                                     <a href=\"https://www.worldtradingdata.com\">World Trading Data</a>, all rights reserved. 2019 &copy; </p>                                 <!-- End of Copyright -->                             </div>                         </div>                     </div>                 </div>             </div>             <!-- End of Footer Default -->         </footer>         <!-- End of Footer -->     </div>     <script src=\"https://www.worldtradingdata.com/assets/main/js/all.js\"></script>     <script src=\"https://www.worldtradingdata.com/assets/main/js/custom.js\"></script>         <!-- Global site tag (gtag.js) - Google Analytics -->     <script async src=\"https://www.googletagmanager.com/gtag/js?id=UA-114192558-1\"></script>     <script>       window.dataLayer = window.dataLayer || [];       function gtag(){dataLayer.push(arguments);}       gtag('js', new Date());        gtag('config', 'UA-114192558-1');     </script>  </body>  </html>";
        Document doc;
        try {
            //String rr=readUri("https://www.worldtradingdata.com/search?q=40B.SI");
            doc = Jsoup.connect("https://www.worldtradingdata.com/stock/40B.SI").get();
            Element elementById = doc.body().getElementById("content");
            Map<String, String> dataset = elementById.dataset();

            String title = doc.title();
            //            RestTemplate restTemplate = new RestTemplate();
            //            String result = restTemplate.getForObject("https://www.worldtradingdata.com/search?q=40B.SI", String.class);
            //            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
