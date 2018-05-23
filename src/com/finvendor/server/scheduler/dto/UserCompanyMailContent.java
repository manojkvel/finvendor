package com.finvendor.server.scheduler.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserCompanyMailContent {
	private Map<String, List<CompanyEmailContent>> perUserCompanyMailMessageMap;

	public UserCompanyMailContent() {
		perUserCompanyMailMessageMap=new LinkedHashMap<>();
	}

	public UserCompanyMailContent(Map<String, List<CompanyEmailContent>> perUserCompanyMailMessageMap) {
		super();
		this.perUserCompanyMailMessageMap = perUserCompanyMailMessageMap;
	}

	public Map<String, List<CompanyEmailContent>> getPerUserCompanyMailMessageMap() {
		return perUserCompanyMailMessageMap;
	}

	public void setPerUserCompanyMailMessageMap(Map<String, List<CompanyEmailContent>> perUserCompanyMailMessageMap) {
		this.perUserCompanyMailMessageMap = perUserCompanyMailMessageMap;
	}
}
