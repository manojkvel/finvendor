package com.finvendor.serverwebapi.resources.watchlist.rsrcharea.stock.impl;

import static com.finvendor.common.exception.ExceptionEnum.ADD_WATCHLIST;
import static com.finvendor.common.exception.ExceptionEnum.FIND_USER_FROM_SESSION;
import static com.finvendor.common.exception.ExceptionEnum.FIND_WATCHLIST;

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
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.watchlist.rsrcharea.stock.service.IRsrchAreaStockWatchListService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.watchlist.rsrcharea.stock.IWebRsrchStockWatchList;

/**
 * 
 * @author ayush on April 30, 2018
 */
@Controller
public class WebRsrchAreaStockWatchListImpl implements IWebRsrchStockWatchList {

	@Autowired
	IRsrchAreaStockWatchListService rsrchAreaStcockWatchListService;

	@Override
	public ResponseEntity<?> addCompanyWatchList(@RequestBody CompanyWatchListPojo companyWatchListPojo,
			@RequestParam(value = "id", required = true) String id) throws WebApiException {
		try {
			boolean addStatus = rsrchAreaStcockWatchListService.addStockWatchList(companyWatchListPojo, id);
			if (addStatus) {
				return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Company Watchlist added successfully."),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Company Watchlist already added."),
						HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			ErrorUtil.logError("CompanyWatchList -> addCompanyWatchList(...) method", e);
			return ErrorUtil.getError(ADD_WATCHLIST.getCode(), ADD_WATCHLIST.getUserMessage(), e);
		}
	}

	@Override
	public List<CompanyWatchListPojo> deleteCompanyWatchlist(String userName) throws WebApiException {
		return null;
	}

	@Override
	public ResponseEntity<?> findAllCompanyWatchlist(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) throws WebApiException {
		try {
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null) {
				return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
			}
			String userName = loggedInUser.getUsername();

			List<CompanyWatchListPojo> findAllWatchList = rsrchAreaStcockWatchListService
					.findAllStockWatchList(userName);
			return new ResponseEntity<List<CompanyWatchListPojo>>(findAllWatchList, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("CompanyWatchList -> findAllCompanyWatchlist(...) method", e);
			return ErrorUtil.getError(FIND_WATCHLIST.getCode(), FIND_WATCHLIST.getUserMessage(), e);
		}
	}
}
