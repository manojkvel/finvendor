package com.finvendor.api.resources.companyprofile.companyprofile.dto;

import java.util.Map;

public class Nifty50PriceReturn {
    private Map<String,String> nifty50PriceReturn;

    public Nifty50PriceReturn(Map<String, String> nifty50PriceReturn) {
        this.nifty50PriceReturn = nifty50PriceReturn;
    }

    public Map<String, String> getNifty50PriceReturn() {
        return nifty50PriceReturn;
    }

    public void setNifty50PriceReturn(Map<String, String> nifty50PriceReturn) {
        this.nifty50PriceReturn = nifty50PriceReturn;
    }
}
