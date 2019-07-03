package com.finvendor.api.fvreport.dto;

import java.io.Serializable;
import java.util.List;

public class UserWatchListData implements Serializable {
    private String userId;
    private String userName;
    private String userEmail;
    private List<String> userWatchLists;

    public UserWatchListData(String userId, String userName, String userEmail, List<String> userWatchLists) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userWatchLists = userWatchLists;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getUserWatchLists() {
        return userWatchLists;
    }

    public void setUserWatchLists(List<String> userWatchLists) {
        this.userWatchLists = userWatchLists;
    }
}
