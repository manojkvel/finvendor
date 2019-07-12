package com.finvendor.api.report.service;

import com.finvendor.api.report.dao.ReportDao;
import com.finvendor.api.report.dto.ReportUser;
import com.finvendor.api.report.dto.corpaction.CorpActionPDFContent;
import com.finvendor.api.report.dto.financials.Financials;
import com.finvendor.api.report.dto.marketdatacontent.MarketDataContent;
import com.finvendor.api.report.dto.resultCalendar.ResultCalendarPDFContent;
import com.finvendor.api.report.dto.sectoral.SectoralDataPDFContent;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import com.finvendor.common.infra.pdf.IPDFGenerator;
import com.finvendor.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportService {
    private static List<String> quarterlyDateList;
    static {
        quarterlyDateList=new ArrayList<>();
        quarterlyDateList.add("31-March");
        quarterlyDateList.add("30-June");
        quarterlyDateList.add("30-Sep");
        quarterlyDateList.add("31-Dec");
    }

    private static final String LOC = "d:\\";
    //    private static final String LOC = "/home/finvendo/tmp/";
    private static final String MKT_PDF = LOC + "market_report.pdf";
    private static final String RESULT_CALENDAR_PDF = LOC + "result_calendar_report.pdf";
    private static final String CORP_ACTION_PDF = LOC + "crop_action_report.pdf";
    private static final String FINANCIALS_PDF = LOC + "financials_report.pdf";
    private static final String SECTORAL_PDF = LOC + "sectoral_report.pdf";

    @Autowired
    @Qualifier(value = "marketDataPDFContentBuilder")
    private IPDFContentBuilder<String, MarketDataContent> marketDataPDFContentBuilder;

    @Autowired
    @Qualifier(value = "corpActionPDFContentBuilder")
    private IPDFContentBuilder<String, CorpActionPDFContent> corpActionPDFContentBuilder;

    @Autowired
    @Qualifier(value = "resultCalendarPDFContentBuilder")
    private IPDFContentBuilder<String, ResultCalendarPDFContent> resultCalendarPDFContentBuilder;

    @Autowired
    @Qualifier(value = "financialsPDFContentBuilder")
    private IPDFContentBuilder<String, Financials> financialsPDFContentBuilder;

    @Autowired
    @Qualifier(value = "sectoralDataPDFContentBuilder")
    private IPDFContentBuilder<String, SectoralDataPDFContent> sectoralDataPDFContentBuilder;

    @Autowired
    @Qualifier(value = "marketDataPDFGenerator")
    private IPDFGenerator marketDataPDFGenerator;

    @Autowired
    @Qualifier(value = "resultCalendarPDFGenerator")
    private IPDFGenerator resultCalendarPDFGenerator;

    @Autowired
    @Qualifier(value = "corpActionPDFGenerator")
    private IPDFGenerator corpActionPDFGenerator;

    @Autowired
    @Qualifier(value = "financialsPDFGenerator")
    private IPDFGenerator financialsPDFGenerator;

    @Autowired
    @Qualifier(value = "sectoralDataPDFGenerator")
    private IPDFGenerator sectoralDataPDFGenerator;

    @Autowired
    private ReportDao dao;

    public List<ReportUser> findAllUsers(String sql) throws Exception {
        List<ReportUser> reportUsers;
        try {
            reportUsers = dao.findAllUsers(sql);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return reportUsers;
    }

    public void generateMarketReport(String userName) throws Exception {
        marketDataPDFGenerator.generate(marketDataPDFContentBuilder.buildContent(userName), MKT_PDF);
    }

    public void generateFinancialsReport(String userName) throws Exception {
        financialsPDFGenerator.generate(financialsPDFContentBuilder.buildContent(userName), FINANCIALS_PDF);
    }

    public void generateCorporateActionReport(String userName) throws Exception {
        corpActionPDFGenerator.generate(corpActionPDFContentBuilder.buildContent(userName), CORP_ACTION_PDF);
    }

    public void generateResultCalendarReport(String userName) throws Exception {
        resultCalendarPDFGenerator.generate(resultCalendarPDFContentBuilder.buildContent(userName), RESULT_CALENDAR_PDF);
    }

    public void generateSectoralReport(String userName) throws Exception {
        sectoralDataPDFGenerator.generate(sectoralDataPDFContentBuilder.buildContent(userName), SECTORAL_PDF);
    }


    public void sendReports() throws Exception {
        List<ReportUser> enabledUsers = findAllUsers(ReportDao.ENABLED_USERS);
        for (ReportUser ru : enabledUsers) {
            String userName = ru.getUserName();
            String subscriptionType = ru.getSubscriptionType();
            String userEmail = ru.getUserEmail();
            String subject = "FV Report Mail";
            String content = "Hello There,\n Please find all reports as an attachment\n\n\n\nFrom:\nFinvendor Team";
            if ("general".equals(subscriptionType) && "active".equals(ru.getSubscriptionStatus())) {
                generateMarketReport(userName);
                EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF});
            } else if ("smart".equals(subscriptionType) && "active".equals(ru.getSubscriptionStatus())) {
                generateMarketReport(userName);
                generateSectoralReport(userName);
                generateResultCalendarReport(userName);
                generateCorporateActionReport(userName);
                EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF, SECTORAL_PDF, RESULT_CALENDAR_PDF, CORP_ACTION_PDF});
            } else if ("sage".equals(subscriptionType) && "active".equals(ru.getSubscriptionStatus())) {
                generateMarketReport(userName);
                generateSectoralReport(userName);
                generateResultCalendarReport(userName);
                generateCorporateActionReport(userName);
                String currentDate="";
                if(quarterlyDateList.contains(currentDate)){

                }
//                if(DateUtils.getCurrentMonthDigit())
                /*
                IF current day is 31-Mar or 30-June or 30-Sep or 31-Dec) then
                    find company id from watch list table with given username
                    generateFinancialsReport(userName and company Names[]);
                    EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF, SECTORAL_PDF, RESULT_CALENDAR_PDF, CORP_ACTION_PDF, FINANCIALS_PDF});
                ELSE

                EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF, SECTORAL_PDF, RESULT_CALENDAR_PDF, CORP_ACTION_PDF});
                FI
                 */
            }
        }
    }


}
