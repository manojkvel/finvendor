package com.finvendor.api.resources.screener.stock.strategies.dto;

import java.io.Serializable;

public class StrategyToolTips implements Serializable {
    private  String strategyToolTips;

    public StrategyToolTips(String strategyToolTips) {
        this.strategyToolTips = strategyToolTips;
    }

    public String getStrategyToolTips() {
        return strategyToolTips;
    }

    public void setStrategyToolTips(String strategyToolTips) {
        this.strategyToolTips = strategyToolTips;
    }
}
