package com.finvendor.api.subscription.dto;

import com.finvendor.api.common.dto.RecordStats;

import java.io.Serializable;
import java.util.List;

public class SubscriptionsResponse implements Serializable {
    private RecordStats recordStats;
    private List<UsersSubscriptionDto> subscriptions;

    public SubscriptionsResponse(RecordStats recordStats, List<UsersSubscriptionDto> subscriptions) {
        this.recordStats = recordStats;
        this.subscriptions = subscriptions;
    }

    public RecordStats getRecordStats() {
        return recordStats;
    }

    public void setRecordStats(RecordStats recordStats) {
        this.recordStats = recordStats;
    }

    public List<UsersSubscriptionDto> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<UsersSubscriptionDto> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
