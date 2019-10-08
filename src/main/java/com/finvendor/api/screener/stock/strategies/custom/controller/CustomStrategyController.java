package com.finvendor.api.screener.stock.strategies.custom.controller;

import com.finvendor.api.metrics.dto.FeatureAllowedDto;
import com.finvendor.api.metrics.enums.FvFeature;
import com.finvendor.api.screener.stock.strategies.custom.dto.CustomStrategyDto;
import com.finvendor.api.screener.stock.strategies.custom.dto.Filters;
import com.finvendor.api.screener.stock.strategies.custom.dto.filter.CustomFilter;
import com.finvendor.api.screener.stock.strategies.custom.service.CustomStrategyService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.exception.ExceptionEnum;
import com.finvendor.common.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.finvendor.api.webutil.WebUtils.getLoggedInUser;
import static com.finvendor.api.webutil.WebUtils.isUserAllowedToAccessFeature;

@RestController
@RequestMapping(value = "/api")
public class CustomStrategyController {

    @Autowired
    private CustomStrategyService service;

    @PostMapping(value = "/customscreeners/feeddata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> feedCustomScreenerData() {
        try {
            service.insertCustomScreenerData();
            return new ResponseEntity<>("Custom Screener data feed completed successfully", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> feedCustomScreenerData(...)", e);
            return ErrorUtil
                    .getError(ExceptionEnum.CUSTOM_STRATEGY_DATA_FEED.getCode(), ExceptionEnum.CUSTOM_STRATEGY_DATA_FEED.getUserMessage(),
                            e);
        }
    }

    @GetMapping(value = "/customscreeners/filters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllFilters() {
        return new ResponseEntity<>(new Filters(service.findIndustryFilterData(), service.findSliderFilterData()), HttpStatus.OK);
    }

    @PostMapping(value = "/customscreeners/recordstats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findRecordStats(HttpServletRequest request,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords, @RequestBody CustomFilter customFilter
    ) {
        try {
            FeatureAllowedDto featureAllowedDto = isUserAllowedToAccessFeature(getLoggedInUser(request),
                    FvFeature.DO_IT_YOUR_SELF);
            if (featureAllowedDto != null) {
                return WebUtils.buildResponseEntity(
                        WebUtils.buildResponse(ApiMessageEnum.RESOURCE_NOT_FOUND, featureAllowedDto, HttpStatus.NOT_FOUND));
            }
            String recordStats = service.findRecordStats(perPageMaxRecords, customFilter);
            return new ResponseEntity<>(recordStats, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> findRecordStats(...)", e);
            return ErrorUtil.getError(ExceptionEnum.CUSTOM_STRATEGY.getCode(), ExceptionEnum.CUSTOM_STRATEGY.getUserMessage(), e);
        }
    }

    @PostMapping(value = "/customscreeners", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCustomScreeners(HttpServletRequest request,
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy, @RequestBody CustomFilter customFilter
    ) {
        try {
            FeatureAllowedDto featureAllowedDto = isUserAllowedToAccessFeature(getLoggedInUser(request),
                    FvFeature.DO_IT_YOUR_SELF);
            if (featureAllowedDto != null) {
                return WebUtils.buildResponseEntity(
                        WebUtils.buildResponse(ApiMessageEnum.RESOURCE_NOT_FOUND, featureAllowedDto, HttpStatus.NOT_FOUND));
            }
            List<CustomStrategyDto> customScreeners = service
                    .findCustomScreeners(pageNumber, perPageMaxRecords, sortBy, orderBy, customFilter);

            return new ResponseEntity<>(customScreeners, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> findCustomScreeners(...)", e);
            return ErrorUtil.getError(ExceptionEnum.CUSTOM_STRATEGY_DATA.getCode(), ExceptionEnum.CUSTOM_STRATEGY_DATA.getUserMessage(), e);
        }
    }
}
