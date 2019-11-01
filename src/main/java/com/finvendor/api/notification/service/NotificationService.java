package com.finvendor.api.notification.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class.getName());
    private static final String FAILED_TO_SENT_EMAIL_SUBJECT = "Failed to sent email to user";
    private static final String FAILED_TO_SENT_EMAIL_CONTENT = "Hello Admin, <br> System failed to send email to user: ";
    private static final String FV_SALES_EMAIL = "sales@finvendor.com";

    public void sendMail(EmailBuilder emailBuilder) throws Exception {
        LOGGER.info("NotificationService - sendMail - START emailBuilder: {}", emailBuilder);
        String from = emailBuilder.getFrom();
        String subject = emailBuilder.getSubject();
        String content = emailBuilder.getContent();
        String[] attachments = emailBuilder.getAttachment();
        if (attachments == null || attachments.length == 0) {
            for (String to : emailBuilder.getTo()) {
                try {
                    EmailUtil.sendMail(from, to, subject, content);
                } catch (Exception e) {
                    sendMailToSalesWhenEmailFailed(to);
                }
            }
        }
        else {
            for (String to : emailBuilder.getTo()) {
                try {
                    EmailUtil.sendMailWithAttachment(from, to, subject, content, attachments);
                } catch (Exception e) {
                    sendMailToSalesWhenEmailFailed(to);
                }
            }

        }
    }

    private void sendMailToSalesWhenEmailFailed(String to) {
        try {
            EmailUtil.sendMail(FV_SALES_EMAIL, FV_SALES_EMAIL, FAILED_TO_SENT_EMAIL_SUBJECT, FAILED_TO_SENT_EMAIL_CONTENT + to);
        } catch (Exception e) {
            LOGGER.error("## Finvendor EMAIL server is down, please contact admin");
        }
    }
}
