package com.finvendor.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.UserDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.UserRole;
import com.finvendor.util.RequestConstans;

public class UserDaoImpl implements UserDao {
	
	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	private static final String UPDATE_LOGIN_UNSUCCESSFUL_ATTEMPTS = "UPDATE users SET login_attempts = login_attempts + 1, last_modified = CURRENT_TIMESTAMP() WHERE username = :username";
	private static final String RESET_LOGIN_UNSUCCESSFUL_ATTEMPTS = "UPDATE users SET login_attempts = 0, last_login = CURRENT_TIMESTAMP(), last_modified = CURRENT_TIMESTAMP() WHERE username = :username";
	private static final String UPDATE_USER_STATUS = "UPDATE users SET enabled = :enabled, last_modified = CURRENT_TIMESTAMP() WHERE username = :username";
	private static final String INSERT_USER_VERIFICATION_RECORD = "INSERT into user_verification (username, registration_id, created_date) values (:username, :registration_id, CURRENT_TIMESTAMP())";
	private static final String DELETE_USER_VERIFICATION_RECORD = "DELETE from user_verification where username = :username";
	private static final String UPDATE_USER_VERIFICATION_DATE = "UPDATE user_verification set verified_date = CURRENT_TIMESTAMP() where registration_id = :registrationId and username = :username and TIMESTAMPDIFF(HOUR, created_date, CURRENT_TIMESTAMP()) < " + RequestConstans.REGISTRATION_LINK_EXPIRY;
	private static final String UPDATE_USER_REGISTER_ENABLE_STATUS = "UPDATE users set enabled = :enabled, verified = 'Y' where username = :username";
	private static final String RESET_USER_PASSWORD = "UPDATE users SET password = :password, enabled = true, login_attempts = -100, last_modified = CURRENT_TIMESTAMP() WHERE username = :username";
	private static final String UPDATE_VENDOR_REGISTRATION_DETAILS = "UPDATE vendor set companytype=:companytype  where username = :username";
	private static final String UPDATE_CONSUMER_REGISTRATION_DETAILS = "UPDATE consumer set companytype=:companytype, tags=:tags where username = :username";
	private static final String UPDATE_REGISTRATION_EMAIL = "UPDATE users set email = :email where username = :username";
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveUserInfo(FinVendorUser user) {
		logger.debug("Entering UserDaoImpl : saveUserInfo");
		try{
			 this.sessionFactory.getCurrentSession().save(user);
			 this.sessionFactory.getCurrentSession().flush();
		}catch(Exception exp){
			logger.error("Error UserDaoImpl : saveUserInfo ", exp);
		}
		logger.debug("Leaving UserDaoImpl : saveUserInfo");
	}

	@Transactional
	@Override
	public void saveUserRolesInfo(UserRole userRole) {
		logger.info("saveUserRolesInfo method---");
		try{
			this.sessionFactory.getCurrentSession().save(userRole);
			this.sessionFactory.getCurrentSession().flush();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Error in saveUserRolesInfo---- " + ex);
		}
	}
	
	@Override
	public boolean validateUsername(String username) throws ApplicationException {
		logger.debug("Entering UserDaoImpl:validateUsername");
		FinVendorUser user = this.getUserDetailsByUsername(username);
		return (user != null) ? true : false;
	}
	
	@Override
	public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException {
		logger.debug("Entering UserDaoImpl:getUserDetailsByUsername");		
		try{
			FinVendorUser user = (FinVendorUser) this.sessionFactory.getCurrentSession().get(FinVendorUser.class, username);
			logger.debug("Leaving UserDaoImpl:getUserDetailsByUsername");
			return user;
		}catch (Exception exp){
			logger.error("Error getUserDetailsByUsername : " + exp);
			throw new ApplicationException("Error Reading User Details : " + username);
		}
		
	}
	
