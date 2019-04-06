package com.finvendor.api.resources.screener.stock.strategies.service;

import com.finvendor.api.resources.screener.stock.strategies.dao.CisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CisService {

    @Autowired
    private CisDao dao;

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
