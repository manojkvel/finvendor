package com.finvendor.api.user.controller;

import com.finvendor.api.subscription.dao.SubscriptionDao;

import com.finvendor.api.user.dto.UserSubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.DateUtils;
import com.finvendor.model.FinVendorUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.getResponseEntity;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
    private UserService userService;
    private SubscriptionDao subscriptionDao;

    @Autowired
    public UserController(UserService userService, SubscriptionDao subscriptionDao) {
        this.userService = userService;
        this.subscriptionDao = subscriptionDao;
    }

    @GetMapping(value = "/v1/users/{userName}/subscription-details")
    public ResponseEntity<ApiResponse<String, UserSubscriptionDto>> findUserSubscriptionDetails(@PathVariable String userName) {
        ApiResponse<String, UserSubscriptionDto> apiResponse = null;
        try {
            if (!userService.isValidUser(userName)) {
                apiResponse = buildResponse(ApiMessageEnum.INVALID_USER_NAME, null, HttpStatus.NOT_FOUND);
            } else {
                FinVendorUser existingUser = userService.getUserDetailsByUsername(userName);
                UserSubscriptionDto dto = new UserSubscriptionDto(existingUser.getUserName(),
                        existingUser.getSubscriptionType(), existingUser.getSubscriptionStatus(), existingUser.getSubscriptionStartTimeInMillis(), existingUser.getSubscriptionEndTimeInMillis());
                apiResponse = buildResponse(ApiMessageEnum.USER_PROFILE_SUBSCRIPTION, dto, HttpStatus.OK);
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return getResponseEntity(apiResponse);
    }


    /**
     * Update subscription type to FREE if subscription expires
     */
    @PutMapping(value = "v1/user/{userName}/subscription-types")
    public ResponseEntity<ApiResponse> updateUserSubscriptions(@PathVariable String userName) throws Exception {
        if (userService.isValidUser(userName)) {
            List<FinVendorUser> existingUsers = userService.getUserDetails();
            for (FinVendorUser user : existingUsers) {
                long subscriptionEndTimeInMillis = Long.parseLong(user.getSubscriptionEndTimeInMillis().trim());
                long currentTimeInMillis = DateUtils.getSubscriptionStartAndEndDateInMillis(30).getElement1();
                //find last 5th day from subscription end date
                //send reminder mail to renew subscription

                LOGGER.info("");
                //on subscription expires
                if (currentTimeInMillis > subscriptionEndTimeInMillis) {
                    user.setSubscriptionType("FREE");
                    user.setSubscriptionStartTimeInMillis("N/A");
                    user.setSubscriptionEndTimeInMillis("N/A");
                    userService.updateUserInfo(user);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse("06/24/2017");
        Date secondDate = sdf.parse("06/30/2017");

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diff);
    }
}
