/**
 * 
 */
package com.finvendor.dao;

import java.util.List;

import com.finvendor.model.UserRole;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.FinVendorUser;

/**
 * @author rayulu vemula
 *
 */
public interface UserDAO {

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to save users info
	 * 
	 * @return Integer with success or failure messages
	 * @see com.finvendor.dao.UserDAO#saveUsers()
	 */
	public void saveUserInfo(FinVendorUser users);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to save users role info
	 * 
	 * @return Integer with success or failure messages
	 * @see com.finvendor.dao.UserDAO#saveUserRolesInfo()
	 */
	public void saveUserRolesInfo(UserRole userRole);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to validate username
	 * 
	 * @return Integer with success or failure messages
	 * @throws ApplicationException 
	 * @see com.finvendor.dao.UserDAO#validateUsername()
	 */
	public boolean validateUsername(String username) throws ApplicationException;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get userrole infor by username
	 * 
	 * @return Integer with success or failure messages
	 * @see com.finvendor.dao.UserDAO#getUserRoleInfobyUsername()
	 */
	public UserRole getUserRoleInfobyUsername(String username);

	

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get user info
	 * 
	 * @return 
	 * @see com.finvendor.dao.UserDAO#getUsersInfoByNamewithPassword()
	 */
	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
			String password);

	
	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException;
	
	public int updateUnsuccessfulLoginAttempts(String username, boolean reset);
	
	public int updateUserAccountStatus(String username, boolean status);
	
	public void insertRegistrationVerificationRecord(String username, String registration_id, boolean recreate);
	
	public int updateUserVerificationStatus(String username, String registration_id);
	
	public FinVendorUser getUserDetailsByEmailId(String email) throws ApplicationException;
	
}