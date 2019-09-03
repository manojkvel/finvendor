/**
 *
 */
package com.finvendor.api.login.service;

import com.finvendor.api.login.dao.LoginDao;
import com.finvendor.model.FinVendorUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rayulu vemula
 *
 */
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class.getName());

    @Autowired
    private LoginDao loginDao;

    public FinVendorUser getUserInfoByEmail(String email) {
        logger.info("getUserInfoByEmail Method....");
        return loginDao.getUserInfoByEmail(email);
    }

}
