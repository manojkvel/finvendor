/**
 * 
 */
package com.finvendor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finvendor.model.UserRole;
import com.finvendor.model.Vendor;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.FinVendorUser;

/**
 * @author rayulu vemula
 *
 */
@Service
public interface UserService {

	/** --------------------------------------------------------------------- */
	/**
	 * Method to save user infor type
	 * 
	 * @param users
	 * @return integer
	 */
	public void saveUserInfo(FinVendorUser users);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to save userrole info type
	 * 
	 * @param userRole
	 * @return 
	 */
	public void saveUserRolesInfo(UserRole userRole);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to validate user name
	 * 
	 * @param username
	 * @return 
	 */
	public boolean validateUsername(String username) throws ApplicationException;

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get userrole info by username
	 * 
	 * @param username
	 * @return 
	 */
	public UserRole getUserRoleInfobyUsername(String username);



	/** --------------------------------------------------------------------- */
	/**
	 * Method to get user info
	 * 
	 * @param username,password
	 * @return 
	 */
	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
			String password);
	
	
	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException;
	public int updateUnsuccessfulLoginAttempts(String username, boolean reset);
	public int updateUserAccountStatus(String username, boolean status);
	public String insertRegistrationVerificationRecord(String username, boolean recreate);
	public boolean updateUserVerificationStatus(String userName, String registrationId);
	public FinVendorUser getUserDetailsByEmailId(String email) throws ApplicationException;
}