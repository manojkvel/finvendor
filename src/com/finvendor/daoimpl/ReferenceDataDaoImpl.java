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

@SuppressWarnings("unchecked")
public class ReferenceDataDaoImpl implements ReferenceDataDao {

	private static Logger logger = LoggerFactory.getLogger(ReferenceDataDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
		
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
	
	@Override
	public String getRegion(String countryId)
			throws ApplicationException {
		Session currentSession = sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(countryId));
		Region region = country.getRegion();
		return region.getName();
	}
	
	@Override
	public Region getRegionsByName(String regionName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getRegionsByName for : {}", 
				regionName);
		Region region=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(
					Region.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + 
					regionName.toLowerCase() + "'"));
			region = (Region) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading Region details for {}", 
					regionName, exp);
			throw new ApplicationException("Error fetching reference data details");
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getRegionsByName for : {}", 
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
	public Country getCountryById(String countryId)
			throws ApplicationException {
		Session currentSession = sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(countryId));
		return country;
	}
	
	@Override
	public Country getCountryByName(String countryName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getCountryByName for : {}", 
				countryName);
		Country country=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + countryName.toLowerCase() + "'"));
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
		
		return null;
	}
	
	/* Exchange */
	
	@Override
	public Exchange getExchangesByName(String exchangeName)
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataDaoImpl - getExchangesByName for : {}", 
				exchangeName);
		Exchange exchange=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(
					Exchange.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + 
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
		
}
