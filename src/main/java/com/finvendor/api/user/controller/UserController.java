package com.finvendor.api.user.controller;

import com.finvendor.api.exception.ApiBadRequestException;
import com.finvendor.api.notification.EmailCondition;
import com.finvendor.api.user.dto.UserSubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.DateUtils;
import com.finvendor.model.FinVendorUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.finvendor.api.webutil.WebUtils.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/users/{userName}/resetSubscription")
    public ResponseEntity<ApiResponse<String, String>> resetSubscription(@PathVariable(value = "userName") String userName)
            throws Exception {
        userService.resetSubscription(userName);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, OK));
    }

    /**
     * Find user subscription details
     */
    @GetMapping(value = "/users/{userName}/subscriptions")
    public ResponseEntity<ApiResponse<String, UserSubscriptionDto>> findUserSubscriptionDetails(@PathVariable String userName)
            throws Exception {
        if (!userService.isValidUser(userName)) {
            LOGGER.error("## User: {} does not exist", userName);
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        else {
            FinVendorUser existingUser = userService.getUserDetailsByUsername(userName);
            boolean userInTrialPeriod = userService.isUserInTrialPeriod(existingUser);
            String subscriptionStartTime = existingUser.getSubscriptionStartTime();
            String subscriptionEndTime = existingUser.getSubscriptionEndTime();
            String subscriptionStartTimeInMs;
            String subscriptionEndTimeInMs;

            if (subscriptionStartTime != null && !"".equals(subscriptionStartTime)) {
                subscriptionStartTimeInMs = String.valueOf(DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionStartTime));
            }
            else {
                subscriptionStartTimeInMs = null;
            }
            if (subscriptionEndTime != null && !"".equals(subscriptionEndTime)) {
                subscriptionEndTimeInMs = String.valueOf(DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionEndTime));
            }
            else {
                subscriptionEndTimeInMs = null;
            }
            String trialPeriodStartTime = existingUser.getTrialPeriodStartTime();
            String trialPeriodEndTime = existingUser.getTrialPeriodEndTime();

            String trialPeriodStartTimeInMs;
            String trialPeriodEndTimeInMs;
            if (trialPeriodStartTime != null && !"".equals(trialPeriodStartTime)) {
                trialPeriodStartTimeInMs = String.valueOf(DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodStartTime));
            }
            else {
                trialPeriodStartTimeInMs = null;
            }
            if (trialPeriodEndTime != null && !"".equals(trialPeriodEndTime)) {
                trialPeriodEndTimeInMs = String.valueOf(DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodEndTime));
            }
            else {
                trialPeriodEndTimeInMs = null;
            }
            UserSubscriptionDto dto = new UserSubscriptionDto(existingUser.getUserName(),
                    existingUser.getSubscriptionType(), existingUser.getSubscriptionState(), subscriptionStartTimeInMs,
                    subscriptionEndTimeInMs, subscriptionStartTime, subscriptionEndTime, trialPeriodStartTimeInMs, trialPeriodEndTimeInMs,
                    trialPeriodStartTime, trialPeriodEndTime, userInTrialPeriod);
            return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, dto, OK));
        }
    }

    /**
     * Send mail to users when subscription expired or about to expired or renewal of subscription
     */
    @PostMapping(value = "/users/sendMail")
    public ResponseEntity<ApiResponse<String, String>> sendMailToUser(@RequestParam(value = "type") EmailCondition emailCondition) {
        LOGGER.info("## sendMailToUser - START emailCondition: {}", emailCondition.name());
        ApiResponse<String, String> apiResponse;
        try {
            switch (emailCondition) {
            case TRIAL_PERIOD_OVER:
                userService.sendEMail_OnTrialPeriodOver();
                break;
            case REMINDER_SUBSCRIPTION_RENEWAL:
                userService.sendMail_OnSubscriptionNearToExpire();
                break;
            case SUBSCRIPTION_EXPIRED:
                userService.sendMail_OnSubscriptionExpired();
                break;
            }
            apiResponse = buildResponse(ApiMessageEnum.SUCCESS, null, OK);
        } catch (Exception e) {
            apiResponse = buildResponse(ApiMessageEnum.INTERNAL_SERVER_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return buildResponseEntity(apiResponse);
    }
}
