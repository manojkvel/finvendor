package com.finvendor.serverwebapi.resources.metrics.impl;

import static com.finvendor.common.exception.ExceptionEnum.REQUEST_METRICS;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.metrics.service.MetricService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.metrics.IWebMetrics;

@Controller
public class WebMetricsImpl implements IWebMetrics {

	@Autowired
	private MetricService metricsService;

	@Override
	public ResponseEntity<?> getAllMetrics(@RequestParam(value = "type") String type) throws WebApiException {
		try {
			String requestMetrics = metricsService.getRequestMetrics(type);
			return new ResponseEntity<String>(requestMetrics, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebMetrics -> getAllMetrics(...) method", e);
			return ErrorUtil.getError(REQUEST_METRICS.getCode(), REQUEST_METRICS.getUserMessage(), e);
		}
	}

	@Override
	public ResponseEntity<?> getYearMetrics(@RequestParam(value = "type") String type,
											@RequestParam(value = "year") String year) throws WebApiException {
		return null;
	}

	@Override
	public ResponseEntity<?> getMonthMetrics(@RequestParam(value = "type") String type,
											 @RequestParam(value = "year") String year,
											 @RequestParam(value = "month") String month) throws WebApiException {
		return null;
	}

	@Override
	public ResponseEntity<?> getDayMetrics(@RequestParam(value = "type") String type,
										   @RequestParam(value = "year") String year,
										   @RequestParam(value = "month") String month,
										   @RequestParam(value = "day") String day) throws WebApiException {
		return null;
	}
}
