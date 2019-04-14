package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.InflationRate;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.NetProfitMargin;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.dff.DffProcesFeed;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScreenerFeed implements DffProcesFeed {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractScreenerFeed.class.getName());

    private static final String RESEARCH_AREA = "7";
    private static final String COUNTRY_ID = "1";

    private static final String COMPANY_DETAILS_QUERY = "SELECT distinct rsch_sub_area_company_dtls.company_id companyId, rsch_sub_area_company_dtls.company_name companyName,market_cap_def.market_cap_name mcap,research_sub_area.description           sector,stock_current_prices.close_price        cmp,stock_current_prices.last_trade_price   ltp,stock_current_info.pe,stock_current_info.pb,stock_current_info.dividend_yield,stock_current_info.eps_ttm,stock_current_info.52w_high,stock_current_info.52w_low,stock_current_info.beta,stock_current_info.as_of_date,stock_current_info.shares_outstanding,stock_current_info.shares_outstanding*stock_current_prices.close_price mktCap,stock_current_info.mkt_cap,stock_current_info.revenue,stock_current_info.face_value,stock_current_info.bv_share,stock_current_info.roe,stock_current_info.pat,stock_current_info.recent_qtr,stock_current_prices.price_date,stock_current_prices.price_src_code,rsch_sub_area_company_dtls.company_desc,stock_current_info.3_yr_eps_growth,rsch_sub_area_company_dtls.currency FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type,research_sub_area,stock_current_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = ? AND country.country_id = ? order by CAST(rsch_sub_area_company_dtls.company_id as UNSIGNED)";
    private static final String EARNING_PREVIEW_QUERY = "select earning_preview_yearly.*,earning_preview_as_of_date.* from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id AND earning_preview_yearly.stock_id = ? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 1 offset 0";
    private static final String INFLATION_RATE_QUERY = "select earning_preview_inflation_rate.* from earning_preview_inflation_rate where earning_preview_inflation_rate.stock_id = ? order by STR_TO_DATE(earning_preview_inflation_rate.period, '%b_%y') desc limit 1 offset 0";
    private static final String NET_PROFIT_MARGIN_QUERY = "select earning_preview_netprofit_margin.stock_id, earning_preview_netprofit_margin.period, earning_preview_netprofit_margin.netprofit_margin from earning_preview_netprofit_margin where earning_preview_netprofit_margin.stock_id =? order by STR_TO_DATE(earning_preview_netprofit_margin.period, '%b_%y') desc limit 3 offset 0";
    private static final String POSITVE_EPS_GROWTH = "select earning_preview_yearly.eps_growth,earning_preview_yearly.period from earning_preview_yearly where earning_preview_yearly.stock_id = ? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 5 offset 0;";

    @Autowired
    protected ICommonDao commonDao;

    @SuppressWarnings("unchecked")
    List<CompanyDetails> findCompanyDetails() {
        logger.info("findCompanyDetails()-> COMPANY_DETAILS_QUERY Query:{}", COMPANY_DETAILS_QUERY);
        SQLQuery sqlQuery = commonDao.getNativeQuery(COMPANY_DETAILS_QUERY, new String[] { RESEARCH_AREA, COUNTRY_ID });
        logger.info("Company details query: {}", COMPANY_DETAILS_QUERY);
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
            logger.info("_3YrEpsGrowthFloat:{}", _3YrEpsGrowth);
            float _3YrEpsGrowthFloat = !_3YrEpsGrowth.isEmpty() && !"-".equals(_3YrEpsGrowth) ? Float.parseFloat(_3YrEpsGrowth) : 0.0F;
            String currency = row[27] != null ? row[27].toString().trim() : "";
            CompanyDetails companyDetails = new CompanyDetails(companyId, companyName, mcap, sector, cmp, cmpFloat, ltp, ltpFloat, pe,
                    peFloat, pb, pbFloat, divYeild, divYeildFloat,
                    epsTtm, epsTtmFloat, _52wLow, _52wLowFloat, _52wHigh, _52wHighFloat, beta, betaFloat, asOfDate, shareOutStanding,
                    shareOutStandingFloat,
                    closePrice, closePriceFloat, mktCap, revenue, revenueFloat, faceValue, faceValueFloat, bvShare, roe, roeFloat, pat,
                    patFloat, recentQtr, priceDate,
                    companyDesc, _3YrEpsGrowth, _3YrEpsGrowthFloat, currency);
            companyDetailsList.add(companyDetails);
        }
        return companyDetailsList;
    }

    @SuppressWarnings("unchecked")
    EarningPreviewDetails findEarningPreview(String companyId) {
        logger.info("findEarningPreview()-> EARNING_PREVIEW_QUERY Query:{}", EARNING_PREVIEW_QUERY);
        logger.info("findEarningPreview()-> companyId: {}", companyId);
        SQLQuery sqlQuery = commonDao.getNativeQuery(EARNING_PREVIEW_QUERY, new String[] { companyId });
        List<Object[]> list = sqlQuery.list();
        EarningPreviewDetails earningPreviewDetails = null;
        if (list.size() != 0) {

            for (Object[] row : list) {
                String period = row[1] != null ? row[1].toString().trim() : "";

                String revenue = row[2] != null ? row[2].toString().trim() : "";
                float revenueFloat = !revenue.isEmpty() ? Float.parseFloat(revenue) : 0.0F;

                String operatingProfitMargin = row[3] != null ? row[3].toString().trim() : "";
                float operatingProfitMarginFloat = !operatingProfitMargin.isEmpty() ? Float.parseFloat(operatingProfitMargin) : 0.0F;

                String pat = row[4] != null ? row[4].toString().trim() : "";
                float patFloat = !pat.isEmpty() ? Float.parseFloat(pat) : 0.0F;

                String eps = row[5] != null ? row[5].toString().trim() : "";
                float epsFloat = !eps.isEmpty() ? Float.parseFloat(eps) : 0.0F;

                String epsGrowth = row[6] != null ? row[6].toString().trim() : "";
                float epsGrowthFloat = !epsGrowth.isEmpty() ? Float.parseFloat(epsGrowth) : 0.0F;

                String netOperatingCashFlow = row[7] != null ? row[7].toString().trim() : "";
                float netOperatingCashFlowFloat = !netOperatingCashFlow.isEmpty() ? Float.parseFloat(netOperatingCashFlow) : 0.0F;

                String roe = row[8] != null ? row[8].toString().trim() : "";
                float roeFloat = !roe.isEmpty() ? Float.parseFloat(roe) : 0.0F;

                String de = row[12] != null ? row[12].toString().trim() : "";
                float deFloat = !de.isEmpty() ? Float.parseFloat(de) : 0.0F;

                String currentAsset = row[13] != null ? row[13].toString().trim() : "";
                float currentAssetFloat = !currentAsset.isEmpty() ? Float.parseFloat(currentAsset) : 0.0F;

                String currentLiabilities = row[14] != null ? row[14].toString().trim() : "";
                float currentLiabilitiesFloat = !currentLiabilities.isEmpty() ? Float.parseFloat(currentLiabilities) : 0.0F;

                String bvShare = row[15] != null ? row[15].toString().trim() : "";
                float bvShareFloat = !bvShare.isEmpty() ? Float.parseFloat(bvShare) : 0.0F;

                String totalFreeCashflow = row[16] != null ? row[16].toString().trim() : "";
                float totalFreeCashflowFloat = !totalFreeCashflow.isEmpty() ? Float.parseFloat(totalFreeCashflow) : 0.0F;

                String rndExpense = row[17] != null ? row[17].toString().trim() : "";
                float rndExpenseFloat = 0.0F;

                String totalDebt = row[18] != null ? row[18].toString().trim() : "";
                float totalDebtFloat = !totalDebt.isEmpty() ? Float.parseFloat(totalDebt) : 0.0F;

                String longTermDebt = row[19] != null ? row[19].toString().trim() : "";
                float longTermDebtFloat = !longTermDebt.isEmpty() ? Float.parseFloat(longTermDebt) : 0.0F;

                String retainedEarning = row[20] != null ? row[20].toString().trim() : "";
                float retainedEarningFloat = !retainedEarning.isEmpty() ? Float.parseFloat(retainedEarning) : 0.0F;

                String totalCapital = row[21] != null ? row[21].toString().trim() : "";
                float totalCapitalFloat = !totalCapital.isEmpty() ? Float.parseFloat(totalCapital) : 0.0F;

                String cashAndCashEquiv = row[22] != null ? row[22].toString().trim() : "";
                float cashAndCashEquivFloat = !cashAndCashEquiv.isEmpty() ? Float.parseFloat(cashAndCashEquiv) : 0.0F;

                String totalAssets = row[23] != null ? row[23].toString().trim() : "";
                float totalAssetsFloat = !totalAssets.isEmpty() ? Float.parseFloat(totalAssets) : 0.0F;

                String avgTotalAssets = row[24] != null ? row[24].toString().trim() : "";
                float avgTotalAssetsFloat = !avgTotalAssets.isEmpty() ? Float.parseFloat(avgTotalAssets) : 0.0F;
                earningPreviewDetails = new EarningPreviewDetails(period, revenue, revenueFloat, operatingProfitMargin,
                        operatingProfitMarginFloat, pat, patFloat, eps, epsFloat, netOperatingCashFlow, netOperatingCashFlowFloat, roe,
                        roeFloat, de, deFloat, currentAsset,
                        currentAssetFloat, currentLiabilities, currentLiabilitiesFloat, bvShare, bvShareFloat, totalFreeCashflow,
                        totalFreeCashflowFloat,
                        rndExpenseFloat, totalDebt, totalDebtFloat, longTermDebt, longTermDebtFloat, retainedEarning, retainedEarningFloat,
                        totalCapital,
                        totalCapitalFloat, cashAndCashEquiv, cashAndCashEquivFloat, totalAssets, totalAssetsFloat, avgTotalAssets,
                        avgTotalAssetsFloat, epsGrowth, epsGrowthFloat, rndExpense);
            }
        }
        return earningPreviewDetails;
    }

    @SuppressWarnings("unchecked")
    InflationRate findInflationRate(String companyId) {
        logger.info("findInflationRate()-> INFLATION_RATE_QUERY Query:{}", INFLATION_RATE_QUERY);
        logger.info("findInflationRate()-> companyId: {}", companyId);
        InflationRate inflationRate;
        SQLQuery sqlQuery = commonDao.getNativeQuery(INFLATION_RATE_QUERY, new String[] { companyId });
        List<Object[]> list = sqlQuery.list();
        if (list.size() != 0) {
            inflationRate = new InflationRate();
            for (Object[] row : list) {
                String inflationRt = (row[2] != null && !"-".equals(row[2])) ? row[2].toString().trim() : null;
                float inflationRateFloat = inflationRt != null ? Float.parseFloat(inflationRt) : 0.0F;
                inflationRate.setInflationRate(inflationRt);
                inflationRate.setInflationRateFloat(inflationRateFloat);
            }
        }
        else {
            inflationRate = null;
        }
        return inflationRate;
    }

    @SuppressWarnings("unchecked")
    NetProfitMargin findAvgNetProfitMargin(String companyId) {
        logger.info("findAvgNetProfitMargin()-> NET_PROFIT_MARGIN_QUERY Query:{}", NET_PROFIT_MARGIN_QUERY);
        logger.info("findAvgNetProfitMargin()-> companyId: {}", companyId);
        NetProfitMargin avgNetProfitMargin;
        SQLQuery sqlQuery = commonDao.getNativeQuery(NET_PROFIT_MARGIN_QUERY, new String[] { companyId });
        List<Object[]> list = sqlQuery.list();
        if (list.size() != 0) {
            float sum = 0.0F;
            avgNetProfitMargin = new NetProfitMargin();
            for (Object[] row4 : list) {
                String netProfitMargin = (row4[2] != null && !"-".equals(row4[2])) ? row4[2].toString().trim() : null;
                sum += netProfitMargin != null ? Float.parseFloat(netProfitMargin) : 0.0F;
            }
            float _3YrAvgNetProfitMarginFloat = sum / 3;
            avgNetProfitMargin.setAvgNetProfitMargin(String.valueOf(_3YrAvgNetProfitMarginFloat));
            avgNetProfitMargin.setAvgNetProfitMarginFloat(_3YrAvgNetProfitMarginFloat);
        }
        else {
            avgNetProfitMargin = null;
        }
        return avgNetProfitMargin;
    }

    boolean is5YearEpsGrowthPositive(String stockId) {
        boolean positive5YrEPSGrowth = false;
        logger.info("is5YearEpsGrowthPositive()-> POSITVE_EPS_GROWTH Query:{}", POSITVE_EPS_GROWTH);
        logger.info("is5YearEpsGrowthPositive()-> stockId Query:{}", stockId);
        SQLQuery sqlQuery = commonDao.getNativeQuery(POSITVE_EPS_GROWTH, new String[] { stockId });
        List<Object[]> list = sqlQuery.list();
        float epsGrowthFloat;
        for (Object[] row : list) {
            String epsGrowth = row[0] != null ? row[0].toString().trim() : "";
            if (!epsGrowth.isEmpty()) {
                epsGrowthFloat = Float.parseFloat(epsGrowth.trim());
                if (epsGrowthFloat < 0.0F) {
                    positive5YrEPSGrowth = false;
                    logger.info("epsGrowthFloat is -Ve :{}", epsGrowthFloat);
                    logger.info("positive5YrEPSGrowth flag:{}", positive5YrEPSGrowth);
                    break;
                }
                else {
                    positive5YrEPSGrowth = true;
                }
            }
            else {
                positive5YrEPSGrowth = false;
                logger.info("epsGrowthFloat is EMPTY");
                logger.info("positive5YrEPSGrowth flag:{}", positive5YrEPSGrowth);
                break;
            }
        }
        return positive5YrEPSGrowth;
    }
}

