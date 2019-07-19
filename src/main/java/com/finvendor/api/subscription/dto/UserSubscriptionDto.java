package com.finvendor.api.subscription.dto;

import java.io.Serializable;

public class UserSubscriptionDto implements Serializable {
    private String subscriptionType;

    public UserSubscriptionDto(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
