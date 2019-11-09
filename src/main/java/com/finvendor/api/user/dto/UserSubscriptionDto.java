package com.finvendor.api.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserSubscriptionDto implements Serializable {
    private String subscriptionId;
    private String userName;
    private String previousSubscriptionType;
    private String previousSubscriptionState;
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
    private String generalMsg;

    public UserSubscriptionDto(String subscriptionId, String userName, String previousSubscriptionType,
            String previousSubscriptionState, String subscriptionType,
            String subscriptionStatus,
            String subscriptionStartTime,
            String subscriptionEndTimeInMs, String subscriptionStartTime1, String subscriptionEndTime,
            String trialPeriodStartTimeInMs,
            String trialPeriodEndTimeInMs, String trialPeriodStartTime, String trialPeriodEndTime, boolean userInTrialPeriod,
            String generalMsg) {
        this.subscriptionId = subscriptionId;
        this.userName = userName;
        this.previousSubscriptionType = previousSubscriptionType;
        this.previousSubscriptionState = previousSubscriptionState;
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
        this.generalMsg = generalMsg;
    }

    public String getPreviousSubscriptionType() {
        return previousSubscriptionType;
    }

    public void setPreviousSubscriptionType(String previousSubscriptionType) {
        this.previousSubscriptionType = previousSubscriptionType;
    }

    public String getPreviousSubscriptionState() {
        return previousSubscriptionState;
    }

    public void setPreviousSubscriptionState(String previousSubscriptionState) {
        this.previousSubscriptionState = previousSubscriptionState;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getSubscriptionStartTimeInMs() {
        return subscriptionStartTimeInMs;
    }

    public void setSubscriptionStartTimeInMs(String subscriptionStartTimeInMs) {
        this.subscriptionStartTimeInMs = subscriptionStartTimeInMs;
    }

    public String getSubscriptionEndTimeInMs() {
        return subscriptionEndTimeInMs;
    }

    public void setSubscriptionEndTimeInMs(String subscriptionEndTimeInMs) {
        this.subscriptionEndTimeInMs = subscriptionEndTimeInMs;
    }

    public String getSubscriptionStartTime() {
        return subscriptionStartTime;
    }

    public void setSubscriptionStartTime(String subscriptionStartTime) {
        this.subscriptionStartTime = subscriptionStartTime;
    }

    public String getSubscriptionEndTime() {
        return subscriptionEndTime;
    }

    public void setSubscriptionEndTime(String subscriptionEndTime) {
        this.subscriptionEndTime = subscriptionEndTime;
    }

    public String getTrialPeriodStartTimeInMs() {
        return trialPeriodStartTimeInMs;
    }

    public void setTrialPeriodStartTimeInMs(String trialPeriodStartTimeInMs) {
        this.trialPeriodStartTimeInMs = trialPeriodStartTimeInMs;
    }

    public String getTrialPeriodEndTimeInMs() {
        return trialPeriodEndTimeInMs;
    }

    public void setTrialPeriodEndTimeInMs(String trialPeriodEndTimeInMs) {
        this.trialPeriodEndTimeInMs = trialPeriodEndTimeInMs;
    }

    public String getTrialPeriodStartTime() {
        return trialPeriodStartTime;
    }

    public void setTrialPeriodStartTime(String trialPeriodStartTime) {
        this.trialPeriodStartTime = trialPeriodStartTime;
    }

    public String getTrialPeriodEndTime() {
        return trialPeriodEndTime;
    }

    public void setTrialPeriodEndTime(String trialPeriodEndTime) {
        this.trialPeriodEndTime = trialPeriodEndTime;
    }

    public boolean isUserInTrialPeriod() {
        return userInTrialPeriod;
    }

    public void setUserInTrialPeriod(boolean userInTrialPeriod) {
        this.userInTrialPeriod = userInTrialPeriod;
    }

    public String getGeneralMsg() {
        return generalMsg;
    }

    public void setGeneralMsg(String generalMsg) {
        this.generalMsg = generalMsg;
    }

    @Override public String toString() {
        return "UserSubscriptionDto{" +
                "userName='" + userName + '\'' +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", subscriptionState='" + subscriptionState + '\'' +
                ", subscriptionStartTimeInMs='" + subscriptionStartTimeInMs + '\'' +
                ", subscriptionEndTimeInMs='" + subscriptionEndTimeInMs + '\'' +
                ", subscriptionStartTime='" + subscriptionStartTime + '\'' +
                ", subscriptionEndTime='" + subscriptionEndTime + '\'' +
                ", trialPeriodStartTimeInMs='" + trialPeriodStartTimeInMs + '\'' +
                ", trialPeriodEndTimeInMs='" + trialPeriodEndTimeInMs + '\'' +
                ", trialPeriodStartTime='" + trialPeriodStartTime + '\'' +
                ", trialPeriodEndTime='" + trialPeriodEndTime + '\'' +
                ", userInTrialPeriod=" + userInTrialPeriod +
                ", generalMsg='" + generalMsg + '\'' +
                '}';
    }
}
