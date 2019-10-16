package com.finvendor.api.common.dto;

import java.io.Serializable;

public class StockReturnDto implements Serializable {
    private String isinCode;

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
}
