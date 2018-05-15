package com.finvendor.serverwebapi.resources.companyprofile.pricealert.impl;

import static com.finvendor.common.exception.ExceptionEnum.ADD_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.DELETE_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_ALL_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_USER_FROM_SESSION;
import static com.finvendor.common.exception.ExceptionEnum.UPDATE_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.UPDATE_PRICE;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.companyprofile.pricealert.service.ICompanyPriceAlertService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.companyprofile.pricealert.IWebCompanyPriceAlert;

/**
 * @author ayush on May 12, 2018
 */
@Controller
public class WebCompanyPriceAlertImpl implements IWebCompanyPriceAlert {

	DateFormat bhavDateFormatFromNSESite = new SimpleDateFormat("dd-MMM-yyyy");
	Format fvDateFormat = new SimpleDateFormat("MM/dd/yy");

	@Autowired
	private ICompanyPriceAlertService service;

	@Resource(name = "finvendorProperties")
	private Properties fvProperties;


	@Override
	public ResponseEntity<?> addCompanyPriceAlert(@RequestBody CompanyPriceAlertPojo companyPriceAlertPojo) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> addCompanyPriceAlert - START");
		try {
			boolean addStatus = service.addCompanyPriceAlert(companyPriceAlertPojo);
			if (addStatus) {
				return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Company price alert added successfully."), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Company price alert already added."), HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> addCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(ADD_COMPANY_PRICE_ALERT.getCode(), ADD_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}
	
	//Update
	@Override
	public ResponseEntity<?> updateCompanyPriceAlert(@RequestBody CompanyPriceAlertPojo companyPriceAlertPojo) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> updateCompanyPriceAlert - START");
		try {
			boolean updatePriceAlertStatus = service.updateCompanyPriceAlert(companyPriceAlertPojo);
			if (updatePriceAlertStatus) {
				return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Company price alert updated successfully"),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Unable to update company price alert"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> updateCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(UPDATE_COMPANY_PRICE_ALERT.getCode(), UPDATE_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}

	// Delete
	@Override
	public ResponseEntity<?> deleteCompanyPriceAlert(@RequestBody List<CompanyPriceAlertPojo> pojoList) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> deleteCompanyPriceAlert - START");
		try {
			boolean deletePriceAlertStatus = service.deleteCompanyPriceAlerts(pojoList);
			if (deletePriceAlertStatus) {
				return new ResponseEntity<StatusPojo>(
						new StatusPojo("true", "All company price alert deleted successfully"), HttpStatus.OK);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Unable to delete company price alert"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> deleteCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(DELETE_COMPANY_PRICE_ALERT.getCode(), DELETE_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}
	//Find
	@Override
	public ResponseEntity<?> findCompanyPriceAlert(HttpServletRequest request, 
			@RequestParam(value = "companyId", required = true) String companyId) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> findCompanyPriceAlert - START");
		try {
			String userName = "amit";
			CompanyPriceAlertPojo pojo = service.findCompanyPriceAlert(companyId, userName);
			return new ResponseEntity<CompanyPriceAlertPojo>(pojo, HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> findCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(FIND_COMPANY_PRICE_ALERT.getCode(), FIND_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}

	//Fina All
	@Override
	public ResponseEntity<?> findAllCompanyPriceAlert(HttpServletRequest request) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> findAllCompanyPriceAlert - START");
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			List<CompanyPriceAlertPojo> allPriceAlert = service.findAllCompanyPriceAlert(userName);
			return new ResponseEntity<List<CompanyPriceAlertPojo>>(allPriceAlert, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> findAllCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(FIND_ALL_COMPANY_PRICE_ALERT.getCode(), FIND_ALL_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Below will call from Scheduler
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public ResponseEntity<?> updateStockPrice(@RequestBody StockCurrentPricePojo stockCurrentPricePojo) throws WebApiException {
		LogUtil.logInfo("\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+ "Update Stocck Price  (NSE) - START\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			//Get ticker and companyId from database
			Map<String, String> tickerAndCompanyIdMap = service.findAllTickerFromDb();
			LogUtil.logInfo("UpdateStockPrice-> TickerAndCompanyIdMap data\n" + tickerAndCompanyIdMap+"\n");
			
			//Get Current Market Price from NSE site
			long startTime=System.currentTimeMillis();
			List<StockCurrentPricePojo> stockCurrentPricePojoList = getCurrentMarketPriceFromNSESite(tickerAndCompanyIdMap);

			// Update CMP in DB
			boolean updateStatus = true;
			for (StockCurrentPricePojo stockCurrentPricePojo1 : stockCurrentPricePojoList) {
				updateStatus &= service.updatePrice(stockCurrentPricePojo1);
			}

			long endTime=System.currentTimeMillis();
			long timeTakenToFetchCMP=(endTime-startTime)/1000L;
			
			LogUtil.logInfo("UpdateStockPrice-> Total time taken to fetch all companies price"
					+ "(total companies from Finvendor db= " + tickerAndCompanyIdMap.size() + ") from NSE site and to update db respectively is = " + timeTakenToFetchCMP + " second(s)!!!");

			if (updateStatus) {
				LogUtil.logInfo("UpdateStockPrice-> CMP price updated in db is completed");
				// Send price alert Email
				/*
				 * Algorithm: Get copmany price alerts pojo list form db For
				 * each StockCurrentPricePojo
				 */
				Map<Integer,String> companyIdWithClosePriceMap=new HashMap<>();
				for(StockCurrentPricePojo currentPricePojo: stockCurrentPricePojoList) {
					companyIdWithClosePriceMap.put(currentPricePojo.getStock_id(), currentPricePojo.getClose_price());
				}
				
				Map<String, List<CompanyPriceAlertPojo>> fetchAllStockCurrentPrices = service.fetchAllStockCurrentPrices();
				for(Map.Entry<String, List<CompanyPriceAlertPojo>> entry:fetchAllStockCurrentPrices.entrySet()) {
					String userName = entry.getKey();
					List<CompanyPriceAlertPojo> priceSpecificationList = entry.getValue();
					for(CompanyPriceAlertPojo priceSpecification:priceSpecificationList) {
						float closePriceOfCompanyIdFromNSE = Float.parseFloat(companyIdWithClosePriceMap.get(priceSpecification.getCompanyId()));
						
						float dayMinPrice=0.0f;
						float dayMaxPrice=0.0f;

						String dayMinPriceStr = priceSpecification.getDayMinPrice();
						if (dayMinPriceStr != null && ! dayMinPriceStr.isEmpty()) {
							dayMinPrice = Float.parseFloat(dayMinPriceStr);
						}
						
						String dayMaxPriceStr = priceSpecification.getDayMaxPrice();
						if (dayMaxPriceStr != null && ! dayMaxPriceStr.isEmpty()) {
							dayMaxPrice = Float.parseFloat(dayMaxPriceStr);
						}
						if (closePriceOfCompanyIdFromNSE <= dayMinPrice || closePriceOfCompanyIdFromNSE >= dayMaxPrice) {
							//then we need to send alert mail to user about day price specification met with current market price - Hurray!!!
						}
						
						
						float weekMinPrice = Float.parseFloat(priceSpecification.getWeekMinPrice());
						float weekMaxPrice = Float.parseFloat(priceSpecification.getWeekMaxPrice());
						
						float monthMinPrice = Float.parseFloat(priceSpecification.getMonthMinPrice());
						float monthMaxPrice = Float.parseFloat(priceSpecification.getMonthMaxPrice());
					}
				}
				System.out.println(fetchAllStockCurrentPrices);
				return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Stock price from NSE site updated successfully"), HttpStatus.OK);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Unable to update stock market price in db"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			ErrorUtil.logError("WebRsrchAreaStockPriceAlertImpl -> UpdateStockPrice(...) method", e);
			return ErrorUtil.getError(UPDATE_PRICE.getCode(), UPDATE_PRICE.getUserMessage(), e);
		}
	}

	private List<StockCurrentPricePojo> getCurrentMarketPriceFromNSESite(Map<String, String> tickerAndCompanyIdMap) {
		List<StockCurrentPricePojo> stockCurrentPricePojoList = new ArrayList<>();
		String companyPriceURL = fvProperties.getProperty("nse_price_url");
		LogUtil.logInfo("START fetching company price from NSE (National Stock Exchange) site...");
		
		String companyPriceURLReferer = fvProperties.getProperty("nse_price_url_referer");
		int configuredNseSiteAttemptCount = Integer.parseInt(fvProperties.getProperty("nse_site_attemp_count"));
		LogUtil.logInfo("Configured_NSE_SiteAttempt_Count=" + configuredNseSiteAttemptCount);
		
		for (Map.Entry<String, String> entry : tickerAndCompanyIdMap.entrySet()) {
			String tickerFromDb=entry.getKey();
			String updatedCompanyPriceURL=companyPriceURL;
			updatedCompanyPriceURL = StringUtils.replace(updatedCompanyPriceURL, "symbolValue", tickerFromDb);
			
			
			org.jsoup.nodes.Document doc=null;
			int nseSiteAccessAttemptCount=1;
			while (true) {
				try {
					//Accessing NSE Site to fetch CMP - START
					Connection connection = Jsoup.connect(updatedCompanyPriceURL);
					connection.referrer(companyPriceURLReferer);
					long startTime=System.currentTimeMillis();
					doc = connection.get();
					long endTime=System.currentTimeMillis();
					long timeDiff=(endTime-startTime)/1000L;
					LogUtil.logInfo("Calling - NSE Site to fetch price details \nURL="+updatedCompanyPriceURL+"\nTicker="+tickerFromDb+"\nTimeTaken="+timeDiff +" second(s)!");
					//Accessing NSE Site to fetch CMP - END
					
					Element table = doc.select("table").get(0);
					Elements rows = table.select("tr");
					
					//In case if no price data found for given ticker
					if (rows.size() == 1 ) {
						LogUtil.logWarn("No price record found for Ticker="+tickerFromDb+"!!!!");
					}
					
					for (int i = 1; i < rows.size();) { 
						Element row = rows.get(i);
						Elements cols = row.select("td");
						
						String symbleAsTicker = cols.get(0).text();
						String companyId = tickerAndCompanyIdMap.get(symbleAsTicker.trim()).trim();
						
						String priceDate = cols.get(2).text();
						String priceDateInMMDDYY = fvDateFormat.format(bhavDateFormatFromNSESite.parse(priceDate));

						String openPrice = cols.get(4).text();
						openPrice=StringUtils.replace(openPrice, ",", "");
						
						String highPrice = cols.get(5).text();
						highPrice=StringUtils.replace(highPrice, ",", "");
						
						String lowPrice = cols.get(6).text();
						lowPrice=StringUtils.replace(lowPrice, ",", "");
						
						String closePrice = cols.get(8).text();
						closePrice=StringUtils.replace(closePrice, ",", "");
						
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
					if (nseSiteAccessAttemptCount == configuredNseSiteAttemptCount ) {
						LogUtil.logInfo("Last attempt to NSE Site to get stock price trial count=" + nseSiteAccessAttemptCount + " failed!");
						break;
					} else {
						LogUtil.logInfo("Attempt to NSE Site to get stock price trial count=" + nseSiteAccessAttemptCount + " failed!");
						nseSiteAccessAttemptCount++;
						continue;
					}
				}
			}
		}
		return stockCurrentPricePojoList;
	}
}
