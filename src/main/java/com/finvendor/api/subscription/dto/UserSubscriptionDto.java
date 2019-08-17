package com.finvendor.api.subscription.dto;

import java.io.Serializable;

public class UserSubscriptionDto implements Serializable {
    private String subscriptionType;
    private String subscriptionState;

    public UserSubscriptionDto(String subscriptionType, String subscriptionState) {
        this.subscriptionType = subscriptionType;
        this.subscriptionState = subscriptionState;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionState() {
        return subscriptionState;
    }

    public void setSubscriptionState(String subscriptionState) {
        this.subscriptionState = subscriptionState;
    }
}
