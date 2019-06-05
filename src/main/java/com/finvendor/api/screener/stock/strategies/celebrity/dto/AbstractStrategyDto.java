package com.finvendor.api.screener.stock.strategies.celebrity.dto;

import java.io.Serializable;

public abstract class AbstractStrategyDto implements Serializable {
    protected String stockId;
    protected String companyName;

    public AbstractStrategyDto(String stockId, String companyName) {
        this.stockId = stockId;
        this.companyName = companyName;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
