package com.finvendor.serverwebapi.resources.stockupdate.impl;

import static com.finvendor.common.exception.ExceptionEnum.UPDATE_PRICE;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.server.companyprofile.pricealert.dto.ConsumerPriceAlertDetailsWrapper;
import com.finvendor.server.stockpriceupdate.service.IStockPriceUpdateService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.stockupdate.IWebStockCurrentPriceUpdate;
import com.finvendor.service.UserService;

@Controller
public class WebStockCurrentPriceUpdateImpl implements IWebStockCurrentPriceUpdate {

	@Autowired
	private IStockPriceUpdateService stockPriceUpdateService;

	private DateFormat bhavDateFormatFromNSESite = new SimpleDateFormat("dd-MMM-yyyy");
	private Format fvDateFormat = new SimpleDateFormat("MM/dd/yy");

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "finvendorProperties")
	private Properties fvProperties;

	@Override
	public ResponseEntity<?> updateStockPrice(@RequestBody StockCurrentPriceDTO stockCurrentPriceDTO)
			throws WebApiException {
		LogUtil.logInfo("-- nUpdate Stock Price  (NSE) - START-- ");
		try {

			Map<String, String> tickerAndCompanyIdMap = stockPriceUpdateService.findAllTickerFromDb();
			LogUtil.logInfo("UpdateStockPrice-> TickerAndCompanyIdMap data\n" + tickerAndCompanyIdMap + "\n");

			// Get Price from NSE
			List<StockCurrentPriceDTO> nsePriceList = getNseStockPrice(tickerAndCompanyIdMap);
			boolean updatStockPrice;
			if (nsePriceList.size() > 0) {
				String nsePriceDate = nsePriceList.get(0).getPrice_date();
				StockCurrentPriceDTO dbStockPrice = stockPriceUpdateService.fetchStockPrice();
				String dbPriceDate = dbStockPrice.getPrice_date();
				if (!nsePriceDate.equals(dbPriceDate)) {
					updatStockPrice = true;
				} else {
					updatStockPrice = false;
				}
			} else {
				updatStockPrice = false;
			}

			if (updatStockPrice) {
				Boolean updateStatus = true;
				for (StockCurrentPriceDTO stockCurrentPricePojo1 : nsePriceList) {
					updateStatus &= stockPriceUpdateService.updatePrice(stockCurrentPricePojo1);
				}

				if (updateStatus) {
					LogUtil.logInfo("Stock price updated in db completed: Status=" + updateStatus);
					return new ResponseEntity<Boolean>(updateStatus, HttpStatus.OK);
				} else {
					return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
				}
			} else {
				LogUtil.logWarn("*** Unable to update stock price on holiday !!!");
				return new ResponseEntity<ConsumerPriceAlertDetailsWrapper>(new ConsumerPriceAlertDetailsWrapper(),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			ErrorUtil.logError("*** UpdateStockPrice(...) method", e);
			return ErrorUtil.getError(UPDATE_PRICE.getCode(), UPDATE_PRICE.getUserMessage(), e);
		}
	}

	private List<StockCurrentPriceDTO> getNseStockPrice(Map<String, String> tickerAndCompanyIdMap) {
		LogUtil.logInfo("NSE Stock Price - START");
		int nseTotalHit = Integer.parseInt(fvProperties.getProperty("nse_total_hit_count"));
		List<StockCurrentPriceDTO> nsePriceList = new ArrayList<>();

		// For each Ticker Hit NSE
		for (Map.Entry<String, String> entry : tickerAndCompanyIdMap.entrySet()) {
			String ticker = entry.getKey().trim();

			int nseHitCounter = 1;
			while (true) {
				try {
					Document doc = getNsePriceDoc(ticker);
					if (doc == null) {
						LogUtil.logWarn("*** Stock price not found from NSE for ticker=" + ticker);
					} else {
						StockCurrentPriceDTO pojo = getNsePrice(tickerAndCompanyIdMap, ticker, doc);
						nsePriceList.add(pojo);
					}
					break;
				} catch (Exception e) {
					if (nseHitCounter == nseTotalHit) {
						LogUtil.logInfo("*** FAILED to get price data from NSE !!");
						break;
					} else {
						LogUtil.logInfo("*** NSE hit failed for counter=" + nseHitCounter);
						nseHitCounter++;
						continue;
					}
				}
			}
		}
		LogUtil.logInfo("NSE Stock Price - END");
		return nsePriceList;
	}

	/**
	 * hit NSE sit with various combinatin of segmentLink and symbolCount index Once
	 * we get data for any combination return html document else return empty doc
	 */
	private Document getNsePriceDoc(String tickerFromDb) {
		String NSE_PRICE_URI = fvProperties.getProperty("nse_price_url");
		String REFERER = fvProperties.getProperty("nse_price_url_referer");
		int segmentAndSymbolCount = Integer.parseInt(fvProperties.getProperty("segment_and_symbol_count").trim());
		org.jsoup.nodes.Document doc = null;
		boolean NSE_URL_HIT_STATUS;
		NSE_PRICE_URI = StringUtils.replace(NSE_PRICE_URI, "TICKER", tickerFromDb);
		String template_NSE_PRICE_URI = NSE_PRICE_URI;
		// Comlexity O(n^2)
		for (int segmentLinkIndex = 1; segmentLinkIndex <= segmentAndSymbolCount; segmentLinkIndex++) {
			for (int symbolCountIndex = 1; symbolCountIndex <= segmentAndSymbolCount;) {
				String updated_NSE_PRICE_URI = StringUtils.replace(template_NSE_PRICE_URI, "SEGMENTLINK",
						String.valueOf(segmentLinkIndex));
				updated_NSE_PRICE_URI = StringUtils.replace(updated_NSE_PRICE_URI, "SYMBOLCOUNT",
						String.valueOf(symbolCountIndex));

				Connection connection = Jsoup.connect(updated_NSE_PRICE_URI);
				connection.referrer(REFERER);
				try {
					doc = connection.get();
					NSE_URL_HIT_STATUS = true;
					if (NSE_URL_HIT_STATUS && doc.toString().contains("Close Price")) {
						LogUtil.logInfo("** Hit success Nse url=" + NSE_PRICE_URI);
						return doc;
					} else {
						NSE_URL_HIT_STATUS = false;
					}
				} catch (Exception e) {
					template_NSE_PRICE_URI = NSE_PRICE_URI;
					NSE_URL_HIT_STATUS = false;
				}
				symbolCountIndex++;
			}
		}
		return doc;
	}

	private StockCurrentPriceDTO getNsePrice(Map<String, String> tickerAndCompanyIdMap, String tickerFromDb,
			org.jsoup.nodes.Document doc) throws ParseException {
		Element table = doc.select("table").get(0);
		Elements rows = table.select("tr");

		// In case if no price data found for given ticker
		if (rows.size() == 1) {
			LogUtil.logWarn("No price record found for Ticker=" + tickerFromDb + "!!!!");
		} else {
			LogUtil.logInfo("*** Todays PRICE record found for Ticker=" + tickerFromDb);
		}
		StockCurrentPriceDTO pojo = new StockCurrentPriceDTO();
		for (int i = 1; i < rows.size();) {
			Element row = rows.get(i);
			Elements cols = row.select("td");

			String symbleAsTicker = cols.get(0).text();
			String companyId = tickerAndCompanyIdMap.get(symbleAsTicker.trim()).trim();
			LogUtil.logInfo("***companyId(From NSE)=" + companyId);

			String priceDate = cols.get(2).text();
			String priceDateInMMDDYY = fvDateFormat.format(bhavDateFormatFromNSESite.parse(priceDate));
			LogUtil.logInfo("***priceDateInMMDDYY(From NSE)=" + priceDateInMMDDYY);

			String openPrice = cols.get(4).text();
			openPrice = StringUtils.replace(openPrice, ",", "");
			LogUtil.logInfo("***openPrice(From NSE)=" + openPrice);

			String highPrice = cols.get(5).text();
			highPrice = StringUtils.replace(highPrice, ",", "");
			LogUtil.logInfo("***highPrice(From NSE)=" + highPrice);

			String lowPrice = cols.get(6).text();
			lowPrice = StringUtils.replace(lowPrice, ",", "");
			LogUtil.logInfo("***lowPrice(From NSE)=" + lowPrice);

			String closePrice = cols.get(8).text();
			closePrice = StringUtils.replace(closePrice, ",", "");
			LogUtil.logInfo("***closePrice(From NSE)=" + closePrice);

			pojo.setStock_id(Integer.parseInt(companyId));
			pojo.setPrice_date(priceDateInMMDDYY);
			pojo.setOpen_price(openPrice);
			pojo.setClose_price(closePrice);
			pojo.setLow_price(lowPrice);
			pojo.setHigh_price(highPrice);
			break;
		}
		return pojo;
	}
}
