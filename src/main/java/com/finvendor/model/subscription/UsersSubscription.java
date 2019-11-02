package com.finvendor.model.subscription;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User Subscription Payment Entity
 */
@Entity
@Table(name = "users_subscription")
public class UsersSubscription implements Serializable {

    @Id
    @Column(name = "subscription_id")
    private String subscriptionId;

    @Column(name = "username")
    private String userName;

    @Column(name = "subscription_type")
    private String subscriptionType;

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

    @OneToMany(mappedBy = "usersSubscription", cascade = CascadeType.ALL)
    private Collection<UsersSubscriptionHistory> usersSubscriptionHistoryList = new ArrayList<>();

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

    public Collection<UsersSubscriptionHistory> getUsersSubscriptionHistoryList() {
        return usersSubscriptionHistoryList;
    }

    public void setUsersSubscriptionHistoryList(
            Collection<UsersSubscriptionHistory> usersSubscriptionHistoryList) {
        this.usersSubscriptionHistoryList = usersSubscriptionHistoryList;
    }
}
