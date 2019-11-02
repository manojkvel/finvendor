package com.finvendor.api.subscription.controller;

import com.finvendor.api.exception.ApiBadRequestException;
import com.finvendor.api.exception.ApiConflictException;
import com.finvendor.api.exception.ApiResourceNotFoundException;
import com.finvendor.api.subscription.dto.*;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.Pair;
import com.finvendor.model.FinVendorUser;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static com.finvendor.api.webutil.WebUtils.*;
import static com.finvendor.common.enums.ApiMessageEnum.SUCCESS;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author ayush, Jul 2019
 */

@RestController
@RequestMapping(value = "/api")
@Validated
public class SubscriptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class.getName());
    private static final String USER_LEN = "Length of user name must be between 1 to 45 characters";

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;
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
    public ResponseEntity<ApiResponse<String, String>> saveSubscription(
            @PathVariable @Size(min = 1, max = 45, message = USER_LEN) String userName,
            @Valid @RequestBody SubscriptionDto subscriptionDto,
            @RequestParam(value = "type", required = false) String type) throws Exception {
        LOGGER.info("## saveSubscription - START userName: {}, type: {}", userName, type);
        ApiResponse<String, String> apiResponse;
        if (userService.isValidUser(userName)) {
            FinVendorUser existingUser = userService.getUserDetailsByUsername(userName);
            if ("trial".equals(type)) {
                if (existingUser.getTrialPeriodStartTime() == null && ("FREE".equals(existingUser.getSubscriptionType()) || "SMART"
                        .equals(existingUser.getSubscriptionType()))) {
                    String trialPeriod = finvendorProperties.getProperty("trial_period_in_days");
                    LOGGER.info("## Trial Period In Days from Property file: {}", trialPeriod);
                    Date userTrailPeriodStartDate = DateUtils.getCurrentDateInDate();
                    Date userTrailPeriodEndDate = DateUtils.addDaysInCurrentDate(userTrailPeriodStartDate, Integer.parseInt(trialPeriod));
                    LOGGER.info("## userTrailPeriodStartDate: {}", userTrailPeriodStartDate);
                    LOGGER.info("## userTrailPeriodEndDate: {}", userTrailPeriodEndDate);
                    existingUser.setTrialPeriodStartTime(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(userTrailPeriodStartDate.getTime()));
                    existingUser.setTrialPeriodEndTime(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(userTrailPeriodEndDate.getTime()));
                    existingUser.setSubscriptionType(subscriptionDto.getSubscriptionType());
                    existingUser.setSubscriptionState("TRIAL");
                    userService.updateUserInfo(existingUser);
                    apiResponse = buildResponse(ApiMessageEnum.CREATED, null, CREATED);
                }
                else {
                    LOGGER.error("## User: {} already in trial period", userName);
                    throw new ApiConflictException(WebUtils.CONFLICT);
                }
            }
            else {
                LOGGER.info("## User: {} not in trial period, saving subscription details", userName);
                existingUser.setTrialPeriodStartTime(null);
                existingUser.setTrialPeriodEndTime(null);
                userService.updateUserInfo(existingUser);
                String subscriptionId = String.valueOf(UUID.randomUUID());
                boolean saveStatus = subscriptionService.saveUserSubscription(userName, subscriptionId, subscriptionDto);
                if (saveStatus) {
                    LOGGER.info("## Subscription details for user: {} saves successfully, now proceeding to user user details like "
                            + "subscription state, start and end time in users table", userName);
                    FinVendorUser userDetails =subscriptionService.updateUserWithSubscription(userName, subscriptionDto.getSubscriptionType());
                    LOGGER.info("## User's subscription details saves successfully");
                    subscriptionService.sentSubscriptionSubmissionEmail(userName, userDetails);
                    LOGGER.info("## Email sent to User successfully");

                    LOGGER.info("## User Subscription created successfully, subscriptionId: {}", subscriptionId);
                    apiResponse = buildResponse(ApiMessageEnum.CREATED, null, HttpStatus.CREATED);
                }
                else {
                    LOGGER.error("## User Subscription already exist, userName: {}", userName);
                    throw new ApiConflictException(WebUtils.CONFLICT);
                }
            }
        }
        else {
            LOGGER.error("## User: {} does not exist, pls provide valid user name in endpoint", userName);
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        return buildResponseEntity(apiResponse);
    }

    /**
     * This api is used to update payment verification from admin dashboard
     */
    @PutMapping(value = "/users/{userName}/subscriptions")
    public ResponseEntity<?> updateSubscriptionOnPaymentReceived(
            @PathVariable @Size(min = 1, max = 45, message = "Length of user name must be between 1 to 45 characters") String userName,
            @RequestBody SubscriptionStateDto subscriptionStateDto) throws Exception {
        LOGGER.info("## updateSubscription - START, userName: {}, subscriptionStateDto: {}", userName, subscriptionStateDto);
        ApiResponse<String, String> apiResponse;
        String subscriptionState = subscriptionStateDto.getSubscriptionState();
        boolean paymentVerified;
        LOGGER.info("## subscriptionState: {}", subscriptionState);
        if ("ACTIVE".equalsIgnoreCase(subscriptionState)) {
            paymentVerified = true;
        }
        else if ("TERMINATE".equalsIgnoreCase(subscriptionState)) {
            paymentVerified = false;
        }
        else {
            LOGGER.error("## Subscription state should be either ACTIVE or TERMINATE");
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        SubscriptionDto dto = new SubscriptionDto();
        dto.setPaymentVerified(paymentVerified);
        ApiMessageEnum apiMessageEnum;
        if (!userService.isValidUser(userName)) {
            LOGGER.error("## UserName: {} does not exist", userName);
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        else {
            LOGGER.info("### Before update subscription state update");
            List<SubscriptionDetails> dataList = subscriptionStateDto.getData();
            apiMessageEnum = subscriptionService.updateUserSubscriptionPaymentDetails(dto, dataList);
            apiResponse = buildResponse(apiMessageEnum, null, OK);
        }
        return buildResponseEntity(apiResponse);
    }

    /**
     * Find all Subscription state used by Admin
     */
    @PostMapping(value = "/subscriptions/recordstat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSubscriptionsRecordStats(@RequestParam(value = "state") String state,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords, @RequestBody(required = false) SubscriptionFilter filter)
            throws Exception {
        if (!("all".equals(state) || ("ACTIVE".equals(state) || "PENDING".equals(state) || "TERMINATE".equals(state)))) {
            throw new ApiBadRequestException("Validation failed");
        }
        else {
            Pair<String, Integer> pair = subscriptionService.getSubscriptionsRecordStat(state, perPageMaxRecords, filter);
            if (pair.getElement2() == 0) {
                LOGGER.error("## Subscription does not exist");
                throw new ApiResourceNotFoundException(RESOURCE_NOT_FOUND);
            }
            else {
                return buildResponseEntity(buildResponse(SUCCESS, pair.getElement1(), OK));
            }
        }
    }

    /**
     * This api is used by admin dashboard to show all payment details so that admin guy will verify
     * user payment
     */
    @PostMapping(value = "/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSubscriptions(@RequestParam(value = "state") String state,
            @RequestParam(value = "pageNumber") String pageNumber, @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam("sortBy") String sortBy, @RequestParam("orderBy") String orderBy,
            @RequestBody(required = false) SubscriptionFilter filter) throws Exception {
        ApiResponse<String, List<UserPaymentDto>> apiResponse;
        boolean failedCondition_1 = !("userName".equalsIgnoreCase(sortBy) || "subscriptionState".equalsIgnoreCase(sortBy));
        boolean failedCondition_2 = !("asc".equalsIgnoreCase(orderBy) || "desc".equalsIgnoreCase(orderBy));
        boolean failedCondition_3 = !("all".equals(state) || ("ACTIVE".equals(state) || "PENDING".equals(state) || "TERMINATE"
                .equals(state)));
        if (failedCondition_1 || failedCondition_2 || failedCondition_3) {
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        else {
            List<UserPaymentDto> subscriptions = subscriptionService
                    .findSubscriptions(state, pageNumber, perPageMaxRecords, sortBy, orderBy, filter);
            if (subscriptions == null || subscriptions.size() == 0) {
                throw new ApiResourceNotFoundException(RESOURCE_NOT_FOUND);
            }
            else {
                apiResponse = buildResponse(SUCCESS, subscriptions, OK);
            }
        }
        return buildResponseEntity(apiResponse);
    }

    /**
     * Download subscriptions into CSV
     */
    @GetMapping(value = "/subscriptions/download")
    public ResponseEntity<ApiResponse<String, StatusPojo>> downloadSubscriptions(HttpServletResponse response) throws Exception {
        final Pair<Long, InputStream> download = subscriptionService.downloadSubscriptions();
        if (download == null || download.getElement2() == null) {
            throw new ApiResourceNotFoundException(RESOURCE_NOT_FOUND);
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + "subscriptions.csv");
        response.setHeader("Content-Length", String.valueOf(download.getElement1()));
        FileCopyUtils.copy(download.getElement2(), response.getOutputStream());
        final StatusPojo statusPojo = new StatusPojo("true", "Subscriptions downloaded successfully.");
        return buildResponseEntity(buildResponse(SUCCESS, statusPojo, OK));
    }
}
