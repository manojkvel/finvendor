package com.finvendor.api.subscription.dto;

import java.io.Serializable;
import java.util.List;

public class SubscriptionStateDto implements Serializable {
    private String subscriptionState;
    private List<String> subscriptionIds;

    public String getSubscriptionState() {
        return subscriptionState;
    }

    public void setSubscriptionState(String subscriptionState) {
        this.subscriptionState = subscriptionState;
    }

    public List<String> getSubscriptionIds() {
        return subscriptionIds;
    }

    public void setSubscriptionIds(List<String> subscriptionIds) {
        this.subscriptionIds = subscriptionIds;
    }
}
