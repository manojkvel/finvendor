package com.finvendor.api.subscription.service;

import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.subscription.dao.SubscriptionDao;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.subscription.UserPayment;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            String refId = dao.savePayment(userName, dto);
            FinVendorUser userDetailsByUsername = userService.getUserDetailsByUsername(userName);
            if (userDetailsByUsername != null) {
                String email = userDetailsByUsername.getEmail();
                String from = EmailUtil.SALES_EMAIL;
                String[] to = new String[]{email};
                String subject = prepareSubject(refId);
                String content = prepareContent(refId);
                //notificationService.sendMail(new EmailBuilder.Builder(to, subject, content).from(from).build());
            } else {
                LOGGER.warn("Unable to send email to user: {} as user details not found in system", userName);
            }
            return refId;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private String prepareContent(String refId) {
        return "dd";
    }

    private String prepareSubject(String refId) {
        return "";
    }

    public boolean updatePayment(SubscriptionDto dto, String id) throws Exception {
        try {
            return dao.updatePayment(dto, id);
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
