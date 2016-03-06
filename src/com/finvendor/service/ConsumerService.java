package com.finvendor.service;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;

public interface ConsumerService {
	Consumer updateConsumerDetails(Consumer consumer) throws ApplicationException;
	Consumer getConsumerInfoByEmail(String email) throws ApplicationException;
	CompanySubType getCompanySubType(int id) throws ApplicationException;
}
