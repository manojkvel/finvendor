package com.finvendor.api.subscription.dto;

import java.io.Serializable;

public class SubscriptionFilter implements Serializable {

    Long from;
    Long to;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
