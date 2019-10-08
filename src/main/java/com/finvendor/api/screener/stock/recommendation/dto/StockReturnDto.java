package com.finvendor.api.screener.stock.recommendation.dto;

import java.io.Serializable;

public class StockReturnDto implements Serializable {
    private String cmp;
    private String isinCode;

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
}
