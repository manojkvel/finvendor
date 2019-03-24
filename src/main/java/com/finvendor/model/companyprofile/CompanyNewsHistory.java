package com.finvendor.model.companyprofile;

import javax.persistence.*;

@Entity
@Table(name = "company_news_history")
public class CompanyNewsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rowId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "broadcast_date")
    private String broadcastDate;

    @ManyToOne
    @JoinColumn(name = "ticker")
    private CompanyNews companyNews;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBroadcastDate() {
        return broadcastDate;
    }

    public void setBroadcastDate(String broadcastDate) {
        this.broadcastDate = broadcastDate;
    }

    public CompanyNews getCompanyNews() {
        return companyNews;
    }

    public void setCompanyNews(CompanyNews companyNews) {
        this.companyNews = companyNews;
    }
}
