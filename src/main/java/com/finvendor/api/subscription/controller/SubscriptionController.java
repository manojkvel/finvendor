package com.finvendor.api.subscription.controller;

import com.finvendor.api.subscription.dto.*;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.getResponseEntity;
import static com.finvendor.common.enums.ApiMessageEnum.*;

/**
 * @author ayush, Jul 2019
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class SubscriptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class.getName());

    private final UserService userService;

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    /**
     * Save user subscription details
     */
    @PostMapping(value = "/users/{userName}/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSubscription(
            @PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName,
            @Valid @RequestBody SubscriptionDto subscriptionDto) throws Exception {
        ApiResponse<String, String> apiResponse;
        if (userService.isValidUser(userName)) {
            String subscriptionRefId = subscriptionService.savePayment(userName, subscriptionDto);
            if (subscriptionRefId != null) {
                LOGGER.info("User Subscription created successfully, subscriptionId: {}", subscriptionRefId);
                apiResponse = buildResponse(CREATE_SUBSCRIPTION, null, HttpStatus.CREATED);
            }
            else {
                LOGGER.info("User Subscription already exist, userName: {}", userName);
                apiResponse = buildResponse(DUP_SUBSCRIPTION, null, HttpStatus.CONFLICT);
            }
        }
        else {
            apiResponse = buildResponse(FAILED_TO_SAVE_SUBSCRIPTION, null, HttpStatus.BAD_REQUEST);
        }
        return getResponseEntity(apiResponse);
    }

    /**
     * This api is used to update payment verification from admin dashboard
     */
    @PutMapping(value = "/users/{userName}/subscriptions/{subscriptionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSubscription(
            @PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName,
            @PathVariable String subscriptionId, @RequestBody SubscriptionStateDto subscriptionStateDto) throws Exception {
        ApiResponse<String, String> apiResponse;
        SubscriptionDto dto = new SubscriptionDto();
        String subscriptionState = subscriptionStateDto.getSubscriptionState();
        boolean paymentVerified;
        if ("ACTIVE".equalsIgnoreCase(subscriptionState)) {
            paymentVerified = true;
        }
        else if ("TERMINATE".equalsIgnoreCase(subscriptionState)) {
            paymentVerified = false;
        }
        else {
            apiResponse = buildResponse(VERIFICATION_TYPE, null, HttpStatus.INTERNAL_SERVER_ERROR);
            return getResponseEntity(apiResponse);
        }
        dto.setPaymentVerified(paymentVerified);
        ApiMessageEnum apiMessageEnum;
        if (!userService.isValidUser(userName)) {
            apiResponse = buildResponse(INVALID_USER_NAME, null, HttpStatus.NOT_FOUND);
        }
        else {
            apiMessageEnum = subscriptionService.updatePayment(userName, dto, subscriptionId);
            apiResponse = buildResponse(apiMessageEnum, null, HttpStatus.OK);
        }
        return getResponseEntity(apiResponse);
    }

    /**
     * This api is used by pricing
     *
     * @param userName logged in username
     * @return subscription type for given user
     */
    @GetMapping(value = "/users/{userName}/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserSubscriptions(@PathVariable(value = "userName", required = false) String userName) throws Exception {
        ApiResponse<String, UserSubscriptionDto> apiResponse;
        UserSubscriptionDto userSubscriptionDto;
        if ((userSubscriptionDto = subscriptionService.findUserSubscriptions(userName)) == null) {
            apiResponse = buildResponse(FAILED_TO_FIND_USER_SUBSCRIPTIONS, null, HttpStatus.NOT_FOUND);
        }
        else {
            apiResponse = buildResponse(GET_SUBSCRIPTION_TYPE, userSubscriptionDto, HttpStatus.OK);
        }
        return getResponseEntity(apiResponse);
    }

    @PostMapping(value = "/subscriptions/recordstat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSubscriptionsRecordStats(@RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestBody(required = false) SubscriptionFilter filter)
            throws Exception {
        String subscriptionsRecordStat = subscriptionService.getSubscriptionsRecordStat(perPageMaxRecords, filter);
        return new ResponseEntity<>(subscriptionsRecordStat, HttpStatus.OK);
    }

    /**
     * This api is used by admin dashboard to show all payment details so that admin guy will verify
     * user payment
     */
    @PostMapping(value = "/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSubscriptions(@RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords, @RequestParam("sortBy") String sortBy,
            @RequestParam("orderBy") String orderBy, @RequestBody(required = false) SubscriptionFilter filter) throws Exception {
        ApiResponse<String, List<UserPaymentDto>> apiResponse;
        List<UserPaymentDto> userPayments;
        if (!("userName".equalsIgnoreCase(sortBy) || "subscriptionState".equalsIgnoreCase(sortBy))) {
            throw new IllegalArgumentException("Validation failed");
        }

        if (!("asc".equalsIgnoreCase(orderBy) || "desc".equalsIgnoreCase(orderBy))) {
            throw new IllegalArgumentException("Validation failed");
        }

        if ((userPayments = subscriptionService.findSubscriptions(pageNumber, perPageMaxRecords, sortBy, orderBy, filter)) == null) {
            apiResponse = buildResponse(FAILED_TO_FIND_SUBSCRIPTION, null, HttpStatus.NO_CONTENT);
        }
        else {
            apiResponse = buildResponse(GET_SUBSCRIPTION, userPayments, HttpStatus.OK);
        }
        return getResponseEntity(apiResponse);
    }

    /**
     * Watch subscription before expire date
     */
    public ResponseEntity<?> watchSubscriptionStateBeforeExpire() {
        /*
        age=find how old subscription is
        and if age=15 or 20 or 25 or 28 or 29 or 30 then send mail
         */
        return null;
    }

    /**
     * Update subscription state to SUSPEND when subscription is over
     */
    public ResponseEntity<?> updateSubscriptionStateOnExpire() {
        /*
        if current date is subscription end date then look for time it time is 12.05 AM then SUSPEND it
         */
        return null;
    }
}
