package com.finvendor.api.resources.companyprofile.pricealert.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.api.resources.WebUriConstants;
import com.finvendor.api.resources.companyprofile.pricealert.service.ConsumerPriceAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on May 12, 2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class ConsumerPriceAlertController {//implements IWebConsumerPriceAlert {

    @Autowired
    private ConsumerPriceAlertService consumerPriceAlertService;

    private static final String LOGGED_IN_USER = "loggedInUser";
    private static final String FALSE_STATUS = "false";

    @RequestMapping(value = "/companypricealert/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCompanyPriceAlert(HttpServletRequest request,
                                                  @RequestBody ConsumerPriceAlertDTO companyPriceAlertPojo) {
        LogUtil.logInfo("IWebCompanyPriceAlert -> addCompanyPriceAlert - START");
        try {
            final User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            companyPriceAlertPojo.setUserName(userName);
            final boolean addStatus = consumerPriceAlertService.addConsumerPriceAlert(companyPriceAlertPojo);
            if (addStatus) {
                return new ResponseEntity<>(new StatusPojo("true", "Company price alert added successfully."),
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new StatusPojo(FALSE_STATUS, "Company price alert already added."),
                        HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyPriceAlert -> addCompanyPriceAlert(...) method", e);
            return ErrorUtil.getError(ADD_COMPANY_PRICE_ALERT.getCode(), ADD_COMPANY_PRICE_ALERT.getUserMessage(), e);
        }
    }

    // Update
    @RequestMapping(value = "/companypricealert/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCompanyPriceAlert(HttpServletRequest request,
                                                     @RequestBody ConsumerPriceAlertDTO companyPriceAlertPojo) {
        LogUtil.logInfo("IWebCompanyPriceAlert -> updateCompanyPriceAlert - START");
        try {
            final User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            companyPriceAlertPojo.setUserName(userName);
            final boolean updatePriceAlertStatus = consumerPriceAlertService.updateConsumerPriceAlert(companyPriceAlertPojo);
            if (updatePriceAlertStatus) {
                return new ResponseEntity<>(
                        new StatusPojo("true", "Company price alert updated successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new StatusPojo(FALSE_STATUS, "Unable to update company price alert"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyPriceAlert -> updateCompanyPriceAlert(...) method", e);
            return ErrorUtil.getError(UPDATE_COMPANY_PRICE_ALERT.getCode(), UPDATE_COMPANY_PRICE_ALERT.getUserMessage(),
                    e);
        }
    }

    // Delete
    @RequestMapping(value = "/companypricealert/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCompanyPriceAlert(HttpServletRequest request,
                                                     @RequestBody List<ConsumerPriceAlertDTO> pojoList) {
        LogUtil.logInfo("IWebCompanyPriceAlert -> deleteCompanyPriceAlert - START");
        try {
            final User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            for (final ConsumerPriceAlertDTO dto : pojoList) {
                dto.setUserName(userName);
            }
            final boolean deletePriceAlertStatus = consumerPriceAlertService.deleteConsumerPriceAlerts(pojoList);
            if (deletePriceAlertStatus) {
                return new ResponseEntity<>(
                        new StatusPojo("true", "All company price alert deleted successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new StatusPojo(FALSE_STATUS, "Unable to delete company price alert"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyPriceAlert -> deleteCompanyPriceAlert(...) method", e);
            return ErrorUtil.getError(DELETE_COMPANY_PRICE_ALERT.getCode(), DELETE_COMPANY_PRICE_ALERT.getUserMessage(),
                    e);
        }
    }

    // Find
    @RequestMapping(value = "/companypricealert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCompanyPriceAlert(HttpServletRequest request,
                                                   @RequestParam(value = "companyId") String companyId) {
        LogUtil.logInfo("IWebCompanyPriceAlert -> findCompanyPriceAlert - START");
        try {
            final User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            final ConsumerPriceAlertDTO pojo = consumerPriceAlertService.findConsumerPriceAlert(companyId, userName);
            return new ResponseEntity<>(pojo, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyPriceAlert -> findCompanyPriceAlert(...) method", e);
            return ErrorUtil.getError(FIND_COMPANY_PRICE_ALERT.getCode(), FIND_COMPANY_PRICE_ALERT.getUserMessage(), e);
        }
    }

    // Fina All
    @RequestMapping(value = "/companypricealert/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllCompanyPriceAlert(HttpServletRequest request) {
        LogUtil.logInfo("IWebCompanyPriceAlert -> findAllCompanyPriceAlert - START");
        try {
            final User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            final List<ConsumerPriceAlertDTO> allPriceAlert = consumerPriceAlertService.findAllConsumerPriceAlert(userName);
            return new ResponseEntity<>(allPriceAlert, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("IWebCompanyPriceAlert -> findAllCompanyPriceAlert(...) method", e);
            return ErrorUtil.getError(FIND_ALL_COMPANY_PRICE_ALERT.getCode(),
                    FIND_ALL_COMPANY_PRICE_ALERT.getUserMessage(), e);
        }
    }
}