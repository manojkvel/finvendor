package com.finvendor.api.resources.screener.stock.strategies.celebrity.dao;

import com.finvendor.api.resources.screener.stock.strategies.celebrity.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.celebrity.dto.FinvendorPickStrategyDto;
import com.finvendor.common.util.CommonCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinvendorPickDao extends AbstractCisDao {
    private static final String FINVENDOR_PICK_QUERY = "select * from strategy_finvendor_pick ";

    @Override
    public String findCisRecordStats(String perPageMaxRecords) throws RuntimeException {
        SQLQuery query = commonDao.getNativeQuery(FINVENDOR_PICK_QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy)
            throws RuntimeException {
        List<FinvendorPickStrategyDto> resultList = new ArrayList<>();

        String finalQuery = queryWithSortByAndOrderByAndPagination(sortBy, orderBy, pageNumber, perPageMaxRecords);
        SQLQuery query = commonDao.getNativeQuery(finalQuery, null);
        List<Object[]> list = query.list();

        for (Object[] row : list) {
            String stockId = row[0] != null && !StringUtils.isEmpty(row[0].toString()) && !"-".equals(row[0].toString()) ? row[0].toString().trim() : "-";
            String companyName = row[1] != null && !StringUtils.isEmpty(row[1].toString()) && !"-".equals(row[1].toString()) ? row[1].toString().trim() : "-";
            String total_free_cash_flow = row[2] != null && !StringUtils.isEmpty(row[2].toString()) && !"-".equals(row[2].toString()) ? row[2].toString().trim() : "-";
            String sales_growth = row[3] != null && !StringUtils.isEmpty(row[3].toString()) && !"-".equals(row[3].toString()) ? row[3].toString().trim() : "-";
            String one_year_eps_growth = row[4] != null && !StringUtils.isEmpty(row[4].toString()) && !"-".equals(row[4].toString()) ? row[4].toString().trim() : "-";
            String pe_ratio = row[5] != null && !StringUtils.isEmpty(row[5].toString()) && !"-".equals(row[5].toString()) ? row[5].toString().trim() : "-";
            String current_ratio = row[6] != null && !StringUtils.isEmpty(row[6].toString()) && !"-".equals(row[6].toString()) ? row[6].toString().trim() : "-";
            String current_roe = row[7] != null && !StringUtils.isEmpty(row[7].toString()) && !"-".equals(row[7].toString()) ? row[7].toString().trim() : "-";
            String current_operating_profit_margin = row[8] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String de = row[9] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String roe_avg = row[10] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String long_term_debt = row[11] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String annual_pat = row[12] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String is_all_year_eps_growth_positive = row[13] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String rotc = row[14] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String earning_yield = row[15] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String retained_earning = row[16] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            String outstanding_share = row[17] != null && !StringUtils.isEmpty(row[8].toString()) && !"-".equals(row[8].toString()) ? row[8].toString().trim() : "-";
            FinvendorPickStrategyDto dto = new FinvendorPickStrategyDto(stockId, companyName, total_free_cash_flow, sales_growth,
                    one_year_eps_growth, pe_ratio,
                    current_ratio, current_roe, current_operating_profit_margin, de, roe_avg, long_term_debt, annual_pat,
                    is_all_year_eps_growth_positive, rotc,
                    earning_yield, retained_earning, outstanding_share);resultList.add(dto);
        }
        return resultList;
    }

    private String queryWithSortByAndOrderByAndPagination(String sortBy, String orderBy, String pageNumber, String perPageMaxRecords) {
        String newQuery = FINVENDOR_PICK_QUERY;
        String sortingQuery = "";
        switch (sortBy) {
        case "companyName":
            sortingQuery = "company_name " + orderBy;
            break;
        case "totalFreeCashFlow":
            sortingQuery = "cast(total_free_cash_flow as DECIMAL) " + orderBy;
            break;
        case "salesGrowth":
            sortingQuery = "cast(sales_growth as DECIMAL) " + orderBy;
            break;
        case "oneYrEpsGrowth":
            sortingQuery = "cast(one_year_eps_growth as DECIMAL) " + orderBy;
            break;
        case "peRatio":
            sortingQuery = "cast(pe_ratio as DECIMAL) " + orderBy;
            break;
        case "currentRatio":
            sortingQuery = "cast(current_ratio as DECIMAL) " + orderBy;
            break;
        case "currentRoe":
            sortingQuery = "cast(current_roe as DECIMAL) " + orderBy;
            break;
        case "currentOPM":
            sortingQuery = "cast(current_operating_profit_margin as DECIMAL) " + orderBy;
            break;
        case "de":
            sortingQuery = "cast(de as DECIMAL) " + orderBy;
            break;
        case "avgRoe":
            sortingQuery = "cast(roe_avg as DECIMAL) " + orderBy;
            break;
        case "longTermDebt":
            sortingQuery = "cast(long_term_debt as DECIMAL) " + orderBy;
            break;
        case "annualPat":
            sortingQuery = "cast(annual_pat as DECIMAL) " + orderBy;
            break;
        case "rotc":
            sortingQuery = "cast(rotc as DECIMAL) " + orderBy;
            break;
        case "earningYield":
            sortingQuery = "cast(earning_yield as DECIMAL) " + orderBy;
            break;
        case "retainedEarning":
            sortingQuery = "cast(retained_earning as DECIMAL) " + orderBy;
            break;
        case "outstandingShare":
            sortingQuery = "cast(outstanding_share as DECIMAL) " + orderBy;
            break;
        }
        String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
        newQuery = newQuery + " order by " + sortingQuery + applyPagination;
        return newQuery;
    }
}
