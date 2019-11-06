package com.finvendor.api.user.service;

import com.finvendor.api.consumer.dao.ConsumerDao;
import com.finvendor.api.login.dto.SubscriptionDto;
import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.user.dao.UserDao;
import com.finvendor.api.vendor.dao.VendorDaoImpl;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.common.util.DateUtils;
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

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static com.finvendor.common.constant.AppConstants.*;
import static com.finvendor.common.util.DateUtils.convertSubscriptionDateToMillis;
import static com.finvendor.util.EmailUtil.SALES_EMAIL;

@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties fvProperties;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VendorDaoImpl vendorDao;

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private NotificationService notificationService;

    private static final String TRIAL_PERIOD_OVER_EMAIL_SUBJECT = "Subscription trial period over!";
    private static final String TRIAL_PERIOD_OVER_EMAIL_MESSAGE =
            "Dear User <br>Your Trial subscription trial period is over. <br> Please go for "
                    + "for monthly subscription.<br><br>Regards,<br>FinvendorTeam";

    private static final String SUBSCRIPTION_EXPIRED_SUBJECT = "Finvendor - Subscription expired";

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

    /**
     * Check user is in trial period
     */
    public boolean isUserInTrialPeriod(FinVendorUser user) throws Exception {
        boolean trialPeriod;
        trialPeriod = false;
        return trialPeriod;
    }
    

    public SubscriptionDto findUserSubscriptionDetails(FinVendorUser user) {
        return new SubscriptionDto(user.getSubscriptionType(), user.getSubscriptionState());
    }

    /**
     * Send email to user whose free trial period is over
     */
    public void sendEMail_OnTrialPeriodOver() throws Exception {
        List<FinVendorUser> userDetails = getUserDetails();
        boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty(EMAIL));
        for (FinVendorUser user : userDetails) {
            if (user.getTrialPeriodEndTime() != null && !isUserInTrialPeriod(user) && emailEnabled) {
                notificationService.sendMail(new EmailBuilder.Builder(new String[] { user.getEmail() },
                        TRIAL_PERIOD_OVER_EMAIL_SUBJECT, TRIAL_PERIOD_OVER_EMAIL_MESSAGE)
                        .from(SALES_EMAIL).build());
            }
        }
    }

    /**
     * Send email to user whose subscription period is expired
     */
    public void sendMail_OnSubscriptionExpired() throws Exception {
        List<FinVendorUser> existingUsers = getUserDetails();
        boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty(EMAIL));
        for (FinVendorUser user : existingUsers) {
            String subsEndTime = user.getSubscriptionEndTime();
            if (!N_A.equals(subsEndTime) && Calendar.getInstance().getTimeInMillis() > convertSubscriptionDateToMillis(subsEndTime)) {
                user.setSubscriptionType(FREE);
                user.setSubscriptionStartTime(N_A);
                user.setSubscriptionEndTime(N_A);
                updateUserInfo(user);
                if (emailEnabled) {
                    String subscriptionType = user.getSubscriptionType();
                    String content = "Dear" + " " + user.getUserName() + "<br>"
                            + "Your Subscription " + subscriptionType + " is expired."
                            + "<br><br>"
                            + "Kindly renew your subscription." + "<br><br>"
                            + "Thank you for choosing us. " + "<br><br>"
                            + "Regards" + "<br>"
                            + "Finvendor Team";
                    notificationService.sendMail(
                            new EmailBuilder.Builder(new String[] { user.getEmail() }, SUBSCRIPTION_EXPIRED_SUBJECT, content)
                                    .from(SALES_EMAIL).build());
                }
            }
        }
    }

    /**
     * Send email to user whose subscription period approaching to expire
     */
    public void sendMail_OnSubscriptionNearToExpire() throws Exception {
        boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty("email"));
        for (FinVendorUser fvUser : getUserDetails()) {
            String subscriptionStartTimeInHumanDate = fvUser.getSubscriptionStartTime();
            String subscriptionEndTimeInHumanDate = fvUser.getSubscriptionEndTime();
            String userName = fvUser.getUserName();
            logger.info("## sendReminderForSubscriptionReNewal - UserName: {}", userName);
            logger.info("## sendReminderForSubscriptionReNewal - subscriptionStartTimeInHumanDate: {}", subscriptionStartTimeInHumanDate);
            logger.info("## sendReminderForSubscriptionReNewal - subscriptionEndTimeInHumanDate: {}", subscriptionEndTimeInHumanDate);
            long subscriptionStartTime = convertSubscriptionDateToMillis(subscriptionStartTimeInHumanDate);
            long subscriptionEndTime = convertSubscriptionDateToMillis(subscriptionEndTimeInHumanDate);
            logger.info("## subscriptionStartTime in Long: {}", subscriptionStartTime);
            logger.info("## subscriptionEndTime in Long: {}", subscriptionEndTime);

            long diffInDays = DateUtils.getDateDifferenceInDays(subscriptionStartTime, subscriptionEndTime);
            logger.info("## sendReminderForSubscriptionReNew - diffInDays: {}", diffInDays);
            if ((diffInDays == 15L || diffInDays == 10L || diffInDays == 5L || diffInDays == 4L
                    || diffInDays == 3L || diffInDays == 2L || diffInDays == 1L) && emailEnabled) {

                String subject = "Finvendor - Subscription ";
                String content = "Dear" + " " + userName + "<br>"
                        + "Your Subscription " + fvUser.getSubscriptionType() + " is approaching towards end and will expire on "
                        + fvUser.getSubscriptionStartTime() + "<br><br>"
                        + "Kindly renew your subscription before expire." + "<br><br>"
                        + "Thank you for choosing us. " + "<br><br>"
                        + "Regards" + "<br>"
                        + "Finvendor Team";
                String email = fvUser.getEmail();
                String from = SALES_EMAIL;
                String[] to = new String[] { email };
                notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
            }
        }
    }

    public void resetSubscription(String userName) throws Exception {
        FinVendorUser existingUser = getUserDetailsByUsername(userName);
        if (existingUser != null && existingUser.getUserName() != null) {
            userDao.deleteSubscription(existingUser.getUserName());
        }
    }
}
