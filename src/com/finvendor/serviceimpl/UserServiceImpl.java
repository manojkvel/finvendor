package com.finvendor.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.UserDAO;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.UserRole;
import com.finvendor.service.UserService;
import com.finvendor.util.RandomPasswordGenerator;

public class UserServiceImpl implements UserService{
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void saveUserInfo(FinVendorUser user) {
		userDAO.saveUserInfo(user);
	}

	@Override
	public void saveUserRolesInfo(UserRole userRole) {
		userDAO.saveUserRolesInfo(userRole);	
	}

	@Override
	public UserRole getUserRoleInfobyUsername(String username) {
		return userDAO.getUserRoleInfobyUsername(username);
	}
	
	@Override
	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
			String password) {
		return userDAO.getUserInfoByNamewithPassword(username,password);
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean validateUsername(String username) throws ApplicationException {
		logger.debug("UserServiceImpl : validateUsername - {}", username);
		return userDAO.validateUsername(username.toLowerCase());
	}
	
	@Override
	@Transactional(readOnly=true)
	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException {
		logger.debug("Entering : UserServiceImpl.getUserDetailsByUsername");
		FinVendorUser user = userDAO.getUserDetailsByUsername(username);
		logger.debug("Leaving : UserServiceImpl.getUserDetailsByUsername");
		return user;
	}
	
	@Override
	@Transactional
	public int updateUnsuccessfulLoginAttempts(String username, boolean reset){
		return userDAO.updateUnsuccessfulLoginAttempts(username, reset);
	}
	
	@Override
	@Transactional
	public int updateUserAccountStatus(String username, boolean status) {
		logger.debug("UserServiceImpl : updateUserAccountStatus to {} for {}", status, username);
		return userDAO.updateUserAccountStatus(username, status);
	}
	
	@Override
	@Transactional
	public String insertRegistrationVerificationRecord(String username, boolean recreate){
		String registration_id = UUID.randomUUID().toString();
		userDAO.insertRegistrationVerificationRecord(username, registration_id, recreate);
		return registration_id;
	}
	
	@Override
	@Transactional
	public boolean updateUserVerificationStatus(String userName, String registrationId){
		int updatedRows = userDAO.updateUserVerificationStatus(userName, registrationId);
		if(updatedRows == 0) {
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public FinVendorUser getUserDetailsByEmailId(String email) throws ApplicationException {
		return userDAO.getUserDetailsByEmailId(email);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<FinVendorUser> getUserDetails() {
		return userDAO.getUserDetails();
	}
	
	@Override
	@Transactional
	public String resetPassword(String username) throws ApplicationException {
		String password = new String(RandomPasswordGenerator.generatePswd(8, 10, 3, 3, 2));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		int updatedRows = userDAO.resetPassword(username, encoder.encode(password));
		if (updatedRows == 1) { 
			return password;
		}else {
			throw new ApplicationException("Error in reset Password");
		}
	}
	
	@Override
	@Transactional
	public int changePassword(String username, String password) throws ApplicationException {
		int updatedRows = userDAO.resetPassword(username, password);
		if (updatedRows == 1) { 
			return updatedRows;
		}else {
			throw new ApplicationException("Error in Change Password");
		}
	}
	
}
