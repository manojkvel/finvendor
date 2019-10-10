package com.finvendor.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rayulu vemula
 *
 */
public class RequestConstans {
	
	public interface Anonymous{
		public static final String ANONYMOUS_USER="anonymousUser";
	}
	
	public interface Home{
		public static final String HOME_PAGE="homePage";
		public static final String My_HOME = "myhome";
	}
	
	
	
	public interface CostValues{
		public static final String $200="Rupees";
		public static final String $300="Sing Dollar";
	}
	
	
	public interface Document{
		public static final String DOCUMENT_DOWNLOAD="downloaddocument";
	}
	
	public interface ConstantValues{
		public static final String ZERO_VALUE="0";
	}
	
	public interface MAIL{
		public static final String MAIL_SEND="sendMail";
	}
	
	public interface Login{
		public static final String LOGIN="login";
		public static final String LOGOUT="logout";
		public static final String MY_HOME_PAGE="myhomepage";
		public static final String MY_VIEW_PAGE="myViewpage";
		public static final String LOGINVALIDATION="checkUserLoginValidation";
		public static final String WELCOME="welcometodashboards";
		public static final String ADMIN_INFO="admin";
		public static final String VENDOR_INFO ="vendor";
		public static final String CONSUMER_INFO ="consumer";
		public static final String SIGNIN_FAILED="loginfailed";
		public static final String HOME = "index";
		public static final String FORGOT_PASSWORD="forgotPassword";
		public static final String RESET_PASSWORD="resetPassword";
		public static final String SITE_MAP="sitemap";
		public static final String PRIVACY="privacy";
		public static final String DISCLAIMER="disclaimer";
	}
	
	public interface Admin {
		public static final String ADMIN_EDIT_REF_DATA = "adminEditRefData";
		public static final String ADMIN_ACTION_EDIT_REF_DATA = "adminActionEditRefData";
		public static final String ADMIN_ACTION_ADD_REF_DATA = "adminActionAddRefData";
		public static final String ADMIN_USER_SUMMARY_PROFILE = "adminUserSummaryProfile";
	}
	
	public interface Roles{
		public static final String ROLE_VENDOR="ROLE_VENDOR";
		public static final String ROLE_CONSUMER="ROLE_CONSUMER";
		public static final String ROLE_ADMIN="ROLE_ADMIN";
		public static final String ROLE_VENDOR_VALUE="2";
		public static final String ROLE_CONSUMER_VALUE="3";
	}

	public interface Register{
		public static final String REGISTER="register";
		public static final String REGISTERATION="registration";
		public static final String REGISTERUSERCHECK = "checkregisteruservalidation";
		public static final String EMPTY = "empty";
		public static final String EMAILVALIDATION = "emailvalidation";
		public static final String CHECKEMAILEXIST = "checkemailvalidationexist";
		
		
		public static final String PHONE_NUMBER_VALIDATION = "phoneNumValidation";
		public static final String COMPANY_URL_VALIDATION = "validateCompanyURL";
		public static final String COMPANY_DESIGNATION= "validateDesignation";
		
		public static final String USER_VERIFICATION_PAGE = "verifyUser";
	}
	
	public interface MarketAggregators{
		public static final String MARKETAGGREGATORS="marketaggregators";
		public static final String SINGLE_ASSET_CLASS_SEARCH_RESULT = "singleassetsearchresult";
		public static final String MULTI_ASSET_CLASS_SEARCH_RESULT = "multiassetsearchresult";
		public static final String LOAD_REGION_NAME_TYPES = "loadRegionNameTypes";
		public static final String LOAD_SECURITY_TYPES = "loadSecurityTypes";
		public static final String LOAD_EQUITIES_SECURITY_TYPES = "loadEquitiesSecurityTypes";
		public static final String LOAD_FI_SECURITY_TYPES = "loadFISecurityTypes";
		public static final String LOAD_INDICES_SECURITY_TYPES = "loadIndicesSecurityTypes";
		public static final String LOAD_COUNTRY_TYPES = "loadCountryTypes";
		public static final String LOAD_COUNTRY_TYPES_INFO = "loadCountryTypesInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_MULTI = "loadCountryTypesInfoMulti";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET = "loadCountryListAssetInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET_FI = "loadCountryListAssetInfoFI";
		public static final String LOAD_EXCHANGE_TYPES = "loadExchangeTypes";
		public static final String LOAD_EXCHANGE_TYPES_ASSET = "loadExchangeAssetList";
		public static final String LOAD_EXCHANGE_TYPES_ASSET_FI = "loadExchangeAssetListFI";
	}
	
