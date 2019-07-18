package com.finvendor.api.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"subscriptionId", "subscriptionType", "subscriptionStartTimeInMillis",
        "subscriptionEndTimeInMillis", "userName", "transactionRefNumber", "transactionDate", "paymentMode", "amountTransferred",
        "bankName", "bankHolderName", "paymentVerified"})
public class UserPaymentDto implements Serializable {

    private String subscriptionId;
    private String subscriptionType;
    private String subscriptionStartTimeInMillis;
    private String subscriptionEndTimeInMillis;
    private String userName;
    private String transactionRefNumber;
    private Long transactionDate;
    private String paymentMode;
    private Double amountTransferred;
    private String bankName;
    private String bankHolderName;
    private Boolean paymentVerified;

    public UserPaymentDto(String subscriptionId, String subscriptionType, String subscriptionStartTimeInMillis,
                          String subscriptionEndTimeInMillis, String userName, String transactionRefNumber, String paymentMode,
                          Long transactionDate, Double amountTransferred, String bankName, String bankHolderName, Boolean paymentVerified) {
        this.subscriptionId = subscriptionId;
        this.subscriptionType = subscriptionType;
        this.subscriptionStartTimeInMillis = subscriptionStartTimeInMillis;
        this.subscriptionEndTimeInMillis = subscriptionEndTimeInMillis;
        this.userName = userName;
        this.transactionRefNumber = transactionRefNumber;
        this.paymentMode = paymentMode;
        this.transactionDate = transactionDate;
        this.amountTransferred = amountTransferred;
        this.bankName = bankName;
        this.bankHolderName = bankHolderName;
        this.paymentVerified = paymentVerified;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionStartTimeInMillis() {
        return subscriptionStartTimeInMillis;
    }

    public void setSubscriptionStartTimeInMillis(String subscriptionStartTimeInMillis) {
        this.subscriptionStartTimeInMillis = subscriptionStartTimeInMillis;
    }

    public String getSubscriptionEndTimeInMillis() {
        return subscriptionEndTimeInMillis;
    }

    public void setSubscriptionEndTimeInMillis(String subscriptionEndTimeInMillis) {
        this.subscriptionEndTimeInMillis = subscriptionEndTimeInMillis;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
