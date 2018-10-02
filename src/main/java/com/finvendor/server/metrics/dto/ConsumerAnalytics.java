package com.finvendor.server.metrics.dto;

import java.util.Objects;

public abstract class ConsumerAnalytics {
    protected String userName;
    protected String registrationDate;
    protected String lastLogin;
    protected String ipAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumerAnalytics that = (ConsumerAnalytics) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(registrationDate, that.registrationDate) &&
                Objects.equals(lastLogin, that.lastLogin) &&
                Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, registrationDate, lastLogin, ipAddress);
    }

    @Override
    public String toString() {
        return "ConsumerAnalytics{" +
                "userName='" + userName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
