package com.finvendor.serverwebapi.resources.researchreport.mf.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import com.finvendor.serverwebapi.resources.researchreport.mf.service.MfProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.MF_PROFILE;
import static com.finvendor.common.exception.ExceptionEnum.SECTOR_RESEARCH_FILTER;

/**
 * @Author Ayush on 4-Oct-2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class MfProfileController {
    private static final Logger logger = LoggerFactory.getLogger(MfProfileController.class.getName());

    @Autowired
    private MfProfileService mfProfileService;

    /**
     * Get MF (Mutual Fund) Profile Data
     */
    @RequestMapping(value = "/mutualfund/profiles", method = RequestMethod.GET)
    public ResponseEntity<?> getMfProfiles(@RequestParam("schemeNavName") String schemeNavName) throws WebApiException {
        logger.info("schemeNavName: {}", schemeNavName);
        try {
            String mfProfile = mfProfileService.getMFProfile(schemeNavName);
            return new ResponseEntity<>(mfProfile, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error in Mutial Fund Profile Controller, Error: {}", e);
            return ErrorUtil.getError(MF_PROFILE.getCode(), MF_PROFILE.getUserMessage(), e);
        }
    }
}
