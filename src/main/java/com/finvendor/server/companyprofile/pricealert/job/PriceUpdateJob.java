package com.finvendor.server.companyprofile.pricealert.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;

/**
 * @author ayush on May 12, 2018
 */
public class PriceUpdateJob implements Job {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			String NSE_PRICE_URI = (String) jobDataMap.get("price_update_uri");
			String PRICE_ALERT_MAIL_URI = (String) jobDataMap.get("price_alert_uri");
			LocalDateTime now = LocalDateTime.now();
			LogUtil.logInfo("***NSE_PRICE_URI="+NSE_PRICE_URI);
			LogUtil.logInfo("***PRICE_ALERT_MAIL_URI="+PRICE_ALERT_MAIL_URI);
			LogUtil.logInfo("**********************************");
			LogUtil.logInfo("***Scheulder Time:" + dtf.format(now));
			LogUtil.logInfo("**********************************");

			// Step-1 Update Price
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Boolean> stockPriceUpdateResponse = restTemplate.postForEntity(NSE_PRICE_URI,
					new StockCurrentPriceDTO(), Boolean.class);

			// Step-2 Send Mail
			Boolean updateStatus = stockPriceUpdateResponse.getBody();
			StatusPojo statusPojo=new StatusPojo();
			if (updateStatus.booleanValue()) {
				statusPojo.setStatus("true");
				ResponseEntity<StatusPojo> mailResponse = restTemplate.postForEntity(PRICE_ALERT_MAIL_URI, statusPojo,
						StatusPojo.class);
				LogUtil.logInfo("*** Price Alert Mail Response code:" + mailResponse.getStatusCode());
				LogUtil.logInfo("*** Price Alert Mail Body:" + mailResponse.getBody().toString());
			} else {
				LogUtil.logWarn("*** Unable to send mail due to either Price ALERT did not SET or holiday !!!!");
			}
		} catch (Exception e) {
			LogUtil.logError("*** Error in PriceUpdateJob - ErrMsg=" + e);
		}
	}
}
