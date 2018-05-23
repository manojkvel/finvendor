package com.finvendor.serverwebapi.resources.scheduler.impl;

import static com.finvendor.common.exception.ExceptionEnum.PRICE_MAIL;
import static com.finvendor.common.exception.ExceptionEnum.UPDATE_PRICE;
import static com.finvendor.common.exception.ExceptionEnum.RESEARCH_REPORT_MAIL;


import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.LogUtil;
import com.finvendor.exception.ApplicationException;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.scheduler.dto.CompanyEmailContent;
import com.finvendor.server.scheduler.dto.UserCompanyMailContent;
import com.finvendor.server.scheduler.service.ISchedulerService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.scheduler.IWebScheduler;
import com.finvendor.service.UserService;
import com.finvendor.util.EmailUtil;

@Controller
public class WebSchedulerImpl implements IWebScheduler {

	private DateFormat bhavDateFormatFromNSESite = new SimpleDateFormat("dd-MMM-yyyy");
	private Format fvDateFormat = new SimpleDateFormat("MM/dd/yy");

	@Resource(name = "userService")
	private UserService userService;

	@Autowired
	private ISchedulerService service;

	@Resource(name = "finvendorProperties")
	private Properties fvProperties;

	@Override
	public ResponseEntity<?> updateStockPrice(@RequestBody StockCurrentPricePojo stockCurrentPricePojo)
			throws WebApiException {
		LogUtil.logInfo("\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+ "Update Stock Price  (NSE) - START\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			// Get ticker and companyId from database
			Map<String, String> tickerAndCompanyIdMap = service.findAllTickerFromDb();
			LogUtil.logInfo("UpdateStockPrice-> TickerAndCompanyIdMap data\n" + tickerAndCompanyIdMap + "\n");

			// Get today's Stock market price from NSE site
			long startTime = System.currentTimeMillis();
			List<StockCurrentPricePojo> stockCurrentPricePojoList = getTodaysStockPriceFromNSESite(
					tickerAndCompanyIdMap);

			boolean isStockPriceNeedToUpdateInDb;
			if (stockCurrentPricePojoList != null && stockCurrentPricePojoList.size() > 0) {
				String priceDateFromNSE = stockCurrentPricePojoList.get(0).getPrice_date();
				// String nsePriceDateInMMDDYY =
				// fvDateFormat.format(bhavDateFormatFromNSESite.parse(priceDateFromNSE));
				StockCurrentPricePojo fetchStockPrice = service.fetchStockPrice();
				String priceDateFromDb = fetchStockPrice.getPrice_date();
				if (!priceDateFromNSE.equals(priceDateFromDb)) {
					isStockPriceNeedToUpdateInDb = true;
				} else {
					isStockPriceNeedToUpdateInDb = false;
				}
			} else {
				isStockPriceNeedToUpdateInDb = false;
			}

			if (isStockPriceNeedToUpdateInDb) {
				// Update CMP in DB
				boolean updateStatus = true;
				for (StockCurrentPricePojo stockCurrentPricePojo1 : stockCurrentPricePojoList) {
					updateStatus &= service.updatePrice(stockCurrentPricePojo1);
				}
				long endTime = System.currentTimeMillis();
				long timeTakenToFetchCMP = (endTime - startTime) / 1000L;

				LogUtil.logInfo("UpdateStockPrice-> Total time taken to fetch all companie sprice"
						+ "(total companies from Finvendor db= " + tickerAndCompanyIdMap.size()
						+ ") from NSE site and to update db respectively is = " + timeTakenToFetchCMP
						+ " second(s)!!!");

				if (updateStatus) {
					LogUtil.logInfo("UpdateStockPrice-> CMP price updated in db is completed");
					UserCompanyMailContent userCompaniesMailContent = prepareMail();
					return new ResponseEntity<UserCompanyMailContent>(userCompaniesMailContent, HttpStatus.OK);
				} else {
					return new ResponseEntity<UserCompanyMailContent>(new UserCompanyMailContent(),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				LogUtil.logWarn("UpdateStockPrice-> Unable to get stock price from NSE Site!!!");
				return new ResponseEntity<UserCompanyMailContent>(new UserCompanyMailContent(), HttpStatus.OK);
			}
		} catch (Exception e) {
			ErrorUtil.logError("WebScheduler -> UpdateStockPrice(...) method", e);
			return ErrorUtil.getError(UPDATE_PRICE.getCode(), UPDATE_PRICE.getUserMessage(), e);
		}
	}

	private UserCompanyMailContent prepareMail() throws NumberFormatException, Exception {
		Map<String, List<CompanyEmailContent>> userCompanyMailContentMap = new LinkedHashMap<>();
		// key is user name and value is CompanyPriceAlerUt List
		// 1 user can set alert for one or more than one company (1:M)
		Map<String, List<CompanyPriceAlertPojo>> companyPriceAlertMap = service.fetchCompanyPriceAlert();
		Map<String, StockCurrentPricePojo> stockPriceMap = service.fetchAllStockPrice();
		for (Map.Entry<String, List<CompanyPriceAlertPojo>> entry : companyPriceAlertMap.entrySet()) {
			String userName = entry.getKey();
			List<CompanyEmailContent> companyMailMessageList = new ArrayList<>();
			List<CompanyPriceAlertPojo> companyPriceList = entry.getValue();
			for (CompanyPriceAlertPojo companyPrice : companyPriceList) {
				// get stock price for given company id
				String companyId = companyPrice.getCompanyId();
				StockCurrentPricePojo stockCurrentPrice = stockPriceMap.get(companyId);
				Float todaysClosePrice = 0.0f;
				Float yesterdayClosePriceAsLTP=0.0f;
				if (stockCurrentPrice != null) {
					String todaysClosePriceFromDb = stockCurrentPrice.getClose_price();
					if (todaysClosePriceFromDb != null && !todaysClosePriceFromDb.isEmpty()) {
						todaysClosePrice = Float.parseFloat(todaysClosePriceFromDb);
					} else {
						todaysClosePrice = null;
					}
					
					String yesterdayClosePriceAsLTPFromDb=stockCurrentPrice.getLast_traded_price();
					if (yesterdayClosePriceAsLTPFromDb != null && !yesterdayClosePriceAsLTPFromDb.isEmpty()) {
						yesterdayClosePriceAsLTP = Float.parseFloat(yesterdayClosePriceAsLTPFromDb);
					} else {
						yesterdayClosePriceAsLTP = null;
					}
				}

				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// Daily Price hit checking
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				float usersDayMinPrice = 0.0f;
				float usersDayMaxPrice = 0.0f;

				String usersDayMinPriceStr = companyPrice.getDayMinPrice();
				if (usersDayMinPriceStr != null && !usersDayMinPriceStr.isEmpty()) {
					usersDayMinPrice = Float.parseFloat(usersDayMinPriceStr);
				}

				String usersDayMaxPriceStr = companyPrice.getDayMaxPrice();
				if (usersDayMaxPriceStr != null && !usersDayMaxPriceStr.isEmpty()) {
					usersDayMaxPrice = Float.parseFloat(usersDayMaxPriceStr);
				}

				float todaysCmpInPercentage = 0.0f;
				String todaysCmpInPercentageStr = "";
				if (todaysClosePrice != null) {
					if (todaysClosePrice == usersDayMinPrice) {
						todaysCmpInPercentageStr = "0% daily change";
					} else if (todaysClosePrice < usersDayMinPrice) {
						todaysCmpInPercentage = (usersDayMinPrice - todaysClosePrice) * 100 / usersDayMinPrice;
						todaysCmpInPercentageStr = "-" + todaysCmpInPercentage + " % daily change";
					} else if (todaysClosePrice == usersDayMaxPrice) {
						todaysCmpInPercentageStr = "0% daily change";
					} else if (todaysClosePrice > usersDayMaxPrice) {
						todaysCmpInPercentage = (todaysClosePrice - usersDayMaxPrice) * 100 / todaysClosePrice;
						todaysCmpInPercentageStr = "+" + todaysCmpInPercentage + " % daily change";
					} else {
						todaysCmpInPercentage = 0.0f;
						todaysCmpInPercentageStr = "";
					}
				} else {
					todaysCmpInPercentage = 0.0f;
					todaysCmpInPercentageStr = "";
				}

				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// Weekly Price hit checking
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				Float lastWeekClosePrice = getLastWeekClosePrice(companyId);
				float lastWeekCmpInPercentage = 0.0f;
				String lastWeekCmpInPercentageStr = "";
				if (lastWeekClosePrice != null) {
					float usersWeekMinPrice = 0.0f;
					float usersWeekMaxPrice = 0.0f;

					String usersWeekMinPriceStr = companyPrice.getWeekMinPrice();
					if (usersWeekMinPriceStr != null && !usersWeekMinPriceStr.isEmpty()) {
						usersWeekMinPrice = Float.parseFloat(usersWeekMinPriceStr);
					}

					String usersWeekMaxPriceStr = companyPrice.getWeekMaxPrice();
					if (usersWeekMaxPriceStr != null && !usersWeekMaxPriceStr.isEmpty()) {
						usersWeekMaxPrice = Float.parseFloat(usersWeekMaxPriceStr);
					}
					
					if (lastWeekClosePrice == usersWeekMinPrice) {
						lastWeekCmpInPercentageStr = "0% weekly change";
					} else if (lastWeekClosePrice < usersWeekMinPrice) {
						lastWeekCmpInPercentage = (usersDayMinPrice - lastWeekClosePrice) * 100 / usersDayMinPrice;
						lastWeekCmpInPercentageStr = "-" + lastWeekCmpInPercentage + " % weekly change";
					} else if (lastWeekClosePrice == usersWeekMaxPrice) {
						lastWeekCmpInPercentageStr = "0% weekly change";
					} else if (lastWeekClosePrice > usersWeekMaxPrice) {
						lastWeekCmpInPercentage = (lastWeekClosePrice - usersWeekMaxPrice) * 100 / lastWeekClosePrice;
						lastWeekCmpInPercentageStr = "+" + lastWeekCmpInPercentage + " % weekly change";
					} else {
						lastWeekCmpInPercentage = 0.0f;
						lastWeekCmpInPercentageStr = "";
					}
				} else {
					lastWeekCmpInPercentage = 0.0f;
					lastWeekCmpInPercentageStr = "";
				}
				
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// Monthly Price hit checking
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				Float lastMonthClosePrice = getLastMonthClosePrice(companyId);
				float lastMonthCmpInPercentage = 0.0f;
				String lastMonthCmpInPercentageStr = "";
				if (lastMonthClosePrice != null) {
					float usersMonthMinPrice = 0.0f;
					float usersMonthMaxPrice = 0.0f;

					String usersMonthMinPriceStr = companyPrice.getMonthMinPrice();
					if (usersMonthMinPriceStr != null && !usersMonthMinPriceStr.isEmpty()) {
						usersMonthMinPrice = Float.parseFloat(usersMonthMinPriceStr);
					}

					String usersMonthMaxPriceStr = companyPrice.getMonthMaxPrice();
					if (usersMonthMaxPriceStr != null && !usersMonthMaxPriceStr.isEmpty()) {
						usersMonthMaxPrice = Float.parseFloat(usersMonthMaxPriceStr);
					}
					
					if (lastMonthClosePrice == usersMonthMinPrice) {
						lastMonthCmpInPercentageStr = "0% monthly change";
					} else if (lastMonthClosePrice < usersMonthMinPrice) {
						lastMonthCmpInPercentage = (usersMonthMinPrice - lastMonthClosePrice) * 100 / usersMonthMinPrice;
						lastMonthCmpInPercentageStr = "-" + lastWeekCmpInPercentage + " % monthly change";
					} else if (lastMonthClosePrice == usersMonthMaxPrice) {
						lastMonthCmpInPercentageStr = "0% monthly change";
					} else if (lastMonthClosePrice > usersMonthMaxPrice) {
						lastMonthCmpInPercentage = (lastMonthClosePrice - usersMonthMaxPrice) * 100 / usersMonthMaxPrice;
						lastMonthCmpInPercentageStr = "+" + lastMonthCmpInPercentage + " % monthly change";
					}
				} else {
					lastMonthCmpInPercentage = 0.0f;
					lastMonthCmpInPercentageStr = "";
				}

				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// No timeframe checking
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//				String noTimeFrame = companyPrice.getNoTimeFrame();
//				if(noTimeFrame!=null) {
//					
//				}
				
				CompanyEmailContent companyEmailContent = new CompanyEmailContent();
				companyEmailContent.setUserName(userName);
				companyEmailContent.setCompanyName(companyPrice.getCompanyName());
				companyEmailContent.setPriceDate(stockCurrentPrice.getPrice_date());

				companyEmailContent.setTodaysCmp(todaysClosePrice);
				companyEmailContent.setYesterdayCmp(yesterdayClosePriceAsLTP);
				companyEmailContent.setTodaysCmpInPercentageStr(todaysCmpInPercentageStr);

				if (lastWeekClosePrice != null) {
					companyEmailContent.setLastWeekCmp(lastWeekClosePrice);
					companyEmailContent.setLastWeekCmpInPercentageStr(lastWeekCmpInPercentageStr);
				}

				if(lastMonthClosePrice!=null) {
					companyEmailContent.setLastMonthCmp(lastMonthClosePrice);
					companyEmailContent.setLastMonthCmpInPercentageStr(lastMonthCmpInPercentageStr);
				}
				
				companyMailMessageList.add(companyEmailContent);
			}
			userCompanyMailContentMap.put(userName, companyMailMessageList);
		}
		return new UserCompanyMailContent(userCompanyMailContentMap);
	}

	@Override
	public ResponseEntity<?> sendMail(@RequestBody UserCompanyMailContent userCompaniesMailContent)
			throws WebApiException {
		try {
			Map<String, List<CompanyEmailContent>> perUserCompanyMailMessageMap = userCompaniesMailContent
					.getPerUserCompanyMailMessageMap();
			for (Map.Entry<String, List<CompanyEmailContent>> entry : perUserCompanyMailMessageMap.entrySet()) {
				String userName = entry.getKey();
				List<CompanyEmailContent> companyEmailContentList = entry.getValue();

				String to = userService.getUserDetailsByUsername(userName).getEmail();
				for (CompanyEmailContent companyEmailContent : companyEmailContentList) {
					String companyName = companyEmailContent.getCompanyName();
					String subject = "Stock Price Alert Triggered for:" + companyName;
					String mailBody = prepareMailBody(companyEmailContent);
					try {
						EmailUtil.sendMail(to, subject, mailBody);
					} catch (RuntimeException e) {
						LogUtil.logError("Unable to send mail to this company :" + companyName);
					}
				}
			}
			return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Mail sent to each user successfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebScheduler -> sendMail(...) method", e);
			return ErrorUtil.getError(UPDATE_PRICE.getCode(), UPDATE_PRICE.getUserMessage(), e);
		}
	}

	@Override
	public ResponseEntity<?> sendMailForResearchReport(String userName,String companyName) throws WebApiException {
		String mailContent="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<title>Example</title>\r\n" + 
				"\r\n" + 
				"<!-- CSS -->\r\n" + 
				"<style>\r\n" + 
				".myTable { \r\n" + 
				"  width: 100%;\r\n" + 
				"  text-align: left;\r\n" + 
				"  background-color: white;\r\n" + 
				"  border-collapse: collapse; \r\n" + 
				"  }\r\n" + 
				".myTable th { \r\n" + 
				"  background-color: mediumaquamarine;\r\n" + 
				"  color: white; \r\n" + 
				"  }\r\n" + 
				".myTable td, \r\n" + 
				".myTable th { \r\n" + 
				"  padding: 10px;\r\n" + 
				"  border: 1px solid mediumaquamarine; \r\n" + 
				"  }\r\n" + 
				"</style>\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"Dear USERNAME<br> <br>\r\n" + 
				"\r\n" + 
				"Thank you for choosing \"Finvendor Corp.\" as your preferred investment partner.<br><br>\r\n" + 
				"\r\n" + 
				"Alert on stock research report<br><br>\r\n" + 
				"<!-- HTML -->\r\n" + 
				"<table class=\"myTable\">\r\n" + 
				"	<tr>\r\n" + 
				"		<th>Company Name</th>\r\n" + 
				"		<th>Report Alert Triggered</th>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<td>COMPANYNAME</td>\r\n" + 
				"		<td>A New research report added for this stock</td>\r\n" + 
				"	</tr>\r\n" + 
				"</table>\r\n" + 
				"<br><br>\r\n" + 
				"In case of any further queries or any assistance feel free to write us mail at sales@finvendor.com	 or contact our Customer support.\r\n" + 
				"Thank you once again for setting research report alert for company COMPANYNAME and look forward to be rewarding and continued relationship.\r\n" + 
				"<br><br>\r\n" + 
				"\r\n" + 
				"Assuring you the best of services.\r\n" + 
				"<br><br>\r\n" + 
				"Regards<br>\r\n" + 
				"Finvendor Corp.<br><br>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		mailContent = StringUtils.replace(mailContent, "USERNAME", userName);
		mailContent = StringUtils.replace(mailContent, "COMPANYNAME", companyName);
		
		String to;
		try {
			to = userService.getUserDetailsByUsername(userName).getEmail();
			String subject = "Stock Price Alert Triggered for:" + companyName;
			String mailBody = mailContent;
			EmailUtil.sendMail(to, subject, mailBody);
			return new ResponseEntity<StatusPojo>(
					new StatusPojo("true", "Research report mail sent to user successfully"), HttpStatus.OK);
		} catch (RuntimeException | ApplicationException e1) {
			ErrorUtil.logError("WebScheduler -> sendMailForResearchReport(...) method", e1);
			return ErrorUtil.getError(RESEARCH_REPORT_MAIL.getCode(), RESEARCH_REPORT_MAIL.getUserMessage(), e1);
		}
	}

	private String prepareMailBody(CompanyEmailContent dto) {
		String headerContentTemplate="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<title>Example</title>\r\n" + 
				"\r\n" + 
				"<!-- CSS -->\r\n" + 
				"<style>\r\n" + 
				".myTable { \r\n" + 
				"  width: 100%;\r\n" + 
				"  text-align: left;\r\n" + 
				"  background-color: white;\r\n" + 
				"  border-collapse: collapse; \r\n" + 
				"  }\r\n" + 
				".myTable th { \r\n" + 
				"  background-color: mediumaquamarine;\r\n" + 
				"  color: white; \r\n" + 
				"  }\r\n" + 
				".myTable td, \r\n" + 
				".myTable th { \r\n" + 
				"  padding: 10px;\r\n" + 
				"  border: 1px solid mediumaquamarine; \r\n" + 
				"  }\r\n" + 
				"</style>\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"Dear USERNAME<br> <br>\r\n" + 
				"\r\n" + 
				"Thank you for choosing \"Finvendor Corp.\" as your preferred investment partner.<br><br>\r\n" + 
				"\r\n" + 
				"Alert on stock as on PRICEDATE<br><br>";
		String tableContentTemplate="<table class=\"myTable\">\r\n" + 
				"	<tr>\r\n" + 
				"		<th>Company name</th>\r\n" + 
				"		<th>Alert triggered</th>\r\n" + 
				"      	<th>Today's price</th>\r\n" + 
				"      	<th>Previous day price</th>\r\n" + 
				"        <th>Price a week ago</th>\r\n" + 
				"        <th>Price a month ago</th>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<td>COMPANYNAME</td>\r\n" + 
				"		<td>PERCENTAGE</td>\r\n" + 
				"        <td>TODAYPRICE</td>\r\n" + 
				"		<td>YESTERDAYPRICE</td>\r\n" + 
				"        <td>WEEKPRICE</td>\r\n" + 
				"        <td>MONTHPRICE</td>\r\n" + 
				"	</tr>\r\n" + 
				"</table><br><br>";
		String footerContentTemplate="\r\n" + 
				"In case of any further queries or any assistance feel free to write us mail at sales@finvendor.com	 or contact our Customer support.<br><br>\r\n" + 
				"Thank you once again for setting price alert for company COMPANYNAME and look forward to be rewarding and continued relationship.\r\n" + 
				"<br><br>\r\n" + 
				"\r\n" + 
				"Assuring you the best of services.\r\n" + 
				"<br><br>\r\n" + 
				"Regards<br>\r\n" + 
				"Finvendor Corp.<br><br>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		String userName=dto.getUserName();
		String priceDate=dto.getPriceDate();
		String companyName=dto.getCompanyName();
		
		String yesterDayPrice = String.valueOf(dto.getYesterdayCmp());
		String todayPrice = String.valueOf(dto.getTodaysCmp());
		String todayPriceInPercentage = dto.getTodaysCmpInPercentageStr();

		String weeklyPrice = String.valueOf(dto.getLastWeekCmp());
		String lastWeekPriceInPercentage = dto.getLastWeekCmpInPercentageStr();

		String monthlyPrice = String.valueOf(dto.getLastMonthCmp());
		String lastMonthPriceInPercentage = dto.getLastMonthCmpInPercentageStr();		
		
		String dayTableContent = "";
		if (dto.getTodaysCmp() != 0.0f) {
			dayTableContent = tableContentTemplate;
			dayTableContent = StringUtils.replace(dayTableContent, "COMPANYNAME", companyName);
			dayTableContent = StringUtils.replace(dayTableContent, "PERCENTAGE", todayPriceInPercentage);
			dayTableContent = StringUtils.replace(dayTableContent, "TODAYPRICE", todayPrice);
			dayTableContent = StringUtils.replace(dayTableContent, "YESTERDAYPRICE", yesterDayPrice);
			dayTableContent = StringUtils.replace(dayTableContent, "WEEKPRICE", weeklyPrice);
			dayTableContent = StringUtils.replace(dayTableContent, "MONTHPRICE", monthlyPrice);
		}

		String weekTableContent = "";
		if (dto.getLastWeekCmp() != 0.0f) {
			weekTableContent = tableContentTemplate;
			weekTableContent = StringUtils.replace(weekTableContent, "COMPANYNAME", companyName);
			dayTableContent = StringUtils.replace(weekTableContent, "PERCENTAGE", lastWeekPriceInPercentage);
			dayTableContent = StringUtils.replace(weekTableContent, "TODAYPRICE", todayPrice);
			dayTableContent = StringUtils.replace(weekTableContent, "YESTERDAYPRICE", yesterDayPrice);
			dayTableContent = StringUtils.replace(weekTableContent, "WEEKPRICE", weeklyPrice);
			dayTableContent = StringUtils.replace(weekTableContent, "MONTHPRICE", monthlyPrice);
		}

		String monthTableContent = "";
		if (dto.getLastMonthCmp() != 0.0f) {
			monthTableContent = tableContentTemplate;
			monthTableContent = StringUtils.replace(monthTableContent, "COMPANYNAME", companyName);
			monthTableContent = StringUtils.replace(monthTableContent, "PERCENTAGE", lastMonthPriceInPercentage);
			monthTableContent = StringUtils.replace(monthTableContent, "TODAYPRICE", todayPrice);
			monthTableContent = StringUtils.replace(monthTableContent, "YESTERDAYPRICE", yesterDayPrice);
			monthTableContent = StringUtils.replace(monthTableContent, "WEEKPRICE", weeklyPrice);
			monthTableContent = StringUtils.replace(monthTableContent, "MONTHPRICE", monthlyPrice);
		}
		
		String headerContent=headerContentTemplate;
		headerContent = StringUtils.replace(headerContent, "USERNAME", userName);
		headerContent = StringUtils.replace(headerContent, "PRICEDATE", priceDate);
		StringBuffer tableSb = new StringBuffer(900);
		if (!dayTableContent.isEmpty()) {
			tableSb.append(dayTableContent).append("\r\n");
		}
		if (!weekTableContent.isEmpty()) {
			tableSb.append(weekTableContent).append("\r\n");
		}
		if (!monthTableContent.isEmpty()) {
			tableSb.append(monthTableContent).append("\r\n");
		}
		String tableContent=tableSb.toString();
		String footerContent=footerContentTemplate;
		footerContent = StringUtils.replace(footerContent, "COMPANYNAME", companyName);
		String finalMailContent = new StringBuffer(1000)
				.append(headerContent).append("\n")
				.append(tableContent).append("\n")
				.append(footerContent).append("\n").toString();
		return finalMailContent;
	}

	private List<StockCurrentPricePojo> getTodaysStockPriceFromNSESite(Map<String, String> tickerAndCompanyIdMap) {
		List<StockCurrentPricePojo> stockCurrentPricePojoList = new ArrayList<>();
		String companyPriceURL = fvProperties.getProperty("nse_price_url");
		LogUtil.logInfo("START fetching company price from NSE (National Stock Exchange) site...");

		String companyPriceURLReferer = fvProperties.getProperty("nse_price_url_referer");
		int configuredNseSiteAttemptCount = Integer.parseInt(fvProperties.getProperty("nse_site_attemp_count"));
		LogUtil.logInfo("Configured_NSE_SiteAttempt_Count=" + configuredNseSiteAttemptCount);

		for (Map.Entry<String, String> entry : tickerAndCompanyIdMap.entrySet()) {
			String tickerFromDb = entry.getKey();
			String updatedCompanyPriceURL = companyPriceURL;
			updatedCompanyPriceURL = StringUtils.replace(updatedCompanyPriceURL, "symbolValue", tickerFromDb);

			org.jsoup.nodes.Document doc = null;
			int nseSiteAccessAttemptCount = 1;
			while (true) {
				try {
					// Accessing NSE Site to fetch CMP - START
					Connection connection = Jsoup.connect(updatedCompanyPriceURL);
					connection.referrer(companyPriceURLReferer);
					long startTime = System.currentTimeMillis();
					doc = connection.get();
					long endTime = System.currentTimeMillis();
					long timeDiff = (endTime - startTime) / 1000L;
					LogUtil.logInfo("Calling - NSE Site to fetch price details \nURL=" + updatedCompanyPriceURL
							+ "\nTicker=" + tickerFromDb + "\nTimeTaken=" + timeDiff + " second(s)!");
					// Accessing NSE Site to fetch CMP - END

					Element table = doc.select("table").get(0);
					Elements rows = table.select("tr");

					// In case if no price data found for given ticker
					if (rows.size() == 1) {
						LogUtil.logWarn("No price record found for Ticker=" + tickerFromDb + "!!!!");
					}

					for (int i = 1; i < rows.size();) {
						Element row = rows.get(i);
						Elements cols = row.select("td");

						String symbleAsTicker = cols.get(0).text();
						String companyId = tickerAndCompanyIdMap.get(symbleAsTicker.trim()).trim();

						String priceDate = cols.get(2).text();
						String priceDateInMMDDYY = fvDateFormat.format(bhavDateFormatFromNSESite.parse(priceDate));

						String openPrice = cols.get(4).text();
						openPrice = StringUtils.replace(openPrice, ",", "");

						String highPrice = cols.get(5).text();
						highPrice = StringUtils.replace(highPrice, ",", "");

						String lowPrice = cols.get(6).text();
						lowPrice = StringUtils.replace(lowPrice, ",", "");

						String closePrice = cols.get(8).text();
						closePrice = StringUtils.replace(closePrice, ",", "");

						StockCurrentPricePojo pojo = new StockCurrentPricePojo();
						pojo.setStock_id(Integer.parseInt(companyId));
						pojo.setPrice_date(priceDateInMMDDYY);
						pojo.setOpen_price(openPrice);
						pojo.setClose_price(closePrice);
						pojo.setLow_price(lowPrice);
						pojo.setHigh_price(highPrice);
						stockCurrentPricePojoList.add(pojo);
						break;
					}
					break;
				} catch (Exception e) {
					if (nseSiteAccessAttemptCount == configuredNseSiteAttemptCount) {
						LogUtil.logInfo("Last attempt to NSE Site to get stock price trial count="
								+ nseSiteAccessAttemptCount + " failed!");
						break;
					} else {
						LogUtil.logInfo("Attempt to NSE Site to get stock price trial count="
								+ nseSiteAccessAttemptCount + " failed!");
						nseSiteAccessAttemptCount++;
						continue;
					}
				}
			}
		}
		return stockCurrentPricePojoList;
	}

	private Float getLastWeekClosePrice(String stockId) throws NumberFormatException, Exception {
		StockCurrentPricePojo lastWeekPrice = service.getLastWeekPrice(stockId);
		String close_price = lastWeekPrice.getClose_price();
		if (close_price != null && !close_price.isEmpty()) {
			return Float.parseFloat(close_price);
		} else {
			return null;
		}
	}

	private Float getLastMonthClosePrice(String stockId) throws NumberFormatException, Exception {
		StockCurrentPricePojo lastMonthPrice = service.getLastMonthPrice(stockId);
		String close_price = lastMonthPrice.getClose_price();
		if (close_price != null && !close_price.isEmpty()) {
			return Float.parseFloat(close_price);
		} else {
			return null;
		}
	}

}
