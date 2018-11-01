package com.finvendor.serverwebapi.resources.companyprofile.watchlist.service;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.serverwebapi.resources.companyprofile.watchlist.dao.CompanyWatchlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ayush on May 01, 2018
 */
@Service
@Transactional
public class CompanyWatchlistService {//implements ICompanyWatchListService {

    @Autowired
    private CompanyWatchlistDao dao;

    public boolean addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception {
        boolean addWatchList = false;
        try {
            addWatchList = dao.addCompanyWatchList(companyWatchListPojo);
            return addWatchList;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<CompanyWatchListPojo> findAllCompanyWatchList(String userName) throws Exception {
        try {
            Map<Object, Object> paramMap = new LinkedHashMap<>();
            paramMap.put("username", userName);
            return dao.findAllCompanyWatchList(paramMap);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public boolean deleteCompnayWatchList(List<CompanyWatchListPojo> pojoList) throws Exception {
        try {
            return dao.deleteCompanyWatchList(pojoList);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
