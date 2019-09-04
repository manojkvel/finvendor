package com.finvendor.api.subscription.dto;

import java.io.Serializable;
import java.util.List;

public class SubscriptionStateDto implements Serializable {
    private String subscriptionState;

    private List<SubscriptionDetails> data;

    public String getSubscriptionState() {
        return subscriptionState;
    }

    public void setSubscriptionState(String subscriptionState) {
        this.subscriptionState = subscriptionState;
    }

    public List<SubscriptionDetails> getData() {
        return data;
    }

    public void setData(List<SubscriptionDetails> data) {
        this.data = data;
    }
}
