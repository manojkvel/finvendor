package com.finvendor.api.resources.screener.stock.strategies.service;

import com.finvendor.api.resources.screener.stock.strategies.dto.CelebrityInvestorStrategyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CelebrityInvestorStrategyService {

    @Autowired
    private CelebrityInvestorStrategyDao dao;

    public String findStrategy(String type) {
        String strategyResult;
        switch (type) {
            case "kenith":
                strategyResult = dao.findKennithFisherStrategy();
                break;
            default:
                strategyResult = "";
        }
        return strategyResult;
    }
}
