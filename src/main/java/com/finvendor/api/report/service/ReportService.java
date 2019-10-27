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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Properties;

import static com.finvendor.common.constant.AppConstants.FV_SUPPORT_EMAIL;

@Service
@Transactional
public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    private final IPDFContentBuilder<String, MarketDataContent> marketDataPDFContentBuilder;
    private final IPDFContentBuilder<String, CorpActionPDFContent> corpActionPDFContentBuilder;
    private final IPDFContentBuilder<String, ResultCalendarPDFContent> resultCalendarPDFContentBuilder;
    private final IPDFContentBuilder<String, Financials> financialsPDFContentBuilder;
    private final IPDFContentBuilder<String, SectoralDataPDFContent> sectoralDataPDFContentBuilder;
    private final IPDFGenerator marketDataPDFGenerator;
    private final IPDFGenerator resultCalendarPDFGenerator;
    private final IPDFGenerator corpActionPDFGenerator;
    private final IPDFGenerator financialsPDFGenerator;
    private final IPDFGenerator sectoralDataPDFGenerator;
    private final ReportDao reportDao;

    @Autowired
    public ReportService(
            @Qualifier(value = "sectoralDataPDFContentBuilder") IPDFContentBuilder<String, SectoralDataPDFContent> sectoralDataPDFContentBuilder,
            @Qualifier(value = "marketDataPDFContentBuilder") IPDFContentBuilder<String, MarketDataContent> marketDataPDFContentBuilder,
            @Qualifier(value = "corpActionPDFContentBuilder") IPDFContentBuilder<String, CorpActionPDFContent> corpActionPDFContentBuilder,
            @Qualifier(value = "resultCalendarPDFContentBuilder") IPDFContentBuilder<String, ResultCalendarPDFContent> resultCalendarPDFContentBuilder,
            @Qualifier(value = "financialsPDFContentBuilder") IPDFContentBuilder<String, Financials> financialsPDFContentBuilder,
            @Qualifier(value = "marketDataPDFGenerator") IPDFGenerator marketDataPDFGenerator,
            @Qualifier(value = "resultCalendarPDFGenerator") IPDFGenerator resultCalendarPDFGenerator,
            @Qualifier(value = "corpActionPDFGenerator") IPDFGenerator corpActionPDFGenerator,
            @Qualifier(value = "financialsPDFGenerator") IPDFGenerator financialsPDFGenerator,
            @Qualifier(value = "sectoralDataPDFGenerator") IPDFGenerator sectoralDataPDFGenerator, ReportDao reportDao) {
        this.sectoralDataPDFContentBuilder = sectoralDataPDFContentBuilder;
        this.marketDataPDFContentBuilder = marketDataPDFContentBuilder;
        this.corpActionPDFContentBuilder = corpActionPDFContentBuilder;
        this.resultCalendarPDFContentBuilder = resultCalendarPDFContentBuilder;
        this.financialsPDFContentBuilder = financialsPDFContentBuilder;
        this.marketDataPDFGenerator = marketDataPDFGenerator;
        this.resultCalendarPDFGenerator = resultCalendarPDFGenerator;
        this.corpActionPDFGenerator = corpActionPDFGenerator;
        this.financialsPDFGenerator = financialsPDFGenerator;
        this.sectoralDataPDFGenerator = sectoralDataPDFGenerator;
        this.reportDao = reportDao;
    }

    public void sendReports() throws Exception {
        String LOCATION = finvendorProperties.getProperty("finvendo_tmp_path") + File.separator;
        String MKT_PDF_FILE_NAME = LOCATION + "market_report.pdf";
        String RESULT_CALENDAR_PDF_FILE_NAME = LOCATION + "result_calendar_report.pdf";
        String CORP_ACTION_PDF_FILE_NAME = LOCATION + "crop_action_report.pdf";
        String FINANCIALS_PDF_FILE_NAME = LOCATION + "financials_report.pdf";
        String SECTORAL_PDF_FILE_NAME = LOCATION + "sectoral_report.pdf";
        List<ReportUser> enabledUsers = findAllUsers();
        for (ReportUser ru : enabledUsers) {
            String userName = ru.getUserName();
            String userEmail = ru.getUserEmail();
            String subscriptionType = ru.getSubscriptionType();
            String subject = "FV Report Mail";
            String content = "Hello There,\n Please find all reports as an attachment\n\n\n\nFrom:\nFinvendor Team";
            switch (subscriptionType) {
            case "FREE":
                generateMarketReport(userName, MKT_PDF_FILE_NAME);
                try {
                    EmailUtil.sendMailWithAttachment(userEmail, subject, content, new String[] { MKT_PDF_FILE_NAME });
                } catch (Exception e) {
                    sendMailToSupportTeamOnEmailFailed(userName, userEmail);
                }
                break;
            case "SMART":
            case "SAGE":
                generateMarketReport(userName, MKT_PDF_FILE_NAME);
                generateSectoralReport(userName, SECTORAL_PDF_FILE_NAME);
                generateResultCalendarReport(userName, RESULT_CALENDAR_PDF_FILE_NAME);
                generateCorporateActionReport(userName, CORP_ACTION_PDF_FILE_NAME);
                try {
                    EmailUtil.sendMailWithAttachment(userEmail, subject, content,
                            new String[] { MKT_PDF_FILE_NAME, SECTORAL_PDF_FILE_NAME, RESULT_CALENDAR_PDF_FILE_NAME,
                                    CORP_ACTION_PDF_FILE_NAME });
                } catch (Exception e) {
                    sendMailToSupportTeamOnEmailFailed(userName, userEmail);
                }
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

    private void sendMailToSupportTeamOnEmailFailed(String userName, String userEmail) {
        logger.info("Unable to send email to this user: {}, userEmail: {}", userName, userEmail);
        String contentForFinVendorSupportTeam = "Unable to send report email to the user. Please verify this user";
        EmailUtil.sendMail(FV_SUPPORT_EMAIL, "Undelivered daily market report to user - " + userName, contentForFinVendorSupportTeam);
    }

    public List<ReportUser> findAllUsers() throws Exception {
        List<ReportUser> reportUsers;
        try {
            reportUsers = reportDao.findAllUsers();
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
