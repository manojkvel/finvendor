package com.finvendor.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.bean.ConsumerMyProfileBusinessNeedMarketData;
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
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> loadConsumerProfileDetails(String consumerId, String tableKey) {
		
		
		return null;
	}
	
	@Override
	@Transactional
	public List<Object[]> updateConsumerProfileDetails(String consumerId, 
			String tableKey, String jsonTableData) throws ApplicationException {
		logger.debug("Entering ConsumerServiceImpl : updateConsumerProfileDetails for Consumer {}", 
				consumerId);
		List<Object[]> tableRowList = new ArrayList<Object[]>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonTableData = jsonTableData.replace(",\"\":\"\"", "");
			Set<ConsumerMyProfileBusinessNeedMarketData> tableData = mapper.readValue(jsonTableData, new TypeReference<Set<ConsumerMyProfileBusinessNeedMarketData>>(){});
			logger.info("ConsumerServiceImpl : updateConsumerProfileDetails - tableData size" + tableData.size());
			for(ConsumerMyProfileBusinessNeedMarketData record : tableData) {
				Object[] tableRow = new Object[6];
				tableRow[0] = record.getAssetClass();
				tableRow[1] = record.getSecurityType();
				tableRow[2] = record.getDataCoverageRegion();
				tableRow[3] = record.getDataCoverageCountry();
				tableRow[4] = record.getDataCoverageExchange();
				tableRow[5] = record.getDataAttribute();
				tableRowList.add(tableRow);
			}
			consumerDAO.updateConsumerProfileDetails(consumerId, tableKey, tableRowList);
		}catch (Exception exp) {
			logger.error("Error updating Profile Details for Consumer : {}", consumerId, exp);
			throw new ApplicationException("Error updating Profile Details");
		}		
		return tableRowList;		
	}
}
