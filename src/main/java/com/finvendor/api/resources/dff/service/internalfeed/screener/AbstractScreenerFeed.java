package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.JoelEbitAndEnterpriseDto;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.JoelRotcDto;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.dff.DffProcesFeed;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public abstract class AbstractScreenerFeed implements DffProcesFeed {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractScreenerFeed.class.getName());
    //final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(".##");
    float INFLATION_RATE_PERCENTAGE = 2.86F;
    private static final  String INFLATION_PERIOD = "Mar_19";
    private static final  String INFLATION_TYPE = "CPI";

    private static final String RESEARCH_AREA = "7";
    private static final String COUNTRY_ID = "1";

    private static final String COMPANY_DETAILS_QUERY = "SELECT distinct rsch_sub_area_company_dtls.company_id companyId, rsch_sub_area_company_dtls.company_name companyName,market_cap_def.market_cap_name mcap,research_sub_area.description           sector,stock_current_prices.close_price        cmp,stock_current_prices.last_trade_price   ltp,stock_current_info.pe,stock_current_info.pb,stock_current_info.dividend_yield,stock_current_info.eps_ttm,stock_current_info.52w_high,stock_current_info.52w_low,stock_current_info.beta,stock_current_info.as_of_date,stock_current_info.shares_outstanding,stock_current_info.shares_outstanding*stock_current_prices.close_price mktCap,stock_current_info.mkt_cap,stock_current_info.revenue,stock_current_info.face_value,stock_current_info.bv_share,stock_current_info.roe,stock_current_info.pat,stock_current_info.recent_qtr,stock_current_prices.price_date,stock_current_prices.price_src_code,rsch_sub_area_company_dtls.company_desc,stock_current_info.3_yr_eps_growth,rsch_sub_area_company_dtls.currency,rsch_sub_area_company_dtls.ticker FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type,research_sub_area,stock_current_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = ? AND country.country_id = ? order by CAST(rsch_sub_area_company_dtls.company_id as UNSIGNED)";
    private static final String EARNING_PREVIEW_QUERY = "select earning_preview_yearly.*,earning_preview_as_of_date.* from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id AND earning_preview_yearly.stock_id = ? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 1 offset 0";
    private static final String _3YR_AVG_NET_PROFIT_MARGIN_QUERY="select earning_preview_yearly.stock_id,earning_preview_yearly.period,earning_preview_yearly.revenue,earning_preview_yearly.profit_after_tax,ROUND((earning_preview_yearly.profit_after_tax/earning_preview_yearly.revenue),2) NPM from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id AND earning_preview_yearly.stock_id = ? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 3 offset 0";

    private static final String EPS_GROWTH = "select earning_preview_yearly.stock_id,earning_preview_yearly.period,earning_preview_yearly.eps from earning_preview_yearly where earning_preview_yearly.stock_id=? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc;";

    //Joel Queries
    private static final String COMPANY_DETAILS_FOR_JOEL="SELECT distinct rsch_sub_area_company_dtls.company_id companyId, rsch_sub_area_company_dtls.company_name companyName,(stock_current_info.shares_outstanding * stock_current_prices.close_price) mktCap,rsch_sub_area_company_dtls.ticker FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type,research_sub_area,stock_current_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = ? AND country.country_id = ? order by CAST(rsch_sub_area_company_dtls.company_id as UNSIGNED)";
    private static final String ROTC_QUERY = "select earning_preview_yearly.stock_id,earning_preview_yearly.profit_after_tax,earning_preview_as_of_date.total_capital, ROUND((earning_preview_yearly.profit_after_tax/earning_preview_as_of_date.total_capital),2) ROTC from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id and earning_preview_yearly.stock_id=? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 1 offset 0";
    private static final String EBIT_ATTRIBUTES_QUERY="select earning_preview_yearly.stock_id,earning_preview_yearly.revenue,earning_preview_yearly.operating_profit_margin,earning_preview_as_of_date.total_debt,earning_preview_as_of_date.cash_and_cash_equiv from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id and earning_preview_yearly.stock_id=? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 1 offset 0";

    //Martin
    private static final String LATEST_REVENUE_GROWTH = "select earning_preview_yearly.stock_id, earning_preview_yearly.period,earning_preview_yearly.revenue from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id AND earning_preview_yearly.stock_id = ? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 2 offset 0";

    @Autowired
    protected ICommonDao commonDao;

    /**
     * This method will be used for Martin Zweig
     * Condition eps growth Y1>Y2>Y3>Y4>Y5
     */
    boolean isEveryEpsGrowthGreaterThanPrevioudEpsGrowth(String stockId){
        boolean isEveryEpsGrowthGreaterThanPrevioudEpsGrowth=true;
        Map<String, Float> lastNYearEPSGrowth = findLastNYearEPSGrowth(stockId);

        return isEveryEpsGrowthGreaterThanPrevioudEpsGrowth;
    }

    /**
     * This method will be used for Martin Zweig
     * % Average for all year
     */
    float findAllYearEpsGrowth(String stockId){
        float allYrEpsGrowth;
        Map<String, Float> lastNYearEPSGrowthMap = findLastNYearEPSGrowth(stockId);
        Collection<Float> values = lastNYearEPSGrowthMap.values();
        float sumOfAllEpsGrowth = 0.0F;
        for (Float epsGrowth : values) {
            sumOfAllEpsGrowth += epsGrowth;
        }
        allYrEpsGrowth = sumOfAllEpsGrowth / values.size();
        return allYrEpsGrowth;
    }

    /**
     * This method will be used for Martin Zweig
     */
    float findLatestRevenueGrowth(String stockId){
        //Last 2 year % growth difference
        float latestRevenueGwoth;

        SQLQuery sqlQuery = commonDao.getNativeQuery(LATEST_REVENUE_GROWTH, new Object[] { stockId});
        List<Object[]> companyDetailsList = sqlQuery.list();
        Object revenueY1 = companyDetailsList.get(0)[2];
        Object revenueY2 = companyDetailsList.get(1)[2];
        float revenueFloatY1 = revenueY1 != null && !StringUtils.isEmpty(revenueY1.toString()) && !"-".equals(revenueY1.toString()) ? Float.parseFloat(revenueY1.toString().trim()) : 0.0F;
        float revenueFloatY2 = revenueY2 != null && !StringUtils.isEmpty(revenueY2.toString()) && !"-".equals(revenueY2.toString()) ? Float.parseFloat(revenueY2.toString().trim()) : 0.0F;
        latestRevenueGwoth = (revenueFloatY1 - revenueFloatY2) * 100 / revenueFloatY2;
        return latestRevenueGwoth;
    }
    float find3YearAverageNetProfitMargin(String stockId) {
        SQLQuery sqlQuery = commonDao.getNativeQuery(_3YR_AVG_NET_PROFIT_MARGIN_QUERY, new Object[] { stockId});
        List<Object[]> companyDetailsList = sqlQuery.list();
        float sum = 0.0F;
        for (Object[] row : companyDetailsList) {
            float revenue = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? Float.parseFloat(row[2].toString().trim()) : 0.0F;
            float profitAfterTax = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? Float.parseFloat(row[3].toString().trim()) : 0.0F;
            float netProfitMargin = revenue != 0.0F ? profitAfterTax / revenue : 0.0F;
            sum += netProfitMargin;
        }
        float _3yrAvgNetProfitMargin = sum / 3;
        return _3yrAvgNetProfitMargin;

    }

    Map<Integer, JoelRotcDto> findTop50Rotc() {
        List<JoelRotcDto> joelRotcDtoList = new ArrayList<>();
        Map<Integer, JoelRotcDto> top50RotcMap = new LinkedHashMap<>();

        SQLQuery sqlQuery = commonDao.getNativeQuery(COMPANY_DETAILS_FOR_JOEL, new Object[] { RESEARCH_AREA, COUNTRY_ID });
        List<Object[]> companyDetailsList = sqlQuery.list();
        for (Object[] row : companyDetailsList) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() : "";
            String rotcCompanyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() : "";
            String rotcTicker = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() : "";

            //Find ROTC for this StockID
            SQLQuery rotcSqlQuery = commonDao.getNativeQuery(ROTC_QUERY, new Object[] { stockId });
            List<Object[]> rotcList = rotcSqlQuery.list();
            int rotcStockIdInt = 0;

            float rotcFloat = 0.0F;
            float patFloat = 0.0F;
            float totalCapitalFloat = 0.0F;

            //Size of this loop is 1
            for (Object[] rotcRow : rotcList) {
                String rotcStockId = rotcRow[0] != null && !StringUtils.isEmpty(rotcRow[0].toString()) && !"-".equals(rotcRow[0].toString()) ? rotcRow[0].toString().trim() : "0";
                rotcStockIdInt = Integer.parseInt(rotcStockId);

                patFloat = rotcRow[1] != null && !StringUtils.isEmpty(rotcRow[1].toString()) && !"-".equals(rotcRow[1].toString()) ? Float.parseFloat(rotcRow[1].toString().trim()) : 0.0F;
                totalCapitalFloat = rotcRow[2] != null && !StringUtils.isEmpty(rotcRow[2].toString()) && !"-".equals(rotcRow[2].toString()) ? Float.parseFloat(rotcRow[2].toString().trim()) : 0.0F;

                if (totalCapitalFloat != 0.0F) {

                    rotcFloat = patFloat / totalCapitalFloat;
                }
                else {
                    rotcFloat = 0.0F;
                }
            }

            if (stockId.equals(String.valueOf(rotcStockIdInt))) {
                JoelRotcDto joelRotcDto = new JoelRotcDto(stockId, rotcCompanyName, rotcTicker, rotcFloat, String.valueOf(patFloat),
                        String.valueOf(totalCapitalFloat));
                joelRotcDtoList.add(joelRotcDto);
            }
        }

        //Sort rotcMap  Map by value - At this stage rotcMap is heavy
        Collections.sort(joelRotcDtoList, new Comparator<JoelRotcDto>() {
            @Override public int compare(JoelRotcDto o1, JoelRotcDto o2) {
                return o2.getRotc().compareTo(o1.getRotc());
            }
        });
        for (int i = 0; i < joelRotcDtoList.size(); i++) {
            //lets break once we get top 50
            if (i == 49) {
                break;
            }
           JoelRotcDto item = joelRotcDtoList.get(i);
            top50RotcMap.put(Integer.parseInt(item.getCompanyId()), item);
        }
        return top50RotcMap;
    }

    public Map<Integer, JoelEbitAndEnterpriseDto> findTop50Ebit() {
        List<JoelEbitAndEnterpriseDto> ebitDividedByEnterpriseValueList = new ArrayList<>();
        Map<Integer, JoelEbitAndEnterpriseDto> top50EbitDividedByEnterpriseValueMapMap = new LinkedHashMap<>();

        SQLQuery ebitCompanyDetailsSqlQuery = commonDao.getNativeQuery(COMPANY_DETAILS_FOR_JOEL, new Object[] { RESEARCH_AREA, COUNTRY_ID });
        List<Object[]> ebitCompanyDetailsList = ebitCompanyDetailsSqlQuery.list();
        for (Object[] row : ebitCompanyDetailsList) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() : "";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() : "";
            String mcap = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() : "";
            String ticker = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() : "";

            //Find ROTC for this StockID
            SQLQuery ebitAttributeSqlQuery = commonDao.getNativeQuery(EBIT_ATTRIBUTES_QUERY, new Object[] { stockId });
            List<Object[]> ebitAttributeList = ebitAttributeSqlQuery.list();

            int ebitStockIdInt = 0;
            float ebitFloat = 0.0F;
            float revenue=0.0F;
            float operatingProfitMargin=0.0F;

            String totalDebt="";
            String cachAndCashEquiv="";

            //Size of this loop is 1
            for (Object[] ebitRow : ebitAttributeList) {
                String ebitStockId = ebitRow[0] != null && !StringUtils.isEmpty(ebitRow[0].toString()) && !"-".equals(ebitRow[0].toString()) ? ebitRow[0].toString().trim() : "0";
                ebitStockIdInt = Integer.parseInt(ebitStockId);

                revenue = ebitRow[1] != null && !StringUtils.isEmpty(ebitRow[1].toString()) && !"-".equals(ebitRow[1].toString()) ? Float.parseFloat(ebitRow[1].toString().trim()) : 0.0F;
                operatingProfitMargin = ebitRow[2] != null && !StringUtils.isEmpty(ebitRow[2].toString()) && !"-".equals(ebitRow[2].toString()) ? Float.parseFloat(ebitRow[2].toString().trim()) : 0.0F;
                ebitFloat = revenue * operatingProfitMargin;

                totalDebt = ebitRow[3] != null && !StringUtils.isEmpty(ebitRow[3].toString()) && !"-".equals(ebitRow[3].toString()) ? ebitRow[3].toString().trim() : "0.0";
                cachAndCashEquiv = ebitRow[4] != null && !StringUtils.isEmpty(ebitRow[4].toString()) && !"-".equals(ebitRow[4].toString()) ? ebitRow[4].toString().trim() : "0.0";
            }

            if (stockId.equals(String.valueOf(ebitStockIdInt))) {
                float ebitDividedByEnterpriceValue =
                        ebitFloat / Float.parseFloat(mcap) + (Float.parseFloat(totalDebt) - Float.parseFloat(cachAndCashEquiv));
                JoelEbitAndEnterpriseDto joelEbitAndEnterpriseDto = new JoelEbitAndEnterpriseDto(stockId, companyName, ticker,
                        ebitDividedByEnterpriceValue,
                        String.valueOf(revenue),
                        String.valueOf(operatingProfitMargin),
                        mcap, totalDebt, cachAndCashEquiv);
                ebitDividedByEnterpriseValueList.add(joelEbitAndEnterpriseDto);
            }
        }

        //Sort rotcMap  Map by value - At this stage rotcMap is heavy
        Collections.sort(ebitDividedByEnterpriseValueList, new Comparator<JoelEbitAndEnterpriseDto>() {
            @Override public int compare(JoelEbitAndEnterpriseDto o1, JoelEbitAndEnterpriseDto o2) {
                return o2.getEbitDividedByEnterprice_value().compareTo(o1.getEbitDividedByEnterprice_value());
            }
        });

        for (int i = 0; i < ebitDividedByEnterpriseValueList.size(); i++) {
            //lets break once we get top 50
            if (i == 49) {
                break;
            }
            JoelEbitAndEnterpriseDto item = ebitDividedByEnterpriseValueList.get(i);
            top50EbitDividedByEnterpriseValueMapMap.put(Integer.parseInt(item.getCompanyId()), item);
        }
        return top50EbitDividedByEnterpriseValueMapMap;
    }

    private Map<String, Float> findLastNYearEPSGrowth(String stockId) {
        Map<String, Float> nYearEpsGrowthMap = new TreeMap<>();
        List<Float> epsList = new ArrayList<>();

        SQLQuery sqlQuery = commonDao.getNativeQuery(EPS_GROWTH, new Object[] { Integer.parseInt(stockId) });
        List<Object[]> list = sqlQuery.list();
        for (Object[] row : list) {
            if (row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString())) {
                epsList.add(Float.parseFloat(row[2].toString()));
            }
            else {
                epsList.add(0.0F);
            }
        }

        for (int index = 0; index <= epsList.size() - 2; index++) {
            float currentIndexEps = epsList.get(index);
            float nextIndexEps = epsList.get(index + 1);

            float epsGrowth = (currentIndexEps - nextIndexEps) * 100 / nextIndexEps;
            nYearEpsGrowthMap.put("Y_" + (index + 1), epsGrowth);
        }

        return nYearEpsGrowthMap;
    }

    @SuppressWarnings("unchecked")
    List<CompanyDetails> findCompanyDetails() {
        logger.info("findCompanyDetails()-> COMPANY_DETAILS_QUERY Query:{}", COMPANY_DETAILS_QUERY);
        SQLQuery sqlQuery = commonDao.getNativeQuery(COMPANY_DETAILS_QUERY, new Object[] { RESEARCH_AREA, COUNTRY_ID });
        List<Object[]> list = sqlQuery.list();
        List<CompanyDetails> companyDetailsList = new ArrayList<>();
        for (Object[] row : list) {
            String companyId = row[0] != null ? row[0].toString().trim() : "";
            String companyName = row[1] != null ? row[1].toString().trim() : "";
            String mcap = row[2] != null ? row[2].toString().trim() : "";
            String sector = row[3] != null ? row[3].toString().trim() : "";

            String cmp = row[4] != null ? row[4].toString().trim() : "";
            cmp = StringUtils.replace(cmp, ",", "");
            float cmpFloat = !cmp.isEmpty() && !"-".equals(cmp) ? Float.parseFloat(cmp) : 0.0F;

            String ltp = row[5] != null ? row[5].toString().trim() : "";
            ltp = StringUtils.replace(ltp, ",", "");
            float ltpFloat = !ltp.isEmpty() && !"-".equals(ltp) ? Float.parseFloat(ltp) : 0.0F;

            String pe = row[6] != null ? row[6].toString().trim() : "";
            pe = StringUtils.replace(pe, ",", "");
            float peFloat = !pe.isEmpty() && !"-".equals(pe) ? Float.parseFloat(pe) : 0.0F;

            String pb = row[7] != null ? row[7].toString().trim() : "";
            pb = StringUtils.replace(pb, ",", "");
            float pbFloat = !pb.isEmpty() && !"-".equals(pb) ? Float.parseFloat(pb.trim()) : 0.0F;

            String divYeild = row[8] != null ? row[8].toString().trim() : "";
            divYeild = StringUtils.replace(divYeild, ",", "");
            float divYeildFloat = !divYeild.isEmpty() && !"-".equals(divYeild) ? Float.parseFloat(divYeild) : 0.0F;

            String epsTtm = row[9] != null ? row[9].toString().trim() : "";
            epsTtm = StringUtils.replace(epsTtm, ",", "");
            float epsTtmFloat = !epsTtm.isEmpty() && !"-".equals(epsTtm) ? Float.parseFloat(epsTtm) : 0.0F;

            String _52wHigh = row[10] != null ? row[10].toString().trim() : "";
            _52wHigh = StringUtils.replace(_52wHigh, ",", "");
            float _52wHighFloat = !_52wHigh.isEmpty() && !"-".equals(_52wHigh) ? Float.parseFloat(_52wHigh) : 0.0F;

            String _52wLow = row[11] != null ? row[11].toString().trim() : "";
            _52wLow = StringUtils.replace(_52wLow, ",", "");
            float _52wLowFloat = !_52wLow.isEmpty() && !"-".equals(_52wLow) ? Float.parseFloat(_52wLow) : 0.0F;

            String beta = row[12] != null ? row[12].toString().trim() : "";
            beta = StringUtils.replace(beta, ",", "");
            float betaFloat = !beta.isEmpty() && !"-".equals(beta) ? Float.parseFloat(beta) : 0.0F;

            String asOfDate = row[13] != null ? row[13].toString().trim() : "";
            String shareOutStanding = row[14] != null ? row[14].toString().trim() : "";
            shareOutStanding = StringUtils.replace(shareOutStanding, ",", "");
            float shareOutStandingFloat =
                    !shareOutStanding.isEmpty() && !"-".equals(shareOutStanding) ? Float.parseFloat(shareOutStanding) : 0.0F;

            String closePrice = row[15] != null ? row[15].toString().trim() : "";
            closePrice = StringUtils.replace(closePrice, ",", "");
            float closePriceFloat = !closePrice.isEmpty() && !"-".equals(closePrice) ? Float.parseFloat(closePrice) : 0.0F;

            String mktCap = row[16] != null ? row[16].toString().trim() : "";

            String revenue = row[17] != null ? row[17].toString().trim() : "";
            revenue = StringUtils.replace(revenue, ",", "");
            float revenueFloat = !revenue.isEmpty() && !"-".equals(revenue) ? Float.parseFloat(revenue) : 0.0F;

            String faceValue = row[18] != null ? row[18].toString().trim() : "";
            faceValue = StringUtils.replace(faceValue, ",", "");
            float faceValueFloat = !faceValue.isEmpty() && !"-".equals(faceValue) ? Float.parseFloat(faceValue) : 0.0F;

            String bvShare = row[19] != null ? row[19].toString().trim() : "";
            float bvShareFloat = !bvShare.isEmpty() && !"-".equals(bvShare) ? Float.parseFloat(bvShare) : 0.0F;

            String roe = row[20] != null ? row[20].toString().trim() : "";
            roe = StringUtils.replace(roe, ",", "");
            float roeFloat = !roe.isEmpty() && !"-".equals(roe) ? Float.parseFloat(roe) : 0.0F;

            String pat = row[21] != null ? row[21].toString().trim() : "";
            pat = StringUtils.replace(pat, ",", "");
            float patFloat = !pat.isEmpty() && !"-".equals(pat) ? Float.parseFloat(pat) : 0.0F;

            String recentQtr = row[22] != null ? row[22].toString().trim() : "";

            String priceDate = row[23] != null ? row[23].toString().trim() : "";
            String priceSrc = row[24] != null ? row[24].toString().trim() : "";
            String companyDesc = row[25] != null ? row[25].toString().trim() : "";
            String _3YrEpsGrowth = row[26] != null ? row[26].toString().trim() : "";

            float _3YrEpsGrowthFloat = !_3YrEpsGrowth.isEmpty() && !"-".equals(_3YrEpsGrowth) ? Float.parseFloat(_3YrEpsGrowth) : 0.0F;
            String currency = row[27] != null ? row[27].toString().trim() : "";
            String ticker = row[28] != null ? row[28].toString().trim() : "";
            CompanyDetails companyDetails = new CompanyDetails(companyId, companyName, mcap, sector, cmp, cmpFloat, ltp, ltpFloat, pe,
                    peFloat, pb, pbFloat, divYeild, divYeildFloat,
                    epsTtm, epsTtmFloat, _52wLow, _52wLowFloat, _52wHigh, _52wHighFloat, beta, betaFloat, asOfDate, shareOutStanding,
                    shareOutStandingFloat,
                    closePrice, closePriceFloat, mktCap, revenue, revenueFloat, faceValue, faceValueFloat, bvShare, bvShareFloat, roe,
                    roeFloat, pat,
                    patFloat, recentQtr, priceDate,
                    companyDesc, _3YrEpsGrowth, _3YrEpsGrowthFloat, currency, ticker);
            companyDetailsList.add(companyDetails);
        }
        return companyDetailsList;
    }

    @SuppressWarnings("unchecked")
    EarningPreviewDetails findLatestEarningPreview(String companyId) {
        SQLQuery sqlQuery = commonDao.getNativeQuery(EARNING_PREVIEW_QUERY, new String[] { companyId });
        List<Object[]> list = sqlQuery.list();
        EarningPreviewDetails earningPreviewDetails = null;
        if (list.size() != 0) {

            for (Object[] row : list) {
                String period = row[1] != null ? row[1].toString().trim() : "";

                String revenue = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() : "";
                float revenueFloat = !revenue.isEmpty() ? Float.parseFloat(revenue) : 0.0F;

                String operatingProfitMargin = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() : "";
                float operatingProfitMarginFloat = !operatingProfitMargin.isEmpty() ? Float.parseFloat(operatingProfitMargin) : 0.0F;

                String pat = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() : "";
                float patFloat = !pat.isEmpty() ? Float.parseFloat(pat) : 0.0F;

                String eps = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() : "";
                float epsFloat = !eps.isEmpty() ? Float.parseFloat(eps) : 0.0F;

                String netOperatingCashFlow = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() : "";
                float netOperatingCashFlowFloat = !netOperatingCashFlow.isEmpty() ? Float.parseFloat(netOperatingCashFlow) : 0.0F;

                String roe = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() : "";
                float roeFloat = !roe.isEmpty() ? Float.parseFloat(roe) : 0.0F;

                String de = row[11] != null && !StringUtils.isEmpty(row[11].toString()) && !"-".equals(row[11].toString()) ? row[11].toString().trim() : "";
                float deFloat = !de.isEmpty() ? Float.parseFloat(de) : 0.0F;

                String currentAsset = row[12] != null && !StringUtils.isEmpty(row[12].toString()) && !"-".equals(row[12].toString()) ? row[12].toString().trim() : "";
                float currentAssetFloat = !currentAsset.isEmpty() ? Float.parseFloat(currentAsset) : 0.0F;

                String currentLiabilities = row[13] != null && !StringUtils.isEmpty(row[13].toString()) && !"-".equals(row[13].toString()) ? row[13].toString().trim() : "";
                float currentLiabilitiesFloat = !currentLiabilities.isEmpty() ? Float.parseFloat(currentLiabilities) : 0.0F;

                String bvShare = row[14] != null && !StringUtils.isEmpty(row[14].toString()) && !"-".equals(row[14].toString()) ? row[14].toString().trim() : "";
                float bvShareFloat = !bvShare.isEmpty() ? Float.parseFloat(bvShare) : 0.0F;

                String totalFreeCashflow = row[15] != null && !StringUtils.isEmpty(row[15].toString()) && !"-".equals(row[15].toString()) ? row[15].toString().trim() : "";
                float totalFreeCashflowFloat = !totalFreeCashflow.isEmpty() ? Float.parseFloat(totalFreeCashflow) : 0.0F;

                String rndExpense = row[16] != null && !StringUtils.isEmpty(row[16].toString()) && !"-".equals(row[16].toString()) ? row[16].toString().trim() : "";
                float rndExpenseFloat = 0.0F;

                String totalDebt = row[17] != null && !StringUtils.isEmpty(row[17].toString()) && !"-".equals(row[17].toString()) ? row[17].toString().trim() : "";
                float totalDebtFloat = !totalDebt.isEmpty() ? Float.parseFloat(totalDebt) : 0.0F;

                String longTermDebt = row[18] != null && !StringUtils.isEmpty(row[18].toString()) && !"-".equals(row[18].toString()) ? row[18].toString().trim() : "";
                float longTermDebtFloat = !longTermDebt.isEmpty() ? Float.parseFloat(longTermDebt) : 0.0F;

                String retainedEarning = row[19] != null && !StringUtils.isEmpty(row[19].toString()) && !"-".equals(row[19].toString()) ? row[19].toString().trim() : "";
                float retainedEarningFloat = !retainedEarning.isEmpty() ? Float.parseFloat(retainedEarning) : 0.0F;

                String totalCapital = row[20] != null && !StringUtils.isEmpty(row[20].toString()) && !"-".equals(row[20].toString()) ? row[20].toString().trim() : "";
                float totalCapitalFloat = !totalCapital.isEmpty() ? Float.parseFloat(totalCapital) : 0.0F;

                String cashAndCashEquiv = row[21] != null && !StringUtils.isEmpty(row[21].toString()) && !"-".equals(row[21].toString()) ? row[21].toString().trim() : "";
                float cashAndCashEquivFloat = !cashAndCashEquiv.isEmpty() ? Float.parseFloat(cashAndCashEquiv) : 0.0F;

                String totalAssets = row[22] != null && !StringUtils.isEmpty(row[22].toString()) && !"-".equals(row[22].toString()) ? row[22].toString().trim() : "";
                float totalAssetsFloat = !totalAssets.isEmpty() ? Float.parseFloat(totalAssets) : 0.0F;

                String avgTotalAssets = row[23] != null && !StringUtils.isEmpty(row[23].toString()) && !"-".equals(row[23].toString()) ? row[23].toString().trim() : "";
                float avgTotalAssetsFloat = !avgTotalAssets.isEmpty() ? Float.parseFloat(avgTotalAssets) : 0.0F;

                Map<String, Float> last_N_years_epsGrowth = findLastNYearEPSGrowth(companyId);
                float latestEpsGrowthInPercentageAsFloat = last_N_years_epsGrowth.get("Y_1");

                earningPreviewDetails = new EarningPreviewDetails(period, revenue, revenueFloat, operatingProfitMargin,
                        operatingProfitMarginFloat, pat, patFloat, eps, epsFloat, netOperatingCashFlow, netOperatingCashFlowFloat, roe,
                        roeFloat, de, deFloat, currentAsset,
                        currentAssetFloat, currentLiabilities, currentLiabilitiesFloat, bvShare, bvShareFloat, totalFreeCashflow,
                        totalFreeCashflowFloat,
                        rndExpenseFloat, totalDebt, totalDebtFloat, longTermDebt, longTermDebtFloat, retainedEarning, retainedEarningFloat,
                        totalCapital,
                        totalCapitalFloat, cashAndCashEquiv, cashAndCashEquivFloat, totalAssets, totalAssetsFloat, avgTotalAssets,
                        avgTotalAssetsFloat, rndExpense, latestEpsGrowthInPercentageAsFloat);
            }
        }
        return earningPreviewDetails;
    }

    boolean isNYearEpsGrowthPositive(String stockId) {
        boolean positive5YrEPSGrowth = true;
        Map<String, Float> last_n_years_epsGrowth = findLastNYearEPSGrowth(stockId);
        if (last_n_years_epsGrowth.size() == 0) {
            positive5YrEPSGrowth = false;
        }
        else {
            for (Map.Entry<String, Float> entry : last_n_years_epsGrowth.entrySet()) {
                positive5YrEPSGrowth = entry.getValue() > 0.0F;
                if (!positive5YrEPSGrowth) {
                    break;
                }
            }
        }
        return positive5YrEPSGrowth;
    }

    void deleteAllRecordsFromStrategyTable(String strategyType, String deleteQuery1) {
        SQLQuery deleteQuery = commonDao.getNativeQuery(deleteQuery1, null);
        int count = deleteQuery.executeUpdate();
        logger.info("{} - DELETED ALL RECORED COUNT: {}",strategyType,count);
    }
}

