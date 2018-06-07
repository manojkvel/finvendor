package com.finvendor.common.exception;

public enum ExceptionEnum {
	ADD_WATCHLIST("FV0001", "Internal error - Unable to add Company WatchList, Please contact Finvendor admin for support!!"), 
	FIND_WATCHLIST("FV0002", "Internal error - Unable to find Company WatchList for given user, Please contact Finvendor admin for support!!"),
	
	EQUITY_RESEARCH_REPORT("FV0003", "Internal error - Unable to find Equity research report, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_RESULT_DASHBOARD("FV0004", "Internal error - Unable to prepare Equity research report dashboard result, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_REPORT_DOWNLOAD("FV0005", "Internal error - Unable to download Equity research report, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_RECORD_STAT("FV0006", "Internal error - Unable find Equity research report record statistics, Please contact Finvendor admin for support!!"),
	
	HOME_PAGE("FV0007", "Internal error - Unable find company homepage search hint, Please contact Finvendor admin for support!!"),
	EQUITY_RESEARCH_FILTER("FV0006", "Internal error - Unable to prepare Equity research filter, Please contact Finvendor admin for support!!"),	
	COMPANY_DETAILS("FV0008", "Internal error - Unable to prepare company details, Please contact Finvendor admin for support!!"),
	
	COMPANY_PROFILE("FV0009", "Internal error - Unable to prepare company profile, Please contact Finvendor admin for support!!"),
	COMPANY_RECORD_STATS("FV0010", "Internal error - Unable to prepare company record statistics, Please contact Finvendor admin for support!!"),
	COMPANY_RESEARCH_REPORT("FV0011", "Internal error - Unable to prepare company research report, Please contact Finvendor admin for support!!"),
	
	ADD_COMPANY_PRICE_ALERT("FV0012", "Internal error - Unable to add company price alert, Please contact Finvendor admin for support!!"),
	FIND_ALL_COMPANY_PRICE_ALERT("FV0013", "Internal error - Unable to find all company price alert, Please contact Finvendor admin for support!!"),
	FIND_USER_FROM_SESSION("FV0014", "Internal error - Unable to find logged in user from current session, Please contact Finvendor admin for support!!"),
	UPDATE_PRICE("FV0015", "Internal error - Unable to update current market price, Please contact Finvendor admin for support!!"),
	
	DELETE_WATCHLIST("FV0016", "Internal error - Unable to delete company watchlist, Please contact Finvendor admin for support!!"),
	UPDATE_COMPANY_PRICE_ALERT("FV0017", "Internal error - Unable to update company price alert, Please contact Finvendor admin for support!!"),
	DELETE_COMPANY_PRICE_ALERT("FV0018", "Internal error - Unable to delete company price alert, Please contact Finvendor admin for support!!"),
	FIND_COMPANY_PRICE_ALERT("FV0018", "Internal error - Unable to delete company price alert, Please contact Finvendor admin for support!!"),
	
	PRICE_MAIL("FV0019", "Internal error - Unable to send mail to user for given company, Please contact Finvendor admin for support!!"),
	RESEARCH_REPORT_MAIL("FV0020", "Internal error - Unable to send research report mail to user for given company, Please contact Finvendor admin for support!!")
	
	;
	
	private String code;
	private String userMessage;

	private ExceptionEnum(String code, String userMessage) {
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

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}