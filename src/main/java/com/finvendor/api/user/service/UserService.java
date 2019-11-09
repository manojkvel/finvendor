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
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());

    private final UserDao userDao;

    private final VendorDaoImpl vendorDao;

    private final ConsumerDao consumerDao;

    @Autowired
    public UserService(UserDao userDao, VendorDaoImpl vendorDao, ConsumerDao consumerDao) {
        this.userDao = userDao;
        this.vendorDao = vendorDao;
        this.consumerDao = consumerDao;
    }

    public void saveUserInfo(FinVendorUser user) {
        userDao.saveUserInfo(user);
    }

    public void updateUserInfo(FinVendorUser user) {
        userDao.updateUserInfo(user);
    }

    public void saveUserRolesInfo(UserRole userRole) {
        userDao.saveUserRolesInfo(userRole);
    }

    public UserRole getUserRoleInfoByUsername(String username) {
        return userDao.getUserRoleInfobyUsername(username);
    }

    public List<FinVendorUser> getUserInfoByNameWithPassword(String username,
            String password) {
        return userDao.getUserInfoByNamewithPassword(username, password);
    }

    public boolean validateUsername(String username) throws ApplicationException {
        LOG.debug("UserServiceImpl : validateUsername - {}", username);
        return userDao.validateUsername(username.toLowerCase());
    }

    public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException {
        return userDao.getUserDetailsByUsername(username);
    }

    public void updateUnsuccessfulLoginAttempts(String username, boolean reset) {
        userDao.updateUnsuccessfulLoginAttempts(username, reset);
    }

    public void updateUserAccountStatus(String username, boolean status) {
        LOG.debug("UserServiceImpl : updateUserAccountStatus to {} for {}", status, username);
        userDao.updateUserAccountStatus(username, status);
    }

    public String insertRegistrationVerificationRecord(String username, boolean recreate) {
        String registration_id = UUID.randomUUID().toString();
        userDao.insertRegistrationVerificationRecord(username, registration_id, recreate);
        return registration_id;
    }

    public boolean updateUserVerificationStatus(String userName, String registrationId) {
        int updatedRows = userDao.updateUserVerificationStatus(userName, registrationId);
        return updatedRows != 0;
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

    public void changePassword(String username, String password) throws ApplicationException {
        int updatedRows = userDao.resetPassword(username, password);
        if (!(updatedRows == 1)) {
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

    public void resetSubscription(String userName) throws Exception {
        FinVendorUser existingUser = getUserDetailsByUsername(userName);
        if (existingUser != null && existingUser.getUserName() != null) {
            userDao.deleteSubscription(existingUser.getUserName());
        }
    }

    public void deleteUser(String userName) throws Exception {
        FinVendorUser existingUser = getUserDetailsByUsername(userName);
        if (existingUser != null && existingUser.getUserName() != null) {
            userDao.deleteUser(existingUser.getUserName());
        }
    }

    public void verifyUser(String userName) throws Exception {
        FinVendorUser existingUser = getUserDetailsByUsername(userName);
        if (existingUser != null && existingUser.getUserName() != null) {
            userDao.verifyUser(existingUser.getUserName());
        }
    }
}
