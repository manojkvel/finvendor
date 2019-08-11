package com.finvendor.api.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"userName","subscriptionDate", "subscriptionType","subscriptionState",
       "subscriptionStartTimeMs","subscriptionEndTimeMs", "subscriptionRefId",
        "transactionRefNumber", "transactionDate", "transactionFor",
        "paymentMode", "amountTransferred",
        "bankName", "bankHolderName", "paymentVerified"})
public class UserPaymentDto implements Serializable {

    private final String userName;
    private final long subscriptionDate;
    private final String subscriptionType;
    private final String subscriptionState;
    private final long subscriptionStartTimeMs;
    private final long subscriptionEndTimeMs;
    private final String subscriptionRefId;
    private final String transactionRefNumber;
    private final long transactionDate;
    private final String transactionFor;
    private final String paymentMode;
    private final double amtTransferred;
    private final String bankName;
    private final String bankHolderName;
    private final String paymentVerified;

    public UserPaymentDto(String userName, long subscriptionDate, String subscriptionType, String subscriptionState,
            long subscriptionStartTimeMs, long subscriptionEndTimeMs, String subscriptionRefId, String transactionRefNumber,
            long transactionDate, String transactionFor, String paymentMode, double amtTransferred,
            String bankName, String bankHolderName, String paymentVerified) {

        this.userName = userName;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionType = subscriptionType;
        this.subscriptionState = subscriptionState;
        this.subscriptionStartTimeMs = subscriptionStartTimeMs;
        this.subscriptionEndTimeMs = subscriptionEndTimeMs;
        this.subscriptionRefId = subscriptionRefId;
        this.transactionRefNumber = transactionRefNumber;
        this.transactionDate = transactionDate;
        this.transactionFor = transactionFor;
        this.paymentMode = paymentMode;
        this.amtTransferred = amtTransferred;
        this.bankName = bankName;
        this.bankHolderName = bankHolderName;
        this.paymentVerified = paymentVerified;
    }

    public String getUserName() {
        return userName;
    }

    public long getSubscriptionDate() {
        return subscriptionDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public String getSubscriptionState() {
        return subscriptionState;
    }

    public long getSubscriptionStartTimeMs() {
        return subscriptionStartTimeMs;
    }

    public long getSubscriptionEndTimeMs() {
        return subscriptionEndTimeMs;
    }

    public String getSubscriptionRefId() {
        return subscriptionRefId;
    }

    public String getTransactionRefNumber() {
        return transactionRefNumber;
    }

    public long getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionFor() {
        return transactionFor;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public double getAmtTransferred() {
        return amtTransferred;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public String getPaymentVerified() {
        return paymentVerified;
    }
}
