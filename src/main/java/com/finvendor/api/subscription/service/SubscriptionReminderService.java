package com.finvendor.api.subscription.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.subscription.dao.SubscriptionReminderDao;
import com.finvendor.common.util.DateUtils;
import com.finvendor.model.FinVendorUser;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

@Service
public class SubscriptionReminderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionReminderService.class.getName());
    private final SubscriptionReminderDao dao;

    private final NotificationService notificationService;

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    public SubscriptionReminderService(SubscriptionReminderDao dao,
            NotificationService notificationService) {
        this.dao = dao;
        this.notificationService = notificationService;
    }

    public void sendReminderForSubscriptionReNew() {
        List<FinVendorUser> finVendorUsers = dao.findAll();
        for (FinVendorUser fvUser : finVendorUsers) {
            long subscriptionStartTimeMs = Long.parseLong(fvUser.getSubscriptionStartTimeInMillis().trim());
            long subscriptionEndTimeMs = Long.parseLong(fvUser.getSubscriptionStartTimeInMillis());
            String userName = fvUser.getUserName();
            LOGGER.info("## sendReminderForSubscriptionReNew - UserName: {}", userName);
            LOGGER.info("## sendReminderForSubscriptionReNew - subscriptionStartTimeMs: {}", subscriptionStartTimeMs);
            LOGGER.info("## sendReminderForSubscriptionReNew - subscriptionEndTimeMs: {}", subscriptionEndTimeMs);
            long diffInDays = DateUtils.getDateDifferenceInDays(subscriptionStartTimeMs, subscriptionEndTimeMs);
            LOGGER.info("## sendReminderForSubscriptionReNew - diffInDays: {}", diffInDays);
            if (diffInDays == 15L || diffInDays == 10L || diffInDays == 5L || diffInDays == 4L
                    || diffInDays == 3L || diffInDays == 2L || diffInDays == 1L) {
                boolean emailFlag = Boolean.parseBoolean(finvendorProperties.getProperty("email"));
                LOGGER.info("## sendReminderForSubscriptionReNew - emailFlag: {}", emailFlag);
                if (emailFlag) {
                    String subscriptionType = fvUser.getSubscriptionType();
                    String endDateOfSubscription = DateUtils.getCurrentDate(fvUser.getSubscriptionStartTimeInMillis());
                    String subject = "Finvendor - Subscription ";
                    String content = "Dear" + " " + userName + "<br>"
                            + "Your Subscription " + subscriptionType + " is approaching towards end and will expire on "
                            + endDateOfSubscription + "<br><br>"
                            + "Kindly renew your subscription before expire." + "<br><br>"
                            + "Thank you for choosing us. " + "<br><br>"
                            + "Regards" + "<br>"
                            + "Finvendor Team";
                    String email = fvUser.getEmail();
                    String from = EmailUtil.SALES_EMAIL;
                    String[] to = new String[] { email };
                    try {
                        notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
                    } catch (Exception e) {
                        LOGGER.error("## Error has occurred while sending subscription re-new mail to user: {}, error:", userName, e);
                    }
                }
            }
        }
    }
}
