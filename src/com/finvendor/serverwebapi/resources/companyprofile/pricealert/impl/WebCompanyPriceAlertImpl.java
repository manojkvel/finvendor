package com.finvendor.serverwebapi.resources.companyprofile.pricealert.impl;

import static com.finvendor.common.exception.ExceptionEnum.ADD_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.DELETE_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_ALL_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_COMPANY_PRICE_ALERT;
import static com.finvendor.common.exception.ExceptionEnum.FIND_USER_FROM_SESSION;
import static com.finvendor.common.exception.ExceptionEnum.UPDATE_COMPANY_PRICE_ALERT;

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
import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.companyprofile.pricealert.service.ICompanyPriceAlertService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.companyprofile.pricealert.IWebCompanyPriceAlert;

/**
 * @author ayush on May 12, 2018
 */
@Controller
public class WebCompanyPriceAlertImpl implements IWebCompanyPriceAlert {

	@Autowired
	private ICompanyPriceAlertService service;

	@Override
	public ResponseEntity<?> addCompanyPriceAlert(HttpServletRequest request, @RequestBody CompanyPriceAlertPojo companyPriceAlertPojo) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> addCompanyPriceAlert - START");
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			companyPriceAlertPojo.setUserName(userName);
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
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
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
}
