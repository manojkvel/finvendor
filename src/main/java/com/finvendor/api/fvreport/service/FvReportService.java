package com.finvendor.api.fvreport.service;

import com.finvendor.api.fvreport.dao.FvReportDao;
import com.finvendor.api.fvreport.dto.ReportUser;
import com.finvendor.api.fvreport.dto.corpaction.CorpActionPDFContent;
import com.finvendor.api.fvreport.dto.financials.Financials;
import com.finvendor.api.fvreport.dto.marketdatacontent.MarketDataContent;
import com.finvendor.api.fvreport.dto.resultCalendar.ResultCalendarPDFContent;
import com.finvendor.api.fvreport.dto.sectoral.Sectoral;
import com.finvendor.api.fvreport.dto.sectoral.SectoralDataPDFContent;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import com.finvendor.common.infra.pdf.IPDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FvReportService {

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
    private FvReportDao dao;

    public List<ReportUser> findAllUsers(String sql) throws Exception {
        List<ReportUser> reportUsers;
        try {
            reportUsers = dao.findAllUsers(sql);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return reportUsers;
    }

    public void sendReport(String userName) throws Exception {
        boolean isMarketReportDone = marketDataPDFGenerator.generate(marketDataPDFContentBuilder.buildContent(userName), MKT_PDF);
        boolean isResultCalendarDone = resultCalendarPDFGenerator.generate(resultCalendarPDFContentBuilder.buildContent(userName), RESULT_CALENDAR_PDF);
        boolean isCorpActionDone = corpActionPDFGenerator.generate(corpActionPDFContentBuilder.buildContent(userName), CORP_ACTION_PDF);
        boolean isFinancialsDone = financialsPDFGenerator.generate(financialsPDFContentBuilder.buildContent(userName), FINANCIALS_PDF);
        boolean isSectoralDone = sectoralDataPDFGenerator.generate(sectoralDataPDFContentBuilder.buildContent(userName), SECTORAL_PDF);
//        if(isMktDataReportGenerated&&isResultCalendarReportGenerated&&isCorpActionReportGenerated){
            //send mail
//      /  EmailUtil.sendMailWithAttachment("jbytrain@gmail.com", "FV Report Mail", "Hello There,\n Please find all reports as an attachment\n\n\n\nFrom:\nFinvendor Team",
//                new String[] { MKT_PDF, RESULT_CALENDAR_PDF, CORP_ACTION_PDF });
//            EmailUtil.sendMail("jbytrain@gmail.com","TestMail","Hey you have many attachments");
//        }
    }


}
