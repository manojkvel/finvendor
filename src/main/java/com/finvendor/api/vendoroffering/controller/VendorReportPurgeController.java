package com.finvendor.api.vendoroffering.controller;

import com.finvendor.api.vendoroffering.service.VendorOfferingPurgeService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author ayush
 */
@ApiIgnore
@RestController
@RequestMapping(value = "/api")
public class VendorReportPurgeController {

    private final VendorOfferingPurgeService service;

    @Autowired
    public VendorReportPurgeController(VendorOfferingPurgeService service) {
        this.service = service;
    }

    @DeleteMapping(value = "/vendorOfferings/purge")
    public ResponseEntity<ApiResponse<String, Void>> purgeVendorOffering() throws Exception {
        service.purgeVendorReport();
        ApiResponse<String, Void> apiResponse = WebUtils.buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK);
        return WebUtils.buildResponseEntity(apiResponse);
    }
}
