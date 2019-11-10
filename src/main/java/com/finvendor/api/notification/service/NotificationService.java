package com.finvendor.api.notification.service;

import com.finvendor.api.configuration.service.SysConfig;
import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.finvendor.util.EmailUtil.SALES_EMAIL;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class.getName());
    private static final String FAILED_TO_SENT_EMAIL_SUBJECT = "Failed to sent email to user";
    private static final String FAILED_TO_SENT_EMAIL_CONTENT = "Hello Admin, <br> System failed to send email to user: ";


    public boolean sendMail(EmailBuilder emailBuilder) {
        LOGGER.info("## NotificationService - sendMail - START emailBuilder: {}", emailBuilder);
        boolean emailSentStatus = false;
        if (Objects.requireNonNull(SysConfig.config()).isEmailEnabled()) {
            String from = emailBuilder.getFrom();
            String subject = emailBuilder.getSubject();
            String content = emailBuilder.getContent();
            String[] attachments = emailBuilder.getAttachment();
            if (attachments == null || attachments.length == 0) {
                for (String to : emailBuilder.getTo()) {
                    try {
                        EmailUtil.sendMail(from, to, subject, content);
                        emailSentStatus = true;
                    } catch (Exception e) {
                        sendMailToSalesWhenEmailFailed(to);
                        emailSentStatus = false;
                    }
                }
            }
            else {
                for (String to : emailBuilder.getTo()) {
                    try {
                        EmailUtil.sendMailWithAttachment(from, to, subject, content, attachments);
                        emailSentStatus = true;
                    } catch (Exception e) {
                        sendMailToSalesWhenEmailFailed(to);
                        emailSentStatus = false;
                    }
                }
            }
        } else{
            LOGGER.info("## Unable to sent Email due to flag: false");
        }
        return emailSentStatus;
    }

    private void sendMailToSalesWhenEmailFailed(String to) {
        try {
            EmailUtil.sendMail(SALES_EMAIL, SALES_EMAIL, FAILED_TO_SENT_EMAIL_SUBJECT, FAILED_TO_SENT_EMAIL_CONTENT + to);
        } catch (Exception e) {
            LOGGER.error("## Finvendor EMAIL server is down, please contact admin");
        }
    }
}
