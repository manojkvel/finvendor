package com.finvendor.common.exception;

public enum ExceptionEnum {
	ADD_WATCHLIST("fv-0001", "Internal error - Unable to add Company WatchList, Please contact Finvendor admin for support!!"),
	FIND_WATCHLIST("fv-0002", "Internal error - Unable to find Company WatchList for given user, Please contact Finvendor admin for support!!"),
	
	EQUITY_RESEARCH_REPORT("fv-0003", "Internal error - Unable to find Equity research report, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_RESULT_DASHBOARD("fv-0004", "Internal error - Unable to prepare Equity research report dashboard result, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_REPORT_DOWNLOAD("fv-0005", "Internal error - Unable to download Equity research report, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_RECORD_STAT("fv-0006", "Internal error - Unable find Equity research report record statistics, Please contact Finvendor admin for support!!"),
	
	HOME_PAGE("fv-0007", "Internal error - Unable find company homepage search hint, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_FILTER("fv-0006", "Internal error - Unable to prepare Equity research filter, Please contact Finvendor admin for support!!"),
	COMPANY_DETAILS("fv-0008", "Internal error - Unable to prepare ResearchReportFor details, Please contact Finvendor admin for support!!"),
	
	COMPANY_PROFILE("fv-0009", "Internal error - Unable to prepare company profile, Please contact Finvendor admin for support!!"),
	COMPANY_RECORD_STATS("fv-0010", "Internal error - Unable to prepare company record statistics, Please contact Finvendor admin for support!!"),
	COMPANY_RESEARCH_REPORT("fv-0011", "Internal error - Unable to prepare company research report, Please contact Finvendor admin for support!!"),
	
	ADD_COMPANY_PRICE_ALERT("fv-0012", "Internal error - Unable to add company price alert, Please contact Finvendor admin for support!!"),
	FIND_ALL_COMPANY_PRICE_ALERT("fv-0013", "Internal error - Unable to find all company price alert, Please contact Finvendor admin for support!!"),
	FIND_USER_FROM_SESSION("fv-0014", "Internal error - Unable to find logged in user from current session, Please contact Finvendor admin for support!!"),
	UPDATE_PRICE("fv-0015", "Internal error - Unable to update current market price, Please contact Finvendor admin for support!!"),
	
	DELETE_WATCHLIST("fv-0016", "Internal error - Unable to delete company watchlist, Please contact Finvendor admin for support!!"),
	UPDATE_COMPANY_PRICE_ALERT("fv-0017", "Internal error - Unable to update company price alert, Please contact Finvendor admin for support!!"),
	DELETE_COMPANY_PRICE_ALERT("fv-0018", "Internal error - Unable to delete company price alert, Please contact Finvendor admin for support!!"),
	FIND_COMPANY_PRICE_ALERT("fv-0018", "Internal error - Unable to delete company price alert, Please contact Finvendor admin for support!!"),
	
	PRICE_MAIL("fv-0019", "Internal error - Unable to send mail to user for given company, Please contact Finvendor admin for support!!"),
	RESEARCH_REPORT_MAIL("fv-0020", "Internal error - Unable to send research report mail to user for given company, Please contact Finvendor admin for support!!"),
	REQUEST_METRICS("fv-0021", "Internal error - Unable to get metrics, Please contact Finvendor admin for support!!"),
	REQUEST_ANALYST_TYPE("fv-0022", "Internal error - Unable to get Analyst Type, Please contact Finvendor admin for support!!"),
	REQUEST_UPDATE_ANALYST_TYPE("fv-0022", "Internal error - Unable to update Analyst Type, Please contact Finvendor admin for support!!"),
	CONSUMER_ANALYTICS_EQTY_RESEARCH("fv-0023", "Internal error - Unable to get Consumer Analytics for Equity Research type, Please contact Finvendor admin for support!!"),
	CONSUMER_ANALYTICS_DOWNLOAD("fv-0024", "Internal error - Unable to download Consumer Analytics for Equity Research type, Please contact Finvendor admin for support!!"),
	BHAV_COPY_DOWNLOAD("fv-0024", "Internal error - Unable to download Bhav Copy, Please contact Finvendor admin for support!!"),
	MARKETS_PERSIST("fv-0024", "Internal error - Unable to Persist Bhav Copy, Please contact Finvendor admin for support!!"),
    MARKETS("fv-0025", "Internal error - Unable to Get Markets data, Please contact Finvendor admin for support!!"),
    MARKETS_RECORD_STATS("fv-0026", "Internal error - Unable to Get Markets Record Stats, Please contact Finvendor admin for support!!"),
    INDEX_NAMES("fv-0027", "Internal error - Unable to get Index Names, Please contact Finvendor admin for support!!"),
    INDEX_SUMMARY("fv-0028", "Internal error - Unable to get Index Summary, Please contact Finvendor admin for support!!"),
    MARKET_ANALYTICS("fv-0029", "Internal error - Unable to get Market Analytics data, Please contact Finvendor admin for support!!"),
	NIFTY_INDICES_PERSIST("fv-0030", "Internal error - Unable to Persist NIFTY Indices, Please contact Finvendor admin for support!!"),

	SECTOR_RESEARCH_FILTER("fv-0031", "Internal error - Unable to filter value for Sector Research, Please contact Finvendor admin for support!!"),
	SECTOR_RECORD_STATS("fv-0032", "Internal error - Unable to get record stats for Sector Research, Please contact Finvendor admin for support!!"),
	SECTOR_RESEARCH_REPORT("fv-0033", "Internal error - Unable to get Sector Research Report, Please contact Finvendor admin for support!!"),
	MF_PROFILE("fv-0034", "Internal error - Unable to get Mutual Fund Profile data, Please contact Finvendor admin for support!!"),
	MARQUEE_ERROR("fv-0035", "Internal error - Unable to get Marquee data, Please contact Finvendor admin for support!!"),
	MF_PERSIST("fv-0036", "Internal error - Unable to isnert Mututal Fund file data, Please contact Finvendor admin for support!!"),

	VO_OP("fv-0037", "Internal error - Unable to perform VO operation like Save|Delete|Update|Find|FindAll, Please contact Finvendor admin for support!!"),

	COMPANY_PROFILE_EARNING_PREVIEW("fv-0038", "Internal error - Unable to get earning preview, Please contact Finvendor admin for support!!"),
	COMPANY_PROFILE_COMPANY_NEWS("fv-0039", "Internal error - Unable to get company news, Please contact Finvendor admin for support!!"),
	COMPANY_PROFILE_CORP_ACTION("fv-0040", "Internal error - Unable to get corporate action, Please contact Finvendor admin for support!!"),
	COMPANY_PROFILE_CALENDAR("fv-0041", "Internal error - Unable to get calendar data, Please contact Finvendor admin for support!!"),
	COMPANY_PROFILE_PRICE_HISTORY("fv-0042", "Internal error - Unable to get price history, Please contact Finvendor admin for support!!"),
	DATA_FEED("fv-0043", "Internal error - Unable to perform data feed operation, Please contact Finvendor admin for support!!"),
	CELEBRITY_INVESTOR_STRATEGY_RECORD_STATS("fv-0045", "Internal error - Unable to find celebrity investor strategy record stats, Please contact Finvendor admin for support!!"),
	CELEBRITY_INVESTOR_STRATEGY("fv-0046", "Internal error - Unable to find celebrity investor strategy, Please contact Finvendor admin for support!!"),
	CELEBRITY_INVESTOR_STRATEGY_TOOL_TIPS("fv-0047", "Internal error - Unable to find celebrity investor strategy tool tips, Please contact Finvendor admin for support!!"),
	CUSTOM_STRATEGY("fv-0048", "Internal error - Unable to find custom strategy, Please contact Finvendor admin for support!!"),
	CUSTOM_STRATEGY_DATA_FEED("fv-0049", "Internal error - Unable to feed custom strategy data, Please contact Finvendor admin for support!!"),
	CUSTOM_STRATEGY_DATA("fv-0050", "Internal error - Unable to feed custom screener records, Please contact Finvendor admin for support!!"),
	REPORT("fv-0051", "Internal error - Unable to send report to users, Please contact Finvendor admin for support!!"),
	USER_LOGIN("fv-0052", "Internal error - Login failed, Please contact Finvendor admin for support!!"),
	SUBSCRIPTION("fv-0053", "Internal error - Subscription failed, Please contact Finvendor admin for support!!"),
	EXAMPLE("fv-0054", "Internal error - Example operation failed, Please contact Finvendor admin for support!!"),
	SUBSCRIPTION_VALIDATION_FAILED("fv-0055", "Validation failed"),
	GENERAL_ERROR("fv-9999", "General error - Something went wrong, please contact admin for support"),
	SUBSCRIPTIONS_DOWNLOAD("fv-0056", "Internal error - Unable to download subscriptions, Please contact Finvendor admin for support!!"),


	;
	
	private String code;
	private String userMessage;

	ExceptionEnum(String code, String userMessage) {
		this.code = code;
		this.userMessage = userMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserMessage() {
		return userMessage;
	}
}
