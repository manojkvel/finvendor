package com.finvendor.api.consumer.service;

import com.finvendor.api.consumer.dao.ConsumerDao;
import com.finvendor.api.consumer.dto.ConsumerMyProfileBusinessNeedMarketData;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;
import com.finvendor.modelpojo.staticpojo.FileDetails;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class.getName());

    @Autowired
    private ConsumerDao consumerDao;

    public Consumer updateConsumerDetails(Consumer consumer) throws ApplicationException {
        logger.debug("ConsumerServiceImpl : saveConsumerInfo");
        Consumer updatedConsumer = consumerDao
                .updateConsumerDetails(consumer);
        return updatedConsumer;
    }

    public Consumer getConsumerInfoByEmail(String email)
            throws ApplicationException {
        logger.debug("ConsumerServiceImpl : getConsumerInfoByEmail");
        return consumerDao.getConsumerInfoByEmail(email);
    }

    public CompanySubType getCompanySubType(int id)
            throws ApplicationException {
        logger.debug("ConsumerServiceImpl : getCompanySubType");
        return consumerDao.getCompanySubType(id);
    }

    public List<Object[]> loadConsumerMyProfile(String consumerId,
            String tableKey) throws ApplicationException {
        List<Object[]> tableRowList = consumerDao.loadConsumerMyProfile(
                consumerId, tableKey);
        return tableRowList;
    }

    public Set<ConsumerMyProfileBusinessNeedMarketData>
    updateConsumerMyProfileBusinessNeedMarketData(String consumerId,
            String tableKey, String jsonTableData) throws ApplicationException {
        logger.debug("Entering ConsumerServiceImpl : updateConsumerProfileDetails for Consumer {}",
                consumerId);
        List<Object[]> tableRowList = new ArrayList<Object[]>();
        Set<ConsumerMyProfileBusinessNeedMarketData> tableData = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonTableData = jsonTableData.replace(",\"\":\"\"", "");
            tableData = mapper.readValue(jsonTableData,
                    new TypeReference<Set<ConsumerMyProfileBusinessNeedMarketData>>() {
                    });
            for (ConsumerMyProfileBusinessNeedMarketData record : tableData) {
                Object[] tableRow = new Object[6];
                tableRow[0] = record.getAssetClass();
                tableRow[1] = record.getSecurityType();
                tableRow[2] = record.getDataCoverageRegion();
                tableRow[3] = record.getDataCoverageCountry();
                tableRow[4] = record.getDataCoverageExchange();
                tableRow[5] = record.getDataAttribute();
                tableRowList.add(tableRow);
            }
            consumerDao.updateConsumerProfileDetails(consumerId, tableKey, tableRowList);
        } catch (Exception exp) {
            logger.error("Error updating Profile Details for Consumer : {}", consumerId, exp);
            throw new ApplicationException("Error updating Profile Details");
        }
        return tableData;
    }

    public Object updateConsumerLogo(FileDetails ufile, String username) {
        return consumerDao.updateConsumerLogo(ufile, username);
    }
}
