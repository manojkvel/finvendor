package com.finvendor.server.markets.dao.impl;

import com.finvendor.common.util.CommonUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.markets.dao.IMarketsDao;
import com.finvendor.server.markets.dto.CustomMarketsDto;
import com.finvendor.server.markets.dto.MarketsDto;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class MarketsDaoImpl implements IMarketsDao {
    private static final Logger logger = LoggerFactory.getLogger(MarketsDaoImpl.class.getName());

    private static final String MARKETS_QUERY = "select distinct a.company_id,a.company_name,c.open,c.high,c.low,c.close,c.last, CAST(c.tot_trd_qty as DECIMAL) \"trade_qty\", CAST(c.total_trades as DECIMAL) \"trades\", CAST((c.close-c.prev_close) AS DECIMAL) \"change\", concat(CAST(((c.close-c.prev_close)*100/c.prev_close) as DECIMAL),'%') \"%change\", cast(f.`52w_low` as DECIMAL) \"wlow\" ,cast(f.`52w_high` as DECIMAL) \"whigh\", e.index_id \"indexid\" from rsch_sub_area_company_dtls a, comp_mkt_cap_type b, bhav_price_details c, index_details d, index_comp_details e, stock_current_info f where a.company_id=b.company_id and a.isin_code=c.isin and a.company_id=e.company_id and e.index_id=d.id and a.company_id=f.stock_id group by a.company_name";

    @Autowired
    private ICommonDao commonDao;

    private static Map<String,String> indexIdNameMap=new LinkedHashMap<>();
    static{
        indexIdNameMap.put("sensex","0019");
        indexIdNameMap.put("nifty50","0001");
        indexIdNameMap.put("niftyMidCap","0006");
        indexIdNameMap.put("niftySmallCap","0007");
        indexIdNameMap.put("nifty500","0004");
    }

    @Override
    public String getMarketsRecordStats(String indexFilter, String perPageMaxRecords) throws RuntimeException {
        logger.info("indexFilter:{}",indexFilter);
        logger.info("perPageMaxRecords:{}",perPageMaxRecords);
        String recordStatsJson;
        long totalRecords;
        String mainQuery;
        try {
            mainQuery = applyFilter(indexFilter);
            logger.info("mainQuery:{}",mainQuery);
            SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
            List<Object[]> rows = query1.list();
            totalRecords = rows.size();
            logger.info("totalRecords:{}",totalRecords);
            if (totalRecords != 0L) {
                long lastPageNumber = CommonUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);

                recordStatsJson = CommonUtil.getRecordStatsJson(totalRecords, lastPageNumber);

            } else {
                recordStatsJson = "NA";
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while processing Markets record stats", e);
        }
        return recordStatsJson;
    }

    private String applyFilter(String indexFilter) {
        String mainQuery;
        if (indexFilter.equals("all")) {
            mainQuery = MARKETS_QUERY;
        } else {
            String indexId= getIndexId(indexFilter);
            mainQuery = MARKETS_QUERY + " having indexid='"+indexId+"' ";
        }
        return mainQuery;
    }

    private String getIndexId(String indexName) {
        return indexIdNameMap.get(indexName);
    }

    @Override
    public String getMarkets(String indexFilter, String type,String pageNumber,
                                       String perPageMaxRecords) throws RuntimeException {
        logger.info("indexFilter:{}",indexFilter);
        logger.info("type:{}",type);
        logger.info("pageNumber:{}",pageNumber);
        logger.info("perPageMaxRecords:{}",perPageMaxRecords);

        String mainQuery = applyFilter(indexFilter);
        mainQuery = mainQuery + applyOrderBy(type);
        mainQuery = mainQuery + CommonUtil.applyPagination(pageNumber, perPageMaxRecords);
        logger.info("mainQuery:{}",mainQuery);

        SQLQuery query = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query.list();

        String resultString;

        try {
            List<MarketsDto> markets=new ArrayList<>();
            for (Object[] row : rows) {
                String companyId = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                String open = row[2] != null ? row[2].toString().trim() : "";
                String high = row[3] != null ? row[3].toString().trim() : "";
                String low = row[4] != null ? row[4].toString().trim() : "";
                String close = row[5] != null ? row[5].toString().trim() : "";
                String last = row[6] != null ? row[6].toString().trim() : "";
                String tradeQty = row[7] != null ? row[7].toString().trim() : "";
                String trades = row[8] != null ? row[8].toString().trim() : "";
                String change = row[9] != null ? row[9].toString().trim() : "";
                String percentChange = row[10] != null ? row[10].toString().trim() : "";
                String _52wLow = row[11] != null ? row[11].toString().trim() : "";
                String _52wHigh = row[12] != null ? row[12].toString().trim() : "";
                //String indexId= row[13] != null ? row[13].toString().trim() : "";
                CustomMarketsDto dto=new CustomMarketsDto();
                dto.setCompanyId(companyId);
                dto.setCompanyName(companyName);
                dto.setOpen(open);
                dto.setHigh(high);
                dto.setLow(low);
                dto.setClose(close);
                dto.setLast(last);
                dto.setTradeQty(tradeQty);
                dto.setTrades(trades);
                dto.setChange(change);
                dto.setPercentChange(percentChange);
                dto.set_52wLow(_52wLow);
                dto.set_52wHigh(_52wHigh);
                markets.add(dto);
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("marketData", markets);
            resultString = JsonUtil.createJsonFromParamsMap(resultMap);
            logger.info("resultString:{}",resultString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultString;
    }

    private String applyOrderBy(String indexFilter) {
        String result;
        switch (indexFilter){
            case "winner":
                result = " order by \"%change\" desc";
                break;
            case "looser":
                result = " order by \"%change\" asc";
                break;
            case "active":
                result = " order by \"trade_qty\" desc ";
                break;
            case "wHigh":
                result = " order by \"whigh\" desc";
                break;
            case "wLow":
                result = " order by \"wLow\" desc";
                break;
            default:
                result = " order by \"%change\" desc";
                break;

        }
        return result;
    }
}
