package com.finvendor.serverwebapi.resources.companyprofile.pricealert.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConsumerPriceAlertDetailsWrapper implements Serializable {
	private static final long serialVersionUID = -3910074899198367900L;
	private Map<String, List<ConsumerPriceAlertDetails>> consumerPriceAlertDetailsMap;

	public ConsumerPriceAlertDetailsWrapper() {
		consumerPriceAlertDetailsMap = new LinkedHashMap<>();
	}

	public ConsumerPriceAlertDetailsWrapper(Map<String, List<ConsumerPriceAlertDetails>> consumerPriceAlertDetailsMap) {
		super();
		this.consumerPriceAlertDetailsMap = consumerPriceAlertDetailsMap;
	}

	public Map<String, List<ConsumerPriceAlertDetails>> getConsumerPriceAlertDetailsMap() {
		return consumerPriceAlertDetailsMap;
	}

	public void setConsumerPriceAlertDetailsMap(
			Map<String, List<ConsumerPriceAlertDetails>> consumerPriceAlertDetailsMap) {
		this.consumerPriceAlertDetailsMap = consumerPriceAlertDetailsMap;
	}

}
