package com.finvendor.serverwebapi.resources.alert.rsrcharea.stock;

import org.springframework.web.bind.annotation.RequestMapping;

import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebResearchAreaStockPriceAlert {

	String addPriceAlert();

	String addDayPriceAlert();

	String addWeekPriceAlert();

	String addMonthPriceAlert();

	String addResearchReportPriceAlert();
}
