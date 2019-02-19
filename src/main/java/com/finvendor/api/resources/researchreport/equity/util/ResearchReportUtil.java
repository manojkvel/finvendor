
package com.finvendor.api.resources.researchreport.equity.util;

import com.finvendor.common.constant.AppConstant;
import com.finvendor.common.util.Pair;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.api.resources.researchreport.equity.dto.filter.impl.EquityResearchFilter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ResearchReportUtil {

    public static final String MAIN_QUERY = "SELECT rsch_sub_area_company_dtls.company_id comapanyId, rsch_sub_area_company_dtls.company_name companyName, rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style, market_cap_def.market_cap_name mcap, research_sub_area.description sector, stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt, stock_current_info.pe pe, stock_current_info.3_yr_pat_growth patGrth, stock_current_info.3_yr_eps_growth epsGrth, stock_current_info.eps_ttm epsttm, vendor_report_data.research_report_for_id companyId, vendor_report_data.product_id prdId, vendor_report_data.vendor_company broker, vendor_report_data.rsrch_recomm_type recommType, vendor_report_data.target_price tgtPrice, vendor_report_data.price_at_recomm prcAtRecomm, ((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100 upside, vendor_report_data.report_name rptName, vendor_report_data.report_date rsrchDt, vendor_report_data.analyst_awards award, vendor_report_data.anayst_cfa_charter cfa, vendor_report_data.analyst_name analystName, vendor_report_data.vendor_analyst_type analystType, vendor_report_data.vendor_id vendorId, vendor_report_data.launched_year ly, vendor_report_data.vendor_name userName, vendor_report_data.rsrch_report_desc rptDesc, vendor_report_data.product_name productNameAsReportName FROM rsch_sub_area_company_dtls,      rsch_area_stock_class,      market_cap_def,      comp_mkt_cap_type,      research_sub_area,      stock_current_prices,      stock_current_info,      vendor_report_data WHERE   rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id   AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id   AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id   AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id   AND rsch_sub_area_company_dtls.country_id = 1   AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id   AND research_sub_area.research_area_id = 7 AND vendor_report_data.research_report_for_id=rsch_sub_area_company_dtls.company_id";
    public static final String BROKER_RANK_SELECT_QUERY = "select broker_analyst.broker_id,broker_analyst.broker_rank,market_cap_def.market_cap_name from broker_analyst,market_cap_def where broker_analyst.market_cap_id = market_cap_def.market_cap_id order by broker_id asc, broker_rank ";

    private static final String LARGE_CAP = "Large Cap";
    private static final String MID_CAP = "Mid Cap";
    private static final String SMALL_CAP = "Small Cap";
    private static final String MICRO_CAP = "Micro Cap";
    private static final String NANO_CAP = "Nano Cap";

    private static final String BETWEEN = " BETWEEN ";
    private static final String AND = " AND ";
    private static Map<String, ColumnNameWithType> sortByColumnNameMap = new LinkedHashMap<>();

    static {
        sortByColumnNameMap.put("company", new ColumnNameWithType("rsch_sub_area_company_dtls.company_name", ColumnType.STRING));
        sortByColumnNameMap.put("style", new ColumnNameWithType("rsch_area_stock_class.stock_class_name", ColumnType.STRING));
        sortByColumnNameMap.put("mcap", new ColumnNameWithType("market_cap_def.market_cap_name", ColumnType.STRING));
        sortByColumnNameMap.put("sector", new ColumnNameWithType("research_sub_area.description", ColumnType.STRING));
        sortByColumnNameMap.put("broker", new ColumnNameWithType("vendor_report_data.vendor_company", ColumnType.STRING));
        sortByColumnNameMap.put("brokerRank", new ColumnNameWithType("vendor_report_data.vendor_id", ColumnType.STRING));
        sortByColumnNameMap.put("since", new ColumnNameWithType("vendor_report_data.launched_year", ColumnType.STRING));
        sortByColumnNameMap.put("awarded", new ColumnNameWithType("vendor_report_data.analyst_awards", ColumnType.STRING));
        sortByColumnNameMap.put("researchedByCfa", new ColumnNameWithType("vendor_report_data.anayst_cfa_charter", ColumnType.STRING));
        sortByColumnNameMap.put("cmp", new ColumnNameWithType("stock_current_prices.close_price", ColumnType.DECIMAL));
        sortByColumnNameMap.put("priceDate", new ColumnNameWithType("stock_current_prices.price_date", ColumnType.DATE));
        sortByColumnNameMap.put("pe", new ColumnNameWithType("stock_current_info.pe", ColumnType.DECIMAL));
        sortByColumnNameMap.put("_3YrPatGrowth", new ColumnNameWithType("stock_current_info.3_yr_pat_growth", ColumnType.DECIMAL));
        sortByColumnNameMap.put("recommType", new ColumnNameWithType("vendor_report_data.rsrch_recomm_type", ColumnType.STRING));
        sortByColumnNameMap.put("targetPrice", new ColumnNameWithType("vendor_report_data.target_price", ColumnType.DECIMAL));
        sortByColumnNameMap.put("priceAtRecomm", new ColumnNameWithType("vendor_report_data.price_at_recomm", ColumnType.DECIMAL));
        sortByColumnNameMap.put("upside", new ColumnNameWithType("(((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100)", ColumnType.DECIMAL));
        sortByColumnNameMap.put("report", new ColumnNameWithType("vendor_report_data.report_name", ColumnType.STRING));
        sortByColumnNameMap.put("researchDate", new ColumnNameWithType("vendor_report_data.report_date", ColumnType.DATE));
        sortByColumnNameMap.put("analystName", new ColumnNameWithType("vendor_report_data.analyst_name", ColumnType.STRING));
    }

    private ResearchReportUtil() {
    }

    public static long convertStringToTimestamp(String dateAsString) throws ParseException {
        DateFormat formatter;
        formatter = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_ONLY_FORMAT);
        Date date = formatter.parse(dateAsString);
        return date.getTime();
    }

    public static class BrokerRankInfo {
        private String brokerId;
        private String rank;
        private String capName;

        BrokerRankInfo(String brokerId, String rank, String capName) {
            super();
            this.brokerId = brokerId;
            this.rank = rank;
            this.capName = capName;
        }

        String getBrokerId() {
            return brokerId;
        }

        public void setBrokerId(String brokerId) {
            this.brokerId = brokerId;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        String getCapName() {
            return capName;
        }

        public void setCapName(String capName) {
            this.capName = capName;
        }

        @Override
        public String toString() {
            return "BrokerRankInfo [brokerId=" + brokerId + ", rank=" + rank + ", capName=" + capName + "]";
        }
    }

    public enum ColumnType {
        DECIMAL, DATE, STRING;
    }

    public static class ColumnNameWithType {
        private String columnName;
        private ColumnType columnType;

        ColumnNameWithType(String columnName, ColumnType columnType) {
            super();
            this.columnName = columnName;
            this.columnType = columnType;
        }

        String getColumnName() {
            return columnName;
        }

        ColumnType getColumnType() {
            return columnType;
        }
    }


    public static String applyOrderBy(String sortBy, String orderBy) {
        String queryOrderByClause;
        if ("asc".equals(orderBy)) {
            queryOrderByClause = " ASC, ";
        } else {
            queryOrderByClause = " DESC, ";
        }

        ColumnNameWithType columnNameWithType = sortByColumnNameMap.get(sortBy);
        if (columnNameWithType == null) {
            throw new RuntimeException("Invalid sortBy column name!!");
        }

        String columnName = columnNameWithType.getColumnName();
        ColumnType columnType = columnNameWithType.getColumnType();

        StringBuilder colNameSb = new StringBuilder(100);
        colNameSb.append(" ORDER BY ");

        if (columnType != null) {
            switch (columnType) {
                case STRING:
                    colNameSb.append(" ").append(columnName).append(queryOrderByClause);
                    break;
                case DECIMAL:
                    colNameSb.append(" ").append("CONVERT(").append(columnName).append(", DECIMAL(10,3))")
                            .append(queryOrderByClause);
                    break;
                case DATE:
                    colNameSb.append(" ").append("STR_TO_DATE(").append(columnName).append(", '%m/%d/%Y')")
                            .append(queryOrderByClause);
                    break;
            }
            colNameSb.deleteCharAt(colNameSb.toString().length() - 1);
        }
        return colNameSb.deleteCharAt(colNameSb.toString().length() - 1).toString();
    }

    @SuppressWarnings("unchecked")
    private static List<BrokerRankInfo> getBrokerInfoList(SQLQuery query) {
        List<Object[]> rows = query.list();
        List<BrokerRankInfo> brokerInfoList = new ArrayList<>();
        for (Object[] row : rows) {
            String brokerId = row[0] != null ? row[0].toString() : "";
            String brokerRank = row[1] != null ? row[1].toString() : "";
            String mcapName = row[2] != null ? row[2].toString() : "";
            brokerInfoList.add(new BrokerRankInfo(brokerId, brokerRank, mcapName));
        }
        return brokerInfoList;
    }

    public static String getFilteredQueryPart(EquityResearchFilter equityFilter) {
        StringBuilder conditionSqlSb = new StringBuilder();
        List<String> conditionQueryList = new ArrayList<>();

        // MCap filter applied
        if (equityFilter.getBrokerRank() == null) {
            if (equityFilter.getMcap() != null) {
                conditionQueryList.add(getConditionQuery("market_cap_def.market_cap_name", getActualMCapList(equityFilter.getMcap())));
            }
        }

        // #Style filter applied
        if (equityFilter.getStyle() != null) {
            conditionQueryList.add(getConditionQuery("rsch_area_stock_class.stock_class_name", equityFilter.getStyle()));
        }

        // AnalystType filter applied
        if (equityFilter.getAnalystType() != null) {
            conditionQueryList.add(getConditionQuery("vendor_report_data.vendor_analyst_type", equityFilter.getAnalystType()));
        }

        // ResearchBroker filter applied
        if (equityFilter.getResearchedBroker() != null) {
            conditionQueryList.add(getConditionQuery("vendor_report_data.vendor_company", equityFilter.getResearchedBroker()));
        }

        // Recommendation Type filter applied
        if (equityFilter.getRecommType() != null) {
            conditionQueryList.add(getConditionQuery("vendor_report_data.rsrch_recomm_type", equityFilter.getRecommType()));
        }

        // Others filter applied "others":["Award Winning Analyst","Research
        // Reports by CFA"]
        if (equityFilter.getOthers() != null) {
            List<String> others = equityFilter.getOthers();
            List<String> otherOptionList = new ArrayList<>();

            if (others.size() == 1) {
                String otherOption = others.get(0);
                if (otherOption.contains("Award")) {
                    otherOptionList.add("Y");
                    conditionSqlSb.append(" vendor_report_data.analyst_awards in (").append(getInClauseItems(otherOptionList)).append(")");
                } else if (otherOption.contains("CFA")) {
                    otherOptionList.add("Y");
                    conditionSqlSb.append(" vendor_report_data.anayst_cfa_charter in (").append(getInClauseItems(otherOptionList)).append(")");
                }
            } else if (others.size() == 2) {
                String otherOption1 = others.get(0);
                String otherOption2 = others.get(1);

                // expect following order for others option
                if (otherOption1 != null && otherOption1.contains("Award")) {
                    otherOptionList.add("Y");
                    conditionSqlSb.append(" vendor_report_data.analyst_awards in (").append(getInClauseItems(otherOptionList)).append(")");
                }

                if (otherOption2 != null && otherOption2.contains("CFA")) {
                    otherOptionList.add("Y");
                    conditionSqlSb.append(" and vendor_report_data.anayst_cfa_charter in (").append(getInClauseItems(otherOptionList)).append(")");
                }
            } else {
                // throw Invalid Filter Exception TODO
            }
            conditionQueryList.add(conditionSqlSb.toString());
            conditionSqlSb.setLength(0);
        }

        // Upside filter applied
        if (equityFilter.getUpside() != null && !equityFilter.getUpside().isEmpty()) {
            List<String> upsideValueList = equityFilter.getUpside();
            // Upside Formula
            String filterCondition = "(((vendor_report_data.target_price - stock_current_prices.close_price) / stock_current_prices.close_price) * 100)";
            List<String> upsideConditions = new ArrayList<>();
            StringBuilder upsideStrSb = new StringBuilder();

            for (String upsideValue : upsideValueList) {
                if ("<0%".equals(upsideValue)) {
                    upsideStrSb.append(filterCondition).append(" < ").append(" 0 ");
                    upsideConditions.add(upsideStrSb.toString());
                    upsideStrSb.setLength(0);
                }

                if ("0-20%".equals(upsideValue)) {
                    upsideStrSb.append(filterCondition).append(BETWEEN).append("0").append(AND).append("20");
                    upsideConditions.add(upsideStrSb.toString());
                    upsideStrSb.setLength(0);
                }

                if ("20-50%".equals(upsideValue)) {
                    upsideStrSb.append(filterCondition).append(BETWEEN).append("20").append(AND).append("50");
                    upsideConditions.add(upsideStrSb.toString());
                    upsideStrSb.setLength(0);
                }

                if ("50-100%".equals(upsideValue)) {
                    upsideStrSb.append(filterCondition).append(BETWEEN).append("50").append(AND).append("100");
                    upsideConditions.add(upsideStrSb.toString());
                    upsideStrSb.setLength(0);
                }

                if (">100%".equals(upsideValue)) {
                    upsideStrSb.append(filterCondition).append(" > ").append("100");
                    upsideConditions.add(upsideStrSb.toString());
                    upsideStrSb.setLength(0);
                }
            }

            for (int i = 0; i < upsideConditions.size(); i++) {
                if (i >= 1 && i < upsideConditions.size()) {
                    conditionSqlSb.append(" OR " + upsideConditions.get(i));
                } else {
                    conditionSqlSb.append(upsideConditions.get(i));
                }
            }
            conditionQueryList.add(conditionSqlSb.toString());
        }

        // #Sector filter applied
        if (equityFilter.getIndustry() != null) {
            conditionQueryList.add(getConditionQuery("research_sub_area.description", equityFilter.getIndustry()));
        }

        //Filter Company Profile Page->Research Report Tab - This is very specific filter when you click on pdf report from Company Profile Page->Research Report Tab in UI
        if (equityFilter.getProductId() != null) {
            conditionQueryList.add(getConditionQuery("vendor_report_data.product_id", equityFilter.getProductId()));
        }

        String mergeConditionQuery = mergeConditionQuery(conditionQueryList);

        //Research Date Filter
        List<String> researchDateList = equityFilter.getResearchDate();
        StringBuilder mergeConditionQuerySb = new StringBuilder(mergeConditionQuery);
        if (researchDateList != null) {
            mergeConditionQuerySb.append(" and (");
            boolean needOR = false;
            if (researchDateList.contains("< 3 months")) {
                mergeConditionQuerySb.append("datediff(curdate(),STR_TO_DATE(vendor_report_data.report_date, '%d/%m/%Y'))<90");
                needOR = true;
            }
            if (researchDateList.contains("3 - 6 months")) {
                if (needOR) {
                    mergeConditionQuerySb.append(" OR ");
                }
                mergeConditionQuerySb.append("(datediff(curdate(),STR_TO_DATE(vendor_report_data.report_date, '%d/%m/%Y')) >=90" +
                        " and datediff(curdate(),STR_TO_DATE(vendor_report_data.report_date, '%d/%m/%Y'))<180)");
                needOR = true;
            }
            if (researchDateList.contains("6 - 12 months")) {
                if (needOR) {
                    mergeConditionQuerySb.append(" OR ");
                }
                mergeConditionQuerySb.append("(datediff(curdate(),STR_TO_DATE(vendor_report_data.report_date, '%d/%m/%Y')) >=180" +
                        " and datediff(curdate(),STR_TO_DATE(vendor_report_data.report_date, '%d/%m/%Y'))<360)");
                needOR = true;
            }
            if (researchDateList.contains("> 12 months")) {
                if (needOR) {
                    mergeConditionQuerySb.append(" OR ");
                }
                mergeConditionQuerySb.append("(datediff(curdate(),STR_TO_DATE(vendor_report_data.report_date, '%d/%m/%Y')) >=360)");
            }
            mergeConditionQuerySb.append(")");
        }
        return mergeConditionQuerySb.toString();
    }

    private static String getConditionQuery(String columnName, List<String> valueList) {
        String inClauseItems = getInClauseItems(valueList);
        return columnName + " in " + "(" + inClauseItems + ")";
    }

    private static String mergeConditionQuery(List<String> conditionQueryList) {
        StringBuilder conditionSqlSb = new StringBuilder();
        if (!conditionQueryList.isEmpty()) {
            conditionSqlSb.append(" and ");
            if (conditionQueryList.size() == 1) {
                conditionSqlSb.append(conditionQueryList.get(0));
            } else {
                int i = 0;
                for (; i < conditionQueryList.size() - 1; i++) {
                    conditionSqlSb.append(conditionQueryList.get(i)).append(AND);
                }
                conditionSqlSb.append(conditionQueryList.get(i));
            }
        }
        return conditionSqlSb.toString();
    }

    private static List<String> getActualMCapList(List<String> mcapList) {
        List<String> actualMcapList = new ArrayList<>();
        for (String mcap : mcapList) {
            if (mcap.contains(LARGE_CAP)) {
                actualMcapList.add(LARGE_CAP);
            }

            if (mcap.contains(MID_CAP)) {
                actualMcapList.add(MID_CAP);
            }

            if (mcap.contains(SMALL_CAP)) {
                actualMcapList.add(SMALL_CAP);
            }

            if (mcap.contains(MICRO_CAP)) {
                actualMcapList.add(MICRO_CAP);
            }

            if (mcap.contains(NANO_CAP)) {
                actualMcapList.add(NANO_CAP);
            }
        }
        return actualMcapList;
    }

    public static String applyFilter(String mainQuery, String filteredQuery) {
        return mainQuery + filteredQuery;
    }

    private static String getInClauseItems(List<String> options) {
        StringBuilder getInClauseItemsSb = new StringBuilder(50);

        for (String option : options) {
            getInClauseItemsSb.append("'").append(option).append("'").append(",");
        }
        getInClauseItemsSb.deleteCharAt(getInClauseItemsSb.toString().length() - 1);
        return getInClauseItemsSb.toString();
    }

    /**
     * Since is minimum launched year date of vendor
     */
    public static Pair<Integer, String> calculateSinceAndYrOfIncorp(Map<String, Integer> vendorIdMap, String vendorId) {
        Pair<Integer, String> sinceWithYrOfInCorpPair = new Pair<>();
        Integer since = vendorIdMap.get(vendorId);//since is Launched year

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // formula for Broker year of incorporation = current_year - since
        int yearOfInCorporation = currentYear - since;
        sinceWithYrOfInCorpPair.setElement1(since);
        sinceWithYrOfInCorpPair.setElement2(String.valueOf(yearOfInCorporation));
        return sinceWithYrOfInCorpPair;
    }

    private static String convertCamelCase(String str) {
        String replace = StringUtils.replace(str, " ", "");
        replace = replace.substring(0, 1).toLowerCase() + replace.substring(1);
        return replace;
    }

    public static Map<String, String> getBrokerRank(List<ResearchReportUtil.BrokerRankInfo> brokerRankData,
                                                    String brokerId, EquityResearchFilter equityFilter) {
        Map<String, String> brokerRankAndMcapMap = new LinkedHashMap<>();
        for (ResearchReportUtil.BrokerRankInfo brokerRankInfo : brokerRankData) {
            if (brokerRankInfo.getBrokerId().equals(brokerId)) {
                String rank = brokerRankInfo.getRank();
                String capName = convertCamelCase(brokerRankInfo.getCapName());
                brokerRankAndMcapMap.put(capName, rank.substring(0, 1));
            }
        }

        List<String> mcapActualList = new ArrayList<>();
        if (equityFilter.getMcap() != null) {
            List<String> mcapList = equityFilter.getMcap();
            for (String mcap : mcapList) {
                if (mcap.contains(LARGE_CAP)) {
                    mcapActualList.add("largeCap");
                }
                if (mcap.contains(MID_CAP)) {
                    mcapActualList.add("midCap");
                }

                if (mcap.contains(SMALL_CAP)) {
                    mcapActualList.add("smallCap");
                }

                if (mcap.contains(MICRO_CAP)) {
                    mcapActualList.add("microCap");
                }

                if (mcap.contains(NANO_CAP)) {
                    mcapActualList.add("nanoCap");
                }
            }
            if (equityFilter.getBrokerRank() != null) {
                List<String> brokerRankList = equityFilter.getBrokerRank();

                // filter brokerRankAndMcapMap
                Map<String, String> filteredbrokerRankAndMcapMap = new LinkedHashMap<>();
                for (Map.Entry<String, String> entry : brokerRankAndMcapMap.entrySet()) {
                    String mcapName = entry.getKey();
                    String rank = entry.getValue();
                    if (mcapActualList.indexOf(mcapName) != -1) {
                        String string = brokerRankList.toString();
                        if (string.contains(rank)) {
                            filteredbrokerRankAndMcapMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
                return filteredbrokerRankAndMcapMap;
            }

        }
        return brokerRankAndMcapMap;
    }

    public static List<ResearchReportUtil.BrokerRankInfo> getBrokerRankData(ICommonDao commonDao, String query,
                                                                            String orderBy) {
        String orderByClause = "asc".equals(orderBy) ? "ASC" : "DESC";
        SQLQuery sqlQuery = commonDao.getNativeQuery(query + orderByClause, null);
        return ResearchReportUtil.getBrokerInfoList(sqlQuery);
    }
}
