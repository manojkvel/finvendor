package com.finvendor.api.contactUs.controller;

import com.finvendor.api.contactUs.dto.ContactUsDto;
import com.finvendor.api.contactUs.service.ContactUsService;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.model.ContactUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.buildResponseEntity;

@RestController
@RequestMapping("/api")
public class ContactUsController {

    private ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping(value = "/contactUs")
    public ResponseEntity<ApiResponse<String, Object>> save(@RequestBody ContactUsDto contactUsDto) throws Exception {
        contactUsService.saveContact(contactUsDto);
        contactUsService.sendEmail(contactUsDto);
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK));
    }

    @GetMapping(value = "/contactUs")
    public ResponseEntity<ApiResponse<String, Object>> findAll() throws Exception {
        List<ContactUs> allContactUs = contactUsService.findAllContactUs();
        return buildResponseEntity(buildResponse(ApiMessageEnum.SUCCESS, allContactUs, HttpStatus.OK));
    }
}
