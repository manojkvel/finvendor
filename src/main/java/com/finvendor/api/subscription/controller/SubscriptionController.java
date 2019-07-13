package com.finvendor.api.subscription.controller;

import com.finvendor.api.subscription.dto.SubscriptionRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/finvendor/api")
@Api(value="Employee Service")
public class SubscriptionController {

    @PutMapping(value = "/user/{userName}/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Create Employee",notes="Creation of a new Employee",response=SubscriptionRequestDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee Created successfully"),
            @ApiResponse(code = 401, message = "You are Not authorized to create Employee"),
            @ApiResponse(code = 403, message = "Create Employee is forbidden"),
            @ApiResponse(code = 404, message = "Resource Not found")
    })
    public RequestEntity<?> saveSubscription(@PathVariable String userName, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return null;
    }
}
