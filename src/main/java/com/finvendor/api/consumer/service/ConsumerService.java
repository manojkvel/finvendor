package com.finvendor.api.consumer.service;

import com.finvendor.api.consumer.dto.ConsumerMyProfileBusinessNeedMarketData;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;
import com.finvendor.modelpojo.staticpojo.FileDetails;

import java.util.List;
import java.util.Set;

public interface ConsumerService {
	Consumer updateConsumerDetails(Consumer consumer) throws ApplicationException;
	Consumer getConsumerInfoByEmail(String email) throws ApplicationException;
	CompanySubType getCompanySubType(int id) throws ApplicationException;
	List<Object[]> loadConsumerMyProfile(String consumerId, String tableKey)
			throws ApplicationException;
	Set<ConsumerMyProfileBusinessNeedMarketData> updateConsumerMyProfileBusinessNeedMarketData(
            String consumerId, String tableKey, String jsonTableData) throws ApplicationException;
	public Object updateConsumerLogo(FileDetails ufile, String username);
}
