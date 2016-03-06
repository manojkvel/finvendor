package com.finvendor.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.controller.ConsumerController;
import com.finvendor.dao.ConsumerDAO;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;
import com.finvendor.service.ConsumerService;

public class ConsumerServiceImpl implements ConsumerService {

	private static Logger logger = LoggerFactory.getLogger(
			ConsumerController.class);

	@Autowired
	private ConsumerDAO consumerDAO;

	@Override
	@Transactional
	public Consumer updateConsumerDetails(Consumer consumer) 
			throws ApplicationException {
		logger.debug("ConsumerServiceImpl : saveConsumerInfo");
		Consumer updatedConsumer = consumerDAO
				.updateConsumerDetails(consumer);
		return updatedConsumer;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Consumer getConsumerInfoByEmail(String email) 
			throws ApplicationException {
		logger.debug("ConsumerServiceImpl : getConsumerInfoByEmail");
		return consumerDAO.getConsumerInfoByEmail(email);
	}
	
	@Override
	@Transactional(readOnly=true)
	public CompanySubType getCompanySubType(int id) 
			throws ApplicationException {
		logger.debug("ConsumerServiceImpl : getCompanySubType");
		return consumerDAO.getCompanySubType(id);
	}
}
