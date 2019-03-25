package com.finvendor.api.resources.companyprofile.companyprofile.dto;

public class CompanyNews {

    private String broadcastDate;
    private String subject;

    public CompanyNews(String broadcastDate, String subject) {
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
