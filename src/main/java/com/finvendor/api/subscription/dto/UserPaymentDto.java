package com.finvendor.api.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPaymentDto implements Serializable {

    private String subscriptionRefId;
    private String transactionId;
    private String subscriptionType;
    private String paymentMode;
    private Long transactionDate;
    private Double amountTransferred;
    private String bankName;
    private String bankHolderName;
    private Boolean paymentVerified;

    public UserPaymentDto(String subscriptionRefId,String transactionId, String subscriptionType, String paymentMode, Long transactionDate, Double amountTransferred, String bankName, String bankHolderName, Boolean paymentVerified) {
        this.subscriptionRefId = subscriptionRefId;
        this.transactionId = transactionId;
        this.subscriptionType = subscriptionType;
        this.paymentMode = paymentMode;
        this.transactionDate = transactionDate;
        this.amountTransferred = amountTransferred;
        this.bankName = bankName;
        this.bankHolderName = bankHolderName;
        this.paymentVerified = paymentVerified;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
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

    public String getSubscriptionRefId() {
        return subscriptionRefId;
    }

    public void setSubscriptionRefId(String subscriptionRefId) {
        this.subscriptionRefId = subscriptionRefId;
    }
}
