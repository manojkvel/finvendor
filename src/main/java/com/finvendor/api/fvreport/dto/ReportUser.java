package com.finvendor.api.fvreport.dto;

import java.io.Serializable;

public class ReportUser implements Serializable {
    private String userName;
    private String userEmail;

    public ReportUser(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
