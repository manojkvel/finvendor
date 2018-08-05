package com.finvendor.server.companyprofile.pricealert.service;

import com.finvendor.server.companyprofile.pricealert.dto.ConsumerPriceAlertDetailsWrapper;

public interface IConsumerPriceAlertMailService {
	ConsumerPriceAlertDetailsWrapper buildConsumerPriceAlertDetails() throws Exception;
	String getIsinCode(String companyId) throws Exception;
}
