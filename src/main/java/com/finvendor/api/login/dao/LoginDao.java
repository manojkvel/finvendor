/**
 * 
 */
package com.finvendor.api.login.dao;

import com.finvendor.model.FinVendorUser;

/**
 * @author rayulu vemula
 *
 */
public interface LoginDao {

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get users info by emailId
	 * 
	 * @return Object with success or failure messages
	 * @see LoginDao#getUserInfoByEmail()
	 */
	FinVendorUser getUserInfoByEmail(String email);
 

}
