package com.finvendor.api.companyprofile.watchlist.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.companyprofile.watchlist.service.CompanyWatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on April 30, 2018
 */
@Controller
@RequestMapping(value = "/system/api")
public class CompanyWatchlistController {

    @Autowired
    CompanyWatchlistService service;

    @RequestMapping(value = "/companywatchlist/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCompanyWatchList(HttpServletRequest request,
                                                 @RequestBody CompanyWatchListPojo companyWatchListPojo) throws WebApiException {
        try {
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            companyWatchListPojo.setUserName(userName);
            final boolean addStatus = service.addCompanyWatchList(companyWatchListPojo);
            if (addStatus) {
                return new ResponseEntity<StatusPojo>(new StatusPojo("true", "Company Watchlist added successfully"),
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Company Watchlist already added!"),
                        HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyWatchList -> addCompanyWatchList(...) method", e);
            return ErrorUtil.getError(ADD_WATCHLIST.getCode(), ADD_WATCHLIST.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/companywatchlist/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCompanyWatchlist(HttpServletRequest request,
                                                    @RequestBody List<CompanyWatchListPojo> companyWatchListPojoList) throws WebApiException {

        try {
            final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            String userName = loggedInUser.getUsername();
            for (CompanyWatchListPojo pojo : companyWatchListPojoList) {
                pojo.setUserName(userName);
            }
            final boolean deleteStatus = service.deleteCompnayWatchList(companyWatchListPojoList);
            if (deleteStatus) {
                return new ResponseEntity<StatusPojo>(
                        new StatusPojo("true", "All company watchlist deleted successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<StatusPojo>(new StatusPojo("false", "Unable to delete company watchlist"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyWatchList -> deleteCompanyWatchlist(...) method", e);
            return ErrorUtil.getError(DELETE_WATCHLIST.getCode(), DELETE_WATCHLIST.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/companywatchlist/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllCompanyWatchlist(HttpServletRequest request) throws WebApiException {
        try {
            final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();

            final List<CompanyWatchListPojo> findAllWatchList = service.findAllCompanyWatchList(userName);
            return new ResponseEntity<List<CompanyWatchListPojo>>(findAllWatchList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyWatchList -> findAllCompanyWatchlist(...) method", e);
            return ErrorUtil.getError(FIND_WATCHLIST.getCode(), FIND_WATCHLIST.getUserMessage(), e);
        }
    }
}
