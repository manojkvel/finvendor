package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "configuration")
public class Configuration implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "row_id")
    private int rowId;

    @Column(name = "email_enabled")
    private boolean emailEnabled;

    @Column(name = "trial_period_in_days")
    private int trialPeriodInDays;

    @Column(name = "reminder_days")
    private String reminderDays;

    @Column(name = "feature_access_daily_limit")
    private int featureAccessDailyLimit;

    @Column(name = "feature_access_weekly_limit")
    private int featureAccessWeeklyLimit;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public int getTrialPeriodInDays() {
        return trialPeriodInDays;
    }

    public void setTrialPeriodInDays(int trialPeriodInDays) {
        this.trialPeriodInDays = trialPeriodInDays;
    }

    public String getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(String reminderDays) {
        this.reminderDays = reminderDays;
    }

    public int getFeatureAccessDailyLimit() {
        return featureAccessDailyLimit;
    }

    public void setFeatureAccessDailyLimit(int featureAccessDailyLimit) {
        this.featureAccessDailyLimit = featureAccessDailyLimit;
    }

    public int getFeatureAccessWeeklyLimit() {
        return featureAccessWeeklyLimit;
    }

    public void setFeatureAccessWeeklyLimit(int featureAccessWeeklyLimit) {
        this.featureAccessWeeklyLimit = featureAccessWeeklyLimit;
    }
}
