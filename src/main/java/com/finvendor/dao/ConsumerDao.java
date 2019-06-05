//package com.finvendor.dao;
//
//import java.util.List;
//
//import com.finvendor.common.exception.ApplicationException;
//import com.finvendor.form.FileDetails;
//import com.finvendor.model.CompanySubType;
//import com.finvendor.model.Consumer;
//
//public interface ConsumerDao {
//
//	Consumer updateConsumerDetails(Consumer consumer) throws ApplicationException;
//	Consumer getConsumerInfoByEmail(String email) throws ApplicationException;
//	CompanySubType getCompanySubType(int id) throws ApplicationException;
//	List<Object[]> loadConsumerMyProfile(String consumerId, String tableKey)
//			throws ApplicationException;
//	List<Object[]> updateConsumerProfileDetails(String consumerId, String tableKey,
//			List<Object[]> tableData) throws ApplicationException;
//	public Object updateConsumerLogo(FileDetails ufile, String username);
//}
