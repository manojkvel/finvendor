package com.finvendor.serverwebapi.resources.markets.dao;

import com.finvendor.common.util.CommonCodeUtil;
import com.finvendor.common.util.DateUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.markets.dto.CustomMarketsDto;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

@Repository
public class MarketsDao {
    private static final Logger logger = LoggerFactory.getLogger(MarketsDao.class.getName());
    private static final String INDEX_NAME_QUERY = "select indice.index_id, indice.name,indice.family from indice";
    private static final String INDEX_SUMMARY_QUERY = "select b.closing, b.point_change, b.percent_change, b.open, b.high, b.low, b.pe, b.pb, b.div_yield from indice a, indice_details b where a.index_details_id=b.indice_details_id and a.index_id=?";

    @Autowired
    private ICommonDao commonDao;

    private static Map<String, String> bseIndexNameCodeMap = new HashMap<>();

    static {
        bseIndexNameCodeMap.put("SENSEX", "SENSEX");
        bseIndexNameCodeMap.put("BSE Bharat 22 Index", "SPBSB2IP");
        bseIndexNameCodeMap.put("BSE Capital Goods", "SI0200");
        bseIndexNameCodeMap.put("BSE Consumer Durables", "SI0400");
        bseIndexNameCodeMap.put("BSE Metals", "SI1200");
    }

