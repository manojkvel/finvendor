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

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
public class ReportService {
    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

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

    public void sendReports() throws Exception {
        String LOCATION = finvendorProperties.getProperty("finvendo_tmp_path") + File.separator;

        String MKT_PDF_FILE_NAME = LOCATION + "market_report.pdf";
        String RESULT_CALENDAR_PDF_FILE_NAME = LOCATION + "result_calendar_report.pdf";
        String CORP_ACTION_PDF_FILE_NAME = LOCATION + "crop_action_report.pdf";
        String FINANCIALS_PDF_FILE_NAME = LOCATION + "financials_report.pdf";
        String SECTORAL_PDF_FILE_NAME = LOCATION + "sectoral_report.pdf";

        List<ReportUser> enabledUsers = findAllUsers(ReportDao.ENABLED_USERS);
        for (ReportUser ru : enabledUsers) {
            String userName = ru.getUserName();
            String userEmail = ru.getUserEmail();
            String subscriptionType = ru.getSubscriptionType();
            String subject = "FV Report Mail";
            String content = "Hello There,\n Please find all reports as an attachment\n\n\n\nFrom:\nFinvendor Team";
            switch (subscriptionType) {
                case "FREE":
                    generateMarketReport(userName, MKT_PDF_FILE_NAME);
                    EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF_FILE_NAME});
                    break;
                case "SMART":
                case "SAGE":
                    generateMarketReport(userName, MKT_PDF_FILE_NAME);
                    generateSectoralReport(userName, SECTORAL_PDF_FILE_NAME);
                    generateResultCalendarReport(userName, RESULT_CALENDAR_PDF_FILE_NAME);
                    generateCorporateActionReport(userName, CORP_ACTION_PDF_FILE_NAME);
                    EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF_FILE_NAME, SECTORAL_PDF_FILE_NAME, RESULT_CALENDAR_PDF_FILE_NAME, CORP_ACTION_PDF_FILE_NAME});
                    break;
//                    case "SAGE":
//                        generateMarketReport(userName, MKT_PDF_FILE_NAME);
//                        generateSectoralReport(userName, SECTORAL_PDF_FILE_NAME);
//                        generateResultCalendarReport(userName, RESULT_CALENDAR_PDF_FILE_NAME);
//                        generateCorporateActionReport(userName, CORP_ACTION_PDF_FILE_NAME);
//                        EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF_FILE_NAME, SECTORAL_PDF_FILE_NAME, RESULT_CALENDAR_PDF_FILE_NAME, CORP_ACTION_PDF_FILE_NAME});
//                        //send financial reports every quarterly
//                        String currentDate = DateUtils.getCurrentDateHaveMonthDigit();
//                        if (QTR_DATES.contains(currentDate)) {
//                            generateFinancialsReport(userName, FINANCIALS_PDF_FILE_NAME);
//                            EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF_FILE_NAME, SECTORAL_PDF_FILE_NAME, RESULT_CALENDAR_PDF_FILE_NAME, CORP_ACTION_PDF_FILE_NAME, FINANCIALS_PDF_FILE_NAME});
//                        } else {
//                            EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[]{MKT_PDF_FILE_NAME, SECTORAL_PDF_FILE_NAME, RESULT_CALENDAR_PDF_FILE_NAME, CORP_ACTION_PDF_FILE_NAME});
//                        }
//                        break;
            }
        }
    }

    public List<ReportUser> findAllUsers(String sql) throws Exception {
        List<ReportUser> reportUsers;
        try {
            reportUsers = dao.findAllUsers(sql);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
        return reportUsers;
    }

    public void generateMarketReport(String userName, String fileName) throws Exception {
        marketDataPDFGenerator.generate(marketDataPDFContentBuilder.buildContent(userName), fileName);
    }

    public void generateFinancialsReport(String userName, String fileName) throws Exception {
        financialsPDFGenerator.generate(financialsPDFContentBuilder.buildContent(userName), fileName);
    }

    public void generateCorporateActionReport(String userName, String fileName) throws Exception {
        corpActionPDFGenerator.generate(corpActionPDFContentBuilder.buildContent(userName), fileName);
    }

    public void generateResultCalendarReport(String userName, String fileName) throws Exception {
        resultCalendarPDFGenerator.generate(resultCalendarPDFContentBuilder.buildContent(userName), fileName);
    }

    public void generateSectoralReport(String userName, String fileName) throws Exception {
        sectoralDataPDFGenerator.generate(sectoralDataPDFContentBuilder.buildContent(userName), fileName);
    }
}