	public interface TradingApplication{
		public static final String TRADING_APPLICATION_INDEX_PAGE="tradingapplicationvendor";
		
		public static final String SINGLE_ASSET_CLASS_SEARCH_RESULT = "singleassetsearchresult";
		public static final String MULTI_ASSET_CLASS_SEARCH_RESULT = "tradingmultiassetsearchresult";
		public static final String LOAD_REGION_NAME_TYPES = "loadRegionNameTypes";
		public static final String LOAD_SECURITY_TYPES = "loadSecurityTypes";
		public static final String LOAD_EQUITIES_SECURITY_TYPES = "loadEquitiesSecurityTypes";
		public static final String LOAD_FI_SECURITY_TYPES = "loadFISecurityTypes";
		public static final String LOAD_INDICES_SECURITY_TYPES = "loadIndicesSecurityTypes";
		public static final String LOAD_COUNTRY_TYPES = "loadCountryTypes";
		public static final String LOAD_COUNTRY_TYPES_INFO = "loadCountryTypesInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_MULTI = "loadCountryTypesInfoMulti";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET = "loadCountryListAssetInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET_FI = "loadCountryListAssetInfoFI";
		public static final String LOAD_EXCHANGE_TYPES = "loadExchangeTypes";
		public static final String LOAD_EXCHANGE_TYPES_ASSET = "loadExchangeAssetList";
		public static final String LOAD_EXCHANGE_TYPES_ASSET_FI = "loadExchangeAssetListFI";
	}
	
	public interface FinancialAnalyticsApplication{
		public static final String FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE="financialanalyticsapplicationvendor";
		
		public static final String SINGLE_ASSET_CLASS_SEARCH_RESULT = "singleassetsearchresult";
		public static final String MULTI_ASSET_CLASS_SEARCH_RESULT = "financialmultiassetsearchresult";
		public static final String LOAD_REGION_NAME_TYPES = "loadRegionNameTypes";
		public static final String LOAD_SECURITY_TYPES = "loadSecurityTypes";
		public static final String LOAD_EQUITIES_SECURITY_TYPES = "loadEquitiesSecurityTypes";
		public static final String LOAD_FI_SECURITY_TYPES = "loadFISecurityTypes";
		public static final String LOAD_INDICES_SECURITY_TYPES = "loadIndicesSecurityTypes";
		public static final String LOAD_COUNTRY_TYPES = "loadCountryTypes";
		public static final String LOAD_COUNTRY_TYPES_INFO = "loadCountryTypesInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_MULTI = "loadCountryTypesInfoMulti";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET = "loadCountryListAssetInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET_FI = "loadCountryListAssetInfoFI";
		public static final String LOAD_EXCHANGE_TYPES = "loadExchangeTypes";
		public static final String LOAD_EXCHANGE_TYPES_ASSET = "loadExchangeAssetList";
		public static final String LOAD_EXCHANGE_TYPES_ASSET_FI = "loadExchangeAssetListFI";
	}
	
	public interface ResearchReportProviders{
		public static final String RESEARCH_REPORT_PROVIDERS_INDEX_PAGE="researchreportprovidersvendor";
		
		public static final String SINGLE_ASSET_CLASS_SEARCH_RESULT = "singleassetsearchresult";
		public static final String MULTI_ASSET_CLASS_SEARCH_RESULT = "researchReportmultiassetsearchresult";
		public static final String LOAD_REGION_NAME_TYPES = "loadRegionNameTypes";
		public static final String LOAD_SECURITY_TYPES = "loadSecurityTypes";
		public static final String LOAD_EQUITIES_SECURITY_TYPES = "loadEquitiesSecurityTypes";
		public static final String LOAD_FI_SECURITY_TYPES = "loadFISecurityTypes";
		public static final String LOAD_INDICES_SECURITY_TYPES = "loadIndicesSecurityTypes";
		public static final String LOAD_COUNTRY_TYPES = "loadCountryTypes";
		public static final String LOAD_COUNTRY_TYPES_INFO = "loadCountryTypesInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_MULTI = "loadCountryTypesInfoMulti";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET = "loadCountryListAssetInfo";
		public static final String LOAD_COUNTRY_TYPES_INFO_ASSET_FI = "loadCountryListAssetInfoFI";
		public static final String LOAD_EXCHANGE_TYPES = "loadExchangeTypes";
		public static final String LOAD_EXCHANGE_TYPES_ASSET = "loadExchangeAssetList";
		public static final String LOAD_EXCHANGE_TYPES_ASSET_FI = "loadExchangeAssetListFI";
	}
	
