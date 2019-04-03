package com.finvendor.api.resources.companyprofile.companyprofile.dto;

public class CorpActionDto {

    private String purpose;
    private String faceValue;
    private String exDate;
    private String recordDate;

    public CorpActionDto(String purpose, String faceValue, String exDate, String recordDate) {
        this.purpose = purpose;
        this.faceValue = faceValue;
        this.exDate = exDate;
        this.recordDate = recordDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
