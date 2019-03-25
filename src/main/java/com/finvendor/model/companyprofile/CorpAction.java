package com.finvendor.model.companyprofile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "company_news")
public class CorpAction {

    @Id
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "corpAction")
    private Collection<CorpActionHistory> corpActionHistories = new ArrayList<>();

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

    public Collection<CorpActionHistory> getCorpActionHistories() {
        return corpActionHistories;
    }

    public void setCorpActionHistories(Collection<CorpActionHistory> corpActionHistories) {
        this.corpActionHistories = corpActionHistories;
    }
}
