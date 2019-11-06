package com.finvendor.api.subscription.controller;

import com.finvendor.api.common.dto.RecordStats;
import com.finvendor.api.exception.ApiBadRequestException;
import com.finvendor.api.exception.ApiConflictException;
import com.finvendor.api.exception.ApiResourceNotFoundException;
import com.finvendor.api.notification.EmailCondition;
import com.finvendor.api.subscription.dto.*;
import com.finvendor.api.subscription.enums.SubscriptionStatesEnum;
import com.finvendor.api.subscription.enums.SubscriptionTypeEnum;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.UserMsgEnums;
import com.finvendor.api.user.dto.UserSubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.Pair;
import com.finvendor.model.subscription.UsersSubscription;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.InputStream;
import java.util.List;

import static com.finvendor.api.webutil.WebUtils.*;
import static com.finvendor.common.enums.ApiMessageEnum.SUCCESS;
import static com.finvendor.common.util.DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author ayush, Jul 2019
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class SubscriptionController {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionController.class.getName());
    private static final String USER_LEN = "Length of user name must be between 1 to 45 characters";

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
    public ResponseEntity<ApiResponse<String, String>> save(@PathVariable @Size(min = 1, max = 45, message = USER_LEN) String userName,
            @Valid @RequestBody SubscriptionDto subscriptionDto,
            @RequestParam(value = "type", required = false) SubscriptionTypeEnum type) throws Exception {
        LOG.info("## saveSubscription - START userName: {}, type: {}", userName, type);
        ApiResponse<String, String> apiResponse;
        if (userService.isValidUser(userName)) {
            if (SubscriptionTypeEnum.TRIAL.equals(type)) {
                if (!subscriptionService.isTrialOpted(userName, subscriptionDto.getSubscriptionType())) {
                    apiResponse = subscriptionService.handleTrialPeriod(userName, subscriptionDto.getSubscriptionType());
                }
                else {
                    LOG.error("## Trial Period is already opted for Subscription Type: {}", subscriptionDto.getSubscriptionType());
                    throw new ApiConflictException(CONFLICT);
                }
            }
            else {
                LOG.info("## User: {} not in trial period, saving subscription details", userName);
                apiResponse = save(userName, subscriptionDto);
                LOG.info("## User Subscription created successfully");
            }
        }
        else {
            LOG.error("## User: {} does not exist, pls provide valid user name in endpoint", userName);
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        return buildResponseEntity(apiResponse);
    }

    private ApiResponse<String, String> save(String userName, SubscriptionDto subscriptionDto) throws Exception {
        boolean saveStatus = subscriptionService.saveUserSubscription(userName, subscriptionDto);
        if (saveStatus) {
            LOG.info("## UsersSubscription saved successfully");
            subscriptionService.sendEmail_OnSubscriptionSubmission(userName);
            return buildResponse(ApiMessageEnum.CREATED, null, HttpStatus.CREATED);
        }
        else {
            LOG.error("## User Subscription already exist, userName: {}", userName);
            throw new ApiConflictException(WebUtils.CONFLICT);
        }
    }

    /**
     * This api is used to update payment verification from admin dashboard
     */
    @PutMapping(value = "/users/{userName}/subscriptions")
    public ResponseEntity<ApiResponse<String, String>> update(@PathVariable @Size(min = 1, max = 45, message = USER_LEN) String userName,
            @RequestBody SubscriptionStateDto subscriptionStateDto) throws Exception {
        LOG.info("## updateSubscription - START, userName: {}, subscriptionStateDto: {}", userName, subscriptionStateDto);
        ApiResponse<String, String> apiResponse;
        String subscriptionState = subscriptionStateDto.getSubscriptionState();
        boolean paymentVerified;
        LOG.info("## subscriptionState: {}", subscriptionState);
        if (SubscriptionStatesEnum.ACTIVE.name().equalsIgnoreCase(subscriptionState)) {
            paymentVerified = true;
        }
        else if (SubscriptionStatesEnum.TERMINATE.name().equalsIgnoreCase(subscriptionState)) {
            paymentVerified = false;
        }
        else {
            LOG.error("## Subscription state should be either ACTIVE or TERMINATE");
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        SubscriptionDto dto = new SubscriptionDto();
        dto.setPaymentVerified(paymentVerified);
        ApiMessageEnum apiMessageEnum;
        if (!userService.isValidUser(userName)) {
            LOG.error("## UserName: {} does not exist", userName);
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        else {
            LOG.info("## Before update subscription state update");
            List<SubscriptionDetails> dataList = subscriptionStateDto.getData();
            apiMessageEnum = subscriptionService.updateUserSubscription(dto, dataList);
            apiResponse = buildResponse(apiMessageEnum, null, OK);
        }
        return buildResponseEntity(apiResponse);
    }

    /**
     * Find user subscription details
     */
    @GetMapping(value = "/users/{userName}/subscriptions")
    public ResponseEntity<ApiResponse<String, UserSubscriptionDto>> find(@PathVariable String userName) throws Exception {
        if (!userService.isValidUser(userName)) {
            LOG.error("## User: {} does not exist", userName);
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        else {
            UsersSubscription existingUsersSubscription = subscriptionService.getUsersSubscription(userName);
            String subscriptionType = existingUsersSubscription.getSubscriptionType();
            boolean isTopHistoricalSubscriptionIsInRenewalState = subscriptionService.isTopHistoricalSubscriptionIsInRenewalState();
            if (isTopHistoricalSubscriptionIsInRenewalState) {
                existingUsersSubscription = subscriptionService.get_second_Last_UsersSubscription_fromHistory();
                subscriptionType = existingUsersSubscription.getSubscriptionType();
            }
            String subscriptionStartTime = existingUsersSubscription.getSubscriptionStartTime();
            String subscriptionEndTime = existingUsersSubscription.getSubscriptionEndTime();

            String subscriptionStartTimeInMs = StringUtils.isNotEmpty(subscriptionStartTime) ?
                    String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionStartTime)) :
                    null;
            String subscriptionEndTimeInMs = StringUtils.isNotEmpty(subscriptionEndTime) ?
                    String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionEndTime)) :
                    null;

            String trialPeriodStartTime = existingUsersSubscription.getTrialPeriodStartTime();
            String trialPeriodEndTime = existingUsersSubscription.getTrialPeriodEndTime();

            String trialPeriodStartTimeInMs = StringUtils.isNotEmpty(trialPeriodStartTime) ?
                    String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodStartTime)) : null;
            String trialPeriodEndTimeInMs = StringUtils.isNotEmpty(trialPeriodEndTime) ?
                    String.valueOf(get_Timestamp_From_DD_MMM_YYYY_hh_Format(trialPeriodEndTime)) : null;
            boolean isUserInTrialPeriod = subscriptionService.isUserInTrialPeriod(existingUsersSubscription);
            String trialPeriodMsg = isUserInTrialPeriod ?
                    UserMsgEnums.USER_IN_TRIAL_PERIOD.getMsg(new Object[] { trialPeriodStartTime, trialPeriodEndTime }) :
                    null;
            String subscriptionId = existingUsersSubscription.getSubscriptionId();
            UserSubscriptionDto dto = new UserSubscriptionDto(subscriptionId, existingUsersSubscription.getUserName(),
                    subscriptionType, existingUsersSubscription.getSubscriptionState(),
                    subscriptionStartTimeInMs,
                    subscriptionEndTimeInMs, subscriptionStartTime, subscriptionEndTime, trialPeriodStartTimeInMs, trialPeriodEndTimeInMs,
                    trialPeriodStartTime, trialPeriodEndTime, isUserInTrialPeriod, trialPeriodMsg);
            return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, dto, OK));
        }
    }

    /**
     * Send mail to users when subscription expired or about to expired or renewal of subscription
     */
    @PostMapping(value = "/users/sendMail")
    public ResponseEntity<ApiResponse<String, String>> sendMailToUser(@RequestParam(value = "type") EmailCondition emailCondition) {
        LOG.info("## sendMailToUser - START emailCondition: {}", emailCondition.name());
        ApiResponse<String, String> apiResponse;
        try {
            switch (emailCondition) {
            case TRIAL_PERIOD_OVER:
                subscriptionService.sendEMail_OnTrialPeriodOver();
                break;
            case SUBSCRIPTION_RENEWAL:
                subscriptionService.sendMail_OnSubscriptionNearToExpire();
                break;
            case SUBSCRIPTION_EXPIRED:
                subscriptionService.sendMail_OnSubscriptionExpired();
                break;
            }
            apiResponse = buildResponse(ApiMessageEnum.SUCCESS, null, OK);
        } catch (Exception e) {
            apiResponse = buildResponse(ApiMessageEnum.INTERNAL_SERVER_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return buildResponseEntity(apiResponse);
    }

    @PostMapping(value = "/users/{userName}/expireForcely")
    public ResponseEntity<ApiResponse<String, String>> expire(@PathVariable String userName) throws Exception {
        subscriptionService.setFree(userName);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, OK));
    }

    @PostMapping(value = "/users/{userName}/subscriptions/{subscriptionId}/renew")
    public ResponseEntity<ApiResponse<String, String>> renew(@PathVariable String userName,
            @PathVariable(value = "subscriptionId") String subscriptionId) throws Exception {
        LOG.info("renew - START: {}", subscriptionId);
        subscriptionService.setFree(userName);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, OK));
    }

    /**
     * This api is used by admin dashboard to show all payment details so that admin guy will verify
     * user payment
     */
    @PostMapping(value = "/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String, SubscriptionsResponse>> findAll(@RequestParam(value = "state") String state,
            @RequestParam(value = "pageNumber") String pageNumber, @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam("sortBy") String sortBy, @RequestParam("orderBy") String orderBy,
            @RequestBody(required = false) SubscriptionFilter filter) throws Exception {
        ApiResponse<String, SubscriptionsResponse> apiResponse;
        boolean failedCondition_1 = !("userName".equalsIgnoreCase(sortBy) || "subscriptionState".equalsIgnoreCase(sortBy));
        boolean failedCondition_2 = !("asc".equalsIgnoreCase(orderBy) || "desc".equalsIgnoreCase(orderBy));
        boolean failedCondition_3 = !("all".equals(state) || (SubscriptionStatesEnum.ACTIVE.name().equals(state)
                || SubscriptionStatesEnum.PENDING.name().equals(state) || SubscriptionStatesEnum.TERMINATE.name().equals(state)));
        if (failedCondition_1 || failedCondition_2 || failedCondition_3) {
            throw new ApiBadRequestException(VALIDATION_FAILED);
        }
        else {
            List<UsersSubscriptionDto> subscriptions;
            subscriptions = subscriptionService.findAllUsersSubscription(state, pageNumber, perPageMaxRecords, sortBy, orderBy, filter);
            if (subscriptions == null || subscriptions.size() == 0) {
                throw new ApiResourceNotFoundException(RESOURCE_NOT_FOUND);
            }
            else {
                RecordStats recordStats = findRecordStats(state, perPageMaxRecords, filter);
                apiResponse = buildResponse(SUCCESS, new SubscriptionsResponse(recordStats, subscriptions), OK);
            }
        }
        return buildResponseEntity(apiResponse);
    }

    private RecordStats findRecordStats(String state, String perPageMaxRecords, SubscriptionFilter filter) throws Exception {
        if (!("all".equals(state) || (SubscriptionStatesEnum.ACTIVE.name().equals(state) || SubscriptionStatesEnum.PENDING.name()
                .equals(state)
                || SubscriptionStatesEnum.TERMINATE.name().equals(state)))) {
            throw new ApiBadRequestException("Validation failed");
        }
        else {
            RecordStats recordStats = subscriptionService.getSubscriptionsRecordStat(state, perPageMaxRecords, filter);
            if (recordStats.getTotalRecords() == 0) {
                LOG.error("## Subscription does not exist");
                throw new ApiResourceNotFoundException(RESOURCE_NOT_FOUND);
            }
            else {
                return recordStats;
            }
        }
    }

    /**
     * Download subscriptions into CSV
     */
    @GetMapping(value = "/subscriptions/download")
    public ResponseEntity<ApiResponse<String, StatusPojo>> download(HttpServletResponse response) throws Exception {
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
