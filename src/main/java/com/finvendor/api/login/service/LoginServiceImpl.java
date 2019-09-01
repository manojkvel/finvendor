/**
 * 
 */
package com.finvendor.api.login.service;

import com.finvendor.api.login.dao.LoginDao;
import com.finvendor.api.login.dao.LoginDaoImpl;
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
public class LoginServiceImpl {//implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class.getName());
	
	@Autowired
	private LoginDaoImpl loginDao;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.LoginServiceImpl#getUserInfoByEmail(FinVendorUser)
	 */
	public FinVendorUser getUserInfoByEmail(String email) {
		logger.info("getUserInfoByEmail Method....");
		return loginDao.getUserInfoByEmail(email);
	}

}