	@Transactional
	@Override
	public UserRole getUserRoleInfobyUsername(String username) {
		logger.info("getUserRoleInfobyUsername method---");
		SQLQuery sqlQuery=null; String hsql = null; 
		try{
			 hsql = "select * from user_roles where username='"+username+"' ";
	    	 sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hsql);
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in validateUsername---- " + ex);
		}
		return (UserRole)sqlQuery.uniqueResult();
	}
	
	@Transactional
	@Override
	public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
			String password) {
		logger.info("getUsersInfoByNamewithPassword method---");
		SQLQuery sqlQuery=null; String hsql = null; 
		List<FinVendorUser>  users = new ArrayList<FinVendorUser>();
		FinVendorUser usersinfo= new FinVendorUser();
		try{
			 hsql = "select * from users where username = :username and password = :password";
	    	 sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hsql);
	    	 sqlQuery.setParameter("username", username);
	    	 sqlQuery.setParameter("password", password);
	    	 @SuppressWarnings("unchecked")
			List<Object[]> usersObject = sqlQuery.list();
	    	 for (int i = 0; i < usersObject.size(); i++) {
	    		 Object[] usercheck = usersObject.get(i);
	    		 String usernameval = usercheck[0].toString();
	    		 String passwordval = usercheck[1].toString();
	    		 usersinfo.setUserName(usernameval);
	    		 usersinfo.setPassword(passwordval);
	    		 users.add(usersinfo);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getUsersInfoByNamewithPassword---- " + ex);
		}
		return users;
	}
	
	@Override
	public int updateUnsuccessfulLoginAttempts(String username, boolean reset){
		logger.debug("Entering UserDaoImpl:updateUnsucessfulLoginAttempts for {} ", username);
		int updatedRows = 0;
		SQLQuery sqlQuery = null;
		if(reset){
			sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(RESET_LOGIN_UNSUCCESSFUL_ATTEMPTS);
		}else{
			sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_LOGIN_UNSUCCESSFUL_ATTEMPTS);
		}		
		sqlQuery.setParameter("username", username);
		updatedRows = sqlQuery.executeUpdate();
		logger.debug("Leaving UserDaoImpl:updateUnsucessfulLoginAttempts");
		return updatedRows;
	}
	
	@Override	
	public int updateUserAccountStatus(String username, boolean status){
		logger.debug("Entering UserDaoImpl:updateUserAccountStatus for {} to update status as {}", username, status);
		int updatedRows = 0;
		SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_USER_STATUS);
		sqlQuery.setParameter("username", username);
		sqlQuery.setParameter("enabled", status);
		updatedRows = sqlQuery.executeUpdate();
		logger.debug("Leaving UserDaoImpl:updateUserAccountStatus");
		return updatedRows;
	}
	
	@Override
	public void insertRegistrationVerificationRecord(String username, String registration_id, boolean recreate){
		logger.debug("Entering UserDaoImpl:insertRegistrationVerificationRecord {}, Recreate : ", username, recreate);
		SQLQuery sqlQuery = null;
		if(recreate){
			sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(DELETE_USER_VERIFICATION_RECORD);
			sqlQuery.setParameter("username", username);
			sqlQuery.executeUpdate();
		}
		sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(INSERT_USER_VERIFICATION_RECORD);
		sqlQuery.setParameter("username", username);
		sqlQuery.setParameter("registration_id", registration_id);
		sqlQuery.executeUpdate();
		logger.debug("Leaving UserDaoImpl:insertRegistrationVerificationRecord {}", username);
	}
	
	@Override
	public int updateUserVerificationStatus(String username, String registration_id){
		logger.debug("Entering UserDaoImpl:updateUserVerificationStatus {}, {}", username, registration_id);
		int updatedRows = 0;
		SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_USER_VERIFICATION_DATE);
		sqlQuery.setParameter("registrationId", registration_id);
		sqlQuery.setParameter("username", username);
		updatedRows = sqlQuery.executeUpdate();
		logger.debug("UserDaoImpl:updateUserVerificationStatus {}, Rows updated : {}", username, updatedRows);
		if(updatedRows == 1){
			sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_USER_REGISTER_ENABLE_STATUS);
			sqlQuery.setParameter("username", username);
			sqlQuery.setParameter("enabled", true);
			sqlQuery.executeUpdate();
		}
		logger.debug("Leaving UserDaoImpl:updateUserVerificationStatus {}, {}", username, registration_id);
		return updatedRows;
	}
	
	@Override
	public FinVendorUser getUserDetailsByEmailId(String email) throws ApplicationException {
		logger.debug("Entering UserDaoImpl:getUserDetailsByEmailId {}", email);
		try{			 
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FinVendorUser.class);  
			criteria.add(Restrictions.eq("email", email));
			FinVendorUser user = (FinVendorUser)criteria.uniqueResult();
			logger.debug("Leaving UserDaoImpl:getUserDetailsByEmailId {}", email);
			return user;
		}catch (Exception exp) {
			logger.error("Error getUserDetailsByEmailId : " + exp);
			throw new ApplicationException("Error Reading User Details : " + email);
		}
	}
	
	@Override
	public List<FinVendorUser> getUserDetails() {		
		logger.debug("Entering UserDaoImpl:getUserDetails");
		Query query = this.sessionFactory.getCurrentSession().createQuery("from FinVendorUser");
		@SuppressWarnings("unchecked")
		List<FinVendorUser> userList = query.list();
		logger.debug("Leaving UserDaoImpl:getUserDetails");
		return userList;
	}
	
	@Override
	public int resetPassword(String username, String password) {
		logger.debug("Entering UserDaoImpl:resetPassword for {}", username);
		int updatedRows = 0;
		SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(RESET_USER_PASSWORD);
		sqlQuery.setParameter("username", username);
		sqlQuery.setParameter("password", password);
		updatedRows = sqlQuery.executeUpdate();
		logger.debug("Leaving UserDaoImpl:resetPassword");
		return updatedRows;
	}
	
	@Override
	public void updateVendorAccountSettings(String userName, String companyType, String email) 
			throws ApplicationException {
		logger.info("Entering UserDaoImpl:updateVendorAccountSettings for {}", userName);
		try{
			SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_VENDOR_REGISTRATION_DETAILS);
			sqlQuery.setParameter("username", userName);
			sqlQuery.setParameter("companytype", companyType);				
			sqlQuery.executeUpdate();
			sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_REGISTRATION_EMAIL);
			sqlQuery.setParameter("email", email);
			sqlQuery.setParameter("username", userName);
			sqlQuery.executeUpdate();
		}catch (Exception exp) {
			logger.error("Error updateVendorAccountSettings : " + exp);
			throw new ApplicationException("Error Updating User Registration Details for : " + userName);
		}
		logger.info("Leaving UserDaoImpl:updateVendorAccountSettings");
	}
	
	@Override
	public void updateConsumerAccountSettings(String userName, String companyType, String tags, String email) 
			throws ApplicationException {
		logger.info("Entering UserDaoImpl:updateConsumerAccountSettings for {}", userName);
		try{
			SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_CONSUMER_REGISTRATION_DETAILS);
			sqlQuery.setParameter("username", userName);
			sqlQuery.setParameter("companytype", companyType);
			sqlQuery.setParameter("tags", tags);
			sqlQuery.executeUpdate();
			sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_REGISTRATION_EMAIL);
			sqlQuery.setParameter("email", email);
			sqlQuery.setParameter("username", userName);
			sqlQuery.executeUpdate();
		}catch (Exception exp) {
			logger.error("Error updateConsumerAccountSettings : " + exp);
			throw new ApplicationException("Error Updating User Registration Details for : " + userName);
		}
		logger.info("Leaving UserDaoImpl:updateConsumerAccountSettings");
	}

}
