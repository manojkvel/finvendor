package com.finvendor.api.login.dto;

public class SubscriptionDto {
    private String subscriptionType;
    private String subscriptionStatus;

    public SubscriptionDto(String subscriptionType, String subscriptionStatus) {
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
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
}
