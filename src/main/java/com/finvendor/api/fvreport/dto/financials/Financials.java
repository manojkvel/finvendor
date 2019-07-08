package com.finvendor.api.fvreport.dto.financials;

import com.finvendor.common.infra.pdf.PDFContent;

import java.util.List;

public class Financials extends PDFContent {
    private final List<FinancialsQuarterly> financialsQuarterly;
    private final List<FinancialsYearly> financialsYearly;
    private String userName;

    public Financials(String userName, List<FinancialsQuarterly> financialsQuarterly, List<FinancialsYearly> financialsYearly) {
        super();
        this.userName = userName;
        this.financialsQuarterly = financialsQuarterly;
        this.financialsYearly = financialsYearly;
    }

    public String getUserName() {
        return userName;
    }

    public List<FinancialsQuarterly> getFinancialsQuarterly() {
        return financialsQuarterly;
    }

    public List<FinancialsYearly> getFinancialsYearly() {
        return financialsYearly;
    }
}
