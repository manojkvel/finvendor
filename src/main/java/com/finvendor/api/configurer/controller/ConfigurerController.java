package com.finvendor.api.configurer.controller;

import com.finvendor.api.configurer.dto.ConfigurationDto;
import com.finvendor.api.configurer.service.ConfigurerService;
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
public class ConfigurerController {

    private final ConfigurerService configurerService;

    @Autowired
    public ConfigurerController(ConfigurerService configurerService) {
        this.configurerService = configurerService;
    }

    @PutMapping(value = "/configurations/{id}/configuration")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ConfigurationDto configurationDto) throws Exception {
        configurerService.update(id, configurationDto);
        return WebUtils.buildResponseEntity(WebUtils.buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK));
    }

    @GetMapping(value = "/configurations")
    public ResponseEntity<ApiResponse<String, List<Configuration>>> findAll() {
        return WebUtils.buildResponseEntity(WebUtils.buildResponse(ApiMessageEnum.SUCCESS, configurerService.findAll(), HttpStatus.OK));
    }
}
