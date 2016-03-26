package com.finvendor.dao;

import java.util.List;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;

public interface ConsumerDAO {

	Consumer updateConsumerDetails(Consumer consumer) throws ApplicationException;
	Consumer getConsumerInfoByEmail(String email) throws ApplicationException;
	CompanySubType getCompanySubType(int id) throws ApplicationException;
	List<Object[]> loadConsumerMyProfile(String consumerId, String tableKey) 
			throws ApplicationException;
	List<Object[]> updateConsumerProfileDetails(String consumerId, String tableKey, 
			List<Object[]> tableData) throws ApplicationException;

}
