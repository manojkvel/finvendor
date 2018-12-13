package com.finvendor.serverwebapi.resources.vendoroffering.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.Vendor;
import com.finvendor.serverwebapi.resources.companyprofile.pricealert.controller.ConsumerPriceAlertMailController;
import com.finvendor.serverwebapi.resources.companyprofile.pricealert.service.ConsumerPriceAlertService;
import com.finvendor.serverwebapi.resources.vendoroffering.dto.VendorReportDataDto;
import com.finvendor.serverwebapi.resources.vendoroffering.dto.VendorReportFileDto;
import com.finvendor.serverwebapi.resources.vendoroffering.service.VendorReportService;
import com.finvendor.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.finvendor.common.exception.ExceptionEnum.VO_OP;

@RestController
@RequestMapping(value = "/system/api")
public class VendorReportController {

    @Autowired
    private VendorReportService service;

    @Autowired
    private ConsumerPriceAlertMailController consumerPriceAlertMailController;

    @Autowired
    private ConsumerPriceAlertService consumerPriceAlertService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/vendorreports/create")
    public ResponseEntity<?> saveVO(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "productId", required = false) String productId,
                                    @RequestParam(value = "productName") String productName,
                                    @RequestParam(value = "productDescription") String productDescription,
                                    @RequestParam(value = "launchedYear") String launchedYear,
                                    @RequestParam(value = "researchAreaId") Integer researchAreaId,
                                    @RequestParam(value = "researchSubAreaId") String researchSubAreaId,
                                    @RequestParam(value = "suitability", required = false) String suitability,
                                    @RequestParam(value = "subsriptionCostPerAnnum", required = false) String subsriptionCostPerAnnum,
                                    @RequestParam(value = "researchReportForId") String researchReportForId,
                                    @RequestParam(value = "priceAtRecomm") String priceAtRecomm,
                                    @RequestParam(value = "targetPrice") String targetPrice,
                                    @RequestParam(value = "recommendationType") String recommendationType,
                                    @RequestParam(value = "reportDate") String reportDate,
                                    @RequestParam(value = "reportDescription") String reportDescription,
                                    @RequestParam(value = "reportAccess", required = false) String reportAccess,
                                    @RequestParam(value = "analystName", required = false) String analystName,
                                    @RequestParam(value = "analystWithCfaCharter", required = false) String analystWithCfaCharter,
                                    @RequestParam(value = "analystWithAwards", required = false) String analystWithAwards,
                                    @RequestParam(value = "reportFile") CommonsMultipartFile reportFile) {
        try {
            //User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            String userName = "ays_broker";//loggedInUser.getUsername();
            Vendor vendor = userService.getUserDetailsByUsername(userName).getVendor();

            VendorReportDataDto vendorReportDataDto = new VendorReportDataDto();
            VendorReportFileDto vendorReportFileDto = new VendorReportFileDto();

            vendorReportDataDto.setProductId(productId);
            vendorReportDataDto.setProductName(productName);
            vendorReportDataDto.setProductDescription(productDescription);
            vendorReportDataDto.setLaunchedYear(launchedYear);
            vendorReportDataDto.setResearchAreaId(String.valueOf(researchAreaId));
            vendorReportDataDto.setResearchSubAreaId(String.valueOf(researchSubAreaId));
            vendorReportDataDto.setSuitability(suitability);
            vendorReportDataDto.setSubscriptionCostPerAnnum(subsriptionCostPerAnnum);
            vendorReportDataDto.setResearchReportForId(researchReportForId);
            vendorReportDataDto.setPriceAtRecomm(priceAtRecomm);
            vendorReportDataDto.setResearchTargetPrice(targetPrice);
            vendorReportDataDto.setRecommType(recommendationType);
            vendorReportDataDto.setReportDate(reportDate);
            vendorReportDataDto.setReportDescription(reportDescription);
            vendorReportDataDto.setReportAccess(reportAccess);
            vendorReportDataDto.setAnalystName(analystName);
            vendorReportDataDto.setAnalystCfaCharter(analystWithCfaCharter);
            vendorReportDataDto.setAnalystAwarded(analystWithAwards);

            vendorReportDataDto.setVendorId(vendor.getId());
            vendorReportDataDto.setVendorAnalystType(vendor.getAnalystType());
            vendorReportDataDto.setVendorName(vendor.getUser().getUserName());
            vendorReportDataDto.setVendorCompany(vendor.getCompany());
            if (reportFile != null) {
                vendorReportDataDto.setReportName(reportFile.getOriginalFilename());
            }
            productId = service.saveVoData(vendorReportDataDto);
            if (reportFile != null) {
                vendorReportFileDto.setProductId(productId);
                byte[] fileContent = reportFile.getBytes();
                Blob blob = Hibernate.createBlob(fileContent);
                vendorReportFileDto.setReportFile(blob);
                service.saveVoFile(vendorReportFileDto);
            }

            //Send mail to logged in user if vendor upload new report
            //String companyName = service.getCompanyName(researchReportFor);
            //if (consumerPriceAlertService.isResearchPriceSet(companyName)) {
            //    consumerPriceAlertMailController.sendResearchReportAlertMail(userName, researchReportFor, companyName);
            //} else {
            //    LogUtil.logInfo("***Research Resport Alert is not set for comapny=" + companyName);
            //}
            return new ResponseEntity<>("VO report saved successfully with ProductId: " + productId, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("VendorReportController -> saveVO(...) method", e);
            return ErrorUtil.getError(VO_OP.getCode(), VO_OP.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/vendorreports/find")
    public ResponseEntity<?> findVo(@RequestParam(value = "productId") String productId) {
        try {
            VendorReportDataDto voData = service.findVoData(productId);
            Map<String, Object> dataMap = new LinkedHashMap<>();
            dataMap.put("data", voData);
            String resultJson = JsonUtil.createJsonFromParamsMap(dataMap);
            return new ResponseEntity<>(resultJson, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("VendorReportController -> saveVO(...) method", e);
            return ErrorUtil.getError(VO_OP.getCode(), VO_OP.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/vendorreports/findall")
    public ResponseEntity<?> findAllVo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userName = "ays_broker";//WebUtil.getLoggedInUser(request);
            List<VendorReportDataDto> voData = service.findAllVo(userName);
            Map<String, Object> dataMap = new LinkedHashMap<>();
            dataMap.put("data", voData);
            String resultJson = JsonUtil.createJsonFromParamsMap(dataMap);
            return new ResponseEntity<>(resultJson, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("VendorReportController -> saveVO(...) method", e);
            return ErrorUtil.getError(VO_OP.getCode(), VO_OP.getUserMessage(), e);
        }
    }

    @DeleteMapping(value = "/vendorreports/delete")
    public ResponseEntity<?> deleteVo(@RequestParam(value = "productId") String productId) {
        try {
            boolean deleteVoDataStatus = service.deleteVoData(productId);
            if (deleteVoDataStatus) {
                service.deleteVoFile(productId);
            } else {
                throw new Exception("Unable to delete VO for given productId: " + productId);
            }
            return new ResponseEntity<>("Vo deleted for product id: " + productId, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("VendorReportController -> deleteVo(...) method", e);
            return ErrorUtil.getError(VO_OP.getCode(), VO_OP.getUserMessage(), e);
        }
    }
}