	public interface Vendor{
		//My profile
		public static final String VENDOR_RFP_INBOX="rfpinbox";
		public static final String VENDOR_GET_REGION="getRegion";
		public static final String VENDOR_SEARCH_DATABUYER="vendorsearchdatabuyer";
		public static final String ADD_VENDOR_DATACOVERAGE = "addVendorDataCoverage";
		public static final String ADD_VENDOR_DATADISTRIBUTION = "addVendorDataDistribution";
		public static final String ADD_VENDOR_ANALYSTPROFILE = "addAnalystProfile";
		
		public static final String ADD_VENDOR_TRADINGCAPABILITIESSUPPORTED = "addTradingCapabilitiesSupported";
		public static final String ADD_VENDOR_TRADINGSOFTWAREDETAILS = "addTradingSoftwareDetails";
		public static final String ADD_VENDOR_ANALYTICSFEATURESSUPPORTED = "addAnalyticsfeaturesSupported";
		public static final String ADD_VENDOR_ANALYTICSSOFTWAREDETAILS = "addAnalyticsSoftwareDetails";
		public static final String ADD_VENDOR_RESEARCHCOVERAGE = "addResearchCoverage";
		public static final String ADD_VENDOR_RESEARCHDETAILS = "addResearchDetails";
		
		public static final String DELETE_VENDOR_DATACOVERAGE = "deleteVendorDataCoverage";
		public static final String DELETE_VENDOR_DATADISTRIBUTION = "deleteVendorDataDistribution";
		
		public static final String VENDOR_MY_PROFILE="vendormyprofile";
		public static final String VENDOR_MY_OFFERINGS="vendormyofferings";
		public static final String VENDOR_SOLUTION="vendorsolutions";
		public static final String MY_OFFERTINGS_FILE="vendormyofferingfiles";
		public static final String VENDOR_SPECIFIC_SOLUTION_LIST="vendorSpecificSolutionList";
		
		public static final String LOAD_VENDOR_SECURITY_TYPES = "loadVendorSecurityTypes";
		public static final String LOAD_VENDOR_SECURITY_AWARD_TYPES = "loadSecurityAwardTypes";
		public static final String LOAD_VENDOR_SECURITY_DISTRI_TYPES = "loadSecurityDistriTypes";
		public static final String LOAD_VENDOR_FOCUS_SECURITY_DISTRI_TYPES = "loadFocusSecurityTypes";
		public static final String LOAD_VENDOR_FOCUS_CREATE_OFFERINGS = "createOfferings";
		public static final String LOAD_VENDOR_FOCUS_ADD_OFFERING_FILES = "addOfferingFiles";
		public static final String LOAD_VENDOR_FOCUS_ADD_FIELD_TO_FILE = "addFieldsToFile";
		
		
		public static final String DELETE_OFFERING = "deleteOffering";
		public static final String DELETE_OFFERING_FILE = "deleteRecordFile";
		public static final String DELETE_FIELDS_FILE = "deleteFieldsFile";
		public static final String LIST_OFFERING_FIELD_DATA = "listOfferingFieldData";
		public static final String LIST_OFFERING_DATA = "listOfferingData";
		public static final String DELETE_RECORD_OFFERING = "deleteRecordOffering";
		
		
		public static final String UPDATE_VENDOR_PERSONAL_INFO_TAB = "updateVendorPersonalTabInfo";
		public static final String UPDATE_VENDOR_SUPPORT_COVEAGE_TAB = "updateVendorSupportCoverageInfo";
		
		public static final String DELETE_VENDOR_SOLUTION = "deleteVendorSolution";
		public static final String DELETE_RECORD = "deleteRecord";
		public static final String ADD_VENDOR_SOLUTION = "addVendorSolution";
		public static final String LIST_VENDOR_SOLUTION = "listVendorSolution";
		
