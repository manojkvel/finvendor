package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "earning_preview")
public class EarningPreview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "isin")
    private String isin;

    @OneToMany(mappedBy = "earningPreview")
    private Collection<EarningPreviewQuarterly> quarterlyEarningPreview = new ArrayList<>();

    @OneToMany(mappedBy = "earningPreview")
    private Collection<EarningPreviewYearly> yearlyEarningPreview = new ArrayList<>();

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Collection<EarningPreviewQuarterly> getQuarterlyEarningPreview() {
        return quarterlyEarningPreview;
    }

    public void setQuarterlyEarningPreview(Collection<EarningPreviewQuarterly> quarterlyEarningPreview) {
        this.quarterlyEarningPreview = quarterlyEarningPreview;
    }

    public Collection<EarningPreviewYearly> getYearlyEarningPreview() {
        return yearlyEarningPreview;
    }

    public void setYearlyEarningPreview(Collection<EarningPreviewYearly> yearlyEarningPreview) {
        this.yearlyEarningPreview = yearlyEarningPreview;
    }
}
