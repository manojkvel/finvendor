package com.finvendor.api.resources.screener.stock.strategies.custom.dao;

import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomStrategyDao {
    private static final Logger logger = LoggerFactory.getLogger(CustomStrategyDao.class.getName());

    private static final String QUERY = "select * from strategy_custom";
    private static final String MCAP_QUERY = "select MIN(CAST((c.shares_outstanding*d.close_price) as DECIMAL)), MAX(CAST((c.shares_outstanding*d.close_price) as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";
    private static final String PE_QUERY = "select MIN(cast(c.pe as DECIMAL)),MAX(cast(c.pe as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";
    private static final String PB_QUERY = "select MIN(CAST(b.book_value_per_share as DECIMAL)),MAX(CAST(b.book_value_per_share as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";
    private static final String DE_QUERY = "select MIN(CAST((b.de) as DECIMAL)), MAX(CAST((b.de) as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";
    private static final String CURRENT_RATIO_QUERY = "select MIN(CAST((b.current_asset/b.current_liabilities) as DECIMAL)), MAX(CAST((b.current_asset/b.current_liabilities) as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";

    private static final String NET_OPERATING_CASH_FLOW_QUERY = "SELECT a.stock_id,a.period, a.net_operating_cash_flow FROM earning_preview_yearly AS a WHERE (SELECT COUNT(*) FROM earning_preview_yearly AS b WHERE b.stock_id = a.stock_id AND STR_TO_DATE(b.period, '%b_%y') >= STR_TO_DATE(a.period, '%b_%y')) <= 1 ORDER BY cast(a.net_operating_cash_flow as decimal) ASC";
    private static final String OPERATING_PROFIT_MARGIN_QUERY = "SELECT a.stock_id,a.period, a.operating_profit_margin FROM earning_preview_yearly AS a WHERE (SELECT COUNT(*) FROM earning_preview_yearly AS b WHERE b.stock_id = a.stock_id AND STR_TO_DATE(b.period, '%b_%y') >= STR_TO_DATE(a.period, '%b_%y')) <= 1 ORDER BY cast(a.operating_profit_margin as decimal) ASC";

    private static final String ROE_QUERY = "select a.stock_id,a.period,a.roe from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String PAT_QUERY = "select a.stock_id,a.period,a.profit_after_tax from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String EPS_GROWTH_QUERY = "select a.stock_id,a.period,a.eps from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String REVENUE_QUERY = "select a.stock_id,a.period,a.revenue from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";

    private static final String TOTAL_FREE_CASH_FLOW_QUERY = "select MIN(CAST((b.total_free_cashflow) as DECIMAL)), MAX(CAST((b.total_free_cashflow) as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";
    private static final String DIV_YIELD_QUERY = "select MIN(CAST((c.dividend_yield) as DECIMAL)), MAX(CAST((c.dividend_yield) as DECIMAL)) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id";
    private static final String RETURN_ON_ASSET_QUERY = "select a.stock_id, a.period,a.profit_after_tax,b.avg_total_assets, (cast(a.profit_after_tax as decimal)/cast(b.avg_total_assets as decimal))*100 'ReturnOnAsset%' from earning_preview_yearly a, earning_preview_as_of_date b where a.stock_id=b.stock_id  order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String ROTC_QUERY = "select a.stock_id,a.period,a.profit_after_tax,b.total_capital, ROUND((a.profit_after_tax/b.total_capital),2)*100 'ROTC%' from earning_preview_yearly a, earning_preview_as_of_date b where a.stock_id = b.stock_id  order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";

    @Autowired
    protected ICommonDao commonDao;

    //MIN MAX
    public Pair findMcapFilter() {
        logger.info("findMcapFilter - START");
        return findMinMax(MCAP_QUERY);
    }

    //MIN MAX
    public Pair findPEFilter() {
        logger.info("findPEFilter - START");
        return findMinMax(PE_QUERY);
    }

    //MIN MAX
    public Pair findPBFilter() {
        logger.info("findPBFilter - START");
        return findMinMax(PB_QUERY);
    }

    /**
     * Debt to Equity Ratio
     */
    //MIN MAX
    public Pair findDEFilter() {
        logger.info("findDEFilter - START");
        return findMinMax(DE_QUERY);
    }

    //MIN MAX
    public Pair findCurrentRatioFilter() {
        logger.info("findCurrentRatioFilter - START");
        return findMinMax(CURRENT_RATIO_QUERY);
    }

    public Pair findNetOperatingCashFlowFilter() {
        logger.info("findNetOperatingCashFlowFilter - START");
        return findFistAndLast(NET_OPERATING_CASH_FLOW_QUERY);
    }

    public Pair findOperatingProfitMarginFilter() {
        logger.info("findOperatingProfitMarginFilter - START");
        return findFistAndLast(OPERATING_PROFIT_MARGIN_QUERY);
    }

    /**
     * ROE
     */
    public Pair findROEFilter() {
        logger.info("findROEFilter - START");
        return findMinMax_For_Roe_Or_Pat_Or_Eps_Or_Revenue(ROE_QUERY);
    }

    /**
     * PAT
     */
    public Pair findPATFilter() {
        logger.info("findPATFilter - START");
        return findMinMax_For_Roe_Or_Pat_Or_Eps_Or_Revenue(PAT_QUERY);
    }

    /**
     * EPS GROWTH
     */
    public Pair findEPSFilter() {
        logger.info("findEPSFilter - START");
        return findMinMax_For_Roe_Or_Pat_Or_Eps_Or_Revenue(EPS_GROWTH_QUERY);
    }

    /**
     * REVENUE
     */
    public Pair findRevenueFilter() {
        logger.info("findRevenueFilter - START");
        return findMinMax_For_Roe_Or_Pat_Or_Eps_Or_Revenue(REVENUE_QUERY);
    }

    /**
     * Total Free Cash Flow
     */
    //MIN MAX
    public Pair findTotalFreeCashFlowFilter() {
        logger.info("findTotalFreeCashFlowFilter - START");
        return findMinMax(TOTAL_FREE_CASH_FLOW_QUERY);
    }

    /**
     * Dividend Yield
     */
    //MIN MAX
    public Pair findDivYieldFilter() {
        logger.info("findDivYieldFilter - START");
        return findMinMax(DIV_YIELD_QUERY);
    }

    /**
     * Return On Asset
     */
    public Pair findReturnOnAssetFilter() {
        logger.info("findReturnOnAssetFilter - START");
        return findMinMax_For_ReturnOnAsset_or_ROTC(RETURN_ON_ASSET_QUERY);
    }

    /**
     * Return on Total Capital
     */
    public Pair findROTCFilter() {
        logger.info("findROTCFilter - START");
        return findMinMax_For_ReturnOnAsset_or_ROTC(ROTC_QUERY);
    }

    private Pair findMinMax(String queryString) {
        SQLQuery query = commonDao.getNativeQuery(queryString, null);
        List<Object[]> rows = query.list();
        String min = "";
        String max = "";
        //loop is of size-1
        for (Object[] row : rows) {
            min = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ?
                    row[0].toString().trim() :
                    "0.0";
            max = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ?
                    row[1].toString().trim() :
                    "0.0";
        }
        logger.info("DB Min:{}", min);
        logger.info("DB Max:{}", max);

        return getAbsoluteMinMaxPair(min, max);
    }

    private Pair findFistAndLast(String queryString) {
        SQLQuery query = commonDao.getNativeQuery(queryString, null);
        List<Object[]> rows = query.list();
        String min = "0.0";
        for (Object[] row : rows) {
            if ((row[2].toString().length() == 1 && row[2].toString().contains("-"))) {
            }
            else {
                min = rows.get(0)[2].toString();
                break;
            }
        }
        String max = "0.0";
        for (int i = rows.size() - 1; i >= 0; i--) {
            if ((Arrays.toString(rows.get(i)).length() == 1 && Arrays.toString(rows.get(i)).contains("-"))) {
            }
            else {
                max = rows.get(i)[2].toString();
                break;
            }
        }

        logger.info("DB min: {}", min);
        logger.info("DB max: {}", max);

        return getAbsoluteMinMaxPair(min, max);
    }

    private Pair findMinMax_For_Roe_Or_Pat_Or_Eps_Or_Revenue(String arbitraryQuery) {
        SQLQuery query = commonDao.getNativeQuery(arbitraryQuery, null);
        List<Object[]> rows = query.list();
        List<Float> percentageAvgTmpList = new ArrayList<>();

        int c = 1;
        float sum = 0.0F;
        for (Object[] row : rows) {
            float temp;
            if (c <= 3) {
                String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ?
                        row[0].toString().trim() : "0";
                temp = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ?
                        Float.parseFloat(row[2].toString().trim()) : 0.0F;
                sum += temp;
            }
            else if (c == 4) {
                float avgTmp = sum / 3.0f; //-471.86, -157.286666
                percentageAvgTmpList.add(Float.parseFloat(String.format ("%.20f", (avgTmp * 100.0F))));
            }
            else if (c == 5) {
                c = 0;
                sum = 0.0F;
            }
            c++;
        }
        sortFloats(percentageAvgTmpList);

        String minStr = String.valueOf(percentageAvgTmpList.get(0));
        String maxStr = String.valueOf(percentageAvgTmpList.get(percentageAvgTmpList.size() - 1));

        logger.info("DB Min:{}", minStr);
        logger.info("DB Max:{}", maxStr);

        return getAbsoluteMinMaxPair(minStr, maxStr);
    }

    private Pair findMinMax_For_ReturnOnAsset_or_ROTC(String arbitraryQuery) {
        SQLQuery query = commonDao.getNativeQuery(arbitraryQuery, null);
        List<Object[]> rows = query.list();
        List<Float> percentageAvgTmpList = new ArrayList<>();

        int c = 1;
        for (Object[] row : rows) {
            float temp;
            if (c <= 1) {
                String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ?
                        row[0].toString().trim() : "0";
                temp = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ?
                        Float.parseFloat(row[4].toString().trim()) : 0.0F;
                percentageAvgTmpList.add(Float.parseFloat(String.format ("%.30f", temp)));
            }
            else if (c == 5) {
                c = 0;
            }
            c++;
        }
        sortFloats(percentageAvgTmpList);

        String min = String.valueOf(percentageAvgTmpList.get(0));
        String max = String.valueOf(percentageAvgTmpList.get(percentageAvgTmpList.size() - 1));

        logger.info("DB Min:{}", min);
        logger.info("DB Max:{}", max);

        return getAbsoluteMinMaxPair(min, max);
    }

    private Pair getAbsoluteMinMaxPair(String min, String max) {
        String minStr = min.trim().length() == 1 && min.trim().contains("-") ? "0.0" : min.trim();
        String maxStr = max.trim().length() == 1 && max.trim().contains("-") ? "0.0" : max.trim();
        float minFloat = Float.parseFloat(minStr);
        float maxFloat = Float.parseFloat(maxStr);

        long minLong;
        long maxLong;

        if (minFloat < 0.0F) {
            if (Float.parseFloat(minStr.substring(minStr.indexOf(".") + 1)) == 0.0F) {
                minLong = (((long) minFloat));
            }
            else {
                minLong = ((long) minFloat) - 1;
            }
        }
        else {
            minLong = ((long) minFloat);
        }

        if (maxFloat < 0.0F) {
            maxLong = ((long) maxFloat);
        }
        else {
            if (Float.parseFloat(maxStr.substring(maxStr.indexOf(".") + 1)) == 0.0F || !maxStr.contains(".")) {
                maxLong = (((long) maxFloat));
            }
            else {
                maxLong = (((long) maxFloat) + 1);
            }
        }
        String minLongStr = String.valueOf(minLong);
        String maxLongStr = String.valueOf(maxLong);

        logger.info("Absolute Min: {}", minLongStr);
        logger.info("Absolute Max: {}", maxLongStr);

        return new Pair<>(minLongStr, maxLongStr);
    }

    private void sortFloats(List<Float> percentageAvgTmpList) {
        Collections.sort(percentageAvgTmpList, new Comparator<Float>() {
            @Override public int compare(Float o1, Float o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public String findRecordStats(String perPageMaxRecords, String[] mcap, String[] industry, String[] pe, String[] pb,
            String[] debtToEquityRatio, String[] currentRatio, String[] netOperatingCashFlow, String[] roeInPercentage,
            String[] operatingProfitMargin, String[] patGrowthInPercentage, String[] epsGrowthInPercentage,
            String[] revenueGrowthInPercentage, String[] totalFreeCashFlow, String[] returnOnAssetInPercentage, String[] divYield,
            String[] rotcInPercentage) {
        SQLQuery query = commonDao.getNativeQuery(QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    public static void main(String[] args) {
        float f = -2678.89f;//-2679

        //min=-12.23=-13, 1.23=1

        //max=-12.23=-12, 1.23=2

        System.out.println(((long) -12.23) - 1);
        System.out.println(((long) 1.23));

        System.out.println(((long) -12.23));
        System.out.println(((long) 1.23) + 1);

    }
}