		public static final String PERSONALDETAILS = "personaldetails";
		public static final String SUPPORTCOVERAGE = "supportcoverage";
		public static final String SUPPORTDETAILS= "supportdetails";
		public static final String AWARDDETAILS= "awarddetails";
		public static final String DATADISTRIBUTION = "datadistribution";
		public static final String SEARCHDATABUYERS = "searchdatabuyers";
		public static final String MYRFP = "myrfp";
		
		// Vendor tags info
		public static final String DATA_AGGREGATOR = "Data Aggregator vendor";
		public static final String TRADING_APPLICATION = "Trading Application vendor";
		public static final String ANALYTICS_APPLICATION = "Analytics Application vendor";
		public static final String RESEARCH_REPORT = "Research Reporting vendor";
		public static final String TRADING_CAPABILITIES_SUPPORTED_OFFERING = "tradingCapabilitiesSupportedOffering";
		public static final String RESEARCH_REPORTING_VENDOR_OFFERING = "researchReportingVendorOffering";
		//My Offerings
		public static final String VENDOR_MYOFFERINGS_AS_DATA_AGGREGATOR_VENDOR="vendormyofferingsasaggregatorvendor";
		public static final String VENDOR_MYOFFERINGS_AS_TRADING_APPLICATION_VENDOR="vendormyofferingsastradingapplicationvendor";
		public static final String VENDOR_MYOFFERINGS_AS_ANALYTICS_APPLICATION_VENDOR="vendormyofferingsasanalyticsapplicationvendor";
		public static final String VENDOR_MYOFFERINGS_AS_RESEARCH_REPORTING_VENDOR="vendormyofferingsasresearchreportingvendor";
		
		//Market Data Aggregator my Offerings
		public static final String VENDOR_MYOFFERINGS_DATACOVERAGE="vendormyofferingsdatacoverage";
		public static final String VENDOR_MYOFFERINGS_DATADICTIONARY="vendorMyOfferingsDataDictionary";
		public static final String VENDOR_MYOFFERINGS_DATA_DISTEIBUTION="vendormyofferingsdatadistribution";
		public static final String UPDATE_VENDOR_AWARD_DETAILS_TAB = "updateVendorAwardDetails";
		public static final String UPDATE_VENDOR_DATA_SEARCH_BUYERS = "updateVendorSearchDataBuyers";
		public static final String UPDATE_VENDOR_MYOFFEINGS_DATA_COVEAGE_TAB = "updateVendorOfferingDataCoverageInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_DATA_DISTRIBUTION_TAB = "updateVendorOfferingDataDistributionInfo";
		
		public static final String UPDATE_VENDOR_MYOFFEINGS_TRADING_CAPABILITIES_SUPPORTED_TAB = "updateVendorOfferingTradingCapabilitiesSupportedInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_TRADING_SOFT_WAREDETAILS_TAB = "updateVendorOfferingTradingSoftwareDetailsInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_ANALYTICS_FEATURES_SUPPORTED_TAB = "updateVendorOfferingAnalyticsFeaturesSupportedInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_AnalyticsSoftwareDetails_TAB = "updateVendorOfferingAnalyticsSoftwareDetailsInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_RESEARCH_COVERAGE_TAB = "updateVendorOfferingResearchCoverageInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_RESEARCH_DETAILS_TAB = "updateVendorOfferingResearchDetailsInfo";
		public static final String UPDATE_VENDOR_MYOFFEINGS_ANALYST_PROFILE_TAB = "updateVendorOfferingAnalystProfileInfo";
		
		
		//Trading Application my Offerings
		public static final String  TRADING_CAPABILITIES_SUPPORTED="tradingcapabilitiessupported";
		public static final String  TRADING_SOFTWARE_DETAILS="tradingsoftwaredetails";
		 
		//Analytics Application  my Offerings
		public static final String  ANALYTICS_FEATURES_SUPPORTED="analyticsfeaturessupported";
		public static final String  ANALYTICS_SOFTWARE_DETAILS="analyticssoftwaredetails";

		//Analytics Application  my Offerings
		public static final String  RESEARCH_COVERAGE="researchcoverage";
		public static final String  RESEARCH_DETAILS="researchdetails";
		public static final String  ANALYTST_PROFILE="analystprofile";

		public static final String CREATE_TREE = "createTree";

		

		
	}
	
