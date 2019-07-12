package com.finvendor.api.login.dto;

public class SubscriptionDto {
    private String subscriptionType;
    private boolean subscriptionStatus;

    public SubscriptionDto(String subscriptionType, boolean subscriptionStatus) {
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
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
}
