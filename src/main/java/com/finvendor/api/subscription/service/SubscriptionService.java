package com.finvendor.api.subscription.service;

import com.finvendor.api.common.dto.RecordStats;
import com.finvendor.api.configuration.service.SysConfig;
import com.finvendor.api.exception.ApiConflictException;
import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.subscription.dao.SubscriptionDao;
import com.finvendor.api.subscription.dao.SubscriptionHistoryDao;
import com.finvendor.api.subscription.dto.SubscriptionDetails;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.dto.SubscriptionFilter;
import com.finvendor.api.subscription.dto.UsersSubscriptionDto;
import com.finvendor.api.subscription.enums.SubscriptionStatesEnum;
import com.finvendor.api.subscription.enums.SubscriptionTypeEnum;
import com.finvendor.api.user.UserMsgEnums;
import com.finvendor.api.user.dto.UserSubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.Pair;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.subscription.UsersSubscription;
import com.finvendor.model.subscription.UsersSubscriptionHistory;
import com.finvendor.util.EmailUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.common.constant.AppConstants.FREE;
import static com.finvendor.common.constant.AppConstants.N_A;
import static com.finvendor.common.util.DateUtils.convertSubscriptionDateToMillis;
import static com.finvendor.common.util.DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format;
import static com.finvendor.util.EmailUtil.SALES_EMAIL;
import static org.springframework.http.HttpStatus.CREATED;