	public interface Consumer{
		// My Profile--:
		public static final String VENDOR_RFP_INBOX="rfpinbox";
		public static final String CONSUMER_MY_PROFILE="consumermyprofile";
		public static final String CONSUMER_MY_OFFERINGS="consumermyofferings";
		
		public static final String CONSUMER_INVITE_AN_RFP="consumermarketdatainviteanrfp";
		
		// Invite RFP start here---
		public static final String CONSUMER_MARKET_DATANEEDS_INVITE_AN_RFP="consumermarketdataneedsinviteanrfp";
		public static final String CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP="consumertradingapplicationinviteanrfp";
		public static final String CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP="consumeranalyticsapplicationinviteanrfp";
		public static final String CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP="consumerresearchreportinviteanrfp";
		
		// Invite RFP end here----
		
		public static final String LOAD_CONSUMER_SECURITY_TYPES = "loadConsumerSecurityTypes";
		public static final String LOAD_CONSUMER_SECURITY_DATADELIVERY_TYPES = "loadConsumerDataDeliverySecurityTypes";
		public static final String LOAD_VENDOR_SECURITY_DISTRI_TYPES = "loadSecurityDistriTypes";
		public static final String LOAD_COST_PREFERENCE_SECURITY_DISTRI_TYPES = "loadCostPreferenceSecurityTypes";
		
		public static final String COMPANYDETAILS = "companydetails";
		
		public static final String MY_BUSINESS_NEEDS = "mybusinessneeds";
		
		public static final String MARKET_DATA_NEEDS = "marketdataneeds";
		public static final String TRADING_APPLICATION_NEEDS = "tradingapplicationneeds";
		public static final String ANALYTICS_APPLICATION_NEEDS = "analyticsapplicationneeds";
		public static final String RESEARCH_REPORT_NEEDS = "researchreportneeds";
		
		
		
		public static final String MYVENDORPREFERENCE = "myvendorpreference";
		
		public static final String MY_PREFERENCE_MARKET_DATA_NEEDS = "mypreferencemarketdataneeds";
		public static final String MY_PREFERENCE_TRADING_APPLICATION_NEEDS = "mypreferencetradingapplicationneeds";
		public static final String MY_PREFERENCE_ANALYTICS_APPLICATION_NEEDS = "mypreferenceanalyticsapplicationneeds";
		public static final String MY_PREFERENCE_RESEARCH_REPORT_NEEDS = "mypreferenceresearchreportneeds";
		
		public static final String DATADELIVERY_COSTPREFERENCE = "datadeliverycostpreference";
		 
		public static final String COST_PREFERENCE_MARKET_DATA_NEEDS = "costpreferencemarketdataneeds";
		public static final String COST_PREFERENCE_TRADING_APPLICATION_NEEDS = "costpreferenceapplicationneeds";
		public static final String COST_PREFERENCE_ANALYTICS_APPLICATION_NEEDS = "costpreferenceanalyticsapplicationneeds";
		public static final String COST_PREFERENCE_RESEARCH_REPORT_NEEDS = "costpreferencesearchreportneeds";
		
		//My Offerings ----:
		
		public static final String CONSUMER_MYOFFER_MARKET_DATA_NEEDS = "consumermyoffermarketdataneeds";
		public static final String CONSUMER_MYOFFER_TRADING_APPLICATION_NEEDS = "consumermyoffertradingapplicationneeds";
		public static final String CONSUMER_MYOFFER_ANALYTICS_APPLICATION_NEEDS = "consumermyofferanalyticsapplicationneeds";
		public static final String CONSUMER_MYOFFER_RESEARCH_REPORT_NEEDS = "consumermyofferresearchreportneeds";
		
		// Invite an RFP
		
		public static final String CONSUMER_INVITE_RFP_MARKET_DATA_NEEDS = "consumerinviteanrfpmarketdataneeds";
		public static final String CONSUMER_INVITE_RFP_TRADING_APPLICATION_NEEDS = "consumerinviteanrfptradingapplicationneeds";
		public static final String CONSUMER_INVITE_RFP_ANALYTICS_APPLICATION_NEEDS = "consumerinviteanrfpanalyticsapplicationneeds";
		public static final String CONSUMER_INVITE_RFP_RESEARCH_REPORT_NEEDS = "consumerinviteanrfpsearchreportneeds";
		
		
		public static final String CONSUMER_WRITE_AN_RFP_TAB1 = "consumerwriteanrfp1";
		public static final String CONSUMER_TRACK_RFP_RESPONSE1 = "consumerrfpresponse1";
		public static final String CONSUMER_SHORTLISTED_VENDORS1 = "consumershortlistedvendor1";
		public static final String CONSUMER_FINAL_VENDOR1 = "consumerfinalvendor1";
		
