package com.finvendor.server.metrics.dto;

import java.util.Objects;

public class EquityResearchAnalyticsDto extends ConsumerAnalytics {
    /**Research Report filter count*/
    private String rfCount;
    private String rfBreach;


    /**Research Report Download filter count*/
    private String dCount;
    private String dBreach;

    public String getRfCount() {
        return rfCount;
    }

    public void setRfCount(String rfCount) {
        this.rfCount = rfCount;
    }

    public String getdCount() {
        return dCount;
    }

    public void setdCount(String dCount) {
        this.dCount = dCount;
    }

    public String getRfBreach() {
        return rfBreach;
    }

    public void setRfBreach(String rfBreach) {
        this.rfBreach = rfBreach;
    }

    public String getdBreach() {
        return dBreach;
    }

    public void setdBreach(String dBreach) {
        this.dBreach = dBreach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EquityResearchAnalyticsDto that = (EquityResearchAnalyticsDto) o;
        return Objects.equals(rfCount, that.rfCount) &&
                Objects.equals(rfBreach, that.rfBreach) &&
                Objects.equals(dCount, that.dCount) &&
                Objects.equals(dBreach, that.dBreach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rfCount, rfBreach, dCount, dBreach);
    }

    @Override
    public String toString() {
        return "EquityResearchAnalyticsDto{" +
                "rfCount='" + rfCount + '\'' +
                ", rfBreach='" + rfBreach + '\'' +
                ", dCount='" + dCount + '\'' +
                ", dBreach='" + dBreach + '\'' +
                ", userName='" + userName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
