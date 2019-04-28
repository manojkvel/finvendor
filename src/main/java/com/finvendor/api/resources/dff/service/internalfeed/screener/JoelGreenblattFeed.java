package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.JoelEbitAndEnterpriseDto;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.JoelRotcDto;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * @author Ayush
 * @since 7-April-2019
 */
@Service
@Transactional
public class JoelGreenblattFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_jeol_greenblatt values(?,?,?,?,?,?,?);";

    @Override
    public boolean processAndFeed() throws Exception {
        Map<Integer, JoelRotcDto> top_50_rotc = findTop50Rotc();
        Map<Integer, JoelEbitAndEnterpriseDto> top_50_ebit = findTop50Ebit();
        Set<Integer> top_50_rotc_stock_id = top_50_rotc.keySet();
        Set<Integer> top_50_ebit_stock_id = top_50_ebit.keySet();
        Set<Integer> intersection = Sets.intersection(top_50_rotc_stock_id, top_50_ebit_stock_id);

        for (Integer stockId : intersection) {
            JoelRotcDto joelRotcDto = top_50_rotc.get(stockId);
            JoelEbitAndEnterpriseDto joelEbitAndEnterpriseDto = top_50_ebit.get(stockId);
            insertFeed(joelRotcDto,joelEbitAndEnterpriseDto);
        }
        return true;
    }

    private void insertFeed(JoelRotcDto joelRotcDto, JoelEbitAndEnterpriseDto joelEbitAndEnterpriseDto) {
        String companyId = joelRotcDto.getCompanyId();
        String companyName = joelRotcDto.getCompanyName();
        String recentYrPat = joelRotcDto.getRecentYrPat();
        String totalCapital = joelRotcDto.getTotalCapital();

        String mcap = joelEbitAndEnterpriseDto.getMcap();
        String totalDebt = joelEbitAndEnterpriseDto.getTotalDebt();
        String cashAndCashEquiv = joelEbitAndEnterpriseDto.getCashAndCashEquiv();

        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);
        sqlQuery.setParameter(0, Integer.parseInt(companyId));
        sqlQuery.setParameter(1, StringUtils.isEmpty(companyName) ? "-" : companyName);


        sqlQuery.setParameter(2, StringUtils.isEmpty(recentYrPat) ? "-" : recentYrPat);
        sqlQuery.setParameter(3, StringUtils.isEmpty(totalCapital) ? "-" : totalCapital);

        sqlQuery.setParameter(4, StringUtils.isEmpty(mcap) ? "-" : mcap);
        sqlQuery.setParameter(5, StringUtils.isEmpty(totalDebt) ? "-" : totalDebt);
        sqlQuery.setParameter(6, StringUtils.isEmpty(cashAndCashEquiv) ? "-" : cashAndCashEquiv);
        sqlQuery.executeUpdate();
    }
}
