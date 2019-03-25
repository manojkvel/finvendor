package com.finvendor.model.companyprofile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "company_news")
public class CompanyNews {

    @Id
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "companyNews")
    private Collection<CompanyNewsHistory> companyNewsHistories = new ArrayList<>();

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

    public Collection<CompanyNewsHistory> getCompanyNewsHistories() {
        return companyNewsHistories;
    }

    public void setCompanyNewsHistories(Collection<CompanyNewsHistory> companyNewsHistories) {
        this.companyNewsHistories = companyNewsHistories;
    }
}
