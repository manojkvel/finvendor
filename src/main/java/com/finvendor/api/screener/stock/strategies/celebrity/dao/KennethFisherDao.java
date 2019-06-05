package com.finvendor.api.screener.stock.strategies.celebrity.dao;

import com.finvendor.api.screener.stock.strategies.celebrity.dto.AbstractStrategyDto;
import com.finvendor.api.screener.stock.strategies.celebrity.dto.KennethFisherStrategyDto;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.util.CommonCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KennethFisherDao extends AbstractCisDao {

    @Autowired
    private ICommonDao commonDao;

    private static final String KENNETH_FISHER_QUERY="SELECT * from strategy_kenneth_fisher ";

    @Override
    public String findCisRecordStats(String perPageMaxRecords) {
        SQLQuery query = commonDao.getNativeQuery(KENNETH_FISHER_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        String finalQuery = queryWithSortByAndOrderByAndPagination(sortBy, orderBy, pageNumber, perPageMaxRecords);
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        List<Object[]>  list = query.list();
        List<KennethFisherStrategyDto> resultList=new ArrayList<>();
        for (Object[] row : list) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() :"-";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() :"-";
            String psr = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() :"-";
            String mcap = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() :"-";
            String annualRevenue = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() :"-";
            String de = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() :"-";
            String epsGrowth = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() :"-";
            String inflationRate = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() :"-";
            String _3YrAvgNetProfitMargin = row[8] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() :"-";
            String rndExpense = row[9] != null && !StringUtils.isEmpty(row[9].toString()) && !"-".equals(row[9].toString()) ? row[9].toString().trim() :"-";
            KennethFisherStrategyDto dto=new KennethFisherStrategyDto(stockId,companyName,psr,mcap,annualRevenue,de,epsGrowth,inflationRate,_3YrAvgNetProfitMargin,rndExpense);
            resultList.add(dto);
        }
        return resultList;
    }

    private String queryWithSortByAndOrderByAndPagination(String sortBy, String orderBy, String pageNumber, String perPageMaxRecords ){
        String newQuery=KENNETH_FISHER_QUERY;
        String sortingQuery = "";
        switch(sortBy){
        case "companyName":
            sortingQuery = "company_name " + orderBy;
            break;
        case "annualRevenue":
            sortingQuery = "cast(annual_revenue as DECIMAL) " + orderBy;
            break;
        case "de":
            sortingQuery = "cast(de as DECIMAL) " + orderBy;
            break;
        case "inflationRate":
            sortingQuery = "cast(inflation_rate as DECIMAL) " + orderBy;
            break;
        case "mcap":
            sortingQuery = "cast(mcap as DECIMAL) " + orderBy;
            break;
        case "psr":
            sortingQuery = "cast(psr as DECIMAL) " + orderBy;
            break;
        case "rndExpense":
            sortingQuery = "cast(rndExpense as DECIMAL) " + orderBy;
            break;
        case "eps":
            sortingQuery = "cast(eps_growth as DECIMAL) " + orderBy;
            break;
        case "avgNetProfitMargin":
            sortingQuery = "cast(3Yr_avg_netprofit_margin as DECIMAL) " + orderBy;
            break;
        }
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        newQuery = newQuery + " order by "+ sortingQuery + applyPagination;
        return newQuery;
    }
}
