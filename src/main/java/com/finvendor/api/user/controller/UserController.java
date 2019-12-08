package com.finvendor.api.user.controller;

import com.finvendor.api.common.dto.UserDto;
import com.finvendor.api.login.service.RegistrationService;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.Pair;
import com.finvendor.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.buildResponseEntity;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
    private UserService userService;
    private RegistrationService registrationService;
    private SubscriptionService subscriptionService;

    @Autowired
    public UserController(UserService userService, RegistrationService registrationService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.subscriptionService = subscriptionService;
    }

    @DeleteMapping(value = "/users/{userName}/resetSubscription")
    public ResponseEntity<ApiResponse<String, String>> resetSubscription(@PathVariable(value = "userName") String userName)
            throws Exception {
        userService.resetSubscription(userName);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, OK));
    }

    @DeleteMapping(value = "/users/{userName}/delete")
    public ResponseEntity<ApiResponse<String, String>> deleteUser(@PathVariable(value = "userName") String userName)
            throws Exception {
        userService.deleteUser(userName);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, OK));
    }

    @PostMapping(value = "/users/{userName}/verify")
    public ResponseEntity<ApiResponse<String, String>> verifyUser(@PathVariable(value = "userName") String userName)
            throws Exception {
        userService.verifyUser(userName);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, OK));
    }

    @PostMapping(value = "/users/reset")
    public ResponseEntity<ApiResponse<String, String>> addFreeSubscriptionToExistingUsers(@RequestBody UserDto userDto)
            throws Exception {
        String userName = userDto.getUserName();
        userService.deleteUser(userName);
        ApiResponse<String, String> apiResponse;
        Pair<Boolean, String> registrationPair = registrationService
                .registerUser(new FinVendorUser(), new Roles(), new UserRole(), new Vendor(), new Consumer(), userName,
                        userDto.getPassword(), userDto.getUserEmail(), userDto.getCompany(), userDto.getCompanyType(), "");
        Boolean registrationStatus = registrationPair.getElement1();
        if (registrationStatus) {
            userService.verifyUser(userName);
            apiResponse = buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK);
        }
        else {
            LOGGER.error("## Unable to perform user registration for user: {}", userName);
            apiResponse = buildResponse(ApiMessageEnum.INTERNAL_SERVER_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return buildResponseEntity(apiResponse);
    }
}
