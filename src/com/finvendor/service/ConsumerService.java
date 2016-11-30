package com.finvendor.service;

import java.util.List;
import java.util.Set;

import com.finvendor.bean.ConsumerMyProfileBusinessNeedMarketData;
import com.finvendor.exception.ApplicationException;
import com.finvendor.form.FileDetails;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;

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
