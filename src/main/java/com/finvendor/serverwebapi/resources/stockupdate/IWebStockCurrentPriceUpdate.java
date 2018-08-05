package com.finvendor.serverwebapi.resources.stockupdate;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebStockCurrentPriceUpdate {

	/**
	 * @param stockCurrentPricePojo
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/updatestockprice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> updateStockPrice(StockCurrentPriceDTO stockCurrentPricePojo) throws WebApiException;

}
