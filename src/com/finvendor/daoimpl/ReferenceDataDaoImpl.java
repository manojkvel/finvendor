package com.finvendor.daoimpl;

import java.io.Serializable;
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

@SuppressWarnings("unchecked")
public class ReferenceDataDaoImpl implements ReferenceDataDao {

	private static Logger logger = LoggerFactory.getLogger(ReferenceDataDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Object getModelObjectById(Class<?> type, Serializable id) {
		Session session = this.sessionFactory.getCurrentSession();
		Object modelObject = session.get(type, id);
		return modelObject;
	}
		
	/* Asset Class */
	
	@Override
	public AssetClass getAssetClassDetails(String assetClassDescription)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getAssetClassDetails for : {}", 
				assetClassDescription);
		AssetClass assetClass=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(AssetClass.class);
			criteria.add(Restrictions.sqlRestriction("lower(description) like '" + 
					assetClassDescription.toLowerCase() + "'"));
			assetClass = (AssetClass) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading AssetClass details for {}", assetClassDescription, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getAssetClassDetails for : {}", 
				assetClassDescription);
		return assetClass;
	}
	
	/* Security Types */
	
	@Override
	public SecurityType getSecurityTypes(String securityName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getSecurityTypes for : {}", 
				securityName);
		SecurityType securityType=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(
					SecurityType.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + 
					securityName.toLowerCase() + "'"));
			securityType = (SecurityType) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading SecurityTypes details for {}", 
					securityName, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getSecurityTypes for : {}", 
				securityName);
		return securityType;
	}
	
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
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getSecurityTypesForAssetClassId for {}", assetClassId);
		return securityTypesList;
	}
	
	/* Region */
	
	public List<Region> getAllRegions() throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getAllRegions");		
		List<Region> regions = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Region.class);
			regions = criteria.list();
 		}catch (Exception exp) {
 			logger.error("Error loading All getAllRegions", exp);
 			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getAllRegions");
		return regions;
	}
	
	
	@Override
	public Region getRegionByName(String regionName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getRegionByName for : {}", 
				regionName);
		Region region=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(
					Region.class, "region");
			criteria.add(Restrictions.sqlRestriction("lower({alias}.name) like '" + 
					regionName.toLowerCase() + "'"));
			region = (Region) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading Region details for {}", 
					regionName, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getRegionByName for : {}", 
				regionName);
		return region;
	}
	
	/* Country */
	
	public List<Country> getAllCountries() 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getAllCountries");		
		List<Country> countries = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
			countries = criteria.list();
 		}catch (Exception exp) {
 			logger.error("Error loading All Countries", exp);
 			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getAllCountries");
		return countries;
	}
	
	@Override
	public Country getCountryByName(String countryName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getCountryByName for : {}", 
				countryName);
		Country country=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class, "country");
			criteria.add(Restrictions.sqlRestriction("lower({alias}name) like '" + countryName.toLowerCase() + "'"));
			country = (Country) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading Country details for {}", 
					countryName, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getCountryByName for : {}", 
				countryName);
		return country;
	}
	
	@Override
	public List<Country> getCountriesByRegionId(String regionId) 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getCountriesByRegionId for : {}", 
				regionId);
		List<Country> countries = null;
		Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class, "country");
			criteria.add(Restrictions.sqlRestriction("{alias}.region_id = " + regionId ));
			countries = criteria.list(); 
		}catch (Exception exp) {
			logger.error("Error reading Country details for Region {}", 
					regionId, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getCountriesByRegionId for : {}", 
				regionId);
		return countries;
	}
	
	/* Exchange */
	
	@Override
	public List<Exchange> getAllExchanges() 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getAllExchanges");		
		List<Exchange> exchanges = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Exchange.class);
			exchanges = criteria.list();
 		}catch (Exception exp) {
 			logger.error("Error loading All Exchanges", exp);
 			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getAllExchanges");
		return exchanges;
	}
	
	@Override
	public Exchange getExchangeByName(String exchangeName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getExchangesByName for : {}", 
				exchangeName);
		Exchange exchange=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(
					Exchange.class, "exchange");
			criteria.add(Restrictions.sqlRestriction("lower({alias}name) like '" + 
			exchangeName.toLowerCase() + "'"));
			exchange = (Exchange) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading Exchange details for {}", 
					exchangeName, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getExchangesByName for : {}", 
				exchangeName);
		return exchange;
	}
	
	@Override
	public List<Exchange> getExchangesByCountryId(String countryId) 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getCountriesByRegionId for : {}", 
				countryId);
		List<Exchange> exchanges = null;
		Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Exchange.class, "exchange");
			criteria.add(Restrictions.sqlRestriction("{alias}.country_id = " + countryId ));
			exchanges = criteria.list(); 
		}catch (Exception exp) {
			logger.error("Error reading Country details for Region {}", 
					countryId, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getCountriesByRegionId for : {}", 
				countryId);
		return exchanges;
	}
	
	
}
