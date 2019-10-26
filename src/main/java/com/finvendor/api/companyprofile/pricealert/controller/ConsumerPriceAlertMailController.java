package com.finvendor.api.companyprofile.pricealert.controller;

import com.finvendor.api.companyprofile.pricealert.dto.ConsumerPriceAlertDetails;
import com.finvendor.api.companyprofile.pricealert.service.ConsumerPriceAlertMailService;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import static com.finvendor.common.exception.ExceptionEnum.RESEARCH_REPORT_MAIL;
import static com.finvendor.common.exception.ExceptionEnum.UPDATE_PRICE;

@Controller
@RequestMapping(value = "/api")
public class ConsumerPriceAlertMailController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerPriceAlertMailController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private ConsumerPriceAlertMailService consumerPriceAlertMailService;


    @RequestMapping(value = "/sendpricealertmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendPriceAlertMail(@RequestBody StatusPojo statusPojo) throws WebApiException {
        try {
            Map<String, List<ConsumerPriceAlertDetails>> consumerPriceAlertDetailsMap = consumerPriceAlertMailService
                    .buildConsumerPriceAlertDetails().getConsumerPriceAlertDetailsMap();

            /*
             * For each consumer send email
             */
            for (final Map.Entry<String, List<ConsumerPriceAlertDetails>> entry : consumerPriceAlertDetailsMap.entrySet()) {
                String userName = entry.getKey();
                List<ConsumerPriceAlertDetails> ConsumerPriceAlertDetails = entry.getValue();

                String toRecipient = userService.getUserDetailsByUsername(userName).getEmail();
                // Each user can set multiple price alert
                for (ConsumerPriceAlertDetails consumerPriceAlertDetail : ConsumerPriceAlertDetails) {
                    String companyName = consumerPriceAlertDetail.getCompanyName();
                    final String subject = "Stock Price Alert Triggered for:" + companyName;
                    final String mailBody = prepareMailBody(consumerPriceAlertDetail);
                    try {
//                        if (mailBody.isEmpty()) {
//                            logger.warn("Unable to send mail to user {} due to Price Alert hit not occurred " +
//                                    "for Day,Week,Month or NoTimeFrame",userName);
//                        } else {
//                            EmailUtil.sendMail(toRecipient, subject, mailBody);
//                            LogUtil.logInfo("***Email has been sent to user:" + toRecipient);
//                        }
                        EmailUtil.sendMail(toRecipient, subject, mailBody);
                        LogUtil.logInfo("***Email has been sent to user:" + toRecipient);
                    } catch (RuntimeException e) {
                        LogUtil.logError("Unable to send mail to this company :" + companyName);
                    }
                }
            }
            return new ResponseEntity<>(new StatusPojo("true", "Mail sent to each user successfully"),
                    HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebScheduler -> sendMail(...) method", e);
            return ErrorUtil.getError(UPDATE_PRICE.getCode(), UPDATE_PRICE.getUserMessage(), e);
        }
    }

    private String prepareMailBody(ConsumerPriceAlertDetails dto) {
        String headerContentTemplate = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
                + "<title>Example</title>\r\n" + "\r\n" + "<!-- CSS -->\r\n" + "<style>\r\n" + ".myTable { \r\n"
                + "  width: 100%;\r\n" + "  text-align: left;\r\n" + "  background-color: white;\r\n"
                + "  border-collapse: collapse; \r\n" + "  }\r\n" + ".myTable th { \r\n"
                + "  background-color: mediumaquamarine;\r\n" + "  color: white; \r\n" + "  }\r\n" + ".myTable td, \r\n"
                + ".myTable th { \r\n" + "  padding: 10px;\r\n" + "  border: 1px solid mediumaquamarine; \r\n"
                + "  }\r\n" + "</style>\r\n" + "\r\n" + "</head>\r\n" + "<body>\r\n" + "Dear USERNAME<br> <br>\r\n"
                + "\r\n"
                + "Thank you for choosing <a href=\"www.finvendor.com\">Finvendor</a> as your preferred investment partner.<br><br>\r\n"
                + "\r\n" + "Alert on stock as on PRICEDATE<br><br>";
        String tableContentTemplate = "<table class=\"myTable\">\r\n" + "	<tr>\r\n"
                + "		<th>Company name</th>\r\n" + "		<th>Alert triggered</th>\r\n"
                + "      	<th>Today's price</th>\r\n" + "      	<th>Previous day price</th>\r\n"
                + "        <th>Price a week ago</th>\r\n" + "        <th>Price a month ago</th>\r\n" + "	</tr>\r\n"
                + "	<tr>\r\n" + "		<td>COMPANYNAME</td>\r\n" + "		<td>PERCENTAGE</td>\r\n"
                + "        <td>TODAYPRICE</td>\r\n" + "		<td>YESTERDAYPRICE</td>\r\n"
                + "        <td>WEEKPRICE</td>\r\n" + "        <td>MONTHPRICE</td>\r\n" + "	</tr>\r\n"
                + "</table><br><br>";
        String noTimeFrameTableContentTemplate = "<table class=\"myTable\">\r\n" + "	<tr>\r\n"
                + "		<th>Company name</th>\r\n" + "        <th>Price when alert was set</th>\r\n"
                + "		<th>Alert triggered</th>\r\n" + "      	<th>Today's price</th>\r\n"
                + "      	<th>Previous day price</th>\r\n" + "        <th>Price a week ago</th>\r\n"
                + "        <th>Price a month ago</th>\r\n" + "	</tr>\r\n" + "	<tr>\r\n"
                + "		<td>COMPANYNAME</td>\r\n" + "        <td>SINCEPRICE</td>\r\n" + "		<td>PERCENTAGE</td>\r\n"
                + "        <td>TODAYPRICE</td>\r\n" + "		<td>YESTERDAYPRICE</td>\r\n"
                + "        <td>WEEKPRICE</td>\r\n" + "        <td>MONTHPRICE</td>\r\n" + "	</tr>\r\n" + "</table>\r\n"
                + "<br><br>";
        final String footerContentTemplate = "\r\n"
                + "In case of any further queries or any assistance feel free to write us mail at sales@finvendor.com	 or contact our Customer support.<br><br>\r\n"
                + "Thank you once again for setting price alert for company COMPANYNAME and look forward to be rewarding and continued relationship.\r\n"
                + "<br><br>\r\n" + "\r\n" + "Assuring you the best of services.\r\n" + "<br><br>\r\n"
                + "Regards<br>\r\n" + "<a href=\"www.finvendor.com\">Finvendor</a> <br><br>\r\n" + "</body>\r\n" + "</html>";
        final String userName = dto.getUserName();
        final String priceDate = dto.getPriceDate();
        final String companyName = dto.getCompanyName();

        String yesterDayPrice = String.valueOf(dto.getYesterdayCmp());
        String todayPrice = String.valueOf(dto.getTodaysCmp());
        final String todayPriceInPercentage = dto.getTodaysCmpInPercentage();

        String weeklyPrice = String.valueOf(dto.getLastWeekCmp());
        final String lastWeekPriceInPercentage = dto.getLastWeekCmpInPercentage();

        String monthlyPrice = String.valueOf(dto.getLastMonthCmp());
        final String lastMonthPriceInPercentage = dto.getLastMonthCmpInPercentage();

        //Set - (hyphen) if price is 0.0
        todayPrice = "0.0".equals(todayPrice.trim()) ? "-" : todayPrice;
        yesterDayPrice = "0.0".equals(yesterDayPrice.trim()) ? "-" : yesterDayPrice;
        weeklyPrice = "0.0".equals(weeklyPrice.trim()) ? "-" : weeklyPrice;
        monthlyPrice = "0.0".equals(monthlyPrice.trim()) ? "-" : monthlyPrice;

        String dayTableContent = "";
        if (!"NA".equals(dto.getTodaysCmpInPercentage())) {
            dayTableContent = getTableBody(tableContentTemplate, companyName, yesterDayPrice, todayPrice, todayPriceInPercentage, weeklyPrice, monthlyPrice, "COMPANYNAME", "PERCENTAGE", "TODAYPRICE", "YESTERDAYPRICE", "WEEKPRICE", "MONTHPRICE");
        }

        String weekTableContent = "";
        if (!"NA".equals(dto.getLastWeekCmpInPercentage())) {
            weekTableContent = tableContentTemplate;
            weekTableContent = StringUtils.replace(weekTableContent, "COMPANYNAME", companyName);
            weekTableContent = StringUtils.replace(weekTableContent, "PERCENTAGE", lastWeekPriceInPercentage);
            weekTableContent = StringUtils.replace(weekTableContent, "TODAYPRICE", todayPrice);
            weekTableContent = StringUtils.replace(weekTableContent, "YESTERDAYPRICE", yesterDayPrice);
            weekTableContent = StringUtils.replace(weekTableContent, "WEEKPRICE", weeklyPrice);
            weekTableContent = StringUtils.replace(weekTableContent, "MONTHPRICE", monthlyPrice);
        }

        String monthTableContent = "";
        if (!"NA".equals(dto.getLastMonthCmpInPercentage())) {
            monthTableContent = tableContentTemplate;
            monthTableContent = StringUtils.replace(monthTableContent, "COMPANYNAME", companyName);
            monthTableContent = StringUtils.replace(monthTableContent, "PERCENTAGE", lastMonthPriceInPercentage);
            monthTableContent = StringUtils.replace(monthTableContent, "TODAYPRICE", todayPrice);
            monthTableContent = StringUtils.replace(monthTableContent, "YESTERDAYPRICE", yesterDayPrice);
            monthTableContent = StringUtils.replace(monthTableContent, "WEEKPRICE", weeklyPrice);
            monthTableContent = StringUtils.replace(monthTableContent, "MONTHPRICE", monthlyPrice);
        }

        String notTimeFrameTableContent = "";
        if (!"NA".equals(dto.getNoTimeFrameInPercentage())) {
            notTimeFrameTableContent = noTimeFrameTableContentTemplate;
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "COMPANYNAME", companyName);
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "SINCEPRICE",
                    dto.getCmpWhenPriceAlertWasSet());
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "PERCENTAGE",
                    dto.getNoTimeFrameInPercentage());
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "TODAYPRICE", todayPrice);
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "YESTERDAYPRICE", yesterDayPrice);
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "WEEKPRICE", weeklyPrice);
            notTimeFrameTableContent = StringUtils.replace(notTimeFrameTableContent, "MONTHPRICE", monthlyPrice);
        }

        String headerContent = headerContentTemplate;
        headerContent = StringUtils.replace(headerContent, "USERNAME", userName);
        headerContent = StringUtils.replace(headerContent, "PRICEDATE", priceDate);
        StringBuilder tableSb = new StringBuilder(900);
        if (!dayTableContent.isEmpty()) {
            tableSb.append(dayTableContent).append("\r\n");
        }
        if (!weekTableContent.isEmpty()) {
            tableSb.append(weekTableContent).append("\r\n");
        }
        if (!monthTableContent.isEmpty()) {
            tableSb.append(monthTableContent).append("\r\n");
        }
        if (!notTimeFrameTableContent.isEmpty()) {
            tableSb.append(notTimeFrameTableContent).append("\r\n");
        }
        String tableContent = tableSb.toString();
        if (tableContent.isEmpty()) {
            tableContent = "NA";
        }
        String footerContent = footerContentTemplate;
        footerContent = StringUtils.replace(footerContent, "COMPANYNAME", companyName);

        return headerContent + "\n" + tableContent + "\n" + footerContent + "\n";
    }

    private String getTableBody(String tableContentTemplate, String companyName, String yesterDayPrice, String todayPrice, String todayPriceInPercentage, String weeklyPrice, String monthlyPrice, String companyname, String percentage, String todayprice, String yesterdayprice, String weekprice, String monthprice) {
        String dayTableContent;
        dayTableContent = tableContentTemplate;
        dayTableContent = StringUtils.replace(dayTableContent, companyname, companyName);
        dayTableContent = StringUtils.replace(dayTableContent, percentage, todayPriceInPercentage);
        dayTableContent = StringUtils.replace(dayTableContent, todayprice, todayPrice);
        dayTableContent = StringUtils.replace(dayTableContent, yesterdayprice, yesterDayPrice);
        dayTableContent = StringUtils.replace(dayTableContent, weekprice, weeklyPrice);
        dayTableContent = StringUtils.replace(dayTableContent, monthprice, monthlyPrice);
        return dayTableContent;
    }

    @RequestMapping(value = "/sendrsearchreportmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendResearchReportAlertMail(String userName, String companyId, String companyName) throws WebApiException {
        try {
            final String isinCode = consumerPriceAlertMailService.getIsinCode(companyId);
            LogUtil.logInfo("sendResearchReportAlertMail-> isinCode=" + isinCode + ", companyName=" + companyName);
            String mailContent = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title></title>\r\n" + "\r\n"
                    + "<!-- CSS -->\r\n" + "<style>\r\n" + ".myTable { \r\n" + "  width: 100%;\r\n"
                    + "  text-align: left;\r\n" + "  background-color: white;\r\n" + "  border-collapse: collapse; \r\n"
                    + "  }\r\n" + ".myTable th { \r\n" + "  background-color: mediumaquamarine;\r\n"
                    + "  color: white; \r\n" + "  }\r\n" + ".myTable td, \r\n" + ".myTable th { \r\n"
                    + "  padding: 10px;\r\n" + "  border: 1px solid mediumaquamarine; \r\n" + "  }\r\n" + "</style>\r\n"
                    + "\r\n" + "</head>\r\n" + "<body>\r\n" + "Dear USERNAME<br> <br>\r\n" + "\r\n"
                    + "Thank you for choosing <a href=\"www.finvendor.com\">Finvendor</a> as your preferred investment partner.<br><br>\r\n"
                    + "\r\n" + "Alert on stock research report<br><br>\r\n" + "<!-- HTML -->\r\n"
                    + "<table class=\"myTable\">\r\n" + "	<tr>\r\n" + "		<th>Company name</th>\r\n"
                    + "		<th>Report alert triggered</th>\r\n" + "	</tr>\r\n" + "	<tr>\r\n"
                    + "		<td><a href=\"https://finvendor.com/view/company-profile.jsp?isinCode=" + isinCode
                    + "&txtSearchBox=Submit/\">COMPANYNAME</a></td>\r\n"
                    + "		<td>A New research report added for this stock</td>\r\n" + "	</tr>\r\n" + "</table>\r\n"
                    + "<br><br>\r\n"
                    + "In case of any further queries or any assistance feel free to write us mail at sales@finvendor.com	 or contact our Customer support.\r\n"
                    + "Thank you once again for setting research report alert for company COMPANYNAME and look forward to be rewarding and continued relationship.\r\n"
                    + "<br><br>\r\n" + "\r\n" + "Assuring you the best of services.\r\n" + "<br><br>\r\n"
                    + "Regards<br>\r\n" + "<a href=\"www.finvendor.com\">Finvendor</a><br><br>\r\n" + "</body>\r\n" + "</html>";

            mailContent = StringUtils.replace(mailContent, "USERNAME", userName);
            mailContent = StringUtils.replace(mailContent, "COMPANYNAME", companyName);

            String toRecipient;

            toRecipient = userService.getUserDetailsByUsername(userName).getEmail();
            String subject = "Stock Price Alert Triggered for:" + companyName;
            String mailBody = mailContent;
            EmailUtil.sendMail(toRecipient, subject, mailBody);
            return new ResponseEntity<>(new StatusPojo("true", "Research report mail sent to user successfully"), HttpStatus.OK);
        } catch (Exception e1) {
            ErrorUtil.logError("WebScheduler -> sendMailForResearchReport(...) method", e1);
            return ErrorUtil.getError(RESEARCH_REPORT_MAIL.getCode(), RESEARCH_REPORT_MAIL.getUserMessage(), e1);
        }
    }
}
