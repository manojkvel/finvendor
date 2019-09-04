package com.finvendor.api.user.service;

import com.finvendor.api.consumer.dao.ConsumerDao;
import com.finvendor.api.user.dao.UserDao;
import com.finvendor.api.vendor.dao.VendorDaoImpl;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.UserRole;
import com.finvendor.modelpojo.staticpojo.FileDetails;
import com.finvendor.util.RandomPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    @Autowired
    private UserDao userDao;

    @Autowired
    private VendorDaoImpl vendorDao;

    @Autowired
    private ConsumerDao consumerDao;

    public void saveUserInfo(FinVendorUser user) {
        userDao.saveUserInfo(user);
    }

    public void updateUserInfo(FinVendorUser user) {
        userDao.updateUserInfo(user);
    }

    public void saveUserRolesInfo(UserRole userRole) {
        userDao.saveUserRolesInfo(userRole);
    }

    public UserRole getUserRoleInfobyUsername(String username) {
        return userDao.getUserRoleInfobyUsername(username);
    }

    public List<FinVendorUser> getUserInfoByNamewithPassword(String username,
            String password) {
        return userDao.getUserInfoByNamewithPassword(username, password);
    }

    public boolean validateUsername(String username) throws ApplicationException {
        logger.debug("UserServiceImpl : validateUsername - {}", username);
        return userDao.validateUsername(username.toLowerCase());
    }

    public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException {
        logger.debug("Entering : UserServiceImpl.getUserDetailsByUsername");
        FinVendorUser user = userDao.getUserDetailsByUsername(username);
        logger.debug("Leaving : UserServiceImpl.getUserDetailsByUsername");
        return user;
    }

    public int updateUnsuccessfulLoginAttempts(String username, boolean reset) {
        return userDao.updateUnsuccessfulLoginAttempts(username, reset);
    }

    public int updateUserAccountStatus(String username, boolean status) {
        logger.debug("UserServiceImpl : updateUserAccountStatus to {} for {}", status, username);
        return userDao.updateUserAccountStatus(username, status);
    }

    public String insertRegistrationVerificationRecord(String username, boolean recreate) {
        String registration_id = UUID.randomUUID().toString();
        userDao.insertRegistrationVerificationRecord(username, registration_id, recreate);
        return registration_id;
    }

    public boolean updateUserVerificationStatus(String userName, String registrationId) {
        int updatedRows = userDao.updateUserVerificationStatus(userName, registrationId);
        if (updatedRows == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<FinVendorUser> getUserDetailsByEmailId(String email) throws ApplicationException {
        return userDao.getUserDetailsByEmailId(email);
    }

    public List<FinVendorUser> getUserDetails() {
        return userDao.getUserDetails();
    }

    public String resetPassword(String username) throws ApplicationException {
        String password = new String(RandomPasswordGenerator.generatePswd(8, 10, 3, 3, 2));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        int updatedRows = userDao.resetPassword(username, encoder.encode(password));
        if (updatedRows == 1) {
            return password;
        }
        else {
            throw new ApplicationException("Error in reset Password");
        }
    }

    public int changePassword(String username, String password) throws ApplicationException {
        int updatedRows = userDao.resetPassword(username, password);
        if (updatedRows == 1) {
            return updatedRows;
        }
        else {
            throw new ApplicationException("Error in Change Password");
        }
    }

    public void updateVendorAccountSettings(String userName, String companyType, String email)
            throws ApplicationException {
        userDao.updateVendorAccountSettings(userName, companyType, email);
    }

    public void updateConsumerAccountSettings(String userName, String companyType, String tags, String email)
            throws ApplicationException {
        userDao.updateConsumerAccountSettings(userName, companyType, tags, email);
    }

    public void updateCompanyLogo(FileDetails ufile, String userName, boolean vendor) {
        if (vendor) {
            vendorDao.updateVendorLogo(ufile, userName);
        }
        else {
            consumerDao.updateConsumerLogo(ufile, userName);
        }
    }

    public boolean isValidUser(String userName) {
        try {
            FinVendorUser user = getUserDetailsByUsername(userName);
            return user != null && userName.equals(user.getUserName());
        } catch (ApplicationException e) {
            return false;
        }
    }
}