    public String getIndexNames() throws RuntimeException {
        String indexNamesJson;
        try {
            SQLQuery query1 = commonDao.getNativeQuery(INDEX_NAME_QUERY, null);
            logger.info("*** Index Name Query:\n{}\n", INDEX_NAME_QUERY);
            List<Object[]> rows = query1.list();
            Map<String, Object> dataMap = new LinkedHashMap<>();
            List<String> indexNames = new ArrayList<>();
            for (Object[] row : rows) {
                String indexId = row[0] != null ? row[0].toString().trim() : "";
                String indexName = row[1] != null ? row[1].toString().trim() : "";
                indexNames.add(indexName);
            }
            dataMap.put("indexNames", indexNames);
            indexNamesJson = JsonUtil.createJsonFromParamsMap(dataMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return indexNamesJson;
    }

    public String getIndexSummary(String indexFilter) throws RuntimeException {
        String indexNamesJson = "{}";
        Map<String, Object> indexSummaryMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        try {
            if ("all".equals(indexFilter)) {
                indexSummaryMap.put("title", "Today's Market Snapshot");
                String date_in_millis = getDateFromDB("select a.id, a.date from markets a where a.id=1");
                indexSummaryMap.put("date", date_in_millis);
            } else {
                String date_in_millis = getDateFromDB("select a.indice_details_id,a.date from indice_details a where a.indice_details_id=1");
                Pair<String, String> indexIdFamilyPair = getIndexId(indexFilter);
                String indexId = indexIdFamilyPair.getElement1();
                String family = indexIdFamilyPair.getElement2();
                if ("BSE".equals(family)) {
                    String indexName = bseIndexNameCodeMap.get(indexFilter);
                    String result = getPriceFromBSE(indexName);

                    String i_close = JsonUtil.getValue(result, "I_close");
                    String closing = i_close.isEmpty() ? "-" : i_close;

                    String i_open = JsonUtil.getValue(result, "I_open");
                    String open = i_open.isEmpty() ? "-" : i_open;

                    String i_high = JsonUtil.getValue(result, "I_high");
                    String high = i_high.isEmpty() ? "-" : i_high;

                    String i_low = JsonUtil.getValue(result, "I_low");
                    String low = i_low.isEmpty() ? "-" : i_low;

                    String i_pe = JsonUtil.getValue(result, "I_pe");
                    String pe = i_pe.isEmpty() ? "-" : i_pe;

                    String i_pb = JsonUtil.getValue(result, "I_pb");
                    String pb = i_pb.isEmpty() ? "-" : i_pb;

                    String i_yl = JsonUtil.getValue(result, "I_yl");
                    String divYield = i_yl.isEmpty() ? "-" : i_yl;

                    String pointChange = "-";
                    String percentChange = "-";

                    indexSummaryMap.put("title", indexFilter);
                    indexSummaryMap.put("closing", closing);
                    indexSummaryMap.put("pointChange", pointChange);
                    indexSummaryMap.put("percentChange", percentChange);
                    indexSummaryMap.put("open", open);
                    indexSummaryMap.put("high", high);
                    indexSummaryMap.put("low", low);
                    indexSummaryMap.put("pe", pe);
                    indexSummaryMap.put("pb", pb);
                    indexSummaryMap.put("divYield", divYield);
                    indexSummaryMap.put("date", date_in_millis);
                } else {
                    logger.info("INDEX_SUMMARY_QUERY:{}", INDEX_SUMMARY_QUERY);
                    SQLQuery query1 = commonDao.getNativeQuery(INDEX_SUMMARY_QUERY, new String[]{indexId});
                    List<Object[]> rows = query1.list();
                    for (Object[] row : rows) {
                        String closing = row[0] != null ? row[0].toString().trim() : "";
                        String pointChange = row[1] != null ? row[1].toString().trim() : "";
                        String percentChange = row[2] != null ? row[2].toString().trim() : "";
                        String open = row[3] != null ? row[3].toString().trim() : "";
                        String high = row[4] != null ? row[4].toString().trim() : "";
                        String low = row[5] != null ? row[5].toString().trim() : "";
                        String pe = row[6] != null ? row[6].toString().trim() : "";
                        String pb = row[7] != null ? row[7].toString().trim() : "";
                        String divYield = row[8] != null ? row[8].toString().trim() : "";
                        indexSummaryMap.put("title", indexFilter);
                        indexSummaryMap.put("closing", closing);
                        indexSummaryMap.put("pointChange", pointChange);
                        indexSummaryMap.put("percentChange", percentChange);
                        indexSummaryMap.put("open", open);
                        indexSummaryMap.put("high", high);
                        indexSummaryMap.put("low", low);
                        indexSummaryMap.put("pe", pe);
                        indexSummaryMap.put("pb", pb);
                        indexSummaryMap.put("divYield", divYield);
                        indexSummaryMap.put("date", date_in_millis);
                    }
                }
            }
            dataMap.put("indexSummary", indexSummaryMap);
            indexNamesJson = JsonUtil.createJsonFromParamsMap(dataMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return indexNamesJson;
    }

    /**
     * BSE URL : "https://api.bseindia.com/BseIndiaAPI/api/IndexArchDaily/w?index=SENSEX&period=D&fmdt=09%2F11%2F2018&todt=09%2F11%2F2018";
     */
    private String getPriceFromBSE(String indexName) throws IOException {
        int attempt = 1;
        String result = "";

        String currentDay = DateUtil.getDayNumber();
        String currentMonth = DateUtil.getCurrentMonthDigit();
        String currentYear = DateUtil.getCurrentYear();

        /**
         * Algo:
         * When BSE PRice is empty on current Day (i.e. vacation/holiday) then we try to hit BSE Site on
         * last working day. When we get price of last working day then we stop hitting BSE Site
         */
        while (attempt <= 10) {
            String currDate = currentDay + "%2F" + currentMonth + "%2F" + currentYear;//%2F means / (forward slash)
            result = readUri(getBseIndiaUri(indexName, currDate));

            if ("{\"Table\":[]}".equals(result)) {

                //This is rare - Lets beake loop after 10 attempts and we still did not get price from BSE
                if (attempt == 11) {
                    logger.info("*** !!! Shit !!!...System could not found price from BSE in max attempts: {}," +
                            " Please report to Finvendor admin", attempt);
                    break;
                }
                attempt++;

                //Now reduce day number by 1 to attempt with prev day again
                currentDay = String.valueOf(Integer.parseInt(currentDay) - 1);

                /*Add 0 before day number when day <10 i.e. convert 9 to 09*/
                currentDay = currentDay.length() == 1 ? "0" + currentDay : currentDay;
            } else {
                logger.info("*** Before Vacation - BSE price Found on date:{} in attempt: {}", currDate.replace("%2F", "/"), attempt);
                break;
            }
        }
        return result;
    }

    private String getBseIndiaUri(String indexName, String currDate) {
        String uri = "https://api.bseindia.com/BseIndiaAPI/api/IndexArchDaily/w?index=INDEX_NAME&period=D&fmdt=FROM_DATE&todt=TO_DATE";
        uri = StringUtils.replace(uri, "INDEX_NAME", indexName);
        uri = StringUtils.replace(uri, "FROM_DATE", currDate);
        uri = StringUtils.replace(uri, "TO_DATE", currDate);
        return uri;
    }

    private String readUri(String uri) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(uri).openStream()))) {
            return reader.readLine();
        }
    }

