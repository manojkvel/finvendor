package com.finvendor.api.fvreport.dto.resultCalendar;

import java.io.Serializable;

public class ResultCalendar implements Serializable {

    private String date;
    private String companyName;

    public ResultCalendar(String date, String companyName) {
        this.date = date;
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public String getCompanyName() {
        return companyName;
    }
}
