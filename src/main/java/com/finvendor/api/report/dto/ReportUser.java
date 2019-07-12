package com.finvendor.api.report.dto;

import java.io.Serializable;
import java.util.List;

public class ReportUser implements Serializable {
    private String userName;
    private String userEmail;
    private String subscriptionType;
    private String subscriptionStatus;


    public ReportUser(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
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

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
