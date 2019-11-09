package com.finvendor.api.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionDto implements Serializable {
    //private String userName;
    //private String subscriptionDate;
    private String subscriptionType;
    //private String trialPeriodStartTime;
    //private String trialPeriodEndTime;
    //private String subscriptionStartTime;
    //private String subscriptionEndTime;
    //private String subscriptionState;
    private String transactionRefNumber;
    private String paymentMode;
    private Long transactionDate;
    private Double amountTransferred;
    private String bankName;
    private String bankHolderName;
    private Boolean paymentVerified;
    private Boolean renewSubscription;

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getTransactionRefNumber() {
        return transactionRefNumber;
    }

    public void setTransactionRefNumber(String transactionRefNumber) {
        this.transactionRefNumber = transactionRefNumber;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(Double amountTransferred) {
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

    public Boolean getPaymentVerified() {
        return paymentVerified;
    }

    public void setPaymentVerified(Boolean paymentVerified) {
        this.paymentVerified = paymentVerified;
    }

    public Boolean getRenewSubscription() {
        return renewSubscription;
    }

    public void setRenewSubscription(Boolean renewSubscription) {
        this.renewSubscription = renewSubscription;
    }
}