    private String getDateFromDB(String sql) throws ParseException {
        SQLQuery nativeQuery = commonDao.getNativeQuery(sql, null);
        List<Object[]> rows = nativeQuery.list();
        String date_in_millis = "0";
        for (Object[] row : rows) {
            String id = row[0] != null ? row[0].toString().trim() : "";
            String date = row[1] != null ? row[1].toString().trim() : "";
            date_in_millis = String.valueOf(DateUtil.convertFvPriceDateToTimestamp(date));
            break;
        }
        return date_in_millis;
    }

    public String getMarketsAnalytics(String indexFilter, String type) throws RuntimeException {
        logger.info("indexFilter:{}", indexFilter);
        logger.info("type:{}", type);
        String analyticsJson;
        long gainer = 0L;
        long looser = 0L;
        long unchanged = 0L;
        try {
            SQLQuery query1;
            String mainQuery;
            if ("all".equals(indexFilter)) {
                if ("all".equals(type) || "winner".equals(type) || "looser".equals(type) || "active".equals(type)
                        || "52wHigh".equals(type) || "52wLow".equals(type) || "''".equals(type) || "".equals(type)) {
                    long[] defaultAnalytics = getDefaultAnalytics();
                    gainer = defaultAnalytics[0];
                    looser = defaultAnalytics[1];
                    unchanged = defaultAnalytics[2];
                }
            } else {
                String indexId = getIndexId(indexFilter).getElement1();
                logger.info("indexId:{} for indexFilter:{}", indexId, indexFilter);
                mainQuery = "select count(a.price_percent_change) from markets a, index_comp_details b where a.company_id=b.company_id and b.index_id=? and a.price_percent_change>0.0";
                logger.info("*** Gainer Query:\n{}\n", mainQuery);
                query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexId});
                Object object = query1.list().get(0);
                if (object instanceof BigInteger) {
                    BigInteger i = (BigInteger) object;
                    gainer = i.longValue();
                }

                mainQuery = "select count(a.price_percent_change) from markets a, index_comp_details b where a.company_id=b.company_id and b.index_id=? and a.price_percent_change<0.0";
                logger.info("*** Looser Query:\n{}\n", mainQuery);
                query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexId});
                object = query1.list().get(0);
                if (object instanceof BigInteger) {
                    BigInteger i = (BigInteger) object;
                    looser = i.longValue();
                }

                mainQuery = "select count(a.price_percent_change) from markets a, index_comp_details b where a.company_id=b.company_id and b.index_id=? and a.price_percent_change=0.0";
                logger.info("*** Unchanged Query:\n{}\n", mainQuery);
                query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexId});
                object = query1.list().get(0);
                if (object instanceof BigInteger) {
                    BigInteger i = (BigInteger) object;
                    unchanged = i.longValue();
                }
            }
            Map<String, Object> analyticsMap = new LinkedHashMap<>();
            analyticsMap.put("gainer", gainer);
            analyticsMap.put("looser", looser);
            analyticsMap.put("unchanged", unchanged);

            Map<String, Object> dataMap = new LinkedHashMap<>();
            dataMap.put("constituentAnalytics", analyticsMap);
            analyticsJson = JsonUtil.createJsonFromParamsMap(dataMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return analyticsJson;
    }

    private long[] getDefaultAnalytics() {
        long gainer = 0L;
        long looser = 0L;
        long unchanged = 0L;
        String mainQuery = "select count(a.price_percent_change) from markets a where a.price_percent_change>0.0";
        logger.info("getDefaultAnalytics - gainerQuery:{}", mainQuery);
        SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);

        Object object = query1.list().get(0);
        if (object instanceof BigInteger) {
            BigInteger i = (BigInteger) object;
            gainer = i.longValue();
        }


        mainQuery = "select count(a.price_percent_change) from markets a where a.price_percent_change<0.0";
        logger.info("getDefaultAnalytics - looserQuery:{}", mainQuery);
        query1 = commonDao.getNativeQuery(mainQuery, null);
        object = query1.list().get(0);
        if (object instanceof BigInteger) {
            BigInteger i = (BigInteger) object;
            looser = i.longValue();
        }

        mainQuery = "select count(a.price_percent_change) from markets a where a.price_percent_change=0.0";
        logger.info("getDefaultAnalytics - unchangedQuery:{}", mainQuery);
        query1 = commonDao.getNativeQuery(mainQuery, null);
        object = query1.list().get(0);
        if (object instanceof BigInteger) {
            BigInteger i = (BigInteger) object;
            unchanged = i.longValue();
        }
        return new long[]{gainer, looser, unchanged};
    }

    public String getMarketsRecordStats(String indexFilter, String type, String perPageMaxRecords) throws RuntimeException {
        logger.info("indexFilter:{}", indexFilter);
        logger.info("perPageMaxRecords:{}", perPageMaxRecords);
        String recordStatsJson;
        long totalRecords = 0L;
        String mainQuery;
        try {
            mainQuery = applyFilter(indexFilter);
            mainQuery = mainQuery + applyOrderBy(type, "", "");
            logger.info("*** Markets RecordStats Query:\n{}\n", mainQuery);
            SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
            List<Object[]> rows = query1.list();
            totalRecords = rows.size();

            if (totalRecords != 0L) {
                long lastPageNumber = CommonCodeUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);
                recordStatsJson = CommonCodeUtil.getRecordStatsJson(totalRecords, lastPageNumber);
            } else {
                long lastPageNumber = 1L;
                recordStatsJson = CommonCodeUtil.getRecordStatsJson(totalRecords, lastPageNumber);
                ;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while processing Markets record stats", e);
        }
        return recordStatsJson;
    }

    private String applyFilter(String indexFilter) {
        String mainQuery = "";
        if ("all".equals(indexFilter)) {
            mainQuery = "select a.company_id, a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty,a.isin from markets a ";
        } else {
            String indexId = getIndexId(indexFilter).getElement1();
            mainQuery = "select a.company_id,a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty,b.index_id,a.isin from markets a,index_comp_details b where a.company_id=b.company_id and b.index_id='" + indexId + "'";
        }
        return mainQuery;
    }

    private Pair<String, String> getIndexId(String indexFilter) {
        String indexId = "";
        String family = "";
        try {
            String mainQuery = INDEX_NAME_QUERY + " where indice.name=?";
            logger.info("getIndexId-> mainQuery:{}", mainQuery);
            SQLQuery query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexFilter});
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                indexId = row[0] != null ? row[0].toString().trim() : "";
                String indexName1 = row[1] != null ? row[1].toString().trim() : "";
                family = row[2] != null ? row[2].toString().trim() : "";

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Pair<>(indexId, family);
    }

    public String getMarkets(String indexFilter, String type, String pageNumber,
                             String perPageMaxRecords, final String sortBy, final String orderBy) throws RuntimeException {
        logger.info("MarketsDao-> getMarkets() - START");
        logger.info("indexFilter: {}", indexFilter);
        logger.info("type: {}", type);
        logger.info("pageNumber: {}", pageNumber);
        logger.info("perPageMaxRecords: {}", perPageMaxRecords);
        logger.info("sortBy: {}", sortBy);
        logger.info("orderBy: {}", orderBy);

        String mainQuery = applyFilter(indexFilter);
        mainQuery = mainQuery + applyOrderBy(type, sortBy, orderBy);
        mainQuery = mainQuery + CommonCodeUtil.applyPagination(pageNumber, perPageMaxRecords);
        logger.info("*** Markets Query:\n{}\n", mainQuery);

        SQLQuery query = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query.list();

        String resultString;

        try {
            List<CustomMarketsDto> markets = new ArrayList<>();
            for (Object[] row : rows) {
                String companyId = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                String open = row[2] != null ? row[2].toString().trim() : "";
                String high = row[3] != null ? row[3].toString().trim() : "";
                String low = row[4] != null ? row[4].toString().trim() : "";
                String close = row[5] != null ? row[5].toString().trim() : "";
                String prevClose = row[6] != null ? row[6].toString().trim() : "";
                String change = row[7] != null ? row[7].toString().trim() : "";
                Object percentChangeObject = row[8] != null ? row[8] : null;
                double percentChangeAsDouble = 0.0D;
                if (percentChangeObject instanceof BigDecimal) {
                    BigDecimal percentChangeBigDecimal = (BigDecimal) percentChangeObject;
                    percentChangeAsDouble = percentChangeBigDecimal.doubleValue();
                }
                String _52wLow = row[9] != null ? row[9].toString().trim() : "";
                String _52wHigh = row[10] != null ? row[10].toString().trim() : "";
                int totalTradeQtyAsInteger = 0;
                Object totalTradeQtyAsObject = row[11] != null ? row[11] : null;
                if (totalTradeQtyAsObject instanceof Integer) {
                    Integer totalTradeQtyAsBigDecimal = (Integer) totalTradeQtyAsObject;
                    totalTradeQtyAsInteger = totalTradeQtyAsBigDecimal.intValue();
                }
                String isinCode = "";
                if ("all".equals(indexFilter)) {
                    isinCode = row[12] != null ? row[12].toString().trim() : "";
                } else {
                    String indexId = row[12] != null ? row[12].toString().trim() : "";
                    isinCode = row[13] != null ? row[13].toString().trim() : "";
                }

                //String indexId= row[13] != null ? row[13].toString().trim() : "";
                CustomMarketsDto dto = new CustomMarketsDto();
                dto.setCompanyId(companyId);
                dto.setCompanyName(companyName);
                dto.setOpen(open);
                dto.setHigh(high);
                dto.setLow(low);
                dto.setClose(close);
                dto.setPrevColse(prevClose);
                dto.setChange(change);
                dto.setPercentChange(percentChangeAsDouble);
                dto.set_52wLow(_52wLow);
                dto.set_52wHigh(_52wHigh);
                dto.setVolume(totalTradeQtyAsInteger);
                dto.setIsinCode(isinCode);
                markets.add(dto);
            }

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("title", "".equals(type) ? indexFilter : getMarketDataTitle(type));
            resultMap.put("marketData", markets);
            resultString = JsonUtil.createJsonFromParamsMap(resultMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultString;
    }

    private String applyOrderBy(String indexFilter, String sortBy, String orderBy) {
        String result = "";
        if ("companyName".equals(sortBy)) {
            sortBy = "a.company_name";
        } else if ("percentChange".equals(sortBy)) {
            sortBy = "a.price_percent_change";
        } else if ("volume".equals(sortBy)) {
            sortBy = "a.tot_trd_qty";
        } else if ("52wHigh".equals(sortBy)) {
            sortBy = "a.52w_high";
        } else if ("52wLow".equals(sortBy)) {
            sortBy = "a.52w_low";
        } else {
            sortBy = "a.price_percent_change";
            orderBy = "desc";
        }


        if ("winners".equals(indexFilter) || "winner".equals(indexFilter)) {
            if (sortBy.isEmpty()) {
                result = " where a.price_percent_change > 0.0 order by a.price_percent_change desc";
            } else {
                result = " where a.price_percent_change > 0.0 order by " + sortBy + " " + orderBy;
            }
        } else if ("loosers".equals(indexFilter) || "looser".equals(indexFilter)) {
            if (sortBy.isEmpty()) {
                result = " where a.price_percent_change < 0.0 order by a.price_percent_change asc";
            } else {
                result = " where a.price_percent_change < 0.0 order by " + sortBy + " " + orderBy;

            }
        } else if ("active".equals(indexFilter)) {
            if (sortBy.isEmpty()) {
                result = " order by a.tot_trd_qty desc";
            } else {
                result = " order by " + sortBy + " " + orderBy;
            }
        } else if ("52wHigh".equals(indexFilter)) {
            if (sortBy.isEmpty()) {
                result = " where cast(a.close as DECIMAL) > cast(a.`52w_high` as decimal)  order by a.close desc";
            } else {
                result = " where cast(a.close as DECIMAL) > cast(a.`52w_high` as decimal)  order by " + sortBy + " " + orderBy;
            }
        } else if ("52wLow".equals(indexFilter)) {
            if (sortBy.isEmpty()) {
                result = " where cast(a.close as DECIMAL) < cast(a.`52w_low` as decimal) order by a.close desc";
            } else {
                result = " where cast(a.close as DECIMAL) < cast(a.`52w_low` as decimal) order by " + sortBy + " " + orderBy;
            }
        } else {
            result = " order by " + sortBy + " " + orderBy;
        }
        return result;
    }

    private String getMarketDataTitle(String type) {
        String title = "";
        if ("winners".equals(type) || "winner".equals(type)) {
            title = "Today's Winners";
        }
        if ("loosers".equals(type) || "looser".equals(type)) {
            title = "Today's Losers";
        }
        if ("active".equals(type)) {
            title = "Most Active Today (By Total Trade Volume)";
        }
        if ("52wHigh".equals(type)) {
            title = "52 Weeks High";
        }
        if ("52wLow".equals(type)) {
            title = "52 Weeks Low";
        }

        return title;
    }
}
