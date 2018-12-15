package com.finvendor.api.resources.metrics.dto;

import java.io.Serializable;

public class MetricsDto implements Serializable {
    private static final long serialVersionUID = -3519208169508030576L;

    private String userName;
    private String count;
    private String localDate;
    private String ipAddress;


    public MetricsDto() {
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
