package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.common.commondao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CisDao {

    @Autowired
    private ICommonDao commonDao;

    public String findKennithFisherStrategy() throws RuntimeException {
        try {
            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}