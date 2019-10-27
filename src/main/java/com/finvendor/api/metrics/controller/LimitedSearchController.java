package com.finvendor.api.metrics.controller;

import com.finvendor.api.metrics.dto.FeatureAllowedDto;
import com.finvendor.api.metrics.enums.FeatureAccessEnum;
import com.finvendor.api.metrics.enums.FvFeature;
import com.finvendor.api.metrics.service.LimitedSearchService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LimitedSearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LimitedSearchController.class.getName());
    private final LimitedSearchService limitedSearchService;

    @Autowired
    public LimitedSearchController(LimitedSearchService limitedSearchService) {
        this.limitedSearchService = limitedSearchService;
    }

    @GetMapping(value = "/limitedSearches")
    public ResponseEntity<ApiResponse<String, FeatureAllowedDto>> isSearchAllowed(
            @RequestParam(value = "currentDate", required = false) String currentDate,
            @RequestParam(value = "featureName") FvFeature fvFeature) throws Exception {
        LOGGER.info("## CONTROLLER isSearchAllowed - START currentDate: {}", currentDate);
        int dayNumOfWeek = limitedSearchService.findDayNumOfWeek(currentDate);
        FeatureAllowedDto featureAllowedDto = limitedSearchService.isAllowedForSearch(fvFeature);
        FeatureAccessEnum featureAccess = featureAllowedDto.getFeatureAccess();
        String name = featureAccess.name();
        return WebUtils.buildResponseEntity(WebUtils.buildResponse(ApiMessageEnum.SUCCESS, featureAllowedDto, HttpStatus.OK));
    }
}
