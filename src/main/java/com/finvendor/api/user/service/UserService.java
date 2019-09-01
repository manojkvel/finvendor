//package com.finvendor.api.user.service;
//
//import com.finvendor.common.exception.ApplicationException;
//import com.finvendor.model.FinVendorUser;
//import com.finvendor.model.UserRole;
//import com.finvendor.modelpojo.staticpojo.FileDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public interface UserService {
//	void updateUserInfo(FinVendorUser user);
//	void saveUserInfo(FinVendorUser users);
//	void saveUserRolesInfo(UserRole userRole);
//	boolean validateUsername(String username) throws ApplicationException;
//	UserRole getUserRoleInfobyUsername(String username);
//	List<FinVendorUser> getUserInfoByNamewithPassword(String username,
//     String password);
//	FinVendorUser getUserDetailsByUsername(String username)
//		throws ApplicationException;
//	int updateUnsuccessfulLoginAttempts(String username, boolean reset);
//	int updateUserAccountStatus(String username, boolean status);
//	String insertRegistrationVerificationRecord(String username, boolean recreate);
//	boolean updateUserVerificationStatus(String userName, String registrationId);
//	List<FinVendorUser> getUserDetailsByEmailId(String email) throws ApplicationException;
//	List<FinVendorUser> getUserDetails();
//	String resetPassword(String username) throws ApplicationException;
//	int changePassword(String username, String password) throws ApplicationException;
//	void updateVendorAccountSettings(String userName, String companyType,
//     String email) throws ApplicationException;
//	void updateConsumerAccountSettings(String userName, String companyType,
//     String tags, String email) throws ApplicationException;
//	void updateCompanyLogo(FileDetails ufile, String userName, boolean vendor);
//	boolean isValidUser(String userName);
//}