package com.finvendor.api.login.controller;

import com.finvendor.api.common.service.ReferenceDataService;
import com.finvendor.api.consumer.service.ConsumerService;
import com.finvendor.api.formdata.controller.FormDataController;
import com.finvendor.api.login.service.LoginService;
import com.finvendor.api.marketdata.service.MarketDataAggregatorsServiceImpl;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.dto.UserSubscriptionDto;
import com.finvendor.api.user.service.UserService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.exception.ErrorMessage;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.model.*;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.EmailUtil;
import com.finvendor.util.RequestConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(FormDataController.class.getName());

    @Autowired
    private LoginService loginService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private MarketDataAggregatorsServiceImpl marketDataAggregatorsService;

    @Autowired
    private ReferenceDataService referenceDataService;

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = RequestConstans.Home.HOME_PAGE, method = RequestMethod.GET)
    public ModelAndView homePageLand(ModelMap modelMap, HttpServletRequest request) {
        logger.debug("Entering LoginController : homePageLand");
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Home.HOME_PAGE);
        logger.debug("Leaving LoginController : homePageLand");
        return modelAndView;
    }

    @RequestMapping(value = RequestConstans.Login.LOGIN, method = RequestMethod.GET)
    public ModelAndView loginInfo(ModelMap modelMap, HttpServletRequest request) {
        logger.debug("Entering LoginController : loginInfo");
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.LOGIN);
        logger.debug("Leaving LoginController : loginInfo");
        return modelAndView;
    }

    @RequestMapping(value = RequestConstans.Login.WELCOME, method = RequestMethod.GET)
    public ModelAndView welcomeInfo(HttpServletRequest request, @ModelAttribute("users") FinVendorUser users,
            @ModelAttribute("userRole") UserRole userRole) {
        logger.debug("Entering LoginController : welcomeInfo");
        List<AssetClass> assetClasses = null;
        List<Region> regions = null;
        List<Country> countries = null;
        List<Exchange> exchanges = null;
        List<Support> supports = null;
        List<Cost> costs = null;
        List<Awards> awards = null;
        List<CompanySubType> companySubType = null;
        List<SecurityType> securityTypeList = null;
        String username = null;
        ModelAndView modelAndView = null;
        Vendor vendor = null;
        Consumer consumer = null;

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal().
                equals(RequestConstans.Anonymous.ANONYMOUS_USER)) {
            logger.error("No User Principal !!");
            modelAndView = new ModelAndView(RequestConstans.Login.HOME);
        }
        else {

            User appUser = (User) SecurityContextHolder.getContext().
                    getAuthentication().getPrincipal();
            username = appUser.getUsername();
            logger.info("redirectLink for User - {} is {}",
                    username, (String) request.getSession().getAttribute("redirectLink"));
            assetClasses = marketDataAggregatorsService.getAllAssetClass();
            regions = marketDataAggregatorsService.getAllRegionClass();
            countries = marketDataAggregatorsService.getAllCountries();
            exchanges = marketDataAggregatorsService.getAllExchanges();
            supports = marketDataAggregatorsService.getAllVendorSupports();
            costs = marketDataAggregatorsService.getAllCostInfo();
            awards = marketDataAggregatorsService.getAllAwards(null);
            companySubType = marketDataAggregatorsService.getCompanySubTypeList();

            try {
                if (appUser.getAuthorities().contains(new SimpleGrantedAuthority(
                        RequestConstans.Roles.ROLE_ADMIN))) {
                    logger.debug("Role for User - {} is {}",
                            username, RequestConstans.Roles.ROLE_ADMIN);
                    modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
                    request.getSession().setAttribute("loggedInRole",
                            RequestConstans.Roles.ROLE_ADMIN);
                }
                else if (appUser.getAuthorities().contains(new SimpleGrantedAuthority(
                        RequestConstans.Roles.ROLE_VENDOR))) {
                    logger.debug("Role for User - {} is {}",
                            username, RequestConstans.Roles.ROLE_VENDOR);
                    modelAndView = new ModelAndView(RequestConstans.Login.VENDOR_INFO);
                    vendor = userService.getUserDetailsByUsername(username).getVendor();
                    awards = marketDataAggregatorsService.getAllAwards(vendor.getId());
                    modelAndView.addObject("myprofiletab", "myprofile");
                    modelAndView.addObject("vendortabdetails", "vendortabdetails");
                    String telephone = vendor.getTelephone();
                    if (telephone != null) {
                        String[] split = telephone.split("-");
                        if (split != null && split.length == 2) {
                            vendor.setTelephoneCode("+" + split[0].replaceAll("\\s+", ""));
                            vendor.setTelephone(split[1]);
                        }
                    }
                    modelAndView.addObject("vendor", vendor);
                    request.getSession().setAttribute("loggedInRole",
                            RequestConstans.Roles.ROLE_VENDOR);
                }
                else if (appUser.getAuthorities().contains(new SimpleGrantedAuthority(
                        RequestConstans.Roles.ROLE_CONSUMER))) {
                    logger.debug("Role for User - {} is {}",
                            username, RequestConstans.Roles.ROLE_CONSUMER);
                    modelAndView = new ModelAndView(RequestConstans.Login.CONSUMER_INFO);
                    consumer = userService.getUserDetailsByUsername(username).getConsumer();
                    securityTypeList = referenceDataService.getSecurityTypesForAssetClassId(1);
                    CommonUtils.populateConsumerProfileRequest(consumer, consumerService, modelAndView);
                    consumer.setVendorPreference();
                    modelAndView.addObject("securityTypeList", securityTypeList);
                    modelAndView.addObject("consumer", consumer);
                    request.getSession().setAttribute("loggedInRole",
                            RequestConstans.Roles.ROLE_CONSUMER);
                }
                modelAndView.addObject("assetClasses", assetClasses);
                modelAndView.addObject("regions", regions);
                modelAndView.addObject("regionslist", regions);
                modelAndView.addObject("countries", countries);
                modelAndView.addObject("exchanges", exchanges);
                modelAndView.addObject("supports", supports);
                modelAndView.addObject("costs", costs);
                modelAndView.addObject("awards", awards);
                modelAndView.addObject("companySubType", companySubType);
                modelAndView.addObject("username", username);
            } catch (Exception exp) {
                logger.error("Error reading Details for {}", username, exp);
            }
        }
        logger.debug("Leaving LoginController : welcomeInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginError() {
        logger.debug("Entering LoginController : loginfailed");
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.SIGNIN_FAILED);
        modelAndView.addObject("error", "true");
        logger.debug("Leaving LoginController : loginfailed");
        return modelAndView;
    }

    /**
     * method for access denied
     *
     * @return modelAndView
     * @throws Exception the exception
     */
    @RequestMapping(value = "/access-denied")
    public ResponseEntity<ErrorMessage> accessDenied() {
        logger.info("Method for access denied 6--:");
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()),
                HttpStatus.FORBIDDEN); // logical view name
    }

    /**
     * method for login out
     *
     * @return modelAndView
     * @throws Exception the exception
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        logger.info("Method for logout 7---:");
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.HOME);
        return modelAndView;

    }

    /**
     * method for my home
     *
     * @return modelAndView
     * @throws Exception the exception
     */

    @RequestMapping(value = RequestConstans.Login.MY_HOME_PAGE, method = RequestMethod.GET)
    public ModelAndView myHomePage(@RequestParam("RaYUnA") String username) {
        logger.info("Method for logout 8---:");
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = null;
        try {
            username = CommonUtils.decrypt(username.getBytes());
            modelAndView = new ModelAndView(RequestConstans.Login.HOME);
            modelAndView.addObject("myusername", username);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return modelAndView;

    }

    /**
     * method for my home
     *
     * @return modelAndView
     * @throws Exception the exception
     */

    @RequestMapping(value = RequestConstans.Login.MY_VIEW_PAGE, method = RequestMethod.GET)
    public ModelAndView myViewPage(@RequestParam("RaYUnA") String username) {
        logger.info("Method for logout 9---:");
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = null;
        try {
            username = CommonUtils.decrypt(username.getBytes());
            modelAndView = new ModelAndView(RequestConstans.Login.HOME);
            modelAndView.addObject("myusername", username);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return modelAndView;

    }

    @RequestMapping(value = RequestConstans.Login.FORGOT_PASSWORD, method = RequestMethod.POST)
    public ModelAndView forgotPassword(HttpServletRequest request,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "email", required = false) String email) {
        logger.debug("Entering LoginController : forgotPassword");
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Register.EMPTY);
        String status = "false";
        try {
            List<FinVendorUser> users = userService.getUserDetailsByEmailId(email);
            FinVendorUser user = users.get(0);
            if (user == null) {
                logger.error("No User record available for : {}", email);
                status = status + ":Invalid Email Id";
            }
            else {
                logger.info("LoginController : forgotPassword - Resetting password for : {}", user.getUserName());
                String password = userService.resetPassword(user.getUserName());
                EmailUtil.sendResetPasswordEmail(user, password);
                status = "true";
            }
        } catch (Exception exp) {
            logger.error("No User record available for : {}", email);
            status = status + ":Error processing Forgot Password";
        }
        modelAndView.addObject("status", status);
        logger.debug("Leaving LoginController : forgotPassword");
        return modelAndView;
    }

    /**
     * method to reset action for forget password
     *
     * @return modelAndView
     * @throws Exception the exception
     */
    @RequestMapping(value = RequestConstans.Login.RESET_PASSWORD, method = RequestMethod.GET)
    public ModelAndView resetForgetPassword(@RequestParam("email") String email) {
        logger.info("Method to reset forget password 11---:");
        FinVendorUser user = null;
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.FORGOT_PASSWORD);
        try {
            if (!email.equals("") && email != null) {
                user = loginService.getUserInfoByEmail(email);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * method to navigate site map
     *
     * @return modelAndView
     * @throws Exception the exception
     */
    @RequestMapping(value = RequestConstans.Login.SITE_MAP, method = RequestMethod.GET)
    public ModelAndView siteMap() {
        logger.info("Method forget password page 12---:");
        ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.SITE_MAP);
        return modelAndView;
    }


	/*
	@RequestMapping(value =RequestConstans.Login.PRIVACY, method=RequestMethod.GET)
    public ModelAndView Privacy() {
		logger.info("Method forget password page 13---:");
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.PRIVACY);
         return modelAndView;
     }

	 /**
	 * method to navigate disclaimer
	 *
	 * @return modelAndView
	 * @throws Exception
	 *             the exception

	@RequestMapping(value =RequestConstans.Login.DISCLAIMER, method=RequestMethod.GET)
    public ModelAndView Disclaimer() {
		logger.info("Method forget password page 14---:");
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.DISCLAIMER);
         return modelAndView;
     }
      */

    @RequestMapping(value = "displayCompanyLogo/{username}", method = RequestMethod.GET)
    public void displayCompanyLogo(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String username) {
        try {
            logger.debug("LoginController : displayCompanyLogo for {}", username);
            FinVendorUser user = userService.getUserDetailsByUsername(username);
            String relativeWebPath = "/resources/images/user.png";
            String absoluteDiskPath = request.getRealPath(relativeWebPath);
            if (user.getVendor() != null) {
                Vendor vendor = user.getVendor();
                if (vendor.getLogoType() != null) {
                    response.setContentType(vendor.getLogoType());
                    FileCopyUtils.copy(vendor.getLogoBytes().getBinaryStream(),
                            response.getOutputStream());
                }
                else {
                    response.setContentType("image/png");
                    FileCopyUtils.copy(FileCopyUtils.copyToByteArray(
                            new File(absoluteDiskPath)),
                            response.getOutputStream());
                }
            }
            else if (user.getConsumer() != null) {
                Consumer consumer = user.getConsumer();
                if (consumer.getLogoType() != null) {
                    response.setContentType(consumer.getLogoType());
                    FileCopyUtils.copy(consumer.getLogoBytes().getBinaryStream(),
                            response.getOutputStream());
                }
                else {
                    response.setContentType("image/png");
                    FileCopyUtils.copy(FileCopyUtils.copyToByteArray(
                            new File(absoluteDiskPath)),
                            response.getOutputStream());
                }
            }
            else {
                response.setContentType("image/png");
                FileCopyUtils.copy(FileCopyUtils.copyToByteArray(
                        new File(absoluteDiskPath)),
                        response.getOutputStream());
            }
        } catch (Exception exp) {
            logger.error("Error reading logo files for {}", username, exp);
        }
    }

    @RequestMapping(value = "/getfile/{value}", method = RequestMethod.GET)
    public void get(HttpServletRequest request,
            HttpServletResponse response, @PathVariable String value) {
        try {
            Vendor vendor = null;
            Consumer consumer = null;
            User appUser = (User) SecurityContextHolder.getContext().
                    getAuthentication().getPrincipal();
            FinVendorUser user = userService.
                    getUserDetailsByUsername(appUser.getUsername());

            if (RequestConstans.Roles.ROLE_VENDOR.equals(
                    (String) request.getSession().getAttribute("loggedInRole"))) {
                vendor = user.getVendor();
                response.setContentType(vendor.getLogoType());
                if (vendor.getLogoLength() != null) {
                    response.setContentLength(vendor.getLogoLength());
                }
                if (vendor.getLogoBytes() != null) {
                    FileCopyUtils.copy(vendor.getLogoBytes().getBinaryStream(),
                            response.getOutputStream());
                }
            }
            else {
                consumer = user.getConsumer();
                response.setContentType(consumer.getLogoType());
                if (consumer.getLogoBytes() != null) {
                    FileCopyUtils.copy(consumer.getLogoBytes().getBinaryStream(),
                            response.getOutputStream());
                }
            }

        } catch (Exception e) {

        }
    }

    /**
     * Login
     */
    @RequestMapping(value = RequestConstans.Login.LOGINVALIDATION, method = RequestMethod.POST)
    public ResponseEntity<?> loginValidation(@RequestParam(value = "VEuMlA", required = false) String username,
            @RequestParam(value = "RaYulU", required = false) String password,
            @RequestParam(value = "chgUsername", required = false) String chgUsername,
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "passChange") String passChange) throws Exception {
        logger.debug("Entering LoginController : loginValidation");
        String status;
        String userId;
        String credentials;
        if (Boolean.parseBoolean(passChange)) {
            userId = chgUsername;
            credentials = oldPassword;
        }
        else {
            userId = username;
            credentials = password;
        }
        FinVendorUser user = userService.getUserDetailsByUsername(userId);
        List<UserSubscriptionDto> subscriptionDtoList = null;
        ApiResponse<String, List<UserSubscriptionDto>> apiResponse;
        if (user == null) {
            logger.error("## No User record available for : {}", userId);
            status = RequestConstans.INVALID_USER;
            apiResponse = WebUtils.buildResponse("fv-404", status, null, NOT_FOUND);
        }
        else {
            logger.info("## User {} enabled : {}", userId, user.getEnabled());
            if (!user.getEnabled()) {
                logger.error("## User record is disabled for : {}", userId);
                status = RequestConstans.ACCOUNT_DISABLED;
            }
            else {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
                if (encoder.matches(credentials, user.getPassword())) {
                    if (Boolean.parseBoolean(passChange)) {
                        userService.changePassword(userId, encoder.encode(newPassword));
                        userService.updateUnsuccessfulLoginAttempts(userId, true);
                        status = RequestConstans.LOGIN_AFTER_CHANGE_PASSWORD;
                    }
                    else if (user.getLoginAttempts() < 0) {
                        status = RequestConstans.CHANGE_PASSWORD;
                    }
                    else {
                        userService.updateUnsuccessfulLoginAttempts(userId, true);
                        status = "User logged-in successfully";
                    }
                }
                else {
                    logger.error("Incorrect password entered for : {}", userId);
                    if (user.getLoginAttempts() >= RequestConstans.MAX_UNSUCCESSFUL_ATTEMPTS) {
                        userService.updateUserAccountStatus(userId, false);
                    }
                    else {
                        userService.updateUnsuccessfulLoginAttempts(userId, false);
                    }
                    status = RequestConstans.INVALID_PASSWORD;
                }
                UserSubscriptionDto userSubscriptionDetails = subscriptionService.find_UsersSubscription(user.getUserName());
                subscriptionDtoList = new ArrayList<>();
                subscriptionDtoList.add(userSubscriptionDetails);
            }
            apiResponse = WebUtils.buildResponse("fv-200", status, subscriptionDtoList, OK);
        }
        return WebUtils.buildResponseEntity(apiResponse);
    }
}
