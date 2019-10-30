package com.finvendor.api.notification.controller;

import com.finvendor.api.example.dto.EmailDto;
import com.finvendor.api.notification.EmailCondition;
import com.finvendor.api.notification.service.NotificationService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.buildResponseEntity;

@RestController
@RequestMapping(value = "/api")
public class NotificationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties fvProperties;

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/users/sendMail")
    public ResponseEntity<ApiResponse<String, String>> sendMailToUser(@RequestParam(value = "type") EmailCondition emailCondition) {
        LOGGER.info("## sendMailToUser - START emailCondition: {}", emailCondition.name());
        if (emailCondition.name().equals(EmailCondition.TRIAL_PERIOD_OVER.name())) {
            notificationService.sendEMailToAllUserWhoseTrialPeriodOver();
        }
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK));
    }

    @PostMapping(value = "/emails")
    public ResponseEntity<?> sendSampleEmail(@RequestBody EmailDto emailDto) throws Exception {
        boolean sendMail = Boolean.valueOf(fvProperties.getProperty("email"));
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
