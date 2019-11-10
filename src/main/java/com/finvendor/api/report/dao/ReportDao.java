package com.finvendor.api.report.dao;

import com.finvendor.api.report.dto.CompanyIdNameDto;
import com.finvendor.api.report.dto.ReportUser;
import com.finvendor.api.report.dto.corpaction.CorpAction;
import com.finvendor.api.report.dto.financials.FinancialsQuarterly;
import com.finvendor.api.report.dto.financials.FinancialsYearly;
import com.finvendor.api.report.dto.resultCalendar.ResultCalendar;
import com.finvendor.common.commondao.ICommonDao;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDao {
    private static final String ENABLED_CONSUMER_USERS = "select u.username,u.email, us.subscription_type, us.subscription_state from users u, user_roles ur, users_subscription us where u.username=ur.username and u.username=us.username and ur.role_id=3 and u.enabled=1";
    private static final String RESULT_CALENDAR_QUERY = "select a.board_meeting_date,b.company_name from company_calendar_history a,rsch_sub_area_company_dtls b where a.ticker=b.ticker and a.purpose like '%Financial Results%' and STR_TO_DATE(a.board_meeting_date, '%d-%M-%Y')>=NOW()";
    private static final String CORP_ACTION_QUERY = "select b.company_name,a.* from corp_action_history a,rsch_sub_area_company_dtls b where a.ticker=b.ticker  and STR_TO_DATE(a.ex_date, '%d-%M-%Y')>NOW()";
    private static final String FINANCIALS_QUARTERLY_QUERY = "select b.company_name,a.period,a.revenue,a.operating_profit_margin,a.profit_after_tax,a.eps from earning_preview_quarterly a, rsch_sub_area_company_dtls b where a.stock_id=b.company_id and b.company_name IN ";
    private static final String FINANCIALS_YEARLY_QUERY = "select b.company_name,a.period,a.revenue,a.operating_profit_margin,a.profit_after_tax,a.eps,a.net_operating_cash_flow,a.roe from earning_preview_yearly a, rsch_sub_area_company_dtls b where a.stock_id=b.company_id and b.company_name IN ";
    private static final String WATCHLIST_USERS_COMPANY_QUERY = "select company_id, company_name from company_watchlist where user_name=?";

    @Autowired
    private ICommonDao commonDao;

    public List<CompanyIdNameDto> findCompanyName(String userName) throws Exception {
        List<CompanyIdNameDto> companyIdNameDtos = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(WATCHLIST_USERS_COMPANY_QUERY, new Object[]{userName});
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String companyId = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                companyIdNameDtos.add(new CompanyIdNameDto(companyId, companyName));
            }
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return companyIdNameDtos;
    }

    /**
     * Find all consumer user who are enabled
     *
     * @return List of consumer users who are enabled
     * @throws Exception if any sql error has occurred
     */
    public List<ReportUser> findAllUsers() throws Exception {
        List<ReportUser> reportUsers = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(ReportDao.ENABLED_CONSUMER_USERS, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : "";
                String email = row[1] != null ? row[1].toString().trim() : "";
                String subscriptionType = row[2] != null ? row[2].toString().trim() : "";
                boolean subscriptionStatus = row[3] != null && (row[3].toString().trim().equals("TRUE"));
                reportUsers.add(new ReportUser(userName, email, subscriptionType, subscriptionStatus));
            }
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return reportUsers;
    }

    public List<ResultCalendar> findResultCalendar() throws Exception {
        List<ResultCalendar> resultCalendars = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(RESULT_CALENDAR_QUERY, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String date = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                resultCalendars.add(new ResultCalendar(date, companyName));
            }
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return resultCalendars;
    }

    public List<CorpAction> findCorpAction() throws Exception {
        List<CorpAction> corpActions = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(CORP_ACTION_QUERY, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String company_name = row[0] != null ? row[0].toString().trim() : "";
                String ticker = row[2] != null ? row[2].toString().trim() : "";
                String purpose = row[3] != null ? row[3].toString().trim() : "";
                String faceValue = row[4] != null ? row[4].toString().trim() : "";
                String exDate = row[5] != null ? row[5].toString().trim() : "";
                String recordDate = row[6] != null ? row[6].toString().trim() : "";
                corpActions.add(new CorpAction(company_name, ticker, purpose, faceValue, exDate, recordDate));
            }
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return corpActions;
    }

    public List<FinancialsQuarterly> findFinancialsQuarterly(List<CompanyIdNameDto> companyIdNameDtoList) throws Exception {
        StringBuilder inQueryValues = getInQueryPart(companyIdNameDtoList);
        String finalQuery = FINANCIALS_QUARTERLY_QUERY + inQueryValues.toString();
        List<FinancialsQuarterly> financialsQuarterlyList = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(finalQuery, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String companyName = row[0] != null ? row[0].toString().trim() : "";
                String period = row[1] != null ? row[1].toString().trim() : "";
                String revenue = row[2] != null ? row[2].toString().trim() : "";
                String operatingProfitMargin = row[3] != null ? row[3].toString().trim() : "";
                String profitAfterTax = row[4] != null ? row[4].toString().trim() : "";
                String eps = row[5] != null ? row[5].toString().trim() : "";
                financialsQuarterlyList.add(new FinancialsQuarterly(companyName,period,revenue,operatingProfitMargin,profitAfterTax,eps));
            }
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return financialsQuarterlyList;
    }

    private StringBuilder getInQueryPart(List<CompanyIdNameDto> companyIdNameDtoList) {
        StringBuilder sb=new StringBuilder(100);
        sb.append("(");
        for(CompanyIdNameDto dto:companyIdNameDtoList){
            sb.append("\'").append(dto.getCompanyName()).append("\'").append(",");
        }
        sb.deleteCharAt(sb.toString().length()-1);
        return sb;
    }

    public List<FinancialsYearly> findFinancialsYearly(List<CompanyIdNameDto> companyIdNameDtoList) throws Exception {
        StringBuilder inQueryValues = getInQueryPart(companyIdNameDtoList);
        String finalQuery = FINANCIALS_YEARLY_QUERY + inQueryValues.toString();
        List<FinancialsYearly> financialsYearlyList = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(finalQuery, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String companyName = row[0] != null ? row[0].toString().trim() : "";
                String period = row[1] != null ? row[1].toString().trim() : "";
                String revenue = row[2] != null ? row[2].toString().trim() : "";
                String operatingProfitMargin = row[3] != null ? row[3].toString().trim() : "";
                String profitAfterTax = row[4] != null ? row[4].toString().trim() : "";
                String eps = row[5] != null ? row[5].toString().trim() : "";
                String netOperatingCashFlow = row[6] != null ? row[6].toString().trim() : "";
                String roe = row[7] != null ? row[7].toString().trim() : "";
                financialsYearlyList.add(new FinancialsYearly(companyName, period,revenue,operatingProfitMargin,profitAfterTax,eps,netOperatingCashFlow,roe));
            }
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return financialsYearlyList;
    }

}
