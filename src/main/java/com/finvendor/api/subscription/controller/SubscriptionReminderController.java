package com.finvendor.api.subscription.controller;

import com.finvendor.api.subscription.service.SubscriptionReminderService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.buildResponseEntity;

/**
 * Subscription reminder controller
 */
@RestController
@RequestMapping(value = "/api")
public class SubscriptionReminderController {

    private final SubscriptionReminderService service;

    @Autowired
    public SubscriptionReminderController(SubscriptionReminderService service) {
        this.service = service;
    }

    @GetMapping(value = "/subscriptionReminders")
    public ResponseEntity<ApiResponse<String, String>> reminderForSubscriptionRenewal() {
        service.sendReminderForSubscriptionReNew();
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK));
    }
}
