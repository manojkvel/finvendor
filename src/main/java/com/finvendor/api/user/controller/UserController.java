package com.finvendor.api.user.controller;

import com.finvendor.api.user.service.UserService;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.model.FinVendorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Update subscription type to FREE if subscription expires
     */
    @PutMapping(value = "v1/user/{userName}/subscription-types")
    public ResponseEntity<ApiResponse> updateUserSubscriptions(@PathVariable String userName) throws Exception {
        if (userService.isValidUser(userName)) {
            List<FinVendorUser> existingUsers = userService.getUserDetails();
            for (FinVendorUser user : existingUsers) {

                String subscriptionStartTimeInMillis = user.getSubscriptionStartTimeInMillis();
                String subscriptionEndTimeInMillis = user.getSubscriptionEndTimeInMillis();
                user.getSubscriptionType();
                /*
                if subs expire then
                 update subs type to free
                 and make start and end time to NA
                 */
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
