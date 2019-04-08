package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.InternalFeedFacade;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.dff.DffProcesFeed;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ayush
 * @since 7-April-2019
 */
@Service
@Transactional
public class KennethFisherFeed implements DffProcesFeed {

    private static final Logger logger = LoggerFactory.getLogger(KennethFisherFeed.class.getName());

    @Autowired
    private ICommonDao commonDao;

    private static final String RESEARCH_AREA = "7";
    private static final String COUNTRY_ID = "1";

    private static final String COMPANY_INFO_QUERY = "SELECT distinct rsch_sub_area_company_dtls.company_id   companyId, rsch_sub_area_company_dtls.company_name companyName, stock_current_info.shares_outstanding*stock_current_prices.close_price mktCap FROM rsch_sub_area_company_dtls, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = ? AND country.country_id = ? order by CAST(rsch_sub_area_company_dtls.company_id as UNSIGNED)";
    private static final String EARNING_PREVIEW_QUERY = "select earning_preview_yearly.stock_id, STR_TO_DATE(earning_preview_yearly.period, '%b_%y') period, earning_preview_yearly.revenue, earning_preview_yearly.eps_growth, earning_preview_as_of_date.de, earning_preview_as_of_date.rnd_expense from earning_preview_yearly, earning_preview_as_of_date where earning_preview_yearly.stock_id = earning_preview_as_of_date.stock_id AND earning_preview_yearly.stock_id = ? order by STR_TO_DATE(earning_preview_yearly.period, '%b_%y') desc limit 1 offset 0";
    private static final String INFLATION_RATE_QUERY = "select earning_preview_inflation_rate.stock_id, earning_preview_inflation_rate.period, earning_preview_inflation_rate.rate, earning_preview_inflation_rate.type from earning_preview_inflation_rate where earning_preview_inflation_rate.stock_id = ? order by STR_TO_DATE(earning_preview_inflation_rate.period, '%b_%y') desc limit 1 offset 0";
    private static final String NET_PROFIT_MARGIN_QUERY = "select earning_preview_netprofit_margin.stock_id, earning_preview_netprofit_margin.period, earning_preview_netprofit_margin.netprofit_margin from earning_preview_netprofit_margin where earning_preview_netprofit_margin.stock_id =? order by STR_TO_DATE(earning_preview_netprofit_margin.period, '%b_%y') desc limit 3 offset 0";
    private static final String INSERT_QUERY = "insert into strategy_kenneth_fisher values(?,?,?,?,?,?,?,?,?,?)";

