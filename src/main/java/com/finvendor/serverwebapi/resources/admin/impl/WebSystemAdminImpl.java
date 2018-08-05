package com.finvendor.serverwebapi.resources.admin.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.admin.IWebSystemAdmin;

@Controller
public class WebSystemAdminImpl implements IWebSystemAdmin {
	
	@Resource(name = "finvendorProperties")
	private Properties finvendorProperties;
	
	@Override
	public String getPerPageMaxRecordCount() throws WebApiException {
		String maxCount = finvendorProperties.getProperty("per_page_max_record_count");
		//TODO use jackson
		return "{\"perpagemaxrecordcount\":\""+maxCount+"\"}";
	}
}
