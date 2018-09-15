/**
 * 
 */
package com.finvendor.serviceimpl;

import com.finvendor.dao.LoginDao;
import com.finvendor.model.FinVendorUser;
import com.finvendor.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rayulu vemula
 *
 */
public class LoginServiceImpl implements LoginService{

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class.getName());
	
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