    @Override
    public boolean processAndFeed() throws Exception {
        SQLQuery sqlQuery = commonDao.getNativeQuery(COMPANY_INFO_QUERY, new String[] { RESEARCH_AREA, COUNTRY_ID });
        List<Object[]> rows1 = sqlQuery.list();
        for (Object[] row1 : rows1) {
            String companyId = row1[0] != null ? row1[0].toString().trim() : null;
            String companyName = row1[1] != null ? row1[1].toString().trim() : null;
            String mcap = row1[2] != null ? row1[2].toString().trim() : null;
            float mcapFloat = mcap != null ? Float.parseFloat(mcap) : 0.0F;

            String revenue = null;
            float revenueFloat = 0.0f;

            String epsGrowth = null;
            float epsGrowthFloat = 0.0F;

            String de = null;
            float deFloat = 0.0F;

            String rndExpense = null;
            float rndExpenseFloat = 0.0F;

            String inflationRate = null;
            float inflationRateFloat = 0.0F;

            float _3YrAvgNetProfitMarginFloat = 0.0F;
            String _3YrAvgNetProfitMarginStr;

            sqlQuery = commonDao.getNativeQuery(EARNING_PREVIEW_QUERY, new String[] { companyId });
            List<Object[]> rows2 = sqlQuery.list();
            if (rows2.size() != 0) {
                for (Object[] row2 : rows2) {
                    revenue = (row2[2] != null && !"-".equals(row2[2])) ? row2[2].toString().trim() : null;
                    revenueFloat = revenue != null ? Float.parseFloat(revenue) : 0.0F;

                    epsGrowth = (row2[3] != null && !"-".equals(row2[3])) ? row2[3].toString().trim() : null;
                    epsGrowthFloat = epsGrowth != null ? Float.parseFloat(epsGrowth) : 0.0F;

                    de = (row2[4] != null && !"-".equals(row2[4])) ? row2[4].toString().trim() : null;
                    deFloat = de != null ? Float.parseFloat(de) : 0.0F;

                    rndExpense = (row2[5] != null && !"-".equals(row2[5])) ? row2[5].toString().trim() : null;
                    rndExpenseFloat = rndExpense != null ? Float.parseFloat(rndExpense) : 0.0F;
                }
                sqlQuery = commonDao.getNativeQuery(INFLATION_RATE_QUERY, new String[] { companyId });
                List<Object[]> rows3 = sqlQuery.list();
                if (rows3.size() != 0) {
                    for (Object[] row3 : rows3) {
                        inflationRate = (row3[2] != null && !"-".equals(row3[2])) ? row3[2].toString().trim() : null;
                        inflationRateFloat = inflationRate != null ? Float.parseFloat(inflationRate) : 0.0F;
                    }
                    sqlQuery = commonDao.getNativeQuery(NET_PROFIT_MARGIN_QUERY, new String[] { companyId });
                    List<Object[]> rows4 = sqlQuery.list();
                    float sum = 0.0F;
                    for (Object[] row4 : rows4) {
                        String netProfitMargin = (row4[2] != null && !"-".equals(row4[2])) ? row4[2].toString().trim() : null;
                        sum += netProfitMargin != null ? Float.parseFloat(netProfitMargin) : 0.0F;
                    }
                    _3YrAvgNetProfitMarginFloat = sum / 3.0F;
                    _3YrAvgNetProfitMarginStr = String.valueOf(_3YrAvgNetProfitMarginFloat);
                }
                else {
                    inflationRate = null;
                    _3YrAvgNetProfitMarginStr = null;
                }
                float psrFloat = mcapFloat / revenueFloat;
                String psr = String.valueOf(psrFloat);

                boolean condition_1 = psrFloat < 0.75f;
                boolean condition_2 = deFloat < 0.40F;
                boolean condition_3 = (epsGrowthFloat - inflationRateFloat) > 0.15F;
                boolean condition_4 = _3YrAvgNetProfitMarginFloat > 0.05F;
                boolean condition_5;
                boolean resultCondition;
                if (rndExpenseFloat != 0.0F) {
                    condition_5 = mcapFloat / rndExpenseFloat < 10.0F;
                    resultCondition = condition_1 && condition_2 && condition_3 && condition_4 && condition_5;
                }
                else {
                    resultCondition = condition_1 && condition_2 && condition_3 && condition_4;
                }
                if (resultCondition) {
                    //insert stock
                    //companyId,CompanyName, psr,mcap,revenue,de,epsGrowth,inflationRate,3YrAvgNetProfitMargin,rndExpense
                    sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);
                    sqlQuery.setParameter(0, Integer.parseInt(companyId));
                    sqlQuery.setParameter(1, companyName == null ? "-" : companyName);
                    sqlQuery.setParameter(2, psr == null ? "-" : psr);
                    sqlQuery.setParameter(3, mcap == null ? "-" : mcap);
                    sqlQuery.setParameter(4, revenue == null ? "-" : revenue);
                    sqlQuery.setParameter(5, de == null ? "-" : de);
                    sqlQuery.setParameter(6, epsGrowth == null ? "-" : epsGrowth);
                    sqlQuery.setParameter(7, inflationRate == null ? "-" : inflationRate);
                    sqlQuery.setParameter(8, _3YrAvgNetProfitMarginStr == null ? "-" : _3YrAvgNetProfitMarginStr);
                    sqlQuery.setParameter(9, rndExpense == null ? "-" : rndExpense);
                    sqlQuery.executeUpdate();
                }
            }
        }
        return false;
    }
}
