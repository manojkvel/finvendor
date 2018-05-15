package com.finvendor.server.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.client.RestTemplate;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;

/**
 * @author ayush on May 12, 2018
 */
public class PriceUpdateJob implements Job {
	public static final String UPDATE_PRICE_URI = "http://localhost:8080/system/api/alert/researcharea/updateprice";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//Step-1 Update Price
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_PRICE_URI, new StockCurrentPricePojo());
		
		//Send Email
//		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
//		FinVendorUser userDetailsByUsername = userService.getUserDetailsByUsername("test_vendor");
//		String to = userDetailsByUsername.getEmail();
//		String subject="Test Subject";
//		String content="Test Content";
//		EmailUtil.sendMail(to,subject,content);
	}
	
}
