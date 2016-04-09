package com.finvendor.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.ReferenceDataDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;

public class ReferenceDataDaoImpl implements ReferenceDataDao {

	private static Logger logger = LoggerFactory.getLogger(ReferenceDataDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId)
			throws ApplicationException {		
		logger.debug("Entering : ReferenceDataDaoImpl - getSecurityTypesForAssetClassId for : {}", assetClassId);
		Criteria criteria = null;
		List<SecurityType> securityTypesList = null;
		try {
			criteria = this.sessionFactory.openSession().createCriteria(SecurityType.class);
			criteria.add(Restrictions.eq("assetClassId", assetClassId));
			securityTypesList = criteria.list();
			logger.debug("securityTypesList = " + securityTypesList.size());
		}catch (Exception exp) {
			logger.error("Error reding SecurityTypes for asset class id : {}", assetClassId, exp);
			throw new ApplicationException("Error reding SecurityTypes for asset class id : " + assetClassId);
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getSecurityTypesForAssetClassId for {}", assetClassId);
		return securityTypesList;
	}
	
	@Override
	public AssetClass getAssetClassDetails(String asset_class)
			throws ApplicationException {
		logger.info("getAssetClassDetails method---");
		AssetClass assetClass=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(AssetClass.class);
			criteria.add(Restrictions.sqlRestriction("lower(description) like '" + asset_class.toLowerCase() + "'"));
			assetClass = (AssetClass) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getAssetClassDetails---- " + ex);
		}
		return assetClass;
	}
	
	@Override
	public SecurityType getSecurityTypes(String security)
			throws ApplicationException {
		logger.info("getSecurityTypes method---");
		SecurityType securityType=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(SecurityType.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + security.toLowerCase() + "'"));
			securityType = (SecurityType) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getSecurityTypes---- " + ex);
		}
		return securityType;
	}
	
	@Override
	public Region getRegionsByName(String regionsName)
			throws ApplicationException {
		logger.info("getRegionsByName method---");
		Region region=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Region.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + regionsName.toLowerCase() + "'"));
			region = (Region) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getRegionsByName---- " + ex);
		}
		return region;
	}
	
	@Override
	public Country getCountryByName(String countryName)
			throws ApplicationException {
		logger.info("getCountryByName method---");
		Country country=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + countryName.toLowerCase() + "'"));
			country = (Country) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getCountryByName---- " + ex);
		}
		return country;
	}
	
	@Override
	public Exchange getExchangesByName(String exchangeName)
			throws ApplicationException {
		logger.info("getExchangesByName method---");
		Exchange exchange=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Exchange.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + exchangeName.toLowerCase() + "'"));
			exchange = (Exchange) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getExchangesByName---- " + ex);
		}
		return exchange;
	}
	
	@Override
	public String getRegion(String countryId)
			throws ApplicationException {
		Session currentSession = sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(countryId));
		Region region = country.getRegion();
		return region.getName();
	}
	
	@Override
	public Country getCountryById(String countryId)
			throws ApplicationException {
		Session currentSession = sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(countryId));
		return country;
	}
}
