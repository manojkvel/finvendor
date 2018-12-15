package com.finvendor.api.resources.homepage.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.WebUriConstants;
import com.finvendor.api.resources.homepage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.HOME_PAGE;

/**
 * @author ayush on April 30, 2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class HomePageController {
    @Autowired
    HomePageService homePageService;

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ResponseEntity<?> getHomePageSearchHint(@RequestParam(value = "searchKey") String searchKey) throws WebApiException {
        try {
            final String homePageSearchHint = homePageService.getHomePageSearchHint(searchKey);
            return new ResponseEntity<>(homePageSearchHint, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("IWebHomePage -> getHomePageSearchHint(...) method", e);
            return ErrorUtil.getError(HOME_PAGE.getCode(), HOME_PAGE.getUserMessage(), e);
        }
    }
}
