package com.finvendor.common.commondao.impl;

import com.finvendor.common.commondao.AbstractCommonDao;
import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.Pair;
import com.finvendor.model.Roles;
import com.finvendor.model.vo.VendorReportFile;
import com.finvendor.modelpojo.staticpojo.admindashboard.ResearchReportFor;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ayush on Feb 17, 2018
 */
@Repository
public class CommonDaoImpl extends AbstractCommonDao {
    private static final Logger logger = LoggerFactory.getLogger(CommonDaoImpl.class.getName());
    public static final String FIND_STOCK_ID_FOR_GIVEN_ISIN_SQL = "select a.company_id,a.isin_code from rsch_sub_area_company_dtls a where a.isin_code=?";

    @SuppressWarnings("unchecked")
    public List<Roles> executeNamedQuery(String namedQueryname) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQueryname);
        return (List<Roles>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ResearchReportFor> getCompanyDetails(String sql, String rsrchAreaId) {
        try {
            SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, Integer.parseInt(rsrchAreaId));
            List<Object[]> rows = query.list();

            List<ResearchReportFor> results = new ArrayList<>();
            for (Object[] row : rows) {
                results.add(new ResearchReportFor(Integer.parseInt(row[0].toString()), row[1].toString()));
            }
            return results;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResearchReportFor> getIndustrySubTypes(String query, String[] values) throws IOException {
        SQLQuery nativeQuery = getNativeQuery(query, values);
        List<Object[]> rows = nativeQuery.list();
        List<ResearchReportFor> industrySubTypes = new ArrayList<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        for (Object[] row : rows) {
            String id = row[0] != null ? row[0].toString().trim() : null;
            String value = row[1] != null ? row[1].toString().trim() : null;
            ResearchReportFor dto = new ResearchReportFor(Integer.parseInt(id), value);
            industrySubTypes.add(dto);
        }
        return industrySubTypes;
    }

    @Override
    public Query getNamedQuery(String namedQuery, Map<Object, Object> paramMap) {
        try {
            org.hibernate.Query query = sessionFactory.openSession().getNamedQuery(namedQuery);
            for (Map.Entry<Object, Object> paramEntry : paramMap.entrySet()) {
                query.setParameter((String) paramEntry.getKey(), paramEntry.getValue());
            }
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(String sql, Map<Integer, Object> params) throws RuntimeException {
        try {
            SQLQuery insertQuery = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object value = entry.getValue();
                insertQuery.setParameter(index, value);
            }
            return insertQuery.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
        String recordStatsJson;
        long totalRecords;
        try {

            SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
            List<Object[]> rows = sqlQuery.list();
            totalRecords = rows.size();
            if (totalRecords != 0L) {
                long lastPageNumber = CommonCodeUtils.calculatePaginationLastPage(perPageMaxRecords, totalRecords);
                recordStatsJson = CommonCodeUtils.getRecordStatsJson(totalRecords, lastPageNumber);
            }
            else {
                recordStatsJson = "";
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while processing record stats", e);
        }
        return recordStatsJson;
    }

    @Override
    public String applyPagination(String pageNumber, String perPageMaxRecords) {
        return CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
    }

    @Override
    public Pair<Long, InputStream> fetchBlobFromTable(String namedQuery, Map<Object, Object> paramMap) throws RuntimeException {
        org.hibernate.Query query = getNamedQuery(namedQuery, paramMap);
        List<VendorReportFile> researchDetailsList = query.list();

        InputStream inputStream = null;
        Long length = null;
        try {
            for (VendorReportFile vendorReportFile : researchDetailsList) {
                Blob rsrchUploadReportBlob = vendorReportFile.getReportFile();
                inputStream = rsrchUploadReportBlob.getBinaryStream();
                length = rsrchUploadReportBlob.length();
                break;
            }
            return new Pair<>(length, inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while fetching blob from table", e);
        }
    }

    @Override
    public synchronized Map<String, String> findStockHistoricalPrices(float todaysCmp, String isinCode, boolean _1W, boolean _1M,
            boolean _3M, boolean _6M)
            throws Exception {
        String stockId = getCompanyId(isinCode);
        String stockTodaysDateFromDB = getStockTodaysDateFromDB(stockId);
        if (stockTodaysDateFromDB.isEmpty()) {
            throw new Exception("Unable to find today's stock date");
        }

        float stock_1Y_Price = getStock_1Y_price(stockId, stockTodaysDateFromDB);

        Map<String, String> stockHistoricalPriceMap = new LinkedHashMap<>();
        if (_1W) {
            float stock_1W_Price = getStock_weekPrice(stockId, stockTodaysDateFromDB);
            stockHistoricalPriceMap
                    .put("1W", stock_1W_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_1W_Price) * 100 / stock_1W_Price));
        }
        if (_1M) {
            float stock_1M_Price = getStock_1M_price(stockId, stockTodaysDateFromDB);
            stockHistoricalPriceMap
                    .put("1M", stock_1M_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_1M_Price) * 100 / stock_1M_Price));
        }
        if (_3M) {
            float stock_3M_Price = getStock_3M_price(stockId, stockTodaysDateFromDB);
            stockHistoricalPriceMap
                    .put("3M", stock_3M_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_3M_Price) * 100 / stock_3M_Price));

        }
        if (_6M) {
            float stock_6M_Price = getStock_6M_price(stockId, stockTodaysDateFromDB);
            stockHistoricalPriceMap
                    .put("6M", stock_6M_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_6M_Price) * 100 / stock_6M_Price));
        }

        stockHistoricalPriceMap
                .put("1Y", stock_1Y_Price == 0.0F ? "-" : String.valueOf((todaysCmp - stock_1Y_Price) * 100 / stock_1Y_Price));
        //        stockHistoricalPriceMap.put("2Y", "-");
        //        stockHistoricalPriceMap.put("5Y", "-");
        return stockHistoricalPriceMap;
    }

    private float getStock_1Y_price(String stockId, String stockTodaysDateFromDB) throws Exception {
        String query =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        String nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        return getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);
    }

    private float getStock_6M_price(String stockId, String stockTodaysDateFromDB) throws Exception {
        String query =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        String nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        return getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);
    }

    private float getStock_3M_price(String stockId, String stockTodaysDateFromDB) throws Exception {
        String query =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        String nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        return getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);
    }

    private float getStock_1M_price(String stockId, String stockTodaysDateFromDB) throws Exception {
        String query =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        String nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        return getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);
    }

    private float getStock_weekPrice(String stockId, String stockTodaysDateFromDB) throws Exception {
        String query =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        String nextQuery =
                "SELECT price_date, close_price from stock_historical_prices where trim(SUBSTRING(price_date,1,10))=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY), INTERVAL COUNT DAY),\"%d/%b/%y\")) and stock_id="
                        + stockId;
        return getHistoricalPrice(stockTodaysDateFromDB, query, nextQuery);
    }

    @Override
    public Map<String, String> findNifty50HistoricalPrices(float nifty50Closing) throws Exception {
        float nifty50_WeekPrice;
        float nifty50_1M_Price;
        float nifty50_3M_Price;
        float nifty50_6M_Price;
        float nifty50_1Y_Price;
        String nifty50TodaysDateFromDB = getNifty50TodaysDateFromDB();
        if (nifty50TodaysDateFromDB.isEmpty()) {
            throw new Exception("Unable to find today's date from nifty50History table, cause: Batch processAndFeed would have failed!!");
        }

        Map<String, String> nifty50HistoricalPriceMap = new LinkedHashMap<>();
        //Week Price
        nifty50_WeekPrice = getNifty50_weekPrice(nifty50TodaysDateFromDB);
        String query;
        String nextQuery;

        //1M Price
        nifty50_1M_Price = getNifty50_1M_price(nifty50TodaysDateFromDB);

        //3M Price
        nifty50_3M_Price = getNifty50_3M_price(nifty50TodaysDateFromDB);

        //6M Price
        nifty50_6M_Price = getNifty50_6M_price(nifty50TodaysDateFromDB);

        //1Y Price
        nifty50_1Y_Price = getNifty50_1Y_price(nifty50TodaysDateFromDB);

        nifty50HistoricalPriceMap.put("1W",
                nifty50_WeekPrice == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_WeekPrice) * 100 / nifty50_WeekPrice));
        nifty50HistoricalPriceMap
                .put("1M", nifty50_1M_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_1M_Price) * 100 / nifty50_1M_Price));
        nifty50HistoricalPriceMap
                .put("3M", nifty50_3M_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_3M_Price) * 100 / nifty50_3M_Price));
        nifty50HistoricalPriceMap
                .put("6M", nifty50_6M_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_6M_Price) * 100 / nifty50_6M_Price));
        nifty50HistoricalPriceMap
                .put("1Y", nifty50_1Y_Price == 0.0F ? "-" : String.valueOf((nifty50Closing - nifty50_1Y_Price) * 100 / nifty50_1Y_Price));
        nifty50HistoricalPriceMap.put("2Y", "-");
        nifty50HistoricalPriceMap.put("5Y", "-");
        return nifty50HistoricalPriceMap;
    }

    @Override public Pair<String, Float> findCmp(String isin) throws Exception {
        String query = "select b.stock_id, b.close_price from rsch_sub_area_company_dtls a, stock_current_prices b where a.company_id=b.stock_id and a.isin_code=?";
        SQLQuery nativeQuery = getNativeQuery(query, new Object[] { isin });
        List<Object[]> rows = nativeQuery.list();
        String stockId = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        String cmp = !rows.isEmpty() ? (rows.get(0)[1] != null ? rows.get(0)[1].toString().trim() : "") : "";
        return new Pair<>(stockId, Float.parseFloat(!cmp.equals("-") ? "0.0" : cmp));
    }

    private float getNifty50_1Y_price(String nifty50TodaysDateFromDB) throws Exception {
        String query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR),\"%e-%b-%y\"))";
        String nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 YEAR), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        return getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);
    }

    private float getNifty50_6M_price(String nifty50TodaysDateFromDB) throws Exception {
        String query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH),\"%e-%b-%y\"))";
        String nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 6 MONTH), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        return getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);
    }

    private float getNifty50_3M_price(String nifty50TodaysDateFromDB) throws Exception {
        String query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH),\"%e-%b-%y\"))";
        String nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 3 MONTH), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        return getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);
    }

    private float getNifty50_1M_price(String nifty50TodaysDateFromDB) throws Exception {
        String query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH),\"%e-%b-%y\"))";
        String nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 1 MONTH), INTERVAL COUNT DAY),\"%e-%b-%y\"))";
        return getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);
    }

    private float getNifty50_weekPrice(String nifty50TodaysDateFromDB) throws Exception {
        String query = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY),\"%e-%b-%y\"))";
        String nextQuery = "SELECT date, close from nifty50_price_history where date=(SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('CURR_DATE',  \"%Y-%c-%d\"), INTERVAL 7 DAY), INTERVAL COUNT DAY),\"%d-%b-%y\"))";
        return getHistoricalPrice(nifty50TodaysDateFromDB, query, nextQuery);
    }

    private float getHistoricalPrice(String nifty50TodayPrice, String query, String nextQuery) throws Exception {
        query = StringUtils.replace(query, "CURR_DATE", nifty50TodayPrice);
        nextQuery = StringUtils.replace(nextQuery, "CURR_DATE", nifty50TodayPrice);
        logger.info("***query: {}", query);

        float historicalPrice;
        SQLQuery query1 = getNativeQuery(query, null);
        List<Object[]> rows = query1.list();
        String date = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
        String price = !rows.isEmpty() ? (rows.get(0)[1] != null ? rows.get(0)[1].toString().trim() : "") : "";
        String count = "1";
        if (date.isEmpty()) {
            String nextPrice;
            while (true) {
                String nextQuery1 = StringUtils.replace(nextQuery, "COUNT", count);
                logger.info("***count: {} nextQuery: {}", count, nextQuery1);
                query1 = getNativeQuery(nextQuery1, null);
                rows = query1.list();
                String nextDate = !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
                nextPrice = !rows.isEmpty() ? (rows.get(0)[1] != null ? rows.get(0)[1].toString().trim() : "") : "";
                int countAsInt = Integer.parseInt(count);
                if (nextDate.isEmpty()) {
                    countAsInt++;
                }
                else {
                    break;
                }
                if (countAsInt == 6) {
                    break;
                }
                count = String.valueOf(countAsInt);
            }
            if (nextPrice.isEmpty()) {
                nextPrice = "0.0";
            }
            historicalPrice = Float.parseFloat(nextPrice);
        }
        else {
            historicalPrice = Float.parseFloat(price);
        }
        return historicalPrice;
    }

    private String getStockTodaysDateFromDB(String stockId) {
        SQLQuery query1;
        List<Object[]> rows;
        String currentDateQuery = "SELECT DATE_FORMAT(STR_TO_DATE(b.price_date,  \"%d/%b/%y\" ),\"%Y-%c-%d\") date, close_price FROM  stock_current_prices b WHERE  b.stock_id=? ORDER BY STR_TO_DATE(b.price_date,  \"%d/%b/%y\" ) DESC limit 1 offset 0";
        query1 = getNativeQuery(currentDateQuery, new Object[] { stockId });
        rows = query1.list();
        return !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
    }

    private String getNifty50TodaysDateFromDB() {
        SQLQuery query1;
        List<Object[]> rows;
        String currentDateQuery = "SELECT DATE_FORMAT(STR_TO_DATE(date,  \"%d-%b-%y\" ),\"%Y-%c-%d\"), close FROM  nifty50_price_history ORDER BY STR_TO_DATE(date,  \"%d-%b-%y\" ) DESC limit 1 offset 0;";
        query1 = getNativeQuery(currentDateQuery, null);
        rows = query1.list();
        return !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
    }

    private String getCompanyId(String isinCode) {
        SQLQuery query1;
        List<Object[]> rows;
        query1 = getNativeQuery(FIND_STOCK_ID_FOR_GIVEN_ISIN_SQL, isinCode != null ? new String[] { isinCode } : null);
        rows = query1.list();
        return !rows.isEmpty() ? (rows.get(0)[0] != null ? rows.get(0)[0].toString().trim() : "") : "";
    }
}
