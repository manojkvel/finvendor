package com.finvendor.api.subscription.controller;

import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.response.BaseResponseDto;
import com.finvendor.model.subscription.UserPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Properties;

import static com.finvendor.common.enums.MessageEnum.*;

/**
 * @author ayush, Jul 2019
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class SubscriptionController {

    private final UserService userService;

    private final SubscriptionService subscriptionService;

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    public SubscriptionController(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping(value = "/users/{userName}/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSubscription(@PathVariable @Size(min = 1, max = 4, message = "Length of user name must be between 1 to 45 characters") String userName,
                                              @Valid @RequestBody SubscriptionDto subscriptionDto) throws Exception {
        subscriptionService.savePayment(userName, subscriptionDto);
        return new ResponseEntity<>(new BaseResponseDto<String, String>(CREATE_SUBSCRIPTION.getCode(), CREATE_SUBSCRIPTION.getMsg(), null), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{userName}/subscriptions/{subscriptionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSubscription(@PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName,
                                                @Valid @RequestBody SubscriptionDto subscriptionDto, @PathVariable String subscriptionId) throws Exception {
        if (userService.isValidUser(userName)) {
            subscriptionService.updatePayment(subscriptionDto, subscriptionId);
            return new ResponseEntity<BaseResponseDto>(new BaseResponseDto<>(UPDATE_SUBSCRIPTION.getCode(), UPDATE_SUBSCRIPTION.getMsg(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BaseResponseDto<String, String>(FAILED_TO_UPDATE_SUBSCRIPTION.getCode(), FAILED_TO_UPDATE_SUBSCRIPTION.getMsg(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/users/{userName}/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDto> findAllSubscriptions(@PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName) throws Exception {
        if (userService.isValidUser(userName)) {
            List<UserPayment> userPayments = subscriptionService.findSubscriptions();
            return new ResponseEntity<BaseResponseDto>(new BaseResponseDto<>(GET_SUBSCRIPTION.getCode(), GET_SUBSCRIPTION.getMsg(), userPayments), HttpStatus.OK);
        } else {
            return new ResponseEntity<BaseResponseDto>(new BaseResponseDto<>(FAILED_TO_FIND_SUBSCRIPTION.getCode(), FAILED_TO_FIND_SUBSCRIPTION.getMsg(), null), HttpStatus.NOT_FOUND);
        }
    }
}
