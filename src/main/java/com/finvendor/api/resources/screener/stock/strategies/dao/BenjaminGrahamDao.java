package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.BenjaminGrahamStrategyDto;
import com.finvendor.common.util.CommonCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BenjaminGrahamDao extends AbstractCisDao {

    private static final String BENJAMIN_GRAMHAM_QUERY="select * from strategy_benjamin_graham ";

    @Override
    public String findCisRecordStats(String perPageMaxRecords) throws RuntimeException {
        SQLQuery query = commonDao.getNativeQuery(BENJAMIN_GRAMHAM_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        List<BenjaminGrahamStrategyDto> resultList=new ArrayList<>();
        String finalQuery = queryWithSortByAndOrderByAndPagination(sortBy,orderBy,pageNumber,perPageMaxRecords);
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        List<Object[]>  list = query.list();

        for (Object[] row : list) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() :"-";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() :"-";
            String totalDebt = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() :"-";
            String currentAsset = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() :"-";
            String currnetLiabilities = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() :"-";
            String pe = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() :"-";
            String pb = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() :"-";
            String dividendYield = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() :"-";
            String _5YrEpsGrowthPositive = row[8] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() :"-";
            BenjaminGrahamStrategyDto dto=new BenjaminGrahamStrategyDto(stockId,companyName,totalDebt,currentAsset,currnetLiabilities,_5YrEpsGrowthPositive, pe,pb,dividendYield);
            resultList.add(dto);
        }
        return resultList;
    }

    private String queryWithSortByAndOrderByAndPagination(String sortBy, String orderBy, String pageNumber, String perPageMaxRecords ){
        String newQuery=BENJAMIN_GRAMHAM_QUERY;
        String sortingQuery = "";
        switch(sortBy) {
        case "companyName":
            sortingQuery = "company_name " + orderBy;
            break;
        case "totalDebt":
            sortingQuery = "cast(total_debt as DECIMAL) " + orderBy;
            break;
        case "currentAsset":
            sortingQuery = "cast(current_assets as DECIMAL) " + orderBy;
            break;
        case "currentLiab":
            sortingQuery = "cast(current_liabilities as DECIMAL) " + orderBy;
            break;
        case "pe":
            sortingQuery = "cast(pe as DECIMAL) " + orderBy;
            break;
        case "pb":
            sortingQuery = "cast(pb as DECIMAL) " + orderBy;
            break;
        case "divYield":
            sortingQuery = "cast(dividend_yield as DECIMAL) " + orderBy;
            break;

        }
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        newQuery = newQuery + " order by "+ sortingQuery + applyPagination;
        return newQuery;
    }
}
