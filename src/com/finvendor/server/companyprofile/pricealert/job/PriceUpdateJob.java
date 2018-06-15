package com.finvendor.server.companyprofile.pricealert.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.server.companyprofile.pricealert.dto.UserCompanyMailContent;

/**
 * @author ayush on May 12, 2018
 */
public class PriceUpdateJob implements Job {
	public static final String UPDATE_PRICE_URI = "http://dev.finvendor.com/system/api/updatestockprice";
	public static final String SEND_PRICE_ALERT_MAIL_URI = "http://dev.finvendor.com/system/api/sendpricealertmail";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Step-1 Update Price
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<UserCompanyMailContent> response = restTemplate.postForEntity(UPDATE_PRICE_URI,
					new StockCurrentPricePojo(), UserCompanyMailContent.class);

			// Step-2 Send Mail
			UserCompanyMailContent userCompanyMailContent = response.getBody();

			if (userCompanyMailContent.getPerUserCompanyMailMessageMap().size() != 0) {
				ResponseEntity<StatusPojo> response1 = restTemplate.postForEntity(SEND_PRICE_ALERT_MAIL_URI,
						userCompanyMailContent, StatusPojo.class);
				LogUtil.logInfo("*** Price Alert Mail Response code:"+response1.getStatusCode());
				LogUtil.logInfo("*** Price Alert Mail Body:"+response1.getBody().toString());
			} else {
				LogUtil.logWarn("*** Unable to send mail as Price ALERT did not SET !!!!");
				LogUtil.logWarn("*** Set Price ALERT to get Email Alert !!");
			}
		} catch (Exception e) {
			LogUtil.logError("*** Error in PriceUpdateJob - ErrMsg="+e.getMessage());
		}
	}
}
