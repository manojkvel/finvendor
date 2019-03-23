package com.finvendor.api.resources.companyprofile.companyprofile.controller;

import com.finvendor.api.resources.WebUriConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = WebUriConstants.BASE_URI)
public class CompanyProfilePersistController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyProfilePersistController.class.getName());

    public void persistCompanyNews(){

    }

    public void persistCorportateAction(){

    }

    public void persistCalendar(){

    }
}
