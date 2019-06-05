package com.finvendor.api.metrics.dto;

import java.util.Objects;

public class EquityResearchAnalyticsDto extends ConsumerAnalytics {
    /**Research Report filter count*/
    private String hitCount;
    private String breachFlag;

    public String getHitCount() {
        return hitCount;
    }

    public void setHitCount(String hitCount) {
        this.hitCount = hitCount;
    }

    public String getBreachFlag() {
        return breachFlag;
    }

    public void setBreachFlag(String breachFlag) {
        this.breachFlag = breachFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EquityResearchAnalyticsDto that = (EquityResearchAnalyticsDto) o;
        return Objects.equals(hitCount, that.hitCount) &&
                Objects.equals(breachFlag, that.breachFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hitCount, breachFlag);
    }

    @Override
    public String toString() {
        return "EquityResearchAnalyticsDto{" +
                "hitCount='" + hitCount + '\'' +
                ", breachFlag='" + breachFlag + '\'' +
                ", userName='" + userName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
