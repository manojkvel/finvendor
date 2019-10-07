package com.finvendor.api.example.controller;

import com.finvendor.api.example.dto.ExampleDto;
import com.finvendor.api.example.dto.ExampleRequestDto;
import com.finvendor.api.example.service.ExampleService;
import com.finvendor.api.exception.ApiResourceNotFoundException;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author ayush on April 30, 2018
 */
@Api(tags={"REST APIs related to Example!!!!"})
@RestController
@RequestMapping(value = "/api")
@Validated
public class ExampleController {

    private ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello Friends...How r u ", HttpStatus.OK);
    }

    /**
     * SAVE Example
     */
    @ApiOperation(value = "Saving Example Resource")
    @PostMapping(value = "/examples", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String, Void>> saveExample(@Valid @RequestBody ExampleRequestDto exampleRequestDto) throws Exception {
        exampleService.saveOrUpdateExample(exampleRequestDto);
        ApiResponse<String, Void> apiResponse = WebUtils.buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK);
        return WebUtils.buildResponseEntity(apiResponse);
    }

    /**
     * Find All Examples
     */
    @GetMapping(value = "/examples", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String, List<ExampleRequestDto>>> findAllExample() throws Exception {
        List<ExampleRequestDto> allExample = exampleService.findAllExample();
        ApiResponse<String, List<ExampleRequestDto>> apiResponse;
        if (allExample != null) {
            apiResponse = WebUtils.buildResponse(ApiMessageEnum.SUCCESS, allExample, HttpStatus.OK);
        }
        else {
            throw new ApiResourceNotFoundException(WebUtils.RESOURCE_NOT_FOUND_ERR_MSG);
        }
        return WebUtils.buildResponseEntity(apiResponse);
    }

    /**
     * Find one Example
     */
    @GetMapping(value = "/examples/{exampleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String, ExampleDto>> findExample(
            @PathVariable @Size(min = 1, max = 3, message = "example id must be [1-3] digit max ") String exampleId) throws Exception {
        ApiResponse<String, ExampleDto> apiResponse;
        ExampleDto exampleDto = exampleService.findExampleById(Integer.parseInt(exampleId));
        if (exampleDto != null) {
            apiResponse = WebUtils.buildResponse(ApiMessageEnum.SUCCESS, exampleDto, HttpStatus.OK);
        }
        else {
            throw new ApiResourceNotFoundException(WebUtils.RESOURCE_NOT_FOUND_ERR_MSG);
        }
        return WebUtils.buildResponseEntity(apiResponse);
    }

    /**
     * Update Example
     */
    @RequestMapping(value = "/examples/{exampleId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateExample(@PathVariable String exampleId, @RequestBody ExampleRequestDto exampleRequestDto) throws WebApiException {
        exampleService.updateExample(exampleRequestDto);
    }
}
