package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.MartinZweigStrategyDto;
import com.finvendor.common.util.CommonCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MartinZweigDao extends AbstractCisDao {
    private static final String MARTIN_ZWEIG_QUERY="select * from strategy_martin_zweig ";
    @Override
    public String findCisRecordStats(String perPageMaxRecords) throws RuntimeException {
        SQLQuery query = commonDao.getNativeQuery(MARTIN_ZWEIG_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        List<MartinZweigStrategyDto> resultList=new ArrayList<>();
        String finalQuery = queryWithSortByAndOrderByAndPagination(sortBy, orderBy, pageNumber, perPageMaxRecords);
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        List<Object[]>  list = query.list();

        for (Object[] row : list) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() :"-";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() :"-";
            String pe = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() :"-";
            String nifty50Pe = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() :"-";
            String latestRevenueGrowth = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() :"-";
            String yearWiseEpsGrowth = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() :"-";
            String allYearEpsGrowth = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() :"-";
            String deRatio = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() :"-";
            MartinZweigStrategyDto dto=new MartinZweigStrategyDto(stockId,companyName,pe,nifty50Pe,latestRevenueGrowth,yearWiseEpsGrowth,allYearEpsGrowth,deRatio);
            resultList.add(dto);
        }
        return resultList;
    }

    private String queryWithSortByAndOrderByAndPagination(String sortBy, String orderBy, String pageNumber, String perPageMaxRecords) {
        String newQuery = MARTIN_ZWEIG_QUERY;
        String sortingQuery = "";
        switch (sortBy) {
        case "companyName":
            sortingQuery = "company_name " + orderBy;
            break;
        case "epsGrowth":
            sortingQuery = "cast(allYearEpsGrowth as DECIMAL) " + orderBy;
            break;
        case "de":
            sortingQuery = "cast(deRatio as DECIMAL) " + orderBy;
            break;
        case "latestRevenueGrowth":
            sortingQuery = "cast(latestRevenueGrowth as DECIMAL) " + orderBy;
            break;
        case "nifty50Pe":
            sortingQuery = "cast(nifty50Pe as DECIMAL) " + orderBy;
            break;
        case "pe":
            sortingQuery = "cast(pe as DECIMAL) " + orderBy;
            break;
        }
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        newQuery = newQuery + " order by " + sortingQuery + applyPagination;
        return newQuery;
    }
}
