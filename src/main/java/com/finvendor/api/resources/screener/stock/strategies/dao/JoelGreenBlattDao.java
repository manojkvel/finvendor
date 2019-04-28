package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.JeolGreenblattStrategyDto;
import com.finvendor.common.util.CommonCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JoelGreenBlattDao extends AbstractCisDao {

    private static final String JOEL_GREENBLATT_QUERY="select * from strategy_joel_greenblatt ";

    @Override
    public String findCisRecordStats(String perPageMaxRecords) throws RuntimeException {
        SQLQuery query = commonDao.getNativeQuery(JOEL_GREENBLATT_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords) throws RuntimeException {
        List<JeolGreenblattStrategyDto> resultList=new ArrayList<>();
        String finalQuery = JOEL_GREENBLATT_QUERY + CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        List<Object[]>  list = query.list();

        for (Object[] row : list) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() :"-";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() :"-";
            String recentYrPat = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() :"-";
            String totalCapital = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() :"-";
            String revenue = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() :"-";
            String operatingProfitMargin = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() :"-";
            String mcap = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() :"-";
            String totalDebt = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() :"-";
            String cashAndCashEquiv = row[8] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() :"-";
            JeolGreenblattStrategyDto dto=new JeolGreenblattStrategyDto(stockId,companyName,recentYrPat,totalCapital,revenue,operatingProfitMargin, mcap,totalDebt,cashAndCashEquiv);
            resultList.add(dto);
        }
        return resultList;
    }
}
