package com.finvendor.api.subscription.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.subscription.dao.SubscriptionDao;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.common.util.Pair;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.subscription.UserPayment;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

            //Update subscription type
            FinVendorUser userDetails = updateUserSubscription(userName, dto.getSubscriptionType());

            //Send Email to user
            //sentEmail(refId, userDetails);

            return refId;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void sentEmail(String refId, FinVendorUser userDetails) throws Exception {
        String email = userDetails.getEmail();
        String from = EmailUtil.SALES_EMAIL;
        String[] to = new String[]{email};
        String subject = prepareSubject(refId);
        String content = prepareContent(refId);
        notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
    }

    private FinVendorUser updateUserSubscription(String userName, String subscriptionType) throws ApplicationException {
        FinVendorUser existingUser = userService.getUserDetailsByUsername(userName);

        //set subscription time period
        existingUser.setSubscriptionStartTimeInMillis("N/A");
        existingUser.setSubscriptionEndTimeInMillis("N/A");

        //Set subscription type
        existingUser.setSubscriptionType(subscriptionType);
        existingUser.setSubscriptionStatus("FALSE");
        userService.saveUserInfo(existingUser);
        return existingUser;
    }

    public static void main(String[] args) {
        System.out.println(java.time.LocalDate.now());
    }

    private String prepareContent(String refId) {
        return "dd";
    }

    private String prepareSubject(String refId) {
        return "";
    }

    public boolean updatePayment(String userName, SubscriptionDto dto, String subscriptionRefId) throws Exception {
        try {
            boolean paymentUpdateStatus = dao.updatePayment(dto, subscriptionRefId);
            if(paymentUpdateStatus){
                FinVendorUser existingUser = userService.getUserDetailsByUsername(userName);

                Pair<Long, Long> subscriptionStartAndEndDateInMillis = getSubscriptionStartAndEndDateInMillis(30);
                String subscriptionStartTimeInMillis = String.valueOf(subscriptionStartAndEndDateInMillis.getElement1());
                String subscriptionEndTimeInMillis = String.valueOf(subscriptionStartAndEndDateInMillis.getElement2());

                LOGGER.info("Subscription start time in ms: {}", subscriptionStartTimeInMillis);
                LOGGER.info("Subscription end time in ms: {}", subscriptionStartTimeInMillis);

                //set subscription time period
                existingUser.setSubscriptionStartTimeInMillis(subscriptionStartTimeInMillis);
                existingUser.setSubscriptionEndTimeInMillis(subscriptionEndTimeInMillis);

                //Set subscription type
                existingUser.setSubscriptionStatus("TRUE");
                userService.saveUserInfo(existingUser);
            }
            return paymentUpdateStatus;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<UserPayment> findSubscriptions() throws Exception {
        try {
            return dao.findAllPayments();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
