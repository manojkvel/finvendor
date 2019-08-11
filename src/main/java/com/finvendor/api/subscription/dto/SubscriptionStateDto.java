package com.finvendor.api.subscription.dto;

import java.io.Serializable;

public class SubscriptionStateDto implements Serializable {
    private String subscriptionState;

    public String getSubscriptionState() {
        return subscriptionState;
    }

    public void setSubscriptionState(String subscriptionState) {
        this.subscriptionState = subscriptionState;
    }
}
