package com.finvendor.api.resources.screener.stock.strategies.custom.dao;

import com.finvendor.api.resources.screener.stock.strategies.custom.dto.CustomStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.custom.enums.FilterTypeEnum;
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

    private static final String CUSTOM_STRATEGY_QUERY = "select * from strategy_custom";

    private static final String INSERT_COMPANY_NAME_AND_STOCK_ID_QUERY = "insert into strategy_custom(stock_id,company_name) select a.stock_id,b.company_name from earning_preview a, rsch_sub_area_company_dtls b where a.stock_id=b.company_id";
    private static final String MCAP_QUERY = "select a.stock_id,CAST((c.shares_outstanding*d.close_price) as DECIMAL) mcap from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id order by a.stock_id ";
    private static final String PE_QUERY = "select a.stock_id,cast(c.pe as DECIMAL) from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id order by a.stock_id";
    private static final String PB_QUERY = "select a.stock_id, CAST(b.book_value_per_share as DECIMAL) pb from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id order by a.stock_id";
    private static final String DE_QUERY = "select a.stock_id, CAST((b.de) as DECIMAL) DE from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id order by a.stock_id";
    private static final String CURRENT_RATIO_QUERY = "select a.stock_id,(CAST((b.current_asset/b.current_liabilities) as DECIMAL)) cr from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id  order by a.stock_id";

    private static final String NET_OPERATING_CASH_FLOW_QUERY = "SELECT a.stock_id,a.net_operating_cash_flow FROM earning_preview_yearly AS a WHERE (SELECT COUNT(*) FROM earning_preview_yearly AS b WHERE b.stock_id = a.stock_id AND STR_TO_DATE(b.period, '%b_%y') >= STR_TO_DATE(a.period, '%b_%y')) <= 1 ORDER BY a.stock_id";
    private static final String OPERATING_PROFIT_MARGIN_QUERY = "SELECT a.stock_id,a.operating_profit_margin FROM earning_preview_yearly AS a WHERE (SELECT COUNT(*) FROM earning_preview_yearly AS b WHERE b.stock_id = a.stock_id AND STR_TO_DATE(b.period, '%b_%y') >= STR_TO_DATE(a.period, '%b_%y')) <= 1 ORDER BY a.stock_id";

    private static final String ROE_QUERY = "select a.stock_id,a.period,a.roe from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String PAT_QUERY = "select a.stock_id,a.period,a.profit_after_tax from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String EPS_GROWTH_QUERY = "select a.stock_id,a.period,a.eps from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String REVENUE_QUERY = "select a.stock_id,a.period,a.revenue from earning_preview_yearly a,earning_preview b where a.stock_id=b.stock_id order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";

    private static final String TOTAL_FREE_CASH_FLOW_QUERY = "select a.stock_id, CAST((b.total_free_cashflow) as DECIMAL) tfcf from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id order by stock_id";
    private static final String DIV_YIELD_QUERY = "select a.stock_id, CAST((c.dividend_yield) as DECIMAL) divy from earning_preview a,earning_preview_as_of_date b, stock_current_info c ,stock_current_prices d where a.stock_id=c.stock_id and a.stock_id=b.stock_id and c.stock_id=d.stock_id order by a.stock_id";
    private static final String RETURN_ON_ASSET_QUERY = "select a.stock_id, a.period,a.profit_after_tax,b.avg_total_assets, (cast(a.profit_after_tax as decimal)/cast(b.avg_total_assets as decimal))*100 'ReturnOnAsset%' from earning_preview_yearly a, earning_preview_as_of_date b where a.stock_id=b.stock_id  order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String ROTC_QUERY = "select a.stock_id,a.period,a.profit_after_tax,b.total_capital, ROUND((a.profit_after_tax/b.total_capital),2)*100 'ROTC%' from earning_preview_yearly a, earning_preview_as_of_date b where a.stock_id = b.stock_id  order by a.stock_id, STR_TO_DATE(a.period, '%b_%y') desc";
    private static final String INDUSTRY_QUERY = "select a.stock_id, c.description from earning_preview a, rsch_sub_area_company_dtls b, research_sub_area c where a.stock_id=b.company_id and b.rsch_sub_area_id=c.research_sub_area_id order by a.stock_id";
    private static final String INDUSTRY_FROM_CUSTOM_STRATEGY_TABLE_QUERY = "select DISTINCT a.industry,a.industry from strategy_custom a order by a.industry";

    @Autowired
    protected ICommonDao commonDao;

    public void insertCustomScreenerData() {
        try {
            insertCompanyNameAndStockId(INSERT_COMPANY_NAME_AND_STOCK_ID_QUERY);
            updateCustomScreenerTable(MCAP_QUERY, "update strategy_custom set mcap=? where stock_id=?");
            updateCustomScreenerTable(PE_QUERY, "update strategy_custom set pe=? where stock_id=?");
            updateCustomScreenerTable(PB_QUERY, "update strategy_custom set pb=? where stock_id=?");
            updateCustomScreenerTable(DE_QUERY, "update strategy_custom set debtToEquityRatio=? where stock_id=?");
            updateCustomScreenerTable(CURRENT_RATIO_QUERY, "update strategy_custom set currentRatio=? where stock_id=?");
            updateCustomScreenerTable(NET_OPERATING_CASH_FLOW_QUERY, "update strategy_custom set netOperatingCashFlow=? where stock_id=?");
            updateCustomScreenerTable(OPERATING_PROFIT_MARGIN_QUERY, "update strategy_custom set operatingProfitMargin=? where stock_id=?");
            updateCustomScreenerTable(TOTAL_FREE_CASH_FLOW_QUERY, "update strategy_custom set totalFreeCashFlow=? where stock_id=?");
            updateCustomScreenerTable(DIV_YIELD_QUERY, "update strategy_custom set divYield=? where stock_id=?");

            //insert ROE, PAT, EPS and Revenue
            updateROEOrPatOrEpsOrRevenueInCustomScreenerTable(ROE_QUERY, "update strategy_custom set roeInPercentage=? where stock_id=?");
            updateROEOrPatOrEpsOrRevenueInCustomScreenerTable(PAT_QUERY,
                    "update strategy_custom set patGrowthInPercentage=? where stock_id=?");
            updateROEOrPatOrEpsOrRevenueInCustomScreenerTable(EPS_GROWTH_QUERY,
                    "update strategy_custom set epsGrowthInPercentage=? where stock_id=?");
            updateROEOrPatOrEpsOrRevenueInCustomScreenerTable(REVENUE_QUERY,
                    "update strategy_custom set revenueGrowthInPercentage=? where stock_id=?");

            //
            updateReturnOnAssetOrROTC(RETURN_ON_ASSET_QUERY, "update strategy_custom set returnOnAssetInPercentage=? where stock_id=?");
            updateReturnOnAssetOrROTC(ROTC_QUERY, "update strategy_custom set rotcInPercentage=? where stock_id=?");

            //update industry
            updateCustomScreenerTable(INDUSTRY_QUERY, "update strategy_custom set industry=? where stock_id=?");

        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while feed custom screener data", e);
        }

    }

    private void insertCompanyNameAndStockId(String insertCompanyNameAndStockIdQuery) {
        SQLQuery insertCompanyIdAndName = commonDao.getNativeQuery(
                insertCompanyNameAndStockIdQuery, null);
        insertCompanyIdAndName.executeUpdate();
    }

    private void updateReturnOnAssetOrROTC(String findQuery, String updateSql) {
        SQLQuery query = commonDao.getNativeQuery(findQuery, null);
        List<Object[]> rows = query.list();
        int c = 1;
        for (Object[] row : rows) {
            float temp;
            int stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ?
                    Integer.parseInt(row[0].toString().trim()) : 0;
            if (c <= 1) {
                temp = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ?
                        Float.parseFloat(row[4].toString().trim()) : 0.0F;
                SQLQuery updateQuery = commonDao.getNativeQuery(updateSql, null);
                updateQuery.setString(0, String.format("%.10f", temp));
                updateQuery.setInteger(1, stockId);
                updateQuery.executeUpdate();
            }
            else if (c == 5) {
                c = 0;
            }
            c++;
        }
    }

    private void updateROEOrPatOrEpsOrRevenueInCustomScreenerTable(String findQuery, String updateSql) {
        SQLQuery query = commonDao.getNativeQuery(findQuery, null);
        List<Object[]> rows = query.list();

        int c = 1;
        float sum = 0.0F;
        for (Object[] row : rows) {
            int stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ?
                    Integer.parseInt(row[0].toString().trim()) : 0;
            float temp;
            if (c <= 3) {
                temp = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ?
                        Float.parseFloat(row[2].toString().trim()) : 0.0F;
                sum += temp;
            }
            else if (c == 4) {
                float avgTmp = sum / 3.0f; //-471.86, -157.286666
                SQLQuery updateQuery = commonDao.getNativeQuery(updateSql, null);
                updateQuery.setString(0, String.format("%.10f", (avgTmp * 100.0F)));
                updateQuery.setInteger(1, stockId);
                updateQuery.executeUpdate();
            }
            else if (c == 5) {
                c = 0;
                sum = 0.0F;
            }
            c++;
        }
    }

    private void updateCustomScreenerTable(String findSql, String updateSql) {
        SQLQuery query = commonDao.getNativeQuery(findSql, null);
        List<Object[]> rows = query.list();
        SQLQuery updateQuery;

        for (Object[] row : rows) {
            int stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ?
                    Integer.parseInt(row[0].toString().trim()) : 0;
            String value = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ?
                    row[1].toString().trim() : "0.0";

            updateQuery = commonDao.getNativeQuery(updateSql, null);
            updateQuery.setString(0, value);
            updateQuery.setInteger(1, stockId);
            updateQuery.executeUpdate();
        }
    }

    private Pair<String, String> getAbsoluteMinMaxPair(String min, String max) {
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

    public Pair<String, String> findSliderFilterData(FilterTypeEnum filterTypeEnum) {
        String min = "0.0";
        String max = "0.0";
        Pair<String, String> absoluteMinMaxPair = new Pair<>("0.0", "0.0");
        switch (filterTypeEnum) {
        case MCAP:
            logger.info("MCAP");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.mcap as DECIMAL)) min,MAX(cast(a.mcap as DECIMAL)) max  from strategy_custom a order by cast(a.mcap as DECIMAL)");
            break;
        case PE:
            logger.info("PE");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.pe as DECIMAL)) min,MAX(cast(a.pe as DECIMAL)) max  from strategy_custom a order by cast(a.pe as DECIMAL)");
            break;
        case PB:
            logger.info("PB");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.pb as DECIMAL)) min,MAX(cast(a.pb as DECIMAL)) max  from strategy_custom a order by cast(a.pb as DECIMAL)");
            break;
        case DE:
            logger.info("DE");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.debtToEquityRatio as DECIMAL)) min,MAX(cast(a.debtToEquityRatio as DECIMAL)) max  from strategy_custom a order by cast(a.debtToEquityRatio as DECIMAL)");
            break;
        case CURRENT_RATIO:
            logger.info("CURRENT_RATIO");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.currentRatio as DECIMAL)) min,MAX(cast(a.currentRatio as DECIMAL)) max  from strategy_custom a order by cast(a.currentRatio as DECIMAL)");
            break;
        case NET_OPERATING_CASH_FLOW:
            logger.info("NET_OPERATING_CASH_FLOW");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.netOperatingCashFlow as DECIMAL)) min,MAX(cast(a.netOperatingCashFlow as DECIMAL)) max  from strategy_custom a order by cast(a.netOperatingCashFlow as DECIMAL)");
            break;
        case OPERATING_PROFIT_MARGIN:
            logger.info("OPERATING_PROFIT_MARGIN");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.operatingProfitMargin as DECIMAL)) min,MAX(cast(a.operatingProfitMargin as DECIMAL)) max  from strategy_custom a order by cast(a.operatingProfitMargin as DECIMAL)");
            break;
        case ROE:
            logger.info("ROE");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.roeInPercentage as DECIMAL)) min,MAX(cast(a.roeInPercentage as DECIMAL)) max  from strategy_custom a order by cast(a.roeInPercentage as DECIMAL)");
            break;
        case PAT:
            logger.info("PAT");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.patGrowthInPercentage as DECIMAL)) min,MAX(cast(a.patGrowthInPercentage as DECIMAL)) max  from strategy_custom a order by cast(a.patGrowthInPercentage as DECIMAL)");
            break;
        case EPS:
            logger.info("EPS");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.epsGrowthInPercentage as DECIMAL)) min,MAX(cast(a.epsGrowthInPercentage as DECIMAL)) max  from strategy_custom a order by cast(a.epsGrowthInPercentage as DECIMAL)");
            break;
        case REVENUE:
            logger.info("REVENUE");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.revenueGrowthInPercentage as DECIMAL)) min,MAX(cast(a.revenueGrowthInPercentage as DECIMAL)) max  from strategy_custom a order by cast(a.revenueGrowthInPercentage as DECIMAL)");
            break;
        case TOTAL_FREE_CASH_FLOW:
            logger.info("TOTAL_FREE_CASH_FLOW");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.totalFreeCashFlow as DECIMAL)) min,MAX(cast(a.totalFreeCashFlow as DECIMAL)) max  from strategy_custom a order by cast(a.totalFreeCashFlow as DECIMAL)");
            break;
        case RETURN_ON_ASSETS:
            logger.info("RETURN_ON_ASSETS");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.returnOnAssetInPercentage as DECIMAL)) min,MAX(cast(a.returnOnAssetInPercentage as DECIMAL)) max  from strategy_custom a order by cast(a.returnOnAssetInPercentage as DECIMAL)");
            break;
        case DIV_YIELD:
            logger.info("DIV_YIELD");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.divYield as DECIMAL)) min,MAX(cast(a.divYield as DECIMAL)) max  from strategy_custom a order by cast(a.divYield as DECIMAL)");
            break;
        case ROTC:
            logger.info("ROTC");
            absoluteMinMaxPair = getAbsoluteMinMax(min, max, "select MIN(cast(a.rotcInPercentage as DECIMAL)) min,MAX(cast(a.rotcInPercentage as DECIMAL)) max  from strategy_custom a order by cast(a.rotcInPercentage as DECIMAL)");
            break;
        }
        return absoluteMinMaxPair;
    }

    private Pair<String, String> getAbsoluteMinMax(String min, String max, String sql) {
        Pair<String, String> absoluteMinMaxPair;
        SQLQuery query = commonDao.getNativeQuery(sql, null);
        List<Object[]> rows = query.list();
        for (Object[] row : rows) {
            min = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() : "0.0";
            max = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() : "0.0";
            logger.info("DB Min:{}", min);
            logger.info("DB Max:{}", max);
        }
        absoluteMinMaxPair = getAbsoluteMinMaxPair(min, max);
        return absoluteMinMaxPair;
    }

    public List<String> findIndustry() {
        SQLQuery query = commonDao.getNativeQuery(INDUSTRY_FROM_CUSTOM_STRATEGY_TABLE_QUERY, null);
        List<Object[]> rows = query.list();
        List<String> industryList = new ArrayList<>();
        for (Object[] row : rows) {
            String industryName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() : "NA";
            industryList.add(industryName);
        }
        return industryList;
    }

    public String findRecordStats(String perPageMaxRecords, String[] mcap, String industry, String[] pe, String[] pb,
            String[] debtToEquityRatio, String[] currentRatio, String[] netOperatingCashFlow, String[] roeInPercentage,
            String[] operatingProfitMargin, String[] patGrowthInPercentage, String[] epsGrowthInPercentage,
            String[] revenueGrowthInPercentage, String[] totalFreeCashFlow, String[] returnOnAssetInPercentage, String[] divYield,
            String[] rotcInPercentage) {
        String filterQuery = applyFilter(mcap, industry, pe, pb, debtToEquityRatio, currentRatio, netOperatingCashFlow,
                roeInPercentage, operatingProfitMargin, patGrowthInPercentage, epsGrowthInPercentage, revenueGrowthInPercentage,
                totalFreeCashFlow, returnOnAssetInPercentage, divYield, rotcInPercentage);
        String finalQuery = CUSTOM_STRATEGY_QUERY + filterQuery;
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    public List<CustomStrategyDto> findCustomScreeners(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy, String[] mcap, String industry, String[] pe, String[] pb, String[] debtToEquityRatio, String[] currentRatio, String[] netOperatingCashFlow,
            String[] roeInPercentage, String[] operatingProfitMargin, String[] patGrowthInPercentage, String[] epsGrowthInPercentage, String[] revenueGrowthInPercentage, String[] totalFreeCashFlow, String[] returnOnAssetInPercentage, String[] divYield, String[] rotcInPercentage) {
        String applyFilter = applyFilter(mcap, industry, pe, pb, debtToEquityRatio, currentRatio, netOperatingCashFlow,
                roeInPercentage, operatingProfitMargin, patGrowthInPercentage, epsGrowthInPercentage, revenueGrowthInPercentage,
                totalFreeCashFlow, returnOnAssetInPercentage, divYield, rotcInPercentage);
        String applyOrderBy = " order by cast(" + sortBy + " as decimal) " + orderBy;
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        String sql = CUSTOM_STRATEGY_QUERY + applyFilter + applyOrderBy + applyPagination;
        SQLQuery query = commonDao.getNativeQuery(sql, null);
        List<Object[]> rows = query.list();
        List<CustomStrategyDto> customStrategyDtoList = new ArrayList<>();
        for (Object[] row : rows) {
            String stockIdData = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() : "-";
            String companyNameData = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() : "-";
            String mcapData = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() : "-";
            String industryData = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() : "-";
            String peData = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() : "-";
            String pbData = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() : "-";
            String deData = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() : "-";
            String currentRatioData = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() : "-";

            String netOperatingCashFlowData = row[8] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String roeData = row[9] != null && !StringUtils.isEmpty(row[9].toString()) && !"-".equals(row[9].toString()) ? row[9].toString().trim() : "-";
            String operatingProfitMarginData = row[10] != null && !StringUtils.isEmpty(row[10].toString()) && !"-".equals(row[10].toString()) ? row[10].toString().trim() : "-";

            String patData = row[11] != null && !StringUtils.isEmpty(row[11].toString()) && !"-".equals(row[11].toString()) ? row[11].toString().trim() : "-";
            String epsData = row[12] != null && !StringUtils.isEmpty(row[12].toString()) && !"-".equals(row[12].toString()) ? row[12].toString().trim() : "-";
            String revenueData = row[13] != null && !StringUtils.isEmpty(row[13].toString()) && !"-".equals(row[13].toString()) ? row[13].toString().trim() : "-";

            String totalFreeCashFlowData = row[14] != null && !StringUtils.isEmpty(row[14].toString()) && !"-".equals(row[14].toString()) ? row[6].toString().trim() : "-";
            String returnOnAssetData = row[15] != null && !StringUtils.isEmpty(row[15].toString()) && !"-".equals(row[15].toString()) ? row[15].toString().trim() : "-";
            String divYieldData = row[16] != null && !StringUtils.isEmpty(row[16].toString()) && !"-".equals(row[16].toString()) ? row[16].toString().trim() : "-";
            String rotcData = row[17] != null && !StringUtils.isEmpty(row[17].toString()) && !"-".equals(row[17].toString()) ? row[17].toString().trim() : "-";
            customStrategyDtoList.add(new CustomStrategyDto(stockIdData,companyNameData,mcapData,industryData,
                    peData,pbData,deData,currentRatioData,netOperatingCashFlowData,roeData,operatingProfitMarginData,patData,epsData,revenueData,totalFreeCashFlowData,returnOnAssetData,divYieldData,rotcData));
        }


        return customStrategyDtoList;
    }

    private String applyFilter(String[] mcap, String industry, String[] pe, String[] pb, String[] debtToEquityRatio, String[] currentRatio,
            String[] netOperatingCashFlow, String[] roeInPercentage, String[] operatingProfitMargin, String[] patGrowthInPercentage,
            String[] epsGrowthInPercentage, String[] revenueGrowthInPercentage,
            String[] totalFreeCashFlow, String[] returnOnAssetInPercentage, String[] divYield, String[] rotcInPercentage) {
        StringBuilder partQuery=new StringBuilder(200);
        StringBuilder finalFilterQuery=new StringBuilder(200);

        if(mcap!= null) {
            partQuery.append("(cast(mcap as decimal) >=").append(mcap[0]).append(" and cast(mcap as decimal) <=").append(mcap[1]).append(")");
        }
        if(pe!= null) {
            partQuery.append("and (cast(pe as decimal)>=").append(pe[0]).append(" and cast(pe as decimal)<=").append(pe[1]).append(")");
        }
        if(pb!= null) {
            partQuery.append("and (cast(pb as decimal)>=").append(pb[0]).append(" and cast(pb as decimal)<=").append(pb[1]).append(")");
        }
        if(debtToEquityRatio!= null) {
            partQuery.append("and (cast(debtToEquityRatio as decimal)>=").append(debtToEquityRatio[0]).append(" 2").append(debtToEquityRatio[1]).append(")");
        }
        if(currentRatio!= null) {
            partQuery.append("and (cast(currentRatio as decimal)>=").append(currentRatio[0]).append(" and cast(currentRatio as decimal)<=").append(currentRatio[1]).append(")");
        }
        if(netOperatingCashFlow!= null) {
            partQuery.append("and (cast(netOperatingCashFlow as decimal)>=").append(netOperatingCashFlow[0]).append(" and cast(netOperatingCashFlow as decimal)<=").append(netOperatingCashFlow[1]).append(")");
        }
        if(roeInPercentage!= null) {
            partQuery.append("and (cast(roeInPercentage as decimal)>=").append(roeInPercentage[0]).append(" and cast(mcap as decimal) <=").append(roeInPercentage[1]).append(")");
        }
        if(operatingProfitMargin!= null) {
            partQuery.append("and (cast(operatingProfitMargin as decimal)>=").append(operatingProfitMargin[0]).append(" and cast(operatingProfitMargin as decimal)<=").append(operatingProfitMargin[1]).append(")");
        }
        if(patGrowthInPercentage!= null) {
            partQuery.append("and (cast(patGrowthInPercentage as decimal)>=").append(patGrowthInPercentage[0]).append(" and cast(patGrowthInPercentage as decimal)<=").append(patGrowthInPercentage[1]).append(")");
        }
        if(epsGrowthInPercentage!= null) {
            partQuery.append("and (cast(epsGrowthInPercentage as decimal)>=").append(epsGrowthInPercentage[0]).append(" and cast(epsGrowthInPercentage as decimal)<=").append(epsGrowthInPercentage[1]).append(")");
        }
        if(revenueGrowthInPercentage!= null) {
            partQuery.append("and (cast(revenueGrowthInPercentage as decimal)>=").append(revenueGrowthInPercentage[0]).append(" and cast(revenueGrowthInPercentage as decimal)<=").append(revenueGrowthInPercentage[1]).append(")");
        }
        if(totalFreeCashFlow!= null) {
            partQuery.append("and (cast(totalFreeCashFlow as decimal)>=").append(totalFreeCashFlow[0]).append(" and cast(totalFreeCashFlow as decimal)<=").append(totalFreeCashFlow[1]).append(")");
        }
        if(returnOnAssetInPercentage!= null) {
            partQuery.append("and (cast(returnOnAssetInPercentage as decimal)>=").append(returnOnAssetInPercentage[0]).append(" and cast(returnOnAssetInPercentage as decimal)<=").append(returnOnAssetInPercentage[1]).append(")");
        }
        if(divYield!= null) {
            partQuery.append("and (cast(divYield as decimal)>=").append(divYield[0]).append(" and cast(divYield as decimal)<=").append(divYield[1]).append(")");
        }
        if(rotcInPercentage!= null) {
            partQuery.append("and (cast(rotcInPercentage as decimal)>=").append(rotcInPercentage[0]).append(" and cast(rotcInPercentage as decimal)<=").append(rotcInPercentage[1]).append(")");
        }
        if (!StringUtils.isEmpty(industry)) {
            partQuery.append("industry='").append(industry).append('\'');
        }
        if(partQuery.length()!=0){
            finalFilterQuery.append(" where ").append(partQuery);
        }
        return finalFilterQuery.toString();
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
