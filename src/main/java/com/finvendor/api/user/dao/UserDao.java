//package com.finvendor.api.user.dao;
//
//import com.finvendor.common.exception.ApplicationException;
//import com.finvendor.model.FinVendorUser;
//import com.finvendor.model.UserRole;
//
//import java.util.List;
//
//public interface UserDao {
//	void updateUserInfo(FinVendorUser user);
//	public void saveUserInfo(FinVendorUser users);
//
//	public void saveUserRolesInfo(UserRole userRole);
//
//	public boolean validateUsername(String username) throws ApplicationException;
//
//	public UserRole getUserRoleInfobyUsername(String username);
//
//	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
//            String password);
//
//	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException;
//
//	public int updateUnsuccessfulLoginAttempts(String username, boolean reset);
//
//	public int updateUserAccountStatus(String username, boolean status);
//
//	public void insertRegistrationVerificationRecord(String username, String registration_id, boolean recreate);
//
//	public int updateUserVerificationStatus(String username, String registration_id);
//
//	public List<FinVendorUser> getUserDetailsByEmailId(String email) throws ApplicationException;
//
//	public List<FinVendorUser> getUserDetails();
//
//	public int resetPassword(String username, String password);
//
//	public void updateVendorAccountSettings(String userName, String companyType, String email) throws ApplicationException;
//	public void updateConsumerAccountSettings(String userName, String companyType, String tags, String email) throws ApplicationException;
//
//}