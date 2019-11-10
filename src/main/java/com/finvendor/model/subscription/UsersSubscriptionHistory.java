package com.finvendor.model.subscription;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User Subscription History Entity
 */
@Entity
@Table(name = "users_subscription_history")
public class UsersSubscriptionHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_id")
    private Integer rowId;

    @Column(name = "username")
    private String userName;

    @Column(name="subscription_date")
    private String subscriptionDate;

    @Column(name="subscription_type")
    private String subscriptionType;

    @Column(name="trial_period_start_time")
    private String trialPeriodStartTime;

    @Column(name="trial_period_end_time")
    private String trialPeriodEndTime;

    @Column(name="subscription_start_time")
    private String subscriptionStartTime;

    @Column(name="subscription_end_time")
    private String subscriptionEndTime;

    @Column(name="subscription_state")
    private String subscriptionState;

    @Column(name = "transaction_ref_number")
    private String transactionRefNumber;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "transaction_for")
    private String transactionFor;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "amt_transferred")
    private String amountTransferred;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_holder_name")
    private String bankHolderName;

    @Column(name = "payment_verified")
    private String paymentVerified;

    @Column(name = "inventory_date")
    private String inventoryDate;

    @Column(name = "subscription_id")
    private String subscriptionId;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
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

    public String getSubscriptionState() {
        return subscriptionState;
    }

    public void setSubscriptionState(String subscriptionState) {
        this.subscriptionState = subscriptionState;
    }

    public String getTransactionRefNumber() {
        return transactionRefNumber;
    }

    public void setTransactionRefNumber(String transactionRefNumber) {
        this.transactionRefNumber = transactionRefNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionFor() {
        return transactionFor;
    }

    public void setTransactionFor(String transactionFor) {
        this.transactionFor = transactionFor;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(String amountTransferred) {
        this.amountTransferred = amountTransferred;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public void setBankHolderName(String bankHolderName) {
        this.bankHolderName = bankHolderName;
    }

    public String getPaymentVerified() {
        return paymentVerified;
    }

    public void setPaymentVerified(String paymentVerified) {
        this.paymentVerified = paymentVerified;
    }

    public String getInventoryDate() {
        return inventoryDate;
    }

    public void setInventoryDate(String inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}