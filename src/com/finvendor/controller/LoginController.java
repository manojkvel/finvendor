/**
 * 
 */
package com.finvendor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.model.AssetClass;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.Region;
import com.finvendor.model.Support;
import com.finvendor.model.UserRole;
import com.finvendor.model.Vendor;
import com.finvendor.service.LoginService;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.service.UserService;
import com.finvendor.service.VendorService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;

/**
 * @author rayulu vemula
 *
 */
@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	
	@RequestMapping(value=RequestConstans.Home.HOME_PAGE, method=RequestMethod.GET)
	public ModelAndView homePageLand(ModelMap modelMap,HttpServletRequest request){
		logger.info("Method to load home page 1---:");
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Home.HOME_PAGE);
		return modelAndView;
	}

	@RequestMapping(value=RequestConstans.Login.LOGIN, method=RequestMethod.GET)
	public ModelAndView loginInfo(ModelMap modelMap,HttpServletRequest request){
		logger.info("Method to login page 2---:");
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.LOGIN);
		return modelAndView;
	}

	/**
	 * method for spring security checking through role
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value=RequestConstans.Login.LOGINVALIDATION, method=RequestMethod.POST)
	public ModelAndView loginValidation(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(value = "VEuMlA", required = false) String username,
			@RequestParam(value = "RaYulU", required = false) String password){
		logger.info("Method to login page 3---:");
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Register.EMPTY);
		String status = "false";
		try{
			username=CommonUtils.decrypt(username.getBytes());
			password=CommonUtils.decrypt(password.getBytes());
			FinVendorUser user = userService.getUserDetailsByUsername(username);			
			if(user == null){
				logger.error("No User record available for : " + username);
				status = status + ":" + RequestConstans.INVALID_USER;
			}else{
				logger.info("User enbabled : " + user.getEnabled());
				if (!user.getEnabled()){
					logger.error("User record is disabled for : " + username);
					status = status + ":" + RequestConstans.ACCOUNT_DISABLED;
				}else{
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
					if(encoder.matches(password, user.getPassword())){
						userService.updateUnsuccessfulLoginAttempts(username, true);
						status = "true";
					}else{
						logger.error("Incorrect password enterd for : " + username);
						if(user.getLoginAttempts() >= RequestConstans.MAX_UNSUCCESSFUL_ATTEMPTS){
							userService.updateUserAccountStatus(username, false);
						}else{
							userService.updateUnsuccessfulLoginAttempts(username, false);
						}
						status = status + ":" + RequestConstans.INVALID_PASSWORD;
					}
				}
			}
			/*
			List<Users> users = userService.getUserInfoByNamewithPassword(username,password);
			if(users.size() > 0){
				status = false;
			}else{
				status = true;
			}*/
			modelAndView.addObject("status", status);
		}catch (Exception e) {
			modelAndView.addObject("status", "false:Error during login");
			e.printStackTrace();
			logger.error("Error to check user validation--- :" + e);
		}
		return modelAndView;
	}

	
	/**
	 * method for spring security checking through role
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value=RequestConstans.Login.WELCOME, method=RequestMethod.GET)
	public ModelAndView welcomeInfo(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@ModelAttribute("userRole") UserRole userRole,@ModelAttribute("vendor") Vendor vendor){
		logger.info("Method to load all home pages 4---:");
		logger.info("redirectLink == " + (String)request.getSession().getAttribute("redirectLink"));
		ModelAndView modelAndView=null;
		if(SecurityContextHolder.getContext().
				getAuthentication().getPrincipal() != null &&  SecurityContextHolder.getContext().
				getAuthentication().getPrincipal().equals(RequestConstans.Anonymous.ANONYMOUS_USER)){
			modelAndView=new ModelAndView(RequestConstans.Login.HOME);
		}else{
			List<AssetClass> assetClasses = null;
			List<Region> regions = null;
			List<Country> countries = null;
			List<Exchange> exchanges = null;
			List<Support> supports = null;
			List<Cost> costs = null;
			List<Awards> awards = null;
 			User appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			try{
			if(appUser.getAuthorities().contains(new GrantedAuthorityImpl(RequestConstans.Roles.ROLE_ADMIN))){
				logger.info("ROLE = " + RequestConstans.Roles.ROLE_ADMIN);
				modelAndView=new ModelAndView(RequestConstans.Login.ADMIN_INFO);
	       		modelAndView.addObject("username", appUser.getUsername());
	       	} else if(appUser.getAuthorities().contains(new GrantedAuthorityImpl(RequestConstans.Roles.ROLE_VENDOR))){
	       		logger.info("ROLE = " + RequestConstans.Roles.ROLE_VENDOR);
	       		modelAndView=new ModelAndView(RequestConstans.Login.VENDOR_INFO);
	       		assetClasses = marketDataAggregatorsService.getAllAssetClass();
				regions = marketDataAggregatorsService.getAllRegionClass();
				countries = marketDataAggregatorsService.getAllCountries();
				exchanges = marketDataAggregatorsService.getAllExchanges();
				supports =  marketDataAggregatorsService.getAllVendorSupports();
				costs  = marketDataAggregatorsService.getAllCostInfo();
				awards = marketDataAggregatorsService.getAllAwards();
				
				vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
				logger.info(vendor.getId());
				logger.info(vendor.getCompany());
				logger.info(vendor.getCompanyAddress());
				logger.info(vendor.getCompanyInfo());
				logger.info(vendor.getFirstName());
				logger.info(vendor.getDesignation());
				
				//vendor = vendorService.getVendorDetails(appUser.getUsername());
				
				modelAndView.addObject("assetClasses", assetClasses);
				modelAndView.addObject("regions", regions);
				modelAndView.addObject("regionslist", regions);
				modelAndView.addObject("countries", countries);
				modelAndView.addObject("exchanges", exchanges);
				modelAndView.addObject("supports", supports);
				modelAndView.addObject("costs", costs);
				modelAndView.addObject("awards", awards);
	       		modelAndView.addObject("username", appUser.getUsername());
	       		modelAndView.addObject("myprofiletab", "myprofile");
	       		
	       		modelAndView.addObject("vendortabdetails", "vendortabdetails");
	       		
	       		modelAndView.addObject("vendor", vendor);
	       		
	       	} else if(appUser.getAuthorities().contains(new GrantedAuthorityImpl(RequestConstans.Roles.ROLE_CONSUMER))){
	       		modelAndView=new ModelAndView(RequestConstans.Login.CONSUMER_INFO);
	       		logger.info("ROLE = " + RequestConstans.Roles.ROLE_CONSUMER);
	       		assetClasses = marketDataAggregatorsService.getAllAssetClass();
				regions = marketDataAggregatorsService.getAllRegionClass();
				countries = marketDataAggregatorsService.getAllCountries();
				exchanges = marketDataAggregatorsService.getAllExchanges();
				supports =  marketDataAggregatorsService.getAllVendorSupports();
				costs  = marketDataAggregatorsService.getAllCostInfo();
				awards = marketDataAggregatorsService.getAllAwards();
				
				modelAndView.addObject("assetClasses", assetClasses);
				modelAndView.addObject("regions", regions);
				modelAndView.addObject("regionslist", regions);
				modelAndView.addObject("countries", countries);
				modelAndView.addObject("exchanges", exchanges);
				modelAndView.addObject("supports", supports);
				modelAndView.addObject("costs", costs);
				modelAndView.addObject("awards", awards);
	       		modelAndView.addObject("username", appUser.getUsername());
	       		modelAndView.addObject("consumerMyProfiletab", "consumermyprofile");
	       	}
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("Error to load all home pages---:" + e);
			}
		}
		return modelAndView;
	}
	
	/**
	 * method for bad login info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginError() {
		logger.info("Method for login failed 5---:");
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.SIGNIN_FAILED);
        modelAndView.addObject("error", "true");
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
	

	/**
	 * method to navigate privacy
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
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
	 */ 
	@RequestMapping(value =RequestConstans.Login.DISCLAIMER, method=RequestMethod.GET)
    public ModelAndView Disclaimer() {
		logger.info("Method forget password page 14---:");
        ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.DISCLAIMER);
         return modelAndView;
     } 
}
