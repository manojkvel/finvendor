package com.finvendor.model.companyprofile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "company_calendar")
public class CompanyCalendar {

    @Id
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "companyCalendar")
    private Collection<CompanyCalendarHistory> companyCalendarHistories = new ArrayList<>();

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Collection<CompanyCalendarHistory> getCompanyCalendarHistories() {
        return companyCalendarHistories;
    }

    public void setCompanyCalendarHistories(Collection<CompanyCalendarHistory> companyCalendarHistories) {
        this.companyCalendarHistories = companyCalendarHistories;
    }
}
