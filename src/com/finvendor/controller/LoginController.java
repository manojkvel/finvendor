package com.finvendor.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.model.AssetClass;
import com.finvendor.model.Awards;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.model.UserRole;
import com.finvendor.model.Vendor;
import com.finvendor.service.ConsumerService;
import com.finvendor.service.LoginService;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.service.ReferenceDataService;
import com.finvendor.service.UserService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;

@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	@Resource(name="referenceDataService")
	private ReferenceDataService referenceDataService;
	
	@Resource(name="consumerService")
	private ConsumerService consumerService;
		
	@RequestMapping(value=RequestConstans.Home.HOME_PAGE, method=RequestMethod.GET)
	public ModelAndView homePageLand(ModelMap modelMap, HttpServletRequest request) {
		logger.debug("Entering LoginController : homePageLand");
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Home.HOME_PAGE);
		logger.debug("Leaving LoginController : homePageLand");
		return modelAndView;
	}

	@RequestMapping(value=RequestConstans.Login.LOGIN, method=RequestMethod.GET)
	public ModelAndView loginInfo(ModelMap modelMap, HttpServletRequest request) {
		logger.debug("Entering LoginController : loginInfo");
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.LOGIN);
		logger.debug("Leaving LoginController : loginInfo");
		return modelAndView;
	}

	@RequestMapping(value=RequestConstans.Login.LOGINVALIDATION, method=RequestMethod.POST)
	public ModelAndView loginValidation(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(value = "VEuMlA", required = false) String username,
			@RequestParam(value = "RaYulU", required = false) String password,
			@RequestParam(value = "chgUsername", required = false) String chgUsername,
			@RequestParam(value = "oldPassword", required = false) String oldPassword,
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "passChange", required = true) String passChange) {
		
		logger.debug("Entering LoginController : loginValidation");
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Register.EMPTY);
		String status = "false";
		try{
			String userId = null;
			String credentials = null;
			boolean changePassword = Boolean.valueOf(passChange);
			
			if (changePassword) {
				userId = chgUsername;
				credentials = oldPassword;
			}else {
				userId = username;
				credentials = password;
			}
			
			FinVendorUser user = userService.getUserDetailsByUsername(userId);			
			if(user == null){
				logger.error("No User record available for : {}", userId);
				status = status + ":" + RequestConstans.INVALID_USER;
			}else{
				logger.info("User {} enbabled : {}", userId, user.getEnabled());
				if (!user.getEnabled()){
					logger.error("User record is disabled for : {}", userId);
					status = status + ":" + RequestConstans.ACCOUNT_DISABLED;
				}else{
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
					if(encoder.matches(credentials, user.getPassword())) {
						if(changePassword) {
							userService.changePassword(userId, encoder.encode(newPassword));
							userService.updateUnsuccessfulLoginAttempts(userId, true);
							status = status + ":" + RequestConstans.LOGIN_AFTER_CHANGE_PASSWORD;
						}
						else if(user.getLoginAttempts() < 0) {
							status = status + ":" + RequestConstans.CHANGE_PASSWORD;
						} else {
							userService.updateUnsuccessfulLoginAttempts(userId, true);
							status = "true";
						}						
					}else{
						logger.error("Incorrect password enterd for : {}", userId);
						if(user.getLoginAttempts() >= RequestConstans.MAX_UNSUCCESSFUL_ATTEMPTS){
							userService.updateUserAccountStatus(userId, false);
						}else{
							userService.updateUnsuccessfulLoginAttempts(userId, false);
						}
						status = status + ":" + RequestConstans.INVALID_PASSWORD;
					}
				}
			}
			modelAndView.addObject("status", status);
		}catch (Exception exp) {
			modelAndView.addObject("status", "false:Error during login");
			logger.error("Error validating User login : ", exp);
		}
		logger.debug("Leaving LoginController : loginValidation");
		return modelAndView;
	}
	
	@RequestMapping(value=RequestConstans.Login.WELCOME, method=RequestMethod.GET)
	public ModelAndView welcomeInfo(HttpServletRequest request,
			@ModelAttribute("users") FinVendorUser users,
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
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null 
			&&  SecurityContextHolder.getContext().getAuthentication().getPrincipal().
				equals(RequestConstans.Anonymous.ANONYMOUS_USER)) {
			logger.error("No User Principal !!");
			modelAndView = new ModelAndView(RequestConstans.Login.HOME);
		}else {			
			
 			User appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
 			username = appUser.getUsername();
 			logger.info("redirectLink for User - {} is {}", 
 					username, (String)request.getSession().getAttribute("redirectLink"));			
 			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			companySubType = marketDataAggregatorsService.getCompanySubTypeList();
			
			try {
				if(appUser.getAuthorities().contains(new SimpleGrantedAuthority(
						RequestConstans.Roles.ROLE_ADMIN))) {
					logger.debug("Role for User - {} is {}", 
							username, RequestConstans.Roles.ROLE_ADMIN);
					modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
					request.getSession().setAttribute("loggedInRole", 
							RequestConstans.Roles.ROLE_ADMIN);
		       	} else if(appUser.getAuthorities().contains(new SimpleGrantedAuthority(
		       			RequestConstans.Roles.ROLE_VENDOR))) {
		       		logger.debug("Role for User - {} is {}", 
		       				username, RequestConstans.Roles.ROLE_VENDOR);
		       		modelAndView = new ModelAndView(RequestConstans.Login.VENDOR_INFO);					
					vendor = userService.getUserDetailsByUsername(username).getVendor();
					modelAndView.addObject("myprofiletab", "myprofile");	       		
		       		modelAndView.addObject("vendortabdetails", "vendortabdetails");	       	
		       		String telephone = vendor.getTelephone();
		       	if(telephone != null){
		       		String[] split = telephone.split("-");
		       		if(split != null && split.length == 2){
			       		vendor.setTelephoneCode("+"+split[0].replaceAll("\\s+",""));
			       		vendor.setTelephone(split[1]);
		       		}
		       	}
		       		modelAndView.addObject("vendor", vendor);
		       		request.getSession().setAttribute("loggedInRole", 
		       				RequestConstans.Roles.ROLE_VENDOR);
		       	} else if(appUser.getAuthorities().contains(new SimpleGrantedAuthority(
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
			}catch (Exception exp) {
				logger.error("Error reading Details for {}", username, exp);
			}
		}		
		logger.debug("Leaving LoginController : welcomeInfo");	
		return modelAndView;
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
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
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/access-denied")
    public String accessDenied() {
		logger.info("Method for access denied 6--:");
          return "access-denied"; // logical view name
     }
	
	/**
	 * method for login out
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		logger.info("Method for logout 7---:");
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (request.getSession() != null ){
			request.getSession().invalidate();
		}
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.HOME);
		return modelAndView;
 
	}
	
	/**
	 * method for my home
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Login.MY_HOME_PAGE, method = RequestMethod.GET)
	public ModelAndView myHomePage(@RequestParam("RaYUnA") String username) {
		logger.info("Method for logout 8---:");
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView  modelAndView= null;
		try{
			username = CommonUtils.decrypt(username.getBytes());
			modelAndView=new ModelAndView(RequestConstans.Login.HOME);
			modelAndView.addObject("myusername", username);
		}catch(Exception exception){
			exception.printStackTrace();
		}
        
		return modelAndView;
 
	}
	
	
	/**
	 * method for my home
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Login.MY_VIEW_PAGE, method = RequestMethod.GET)
	public ModelAndView myViewPage(@RequestParam("RaYUnA") String username) {
		logger.info("Method for logout 9---:");
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView  modelAndView= null;
		try{
			username = CommonUtils.decrypt(username.getBytes());
			modelAndView=new ModelAndView(RequestConstans.Login.HOME);
			modelAndView.addObject("myusername", username);
		}catch(Exception exception){
			exception.printStackTrace();
		}
        
		return modelAndView;
 
	}
	
	/**
	 * method to navigate forget password
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Login.FORGET, method=RequestMethod.GET)
    public ModelAndView forgetPassword() {
		logger.info("Method forget password page 10---:");
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.FORGET);
         return modelAndView;
     }
	
	/**
	 * method to reset action for forget password
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Login.RESET_PASSWORD, method=RequestMethod.GET)
    public ModelAndView resetForgetPassword(@RequestParam("email") String email) {
		logger.info("Method to reset forget password 11---:");
		FinVendorUser user=null;
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.FORGET);
       try{
        if(!email.equals("") && email != null){
        	user=loginService.getUserInfoByEmail(email); 
        }
       }
       catch(Exception ex){
    	   ex.printStackTrace();
       }
         return modelAndView;
     }
	
	/**
	 * method to navigate site map
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Login.SITE_MAP, method=RequestMethod.GET)
    public ModelAndView siteMap() {
		logger.info("Method forget password page 12---:");
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.SITE_MAP);
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
	public void displayCompanyLogo(HttpServletResponse response,
			@PathVariable String username) {
		try {
			logger.debug("LoginController : displayCompanyLogo for {}", username);
			FinVendorUser user = userService.getUserDetailsByUsername(username);
			if(user.getVendor() != null) {
				Vendor vendor = user.getVendor();
				response.setContentType(vendor.getLogoType());
				FileCopyUtils.copy(vendor.getLogoBytes().getBinaryStream(), response.getOutputStream());
			}else {
				Consumer consumer = user.getConsumer();
				response.setContentType(consumer.getLogoType());
				FileCopyUtils.copy(consumer.getLogoBytes().getBinaryStream(), response.getOutputStream());
			}
		}catch (Exception exp) {
			logger.error("Error reading logo files for {}", username, exp);
		}
	}
}
