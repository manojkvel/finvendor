package com.finvendor.api.report.dto.corpaction;

import java.io.Serializable;

public class CorpAction implements Serializable {
    private final String ticker;
    private final String purpose;
    private final String faceValue;
    private String exDate;
    private String companyName;
    private String recordDate;

    public CorpAction(String companyName, String ticker, String purpose, String faceValue, String exDate, String recordDate) {
        this.recordDate = recordDate;
        this.ticker = ticker;
        this.purpose = purpose;
        this.faceValue = faceValue;
        this.exDate = exDate;
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public String getExDate() {
        return exDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRecordDate() {
        return recordDate;
    }
}
