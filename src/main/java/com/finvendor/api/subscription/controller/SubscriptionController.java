package com.finvendor.api.subscription.controller;

import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.model.subscription.UserPayment;
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
import static com.finvendor.common.enums.MessageEnum.*;

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

    @PostMapping(value = "/users/{userName}/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSubscription(@PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName,
                                              @Valid @RequestBody SubscriptionDto subscriptionDto) throws Exception {
        ApiResponse<String, String> apiResponse;
        if (userService.isValidUser(userName)) {
            String subscriptionRefId = subscriptionService.savePayment(userName, subscriptionDto);
            LOGGER.info("User subscription saved in system, subscriptionRefId: {}", subscriptionRefId);
            apiResponse = buildResponse(CREATE_SUBSCRIPTION, null, HttpStatus.CREATED);
        } else {
            apiResponse = buildResponse(FAILED_TO_SAVE_SUBSCRIPTION, null, HttpStatus.BAD_REQUEST);
        }
        return getResponseEntity(apiResponse);
    }




    @PutMapping(value = "/users/{userName}/subscriptions/{subscriptionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSubscription(@PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName,
                                                @Valid @RequestBody SubscriptionDto subscriptionDto, @PathVariable String subscriptionId) throws Exception {
        ApiResponse<String, String> apiResponse;
        if (!userService.isValidUser(userName) || !(subscriptionService.updatePayment(subscriptionDto, subscriptionId))) {
            apiResponse = buildResponse(FAILED_TO_UPDATE_SUBSCRIPTION, null, HttpStatus.NOT_FOUND);
        } else {
            apiResponse = buildResponse(UPDATE_SUBSCRIPTION, null, HttpStatus.OK);
        }
        return getResponseEntity(apiResponse);
    }

    @GetMapping(value = "/users/{userName}/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSubscriptions(@PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName) throws Exception {
        ApiResponse<String, List<UserPayment>> apiResponse;
        List<UserPayment> userPayments;
        if (!userService.isValidUser(userName) || (userPayments = subscriptionService.findSubscriptions()) == null) {
            apiResponse = buildResponse(FAILED_TO_FIND_SUBSCRIPTION, null, HttpStatus.NO_CONTENT);
        } else {
            apiResponse = buildResponse(GET_SUBSCRIPTION, userPayments, HttpStatus.OK);
        }
        return getResponseEntity(apiResponse);
    }
}
