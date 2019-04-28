package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.KennethFisherStrategyDto;
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

    private static final String KENNETH_FISHER_QUERY="SELECT strategy_kenneth_fisher.* from strategy_kenneth_fisher ";

    @Override
    public String findCisRecordStats(String perPageMaxRecords) {
        SQLQuery query = commonDao.getNativeQuery(KENNETH_FISHER_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords) throws RuntimeException {
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        String finalQuery = KENNETH_FISHER_QUERY + applyPagination;
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
}
