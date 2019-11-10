package com.finvendor.api.contactUs.service;

import com.finvendor.api.contactUs.dao.ContactUsDao;
import com.finvendor.api.contactUs.dto.ContactUsDto;
import com.finvendor.api.exception.ApiResourceNotFoundException;
import com.finvendor.api.notification.dto.EmailBuilder;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.model.ContactUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.finvendor.util.EmailUtil.SALES_EMAIL;

@Service
@Transactional
public class ContactUsService {

    private final ContactUsDao contactUsDao;
    private final NotificationService notificationService;
    private static final String content = "Howdy <br>Thanks for contacting us.<br> We will get back to you shortly on provided email soon.<br><br>Yours truly,<br>Finvendor Sales Team<br>https://www.finvendor.com";
    private static final String subject = "Welcome to Finvendor";

    @Autowired
    public ContactUsService(ContactUsDao contactUsDao, NotificationService notificationService) {
        this.contactUsDao = contactUsDao;
        this.notificationService = notificationService;
    }

    public void saveContact(ContactUsDto contactUsDto) throws Exception {
        ContactUs contactUsEntity = new ContactUs();
        if (contactUsDto.getName() != null) {
            contactUsEntity.setName(contactUsDto.getName());
        }

        if (contactUsDto.getPhone() != null) {
            contactUsEntity.setPhone(contactUsDto.getPhone());
        }

        if (contactUsDto.getEmail() != null) {
            contactUsEntity.setEmail(contactUsDto.getEmail());
        }

        if (contactUsDto.getMessage() != null) {
            contactUsEntity.setMessage(contactUsDto.getMessage());
        }
        try {
            contactUsDao.saveOrUpdate(contactUsEntity);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<ContactUs> findAllContactUs() throws Exception {
        try {
            List<ContactUs> all = contactUsDao.findAll();
            if (all.size() == 0) {
                throw new ApiResourceNotFoundException("Resource not found");
            }
            return all;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public void sendEmail(ContactUsDto contactUsDto) {
        notificationService.sendMail(new EmailBuilder.Builder(new String[] { SALES_EMAIL, contactUsDto.getEmail() },
                subject, content).build());
    }
}
