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
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.server.companyprofile.pricealert.service.IConsumerPriceAlertService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.companyprofile.pricealert.IWebConsumerPriceAlert;

/**
 * @author ayush on May 12, 2018
 */
@Controller
public class WebConsumerPriceAlertImpl implements IWebConsumerPriceAlert {

	@Autowired
	private IConsumerPriceAlertService consumerPriceAlertService;

	@Override
	public ResponseEntity<?> addCompanyPriceAlert(HttpServletRequest request,
			@RequestBody ConsumerPriceAlertDTO companyPriceAlertPojo) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> addCompanyPriceAlert - START");
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			companyPriceAlertPojo.setUserName(userName);
			boolean addStatus = consumerPriceAlertService.addConsumerPriceAlert(companyPriceAlertPojo);
			if (addStatus) {
				return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Company price alert added successfully."),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Company price alert already added."),
						HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> addCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(ADD_COMPANY_PRICE_ALERT.getCode(), ADD_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}

	// Update
	@Override
	public ResponseEntity<?> updateCompanyPriceAlert(HttpServletRequest request,
			@RequestBody ConsumerPriceAlertDTO companyPriceAlertPojo) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> updateCompanyPriceAlert - START");
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			companyPriceAlertPojo.setUserName(userName);
			boolean updatePriceAlertStatus = consumerPriceAlertService.updateConsumerPriceAlert(companyPriceAlertPojo);
			if (updatePriceAlertStatus) {
				return new ResponseEntity<StatusPojo>(
						new StatusPojo("true", "Company price alert updated successfully"), HttpStatus.OK);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Unable to update company price alert"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> updateCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(UPDATE_COMPANY_PRICE_ALERT.getCode(), UPDATE_COMPANY_PRICE_ALERT.getUserMessage(),
					e);
		}
	}

	// Delete
	@Override
	public ResponseEntity<?> deleteCompanyPriceAlert(HttpServletRequest request,
			@RequestBody List<ConsumerPriceAlertDTO> pojoList) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> deleteCompanyPriceAlert - START");
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			for (ConsumerPriceAlertDTO dto : pojoList) {
				dto.setUserName(userName);
			}
			boolean deletePriceAlertStatus = consumerPriceAlertService.deleteConsumerPriceAlerts(pojoList);
			if (deletePriceAlertStatus) {
				return new ResponseEntity<StatusPojo>(
						new StatusPojo("true", "All company price alert deleted successfully"), HttpStatus.OK);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Unable to delete company price alert"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> deleteCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(DELETE_COMPANY_PRICE_ALERT.getCode(), DELETE_COMPANY_PRICE_ALERT.getUserMessage(),
					e);
		}
	}

	// Find
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
			ConsumerPriceAlertDTO pojo = consumerPriceAlertService.findConsumerPriceAlert(companyId, userName);
			return new ResponseEntity<ConsumerPriceAlertDTO>(pojo, HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> findCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(FIND_COMPANY_PRICE_ALERT.getCode(), FIND_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}

	// Fina All
	@Override
	public ResponseEntity<?> findAllCompanyPriceAlert(HttpServletRequest request) throws WebApiException {
		LogUtil.logInfo("IWebCompanyPriceAlert -> findAllCompanyPriceAlert - START");
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();
			List<ConsumerPriceAlertDTO> allPriceAlert = consumerPriceAlertService.findAllConsumerPriceAlert(userName);
			return new ResponseEntity<List<ConsumerPriceAlertDTO>>(allPriceAlert, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyPriceAlert -> findAllCompanyPriceAlert(...) method", e);
			return ErrorUtil.getError(FIND_ALL_COMPANY_PRICE_ALERT.getCode(),
					FIND_ALL_COMPANY_PRICE_ALERT.getUserMessage(), e);
		}
	}
}
