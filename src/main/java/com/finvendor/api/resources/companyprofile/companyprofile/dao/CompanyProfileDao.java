package com.finvendor.api.resources.companyprofile.companyprofile.dao;

import com.finvendor.api.resources.companyprofile.companyprofile.dto.PriceReturn;
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

    /**
     * Company Profile Query
     */
    public static final String companyProfileDataQuery = "SELECT distinct rsch_sub_area_company_dtls.company_id companyId,rsch_sub_area_company_dtls.company_name companyName, market_cap_def.market_cap_name mcap, research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.last_trade_price ltp,stock_current_info.pe,stock_current_info.pb,stock_current_info.dividend_yield,stock_current_info.eps_ttm,stock_current_info.52w_high,stock_current_info.52w_low,stock_current_info.beta,stock_current_info.as_of_date,stock_current_info.shares_outstanding,stock_current_info.mkt_cap,stock_current_info.revenue,stock_current_info.face_value,stock_current_info.bv_share,stock_current_info.roe,stock_current_info.pat,stock_current_info.recent_qtr,stock_current_prices.price_date, stock_current_prices.price_src_code,rsch_sub_area_company_dtls.company_desc,stock_current_info.3_yr_eps_growth  FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices,stock_current_info,country,ven_rsrch_rpt_dtls WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = %1$d AND country.country_id = %2$d AND rsch_sub_area_company_dtls.isin_code = '%3$s'";

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
    public static final String mainQuery = "SELECT rsch_sub_area_company_dtls.company_id comapanyId, rsch_sub_area_company_dtls.company_name companyName, rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style, market_cap_def.market_cap_name mcap, research_sub_area.description sector, stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt, stock_current_info.pe pe, stock_current_info.3_yr_pat_growth patGrth, stock_current_info.3_yr_eps_growth epsGrth, stock_current_info.eps_ttm epsttm, vendor_report_data.research_report_for_id companyId, vendor_report_data.product_id prdId, vendor_report_data.vendor_company broker, vendor_report_data.rsrch_recomm_type recommType, vendor_report_data.target_price tgtPrice, vendor_report_data.price_at_recomm prcAtRecomm, ((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100 upside, vendor_report_data.report_name rptName, vendor_report_data.report_date rsrchDt, vendor_report_data.analyst_awards award, vendor_report_data.anayst_cfa_charter cfa, vendor_report_data.analyst_name analystName, vendor_report_data.vendor_analyst_type analystType, vendor_report_data.vendor_id vendorId, vendor_report_data.launched_year ly, vendor_report_data.vendor_name userName, vendor_report_data.rsrch_report_desc rptDesc, vendor_report_data.product_name productNameAsReportName FROM rsch_sub_area_company_dtls,      rsch_area_stock_class,      market_cap_def,      comp_mkt_cap_type,      research_sub_area,      stock_current_prices,      stock_current_info,      vendor_report_data WHERE   rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id and rsch_sub_area_company_dtls.isin_code=?";

