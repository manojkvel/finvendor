package com.finvendor.api.report.dto.resultCalendar;

import com.finvendor.common.infra.pdf.PDFContent;

import java.util.List;

public class ResultCalendarPDFContent extends PDFContent {
    private String userName;
    private List<ResultCalendar> resultCalendar;

    public ResultCalendarPDFContent(String userName, List<ResultCalendar> resultCalendar) {
        this.userName = userName;
        this.resultCalendar = resultCalendar;
    }

    public List<ResultCalendar> getResultCalendar() {
        return resultCalendar;
    }

    public String getUserName() {
        return userName;
    }
}
