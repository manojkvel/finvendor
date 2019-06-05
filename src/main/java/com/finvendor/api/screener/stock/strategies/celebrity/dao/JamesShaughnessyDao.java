package com.finvendor.api.screener.stock.strategies.celebrity.dao;

import com.finvendor.api.screener.stock.strategies.celebrity.dto.JamesShaughnessyStrategyDto;
import com.finvendor.api.screener.stock.strategies.celebrity.dto.AbstractStrategyDto;
import com.finvendor.common.util.CommonCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JamesShaughnessyDao extends AbstractCisDao {
    private static final String JAMES_SHAUGHNESSY_QUERY="select * from strategy_james_shaughnessy ";

    @Override
    public String findCisRecordStats(String perPageMaxRecords) throws RuntimeException {
        SQLQuery query = commonDao.getNativeQuery(JAMES_SHAUGHNESSY_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        List<JamesShaughnessyStrategyDto> resultList=new ArrayList<>();
        String finalQuery = queryWithSortByAndOrderByAndPagination(sortBy, orderBy, pageNumber, perPageMaxRecords);
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        List<Object[]>  list = query.list();

        for (Object[] row : list) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() :"-";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() :"-";
            String cmp = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() :"-";
            String revenue = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() :"-";
            String eps = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() :"-";
            String pb = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() :"-";
            String mcap = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() :"-";
            String netOperatingCashFlow = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() :"-";
            JamesShaughnessyStrategyDto dto=new JamesShaughnessyStrategyDto(stockId,companyName,cmp,revenue,eps,pb, mcap,netOperatingCashFlow);
            resultList.add(dto);
        }
        return resultList;
    }

    private String queryWithSortByAndOrderByAndPagination(String sortBy, String orderBy, String pageNumber, String perPageMaxRecords ){
        String newQuery=JAMES_SHAUGHNESSY_QUERY;
        String sortingQuery = "";
        switch(sortBy) {
        case "companyName":
            sortingQuery = "company_name " + orderBy;
            break;
        case "cmp":
            sortingQuery = "cast(cmp as DECIMAL) " + orderBy;
            break;
        case "eps":
            sortingQuery = "cast(eps as DECIMAL) " + orderBy;
            break;
        case "mcap":
            sortingQuery = "cast(mcap as DECIMAL) " + orderBy;
            break;
        case "nocf":
            sortingQuery = "cast(netOperatingCashFlow as DECIMAL) " + orderBy;
            break;
        case "pb":
            sortingQuery = "cast(pb as DECIMAL) " + orderBy;
            break;
        case "revenue":
            sortingQuery = "cast(revenue as DECIMAL) " + orderBy;
            break;

        }
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        newQuery = newQuery + " order by "+ sortingQuery + applyPagination;
        return newQuery;
    }
}
