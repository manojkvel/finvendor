//package com.finvendor.api.common.service;
//
//import com.finvendor.common.exception.ApplicationException;
//import com.finvendor.model.*;
//import com.finvendor.modelpojo.staticpojo.ReferenceDataJson;
//
//import java.io.Serializable;
//import java.util.List;
//
//public interface ReferenceDataService {
//
//	public Object getModelObjectById(Class<?> type, Serializable id);
//
//	public AssetClass getAssetClassByDescription(String assetClassDescription)
//			throws ApplicationException;
//
//	public SecurityType getSecurityTypeByName(String securityName)
//			throws ApplicationException;
//	public List<SecurityType> getSecurityTypesForAssetClassId(
//            int assetClassId) throws ApplicationException;
//
//	public Region getRegionByName(String regionName)
//			throws ApplicationException;
//
//	public Country getCountryByName(String countryName)
//			throws ApplicationException;
//
//	public Exchange getExchangeByName(String exchangeName)
//			throws ApplicationException;
//
//	public List<ReferenceDataJson> getJsonReferenceData(String type, String parentId)
//            throws ApplicationException, ApplicationException;
//	public ReferenceDataJson getJsonReferenceDataById(String type,
//            String id)  throws ApplicationException;
//	public List<ReferenceDataJson> getJsonReferenceDataByParentId(
//            String type, String id) throws ApplicationException;
//}
