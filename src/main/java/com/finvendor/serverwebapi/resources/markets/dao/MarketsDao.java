package com.finvendor.serverwebapi.resources.markets.dao;

import com.finvendor.common.util.CommonCodeUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.markets.dto.CustomMarketsDto;
import com.finvendor.serverwebapi.resources.markets.dto.MarketsDto;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Repository
public class MarketsDao {
    private static final Logger logger = LoggerFactory.getLogger(MarketsDao.class.getName());

    private static final String INDEX_NAME_QUERY = "select indice.index_id, indice.name,indice.family from indice";
    private static final String INDEX_SUMMARY_QUERY = "select b.closing, B.point_change, b.percent_change, b.open, b.high, b.low, b.pe, b.pb, b.div_yield from indice a, indice_details b where a.index_details_id=b.indice_details_id and a.index_id=?";

    private static final String MARKETS_QUERY = "select distinct a.company_id, a.company_name, c.open, c.high, c.low, c.close, c.prev_close, CAST(c.tot_trd_qty as DECIMAL) \"trade_qty\", CAST(c.total_trades as DECIMAL) \"trades\", CAST((c.close-c.prev_close) AS DECIMAL) \"change\", concat(CAST(((c.close-c.prev_close)*100/c.prev_close) as DECIMAL),'%') \"percentChange\", cast(f.`52w_low` as DECIMAL) \"wlow\" ,cast(f.`52w_high` as DECIMAL) \"whigh\", e.index_id indexid from   rsch_sub_area_company_dtls a,   bhav_price_details c,   index_comp_details e,   stock_current_info f where   a.isin_code=c.isin   and a.company_id=e.company_id   and a.company_id=f.stock_id group by a.company_name";

    @Autowired
    private ICommonDao commonDao;

    private static Map<String, String> bseIndexNameCodeMap = new HashMap<>();

    static {
        bseIndexNameCodeMap.put("SENSEX", "22");
        bseIndexNameCodeMap.put("BSE Bharat 22 Index", "22");
        bseIndexNameCodeMap.put("BSE Capital Goods", "22");
        bseIndexNameCodeMap.put("BSE Consumer Durables", "22");
        bseIndexNameCodeMap.put("BSE Metals", "22");
    }

