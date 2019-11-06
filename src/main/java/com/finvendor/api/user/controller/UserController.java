package com.finvendor.api.user.controller;

import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SubscriptionService subscriptionService;

    @Autowired
    public UserController(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
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
}