		//Company Type
		public static final String FIN_CONSUMER_COMPANY_SELL_SIDE = "Financial Firm - Sell side";
		public static final String FIN_CONSUMER_COMPANY_BUY_SIDE = "Financial Firm - Buy side"; 
		public static final String FIN_CONSUMER_COMPANY_OTHERS = "Financial Firm - Others";
		public static final String CONSUMER_UNIVERSITY = "University/College";
		public static final String CONSUMER_OTHER_FIRM = "Other Firm";
		
		
	}
		
	public static final Map<String, String> reqParamDescriptionMap = new HashMap<String, String>();
	
	static{
		reqParamDescriptionMap.put("SOLUTIONS_mdvad", "Market Data Vendors");
		reqParamDescriptionMap.put("SOLUTIONS_tavd", "Trading Application Vendors");
		reqParamDescriptionMap.put("SOLUTIONS_faavd", "Analytics Application Vendors");
		reqParamDescriptionMap.put("SOLUTIONS_frrpd", "Research Report Providers");
		reqParamDescriptionMap.put("SOLUTIONS_afvd", "Advanced Financial Vendors Directory");
		reqParamDescriptionMap.put("SERVICES_dadd", "Data Aggregator Services");
		reqParamDescriptionMap.put("SERVICES_tapdd", "Trading Application Services");
		reqParamDescriptionMap.put("SERVICES_aapdd", "Analytics Application Services");
		reqParamDescriptionMap.put("SERVICES_rrpdd", "Research Report Services");
		reqParamDescriptionMap.put("SERVICES_ic", "IT Consulting");
		reqParamDescriptionMap.put("RESOURCES_b", "Brochures");
		reqParamDescriptionMap.put("RESOURCES_w", "Whitepapers");
		reqParamDescriptionMap.put("RESOURCES_blgs", "Blogs");
		reqParamDescriptionMap.put("RESOURCES_cs", "Case Studies");
		reqParamDescriptionMap.put("RESOURCES_s", "Spotlights");	
		
		/* Admin Menu entries */
		reqParamDescriptionMap.put("ASSET_ac", "Asset Class");
		reqParamDescriptionMap.put("ASSET_sec", "Security");
		reqParamDescriptionMap.put("GEOGRAPHICAL_r", "Region");
		reqParamDescriptionMap.put("GEOGRAPHICAL_c", "Country");
		reqParamDescriptionMap.put("GEOGRAPHICAL_e", "Exchange");
		reqParamDescriptionMap.put("GEOGRAPHICAL_cur", "Currency");
		reqParamDescriptionMap.put("OTHER_dm", "Distribution Mode");
		reqParamDescriptionMap.put("OTHER_supp", "Support");
	}
	
	
	public static String[] ASSETSLIST = { "Equities", "FI", "Indices", "Derivatives", "FX", "AI", "Misc" };
	
	public static final int MAX_UNSUCCESSFUL_ATTEMPTS = 5;
	
	public static final String INVALID_USER = "User Account is not registered";
	public static final String INVALID_PASSWORD = "Invalid Password entered.\nPlease note account will be locked after " + MAX_UNSUCCESSFUL_ATTEMPTS + " unsuccessful attempts.";
	public static final String ACCOUNT_DISABLED = "User Account is disabled";
	public static final String CHANGE_PASSWORD = "Please Change your password";
	public static final String LOGIN_AFTER_CHANGE_PASSWORD = "Change Password is successful. Please login again with new password.";
	public static final String UNSUCCESSFUL_ATTEMPTS = "" + MAX_UNSUCCESSFUL_ATTEMPTS + " unsuccessfull password attempts. Account is disabled";
	public static final int REGISTRATION_LINK_EXPIRY = 48;
	
	public static final String INDEPENDENT_RESEARCH_ANALYST = "Independent Research Analyst";
	public static final String RESEARCH_BROKER = "Research Broker";
	
	public static final String INDIVIDUAL_INVESTOR = "Individual Investor";
	public static final String UNIVERSITY_OR_PHD_STUDENT = "University/Phd Student";
		
}
