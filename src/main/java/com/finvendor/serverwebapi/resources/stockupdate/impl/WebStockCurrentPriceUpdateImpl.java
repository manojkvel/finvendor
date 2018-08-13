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
	public ResponseEntity<?> updateStockPriceByVendor(@RequestBody StockCurrentPriceDTO stockCurrentPriceDTO)
			throws WebApiException {
		LogUtil.logInfo("-- nUpdate Stock Price  (NSE) - START-- ");
		return null;
	}

	@Override
	public ResponseEntity<?> updateStockPriceByBhavCopy() throws WebApiException {
		try {
			stockPriceUpdateService.updateStockPrice();
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("*** UpdateStockPrice(...) method", e);
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

}
