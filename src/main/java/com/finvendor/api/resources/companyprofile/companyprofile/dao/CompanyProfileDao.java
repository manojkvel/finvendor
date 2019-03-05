package com.finvendor.api.resources.companyprofile.companyprofile.dao;

import com.finvendor.api.resources.companyprofile.companyprofile.dto.BrokerRank;
import com.finvendor.api.resources.companyprofile.companyprofile.dto.PriceReturn;
import com.finvendor.api.resources.markets.dao.MarketsDao;
import com.finvendor.common.util.DateUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.api.resources.companyprofile.companyprofile.dto.CompanyProfileData;
import com.finvendor.api.resources.researchreport.equity.dao.EquityReportDao;
import com.finvendor.api.resources.researchreport.equity.dto.filter.ResearchReportFilter;
import com.finvendor.api.resources.researchreport.equity.dto.result.AbsResearchReportResult;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyProfileDao {
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
    public static final String buyCountQuery = "SELECT count(*) FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, vendor_report_data WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=? and vendor_report_data.rsrch_recomm_type='buy'";
    public static final String sellCountQuery = "SELECT count(*) FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, vendor_report_data WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=? and vendor_report_data.rsrch_recomm_type='sell'";
    public static final String neutralCountQuery = "SELECT count(*) FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, vendor_report_data WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=? and vendor_report_data.rsrch_recomm_type='neutral'";
    public static final String avgCountQuery = "select avg(vendor_report_data.target_price) FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, vendor_report_data WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=?";

    /**
     * Company Research Report Query
     */
    // Complex Query - Do not refactor it
    public static final String mainQuery = "SELECT rsch_sub_area_company_dtls.company_id comapanyId, rsch_sub_area_company_dtls.company_name companyName, rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style, market_cap_def.market_cap_name mcap, research_sub_area.description sector, stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt, stock_current_info.pe pe, stock_current_info.3_yr_pat_growth patGrth, stock_current_info.3_yr_eps_growth epsGrth, stock_current_info.eps_ttm epsttm, vendor_report_data.research_report_for_id companyId, vendor_report_data.product_id prdId, vendor_report_data.vendor_company broker, vendor_report_data.rsrch_recomm_type recommType, vendor_report_data.target_price tgtPrice, vendor_report_data.price_at_recomm prcAtRecomm, ((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100 upside, vendor_report_data.report_name rptName, vendor_report_data.report_date rsrchDt, vendor_report_data.analyst_awards award, vendor_report_data.anayst_cfa_charter cfa, vendor_report_data.analyst_name analystName, vendor_report_data.vendor_analyst_type analystType, vendor_report_data.vendor_id vendorId, vendor_report_data.launched_year ly, vendor_report_data.vendor_name userName, vendor_report_data.rsrch_report_desc rptDesc, vendor_report_data.product_name productNameAsReportName FROM rsch_sub_area_company_dtls,      rsch_area_stock_class,      market_cap_def,      comp_mkt_cap_type,      research_sub_area,      stock_current_prices,      stock_current_info,      vendor_report_data WHERE   rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = COUNTRYID   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=?";

    @Autowired
    private ICommonDao commonDao;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    private EquityReportDao equityReportDao;
    private static final String STOCK_CLOSE_PRICE = "SELECT b.stock_id,b.close_price,STR_TO_DATE(b.price_date,  \"%d/%b/%y\" )  date FROM  stock_historical_prices b WHERE  b.stock_id=? ORDER BY STR_TO_DATE(b.price_date,  \"%d/%b/%y\" ) DESC limit 1 offset ?";
    private static final String NIFTY_CLOSE_PRICE = "SELECT STR_TO_DATE(date,  \"%d-%b-%y\" ), close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  \"%d-%b-%y\" ) DESC limit 1 offset ?";

    /**
     * upside = ((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100
     */
    @SuppressWarnings("unchecked")
    public String getCompanyProfile(String query, String isinCode) throws RuntimeException {
        SQLQuery query1 = commonDao.getNativeQuery(query, null);
        List<Object[]> rows = query1.list();
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        String companyProfile = "NA";
        String summary = "";
        String valuationScoreStr = "";
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

                // market cap needs to be calculated daily using below formula:
                // = shares_outstanding x today's market price
                String mkt_cap = String.valueOf(shareOutStandingAsFloat * cmpAsFloat);// row[15] != null ?
                // row[15].toString().trim() :
                // "";

                // static and it will updated quaterly
                String revenue = row[16] != null ? row[16].toString().trim() : "";// static
                revenue = StringUtils.remove(revenue, ",");
                // static and it will updated quaterly
                String face_value = row[17] != null ? row[17].toString().trim() : "";// static

                // static and it will updated quaterly
                //String bv_share = row[18] != null ? row[18].toString().trim() : "";// static
                float bv_share_as_float = cmpAsFloat / Float.parseFloat(pb);

                // static and it will updated quaterly
                String roe = row[19] != null ? row[19].toString().trim() : "";// static update qtrly

                // static and it will updated quaterly
                String pat = row[20] != null ? row[20].toString().trim() : "";// static
                pat = StringUtils.remove(pat, ",");
                String recent_qtr = row[21] != null ? row[21].toString().trim() : "";

                // date is comming in this format "dd/MMM/yy HH:mm:ss"
                String price_date = row[22] != null ? row[22].toString().trim() : "";
                String price_date_in_millis = String.valueOf(DateUtil.convertFvPriceDateToTimestamp(price_date));
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
                } else {
                    newPe = cmpAsFloat / eps_ttm_as_float;
                    newPeStr = String.valueOf(newPe);
                }

                // PB Calculation PB=cmp/bv_share
                String newPBStr = "";
                float newPB = 0.0f;
                if (bv_share_as_float == 0.0f) {
                    newPBStr = "N/A";
                } else {
                    newPB = cmpAsFloat / bv_share_as_float;
                    newPBStr = String.valueOf(newPB);
                }

                //Demand - Valuation Score Calculation logic
                /*
                Valuation score
                   Logic should be implemented in below order (if logic-1 meets the condition , no need to go to
                    logic-2 or 3; if logic-2 meets the condition , no need to go to logic-3)

                   Logic-1 : if [3 YR EPS growth] <= 0 then Strong Sell (Deep RED)

                   Logic-2 : if [P/E] <= 0 then Strong Sell (Deep RED)

                   Logic-3 :
                   [(3 YR EPS growth - P/E ) / 3 YR EPS growth] * 100 = V
                   IF V > 15%  	        => Strong Buy (Deep Green)
                   IF 15% > V > 5%  	=> Buy (Light Green)
                   if -5% > V > 5% 	        => Neutral (Orange)
                   if -15% < V < -5% 	=> Sell (Light RED)
                   if V < -15% 	        => STRONG Sell (Deep RED)
                 */
                if (_3yrEpsGrowthAsFloat <= 0.0F || newPe <= 0.0F) {
                    valuationScoreStr = "Very Overpriced";
                } else {
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
                paramsMap.put("companyProfileData",
                        new CompanyProfileData(companyId, companyName, industry, mcap, cmp, absoluteLastChangedCmp,
                                lastChangedCmpInPercentage, newPeStr, "-", dividen_yield, eps_ttm, _52w_high,
                                _52w_low, beta, share_outstanding, mkt_cap, revenue.trim(), face_value, "-", roe, pat,
                                recent_qtr, price_date_in_millis, price_src_code, valuationScoreStr, currency));
                paramsMap.put("summary", summary);

                //Hisorical price
                Map<String, String> stockPriceReturnMap = new LinkedHashMap<>();
                String stockId = getCompanyId("select a.company_id,a.isin_code from rsch_sub_area_company_dtls a where a.isin_code=?", isinCode);
                float wPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 4);
                String _lWPriceStr = wPrice != 0.0F ? String.valueOf((cmpAsFloat - wPrice) * 100 / wPrice) : "-";

                float _1MPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 19);
                String _lMPriceStr = _1MPrice != 0.0F ? String.valueOf((cmpAsFloat - _1MPrice) * 100.0F / _1MPrice) : "-";

                float _3MPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 59);
                String _3MPriceStr = _3MPrice != 0.0F ? String.valueOf((cmpAsFloat - _3MPrice) * 100.0F / _3MPrice) : "-";

                float _6MPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 119);
                String _6MPriceStr = _6MPrice != 0.0F ? String.valueOf((cmpAsFloat - _6MPrice) * 100.0F / _6MPrice) : "-";

                float _1YPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 239);
                String _lYPriceStr = _1YPrice != 0.0F ? String.valueOf((cmpAsFloat - _1YPrice) * 100.0F / _1YPrice) : "-";

                float _2YPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 479);
                String _2YPriceStr = _2YPrice != 0.0F ? String.valueOf((cmpAsFloat - _2YPrice) * 100.0F / _2YPrice) : "-";

                float _5YPrice = getHistoryClosePrice(STOCK_CLOSE_PRICE, stockId, 1199);
                String _5YPriceStr = _5YPrice != 0.0F ? String.valueOf((cmpAsFloat - _5YPrice) * 100.0F / _5YPrice) : "-";

                stockPriceReturnMap.put("1W", _lWPriceStr);
                stockPriceReturnMap.put("1M", _lMPriceStr);
                stockPriceReturnMap.put("3M", _3MPriceStr);
                stockPriceReturnMap.put("6M", _6MPriceStr);
                stockPriceReturnMap.put("1Y", _lYPriceStr);
                stockPriceReturnMap.put("2Y", _2YPriceStr);
                stockPriceReturnMap.put("5Y", _5YPriceStr);

                Map<String, String> nifty50PriceReturnMap = new LinkedHashMap<>();
                String indexSummary = marketsDao.getIndexSummary("Nifty 50");
                float closing = Float.parseFloat(JsonUtil.getValue(indexSummary, "closing").trim());
                wPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 5);
                _lWPriceStr = wPrice != 0.0F ? String.valueOf((closing - wPrice) * 100 / wPrice) : "-";

                _1MPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 19);
                _lMPriceStr = _1MPrice != 0.0F ? String.valueOf((closing - _1MPrice) * 100.0F / _1MPrice) : "-";

                _3MPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 62);
                _3MPriceStr = _3MPrice != 0.0F ? String.valueOf((closing - _3MPrice) * 100.0F / _3MPrice) : "-";

                _6MPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 121);
                _6MPriceStr = _6MPrice != 0.0F ? String.valueOf((closing - _6MPrice) * 100.0F / _6MPrice) : "-";

                _1YPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 247);
                _lYPriceStr = _1YPrice != 0.0F ? String.valueOf((closing - _1YPrice) * 100.0F / _1YPrice) : "-";

                _2YPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 480);
                _2YPriceStr = _2YPrice != 0.0F ? String.valueOf((closing - _2YPrice) * 100.0F / _2YPrice) : "-";

                _5YPrice = getHistoryClosePrice(NIFTY_CLOSE_PRICE, null, 1199);
                _5YPriceStr = _5YPrice != 0.0F ? String.valueOf((closing - _5YPrice) * 100.0F / _5YPrice) : "-";

                nifty50PriceReturnMap.put("1W", _lWPriceStr);
                nifty50PriceReturnMap.put("1M", _lMPriceStr);
                nifty50PriceReturnMap.put("3M", _3MPriceStr);
                nifty50PriceReturnMap.put("6M", _6MPriceStr);
                nifty50PriceReturnMap.put("1Y", _lYPriceStr);
                nifty50PriceReturnMap.put("2Y", _2YPriceStr);
                nifty50PriceReturnMap.put("5Y", _5YPriceStr);


                PriceReturn priceReturn = new PriceReturn();
                priceReturn.setNifty50(nifty50PriceReturnMap);
                priceReturn.setStock(stockPriceReturnMap);
                paramsMap.put("priceHistory", priceReturn);
                BrokerRank brokerRank=new BrokerRank(5,6,8,256.36f,19.8f);
                paramsMap.put("brokerRank", brokerRank);

                companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException("Error has occured while creating json for company profile data", e);
        }
        return companyProfile;
    }

    private float getHistoryClosePrice(String query, String stockId, Integer offset) {
        SQLQuery query1;
        List<Object[]> rows;
        query1 = commonDao.getNativeQuery(query, stockId != null ? new Object[]{stockId, offset} : new Object[]{offset});
        rows = query1.list();
        String closePrice = !rows.isEmpty() ? (rows.get(0)[1] != null ? rows.get(0)[1].toString().trim() : "") : "";
        return !StringUtils.isEmpty(closePrice) ? Float.parseFloat(closePrice) : 0.0f;
    }

    private String getCompanyId(String query, String isinCode) {
        SQLQuery query1;
        List<Object[]> rows;
        query1 = commonDao.getNativeQuery(query, isinCode != null ? new String[]{isinCode} : null);
        rows = query1.list();
        String companyId = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        return companyId;
    }

    public String getCompanyProfileReasearchReport(String mainQuery, String isinCode, ResearchReportFilter filter,
                                                   String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        String companyProfile = "NA";
        try {
            Map<String, ? extends AbsResearchReportResult> equityData = equityReportDao
                    .findResearchReportTableData(mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);

            Collection<? extends AbsResearchReportResult> equityList = equityData.values();

            paramsMap.put("noOfAnalystReport", equityList.size());
            // Total Buy Recomm
            SQLQuery sqlQuery = commonDao.getNativeQuery(buyCountQuery, new String[]{isinCode});
            Object object = sqlQuery.list().get(0);
            if (object instanceof BigInteger) {
                BigInteger i = (BigInteger) object;
                paramsMap.put("totalBuyRecomm", i);
            }

            // Total Sell Recomm
            sqlQuery = commonDao.getNativeQuery(sellCountQuery, new String[]{isinCode});
            object = sqlQuery.list().get(0);
            if (object instanceof BigInteger) {
                BigInteger i = (BigInteger) object;
                paramsMap.put("totalSellRecomm", i);
            }

            // Total Neutral Recomm
            sqlQuery = commonDao.getNativeQuery(neutralCountQuery, new String[]{isinCode});
            object = sqlQuery.list().get(0);
            if (object instanceof BigInteger) {
                BigInteger i = (BigInteger) object;
                paramsMap.put("totalNeutralRecomm", i);
            }

            // Average Target Price
            sqlQuery = commonDao.getNativeQuery(avgCountQuery, new String[]{isinCode});
            object = sqlQuery.list().get(0);
            if (object instanceof Double) {
                Double i = (Double) object;
                paramsMap.put("averageTargetPrice", i);
            }

            paramsMap.put("equity", equityList);
            companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException("Error has occured getReasearchReportData(), DAO Error::", e);
        }
        return companyProfile;
    }
}
