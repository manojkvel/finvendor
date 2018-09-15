package com.finvendor.serviceimpl;

import com.finvendor.dao.ConsumerDao;
import com.finvendor.dao.UserDao;
import com.finvendor.dao.VendorDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.form.FileDetails;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.UserRole;
import com.finvendor.service.UserService;
import com.finvendor.util.RandomPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private VendorDao vendorDao;
	
	@Autowired
	private ConsumerDao consumerDao;

	@Override
	@Transactional
	public void saveUserInfo(FinVendorUser user) {
		userDao.saveUserInfo(user);
	}

	@Override
	public void saveUserRolesInfo(UserRole userRole) {
		userDao.saveUserRolesInfo(userRole);	
	}

	@Override
	public UserRole getUserRoleInfobyUsername(String username) {
		return userDao.getUserRoleInfobyUsername(username);
	}
	
	@Override
	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
			String password) {
		return userDao.getUserInfoByNamewithPassword(username,password);
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean validateUsername(String username) throws ApplicationException {
		logger.debug("UserServiceImpl : validateUsername - {}", username);
		return userDao.validateUsername(username.toLowerCase());
	}
	
	@Override
	@Transactional(readOnly=true)
	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException {
		logger.debug("Entering : UserServiceImpl.getUserDetailsByUsername");
		FinVendorUser user = userDao.getUserDetailsByUsername(username);
		logger.debug("Leaving : UserServiceImpl.getUserDetailsByUsername");
		return user;
	}
	
	@Override
	@Transactional
	public int updateUnsuccessfulLoginAttempts(String username, boolean reset){
		return userDao.updateUnsuccessfulLoginAttempts(username, reset);
	}
	
	@Override
	@Transactional
	public int updateUserAccountStatus(String username, boolean status) {
		logger.debug("UserServiceImpl : updateUserAccountStatus to {} for {}", status, username);
		return userDao.updateUserAccountStatus(username, status);
	}
	
	@Override
	@Transactional
	public String insertRegistrationVerificationRecord(String username, boolean recreate){
		String registration_id = UUID.randomUUID().toString();
		userDao.insertRegistrationVerificationRecord(username, registration_id, recreate);
		return registration_id;
	}
	
	@Override
	@Transactional
	public boolean updateUserVerificationStatus(String userName, String registrationId){
		int updatedRows = userDao.updateUserVerificationStatus(userName, registrationId);
		if(updatedRows == 0) {
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public FinVendorUser getUserDetailsByEmailId(String email) throws ApplicationException {
		return userDao.getUserDetailsByEmailId(email);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<FinVendorUser> getUserDetails() {
		return userDao.getUserDetails();
	}
	
	@Override
	@Transactional
	public String resetPassword(String username) throws ApplicationException {
		String password = new String(RandomPasswordGenerator.generatePswd(8, 10, 3, 3, 2));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		int updatedRows = userDao.resetPassword(username, encoder.encode(password));
		if (updatedRows == 1) { 
			return password;
		}else {
			throw new ApplicationException("Error in reset Password");
		}
	}
	
	@Override
	@Transactional
	public int changePassword(String username, String password) throws ApplicationException {
		int updatedRows = userDao.resetPassword(username, password);
		if (updatedRows == 1) { 
			return updatedRows;
		}else {
			throw new ApplicationException("Error in Change Password");
		}
	}

	@Override
	@Transactional
	public void updateVendorAccountSettings(String userName, String companyType, String email) 
			throws ApplicationException {
		userDao.updateVendorAccountSettings(userName, companyType, email);
	}
	
	@Override
	@Transactional
	public void updateConsumerAccountSettings(String userName, String companyType, String tags, String email) 
			throws ApplicationException {
		userDao.updateConsumerAccountSettings(userName, companyType, tags, email);
	}
	
	@Override
	@Transactional
	public void updateCompanyLogo(FileDetails ufile, String userName, boolean vendor) {
		if(vendor) {
			vendorDao.updateVendorLogo(ufile, userName);
		} else {
			consumerDao.updateConsumerLogo(ufile, userName);
		}
		
	}
}
