package com.finvendor.api.configuration.controller;

import com.finvendor.api.configuration.dto.SysConfigDto;
import com.finvendor.api.configuration.service.SysConfig;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SysConfigController {

    private final SysConfig sysConfig;

    @Autowired
    public SysConfigController(SysConfig sysConfig) {
        this.sysConfig = sysConfig;
    }

    @PutMapping(value = "/configurations/{id}/configuration")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SysConfigDto sysConfigDto) throws Exception {
        sysConfig.update(id, sysConfigDto);
        return WebUtils.buildResponseEntity(WebUtils.buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK));
    }

    @GetMapping(value = "/configurations")
    public ResponseEntity<ApiResponse<String, List<Configuration>>> findAll() {
        return WebUtils.buildResponseEntity(WebUtils.buildResponse(ApiMessageEnum.SUCCESS, sysConfig.findAll(), HttpStatus.OK));
    }
}
