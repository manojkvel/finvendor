package com.finvendor.api.report.dto;

import java.io.Serializable;

public class ReportUser implements Serializable {
    private String userName;
    private String userEmail;
    private String subscriptionType;
    private boolean subscriptionStatus;


    public ReportUser(String userName, String userEmail, String subscriptionType, boolean subscriptionStatus) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public boolean getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean isSubscriptionStatus() {
        return subscriptionStatus;
    }
}
