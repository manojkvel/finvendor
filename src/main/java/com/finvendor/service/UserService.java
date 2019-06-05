//package com.finvendor.service;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.finvendor.common.exception.ApplicationException;
//import com.finvendor.form.FileDetails;
//import com.finvendor.model.FinVendorUser;
//import com.finvendor.model.UserRole;
//
//@Service
//public interface UserService {
//
//	public void saveUserInfo(FinVendorUser users);
//	public void saveUserRolesInfo(UserRole userRole);
//	public boolean validateUsername(String username) throws ApplicationException;
//	public UserRole getUserRoleInfobyUsername(String username);
//	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
//			String password);
//	public FinVendorUser getUserDetailsByUsername(String username)
//			throws ApplicationException;
//	public int updateUnsuccessfulLoginAttempts(String username, boolean reset);
//	public int updateUserAccountStatus(String username, boolean status);
//	public String insertRegistrationVerificationRecord(String username, boolean recreate);
//	public boolean updateUserVerificationStatus(String userName, String registrationId);
//	public List<FinVendorUser> getUserDetailsByEmailId(String email) throws ApplicationException;
//	public List<FinVendorUser> getUserDetails();
//	public String resetPassword(String username) throws ApplicationException;
//	public int changePassword(String username, String password) throws ApplicationException;
//	public void updateVendorAccountSettings(String userName, String companyType,
//			String email) throws ApplicationException;
//	public void updateConsumerAccountSettings(String userName, String companyType,
//			String tags, String email) throws ApplicationException;
//	public void updateCompanyLogo(FileDetails ufile, String userName, boolean vendor);
//}