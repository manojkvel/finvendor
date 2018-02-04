/**
 * 
 */
package com.finvendor.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.LoginDao;
import com.finvendor.model.FinVendorUser;
import com.finvendor.service.LoginService;

/**
 * @author rayulu vemula
 *
 */
public class LoginServiceImpl implements LoginService{
	
	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.LoginServiceImpl#getUserInfoByEmail(com.finvendor.model.FinVendorUser)
	 */
	public FinVendorUser getUserInfoByEmail(String email) {
		logger.info("getUserInfoByEmail Method....");
		return loginDao.getUserInfoByEmail(email);
	}

}
