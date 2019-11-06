package com.finvendor.api.subscription.dto;

public class SubscriptionDetails {
    private String userId;
    private String subscriptionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override public String toString() {
        return "SubscriptionDetails{" +
                "userId='" + userId + '\'' +
                ", subscriptionId='" + subscriptionId + '\'' +
                '}';
    }
}
