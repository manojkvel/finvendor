package com.finvendor.serverwebapi.resources.researchreport.mf.service;

import com.finvendor.serverwebapi.resources.researchreport.mf.dao.MfProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Ayush on 4-Oct-2018
 */
@Service
@Transactional
public class MfProfileService {

    @Autowired
    private MfProfileDao mfProfileDao;

    public String getMFProfile(String schemeNavName) throws  Exception{
        try{
           return mfProfileDao.getMFProfile(schemeNavName);
        }catch (RuntimeException e){
            throw new Exception(e);
        }
    }
}
