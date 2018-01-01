/**
 * 
 */
package com.finvendor.dao;

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
	 * @see com.finvendor.dao.LoginDao#getUserInfoByEmail()
	 */
	FinVendorUser getUserInfoByEmail(String email);
 

}
