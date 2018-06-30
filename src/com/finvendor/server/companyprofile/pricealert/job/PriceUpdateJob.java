package com.finvendor.server.companyprofile.pricealert.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.finvendor.common.util.LogUtil;

/**
 * @author ayush on May 12, 2018
 */
public class PriceUpdateJob implements Job {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			// String NSE_PRICE_URI = (String) jobDataMap.get("price_update_uri");
			// String PRICE_ALERT_MAIL_URI = (String) jobDataMap.get("price_alert_uri");
			LocalDateTime now = LocalDateTime.now();
			System.out.println("**********************************");
			System.out.println("CurrentTime:" + dtf.format(now));
			System.out.println("**********************************");

			// // Step-1 Update Price
			// RestTemplate restTemplate = new RestTemplate();
			// ResponseEntity<Boolean> stockPriceUpdateResponse =
			// restTemplate.postForEntity(NSE_PRICE_URI,
			// new StockCurrentPriceDTO(), Boolean.class);
			//
			// // Step-2 Send Mail
			// Boolean updateStatus = stockPriceUpdateResponse.getBody();
			// if (updateStatus.booleanValue()) {
			// ResponseEntity<StatusPojo> mailResponse =
			// restTemplate.postForEntity(PRICE_ALERT_MAIL_URI, updateStatus,
			// StatusPojo.class);
			// LogUtil.logInfo("*** Price Alert Mail Response code:" +
			// mailResponse.getStatusCode());
			// LogUtil.logInfo("*** Price Alert Mail Body:" +
			// mailResponse.getBody().toString());
			// } else {
			// LogUtil.logWarn("*** Unable to send mail due to either Price ALERT did not
			// SET or holiday !!!!");
			// }
		} catch (Exception e) {
			LogUtil.logError("*** Error in PriceUpdateJob - ErrMsg=" + e.getMessage());
		}
	}
}
