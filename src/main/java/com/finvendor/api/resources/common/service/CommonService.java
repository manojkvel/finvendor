package com.finvendor.api.resources.common.service;

import com.finvendor.api.resources.common.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonService {

    @Autowired
    private CommonDao dao;

    public void insertVo() throws Exception {
        try {
            dao.saveVo();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }

    }
}