@Service
@Transactional
public class SubscriptionService {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionService.class.getName());

    private static final String SUBMIT_SUBJECT = "Finvendor - Subscription Details Successfully Submitted";
    private static final String ACTIVATION_SMART_SUBJECT = "Finvendor - Subscription \"SMART\" has been Successfully Activated";
    private static final String ACTIVATION_SAGE_SUBJECT = "Finvendor - Subscription \"SAGE\" has been Successfully Activated";
    private static final String TRIAL_PERIOD_OVER_EMAIL_SUBJECT = "Subscription trial period over!";
    private static final String TRIAL_PERIOD_OVER_EMAIL_MESSAGE =
            "Dear User <br>Your Trial subscription trial period is over. <br> Please go for "
                    + "for monthly subscription.<br><br>Regards,<br>FinvendorTeam";

    private static final String SUBSCRIPTION_EXPIRED_SUBJECT = "Finvendor - Subscription expired";

    private final SubscriptionDao subscriptionDao;
    private final SubscriptionHistoryDao subscriptionHistoryDao;
    private final NotificationService notificationService;
    private final UserService userService;


    @Autowired
    public SubscriptionService(SubscriptionDao subscriptionDao, SubscriptionHistoryDao subscriptionHistoryDao, UserService userService,
            NotificationService notificationService) {
        this.subscriptionDao = subscriptionDao;
        this.subscriptionHistoryDao = subscriptionHistoryDao;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    /**
     * Save subscription details into system
     */
    public boolean saveUserSubscription(String userName, SubscriptionDto subscriptionDto) throws Exception {
        LOG.info("## saveUserSubscription - START userName: {}", userName);
        return subscriptionDao.saveUserSubscription(userName, subscriptionDto);
    }

    public boolean isTrialOpted(String userName, String subscriptionType) {
        LOG.info("## isTrialOpted - START userName: {}, subscriptionType: {}", userName, subscriptionType);
        return subscriptionHistoryDao.isFreeTrialAlreadyChosen(userName, subscriptionType);
    }

    public ApiResponse<String, String> handleTrialPeriod(String userName, String trialSubscriptionType) throws Exception {
        LOG.info("## handleTrialPeriod - START userName: {}, trialSubscriptionType: {}", userName, trialSubscriptionType);
        ApiResponse<String, String> apiResponse;
        UsersSubscription existingSubscription = subscriptionDao.findUsersSubscription(userName);
        String type = existingSubscription.getSubscriptionType();
        if ((SubscriptionTypeEnum.FREE.name().equals(type) || SubscriptionTypeEnum.SMART.name().equals(type))) {
            //String trialPeriod = fvProperties.getProperty("trial_period_in_days");
            int trialPeriodInDays = SysConfig.config().getTrialPeriodInDays();
            LOG.info("## Trial Period In Days from Property file: {}", trialPeriodInDays);
            Date userTrailPeriodStartDate = DateUtils.getCurrentDateInDate();
            Date userTrailPeriodEndDate = DateUtils.addDaysInCurrentDate(userTrailPeriodStartDate, trialPeriodInDays);
            LOG.info("## userTrailPeriodStartDate: {}", userTrailPeriodStartDate);
            LOG.info("## userTrailPeriodEndDate: {}", userTrailPeriodEndDate);
            existingSubscription.setTrialPeriodStartTime(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(userTrailPeriodStartDate.getTime()));
            existingSubscription.setTrialPeriodEndTime(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(userTrailPeriodEndDate.getTime()));

            //below is to handle case smart-> sage trial
            String tempState = null;
            if (existingSubscription.getSubscriptionState() != null && existingSubscription.getSubscriptionState()
                    .equals(SubscriptionStatesEnum.ACTIVE.name())) {
                existingSubscription.setSubscriptionStartTime(null);
                existingSubscription.setSubscriptionEndTime(null);
                tempState = SubscriptionStatesEnum.ACTIVE.name();
            }
            existingSubscription.setSubscriptionType(trialSubscriptionType);
            existingSubscription.setSubscriptionState(SubscriptionTypeEnum.TRIAL.name());
            subscriptionDao.updateUsersSubscription(existingSubscription);
            //below is to handle case smart-> sage trial
            if (tempState != null && tempState.equals(SubscriptionStatesEnum.ACTIVE.name())) {
                existingSubscription.setTransactionRefNumber(null);
                existingSubscription.setTransactionDate(null);
                existingSubscription.setTransactionFor(null);
                existingSubscription.setPaymentMode(null);
                existingSubscription.setAmountTransferred(null);
                existingSubscription.setBankName(null);
                existingSubscription.setBankHolderName(null);
                existingSubscription.setPaymentVerified(null);
            }
            subscriptionHistoryDao.saveSubscriptionHistory(existingSubscription);
            apiResponse = buildResponse(ApiMessageEnum.CREATED, null, CREATED);
        }
        else {
            LOG.error("## User: {} already in trial period", userName);
            throw new ApiConflictException(WebUtils.CONFLICT);
        }
        return apiResponse;
    }

    public ApiMessageEnum updateUserSubscription(SubscriptionDto subscriptionDto, List<SubscriptionDetails> dataList) throws Exception {
        LOG.info("## updateUserSubscription - START ");
        try {
            ApiMessageEnum apiMessageEnum = ApiMessageEnum.FAILED_TO_UPDATE_SUBSCRIPTION;
            for (SubscriptionDetails subscriptionDetail : dataList) {
                apiMessageEnum = subscriptionDao.update_UsersSubscription(subscriptionDto, subscriptionDetail);
                subscriptionHistoryDao.saveSubscriptionHistory(subscriptionDao.findById(subscriptionDetail.getSubscriptionId()));
                sendMail_OnActivationOrTermination(getUsersSubscription(subscriptionDetail.getUserId()));
            }
            return apiMessageEnum;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public UsersSubscription getUsersSubscription(String userId) throws ApplicationException {
        LOG.info("## getUsersSubscription - START userId: {}", userId);
        return subscriptionDao.findUsersSubscription(userId);
    }

    public UsersSubscription get_second_Last_UsersSubscription_fromHistory() throws ApplicationException {
        LOG.info("## get_second_Last_UsersSubscription_fromHistory - START");
        Pair<UsersSubscriptionHistory, UsersSubscriptionHistory> top2Subscription = subscriptionHistoryDao.find_top2Subscription();
        UsersSubscriptionHistory top2_UsersSubscriptionHistory = top2Subscription.getElement2();
        UsersSubscription usersSubscription = new UsersSubscription();
        //Transform
        usersSubscription.setSubscriptionId(top2_UsersSubscriptionHistory.getSubscriptionId());
        usersSubscription.setUserName(top2_UsersSubscriptionHistory.getUserName());
        usersSubscription.setSubscriptionDate(top2_UsersSubscriptionHistory.getSubscriptionDate());
        usersSubscription.setSubscriptionType(top2_UsersSubscriptionHistory.getSubscriptionType());
        usersSubscription.setTrialPeriodStartTime(top2_UsersSubscriptionHistory.getTrialPeriodStartTime());
        usersSubscription.setTrialPeriodEndTime(top2_UsersSubscriptionHistory.getTrialPeriodEndTime());
        usersSubscription.setSubscriptionStartTime(top2_UsersSubscriptionHistory.getSubscriptionStartTime());
        usersSubscription.setSubscriptionEndTime(top2_UsersSubscriptionHistory.getSubscriptionEndTime());
        usersSubscription.setSubscriptionState(top2_UsersSubscriptionHistory.getSubscriptionState());
        usersSubscription.setTransactionRefNumber(top2_UsersSubscriptionHistory.getTransactionRefNumber());
        usersSubscription.setTransactionDate(top2_UsersSubscriptionHistory.getTransactionDate());
        usersSubscription.setTransactionFor(top2_UsersSubscriptionHistory.getTransactionFor());
        usersSubscription.setPaymentMode(top2_UsersSubscriptionHistory.getPaymentMode());
        usersSubscription.setAmountTransferred(top2_UsersSubscriptionHistory.getAmountTransferred());
        usersSubscription.setBankName(top2_UsersSubscriptionHistory.getBankName());
        usersSubscription.setBankHolderName(top2_UsersSubscriptionHistory.getBankHolderName());
        usersSubscription.setPaymentVerified(top2_UsersSubscriptionHistory.getPaymentVerified());
        return usersSubscription;
    }

    public UsersSubscriptionHistory findPreviousSubscription(String subscriptionType) {
        return subscriptionHistoryDao.find_PreviousSubscription(subscriptionType);
    }

    public boolean isTopHistoricalSubscriptionIsInRenewalState() throws ApplicationException {
        LOG.info("## get_second_Last_UsersSubscription_fromHistory - START");
        Pair<UsersSubscriptionHistory, UsersSubscriptionHistory> top2Subscription = subscriptionHistoryDao.find_top2Subscription();
        if (top2Subscription == null) {
            return false;
        }
        UsersSubscriptionHistory top1_UsersSubscriptionHistory = top2Subscription.getElement1();
        return top1_UsersSubscriptionHistory.getSubscriptionType().contains("RENEW");
    }

    public UserSubscriptionDto find_UsersSubscription(String userName) throws Exception {
        LOG.info("## find_UsersSubscription - START userName: {}", userName);
        UsersSubscription existingUsersSubscription = subscriptionDao.findUsersSubscription(userName);
        String subscriptionStartTime = existingUsersSubscription.getSubscriptionStartTime();
        String subscriptionEndTime = existingUsersSubscription.getSubscriptionEndTime();

        String subscriptionStartTimeInMs = StringUtils.isNotEmpty(subscriptionStartTime) ? String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionStartTime)) : null;
        String subscriptionEndTimeInMs = StringUtils.isNotEmpty(subscriptionEndTime) ? String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionEndTime)) : null;

        String trialPeriodStartTime = existingUsersSubscription.getTrialPeriodStartTime();
        String trialPeriodEndTime = existingUsersSubscription.getTrialPeriodEndTime();

        String trialPeriodStartTimeInMs = StringUtils.isNotEmpty(trialPeriodStartTime) ? String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodStartTime)) : null;
        String trialPeriodEndTimeInMs = StringUtils.isNotEmpty(trialPeriodEndTime) ? String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodEndTime)) : null;
        boolean userInTrialPeriod = isUserInTrialPeriod(existingUsersSubscription);
        String generalMsg = getGeneralMsg(userInTrialPeriod, existingUsersSubscription);

        //Previous subscription
        UsersSubscriptionHistory prevSubscription = findPreviousSubscription(existingUsersSubscription.getSubscriptionType());
        String previousSubscriptionType = prevSubscription != null ? prevSubscription.getSubscriptionType() : "";
        String previousSubscriptionState = prevSubscription != null ? prevSubscription.getSubscriptionState() : "";
        return new UserSubscriptionDto(existingUsersSubscription.getSubscriptionId(), userName,
                previousSubscriptionType, previousSubscriptionState, existingUsersSubscription.getSubscriptionType(),
                existingUsersSubscription.getSubscriptionState(),
                subscriptionStartTimeInMs, subscriptionEndTimeInMs, subscriptionStartTime, subscriptionEndTime, trialPeriodStartTimeInMs, trialPeriodEndTimeInMs,
                trialPeriodStartTime, trialPeriodEndTime, userInTrialPeriod, generalMsg);
    }

    private String getGeneralMsg(boolean userInTrialPeriod, UsersSubscription usersSubscription) {
        String trialPeriodStartTime = usersSubscription.getTrialPeriodStartTime();
        String trialPeriodEndTime = usersSubscription.getTrialPeriodEndTime();
        String generalMsg;
        if (userInTrialPeriod) {
            generalMsg = UserMsgEnums.USER_IN_TRIAL_PERIOD.getMsg(new Object[] { trialPeriodStartTime, trialPeriodEndTime });
        }
        else {
            String subscriptionState = usersSubscription.getSubscriptionType();
            if (SubscriptionTypeEnum.SMART.name().equals(subscriptionState) && usersSubscription.getSubscriptionState()
                    .equals(SubscriptionStatesEnum.ACTIVE.name())) {
                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SMART_ACTIVE
                        .getMsg(new Object[] { usersSubscription.getSubscriptionEndTime() });
            }
            else if (SubscriptionTypeEnum.SMART.name().equals(subscriptionState) && usersSubscription.getSubscriptionState()
                    .equals(SubscriptionStatesEnum.PENDING.name())) {
                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SMART_PENDING.getMsg();
            }
            else if (SubscriptionTypeEnum.SAGE.name().equals(subscriptionState) && usersSubscription.getSubscriptionState()
                    .equals(SubscriptionStatesEnum.ACTIVE.name())) {
                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SAGE_ACTIVE.getMsg(new Object[] { usersSubscription.getSubscriptionEndTime() });
            }
            else if (SubscriptionTypeEnum.SAGE.name().equals(subscriptionState) && usersSubscription.getSubscriptionState()
                    .equals(SubscriptionStatesEnum.PENDING.name())) {
                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_SAGE_PENDING.getMsg();
            }
            else {
                generalMsg = UserMsgEnums.SUBSCRIPTION_TYPE_FREE.getMsg();
            }
        }
        return generalMsg;
    }

    /**
     * Check subscription is in trial period
     */
    public boolean isUserInTrialPeriod(UsersSubscription usersSubscription) throws Exception {
        LOG.info("## isUserInTrialPeriod: subscriptionType: {}", usersSubscription.getSubscriptionType());
        boolean trialPeriod;
        String trialPeriodEndTime = usersSubscription.getTrialPeriodEndTime();
        if (trialPeriodEndTime != null) {
            trialPeriod =
                    Calendar.getInstance().getTimeInMillis() <= DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodEndTime);
        }
        else {
            trialPeriod = false;
        }
        return trialPeriod;
    }

    public void NullifyUserTrialPeriod(String userName) throws ApplicationException {
        UsersSubscription existingUsersSubscription = subscriptionDao.findUsersSubscription(userName);
        if (existingUsersSubscription != null) {
            existingUsersSubscription.setTrialPeriodStartTime(null);
            existingUsersSubscription.setTrialPeriodEndTime(null);
            subscriptionDao.updateUsersSubscription(existingUsersSubscription);
        }
    }

    public void setFree(String userName) throws ApplicationException {
        LOG.info("## setFree: {}", userName);
        UsersSubscription usersSubscription = subscriptionDao.findUsersSubscription(userName);
        if (usersSubscription != null && usersSubscription.getUserName() != null) {
            subscriptionDao.updateUserSubscriptionToFree(usersSubscription);
            subscriptionHistoryDao.saveSubscriptionHistory(usersSubscription);
        }
    }

    //### Email Service Start
    public void sendEmail_OnSubscriptionSubmission(String userName) throws Exception {
        LOG.info("## sendEmail_OnSubscriptionSubmission: {}", userName);
        boolean emailFlag = Objects.requireNonNull(SysConfig.config()).isEmailEnabled();
        LOG.info("## emailFlag: {}", emailFlag);
        if (emailFlag) {
            String email = userService.getUserDetailsByUsername(userName).getEmail();
            String from = EmailUtil.SALES_EMAIL;
            String[] to = new String[] { email };
            String content = "Dear" + " " + userName + "<br>"
                    + "Your Subscription details have been successfully submitted" + "<br><br>"
                    + "Please be informed that your subscription will get activated within next 48 hours."
                    + "<br>"
                    + "If you don't get any confirmation on activation of your Subscription within next 48 hours, do reach out to us at enquiry@finvendor.com ."
                    + "<br><br>"
                    + "Regards" + "<br>" + "Finvendor Team";
            notificationService.sendMail(new EmailBuilder.Builder(to, SUBMIT_SUBJECT, content).from(from).build());
            LOG.info("## Email sent to User successfully");
        }
    }

    /**
     * Send email to user whose free trial period is over
     */
    public void sendEMail_OnTrialPeriodOver() throws Exception {
        LOG.info("## sendEMail_OnTrialPeriodOver");
        List<FinVendorUser> userDetails = userService.getUserDetails();
        boolean emailEnabled = Objects.requireNonNull(SysConfig.config()).isEmailEnabled();
        LOG.info("## emailEnabled: {}", emailEnabled);
        for (FinVendorUser user : userDetails) {
            String userName = user.getUserName();
            UsersSubscription usersSubscription = subscriptionDao.findUsersSubscription(userName);
            if (usersSubscription.getTrialPeriodEndTime() != null && !isUserInTrialPeriod(usersSubscription) && emailEnabled) {
                notificationService.sendMail(new EmailBuilder.Builder(new String[] { user.getEmail() },
                        TRIAL_PERIOD_OVER_EMAIL_SUBJECT, TRIAL_PERIOD_OVER_EMAIL_MESSAGE)
                        .from(SALES_EMAIL).build());
                setFree(userName);
            }
        }
    }

    public void sendMail_OnActivationOrTermination(UsersSubscription usersSubscription) throws Exception {
        LOG.info("## sendMail_OnActivationOrTermination");
        boolean emailEnabled = Objects.requireNonNull(SysConfig.config()).isEmailEnabled();
        LOG.info("## emailEnabled: {}", emailEnabled);
        if (emailEnabled) {
            LOG.info("Email flag: true");
            String from = EmailUtil.SALES_EMAIL;
            String userName = usersSubscription.getUserName();
            String[] to = new String[] { userService.getUserDetailsByUsername(userName).getEmail() };
            String subscriptionType = usersSubscription.getSubscriptionType();
            String subscriptionState = usersSubscription.getSubscriptionState();
            if ("ACTIVE".equals(subscriptionState)) {
                if ("SMART".equals(subscriptionType)) {
                    String emailContentSb = "Dear " + userName + "<br>"
                            + "Your Subscription " + usersSubscription.getSubscriptionType()
                            + " details have been successfully Activated today." + "<br><br>"
                            + "Your subscription gives you  access to below items:" + "<br>" + "<br>"
                            + "1. Access to Unlimited Company profile search" + "<br>"
                            + "2. Access to Today's Market Summary Page" + "<br>"
                            + "3. Access to Today's performance of All indices & its constituents." + "<br>"
                            + "4. Access to Stock Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
                            + "<br>"
                            + "5. Access to Sector Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
                            + "<br>"
                            + "6. Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks."
                            + "<br>"
                            + "7. Set alerts on any companies if any new research analyst's report is available." + "<br>"
                            + "8. Track your shortlisted companies in your watchlist." + "<br>"
                            + "9.Get Daily market summary report through email." + "<br>"
                            + "10.Get Daily Sectoral Performance Summary Report through email." + "<br>"
                            + "11.Financial Results Calendar for coming week for NSE listed stocks everyday through email. "
                            + "<br>"
                            + "12.Corporate Actions for company under the watchlist everyday through email. " + "<br><br>"
                            + "Thank you for choosing us. " + "<br><br>"
                            + "Regards" + "<br>"
                            + "Finvendor Team";
                    notificationService.sendMail(
                            new EmailBuilder.Builder(to, ACTIVATION_SMART_SUBJECT, emailContentSb).from(from).build());
                }
                else if ("SAGE".equals(subscriptionType)) {
                    String emailContentSb = "Dear " + userName + "<br>"
                            + "Your Subscription " + usersSubscription.getSubscriptionType()
                            + " details have been successfully Activated today." + "<br><br>"
                            + "Your subscription gives you  access to below items:" + "<br>" + "<br>"
                            + "1  Access to Stock Screener \"Celebrity Investors' Strategies\" & \"Do It Yourself\" (CUSTOM Stock Screener). "
                            + "<br>"
                            + "2. Access to Unlimited Company profile search" + "<br>"
                            + "3. Access to Today's Market Summary Page" + "<br>"
                            + "4. Access to Today's performance of All indices & its constituents." + "<br>"
                            + "5. Access to Stock Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
                            + "<br>"
                            + "6. Access to Sector Screener - based on Research Analyst's Recommendations (unlimited search results per months)"
                            + "<br>"
                            + "7. Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks."
                            + "<br>"
                            + "8. Set alerts on any companies if any new research analyst's report is available." + "<br>"
                            + "9. Track your shortlisted companies in your watchlist." + "<br>"
                            + "10.Get Daily market summary report through email." + "<br>"
                            + "11.Get Daily Sectoral Performance Summary Report through email." + "<br>"
                            + "12.Financial Results Calendar for coming week for NSE listed stocks everyday through email. "
                            + "<br>"
                            + "13.Corporate Actions for company under the watchlist everyday through email. " + "<br>"
                            + "14.Financial results summary for company under the watchlist." + "<br><br>"
                            + "Thank you for choosing us. " + "<br><br>"
                            + "Regards" + "<br>"
                            + "Finvendor Team";
                    notificationService.sendMail(
                            new EmailBuilder.Builder(to, ACTIVATION_SAGE_SUBJECT, emailContentSb).from(from).build());
                }
                else {
                    LOG.warn("Unable to send an email due to Invalid subscriptionType: {}", subscriptionType);
                }
            }
            else if ("TERMINATE".equals(subscriptionState)) {
                LOG.info("TBD");
            }
            LOG.info("## After payment verification by Finvendor ADMIN, Email sent to user: {} successfully.", userName);
        }
        else {
            LOG.info("## Email flag: false , so unable to send email to user for Subscription Activation or Termination");
        }
    }

    /**
     * Send email to user whose subscription period is expired
     */
    public void sendMail_OnSubscriptionExpired() throws Exception {
        LOG.info("## sendMail_OnSubscriptionExpired");
        List<FinVendorUser> existingUsers = userService.getUserDetails();
        boolean emailEnabled = Objects.requireNonNull(SysConfig.config()).isEmailEnabled();
        LOG.info("## emailEnabled: {}", emailEnabled);

        for (FinVendorUser user : existingUsers) {
            String userName = user.getUserName();
            UsersSubscription usersSubscription = subscriptionDao.findUsersSubscription(userName);
            String subsEndTime = usersSubscription.getSubscriptionEndTime();
            if (!N_A.equals(subsEndTime) && Calendar.getInstance().getTimeInMillis() > convertSubscriptionDateToMillis(subsEndTime)) {
                usersSubscription.setSubscriptionType(FREE);
                usersSubscription.setSubscriptionStartTime(N_A);
                usersSubscription.setSubscriptionEndTime(N_A);
                subscriptionDao.updateUsersSubscription(usersSubscription);
                if (emailEnabled) {
                    String subscriptionType = usersSubscription.getSubscriptionType();
                    String content = "Dear" + " " + userName + "<br>"
                            + "Your Subscription " + subscriptionType + " is expired."
                            + "<br><br>"
                            + "Kindly renew your subscription." + "<br><br>"
                            + "Thank you for choosing us. " + "<br><br>"
                            + "Regards" + "<br>"
                            + "Finvendor Team";
                    notificationService.sendMail(
                            new EmailBuilder.Builder(new String[] { user.getEmail() }, SUBSCRIPTION_EXPIRED_SUBJECT, content)
                                    .from(SALES_EMAIL).build());
                    setFree(userName);
                }
            }
        }
    }

    /**
     * Send email to user whose subscription period approaching to expire
     */
    public void sendMail_OnSubscriptionNearToExpire() throws Exception {
        LOG.info("## sendMail_OnSubscriptionNearToExpire");
        boolean emailEnabled = Objects.requireNonNull(SysConfig.config()).isEmailEnabled();
        if (emailEnabled) {
            String[] split = Objects.requireNonNull(SysConfig.config()).getReminderDays().trim().split(",");

            List<Long> reminderDaysList = new ArrayList<>();
            for (String day : split) {
                reminderDaysList.add(Long.parseLong(day));
            }
            LOG.info("## reminderDaysList: {}", reminderDaysList);
            for (FinVendorUser fvUser : userService.getUserDetails()) {
                UsersSubscription usersSubscription = subscriptionDao.findUsersSubscription(fvUser.getUserName());
                String subscriptionStartTimeInHumanDate = usersSubscription.getSubscriptionStartTime();
                String subscriptionEndTimeInHumanDate = usersSubscription.getSubscriptionEndTime();
                String userName = fvUser.getUserName();
                LOG.info("## sendReminderForSubscriptionReNewal - UserName: {}", userName);
                LOG.info("## sendReminderForSubscriptionReNewal - subscriptionStartTimeInHumanDate: {}", subscriptionStartTimeInHumanDate);
                LOG.info("## sendReminderForSubscriptionReNewal - subscriptionEndTimeInHumanDate: {}", subscriptionEndTimeInHumanDate);
                long subscriptionStartTime = convertSubscriptionDateToMillis(subscriptionStartTimeInHumanDate);
                long subscriptionEndTime = convertSubscriptionDateToMillis(subscriptionEndTimeInHumanDate);
                LOG.info("## subscriptionStartTime in Long: {}", subscriptionStartTime);
                LOG.info("## subscriptionEndTime in Long: {}", subscriptionEndTime);

                long diffInDays = DateUtils.getDateDifferenceInDays(subscriptionStartTime, subscriptionEndTime);
                LOG.info("## sendReminderForSubscriptionReNew - diffInDays: {}", diffInDays);
                //            if ((diffInDays == 15L || diffInDays == 10L || diffInDays == 5L || diffInDays == 4L
                //                    || diffInDays == 3L || diffInDays == 2L || diffInDays == 1L) && emailEnabled) {
                if (reminderDaysList.contains(diffInDays)) {
                    String subject = "Finvendor - Subscription ";
                    String content = "Dear" + " " + userName + "<br>"
                            + "Your Subscription " + usersSubscription.getSubscriptionType()
                            + " is approaching towards end and will expire on "
                            + usersSubscription.getSubscriptionStartTime() + "<br><br>"
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
    }
    //### Email Service End

    public RecordStats getSubscriptionsRecordStat(String state, String perPageMaxRecords, SubscriptionFilter filter)
            throws Exception {
        try {
            return subscriptionDao.getSubscriptionsRecordStats(state, perPageMaxRecords, filter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<UsersSubscriptionDto> findAllUsersSubscription(String state, String pageNumber, String perPageMaxRecords, String sortBy,
            String orderBy, SubscriptionFilter filter) throws Exception {
        try {
            return subscriptionDao.findAllUsersSubscription(state, pageNumber, perPageMaxRecords, sortBy, orderBy, filter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public Pair<Long, InputStream> downloadSubscriptions() throws Exception {
        try {
            return subscriptionDao.downloadSubscriptions();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
