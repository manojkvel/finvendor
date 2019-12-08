package com.finvendor.api.login.service;

import com.finvendor.api.configuration.service.SysConfig;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.enums.SubscriptionTypeEnum;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.common.util.Pair;
import com.finvendor.model.*;
import com.finvendor.util.EmailUtil;
import com.finvendor.util.RequestConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class RegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class.getName());
    private String[] vendorTypes = { RequestConstans.INDEPENDENT_RESEARCH_ANALYST, RequestConstans.RESEARCH_BROKER };
    private List<String> vendorTypesList = Arrays.asList(vendorTypes);

    private final UserService userService;

    private final SysConfig sysConfig;

    private final SubscriptionService subscriptionService;

    @Autowired
    public RegistrationService(UserService userService, SysConfig sysConfig,
            SubscriptionService subscriptionService) {
        this.userService = userService;
        this.sysConfig = sysConfig;
        this.subscriptionService = subscriptionService;
    }

    public Pair<Boolean, String> registerUser(FinVendorUser user, Roles role, UserRole userRole,
            Vendor vendor, Consumer consumer, String uname, String password, String email, String company, String companyType,
            String tags) {
        String json;
        boolean registrationStatus = true;

        Set<UserRole> userRoles;
        String userRoleName;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setUserName(uname.toLowerCase());
        user.setPassword(encoder.encode(password));
        user.setEnabled(false);
        user.setEmail(email.toLowerCase());
        user.setVerified("N");
        user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));

        //Set Subscription details
        //user.setSubscriptionType(SubscriptionTypeEnum.FREE.toString());
        //user.setSubscriptionStartTime(null);
        //user.setSubscriptionEndTime(null);
        //user.setSubscriptionState(null);
        boolean isVendor = checkUserTypeFromCompany(companyType);
        try {
            FinVendorUser userDetailsByUsername = userService.getUserDetailsByUsername(uname);
            if (userDetailsByUsername != null) {
                json = "{\"message\":\"User name already exist, Please provide other user name.\"}";
            }
            else {
                //new UserName here now check for existing email
                List<FinVendorUser> emailsFromDb = userService.getUserDetailsByEmailId(email);
                if (!emailsFromDb.isEmpty()) {
                    json = "{\"message\":\"Email already exist, Please provide other email address.\"}";
                    registrationStatus = false;
                }
                else {
                    if (isVendor) {
                        role.setId(new Integer(RequestConstans.Roles.ROLE_VENDOR_VALUE));
                        userRoleName = "VENDOR";
                        vendor.setId(UUID.randomUUID().toString());
                        vendor.setFirstName(uname);
                        vendor.setLastName("");
                        vendor.setDesignation("");
                        vendor.setSecondaryEmail("");
                        vendor.setTelephone("");
                        vendor.setCompany(company);
                        vendor.setCompanyInfo("");
                        vendor.setCompanyUrl("");
                        vendor.setCompanyType(companyType);
                        vendor.setTags(tags);
                        vendor.setCompanyAddress("");
                        vendor.setUser(user);
                        user.setVendor(vendor);
                    }
                    else {
                        role.setId(new Integer(RequestConstans.Roles.ROLE_CONSUMER_VALUE));
                        userRoleName = "CONSUMER";
                        consumer.setId(UUID.randomUUID().toString());
                        consumer.setFirstName(uname);
                        consumer.setLastName("");
                        consumer.setTelephone("");
                        consumer.setCompany(company);
                        consumer.setCompanyType(companyType);
                        consumer.setTags(tags);
                        consumer.setUser(user);
                        user.setConsumer(consumer);
                    }
                    userRole.setRoles(role);
                    userRole.setUser(user);
                    userRoles = new HashSet<>();
                    userRoles.add(userRole);
                    user.setUserRoles(userRoles);
                    userService.saveUserInfo(user);
                    String registrationId = userService.insertRegistrationVerificationRecord(user.getUserName(), false);
                    if (Objects.requireNonNull(sysConfig.config()).isEmailEnabled()) {
                        EmailUtil.sendRegistrationEmail(user, email.toLowerCase(), registrationId);
                        EmailUtil.sendNotificationEmail("FinVendor Registration", "has registered on FinVendor.", user, userRoleName);
                    }
                    SubscriptionDto subscriptionDto = new SubscriptionDto();
                    subscriptionDto.setSubscriptionType(SubscriptionTypeEnum.FREE.name());
                    subscriptionService.saveUserSubscription(user.getUserName(), subscriptionDto);
                    LOGGER.info("## FREE Subscription added successfully");
                    json = "{\"message\":\"Registration done successfully\"}";
                }
            }
        } catch (Exception e) {
            json = "{\"message\":\"Error registering user. Please contact Fin Vendor for support.\"}";
            registrationStatus = false;
        }
        return new Pair<>(registrationStatus, json);
    }

    private boolean checkUserTypeFromCompany(String companyType) {
        String[] companyTypeSelected = companyType.split(",");
        for (String company : companyTypeSelected) {
            if (vendorTypesList.contains(company)) {
                return true;
            }
        }
        return false;
    }

}
