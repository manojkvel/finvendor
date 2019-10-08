package com.finvendor.api.subscription.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.subscription.dao.SubscriptionDao;
import com.finvendor.api.subscription.dto.*;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.common.util.Pair;
import com.finvendor.model.FinVendorUser;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static com.finvendor.common.util.DateUtils.getSubscriptionStartAndEndDateInMillis;

@Service
@Transactional
public class SubscriptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class.getName());

    private final SubscriptionDao dao;
    private final NotificationService notificationService;

    private final UserService userService;

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    private static final String SUBMIT_SUBJECT = "Finvendor - Subscription Details Successfully Submitted";
    private static final String ACTIVATION_SMART_SUBJECT = "Finvendor - Subscription \"SMART\" has been Successfully Activated";
    private static final String ACTIVATION_SAGE_SUBJECT = "Finvendor - Subscription \"SAGE\" has been Successfully Activated";


    @Autowired
    public SubscriptionService(SubscriptionDao dao, NotificationService notificationService, UserService userService) {
        this.dao = dao;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public String savePayment(String userName, SubscriptionDto dto) throws Exception {
        try {
            String refId = dao.savePaymentDetails(userName, dto);
            if (refId != null) {
                FinVendorUser userDetails = updateUserSubscription(userName, dto.getSubscriptionType());
                sentSubscriptionSubmissionEmail(userName, userDetails);
            }
            return refId;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void sentSubscriptionSubmissionEmail(String userName, FinVendorUser userDetails) throws Exception {
        boolean emailFlag = Boolean.parseBoolean(finvendorProperties.getProperty("email"));
        LOGGER.info("### Save subscription - emailFlag: {}", emailFlag);
        if (emailFlag) {
            String email = userDetails.getEmail();
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
        }
    }

    private FinVendorUser updateUserSubscription(String userName, String subscriptionType) throws ApplicationException {
        FinVendorUser existingUser = userService.getUserDetailsByUsername(userName);
        //Set subscription type
        if (existingUser.getSubscriptionDate() == null) {
            existingUser.setSubscriptionDate(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        }
        existingUser.setSubscriptionType(subscriptionType);
        existingUser.setSubscriptionState("PENDING");

        //set subscription time period
        existingUser.setSubscriptionStartTimeInMillis("N/A");
        existingUser.setSubscriptionEndTimeInMillis("N/A");

        userService.updateUserInfo(existingUser);
        return existingUser;
    }

    public ApiMessageEnum updatePayment(SubscriptionDto dto, List<SubscriptionDetails> dataList) throws Exception {
        try {
            ApiMessageEnum apiMessageEnum = ApiMessageEnum.FAILED_TO_UPDATE_SUBSCRIPTION;
            for (SubscriptionDetails subscriptionDetail : dataList) {
                apiMessageEnum = dao.updatePayment(dto, subscriptionDetail.getSubscriptionId());
                if (apiMessageEnum.equals(ApiMessageEnum.UPDATE_SUBSCRIPTION_SUCCESS)) {
                    FinVendorUser existingUser = userService.getUserDetailsByUsername(subscriptionDetail.getUserId());
                    Pair<Long, Long> subscriptionStartAndEndDateInMillis = getSubscriptionStartAndEndDateInMillis(30);
                    String subscriptionStartTimeInMillis = String.valueOf(subscriptionStartAndEndDateInMillis.getElement1());
                    String subscriptionEndTimeInMillis = String.valueOf(subscriptionStartAndEndDateInMillis.getElement2());
                    LOGGER.info("Subscription start time in ms: {}", subscriptionStartTimeInMillis);
                    LOGGER.info("Subscription end time in ms: {}", subscriptionStartTimeInMillis);
                    //set subscription time period
                    existingUser.setSubscriptionStartTimeInMillis(subscriptionStartTimeInMillis);
                    existingUser.setSubscriptionEndTimeInMillis(subscriptionEndTimeInMillis);
                    //Set subscription STATE
                    if (dto.getPaymentVerified()) {
                        existingUser.setSubscriptionState("ACTIVE");
                    }
                    else {
                        existingUser.setSubscriptionState("TERMINATE");
                    }
                    userService.updateUserInfo(existingUser);
                    sendActivationOrTerminationMail(existingUser);
                }
            }
            return apiMessageEnum;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    private void sendActivationOrTerminationMail(FinVendorUser existingUser) throws Exception {
        boolean email = Boolean.parseBoolean(finvendorProperties.getProperty("email"));
        LOGGER.info("### Send email flag: {}", email);
        if (email) {
            String from = EmailUtil.SALES_EMAIL;
            String[] to = new String[] { existingUser.getEmail() };
            String subscriptionType = existingUser.getSubscriptionType();
            String subscriptionState = existingUser.getSubscriptionState();
            if ("ACTIVE".equals(subscriptionState)) {
                if ("SMART".equals(subscriptionType)) {
                    String emailContentSb = "Dear " + existingUser.getUserName() + "<br>"
                            + "Your Subscription " + existingUser.getSubscriptionType()
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
                    String emailContentSb = "Dear " + existingUser.getUserName() + "<br>"
                            + "Your Subscription " + existingUser.getSubscriptionType()
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
                    LOGGER.warn("Unable to send an email due to Invalid subscriptionType: {}", subscriptionType);
                }
            }
            else if ("TERMINATE".equals(subscriptionState)) {
                LOGGER.info("TBD");
            }
        }
    }

    public String getSubscriptionsRecordStat(String state, String perPageMaxRecords, SubscriptionFilter filter) throws Exception {
        try {
            return dao.getSubscriptionsRecordStats(state, perPageMaxRecords, filter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<UserPaymentDto> findSubscriptions(String state, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy,
            SubscriptionFilter filter) throws Exception {
        try {
            return dao.findAllPayments(state, pageNumber, perPageMaxRecords, sortBy, orderBy, filter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public UserSubscriptionDto findUserSubscriptions(String userName) throws Exception {
        try {
            return dao.findUserSubscription(userName);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public Pair<Long, InputStream> downloadSubscriptions() throws Exception {
        try {
            Pair<Long, InputStream> customerAnalyticsDownloadData = dao.downloadSubscriptions();
            return customerAnalyticsDownloadData;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
