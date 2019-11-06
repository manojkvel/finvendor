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
        LOG.debug("UserServiceImpl : validateUsername - {}", username);
        return userDao.validateUsername(username.toLowerCase());
    }

    public FinVendorUser getUserDetailsByUsername(String username) throws ApplicationException {
        LOG.debug("Entering : UserServiceImpl.getUserDetailsByUsername");
        FinVendorUser user = userDao.getUserDetailsByUsername(username);
        LOG.debug("Leaving : UserServiceImpl.getUserDetailsByUsername");
        return user;
    }

    public int updateUnsuccessfulLoginAttempts(String username, boolean reset) {
        return userDao.updateUnsuccessfulLoginAttempts(username, reset);
    }

    public int updateUserAccountStatus(String username, boolean status) {
        LOG.debug("UserServiceImpl : updateUserAccountStatus to {} for {}", status, username);
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

//    /**
//     * Check user is in trial period
//     */
//    public boolean isUserInTrialPeriod(FinVendorUser user) throws Exception {
//        boolean trialPeriod;
//        String trialPeriodEndTime = user.getTrialPeriodEndTime();
//        if (trialPeriodEndTime != null) {
//            trialPeriod =
//                    Calendar.getInstance().getTimeInMillis() <= DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodEndTime);
//        }
//        else {
//            trialPeriod = false;
//        }
//        return trialPeriod;
//    }

//    public UserSubscriptionDto findUserSubscriptionDetails(FinVendorUser existingUser) throws Exception {
//        String subscriptionStartTime = existingUser.getSubscriptionStartTime();
//        String subscriptionEndTime = existingUser.getSubscriptionEndTime();
//
//        String subscriptionStartTimeInMs = StringUtils.isNotEmpty(subscriptionStartTime) ?
//                String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionStartTime)) :
//                null;
//        String subscriptionEndTimeInMs = StringUtils.isNotEmpty(subscriptionEndTime) ?
//                String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionEndTime)) :
//                null;
//
//        String trialPeriodStartTime = existingUser.getTrialPeriodStartTime();
//        String trialPeriodEndTime = existingUser.getTrialPeriodEndTime();
//
//        String trialPeriodStartTimeInMs = StringUtils.isNotEmpty(trialPeriodStartTime) ?
//                String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodStartTime)) :
//                null;
//        String trialPeriodEndTimeInMs = StringUtils.isNotEmpty(trialPeriodEndTime) ?
//                String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodEndTime)) :
//                null;
//        boolean userInTrialPeriod = isUserInTrialPeriod(existingUser);
//        String generalMsg = getGeneralMsg(userInTrialPeriod, existingUser);
//        return new UserSubscriptionDto(existingUser.getUserName(),
//                existingUser.getSubscriptionType(), existingUser.getSubscriptionState(), subscriptionStartTimeInMs,
//                subscriptionEndTimeInMs, subscriptionStartTime, subscriptionEndTime, trialPeriodStartTimeInMs, trialPeriodEndTimeInMs,
//                trialPeriodStartTime, trialPeriodEndTime, userInTrialPeriod, generalMsg);
//    }

//    private String getGeneralMsg(boolean userInTrialPeriod, FinVendorUser existingUser) {
//        String trialPeriodStartTime = existingUser.getTrialPeriodStartTime();
//        String trialPeriodEndTime = existingUser.getTrialPeriodEndTime();
//        String generalMsg;
//        if (userInTrialPeriod) {
//            generalMsg = UserMsgEnums.USER_IN_TRIAL_PERIOD.getMsg(new Object[] { trialPeriodStartTime, trialPeriodEndTime });
//        }
//        else {
//            String subscriptionState = existingUser.getSubscriptionType();
//            if (SubscriptionTypeEnum.SMART.name().equals(subscriptionState) && existingUser.getSubscriptionState()
//                    .equals(SubscriptionStatesEnum.ACTIVE.name())) {
//                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SMART_ACTIVE.getMsg(new Object[] { existingUser.getSubscriptionEndTime() });
//            }
//            else if (SubscriptionTypeEnum.SMART.name().equals(subscriptionState) && existingUser.getSubscriptionState()
//                    .equals(SubscriptionStatesEnum.PENDING.name())) {
//                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SMART_PENDING.getMsg();
//            }
//            else if (SubscriptionTypeEnum.SAGE.name().equals(subscriptionState) && existingUser.getSubscriptionState()
//                    .equals(SubscriptionStatesEnum.ACTIVE.name())) {
//                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SAGE_ACTIVE.getMsg(new Object[] { existingUser.getSubscriptionEndTime() });
//            }
//            else if (SubscriptionTypeEnum.SAGE.name().equals(subscriptionState) && existingUser.getSubscriptionState()
//                    .equals(SubscriptionStatesEnum.PENDING.name())) {
//                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SAGE_PENDING.getMsg();
//            }
//            else {
//                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_FREE.getMsg();
//            }
//        }
//        return generalMsg;
//    }

    //    public FinVendorUser updateUserWithSubscription(String userName, String subscriptionType) throws ApplicationException {
    //        FinVendorUser existingUser = getUserDetailsByUsername(userName);
    //        //Set subscription type
    //        if (existingUser.getSubscriptionDate() == null) {
    //            existingUser.setSubscriptionDate(String.valueOf(Calendar.getInstance().getTimeInMillis()));
    //        }
    //        existingUser.setSubscriptionType(subscriptionType);
    //        existingUser.setSubscriptionState(SubscriptionStatesEnum.PENDING.name());
    //        //set subscription time period as null and start and end time will be set when Admin Activate it after payment received
    //        existingUser.setSubscriptionStartTime(null);
    //        existingUser.setSubscriptionEndTime(null);
    //        updateUserInfo(existingUser);
    //        LOG.info("## User's subscription details saves successfully");
    //        return existingUser;
    //    }

//    public void sendEmail_OnSubscriptionSubmission(String userName) throws Exception {
//        boolean emailFlag = Boolean.parseBoolean(fvProperties.getProperty("email"));
//        LOG.info("## Save subscription - emailFlag: {}", emailFlag);
//        if (emailFlag) {
//            String email = getUserDetailsByUsername(userName).getEmail();
//            String from = EmailUtil.SALES_EMAIL;
//            String[] to = new String[] { email };
//            String content = "Dear" + " " + userName + "<br>"
//                    + "Your Subscription details have been successfully submitted" + "<br><br>"
//                    + "Please be informed that your subscription will get activated within next 48 hours."
//                    + "<br>"
//                    + "If you don't get any confirmation on activation of your Subscription within next 48 hours, do reach out to us at enquiry@finvendor.com ."
//                    + "<br><br>"
//                    + "Regards" + "<br>" + "Finvendor Team";
//            notificationService.sendMail(new EmailBuilder.Builder(to, SUBMIT_SUBJECT, content).from(from).build());
//            LOG.info("## Email sent to User successfully");
//        }
//    }
//
//    /**
//     * Send email to user whose free trial period is over
//     */
//    public void sendEMail_OnTrialPeriodOver() throws Exception {
//        List<FinVendorUser> userDetails = getUserDetails();
//        boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty(EMAIL));
//        for (FinVendorUser user : userDetails) {
//            if (user.getTrialPeriodEndTime() != null && !isUserInTrialPeriod(user) && emailEnabled) {
//                notificationService.sendMail(new EmailBuilder.Builder(new String[] { user.getEmail() },
//                        TRIAL_PERIOD_OVER_EMAIL_SUBJECT, TRIAL_PERIOD_OVER_EMAIL_MESSAGE)
//                        .from(SALES_EMAIL).build());
//            }
//        }
//    }
//
//    public void sendMail_OnActivationOrTermination(UsersSubscription usersSubscription) throws Exception {
//        if ("true".equals(fvProperties.getProperty("email"))) {
//            LOG.info("Email flag: true");
//            String from = EmailUtil.SALES_EMAIL;
//            String userName = usersSubscription.getUserName();
//            String[] to = new String[] { getUserDetailsByUsername(userName).getEmail()};
//            String subscriptionType = usersSubscription.getSubscriptionType();
//            String subscriptionState = usersSubscription.getSubscriptionState();
//            if ("ACTIVE".equals(subscriptionState)) {
//                if ("SMART".equals(subscriptionType)) {
//                    String emailContentSb = "Dear " + userName + "<br>"
//                            + "Your Subscription " + usersSubscription.getSubscriptionType()
//                            + " details have been successfully Activated today." + "<br><br>"
//                            + "Your subscription gives you  access to below items:" + "<br>" + "<br>"
//                            + "1. Access to Unlimited Company profile search" + "<br>"
//                            + "2. Access to Today's Market Summary Page" + "<br>"
//                            + "3. Access to Today's performance of All indices & its constituents." + "<br>"
//                            + "4. Access to Stock Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
//                            + "<br>"
//                            + "5. Access to Sector Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
//                            + "<br>"
//                            + "6. Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks."
//                            + "<br>"
//                            + "7. Set alerts on any companies if any new research analyst's report is available." + "<br>"
//                            + "8. Track your shortlisted companies in your watchlist." + "<br>"
//                            + "9.Get Daily market summary report through email." + "<br>"
//                            + "10.Get Daily Sectoral Performance Summary Report through email." + "<br>"
//                            + "11.Financial Results Calendar for coming week for NSE listed stocks everyday through email. "
//                            + "<br>"
//                            + "12.Corporate Actions for company under the watchlist everyday through email. " + "<br><br>"
//                            + "Thank you for choosing us. " + "<br><br>"
//                            + "Regards" + "<br>"
//                            + "Finvendor Team";
//                    notificationService.sendMail(
//                            new EmailBuilder.Builder(to, ACTIVATION_SMART_SUBJECT, emailContentSb).from(from).build());
//                }
//                else if ("SAGE".equals(subscriptionType)) {
//                    String emailContentSb = "Dear " + userName + "<br>"
//                            + "Your Subscription " + usersSubscription.getSubscriptionType()
//                            + " details have been successfully Activated today." + "<br><br>"
//                            + "Your subscription gives you  access to below items:" + "<br>" + "<br>"
//                            + "1  Access to Stock Screener \"Celebrity Investors' Strategies\" & \"Do It Yourself\" (CUSTOM Stock Screener). "
//                            + "<br>"
//                            + "2. Access to Unlimited Company profile search" + "<br>"
//                            + "3. Access to Today's Market Summary Page" + "<br>"
//                            + "4. Access to Today's performance of All indices & its constituents." + "<br>"
//                            + "5. Access to Stock Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
//                            + "<br>"
//                            + "6. Access to Sector Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
//                            + "<br>"
//                            + "7. Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks."
//                            + "<br>"
//                            + "8. Set alerts on any companies if any new research analyst's report is available." + "<br>"
//                            + "9. Track your shortlisted companies in your watchlist." + "<br>"
//                            + "10.Get Daily market summary report through email." + "<br>"
//                            + "11.Get Daily Sectoral Performance Summary Report through email." + "<br>"
//                            + "12.Financial Results Calendar for coming week for NSE listed stocks everyday through email. "
//                            + "<br>"
//                            + "13.Corporate Actions for company under the watchlist everyday through email. " + "<br>"
//                            + "14.Financial results summary for company under the watchlist." + "<br><br>"
//                            + "Thank you for choosing us. " + "<br><br>"
//                            + "Regards" + "<br>"
//                            + "Finvendor Team";
//                    notificationService.sendMail(
//                            new EmailBuilder.Builder(to, ACTIVATION_SAGE_SUBJECT, emailContentSb).from(from).build());
//                }
//                else {
//                    LOG.warn("Unable to send an email due to Invalid subscriptionType: {}", subscriptionType);
//                }
//            }
//            else if ("TERMINATE".equals(subscriptionState)) {
//                LOG.info("TBD");
//            }
//            LOG.info("## After payment verification by Finvendor ADMIN, Email sent to user: {} successfully.",
//                    userName);
//        }
//        else {
//            LOG.info("## Email flag: false , so unable to send email to user for Subscription Activation or Termination");
//        }
//    }
//
//    /**
//     * Send email to user whose subscription period is expired
//     */
//    public void sendMail_OnSubscriptionExpired() throws Exception {
//        List<FinVendorUser> existingUsers = getUserDetails();
//        boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty(EMAIL));
//        for (FinVendorUser user : existingUsers) {
//            String subsEndTime = user.getSubscriptionEndTime();
//            if (!N_A.equals(subsEndTime) && Calendar.getInstance().getTimeInMillis() > convertSubscriptionDateToMillis(subsEndTime)) {
//                user.setSubscriptionType(FREE);
//                user.setSubscriptionStartTime(N_A);
//                user.setSubscriptionEndTime(N_A);
//                updateUserInfo(user);
//                if (emailEnabled) {
//                    String subscriptionType = user.getSubscriptionType();
//                    String content = "Dear" + " " + user.getUserName() + "<br>"
//                            + "Your Subscription " + subscriptionType + " is expired."
//                            + "<br><br>"
//                            + "Kindly renew your subscription." + "<br><br>"
//                            + "Thank you for choosing us. " + "<br><br>"
//                            + "Regards" + "<br>"
//                            + "Finvendor Team";
//                    notificationService.sendMail(
//                            new EmailBuilder.Builder(new String[] { user.getEmail() }, SUBSCRIPTION_EXPIRED_SUBJECT, content)
//                                    .from(SALES_EMAIL).build());
//                }
//            }
//        }
//    }
//
//    /**
//     * Send email to user whose subscription period approaching to expire
//     */
//    public void sendMail_OnSubscriptionNearToExpire() throws Exception {
//        boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty("email"));
//        for (FinVendorUser fvUser : getUserDetails()) {
//            String subscriptionStartTimeInHumanDate = fvUser.getSubscriptionStartTime();
//            String subscriptionEndTimeInHumanDate = fvUser.getSubscriptionEndTime();
//            String userName = fvUser.getUserName();
//            LOG.info("## sendReminderForSubscriptionReNewal - UserName: {}", userName);
//            LOG.info("## sendReminderForSubscriptionReNewal - subscriptionStartTimeInHumanDate: {}", subscriptionStartTimeInHumanDate);
//            LOG.info("## sendReminderForSubscriptionReNewal - subscriptionEndTimeInHumanDate: {}", subscriptionEndTimeInHumanDate);
//            long subscriptionStartTime = convertSubscriptionDateToMillis(subscriptionStartTimeInHumanDate);
//            long subscriptionEndTime = convertSubscriptionDateToMillis(subscriptionEndTimeInHumanDate);
//            LOG.info("## subscriptionStartTime in Long: {}", subscriptionStartTime);
//            LOG.info("## subscriptionEndTime in Long: {}", subscriptionEndTime);
//
//            long diffInDays = DateUtils.getDateDifferenceInDays(subscriptionStartTime, subscriptionEndTime);
//            LOG.info("## sendReminderForSubscriptionReNew - diffInDays: {}", diffInDays);
//            if ((diffInDays == 15L || diffInDays == 10L || diffInDays == 5L || diffInDays == 4L
//                    || diffInDays == 3L || diffInDays == 2L || diffInDays == 1L) && emailEnabled) {
//
//                String subject = "Finvendor - Subscription ";
//                String content = "Dear" + " " + userName + "<br>"
//                        + "Your Subscription " + fvUser.getSubscriptionType() + " is approaching towards end and will expire on "
//                        + fvUser.getSubscriptionStartTime() + "<br><br>"
//                        + "Kindly renew your subscription before expire." + "<br><br>"
//                        + "Thank you for choosing us. " + "<br><br>"
//                        + "Regards" + "<br>"
//                        + "Finvendor Team";
//                String email = fvUser.getEmail();
//                String from = SALES_EMAIL;
//                String[] to = new String[] { email };
//                notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
//            }
//        }
//    }

    //    public void updateSubscriptionStartAndEndTime(FinVendorUser existingUser, int numberOfDays) {
    //        Pair<String, String> subscriptionStartAndEndDate = DateUtils.getSubscriptionStartAndEndDateInHumanDate(numberOfDays);
    //        String subscriptionStartTime = subscriptionStartAndEndDate.getElement1();
    //        String subscriptionEndTime = subscriptionStartAndEndDate.getElement2();
    //        LOG.info("## Subscription start time: {}", subscriptionStartTime);
    //        LOG.info("## Subscription end time: {}", subscriptionStartTime);
    //        //set subscription time period
    //        existingUser.setSubscriptionStartTime(subscriptionStartTime);
    //        existingUser.setSubscriptionEndTime(subscriptionEndTime);
    //    }
    //
    //    public void updateSubscriptionState(FinVendorUser existingUser, Boolean paymentVerified) {
    //        //Set subscription STATE
    //        if (paymentVerified) {
    //            existingUser.setSubscriptionState(SubscriptionStatesEnum.ACTIVE.name());
    //        }
    //        else {
    //            existingUser.setSubscriptionState(SubscriptionStatesEnum.TERMINATE.name());
    //        }
    //    }

//    public void NullifyUserTrialPeriod(String userName) throws ApplicationException {
//        FinVendorUser existingUser = getUserDetailsByUsername(userName);
//        existingUser.setTrialPeriodStartTime(null);
//        existingUser.setTrialPeriodEndTime(null);
//        updateUserInfo(existingUser);
//    }

    //    public ApiResponse<String, String> handleTrialPeriod(String userName, String newSubscriptionType) throws Exception {
    //        ApiResponse<String, String> apiResponse;
    //        FinVendorUser existingUser = getUserDetailsByUsername(userName);
    //        String existingUserSubscriptionType = existingUser.getSubscriptionType();
    //        if (existingUser.getTrialPeriodStartTime() == null &&
    //                (SubscriptionTypeEnum.FREE.name().equals(existingUserSubscriptionType) || SubscriptionTypeEnum.SMART.name()
    //                        .equals(existingUserSubscriptionType))) {
    //            String trialPeriod = fvProperties.getProperty("trial_period_in_days");
    //            LOG.info("## Trial Period In Days from Property file: {}", trialPeriod);
    //            Date userTrailPeriodStartDate = DateUtils.getCurrentDateInDate();
    //            Date userTrailPeriodEndDate = DateUtils.addDaysInCurrentDate(userTrailPeriodStartDate, Integer.parseInt(trialPeriod));
    //            LOG.info("## userTrailPeriodStartDate: {}", userTrailPeriodStartDate);
    //            LOG.info("## userTrailPeriodEndDate: {}", userTrailPeriodEndDate);
    //            existingUser.setTrialPeriodStartTime(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(userTrailPeriodStartDate.getTime()));
    //            existingUser.setTrialPeriodEndTime(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(userTrailPeriodEndDate.getTime()));
    //            existingUser.setSubscriptionType(newSubscriptionType);
    //            existingUser.setSubscriptionState(SubscriptionTypeEnum.TRIAL.name());
    //            updateUserInfo(existingUser);
    //            apiResponse = buildResponse(ApiMessageEnum.CREATED, null, CREATED);
    //        }
    //        else {
    //            LOG.error("## User: {} already in trial period", userName);
    //            throw new ApiConflictException(WebUtils.CONFLICT);
    //        }
    //        return apiResponse;
    //    }

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

//    public void expire_FromSmartTrial(String userName) throws ApplicationException {
//        FinVendorUser existingUser = getUserDetailsByUsername(userName);
//        if (existingUser != null && existingUser.getUserName() != null) {
//            userDao.updateUserSubscriptionToFree(existingUser);
//        }
//    }
//
//    public void expire_FromSageTrial(String userName) throws ApplicationException {
//        FinVendorUser existingUser = getUserDetailsByUsername(userName);
//        if (existingUser != null && existingUser.getUserName() != null) {
//            userDao.updateUserSubscriptionToFree(existingUser);
//        }
//    }
}
