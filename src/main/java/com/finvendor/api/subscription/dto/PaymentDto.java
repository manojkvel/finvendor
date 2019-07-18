package com.finvendor.api.subscription.dto;

import java.io.Serializable;

public class PaymentDto implements Serializable {
    private Boolean paymentVerified;

    public Boolean getPaymentVerified() {
        return paymentVerified;
    }

    public void setPaymentVerified(Boolean paymentVerified) {
        this.paymentVerified = paymentVerified;
    }
}