    public String getIndexNames() throws RuntimeException {
        String indexNamesJson;
        try {
            SQLQuery query1 = commonDao.getNativeQuery(INDEX_NAME_QUERY, null);
            logger.info("INDEX_NAME_QUERY:{}", INDEX_NAME_QUERY);
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

    public String getIndexSummary(String indexName) throws RuntimeException {
        Pair<String, String> indexIdFamilyPair = getIndexId(indexName);
        String indexId = indexIdFamilyPair.getElement1();
        String family = indexIdFamilyPair.getElement2();
        String indexNamesJson;
        try {
            Map<String, Object> indexSummaryMap = new LinkedHashMap<>();
            Map<String, Object> dataMap = new LinkedHashMap<>();
            if ("BSE".equals(family)) {
                RestTemplate restTemplate = new RestTemplate();
                String code = bseIndexNameCodeMap.get(indexName);
                String uri = "https://api.bseindia.com/BseIndiaAPI/api/GetLinknew/w?code=" + code;
                String result = restTemplate.getForObject(uri, String.class);
                String closing = JsonUtil.getValue(result, "CurrValue");
                String pointChange = JsonUtil.getValue(result, "Chg");
                String percentChange = JsonUtil.getValue(result, "ChgPer");
                String high = JsonUtil.getValue(result, "High");
                String low = JsonUtil.getValue(result, "Low");
                String pe = "-";
                String pb = "-";
                String divYield = "-";
                indexSummaryMap.put("closing", closing);
                indexSummaryMap.put("pointChange", pointChange);
                indexSummaryMap.put("percentChange", percentChange);
                indexSummaryMap.put("open", "-");
                indexSummaryMap.put("high", high);
                indexSummaryMap.put("low", low);
                indexSummaryMap.put("pe", pe);
                indexSummaryMap.put("pb", pb);
                indexSummaryMap.put("divYield", divYield);
                indexSummaryMap.put("date", Calendar.getInstance().getTimeInMillis());
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
                    indexSummaryMap.put("closing", closing);
                    indexSummaryMap.put("pointChange", pointChange);
                    indexSummaryMap.put("percentChange", percentChange);
                    indexSummaryMap.put("open", open);
                    indexSummaryMap.put("high", high);
                    indexSummaryMap.put("low", low);
                    indexSummaryMap.put("pe", pe);
                    indexSummaryMap.put("pb", pb);
                    indexSummaryMap.put("divYield", divYield);
                    indexSummaryMap.put("date", Calendar.getInstance().getTimeInMillis());
                }
            }
            dataMap.put("indexSummary", indexSummaryMap);
            indexNamesJson = JsonUtil.createJsonFromParamsMap(dataMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return indexNamesJson;
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
                        || "52wHigh".equals(type) || "52wLow".equals(type) || "''".equals(type)) {
                    long[] defaultAnalytics = getDefaultAnalytics();
                    gainer = defaultAnalytics[0];
                    looser = defaultAnalytics[1];
                    unchanged = defaultAnalytics[2];
                }
            } else {
                String indexId = getIndexId(indexFilter).getElement1();
                logger.info("indexId:{} for indexFilter:{}", indexId, indexFilter);
                mainQuery = "select count(a.price_percent_change) from markets a, index_comp_details b where a.company_id=b.company_id and b.index_id=? and a.price_percent_change>0.0";
                logger.info("gainerQuery:{}", mainQuery);
                query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexId});
                List<Object[]> rows = query1.list();
                gainer = rows.size();

                mainQuery = "select count(a.price_percent_change) from markets a, index_comp_details b where a.company_id=b.company_id and b.index_id=? and a.price_percent_change<0.0";
                logger.info("looserQuery:{}", mainQuery);
                query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexId});
                rows = query1.list();
                looser = rows.size();

                mainQuery = "select count(a.price_percent_change) from markets a, index_comp_details b where a.company_id=b.company_id and b.index_id=? and a.price_percent_change=0.0";
                logger.info("unchangedQuery:{}", mainQuery);
                query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexId});
                rows = query1.list();
                unchanged = rows.size();
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
        long gainer;
        long looser;
        long unchanged;
        String mainQuery = "select count(a.price_percent_change) from markets a where a.price_percent_change>0.0";
        logger.info("getDefaultAnalytics - gainerQuery:{}", mainQuery);
        SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query1.list();
        gainer = rows.size();

        mainQuery = "select count(a.price_percent_change) from markets a where a.price_percent_change<0.0";
        logger.info("getDefaultAnalytics - looserQuery:{}", mainQuery);
        query1 = commonDao.getNativeQuery(mainQuery, null);
        rows = query1.list();
        looser = rows.size();

        mainQuery = "select count(a.price_percent_change) from markets a where a.price_percent_change=0.0";
        logger.info("getDefaultAnalytics - unchangedQuery:{}", mainQuery);
        query1 = commonDao.getNativeQuery(mainQuery, null);
        rows = query1.list();
        unchanged = rows.size();
        return new long[]{gainer, looser, unchanged};
    }

    public String getMarketsRecordStats(String indexFilter, String type, String perPageMaxRecords) throws RuntimeException {
        logger.info("indexFilter:{}", indexFilter);
        logger.info("perPageMaxRecords:{}", perPageMaxRecords);
        String recordStatsJson;
        long totalRecords;
        String mainQuery;
        try {
            mainQuery = applyFilter(indexFilter,type);
            mainQuery = mainQuery + applyOrderBy(type);
            logger.info("mainQuery:{}", mainQuery);
            SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
            List<Object[]> rows = query1.list();
            totalRecords = rows.size();
            logger.info("totalRecords:{}", totalRecords);
            if (totalRecords != 0L) {
                long lastPageNumber = CommonCodeUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);
                recordStatsJson = CommonCodeUtil.getRecordStatsJson(totalRecords, lastPageNumber);
            } else {
                recordStatsJson = "";
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while processing Markets record stats", e);
        }
        return recordStatsJson;
    }

    private String applyFilter(String indexFilter, String type) {
        String mainQuery="";
        if ("all".equals(indexFilter)) {
//            if ("winners".equals(type)) {
//                mainQuery = "select a.company_id, a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty from markets a where a.price_percent_change>0.0 order by a.price_percent_change desc";
//            } else if ("loosers".equals(type)) {
//                mainQuery = "select a.company_id, a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty from markets a where a.price_percent_change<0.0 order by a.price_percent_change asc";
//            } else {
//                mainQuery = "select a.company_id, a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty from markets a";
//            }
            mainQuery = "select a.company_id, a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty from markets a ";
        } else {
            String indexId = getIndexId(indexFilter).getElement1();
            mainQuery = "select a.company_id,a.company_name,a.open,a.high,a.low,a.close,a.prev_close,a.price_change,a.price_percent_change,a.52w_low,a.52w_high,a.tot_trd_qty,b.index_id from markets a,index_comp_details b where a.company_id=b.company_id and b.index_id='" + indexId + "'";
        }
        return mainQuery;
    }

    private Pair<String, String> getIndexId(String indexName) {
        String indexId = "";
        String family = "";
        try {
            String mainQuery = INDEX_NAME_QUERY + " where indice.name=?";
            logger.info("getIndexId-> mainQuery:{}", mainQuery);
            SQLQuery query1 = commonDao.getNativeQuery(mainQuery, new String[]{indexName});
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
                             String perPageMaxRecords) throws RuntimeException {
        logger.info("indexFilter:{}", indexFilter);
        logger.info("type:{}", type);
        logger.info("pageNumber:{}", pageNumber);
        logger.info("perPageMaxRecords:{}", perPageMaxRecords);

        String mainQuery = applyFilter(indexFilter, type);
        mainQuery = mainQuery + applyOrderBy(type);
        mainQuery = mainQuery + CommonCodeUtil.applyPagination(pageNumber, perPageMaxRecords);
        logger.info("mainQuery:{}", mainQuery);

        SQLQuery query = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query.list();

        String resultString;

        try {
            List<MarketsDto> markets = new ArrayList<>();
            for (Object[] row : rows) {
                String companyId = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                String open = row[2] != null ? row[2].toString().trim() : "";
                String high = row[3] != null ? row[3].toString().trim() : "";
                String low = row[4] != null ? row[4].toString().trim() : "";
                String close = row[5] != null ? row[5].toString().trim() : "";
                String prevClose = row[6] != null ? row[6].toString().trim() : "";
                String change = row[7] != null ? row[7].toString().trim() : "";
                String percentChange = row[8] != null ? row[8].toString().trim() : "";
                String _52wLow = row[9] != null ? row[9].toString().trim() : "";
                String _52wHigh = row[10] != null ? row[10].toString().trim() : "";
                String totalTradeQty = row[11] != null ? row[11].toString().trim() : "";

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
                dto.setPercentChange(percentChange);
                dto.set_52wLow(_52wLow);
                dto.set_52wHigh(_52wHigh);
                dto.setVolume(totalTradeQty);
                dto.setDate(String.valueOf(Calendar.getInstance().getTimeInMillis()));
                markets.add(dto);
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("marketData", markets);
            resultString = JsonUtil.createJsonFromParamsMap(resultMap);
            logger.info("resultString:{}", resultString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultString;
    }

    private String applyOrderBy(String indexFilter) {
        String result = "";
        if ("winners".equals(indexFilter) || "winner".equals(indexFilter)) {
            result = " where a.price_percent_change>0.0 order by a.price_percent_change desc";
        }
        if ("loosers".equals(indexFilter) || "looser".equals(indexFilter)) {
            result = " where a.price_percent_change<0.0 order by a.price_percent_change asc";
        }
        if ("active".equals(indexFilter)) {
            result = " order by a.tot_trd_qty desc";
        }
        if ("52wHigh".equals(indexFilter)) {
            result = " order by a.52w_high desc";
        }
        if ("52wLow".equals(indexFilter)) {
            result = " order by a.52w_low desc";
        }
        return result;
    }
}
