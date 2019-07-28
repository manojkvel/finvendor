package com.finvendor.api.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSubscriptionDto implements Serializable {

    private String userName;
    private String subscriptionType;
    private String subscriptionStatus;
    private String subscriptionStartTimeInMs;
    private String subscriptionEndTimeInMs;

    public UserSubscriptionDto(String userName, String subscriptionType, String subscriptionStatus, String subscriptionStartTimeInMs, String subscriptionEndTimeInMs) {
        this.userName = userName;
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionStartTimeInMs = subscriptionStartTimeInMs;
        this.subscriptionEndTimeInMs = subscriptionEndTimeInMs;
    }
}
