package com.finvendor.server.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.server.scheduler.dto.UserCompanyMailContent;

/**
 * @author ayush on May 12, 2018
 */
public class PriceUpdateJob implements Job {
	public static final String UPDATE_PRICE_URI = "http://localhost:8080/system/api/updatestockprice";
	public static final String SEND_PRICE_ALERT_MAIL_URI = "http://localhost:8080/system/api/sendpricealertmail";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Step-1 Update Price
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<UserCompanyMailContent> response = restTemplate.postForEntity(UPDATE_PRICE_URI,
					new StockCurrentPricePojo(), UserCompanyMailContent.class);

			// Step-2 Sent Mail
			UserCompanyMailContent userCompanyMailContent = response.getBody();

			if (userCompanyMailContent.getPerUserCompanyMailMessageMap().size() != 0) {
				ResponseEntity<StatusPojo> response1 = restTemplate.postForEntity(SEND_PRICE_ALERT_MAIL_URI,
						userCompanyMailContent, StatusPojo.class);
				System.out.println("***response1=" + response1.getBody().getMessage());
			} else {
				System.out.println("We did not update price from NSE so cannot send mail....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
