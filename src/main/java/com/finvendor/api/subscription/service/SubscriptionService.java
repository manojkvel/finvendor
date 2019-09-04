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

import java.util.Calendar;
import java.util.List;

import static com.finvendor.common.util.DateUtils.getSubscriptionStartAndEndDateInMillis;

@Service
@Transactional
public class SubscriptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class.getName());

    private final SubscriptionDao dao;
    private final NotificationService notificationService;
    private final UserService userService;

    @Autowired
    public SubscriptionService(SubscriptionDao dao, NotificationService notificationService, UserService userService) {
        this.dao = dao;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public String savePayment(String userName, SubscriptionDto dto) throws Exception {
        try {
            //Save Payment details
            String refId = dao.savePayment(userName, dto);

            if (refId != null) {
                //Update subscription type in user table
                FinVendorUser userDetails = updateUserSubscription(userName, dto.getSubscriptionType());

                //Send Email to user
                //sentEmail(refId, userDetails);
            }
            else {
                refId = null;
            }
            return refId;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void sentEmail(String refId, FinVendorUser userDetails) throws Exception {
        String email = userDetails.getEmail();
        String from = EmailUtil.SALES_EMAIL;
        String[] to = new String[] { email };
        String subject = prepareSubject(refId);
        String content = prepareContent(refId);
        notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
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

    private String prepareContent(String refId) {
        return "dd";
    }

    private String prepareSubject(String refId) {
        return "";
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
                }
            }
            return apiMessageEnum;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getSubscriptionsRecordStat(String perPageMaxRecords, SubscriptionFilter filter) throws Exception {
        try {
            return dao.getSubscriptionsRecordStats(perPageMaxRecords, filter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<UserPaymentDto> findSubscriptions(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy,
            SubscriptionFilter filter) throws Exception {
        try {
            return dao.findAllPayments(pageNumber, perPageMaxRecords, sortBy, orderBy, filter);
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
}
