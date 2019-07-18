package com.finvendor.api.notification.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.util.EmailUtil;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

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
            } else {
                for (String to : emailBuilder.getTo()) {
                    EmailUtil.sendMailWithAttachment(from, to, subject, content, attachments);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error has occurred while sending Email with attachments ", e);
        }
    }
}