//    #Current
//    SELECT STR_TO_DATE(b.price_date,  "%d/%b/%y" ),b.close_price FROM  rsch_sub_area_company_dtls a, stock_historical_prices b WHERE a.isin_code='INE002A01018' and  a.company_id=b.stock_id ORDER BY STR_TO_DATE( price_date,  "%d/%b/%y" ) DESC limit 1 offset 0;
//
//#Week
//    SELECT b.close_price FROM  rsch_sub_area_company_dtls a, stock_historical_prices b WHERE a.company_id=b.stock_id and a.isin_code='INE002A01018' ORDER BY STR_TO_DATE( price_date,  "%d/%b/%y" ) DESC limit 1 offset 6;
//
//#1M
//    SELECT b.close_price FROM  rsch_sub_area_company_dtls a, stock_historical_prices b WHERE a.company_id=b.stock_id and a.isin_code='INE002A01018' ORDER BY STR_TO_DATE( price_date,  "%d/%b/%y" ) DESC limit 1 offset 29;
//
//#3M
//    SELECT b.close_price FROM  rsch_sub_area_company_dtls a, stock_historical_prices b WHERE a.company_id=b.stock_id and a.isin_code='INE002A01018' ORDER BY STR_TO_DATE( price_date,  "%d/%b/%y" ) DESC limit 1 offset 89;
//
//#6M
//    SELECT b.close_price FROM  rsch_sub_area_company_dtls a, stock_historical_prices b WHERE a.company_id=b.stock_id and a.isin_code='INE002A01018' ORDER BY STR_TO_DATE( price_date,  "%d/%b/%y" ) DESC limit 1 offset 179;
//
//    CREATE TABLE IF NOT EXISTS `nifty50_price_history` (
//            `date` varchar(50) DEFAULT NULL,
//`close` varchar(50) DEFAULT NULL
//) ENGINE=InnoDB DEFAULT CHARSET=latin1;
//
//##Nify50
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close
//    FROM  nifty50_price_history
//    ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC;
//
//#Current
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 0;
//
//#1W
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//#1M
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//#3M
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//#6M
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//#1Y
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//#2Y
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//#5Y
//    SELECT STR_TO_DATE(date,  "%d-%b-%y" ),close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  "%d-%b-%y" ) DESC limit 1 offset 6;
//
//

    @Autowired
    private ICommonDao commonDao;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    private EquityReportDao equityReportDao;

    @SuppressWarnings("unchecked")
    public String getCompanyProfile(String query) throws RuntimeException {
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
                if (_3yrEpsGrowthAsFloat <= 0.0F || newPe <= 0) {
                    valuationScoreStr = "Strong Sell";
                } else {
                    float valuationScore = (_3yrEpsGrowthAsFloat - newPe) / _3yrEpsGrowthAsFloat * 100;
                    logger.info("valuationScore:{}", valuationScore);
                    if (valuationScore >= -15.0f && valuationScore < -5.0f) {
                        valuationScoreStr = "Sell";
                    }
                    if (valuationScore >= -5.0f || valuationScore < 5.0f) {
                        valuationScoreStr = "Neutral";
                    }
                    if (valuationScore >= 5.0f && valuationScore < 15.0f) {
                        valuationScoreStr = "Buy";
                    }
                    if (valuationScore >= 15.0f) {
                        valuationScoreStr = "Strong Buy";
                    }
                    if (valuationScore < -15.0) {
                        valuationScoreStr = "Strong Sell";
                    }
                }
                paramsMap.put("companyProfileData",
                        new CompanyProfileData(companyId, companyName, industry, mcap, cmp, absoluteLastChangedCmp,
                                lastChangedCmpInPercentage, newPeStr, "-", dividen_yield, eps_ttm, _52w_high,
                                _52w_low, beta, share_outstanding, mkt_cap, revenue.trim(), face_value, "-", roe, pat,
                                recent_qtr, price_date_in_millis, price_src_code, valuationScoreStr));
            }
            paramsMap.put("summary", summary);
            PriceReturn priceReturn = new PriceReturn();

            Map<String, String> nifty50PriceReturnMap = new LinkedHashMap<>();
            nifty50PriceReturnMap.put("1W", "-");
            nifty50PriceReturnMap.put("1M", "-");
            nifty50PriceReturnMap.put("3M", "-");
            nifty50PriceReturnMap.put("6M", "-");
            nifty50PriceReturnMap.put("1Y", "-");
            nifty50PriceReturnMap.put("2Y", "-");
            nifty50PriceReturnMap.put("5Y", "-");

            Map<String, String> stockPriceReturnMap = new LinkedHashMap<>();
            stockPriceReturnMap.put("1W", "-");
            stockPriceReturnMap.put("1M", "-");
            stockPriceReturnMap.put("3M", "-");
            stockPriceReturnMap.put("6M", "-");
            stockPriceReturnMap.put("1Y", "-");
            stockPriceReturnMap.put("2Y", "-");
            stockPriceReturnMap.put("5Y", "-");


            priceReturn.setNifty50(nifty50PriceReturnMap);
            priceReturn.setStock(stockPriceReturnMap);
            paramsMap.put("priceHistory", priceReturn);

            companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
            return companyProfile;
        } catch (ParseException | IOException e) {
            throw new RuntimeException("Error has occured while creating json for company profile data", e);
        }
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
