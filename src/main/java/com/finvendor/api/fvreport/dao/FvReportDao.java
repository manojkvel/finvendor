package com.finvendor.api.fvreport.dao;

import com.finvendor.api.fvreport.dto.ReportUser;
import com.finvendor.api.fvreport.dto.corpaction.CorpAction;
import com.finvendor.api.fvreport.dto.resultCalendar.ResultCalendar;
import com.finvendor.common.commondao.ICommonDao;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FvReportDao {
    private static final String ENABLED_USERS = "select a.username,a.email from users a where a.enabled=1";
    private static final String RESULT_CALENDAR_QUERY = "select a.board_meeting_date,b.company_name from company_calendar_history a,rsch_sub_area_company_dtls b where a.ticker=b.ticker and a.purpose like '%Financial Results%' and STR_TO_DATE(a.board_meeting_date, '%d-%M-%Y')>=NOW()";
    private static final String CORP_ACTION_QUERY = "select b.company_name,a.* from corp_action_history a,rsch_sub_area_company_dtls b where a.ticker=b.ticker  and STR_TO_DATE(a.ex_date, '%d-%M-%Y')>NOW()";

    @Autowired
    private ICommonDao commonDao;

    public List<ReportUser> findAllUsers() throws Exception {
        List<ReportUser> reportUsers = new ArrayList<>();
        try {
            SQLQuery query1 = commonDao.getNativeQuery(ENABLED_USERS, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : "";
                String email = row[1] != null ? row[1].toString().trim() : "";
                reportUsers.add(new ReportUser(userName, email));
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


}
