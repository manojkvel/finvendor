package com.finvendor.api.companyprofile.companyprofile.dto;

public class CompanyNewsDto {

    private String broadcastDate;
    private String subject;

    public CompanyNewsDto(String broadcastDate, String subject) {
        this.broadcastDate = broadcastDate;
        this.subject = subject;
    }

    public String getBroadcastDate() {
        return broadcastDate;
    }

    public void setBroadcastDate(String broadcastDate) {
        this.broadcastDate = broadcastDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
