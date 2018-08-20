package com.finvendor.server.companyprofile.pricealert.job;


import com.finvendor.common.constant.AppConstant;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author ayush on May 12, 2018
 */
public class PriceUpdateJob implements Job {
	SimpleDateFormat formatter=new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			String NSE_PRICE_UPDATE_URI = (String) jobDataMap.get("price_update_uri");
			String PRICE_ALERT_MAIL_URI = (String) jobDataMap.get("price_alert_uri");
			String now = formatter.format(Calendar.getInstance().getTime());
			System.out.println("***NSE_PRICE_UPDATE_URI="+NSE_PRICE_UPDATE_URI);
			System.out.println("***PRICE_ALERT_MAIL_URI="+PRICE_ALERT_MAIL_URI);
			System.out.println("**********************************");
			System.out.println("***Scheulder Time:" + now);
			LogUtil.logInfo("**********************************");

			// Step-1 Update Price
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<Boolean> stockPriceUpdateResponse = restTemplate.postForEntity(NSE_PRICE_UPDATE_URI,
//					new StockCurrentPriceDTO(), Boolean.class);
//
//			// Step-2 Send Mail
//			Boolean updateStatus = stockPriceUpdateResponse.getBody();
//			StatusPojo statusPojo=new StatusPojo();
//			if (updateStatus.booleanValue()) {
//				statusPojo.setStatus("true");
//				ResponseEntity<StatusPojo> mailResponse = restTemplate.postForEntity(PRICE_ALERT_MAIL_URI, statusPojo,
//						StatusPojo.class);
//				LogUtil.logInfo("*** Price Alert Mail Response code:" + mailResponse.getStatusCode());
//				LogUtil.logInfo("*** Price Alert Mail Body:" + mailResponse.getBody().toString());
//			} else {
//				LogUtil.logWarn("*** Unable to send mail due to either Price ALERT did not SET or holiday !!!!");
//			}
		} catch (Exception e) {
			LogUtil.logError("*** Error in PriceUpdateJob - ErrMsg=" + e);
		}
	}
}
