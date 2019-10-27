package com.finvendor.api.login.dto;

import java.io.Serializable;

public class SubscriptionDto implements Serializable {
    private String subscriptionType;
    private String subscriptionStatus;
    private boolean trialPeriod;

    public SubscriptionDto(String subscriptionType, String subscriptionStatus, boolean trialPeriod) {
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
        this.trialPeriod = trialPeriod;
    }

    public SubscriptionDto(String subscriptionType, String subscriptionState) {
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionState;
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

    public boolean isTrialPeriod() {
        return trialPeriod;
    }

    public void setTrialPeriod(boolean trialPeriod) {
        this.trialPeriod = trialPeriod;
    }
}
