package com.finvendor.api.configuration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysConfigDto implements Serializable {
    private Boolean emailEnabled;
    private Integer trialPeriodInDays;
    private String reminderDays;
    private Integer featureAccessDailyLimit;
    private Integer featureAccessWeeklyLimit;

    public Boolean getEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(Boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public Integer getTrialPeriodInDays() {
        return trialPeriodInDays;
    }

    public void setTrialPeriodInDays(Integer trialPeriodInDays) {
        this.trialPeriodInDays = trialPeriodInDays;
    }

    public String getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(String reminderDays) {
        this.reminderDays = reminderDays;
    }

    public Integer getFeatureAccessDailyLimit() {
        return featureAccessDailyLimit;
    }

    public void setFeatureAccessDailyLimit(Integer featureAccessDailyLimit) {
        this.featureAccessDailyLimit = featureAccessDailyLimit;
    }

    public Integer getFeatureAccessWeeklyLimit() {
        return featureAccessWeeklyLimit;
    }

    public void setFeatureAccessWeeklyLimit(Integer featureAccessWeeklyLimit) {
        this.featureAccessWeeklyLimit = featureAccessWeeklyLimit;
    }
}
