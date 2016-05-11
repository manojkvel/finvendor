package com.finvendor.dao;

import java.util.List;

import com.finvendor.model.UserRole;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.FinVendorUser;

public interface UserDAO {

	public void saveUserInfo(FinVendorUser users);

	public void saveUserRolesInfo(UserRole userRole);

	public boolean validateUsername(String username) throws ApplicationException;

	public UserRole getUserRoleInfobyUsername(String username);

	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
			String password);
	
	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException;
	
	public int updateUnsuccessfulLoginAttempts(String username, boolean reset);
	
	public int updateUserAccountStatus(String username, boolean status);
	
	public void insertRegistrationVerificationRecord(String username, String registration_id, boolean recreate);
	
	public int updateUserVerificationStatus(String username, String registration_id);
	
	public FinVendorUser getUserDetailsByEmailId(String email) throws ApplicationException;
	
	public List<FinVendorUser> getUserDetails();
	
	public int resetPassword(String username, String password);
	
	public void updateVendorAccountSettings(String userName, String companyType, String email) throws ApplicationException;
	public void updateConsumerAccountSettings(String userName, String companyType, String tags, String email) throws ApplicationException;
	
}