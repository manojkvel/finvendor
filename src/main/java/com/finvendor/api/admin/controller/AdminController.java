package com.finvendor.api.admin.controller;

import com.finvendor.api.admin.service.AdminServiceImpl;
import com.finvendor.api.common.service.ReferenceDataServiceImpl;
import com.finvendor.api.user.service.UserServiceImpl;
import com.finvendor.api.vendor.service.VendorServiceImpl;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.Country;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.ReferenceData;
import com.finvendor.model.TableColumn;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.EmailUtil;
import com.finvendor.util.RequestConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class.getName());

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AdminServiceImpl adminService;
	@Autowired
	private VendorServiceImpl vendorService;

//	@Resource(name="consumerService")
//	private ConsumerService consumerService;

	@Autowired
	private ReferenceDataServiceImpl referenceDataService;

	@Resource(name = "finvendorProperties")
	private Properties finvendorProperties;

	private static final String KEY_PREFIX = "admin_ref_data_";
	
	@RequestMapping(value="adminViewUserDetails", method=RequestMethod.GET)
	public ModelAndView adminViewUserDetails(HttpServletRequest request,
			@RequestParam(value = "nav", required = true) String nav) {
		logger.debug("Entering AdminController : adminViewUserDetails");
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
		modelAndView.addObject("requestType", "adminViewUserDetails");
		List<FinVendorUser> userList = userService.getUserDetails();
		logger.debug("Entering AdminController : User list of size {} retrived", userList.size());
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("nav", nav);
		logger.debug("Leaving AdminController : adminViewUserDetails");
		return modelAndView;
	}

	@RequestMapping(value="adminUpdateUserAccountStatus", method=RequestMethod.GET)
	public ModelAndView adminUpdateUserAccountStatus(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "enable", required = true) String enable) {
		logger.debug("Entering AdminController : adminUpdateUserAccountStatus");
		logger.debug("Update User Account Status for User Id {}, Status {}", userId, enable);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
		userService.updateUserAccountStatus(userId, Boolean.valueOf(enable));
		modelAndView.addObject("requestType", "adminViewUserDetails");
		List<FinVendorUser> userList = userService.getUserDetails();
		logger.debug("Entering AdminController : User list of size {} retrived", userList.size());
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("nav", "Manage Account");
		modelAndView.addObject("lastActionStatus", "User Account Updated Successfully !");
		return modelAndView;
	}
	
	@RequestMapping(value="adminResetPassword", method=RequestMethod.GET)
	public ModelAndView adminResetPassword(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value = "userId", required = true) String userId) 
					throws ApplicationException {
		logger.debug("Entering AdminController : adminResetPassword for {}", userId);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
		String password = userService.resetPassword(userId);
		modelAndView.addObject("requestType", "adminViewUserDetails");
		FinVendorUser user = userService.getUserDetailsByUsername(userId);
		List<FinVendorUser> userList = userService.getUserDetails();
		logger.debug("Entering AdminController : User list of size {} retrived", userList.size());
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("nav", "Manage Account");
		try {
			EmailUtil.sendResetPasswordEmail(user, password);
		}catch (MessagingException exp) {
			logger.error("Error sending Reset Password mail for {}", userId, exp);
			throw new ApplicationException("Error sending Registration Email for " + userId);
		}
		modelAndView.addObject("lastActionStatus", "User Password Reset Successfully !");
		return modelAndView;
	}
	
	@RequestMapping(value="adminReferenceDataDetails", method=RequestMethod.GET)
	public ModelAndView adminReferenceDataDetails(HttpServletRequest request,
			@RequestParam(value = "nav", required = true) String nav,
			@RequestParam(value = "subNav", required = true) String subNav) {
		logger.debug("Entering AdminController : adminReferenceDataDetails for {}_{}", nav, subNav);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
		modelAndView.addObject("requestType", "adminReferenceDataDetails");
		String dataMapping = finvendorProperties.getProperty(KEY_PREFIX + nav + "_" + subNav);
		ReferenceData refData = getReferenceDataFromMapping(dataMapping);
		List<Object[]> referenceTableData = adminService.getReferenceData(refData);
		modelAndView.addObject("referenceTableData", referenceTableData);
		modelAndView.addObject("refData", refData);
		modelAndView.addObject("nav", nav);
		modelAndView.addObject("subNav", subNav);
		logger.debug("Leaving AdminController : adminReferenceDataDetails for {}_{}", nav, subNav);
		return modelAndView;
	}
	
	@RequestMapping(value="adminEditReferenceDataRow", method=RequestMethod.GET)
	public ModelAndView adminEditReferenceDataRow(HttpServletRequest request,
			@RequestParam(value = "nav", required = true) String nav,
			@RequestParam(value = "subNav", required = true) String subNav,
			@RequestParam(value = "tableKey", required = true) String tableKey) {
		logger.debug("Entering AdminController : adminEditReferenceDataRow for {}_{}", nav, subNav);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Admin.ADMIN_EDIT_REF_DATA);
		String dataMapping = finvendorProperties.getProperty(KEY_PREFIX + nav + "_" + subNav);
		ReferenceData refData = getReferenceDataFromMapping(dataMapping);
		List<Object[]> referenceTableData = adminService.getReferenceDataRow(refData, tableKey);
		String[] columnNames = refData.getColumnNames().split(",");
		modelAndView.addObject("columnNames", columnNames);
		modelAndView.addObject("totalColumns", columnNames.length);
		modelAndView.addObject("referenceTableData", referenceTableData.get(0));
		modelAndView.addObject("refData", refData);
		modelAndView.addObject("nav", nav);
		modelAndView.addObject("subNav", subNav);
		modelAndView.addObject("adminAction", RequestConstans.Admin.ADMIN_ACTION_EDIT_REF_DATA);
		logger.debug("Leaving AdminController : adminEditReferenceDataRow for {}_{}", nav, subNav);
		return modelAndView;
	}
	
	@RequestMapping(value="adminUpdateReferenceData", method=RequestMethod.POST)
	public ModelAndView adminUpdateReferenceData(HttpServletRequest request,
			@RequestParam(value = "nav", required = true) String nav,
			@RequestParam(value = "subNav", required = true) String subNav,
			@RequestParam(value = "adminAction", required = true) String adminAction) {
		logger.debug("Entering AdminController : adminUpdateReferenceData for {}_{}", nav, subNav);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
		String dataMapping = finvendorProperties.getProperty(KEY_PREFIX + nav + "_" + subNav);
		ReferenceData refData = getReferenceDataFromMapping(dataMapping);
		List<String> params = new ArrayList<String>();
		List<Boolean> paramSelected = new ArrayList<Boolean>();
		StringTokenizer tokenizer = new StringTokenizer(refData.getColumnNames(), ",");
		while(tokenizer.hasMoreTokens()) {
			String columnName = tokenizer.nextToken();
			String paramValue = request.getParameter(columnName);
			if("id".equals(columnName) && (paramValue == null || paramValue.trim().equals(""))) {
				paramValue = UUID.randomUUID().toString();
			}
			params.add(paramValue);
			columnName = columnName + "_check";
			paramValue = request.getParameter(columnName);
			if (paramValue == null) {
				paramSelected.add(false);
			}else {
				paramSelected.add(true);
			}
		}
		try{
			if(RequestConstans.Admin.ADMIN_ACTION_ADD_REF_DATA.equals(adminAction)) {
				adminService.addReferenceDataRow(refData, params, paramSelected);
			}else {
				adminService.updateReferenceDataRow(refData, params, paramSelected);	
			}				
			List<Object[]> referenceTableData = adminService.getReferenceData(refData);
			modelAndView.addObject("referenceTableData", referenceTableData);
			modelAndView.addObject("lastActionStatus", "Reference Data for Table " + 
				refData.getTableName()  + " Updated Successfully !");
		}catch(ApplicationException exp){
			logger.error("Error Updating Reference Data Row", exp);
			modelAndView.addObject("lastActionError", exp.getMessage());
		}
		modelAndView.addObject("refData", refData);
		modelAndView.addObject("nav", nav);
		modelAndView.addObject("subNav", subNav);		
		logger.debug("Leaving AdminController : adminUpdateReferenceData for {}_{}", nav, subNav);
		return modelAndView;
	}
	
	@RequestMapping(value="adminDeleteReferenceDataRow", method=RequestMethod.GET)
	public ModelAndView adminDeleteReferenceDataRow(HttpServletRequest request,
			@RequestParam(value = "nav", required = true) String nav,
			@RequestParam(value = "subNav", required = true) String subNav,
			@RequestParam(value = "tableKeyName", required = true) String tableKeyName,
			@RequestParam(value = "tableKey", required = true) String tableKey) {
		logger.debug("Entering AdminController : adminDeleteReferenceDataRow for {}_{}", nav, subNav);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.ADMIN_INFO);
		String dataMapping = finvendorProperties.getProperty(KEY_PREFIX + nav + "_" + subNav);
		ReferenceData refData = getReferenceDataFromMapping(dataMapping);
		List<String> primaryKeyList = new ArrayList<String>();
		primaryKeyList.add(tableKeyName);
		List<String> primaryKeyValueList = new ArrayList<String>();
		primaryKeyValueList.add(tableKey);
		try{
			adminService.deleteReferenceDataRow(refData, primaryKeyList, primaryKeyValueList);		
			List<Object[]> referenceTableData = adminService.getReferenceData(refData);
			modelAndView.addObject("referenceTableData", referenceTableData);
			modelAndView.addObject("lastActionStatus", "Reference Data for Table " + 
				refData.getTableName()  + " Updated Successfully !");
		}catch(ApplicationException exp){
			logger.error("Error Deleting Reference Data Row", exp);
			modelAndView.addObject("lastActionError", exp.getMessage());
		}
		modelAndView.addObject("refData", refData);
		modelAndView.addObject("nav", nav);
		modelAndView.addObject("subNav", subNav);
		logger.debug("Leaving AdminController : adminDeleteReferenceDataRow for {}_{}", nav, subNav);
		return modelAndView;
	}
	
	@RequestMapping(value="adminAddReferenceDataRow", method=RequestMethod.GET)
	public ModelAndView adminAddReferenceDataRow(HttpServletRequest request,
			@RequestParam(value = "nav", required = true) String nav,
			@RequestParam(value = "subNav", required = true) String subNav) {
		logger.debug("Entering AdminController : adminAddReferenceDataRow for {}_{}", nav, subNav);
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Admin.ADMIN_EDIT_REF_DATA);
		String dataMapping = finvendorProperties.getProperty(KEY_PREFIX + nav + "_" + subNav);
		ReferenceData refData = getReferenceDataFromMapping(dataMapping);
		String[] columnNames = refData.getColumnNames().split(",");
		modelAndView.addObject("columnNames", columnNames);
		modelAndView.addObject("totalColumns", columnNames.length);
		modelAndView.addObject("refData", refData);
		modelAndView.addObject("nav", nav);
		modelAndView.addObject("subNav", subNav);
		modelAndView.addObject("adminAction", RequestConstans.Admin.ADMIN_ACTION_ADD_REF_DATA);
		logger.debug("Leaving AdminController : adminAddReferenceDataRow for {}_{}", nav, subNav);
		return modelAndView;
	}
	
	@RequestMapping(value="adminUserSummaryProfile", method=RequestMethod.GET)
	public ModelAndView adminUserSummaryProfile(HttpServletRequest request,
			@RequestParam(value = "nav", required = false) String nav,
			@RequestParam(value = "userName", required = true) String userName) {
		logger.debug("Entering AdminController : adminUserSummaryProfile");
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Admin.ADMIN_USER_SUMMARY_PROFILE);
		modelAndView.addObject("requestType", "adminUserSummaryProfile");
		List<Object[]> marketDataOfferings = null;
		List<Object[]> tradingApplicationOfferings = null;
		List<Object[]> analyticsApplicationOfferings = null;
		List<Object[]> researchReportOfferings = null;
		List<Object[]> vendorAwardDetails = null;
		try {
			Country country = null;
			FinVendorUser user = userService.getUserDetailsByUsername(userName);
			if(user.getVendor() != null) {
				if(user.getVendor().getCountryofincorp() != null && !user.getVendor().
						getCountryofincorp().equals("")) {
					country = (Country)referenceDataService.getModelObjectById(
							Country.class, new Integer(user.getVendor().getCountryofincorp()));
					}
				CommonUtils.populateVendorProfileRequest(user.getVendor(),
						vendorService, modelAndView);
				marketDataOfferings = vendorService.
						getMarketDataVendorOfferingsForProfile(user.getVendor().getId());
				tradingApplicationOfferings = vendorService.
						getTradingApplicationOfferingsForProfile(user.getVendor().getId());
				analyticsApplicationOfferings = vendorService.
						getAnalyticsApplicationOfferingsForProfile(user.getVendor().getId());
				researchReportOfferings = vendorService.
						getResearchReportOfferingsForProfile(user.getVendor().getId());
				vendorAwardDetails = vendorService.getVendorAwardDetailsForProfile(
						user.getVendor().getId());

				/*
				marketDataOfferings = new ArrayList<Object[]>();
				Object[] obj = new Object[6];
				obj[0]="Equities";
				obj[1]="testoffering1 2010";
				obj[2]="Description";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				marketDataOfferings.add(obj);
				obj = new Object[6];
				obj[0]="Equities";
				obj[1]="Equity 2014";
				obj[2]="Description";
				obj[3]="Asia Pacific";
				obj[4]="IndiaMalaysiaSingapore";
				obj[5]="2014";
				marketDataOfferings.add(obj);
				obj = new Object[6];
				obj[0]="Fixed Income";
				obj[1]="Fixed Income 1 2014";
				obj[2]="Description";
				obj[3]="Asia Pacific";
				obj[4]="IndiaMalaysiaSingapore";
				obj[5]="2014";
				marketDataOfferings.add(obj);
				obj = new Object[6];
				obj[0]="Fixed Income";
				obj[1]="Fixed Income 2 2015";
				obj[2]="Description";
				obj[3]="Asia Pacific";
				obj[4]="IndiaMalaysiaSingapore";
				obj[5]="2015";
				marketDataOfferings.add(obj);
				obj = new Object[6];
				obj[0]="Stocks";
				obj[1]="Stocks 1 2015";
				obj[2]="Description";
				obj[3]="Asia Pacific";
				obj[4]="IndiaMalaysiaSingapore";
				obj[5]="2015";
				marketDataOfferings.add(obj);
				obj = new Object[6];
				obj[0]="Stocks";
				obj[1]="Stocks 2 2015";
				obj[2]="Description";
				obj[3]="Asia Pacific";
				obj[4]="IndiaMalaysiaSingapore";
				obj[5]="2015";
				marketDataOfferings.add(obj);
				*/
				modelAndView.addObject("marketDataOfferings", marketDataOfferings);

				/*tradingApplicationOfferings = new ArrayList<Object[]>();
				obj = new Object[8];
				obj[0]="Equities";
				obj[1]="2010";
				obj[2]="Equity Offering 1";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="Equities";
				obj[1]="2010";
				obj[2]="Equity Offering 2";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="Equities";
				obj[1]="2010";
				obj[2]="Equity Offering 3";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="Equities";
				obj[1]="2011";
				obj[2]="Equity Offering 4";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="Stocks";
				obj[1]="2011";
				obj[2]="Stocks Offering 1";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="Stocks";
				obj[1]="2015";
				obj[2]="Stocks Offering 2";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="FI";
				obj[1]="2015";
				obj[2]="FI Offering 1";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				obj = new Object[8];
				obj[0]="FI";
				obj[1]="2015";
				obj[2]="FI Offering 2";
				obj[3]="Europe";
				obj[4]="Malaysia";
				obj[5]="2010";
				obj[6]="2010";
				obj[7]="2010";
				tradingApplicationOfferings.add(obj);
				*/
				modelAndView.addObject("tradingApplicationOfferings", tradingApplicationOfferings);

				/*
				analyticsApplicationOfferings = new ArrayList<Object[]>();
				obj = new Object[5];
				obj[0]="Anly Appl 1";
				obj[1]="Offering 1";
				obj[2]="Sub 1";
				obj[3]="2010";
				obj[4]="1000$";
				analyticsApplicationOfferings.add(obj);

				obj = new Object[5];
				obj[0]="Anly Appl 1";
				obj[1]="Offering 2";
				obj[2]="Sub 1";
				obj[3]="2010";
				obj[4]="1000$";
				analyticsApplicationOfferings.add(obj);

				obj = new Object[5];
				obj[0]="Anly Appl 1";
				obj[1]="Offering 3";
				obj[2]="Sub 2";
				obj[3]="2010";
				obj[4]="1000$";
				analyticsApplicationOfferings.add(obj);

				obj = new Object[5];
				obj[0]="Anly Appl 2";
				obj[1]="Offering 1";
				obj[2]="Sub 2";
				obj[3]="2010";
				obj[4]="100990$";
				analyticsApplicationOfferings.add(obj);

				obj = new Object[5];
				obj[0]="Anly Appl 3";
				obj[1]="Offering 1";
				obj[2]="Sub 2";
				obj[3]="2010";
				obj[4]="100990$";
				analyticsApplicationOfferings.add(obj);

				obj = new Object[5];
				obj[0]="Anly Appl 3";
				obj[1]="Offering 1";
				obj[2]="Sub 3";
				obj[3]="2010";
				obj[4]="100990$";
				analyticsApplicationOfferings.add(obj);
				*/

				modelAndView.addObject("analyticsApplicationOfferings", analyticsApplicationOfferings);

				/*
				researchReportOfferings = new ArrayList<Object[]>();
				obj = new Object[7];
				obj[0]="Res rep 1";
				obj[1]="Offering 1";
				obj[2]="Sub 1";
				obj[3]="2010";
				obj[4]="1000$";
				obj[5]="Name";
				obj[6]="1000$";
				researchReportOfferings.add(obj);

				obj = new Object[7];
				obj[0]="Res rep 2";
				obj[1]="Offering 2";
				obj[2]="Sub 1";
				obj[3]="2010";
				obj[4]="1000$";
				obj[5]="Name";
				obj[6]="1000$";
				researchReportOfferings.add(obj);

				obj = new Object[7];
				obj[0]="Res rep 2";
				obj[1]="Offering 3";
				obj[2]="Sub 1";
				obj[3]="2010";
				obj[4]="1000$";
				obj[5]="Name";
				obj[6]="1000$";
				researchReportOfferings.add(obj);

				obj = new Object[7];
				obj[0]="Res rep 3";
				obj[1]="Offering 4";
				obj[2]="Sub 1";
				obj[3]="2010";
				obj[4]="1000$";
				obj[5]="Name";
				obj[6]="1000$";
				researchReportOfferings.add(obj);
				*/

				modelAndView.addObject("researchReportOfferings", researchReportOfferings);
				modelAndView.addObject("vendorAwardDetails", vendorAwardDetails);
			}else if(user.getConsumer() != null) {
				if(user.getConsumer().getCountryOfIncorporation() != 0) {
					country = (Country)referenceDataService.getModelObjectById(Country.class,
							user.getConsumer().getCountryOfIncorporation());
				}
			}
			modelAndView.addObject("user", user);
			modelAndView.addObject("country", country);
		} catch(ApplicationException exp){
			logger.error("Error Reading User Summary Profile", exp);
			modelAndView.addObject("lastActionError", exp.getMessage());
		}
		modelAndView.addObject("nav", nav);
		logger.debug("Leaving AdminController : adminUserSummaryProfile");
		return modelAndView;
	}
	
	@RequestMapping(value="adminUserLogin", method=RequestMethod.GET)
	public String adminUserLogin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userName", required = true) String userName) {
		logger.debug("Entering AdminController : adminUserLogin for {}", userName);
		try {
			FinVendorUser user = userService.getUserDetailsByUsername(userName);
			String userRole = null;
			if(user.getVendor() != null) {
				userRole = RequestConstans.Roles.ROLE_VENDOR;
			}else {
				userRole = RequestConstans.Roles.ROLE_CONSUMER;
			}
			User appUser = new User(userName, user.getPassword(), AuthorityUtils.createAuthorityList(userRole));					
			Authentication authentication = new UsernamePasswordAuthenticationToken(appUser, null,
				    AuthorityUtils.createAuthorityList(userRole));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				request.getSession(true).setAttribute("loggedInUser", appUser);
		} catch(Exception exp){
			logger.error("Error Reading User Summary Profile", exp);
		}		
		logger.debug("Leaving AdminController : adminUserLogin");
		return "redirect:/welcometodashboards";
	}
		
	private ReferenceData getReferenceDataFromMapping(String dataMapping) {
		ReferenceData refData = new ReferenceData();
		String[] dataDetails = dataMapping.split(";");
		refData.setTableName(dataDetails[0]);
		Map<String, TableColumn> fieldTypeMap = new HashMap<String, TableColumn>();
		String dataTypes = dataDetails[1];
		StringTokenizer tokenizer = new StringTokenizer(dataTypes, ",");
		StringBuilder tableColumns = new StringBuilder(25);
		List<TableColumn> columnList = new ArrayList<TableColumn>();
		while(tokenizer.hasMoreTokens()) {
			String[] columnProperties = tokenizer.nextToken().split(":");
			tableColumns.append(columnProperties[0]);
			tableColumns.append(",");
			TableColumn column = new TableColumn();
			column.setColumnName(columnProperties[0]);
			column.setColumnType(columnProperties[1]);
			if(columnProperties.length > 2) {
				String pkOrFk = columnProperties[2];
				if (ReferenceData.PRIMARY_KEY.equals(pkOrFk)) {
					column.setPrimaryKey(true);
					if(columnProperties.length > 3) {
						column.setAutoIncrement(true);
					}					
				}else if (ReferenceData.FOREIGN_KEY.equals(pkOrFk)) {
					column.setForeignKey(true);
					column.setForeignKeyTableName(columnProperties[3]);
				} else if (ReferenceData.COLUMNTYPE_VARCHAR.equals(column.getColumnType())) {
					column.setColumnLength(Integer.parseInt(columnProperties[2]));
				}
			}
			columnList.add(column);
			fieldTypeMap.put(columnProperties[0], column);
		}
		refData.setFieldTypeMap(fieldTypeMap);
		refData.setColumnList(columnList);
		refData.setColumnNames(tableColumns.substring(0, tableColumns.length() - 1));
		return refData;
	}
}
