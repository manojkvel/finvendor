package com.finvendor.api.notification.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.user.service.UserService;
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
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class.getName());

    private static final String TRIAL_PERIOD_OVER_EMAIL_SUBJECT = "Subscription trial period over!";
    private static final String TRIAL_PERIOD_OVER_EMAIL_MESSAGE =
            "Dear User <br>Your Trial subscription trial period is over. <br> Please go for "
                    + "for monthly subscription.<br><br>Regards,<br>FinvendorTeam";

    @Resource(name = "finvendorProperties")
    private Properties fvProperties;

    @Autowired
    private UserService userService;

    public void sendEMailToAllUserWhoseTrialPeriodOver() {
        List<FinVendorUser> userDetails = userService.getUserDetails();
        for (FinVendorUser user : userDetails) {
            boolean userInTrialPeriod = userService.isUserInTrialPeriod(user);
            if (!userInTrialPeriod) {
                boolean emailEnabled = Boolean.parseBoolean(fvProperties.getProperty("email"));
                if (emailEnabled) {
                    String to = user.getEmail();
                    try {
                        EmailUtil.sendMail(to, TRIAL_PERIOD_OVER_EMAIL_SUBJECT, TRIAL_PERIOD_OVER_EMAIL_MESSAGE);
                    } catch (Exception e) {
                        LOGGER.error("## Unable to sent trial period over Email to user: {}", user.getEmail());
                    }
                    LOGGER.info("Email sent to user: {} successfully", to);
                }
            }
        }
    }

    public void sendMail(EmailBuilder emailBuilder) throws Exception {
        String from = emailBuilder.getFrom();
        String subject = emailBuilder.getSubject();
        String content = emailBuilder.getContent();
        String[] attachments = emailBuilder.getAttachment();

        try {
            if (attachments == null || attachments.length == 0) {
                for (String to : emailBuilder.getTo()) {
                    EmailUtil.sendMail(from, to, subject, content);
                }
            }
            else {
                for (String to : emailBuilder.getTo()) {
                    EmailUtil.sendMailWithAttachment(from, to, subject, content, attachments);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error has occurred while sending Email with attachments ", e);
        }
    }
}
