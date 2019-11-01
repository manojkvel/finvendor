package com.finvendor.api.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserSubscriptionDto implements Serializable {

    private String userName;
    private String subscriptionType;
    private String subscriptionState;
    private String subscriptionStartTimeInMs;
    private String subscriptionEndTimeInMs;
    private String subscriptionStartTime;
    private String subscriptionEndTime;
    private String trialPeriodStartTimeInMs;
    private String trialPeriodEndTimeInMs;

    private String trialPeriodStartTime;
    private String trialPeriodEndTime;

    private boolean userInTrialPeriod;

    public UserSubscriptionDto(String userName, String subscriptionType, String subscriptionStatus, String subscriptionStartTime,
            String subscriptionEndTimeInMs, String subscriptionStartTime1, String subscriptionEndTime,
            String trialPeriodStartTimeInMs,
            String trialPeriodEndTimeInMs, String trialPeriodStartTime, String trialPeriodEndTime, boolean userInTrialPeriod) {
        this.userName = userName;
        this.subscriptionType = subscriptionType;
        this.subscriptionState = subscriptionStatus;
        this.subscriptionStartTimeInMs = subscriptionStartTime;
        this.subscriptionEndTimeInMs = subscriptionEndTimeInMs;
        this.subscriptionStartTime = subscriptionStartTime1;
        this.subscriptionEndTime = subscriptionEndTime;
        this.trialPeriodStartTimeInMs = trialPeriodStartTimeInMs;
        this.trialPeriodEndTimeInMs = trialPeriodEndTimeInMs;
        this.trialPeriodStartTime = trialPeriodStartTime;
        this.trialPeriodEndTime = trialPeriodEndTime;
        this.userInTrialPeriod = userInTrialPeriod;
    }
}
