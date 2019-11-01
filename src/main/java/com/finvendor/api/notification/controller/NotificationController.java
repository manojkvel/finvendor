package com.finvendor.api.notification.controller;

import com.finvendor.api.example.dto.EmailDto;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Properties;

@RestController
@RequestMapping(value = "/api")
public class NotificationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties fvProperties;

    @PostMapping(value = "/emails")
    public ResponseEntity<?> sendSampleEmail(@RequestBody EmailDto emailDto) throws Exception {
        boolean sendMail = Boolean.parseBoolean(fvProperties.getProperty("email"));
        try {
            for (String to : emailDto.getTo()) {
                if (sendMail) {
                    EmailUtil.sendMail(to, emailDto.getSubject(), emailDto.getMessage());
                    LOGGER.info("Email sent to user: {} successfully", to);
                }
                else {
                    LOGGER.info("Unable to send mail as mail property is false");
                    return new ResponseEntity<>("Unable to send mail as mail property is false", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Email sent!!", HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
