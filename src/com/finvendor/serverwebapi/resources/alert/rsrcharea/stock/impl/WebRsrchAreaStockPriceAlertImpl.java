package com.finvendor.serverwebapi.resources.alert.rsrcharea.stock.impl;

import static com.finvendor.common.exception.ExceptionEnum.ADD_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_ALL_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_USER_FROM_SESSION;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.alert.rsrcharea.stock.service.IRsrchAreaStockPriceAlertService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.alert.rsrcharea.stock.IWebRsrchAreaStockPriceAlert;

@Controller
public class WebRsrchAreaStockPriceAlertImpl implements IWebRsrchAreaStockPriceAlert {

	@Autowired
	private IRsrchAreaStockPriceAlertService priceService;

	@Override
	public ResponseEntity<?> addPriceAlert(@RequestBody CompanyPriceAlertPojo companyPriceAlertPojo,
			@RequestParam(value = "id", required = true) String id) throws WebApiException {
		try {
			boolean addStatus = priceService.addPriceAlert(companyPriceAlertPojo, id);
			if (addStatus) {
				return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Company price alert added successfully."),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Company price alert already added."),
						HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			ErrorUtil.logError("StockPriceAlert -> addPriceAlert(...) method", e);
			return ErrorUtil.getError(ADD_COMPANY_PRICE_ALERT.getCode(), ADD_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}

	@Override
	public ResponseEntity<?> findAllRsrchAreaStockPriceAlerts(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) throws WebApiException {
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			List<CompanyPriceAlertPojo> allPriceAlert = priceService.findAllPriceAlerts(userName);
			return new ResponseEntity<List<CompanyPriceAlertPojo>>(allPriceAlert, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("StockPriceAlert -> findAllRsrchAreaStockPriceAlerts(...) method", e);
			return ErrorUtil.getError(FIND_ALL_COMPANY_PRICE_ALERT.getCode(),
					FIND_ALL_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}

}
