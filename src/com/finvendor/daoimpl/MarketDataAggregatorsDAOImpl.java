/**
 * 
 */
package com.finvendor.daoimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.MarketDataAggregatorsDAO;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassDataDetails;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.CountryExchangeMap;
import com.finvendor.model.Exchange;
import com.finvendor.model.FileFields;
import com.finvendor.model.OfferingFiles;
import com.finvendor.model.Region;
import com.finvendor.model.RegionCountryMap;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorOffering;

/**
 * @author rayulu vemula
 *
 */
public class MarketDataAggregatorsDAOImpl implements MarketDataAggregatorsDAO{

	private static Logger logger = Logger.getLogger(MarketDataAggregatorsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAllAssetClass(com.finvendor.model.AssetClass)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AssetClass> getAllAssetClass() {
		logger.info("Method to load all asset classes---");
		List<AssetClass>  assetClasses = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(AssetClass.class);
			assetClasses = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all asset classes---");
		}
		return assetClasses;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getSecurityTypeByAssetClassId(com.finvendor.model.AssetClassSecurityMap)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId) {
		logger.info("Method to load security types---");
		Criteria criteria = null;
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try{
			criteria = this.sessionFactory.openSession().createCriteria(AssetClassSecurityMap.class);
			criteria.add(Restrictions.eq("assetClass.asset_class_id", assetId));
			assetClassSecurityMaps = criteria.list();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load security types---"+ e);
		}
		return assetClassSecurityMaps;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getSecurityTypeByAssetClassId(com.finvendor.model.SecurityType)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Region> getAllRegionClass() {
		logger.info("Method to load all regions---");
		List<Region>  regions = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Region.class);
			regions = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all regions---");
		}
		return regions;
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getRegionCountryMapsRegionId(com.finvendor.model.RegionCountryMap)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId) {
		logger.info("Method to getRegionCountryMapsRegionId---");
		Criteria criteria = null;
		List<RegionCountryMap> regionCountryMaps = null;
		try{
			criteria = this.sessionFactory.openSession().createCriteria(RegionCountryMap.class);
			criteria.add(Restrictions.eq("region.region_id", regionId));
			regionCountryMaps = criteria.list();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load getRegionCountryMapsRegionId---"+ e);
		}
		return regionCountryMaps;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getCountryExchangeMapsByCountryId(com.finvendor.model.CountryExchangeMap)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<CountryExchangeMap> getCountryExchangeMapsByCountryId(
			Integer countryId) {
		logger.info("Method to getCountryExchangeMapsByCountryId---");
		Criteria criteria = null;
		List<CountryExchangeMap> countryExchangeMaps = null;
		try{
			criteria = this.sessionFactory.openSession().createCriteria(CountryExchangeMap.class);
			criteria.add(Restrictions.eq("country.country_id", countryId));
			countryExchangeMaps = criteria.list();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load getCountryExchangeMapsByCountryId---"+ e);
		}
		return countryExchangeMaps;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAllCountries(com.finvendor.model.Country)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Country> getAllCountries() {
		logger.info("Method to load all countries---");
		List<Country>  countries = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
			countries = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all asset classes---");
		}
		return countries;
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAllVendorSupports(com.finvendor.model.VendorSupport)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Support> getAllVendorSupports() {
		logger.info("Method to load all supports---");
		List<Support>  supports = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Support.class);
			supports = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all supports---");
		}
		return supports;
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAllCostInfo(com.finvendor.model.Cost)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Cost> getAllCostInfo() {
		logger.info("Method to load all costs---");
		List<Cost>  costs = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Cost.class);
			costs = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all costs---");
		}
		return costs;
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAllAwards(com.finvendor.model.Awards)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Awards> getAllAwards() {
		logger.info("Method to load all awards---");
		List<Awards>  awards = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Awards.class);
			awards = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all awards---");
		}
		return awards;
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAllAwards(com.finvendor.model.Awards)
	 */
	@Transactional
	@Override
	public List<Exchange> getAllExchanges() {
		logger.info("Method to getAllExchanges---");
		List<Exchange>  exchanges = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Exchange.class);
			exchanges = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to getAllExchanges---");
		}
		return exchanges;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getAssetClassByName(com.finvendor.model.AssetClass)
	 */
	@Transactional
	@Override
	public AssetClass getAssetClassByName(String assetType) {
		logger.info("Method to getAssetClassByName---");
		AssetClass assetClass = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(AssetClass.class);
			criteria.add(Restrictions.eq("description", assetType));	
			assetClass = (AssetClass) criteria.uniqueResult();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to getAssetClassByName---");
		}
		return assetClass;
		
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getRegionNamesByName(com.finvendor.model.Region)
	 */
	@Transactional
	@Override
	public List<Region> getRegionNamesByName(String regionName) {
		logger.info("Method to find getRegionNamesByName---");
		List<Region> regions = new ArrayList<Region>();
		String sqlQuery = null;
		Query query = null;
		Region region = new Region();
		try{
			sqlQuery = "SELECT 	region_id, name FROM region  WHERE name LIKE '%" + regionName.toLowerCase().trim() + "%' ";
			query = this.sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
	    	 @SuppressWarnings("unchecked")
	    	 List<Object[]> regionObject = query.list();
	    	 for (int i = 0; i < regionObject.size(); i++) {
	    		 Object[] regionCheck = regionObject.get(i);
	    		 Integer regionId = Integer.parseInt(regionCheck[0].toString());
	    		 String regionname = regionCheck[1].toString();
	    		 region.setRegion_id(regionId);
	    		 region.setName(regionname);
	    		 regions.add(region);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error to find getRegionNamesByName");
		}
		return regions;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getSingleAssetClassSearchResultInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(
			String assetclassId, List<String> securitytypes, String dataattribute,
			List<String> regionList, List<String> countryList,
			List<String> exchangeList) {
		logger.info("Method to find single asset class search---");
		List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>();
		String sqlQuery = null;
		Query query = null;
		List<Object[]> assetClassDataDetails =null;
		try{
			if(assetclassId != null && securitytypes.size() > 0){
				sqlQuery = "SELECT  * FROM  vendor_asset_class_search_info where asset_class_id = '"+assetclassId+"' and security_type_id in (:securitytypes)  " ;
			       if(regionList != null && regionList.size() > 0)
			    	   sqlQuery = sqlQuery +	"or region_id in (:regionlist) " ;  
			       if(countryList !=null && countryList.size() > 0)
			    	   sqlQuery = sqlQuery + "or country_id in (:countrylist)";
			       if(exchangeList !=null && exchangeList.size() > 0)	  
			    	sqlQuery = sqlQuery + "or exchange_id in (:exchagelist) "  ;
			       	query = this.sessionFactory.openSession().createSQLQuery(sqlQuery);
			       	query.setParameterList("securitytypes", securitytypes);
					 if(regionList != null && regionList.size() > 0)
						 query.setParameterList("regionlist", regionList);	 
					 if(countryList !=null && countryList.size() > 0) 
						 query.setParameterList("countrylist", countryList);	 
					 if(exchangeList !=null && exchangeList.size() > 0)
						 query.setParameterList("exchagelist", exchangeList);	 
				 assetClassDataDetails = query.list();
				 for (int i = 0; i < assetClassDataDetails.size(); i++) {
					 Object[] assetClassDatainfo = assetClassDataDetails.get(i);
					 String username =  assetClassDatainfo[0].toString();
					 String company =  assetClassDatainfo[1].toString();
					 String vendorId = assetClassDatainfo[2].toString();
					 Integer assetClassId = Integer.parseInt(assetClassDatainfo[3].toString());
					 Integer securityTypeId =  Integer.parseInt(assetClassDatainfo[4].toString());
					 String assetclass_description =  assetClassDatainfo[5].toString();
					 String security_type_name =  assetClassDatainfo[6].toString();
					 Integer regionofincorp =  Integer.parseInt(assetClassDatainfo[7].toString());
					 Integer countryofincorp =  Integer.parseInt(assetClassDatainfo[8].toString());
					 Integer regionId =  Integer.parseInt(assetClassDatainfo[9].toString());
					 Integer countryId =  Integer.parseInt(assetClassDatainfo[10].toString());
					 Integer exchangeId =  Integer.parseInt(assetClassDatainfo[11].toString());
					 Integer costId =  Integer.parseInt(assetClassDatainfo[12].toString());
					 String cost_range = assetClassDatainfo[13].toString();
					 String cost_name = assetClassDatainfo[14].toString();
					 Integer support_id =  Integer.parseInt(assetClassDatainfo[15].toString());
					 String support_name = assetClassDatainfo[16].toString();
					 Integer awardId =  Integer.parseInt(assetClassDatainfo[17].toString());
					 String awar_name = assetClassDatainfo[18].toString();
					 Integer ditributionmodeId =  Integer.parseInt(assetClassDatainfo[19].toString());
					 String ditributionmodeName = assetClassDatainfo[20].toString();
					 assetClassDataDetailslist.add(new AssetClassDataDetails(username, company, vendorId, 
							 assetClassId, securityTypeId,assetclass_description,security_type_name,
							 regionofincorp,countryofincorp,regionId, countryId, exchangeId,
							 costId, cost_range,cost_name,support_id,support_name,awardId,awar_name,ditributionmodeId,ditributionmodeName));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to find single asset class search---");
		}
		return assetClassDataDetailslist;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getSingleAssetClassSearchResultVendorInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultVendorInfo(
			String assetclassId, List<String> securitytypeList,
			String vendorregionofincorp,
			List<String> vendorcountryofincorpList,
			List<String> vendorprofilefreshnessList,
			List<String> vendoryearoperationList, String searchkeyword,
			List<String> vendorsupportregionList,
			List<String> vendorsupporttimeList, List<String> awardsList,
			List<String> acquisitioncostrangeList) {
		logger.info("Method to find single asset class search---");
		List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>();
		String sqlQuery = null;
		Query query = null;
		List<Object[]> assetClassDataDetails =null;
		try{
			if(assetclassId != null && securitytypeList.size() > 0){
				sqlQuery = "SELECT  * FROM  asset_class_data_details where asset_class_id = '"+assetclassId+"' and security_type_id in (:securitytypes) " +
						"or region_id ='"+vendorregionofincorp+"' or country_id in (:countryIds) or " +
								"region_id in (:supportregionlist) or support_id in (:supportIdlist) or " +
								"award_id in (:awardId)";
				 query = this.sessionFactory.openSession().createSQLQuery(sqlQuery);
				 query.setParameterList("securitytypes", securitytypeList);
				 query.setParameterList("countryIds", vendorcountryofincorpList);
				 query.setParameterList("supportregionlist", vendorsupportregionList);
				 query.setParameterList("supportIdlist", vendorsupporttimeList);
				 query.setParameterList("awardId", awardsList);
				 assetClassDataDetails = query.list();
				 for (int i = 0; i < assetClassDataDetails.size(); i++) {
					 Object[] assetClassDatainfo = assetClassDataDetails.get(i);
					 String username =  assetClassDatainfo[0].toString();
					 String company =  assetClassDatainfo[1].toString();
					 String vendorId = assetClassDatainfo[2].toString();
					 Integer assetClassId = Integer.parseInt(assetClassDatainfo[3].toString());
					 Integer securityTypeId =  Integer.parseInt(assetClassDatainfo[4].toString());
					 Long countRegions = Long.parseLong(assetClassDatainfo[5].toString());
					 Long countCountries =  Long.parseLong(assetClassDatainfo[6].toString());
					 Long countExchanges =  Long.parseLong(assetClassDatainfo[7].toString());
					 Integer regionId =  Integer.parseInt(assetClassDatainfo[8].toString());
					 Integer countryId =  Integer.parseInt(assetClassDatainfo[9].toString());
					 Integer exchangeId =  Integer.parseInt(assetClassDatainfo[10].toString());
					 Integer costId =  Integer.parseInt(assetClassDatainfo[11].toString());
					 String range = assetClassDatainfo[12].toString();
					 Integer ditributionmodeId =  Integer.parseInt(assetClassDatainfo[13].toString());
					 String ditributionmodeName = assetClassDatainfo[14].toString();
					 Integer supportId =  Integer.parseInt(assetClassDatainfo[15].toString());
					 Integer awardId =  Integer.parseInt(assetClassDatainfo[16].toString());
					 /*assetClassDataDetailslist.add(new AssetClassDataDetails(username, company, vendorId, 
							 assetClassId, securityTypeId, countRegions, countCountries, 
							 countExchanges, regionId, countryId, exchangeId, costId, range, ditributionmodeId,ditributionmodeName,supportId,awardId));*/
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to find single asset class search---");
		}
		return assetClassDataDetailslist;
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getSingleAssetClassVendorDetails(com.finvendor.model.AssetClassDataDetails)
	 */
	@Transactional
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassVendorDetails(
			String assetclassId, List<String> securitytypeList,
			List<String> vendorregionofincorpList,
			List<String> vendorcountryofincorpList,
			String vendorprofilefreshness, String vendoryearoperation,
			String searchkeyword, String vendorsupportregion,
			String vendorsupporttime, String vendorawards,
			String vendorcostrange) {
		logger.info("Method to find single asset class search---");
		List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>();
		String sqlQuery = null;
		Query query = null;
		List<Object[]> assetClassDataDetails =null;
		try{
			if(assetclassId != null && securitytypeList.size() > 0){
				sqlQuery = "SELECT  * FROM  vendor_asset_class_search_info where asset_class_id = '"+assetclassId+"' and security_type_id in (:securitytypes)  " ;
			       if(vendorregionofincorpList != null && vendorregionofincorpList.size() > 0)
			    	   sqlQuery = sqlQuery +	"or region_id in (:regionlist) " ;  
			       if(vendorcountryofincorpList !=null && vendorcountryofincorpList.size() > 0)
			    	   sqlQuery = sqlQuery + "or country_id in (:countrylist) or support_id = '" +vendorsupporttime+ "' " +
			    	   		"or award_id = '"+vendorawards+"' or cost_id = '"+vendorcostrange+"'";
			       	query = this.sessionFactory.openSession().createSQLQuery(sqlQuery);
			       	query.setParameterList("securitytypes", securitytypeList);
					 if(vendorregionofincorpList != null && vendorregionofincorpList.size() > 0)
						 query.setParameterList("regionlist", vendorregionofincorpList);	 
					 if(vendorcountryofincorpList !=null && vendorcountryofincorpList.size() > 0) 
						 query.setParameterList("countrylist", vendorcountryofincorpList);	 
				 assetClassDataDetails = query.list();
				 for (int i = 0; i < assetClassDataDetails.size(); i++) {
					 Object[] assetClassDatainfo = assetClassDataDetails.get(i);
					 String username =  assetClassDatainfo[0].toString();
					 String company =  assetClassDatainfo[1].toString();
					 String vendorId = assetClassDatainfo[2].toString();
					 Integer assetClassId = Integer.parseInt(assetClassDatainfo[3].toString());
					 Integer securityTypeId =  Integer.parseInt(assetClassDatainfo[4].toString());
					 String assetclass_description =  assetClassDatainfo[5].toString();
					 String security_type_name =  assetClassDatainfo[6].toString();
					 Integer regionofincorp =  Integer.parseInt(assetClassDatainfo[7].toString());
					 Integer countryofincorp =  Integer.parseInt(assetClassDatainfo[8].toString());
					 Integer regionId =  Integer.parseInt(assetClassDatainfo[9].toString());
					 Integer countryId =  Integer.parseInt(assetClassDatainfo[10].toString());
					 Integer exchangeId =  Integer.parseInt(assetClassDatainfo[11].toString());
					 Integer costId =  Integer.parseInt(assetClassDatainfo[12].toString());
					 String cost_range = assetClassDatainfo[13].toString();
					 String cost_name = assetClassDatainfo[14].toString();
					 Integer support_id =  Integer.parseInt(assetClassDatainfo[15].toString());
					 String support_name = assetClassDatainfo[16].toString();
					 Integer awardId =  Integer.parseInt(assetClassDatainfo[17].toString());
					 String awar_name = assetClassDatainfo[18].toString();
					 Integer ditributionmodeId =  Integer.parseInt(assetClassDatainfo[19].toString());
					 String ditributionmodeName = assetClassDatainfo[20].toString();
					 assetClassDataDetailslist.add(new AssetClassDataDetails(username, company, vendorId, 
							 assetClassId, securityTypeId,assetclass_description,security_type_name,
							 regionofincorp,countryofincorp,regionId, countryId, exchangeId,
							 costId, cost_range,cost_name,support_id,support_name,awardId,awar_name,ditributionmodeId,ditributionmodeName));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to find single asset class search---");
		}
		return assetClassDataDetailslist;
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.MarketDataAggregatorsDAOImpl#getMultiAssetClassSearchResultInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@Transactional
	@Override
	public List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList) {
		logger.info("Method to find single asset class search---");
		List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>();
		String sqlQuery = null;
		Query query = null;
		List<Object[]> assetClassDataDetails =null;
		try{
			if(assetClassList.size() > 0 && securityList.size() > 0){
				sqlQuery = "SELECT * FROM  vendor_asset_class_search_info where " ;
			       if(assetClassList != null && assetClassList.size() > 0)
			    	   sqlQuery = sqlQuery + "asset_class_id in (:assetclasstypes)" ;  
			       if(securityList !=null && securityList.size() > 0)
			    	   sqlQuery = sqlQuery + " and security_type_id in (:securitytypes) ";
			       	query = this.sessionFactory.openSession().createSQLQuery(sqlQuery);
					 if(assetClassList != null && assetClassList.size() > 0)
						 query.setParameterList("assetclasstypes", assetClassList);	 
					 if(securityList !=null && securityList.size() > 0) 
						 query.setParameterList("securitytypes", securityList);	
					 
				 assetClassDataDetails = query.list();
				 for (int i = 0; i < assetClassDataDetails.size(); i++) {
					 Object[] assetClassDatainfo = assetClassDataDetails.get(i);
					 String username =  assetClassDatainfo[0].toString();
					 String company =  assetClassDatainfo[1].toString();
					 String vendorId = assetClassDatainfo[2].toString();
					 Integer assetClassId = Integer.parseInt(assetClassDatainfo[3].toString());
					 Integer securityTypeId =  Integer.parseInt(assetClassDatainfo[4].toString());
					 String assetclass_description =  assetClassDatainfo[5].toString();
					 String security_type_name =  assetClassDatainfo[6].toString();
					 Integer regionofincorp =  Integer.parseInt(assetClassDatainfo[7].toString());
					 Integer countryofincorp =  Integer.parseInt(assetClassDatainfo[8].toString());
					 Integer regionId =  Integer.parseInt(assetClassDatainfo[9].toString());
					 Integer countryId =  Integer.parseInt(assetClassDatainfo[10].toString());
					 Integer exchangeId =  Integer.parseInt(assetClassDatainfo[11].toString());
					 Integer costId =  Integer.parseInt(assetClassDatainfo[12].toString());
					 String cost_range = assetClassDatainfo[13].toString();
					 String cost_name = assetClassDatainfo[14].toString();
					 Integer support_id =  Integer.parseInt(assetClassDatainfo[15].toString());
					 String support_name = assetClassDatainfo[16].toString();
					 Integer awardId =  Integer.parseInt(assetClassDatainfo[17].toString());
					 String awar_name = assetClassDatainfo[18].toString();
					 Integer ditributionmodeId =  Integer.parseInt(assetClassDatainfo[19].toString());
					 String ditributionmodeName = assetClassDatainfo[20].toString();
					 assetClassDataDetailslist.add(new AssetClassDataDetails(username, company, vendorId, 
							 assetClassId, securityTypeId,assetclass_description,security_type_name,
							 regionofincorp,countryofincorp,regionId, countryId, exchangeId,
							 costId, cost_range,cost_name,support_id,support_name,awardId,awar_name,ditributionmodeId,ditributionmodeName));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to find single asset class search---");
		}
		return assetClassDataDetailslist;

	}
	@Transactional
	@Override
	public OfferingFiles addOfferingFiles(String id, OfferingFiles offeringFiles ) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		VendorOffering vendorOffering = (VendorOffering)currentSession.get(VendorOffering.class, Integer.parseInt(id));
		//Set<OfferingFiles> hashSet = new HashSet<OfferingFiles>();
		offeringFiles.setVendorOffering(vendorOffering);
		//hashSet.add(offeringFiles);
		//vendorOffering.setOfferingFiles(hashSet);
		currentSession.save(offeringFiles);
		return offeringFiles;
	}
	
	@Transactional
	@Override
	public VendorOffering createOfferings(String id, VendorOffering vendorOffering) {
		Session currentSession = this.sessionFactory.getCurrentSession();
	//	currentSession.beginTransaction();
		currentSession.save(vendorOffering);
		
		return vendorOffering;
	}
	@Transactional
	@Override
	public FileFields addFieldsToFile(String id,FileFields fileFields) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		OfferingFiles offeringFiles = (OfferingFiles)currentSession.get(OfferingFiles.class, Integer.parseInt(id));
		fileFields.setOfferingFiles(offeringFiles);
		currentSession.save(fileFields);
		return fileFields;
	}
	@Transactional
	@Override
	public Set<VendorOffering> listOfferings(String id) {

		Session currentSession = this.sessionFactory.getCurrentSession();
		Set<VendorOffering> vendorOfferings = null;
		
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, id);
		 vendorOfferings = vendor.getVendorOfferings();
	
		 vendorOfferings = new HashSet<VendorOffering>(vendorOfferings);
		
		// currentSession.close();
		return vendorOfferings;
	 
	}
	@Transactional
	@Override
	public Set<OfferingFiles> listOfferingFiles(String id) {

		Session currentSession = this.sessionFactory.getCurrentSession();
		VendorOffering vendorOffering = (VendorOffering)currentSession.get(VendorOffering.class, Integer.parseInt(id));
		Set<OfferingFiles> vendorOfferings =vendorOffering.getOfferingFiles();
		// currentSession.close();
		return vendorOfferings;
	 
	}
	@Transactional
	@Override
	public Set<FileFields> listFieldsToFile(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		OfferingFiles offeringFiles = (OfferingFiles)currentSession.get(OfferingFiles.class, Integer.parseInt(id));
		Set<FileFields> fileFields = offeringFiles.getFileFields();
		// currentSession.close();
		return fileFields;
	}
	@Transactional
	@Override
	public VendorOffering deleteOfferings(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		VendorOffering vendorOffering = (VendorOffering)currentSession.get(VendorOffering.class, Integer.parseInt(id));
		if(vendorOffering != null)
		currentSession.delete(vendorOffering);
		return vendorOffering;
	}
	@Transactional
	@Override
	public OfferingFiles deleteOfferingFiles(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		
		OfferingFiles offeringFiles = (OfferingFiles)currentSession.get(OfferingFiles.class, Integer.parseInt(id));
		
		if(offeringFiles != null)
			currentSession.delete(offeringFiles);
		
		/*String sql = "delete FROM offeringfiles WHERE Offering_Files_id = :id";
		SQLQuery query = currentSession.createSQLQuery(sql);
		query.addEntity(FileFields.class);
		query.setParameter("id", id);*/
	
		return new OfferingFiles();
	}
	@Transactional
	@Override
	public FileFields deleteFieldsToFile(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		
		String sql = "delete FROM FileFields WHERE FileFields_id = :id";
		SQLQuery query = currentSession.createSQLQuery(sql);
		query.addEntity(FileFields.class);
		query.setParameter("id", id);
		
		return new FileFields();
	}
	@Transactional
	public List<SecurityType> listSecurityType() {

		logger.info("Method to load all listSecurityType---");
		List<SecurityType>  assetClasses = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(SecurityType.class);
			assetClasses = criteria.list();
 		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Method to load all listSecurityType---");
		}
		return assetClasses;
	}
	@Transactional
	@Override
	public VendorOffering getVendorOfferingById(String id) {
		
		Session currentSession = this.sessionFactory.getCurrentSession();
		VendorOffering vendorOffering = (VendorOffering)currentSession.get(VendorOffering.class, Integer.parseInt(id));
		return vendorOffering;
	}

	@Transactional
	@Override
	public Region getRegionById(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Region region = (Region)currentSession.get(Region.class, Integer.parseInt(id));
		
		return region;
	}

	@Transactional
	@Override
	public Country getCountryById(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(id)); 
		
		return country;
	}

	@Transactional
	@Override
	public Cost getCostById(String id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Cost cost = (Cost)currentSession.get(Cost.class, Integer.parseInt(id)); 
		return cost;
	}
    
	@Transactional
	@Override
	public Exchange getExchangeById(String exchangeId) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Exchange exchange = (Exchange)currentSession.get(Exchange.class, Integer.parseInt(exchangeId)); 

				return exchange;
	}

 }
