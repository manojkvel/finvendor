/**
 * 
 */
package com.finvendor.api.login.service;

import com.finvendor.model.FinVendorUser;
import org.springframework.stereotype.Service;

/**
 * @author rayulu vemula
 *
 */
@Service
public interface LoginService {

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get user info by email
	 * 
	 * @param email
	 * @return 
	 */
	FinVendorUser getUserInfoByEmail(String email);


}
