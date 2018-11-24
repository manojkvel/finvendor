package com.finvendor.serverwebapi.resources.researchreport.mf.dao;

import com.finvendor.model.MutualFundMaster;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.researchreport.mf.controller.MfProfileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author Ayush on 4-Oct-2018
 */
@Repository
public class MfProfileDao extends GenericDao<MutualFundMaster> {
    private static final Logger logger = LoggerFactory.getLogger(MfProfileDao.class.getName());

    @Autowired
    private ICommonDao commonDao;

    public String getMFProfile(String schemeNavName) throws  RuntimeException {
        try{
            commonDao.getNativeQuery("",null);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
}
