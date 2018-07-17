package com.finvendor.serverwebapi.resources.metrics.impl;

import static com.finvendor.common.exception.ExceptionEnum.REQUEST_METRICS;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.metrics.dto.MetricsDto;
import com.finvendor.server.metrics.service.MetricService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.metrics.IWebMetrics;

@Controller
public class WebMetricsImpl implements IWebMetrics {

	@Autowired
	private MetricService metricsService;

	@Override
	public ResponseEntity<?> getRequestMetrics(@RequestParam(value = "type", required = false) String type) throws WebApiException {
		try {
			Collection<MetricsDto> requestMetrics = metricsService.getRequestMetrics(type);
			return new ResponseEntity<Collection<MetricsDto>>(requestMetrics, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebMetrics -> getRequestMetrics(...) method", e);
			return ErrorUtil.getError(REQUEST_METRICS.getCode(), REQUEST_METRICS.getUserMessage(), e);
		}
	}

}
