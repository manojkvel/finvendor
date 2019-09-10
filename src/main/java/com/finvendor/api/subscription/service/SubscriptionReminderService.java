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
    @Autowired
    private SubscriptionReminderDao dao;

    @Autowired
    private NotificationService notificationService;

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    public void sendReminderForSubscriptionReNew() throws Exception {
        List<FinVendorUser> finVendorUsers = dao.findAll();
        for (FinVendorUser fvUser : finVendorUsers) {
            long subscriptionStartTimeMs = Long.parseLong(fvUser.getSubscriptionStartTimeInMillis().trim());
            long subscriptionEndTimeMs = Long.parseLong(fvUser.getSubscriptionStartTimeInMillis());
            LOGGER.info("### UserName: {}", fvUser.getUserName());
            LOGGER.info("### subscriptionStartTimeMs: {}", subscriptionStartTimeMs);
            LOGGER.info("### subscriptionEndTimeMs: {}", subscriptionEndTimeMs);
            long diffInDays = DateUtils.getDateDifferenceInDays(subscriptionStartTimeMs, subscriptionEndTimeMs);
            LOGGER.info("### diffInDays: {}", diffInDays);
            if (diffInDays == 15L || diffInDays == 10 || diffInDays == 5 || diffInDays == 4
                    || diffInDays == 3 || diffInDays == 2
                    || diffInDays == 1) {
                boolean emailFlag = Boolean.parseBoolean(finvendorProperties.getProperty("email"));
                LOGGER.info("### Save subscription - emailFlag: {}", emailFlag);
                if (emailFlag) {
                    String subject = finvendorProperties.getProperty("subscription_reminder_subject");
                    String content = finvendorProperties.getProperty("subscription_reminder_content");
                    String email = fvUser.getEmail();
                    String from = EmailUtil.SALES_EMAIL;
                    String[] to = new String[] { email };
                    notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
                }
            }
        }
    }
}
