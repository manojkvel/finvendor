package com.finvendor.api.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.finvendor.common.annotations.ValidCategory;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionDto implements Serializable {

    //@Pattern(regexp = "^[A-Za-z0-9]*$", message = "Transaction Id must be Alpha Numeric !!")
    private String transactionId;

    //@ValidCategory(categoryType="subscriptionType", message = "Subscription value must be either SMART or SAGE !!")
    private String subscriptionType;

    //@ValidCategory(categoryType="paymentType", message = "Payment mode can be among these [NEFT, RTGS, IMPS] !!")
    private String paymentMode;

    private Long transactionDate;
    private Double amountTransferred;
    private String bankName;
    private String bankHolderName;
    private Boolean transactionVerified;

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

    public Boolean getTransactionVerified() {
        return transactionVerified;
    }

    public void setTransactionVerified(Boolean transactionVerified) {
        this.transactionVerified = transactionVerified;
    }
}
