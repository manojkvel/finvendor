package com.finvendor.api.resources.homepage.dto;

public class MutualFundData extends HomePageSearchData {
    private String code;
    private String navName;

    public MutualFundData(String code, String navName) {
        this.code = code;
        this.navName = navName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }
}
