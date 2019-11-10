package com.finvendor.api.notification.service;

import com.finvendor.api.notification.dto.EmailDataDto;
import com.finvendor.common.util.DateUtils;

public final class EmailContentProvider {

    private static final String BR = "<br>";

    private EmailContentProvider() {
    }

    public static EmailDataDto marketReportEmailData(String userName) {
        EmailDataDto emailDataDto = new EmailDataDto();
        String currentDate = DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format();
        String subject = "FinVendor - Daily Market & Sectoral Performance Summary Report for - (" + currentDate + ")";
        String content = "Dear " + userName
                + BR + BR
                + "Reports prepared by FinVendor Team keeps you abreast with all the stock markets related updates. "
                + BR + BR
                + "Please find attached the today's Reports (" + currentDate + "):"
                + BR + BR
                + "1. Daily Market Summary Reports - A Summary of Nifty50, NiftyMidCap100, NiftySmallCap100 Index today's performance "
                + BR + "   and overall market sentiments."
                + BR + BR
                + "2. Sectoral Performance Summary Reports - A Summary of all Major NSE Sectoral Index today's Performance Summary."
                + BR + BR + BR
                + "Many thanks for Reading the report. " + BR
                + "FinVendor Team";
        emailDataDto.setSubject(subject);
        emailDataDto.setContent(content);
        return emailDataDto;
    }

    public static EmailDataDto smartReportEmailData(String userName) {
        EmailDataDto emailDataDto = new EmailDataDto();
        String currentDate = DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format();
        String subject = "FinVendor - Daily Market Summary , Result Calendar & Corporate Action Reports (" + currentDate + ")";
        String content = "Dear " + userName
                + BR + BR
                + "Reports prepared by FinVendor Team keeps you abreast with all the stock markets related updates. " + BR + BR
                + "Please find attached the today's Reports (" + currentDate + "):"
                + "1. Daily Market Summary Reports - A Summary of Nifty50, NiftyMidCap100, NiftySmallCap100 Index today's performance " + BR
                + "   and overall market sentiments." + BR + BR
                + "2. Sectoral Performance Summary Reports - A Summary of all Major NSE Sectoral Index today's Performance Summary. " + BR
                + BR
                + "3. Financial Results Calendar - A List of Companies which are going to declare their Quarterly/Annual results " + BR
                + "   in next 2 weeks." + BR + BR
                + "4. Corporate Actions Summary report - Corporate Actions (Stock splits, dividend declaration, bonus shares, etc.)" + BR
                + "   For the Companies under your Watchlist." + BR + BR
                + "Many thanks for Reading the report. " + BR
                + "FinVendor Team";
        emailDataDto.setSubject(subject);
        emailDataDto.setContent(content);
        return emailDataDto;
    }

    public static EmailDataDto sageReportEmailData(String userName) {
        EmailDataDto emailDataDto = new EmailDataDto();
        String currentDate = DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format();
        String subject =
                "FinVendor - Daily Market Summary , Result Calendar , Corporate Action & Financial Result Summary Reports (" + currentDate
                        + ")";
        String content = "Dear " + userName
                + BR + BR
                + "Reports prepared by FinVendor Team keeps you abreast with all the stock markets related updates. " + BR + BR
                + "Please find attached the today's Reports (" + currentDate + "):" + BR + BR
                + "1. Daily Market Summary Reports - A Summary of Nifty50, NiftyMidCap100, NiftySmallCap100 Index today's performance " + BR
                + "   and overall market sentiments." + BR + BR
                + "2. Sectoral Performance Summary Reports - A Summary of all Major NSE Sectoral Index today's Performance Summary." + BR
                + BR
                + "3. Financial Results Calendar - A List of Companies which are going to declare their Quarterly/Annual results " + BR
                + "   in next 2 weeks." + BR + BR
                + "4. Corporate Actions Summary report - Corporate Actions (Stock splits, dividend declaration, bonus shares, etc.)\n"
                + "   For the Companies under your Watchlist. " + BR + BR
                + "5. Financial Results Summary - Recent Quarterly/Annual financial results summary of companies under your watchlist." + BR + BR
                + "Many thanks for Reading the report. " + BR
                + "FinVendor Team"
                ;
        emailDataDto.setSubject(subject);
        emailDataDto.setContent(content);
        return emailDataDto;
    }
}
