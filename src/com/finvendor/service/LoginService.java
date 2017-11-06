/**
 * 
 */
package com.finvendor.service;

 
import org.springframework.stereotype.Service;

import com.finvendor.model.FinVendorUser;

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
