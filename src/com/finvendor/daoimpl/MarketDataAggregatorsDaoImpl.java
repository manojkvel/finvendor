package com.finvendor.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.MarketDataAggregatorsDao;
import com.finvendor.form.FinancialAnalyticsApplicationVendorSearchForm;
import com.finvendor.form.MarketDataAggregatorsVendorSearchForm;
import com.finvendor.form.ResearchReportProvidersVendorSearchForm;
import com.finvendor.form.TradingApplicationVendorSearchForm;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassDataDetails;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.CompanySubType;
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
import com.finvendor.model.VendorSearchResult;

@SuppressWarnings("unchecked")
public class MarketDataAggregatorsDaoImpl implements MarketDataAggregatorsDao{

	private static Logger logger = LoggerFactory.getLogger(MarketDataAggregatorsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<AssetClass> getAllAssetClass() {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getAllAssetClass");
		List<AssetClass> assetClasses = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(AssetClass.class);
			assetClasses = criteria.list();
 		}catch (Exception exp) {
			logger.error("Error loading Asset Classes", exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getAllAssetClass");
		return assetClasses;
	}
	
	@Transactional
	@Override
	public List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId) {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getSecurityTypeByAssetClassId for {} ", assetId);
		Criteria criteria = null;
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try{
			criteria = this.sessionFactory.openSession().createCriteria(AssetClassSecurityMap.class);
			criteria.add(Restrictions.eq("assetClass.asset_class_id", assetId));
			assetClassSecurityMaps = criteria.list();
		}catch (Exception exp) {
			logger.error("Error loading Security Types for Asset Class Id {}", assetId, exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getSecurityTypeByAssetClassId for {} ", assetId);
		return assetClassSecurityMaps;
	}
	
	@Transactional
	@Override
	public List<Region> getAllRegionClass() {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getAllRegionClass");
		List<Region> regions = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Region.class);
			regions = criteria.list();
 		}catch (Exception exp) {
 			logger.error("Error loading Region Classes", exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getAllRegionClass");
		return regions;
	}

	@Transactional
	@Override
	public List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId) {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getRegionCountryMapsRegionId for {} ", regionId);
		Criteria criteria = null;
		List<RegionCountryMap> regionCountryMaps = null;
		try{
			criteria = this.sessionFactory.openSession().createCriteria(RegionCountryMap.class);
			criteria.add(Restrictions.eq("region.region_id", regionId));
			regionCountryMaps = criteria.list();
		}catch (Exception exp) {
			logger.error("Error getRegionCountryMapsRegionId for Region Id {}", regionId, exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getRegionCountryMapsRegionId for {} ", regionId);
		return regionCountryMaps;
	}
	
	@Transactional
	@Override
	public List<CountryExchangeMap> getCountryExchangeMapsByCountryId(Integer countryId) {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getCountryExchangeMapsByCountryId for {} ", countryId);
		Criteria criteria = null;
		List<CountryExchangeMap> countryExchangeMaps = null;
		try{
			criteria = this.sessionFactory.openSession().createCriteria(CountryExchangeMap.class);
			criteria.add(Restrictions.eq("country.country_id", countryId));
			countryExchangeMaps = criteria.list();
		}catch (Exception exp) {
			logger.error("Error getCountryExchangeMapsByCountryId for Country Id {}", countryId, exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getCountryExchangeMapsByCountryId for {} ", countryId);
		return countryExchangeMaps;
	}
	
	@Transactional
	@Override
	public List<Country> getAllCountries() {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getAllCountries");
		List<Country> countries = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
			countries = criteria.list();
 		}catch (Exception exp) {
 			logger.error("Error loading All Countries", exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getAllCountries");
		return countries;
	}

	@Transactional
	@Override
	public List<Support> getAllVendorSupports() {
		logger.debug("Entering - MarketDataAggregatorsDaoImpl : getAllVendorSupports");
		List<Support>  supports = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Support.class);
			supports = criteria.list();
 		}catch (Exception exp) {
 			logger.error("Error loading Vendor Supports", exp);
		}
		logger.debug("Leaving - MarketDataAggregatorsDaoImpl : getAllVendorSupports");
		return supports;
	}

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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getAllAwards(com.finvendor.model.Awards)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Awards> getAllAwards(String vendorId) {
		logger.info("Method to load all awards---");
		List<Awards>  awards = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Awards.class);
			if(vendorId != null) {
				criteria.add(Restrictions.eq("vendorId", vendorId));
			}
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getAllAwards(com.finvendor.model.Awards)
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getAssetClassByName(com.finvendor.model.AssetClass)
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getRegionNamesByName(com.finvendor.model.Region)
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getSingleAssetClassSearchResultInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(
			String assetclassId, List<String> securitytypes, String dataattribute,
			List<String> regionList, List<String> countryList,
			List<String> exchangeList) {
		logger.info("Method to find single asset class search---");
		/*
		SELECT * 
		FROM vendor_datacoverage dc 
		inner join vendor_offering vo on dc.vendor_offering_id = vo.vendor_offering_id
		inner join offeringfiles of on of.vendor_offering_id = vo.vendor_offering_id 
		inner join vendor_distribution vd on vd.solution_id = vd.solution_id 
		where dc.cost_ids like '$200%' 
		and dc.region_ids = "Asia Pacific" 
		and dc.country_ids='india' 
		and vo.asset_class_id = 1
		and of.security_type_id = 1
		and vd.exchanges = 'NSE'
		*/
		List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>();
		String sqlQuery = null;
		Query query = null;
		List<Object[]> assetClassDataDetails =null;
		try{
			if(assetclassId != null && securitytypes.size() > 0){/*
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
			*/}
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getSingleAssetClassSearchResultVendorInfo(com.finvendor.model.AssetClassDataDetails)
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getSingleAssetClassVendorDetails(com.finvendor.model.AssetClassDataDetails)
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDaoImpl#getMultiAssetClassSearchResultInfo(com.finvendor.model.AssetClassDataDetails)
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
		 //vendorOfferings = vendor.getVendorOfferings();
	
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
	public VendorOffering findOfferingById(String id) {
		VendorOffering vendorOffering = null;
		Session currentSession = this.sessionFactory.getCurrentSession();
		vendorOffering = (VendorOffering)currentSession.get(VendorOffering.class, Integer.parseInt(id));
		return vendorOffering;
	}
	
	@Transactional
	@Override
	public VendorOffering deleteOfferings(VendorOffering vendorOffering) {
		Session currentSession = this.sessionFactory.getCurrentSession();
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
	
	@Override
	@Transactional(readOnly=true)
	public List<CompanySubType> getCompanySubTypeList(){
		logger.debug("Entering MarketDataAggregatorsDaoImpl : getCompanySubTypeList");
		List<CompanySubType> compnaySubType = null;
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(CompanySubType.class);
			compnaySubType = criteria.list();
 		}catch (Exception exp) {
			logger.error("Error reading CompanySubType details", exp);
		}
		logger.debug("Leaving MarketDataAggregatorsDaoImpl : getCompanySubTypeList");
		return compnaySubType;
	}
	
	/* Market Data Aggregators Search */
	@Transactional
	@Override
	public Map<String, Object> getMultiAssetClassSearchResult(Map<Object, Object> searchData, 
			MarketDataAggregatorsVendorSearchForm dataForm) {
		
		Session currentSession = this.sessionFactory.getCurrentSession();
		
		//String vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, asset.description ASSET_CLASS, off.name OFFERING_NAME, off.description OFFERING_DESCRIPTION, cov.region_ids REGION, cov.country_ids COUTRIES, off.LaunchedYear YEAR, cov.coverageexchange, security.name, security.security_type_id, asset_sec.description sec_asset_description from vendor_offering off, vendor_datacoverage cov, asset_class asset, vendor ven, users u, country country, offeringfiles files, security_types security, asset_class asset_sec where u.username = ven.username and off.vendor_offering_id = cov.vendor_offering_id and asset.asset_class_id = off.asset_class_id and off.vendor_id = cov.vendor_id and ven.vendor_id = off.vendor_id and country.country_id = ven.countryofincorp and files.vendor_offering_id = off.vendor_offering_id and security.security_type_id = files.security_type_id and security.asset_class_id = asset_sec.asset_class_id ";
		String vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, asset.description ASSET_CLASS, off.product_name OFFERING_NAME, off.product_description OFFERING_DESCRIPTION, cov.coverage_region REGION, cov.coverage_country COUTRIES, off.launched_year YEAR, cov.coverage_exchange coverageexchange from ven_data_aggr_offering off, ven_data_aggr_offering_coverage cov, asset_class asset, vendor ven, users u, country country, security_types security where u.username = ven.username and off.product_id = cov.product_id and asset.asset_class_id = off.asset_class_id and ven.vendor_id = off.vendor_id and country.country_id = ven.countryofincorp ";
		StringBuilder searchSql = new StringBuilder(2000);
		searchSql.append(vendorSearchSql);
		
		if (dataForm.getVendorregionofincorp() != null && !dataForm.getVendorregionofincorp().toString().trim().equals("")) {
			searchSql.append("and ven.regionofincorp in ( ");
			searchSql.append(dataForm.getVendorregionofincorp());
			searchSql.append(" ) ");
		}
		
		if (dataForm.getVendorcountryofincorp() != null && !dataForm.getVendorcountryofincorp().toString().trim().equals("")) {
			searchSql.append(" and ven.countryofincorp in ( ");
			searchSql.append(dataForm.getVendorcountryofincorp());
			searchSql.append(" ) ");
		}
		
		String assetClasses = (String)searchData.get("assetClassChk");
		String[] assetClassList = new String[0];
		
		if(assetClasses != null && !assetClasses.trim().equals("")) {
			assetClassList = assetClasses.split(",");
		}
						
		boolean firstAssetClass = false;
		
		for(String assetClass : assetClassList) {
			if(!firstAssetClass) {
				searchSql.append(" and (");
				firstAssetClass = true;
			}
			searchSql.append(" ( ");
			searchSql.append(" asset.description = '");
			searchSql.append(assetClass);
			searchSql.append("'");
			
			Object datacoveragecountry = searchData.get(assetClass.toLowerCase() + "datacoveragecountry");
			//populateFilterCondition(searchSql, datacoveragecountry, "cov.country_ids");
			populateFilterCondition(searchSql, datacoveragecountry, "cov.coverage_country");
			Object datacoverageregion = searchData.get(assetClass.toLowerCase() + "datacoverageregion");
			//populateFilterCondition(searchSql, datacoverageregion, "cov.region_ids");
			populateFilterCondition(searchSql, datacoverageregion, "cov.coverage_region");
			Object datacoverageexchange = searchData.get(assetClass.toLowerCase() + "datacoverageexchange");
			//populateFilterCondition(searchSql, datacoverageexchange, "cov.coverageexchange");
			populateFilterCondition(searchSql, datacoverageexchange, "cov.coverage_exchange");
			Object dataacquisitioncostrange = searchData.get(assetClass.toLowerCase() + "dataacquisitioncostrange");
			//populateFilterCondition(searchSql, dataacquisitioncostrange, "cov.cost_ids");
			Object securitytype = searchData.get(assetClass.toLowerCase() + "securitytype");
			populateFilterCondition(searchSql, securitytype, "off.security_types");
						
			/*
			if(datacoveragecountry != null && !datacoveragecountry.toString().isEmpty() ) {
				String[] countries = datacoveragecountry.toString().split(",");
				boolean firstCountry = false;
				for(String country : countries) {
					if(!firstCountry) {
						firstCountry = true;
						searchSql.append(" and ( ");						
					}
					searchSql.append(" cov.country_ids like '%");
					searchSql.append(country.trim());
					searchSql.append("%' ");
					searchSql.append("or");
				}
				if(firstCountry) {
					searchSql.delete(searchSql.length() - 2, searchSql.length());
					searchSql.append(")");
				}
			}
			*/			
			searchSql.append(")");
			searchSql.append("or");
		}
		
		if(firstAssetClass) {
			searchSql.delete(searchSql.length() - 2, searchSql.length());
			searchSql.append(" ) ");
		}
		
		searchSql.append(" order by vendor_id, asset.description");
		logger.info("searchSql ################## QUERY == " + searchSql.toString());
		
		SQLQuery sqlQuery = currentSession.createSQLQuery(searchSql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> searchResultList = (List<Object[]>)sqlQuery.list();
		Set<VendorSearchResult> vendorSearchResultList = new HashSet<VendorSearchResult>();
		
		Map<String, Set<String>> assetCountries = new HashMap<String, Set<String>>();
		Map<String, Set<String>> assetExchanges = new HashMap<String, Set<String>>();
		Map<String, List<VendorSearchResult>> awardsMap = new HashMap<String, List<VendorSearchResult>>();
		
		Map<String, Object> searchResultMap = new HashMap <String, Object>();
		
		StringBuilder vendorList = new StringBuilder();
		boolean vendorFound = false;
		int vendorCount = 0;
		for(Object[] searchRow : searchResultList) {
			vendorFound = true;
			vendorList.append("'");
			vendorList.append((String)searchRow[0]);
			vendorList.append("'");
			vendorCount++;
			if (vendorCount <= searchResultList.size() -1) {
				vendorList.append(",");
			}
		}
		
		List<Object[]> vendorAwardList = new ArrayList<Object[]>();
		if(vendorFound) {
			//vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, asset.description ASSET_CLASS, off.name OFFERING_NAME, off.description OFFERING_DESCRIPTION, cov.region_ids REGION, cov.country_ids COUTRIES, off.LaunchedYear YEAR, cov.coverageexchange, security.name, security.security_type_id, asset_sec.description sec_asset_description from vendor_offering off, vendor_datacoverage cov, asset_class asset, vendor ven, users u, country country, offeringfiles files, security_types security, asset_class asset_sec where u.username = ven.username and off.vendor_offering_id = cov.vendor_offering_id and asset.asset_class_id = off.asset_class_id and off.vendor_id = cov.vendor_id and ven.vendor_id = off.vendor_id and country.country_id = ven.countryofincorp and files.vendor_offering_id = off.vendor_offering_id and security.security_type_id = files.security_type_id and security.asset_class_id = asset_sec.asset_class_id and ven.vendor_id in (VENDOR_ID_LIST)";
			vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, asset.description ASSET_CLASS,  off.product_name OFFERING_NAME, off.product_description OFFERING_DESCRIPTION, cov.coverage_region REGION, cov.coverage_country COUTRIES, off.launched_year YEAR, cov.coverage_exchange coverageexchange, off.security_types security_types from ven_data_aggr_offering off, ven_data_aggr_offering_coverage cov, asset_class asset, vendor ven, users u, country country where u.username = ven.username and off.product_id = cov.product_id and asset.asset_class_id = off.asset_class_id and ven.vendor_id = off.vendor_id and country.country_id = ven.countryofincorp and ven.vendor_id in (VENDOR_ID_LIST)";
			vendorSearchSql = vendorSearchSql.replaceAll("VENDOR_ID_LIST", vendorList.toString());
			sqlQuery = currentSession.createSQLQuery(vendorSearchSql);
			logger.info("vendorSearchSql ################## VENDOR SEARCH QUERY == " + vendorSearchSql.toString());
			searchResultList = (List<Object[]>)sqlQuery.list();
			String vendorAwardSql = "select vendor_id, Awardname, Awardsponsor, Awardedyear from vendor_awards where vendor_id in (" + vendorList.toString()  + ") order by vendor_id";
			sqlQuery = currentSession.createSQLQuery(vendorAwardSql);
			vendorAwardList = (List<Object[]>)sqlQuery.list();
		}
		
		for(Object[] searchRow : searchResultList) {
			
			VendorSearchResult vendorSearchRow = new VendorSearchResult();
			vendorSearchRow.setVendorId((String)searchRow[0]);
			vendorSearchRow.setVendorName((String)searchRow[1]);
			vendorSearchRow.setVendorCompany((String)searchRow[2]);
			vendorSearchRow.setVendorCountry((String)searchRow[3]);
			vendorSearchRow.setVendorType((String)searchRow[4]);
			
			String assetClass = (String)searchRow[5];
			String countries = (String)searchRow[9];
			String exchanges = (String)searchRow[11];
			String securityNames = (String)searchRow[12];
			
			String mapKey = (String)searchRow[0] + "_" + assetClass;
			
			if(countries != null && !countries.trim().equals("")) {
				String[] countryNames = countries.split(",");
				for(String countryName : countryNames) {
					Set<String> assetCountriesSet = assetCountries.get(mapKey);
					if(assetCountriesSet == null) {
						assetCountriesSet = new HashSet<String>();
						assetCountries.put(mapKey, assetCountriesSet);
						assetCountriesSet.add(countryName);
					} else {
						assetCountriesSet.add(countryName);
					}
				}
			}
			
			String [] securities = securityNames.split(",");
			for(String securityName : securities) {
				if(securityName != null && !securityName.trim().equals("")) {
					if("Indices".equals(assetClass)) {
						if(securityName != null && securityName.indexOf("Equity") != -1) {
							mapKey = (String)searchRow[0] + "_Equity_" + assetClass;
						} else if (securityName != null && securityName.indexOf("FI") != -1) {
							mapKey = (String)searchRow[0] + "_FI_" + assetClass;
						} else {
							mapKey = (String)searchRow[0] + "_Other_" + assetClass;
						}
					}else if (assetClass.indexOf("Derivative") != -1) {
						mapKey = (String)searchRow[0] + "_Derivative";
					}
					
					if(exchanges != null && !exchanges.trim().equals("")) {
						String[] exchangeNames = exchanges.split(",");
						for(String exchangeName : exchangeNames) {
							Set<String> assetExchangesSet = assetExchanges.get(mapKey);
							if(assetExchangesSet == null) {
								assetExchangesSet = new HashSet<String>();
								assetExchanges.put(mapKey, assetExchangesSet);
								assetExchangesSet.add(exchangeName);
							} else {
								assetExchangesSet.add(exchangeName);
							}
						}
					}
				}
			}
			
			// 5 - Asset class, 9 - Countries, 11 - Exchanges, 12- Security Types
			vendorSearchResultList.add(vendorSearchRow);
		}
		
		for(Object[] searchRow : vendorAwardList) {
			VendorSearchResult vendorSearchRow = new VendorSearchResult();
			vendorSearchRow.setVendorId((String)searchRow[0]);
			vendorSearchRow.setAwardName((String)searchRow[1]);
			vendorSearchRow.setAwardSponsor((String)searchRow[2]);
			vendorSearchRow.setAwardYear(searchRow[3].toString());
			
			List<VendorSearchResult> vendorSearchAwardList = awardsMap.get((String)searchRow[0]);
			
			if(vendorSearchAwardList == null) {
				vendorSearchAwardList = new ArrayList<VendorSearchResult>();
				vendorSearchAwardList.add(vendorSearchRow);
				awardsMap.put((String)searchRow[0], vendorSearchAwardList);
			}else {
				vendorSearchAwardList.add(vendorSearchRow);
			}
		}
		
		searchResultMap.put("vendorSearchResultList", vendorSearchResultList);
		searchResultMap.put("assetCountries", assetCountries);
		searchResultMap.put("assetExchanges", assetExchanges);
		searchResultMap.put("awardsMap", awardsMap);
		
		//logger.info("MAP == " + Arrays.toString(searchResultMap.entrySet().toArray()));
		
		return searchResultMap;
	}
	
	private void populateFilterCondition(StringBuilder searchSql, Object filter, String columnName) {
		if(filter != null && !filter.toString().isEmpty() && !"Any".equalsIgnoreCase(filter.toString())) {
			String[] filterList = filter.toString().split(",");
			boolean firstValue = false;
			for(String filterValue : filterList) {
				if(!firstValue) {
					firstValue = true;
					searchSql.append(" and ( ");						
				}
				searchSql.append(" ");
				searchSql.append(columnName);
				searchSql.append(" like '%,");
				searchSql.append(filterValue.trim());
				searchSql.append(",%' ");
				searchSql.append("or");
			}
			if(firstValue) {
				searchSql.delete(searchSql.length() - 2, searchSql.length());
				searchSql.append(")");
			}
		}
	}
	
	private void populateEqualFilterCondition(StringBuilder searchSql, Object filter, String columnName) {
		if(filter != null && !filter.toString().isEmpty() && !"Any".equalsIgnoreCase(filter.toString())) {
			String[] filterList = filter.toString().split(",");
			boolean firstValue = false;
			for(String filterValue : filterList) {
				if(!firstValue) {
					firstValue = true;
					searchSql.append(" and ( ");						
				}
				searchSql.append(" ");
				searchSql.append(columnName);
				searchSql.append(" = '");
				searchSql.append(filterValue.trim());
				searchSql.append("' ");
				searchSql.append("or");
			}
			if(firstValue) {
				searchSql.delete(searchSql.length() - 2, searchSql.length());
				searchSql.append(")");
			}
		}
	}
	
	@Transactional
	@Override
	public List<FinancialAnalyticsApplicationVendorSearchForm> getFAMultiAssetClassSearchResult(Map<Object, Object> searchData, FinancialAnalyticsApplicationVendorSearchForm dataForm) {

		Session currentSession = this.sessionFactory.getCurrentSession();
				
		String assetClasses = (String)searchData.get("assetClassChk");
		String[] assetClassList = assetClasses.split(",");
		LinkedHashMap<String, List<FinancialAnalyticsApplicationVendorSearchForm>> searchResults = new LinkedHashMap<String,List<FinancialAnalyticsApplicationVendorSearchForm>>();
		/*
		SELECT v.fname,c.name,r.name
		FROM vendor v
		inner join vendor_analyticssoftwaredetails va on va.vendor_id = v.vendor_id
		inner join country c on c.country_id = v.countryofincorp
		inner join region r on r.region_id = v.regionofincorp
		*/
		
		for(String assetClass : assetClassList){
			StringBuffer query = new StringBuffer();
			query.append("SELECT v.fname,va.analyticssolutionsType,va.suitability,va.accessibility,c.name,r.name");
			query.append(" FROM vendor v");
			query.append(" inner join vendor_AnalyticsSoftwareDetails va on va.vendor_id = v.vendor_id");
			query.append(" inner join country c on c.country_id = v.countryofincorp");
			query.append(" inner join region r on r.region_id = v.regionofincorp");
			
			query.append(" where va.analyticssolutionsType in ('"+assetClass+"')");
			
			if(dataForm.getSuitability() != null && !dataForm.getSuitability().isEmpty() ){
					query.append(" and va.suitability = '"+dataForm.getSuitability()+"'");
				}
			if(dataForm.getAccessibility() != null  && !dataForm.getAccessibility().isEmpty() ){
					query.append(" and va.accessibility = '"+dataForm.getAccessibility()+"'");
				}		
					
			Object analyticsSolutionsSubType = searchData.get(assetClass.toLowerCase()+"analyticsSolutionsSubType");
			if(analyticsSolutionsSubType != null && !analyticsSolutionsSubType.toString().isEmpty() ){
			   query.append(" and va.analyticsSolutionsSubType like '%"+analyticsSolutionsSubType+"%'");
			}
			Object datacoverageregion = searchData.get("datacoverageregion");
			if(datacoverageregion != null && !datacoverageregion.toString().isEmpty() ){
			    query.append(" and r.name = '"+datacoverageregion+"'");
			}
			Object datacoveragecountry = searchData.get("datacoveragecountry");
			if(datacoveragecountry != null && !datacoveragecountry.toString().isEmpty() ){
				query.append(" and r.name='"+datacoveragecountry+"'");
			}
			Object datacoverageexchange = searchData.get(assetClass.toLowerCase()+"datacoverageexchange");
			if(datacoverageexchange != null  && !datacoverageexchange.toString().isEmpty()){
			   query.append(" and vd.exchanges='"+datacoverageexchange+"'");
			}
			
			Object securitytype = searchData.get(assetClass.toLowerCase()+"securitytype");
			if(securitytype != null  && !securitytype.toString().isEmpty()){
			   query.append(" and of.security_type_id="+securitytype);
			}
			SQLQuery createSQLQuery = currentSession.createSQLQuery(query.toString());
			List list = createSQLQuery != null ? createSQLQuery.list(): null;
			List<FinancialAnalyticsApplicationVendorSearchForm> mdavsfs= new ArrayList<FinancialAnalyticsApplicationVendorSearchForm>(); 
			if(list != null && list.size()>0){
				for(Object object:list){
					FinancialAnalyticsApplicationVendorSearchForm mdavsf = new FinancialAnalyticsApplicationVendorSearchForm();
					Object[] obj = (Object[])object;
					
					mdavsf.setVendor((String)obj[0]);
					mdavsf.setAnalyticssolutionsType((String)obj[1]);
					mdavsf.setSuitability((String)obj[2]);
					mdavsf.setAccessibility((String)obj[3]);
					mdavsf.setDataCoverageCountry((String)obj[4]);
					mdavsf.setDataCoverageExchange((String)obj[5]);
					
					mdavsfs.add(mdavsf);
				}
			}
			searchResults.put(assetClass, mdavsfs);
		}
		List<FinancialAnalyticsApplicationVendorSearchForm> mdavsfsResult= new ArrayList<FinancialAnalyticsApplicationVendorSearchForm>();
	//	mdavsfsResult.retainAll(mdavsfsResult);
		
		if(searchResults.size()>1){
			for(Map.Entry<String, List<FinancialAnalyticsApplicationVendorSearchForm>> filterResult: searchResults.entrySet()){
				if(mdavsfsResult.size()>1){
					Set<FinancialAnalyticsApplicationVendorSearchForm> tempResult= new LinkedHashSet<FinancialAnalyticsApplicationVendorSearchForm>();
					for(FinancialAnalyticsApplicationVendorSearchForm sfr:mdavsfsResult){
						for(FinancialAnalyticsApplicationVendorSearchForm sf:filterResult.getValue()){
							if(sfr.getVendor().equals(sf.getVendor())){
								tempResult.add(sf);
								tempResult.add(sfr);
							}
						}
				}
					mdavsfsResult.clear();
					mdavsfsResult.addAll(tempResult);
				}else{
					mdavsfsResult = filterResult.getValue();
				}
			}
		}else{
			
			mdavsfsResult = searchResults.entrySet().iterator().next().getValue();
		}
		return mdavsfsResult;
	}
	@Transactional
	@Override
	public List<ResearchReportProvidersVendorSearchForm> getRRMultiAssetClassSearchResult(Map<Object, Object> searchData, ResearchReportProvidersVendorSearchForm dataForm) {

		Session currentSession = this.sessionFactory.getCurrentSession();
		String assetClasses = (String)searchData.get("assetClassChk");
		String[] assetClassList = assetClasses.split(",");
		LinkedHashMap<String, List<ResearchReportProvidersVendorSearchForm>> searchResults = new LinkedHashMap<String,List<ResearchReportProvidersVendorSearchForm>>();

		for(String assetClass : assetClassList){
			StringBuffer query = new StringBuffer();
			query.append("SELECT v.fname,vr.ResearchArea,va.AnalystAwards,va.AnalystName, c.name,r.name as region");
			query.append(" FROM vendor v");
			query.append(" inner join vendor_ResearchCoverage vr on vr.vendor_id = v.vendor_id");
			query.append(" inner join vendor_ResearchDetails vrd on vrd.vendor_id = v.vendor_id");
			query.append(" inner join vendor_AnalystProfile va on va.vendor_id = v.vendor_id");
			query.append(" inner join country c on c.country_id = v.countryofincorp");
			query.append(" inner join region r on r.region_id = v.regionofincorp");
			query.append(" where vr.ResearchArea in ('"+assetClass+"')");
			/*if(dataForm.getVendorregionofincorp() != null && !dataForm.getVendorregionofincorp().isEmpty() ){
					query.append(" and v.regionofincorp = "+dataForm.getVendorregionofincorp());
				}
			if(dataForm.getVendorcountryofincorp() != null  && !dataForm.getVendorcountryofincorp().isEmpty() ){
					query.append(" and v.countryofincorp = "+dataForm.getVendorcountryofincorp());
				}		
			*/		
			Object dataacquisitioncostrange = searchData.get(assetClass.toLowerCase()+"dataacquisitioncostrange");
			if(dataacquisitioncostrange != null && !dataacquisitioncostrange.toString().isEmpty() ){
			   query.append(" and dc.cost_ids like '%"+dataacquisitioncostrange+"%'");
			}
			Object datacoverageregion = searchData.get(assetClass.toLowerCase()+"datacoverageregion");
			if(datacoverageregion != null && !datacoverageregion.toString().isEmpty() ){
			    query.append(" and dc.region_ids = '"+datacoverageregion+"'");
			}
			Object datacoveragecountry = searchData.get(assetClass.toLowerCase()+"datacoveragecountry");
			if(datacoveragecountry != null && !datacoveragecountry.toString().isEmpty() ){
				query.append(" and dc.country_ids='"+datacoveragecountry+"'");
			}
			Object datacoverageexchange = searchData.get(assetClass.toLowerCase()+"datacoverageexchange");
			if(datacoverageexchange != null  && !datacoverageexchange.toString().isEmpty()){
			   query.append(" and vd.exchanges='"+datacoverageexchange+"'");
			}
			
			Object securitytype = searchData.get(assetClass.toLowerCase()+"securitytype");
			if(securitytype != null  && !securitytype.toString().isEmpty()){
			   query.append(" and of.security_type_id="+securitytype);
			}
			SQLQuery createSQLQuery = currentSession.createSQLQuery(query.toString());
			List list = createSQLQuery != null ? createSQLQuery.list(): null;
			List<ResearchReportProvidersVendorSearchForm> mdavsfs= new ArrayList<ResearchReportProvidersVendorSearchForm>(); 
			if(list != null && list.size()>0){
				for(Object object:list){
					
					ResearchReportProvidersVendorSearchForm mdavsf = new ResearchReportProvidersVendorSearchForm();
					Object[] obj = (Object[])object;
					mdavsf.setVendor((String)obj[0]);
					mdavsf.setResearchArea((String)obj[1]);
					mdavsf.setAnalystAwards((String)obj[2]);
					mdavsf.setAnalystName((String)obj[3]);
					mdavsf.setCountryName((String)obj[4]);
					mdavsf.setRegion((String)obj[5]);
					mdavsfs.add(mdavsf);
				}
			}
			searchResults.put(assetClass, mdavsfs);
		}
		List<ResearchReportProvidersVendorSearchForm> mdavsfsResult= new ArrayList<ResearchReportProvidersVendorSearchForm>();
	//	mdavsfsResult.retainAll(mdavsfsResult);
		
		if(searchResults.size()>1){
			for(Map.Entry<String, List<ResearchReportProvidersVendorSearchForm>> filterResult: searchResults.entrySet()){
				if(mdavsfsResult.size()>1){
					Set<ResearchReportProvidersVendorSearchForm> tempResult= new LinkedHashSet<ResearchReportProvidersVendorSearchForm>();
					for(ResearchReportProvidersVendorSearchForm sfr:mdavsfsResult){
						for(ResearchReportProvidersVendorSearchForm sf:filterResult.getValue()){
							if(sfr.getVendor().equals(sf.getVendor())){
								tempResult.add(sf);
								tempResult.add(sfr);
							}
						}
				}
					mdavsfsResult.clear();
					mdavsfsResult.addAll(tempResult);
				}else{
					mdavsfsResult = filterResult.getValue();
				}
			}
		}else{
			
			mdavsfsResult = searchResults.entrySet().iterator().next().getValue();
		}
		return mdavsfsResult;
		
	}
	@Transactional 
	@Override
	public List<TradingApplicationVendorSearchForm> getTAMultiAssetClassSearchResult(Map<Object, Object> searchData, TradingApplicationVendorSearchForm dataForm) {


	Session currentSession = this.sessionFactory.getCurrentSession();
	String assetClasses = (String)searchData.get("assetClassChk");
	String[] assetClassList = assetClasses.split(",");
	LinkedHashMap<String, List<TradingApplicationVendorSearchForm>> searchResults = new LinkedHashMap<String,List<TradingApplicationVendorSearchForm>>();
	
	for(String assetClass : assetClassList){
		StringBuffer query = new StringBuffer();
		query.append("SELECT v.fname,a.description,vtsd.accessibility,vtsd.suitability,vtsd.orderType,c.name,r.name as region");
		query.append(" FROM vendor v");
		query.append(" inner join vendor_TradingSoftwareDetails vtsd on vtsd.vendor_id = v.vendor_id");
		query.append(" inner join vendor_TradingCapabilitieSupported vtcs on vtcs.vendor_id = v.vendor_id");
		query.append(" inner join country c on c.country_id = v.countryofincorp");
		query.append(" inner join region r on r.region_id = v.regionofincorp");
		query.append(" inner join asset_class a on a.asset_class_id = vtsd.asset_class_id");
		query.append(" where a.description in ('"+assetClass+"')");

		if(dataForm.getSuitability() != null && !dataForm.getSuitability().isEmpty() ){
			query.append(" and va.suitability = '"+dataForm.getSuitability()+"'");
		}
		if(dataForm.getAccessibility() != null  && !dataForm.getAccessibility().isEmpty() ){
			query.append(" and va.accessibility = '"+dataForm.getAccessibility()+"'");
		}if(dataForm.getOrderType() != null  && !dataForm.getOrderType().isEmpty() ){
			query.append(" and vtsd.orderType = '"+dataForm.getOrderType()+"'");
		}		
		 
		Object dataacquisitioncostrange = searchData.get(assetClass.toLowerCase()+"dataacquisitioncostrange");
		if(dataacquisitioncostrange != null && !dataacquisitioncostrange.toString().isEmpty() ){
		   query.append(" and dc.cost_ids like '%"+dataacquisitioncostrange+"%'");
		}
		Object datacoverageregion = searchData.get(assetClass.toLowerCase()+"datacoverageregion");
		if(datacoverageregion != null && !datacoverageregion.toString().isEmpty() ){
		    query.append(" and dc.region_ids = '"+datacoverageregion+"'");
		}
		Object datacoveragecountry = searchData.get(assetClass.toLowerCase()+"datacoveragecountry");
		if(datacoveragecountry != null && !datacoveragecountry.toString().isEmpty() ){
			query.append(" and dc.country_ids='"+datacoveragecountry+"'");
		}
		Object datacoverageexchange = searchData.get(assetClass.toLowerCase()+"datacoverageexchange");
		if(datacoverageexchange != null  && !datacoverageexchange.toString().isEmpty()){
		   query.append(" and vd.exchanges='"+datacoverageexchange+"'");
		}
		
		Object securitytype = searchData.get(assetClass.toLowerCase()+"securitytype");
		if(securitytype != null  && !securitytype.toString().isEmpty()){
		   query.append(" and of.security_type_id="+securitytype);
		}
		SQLQuery createSQLQuery = currentSession.createSQLQuery(query.toString());
		List list = createSQLQuery != null ? createSQLQuery.list(): null;
		List<TradingApplicationVendorSearchForm> mdavsfs= new ArrayList<TradingApplicationVendorSearchForm>(); 
		if(list != null && list.size()>0){
			for(Object object:list){
				TradingApplicationVendorSearchForm mdavsf = new TradingApplicationVendorSearchForm();
				Object[] obj = (Object[])object;
				mdavsf.setVendor((String)obj[0]);
				mdavsf.setAssetClass((String)obj[1]);
				mdavsf.setAccessibility((String)obj[2]);
				mdavsf.setSuitability((String)obj[3]);
				mdavsf.setOrderType((String)obj[4]);
				mdavsf.setCoverageCountry((String)obj[5]);
				mdavsf.setCoverageRegion((String)obj[6]);
		
				mdavsfs.add(mdavsf);
			}
		}
		searchResults.put(assetClass, mdavsfs);
	}
	List<TradingApplicationVendorSearchForm> mdavsfsResult= new ArrayList<TradingApplicationVendorSearchForm>();
//	mdavsfsResult.retainAll(mdavsfsResult);
	
	if(searchResults.size()>1){
		for(Map.Entry<String, List<TradingApplicationVendorSearchForm>> filterResult: searchResults.entrySet()){
			if(mdavsfsResult.size()>1){
				Set<TradingApplicationVendorSearchForm> tempResult= new LinkedHashSet<TradingApplicationVendorSearchForm>();
				for(TradingApplicationVendorSearchForm sfr:mdavsfsResult){
					for(TradingApplicationVendorSearchForm sf:filterResult.getValue()){
						if(sfr.getVendor().equals(sf.getVendor())){
							tempResult.add(sf);
							tempResult.add(sfr);
						}
					}
			}
				mdavsfsResult.clear();
				mdavsfsResult.addAll(tempResult);
			}else{
				mdavsfsResult = filterResult.getValue();
			}
		}
	}else{
		
		mdavsfsResult = searchResults.entrySet().iterator().next().getValue();
	}

	
	return mdavsfsResult;
}
	
	@Override
	public Object getModelObjectById(Class<?> type, Serializable id) {
		Session session = this.sessionFactory.getCurrentSession();
		Object modelObject = session.load(type, id);
		return modelObject;
	}
	
	
	
	/* Research Report Search */
	@Override
	public Map<String, Object> getResearchReportVendorSearchResult(Map<Object, Object> searchData, 
			MarketDataAggregatorsVendorSearchForm dataForm) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		
		String vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, rarea.description RESEARCH_AREA, off.product_name OFFERING_NAME, off.product_description OFFERING_DESCRIPTION, cov.regions_covered REGION, cov.countries_covered COUTRIES, off.launched_year YEAR from ven_rsrch_rpt_offering off, ven_rsrch_rpt_covg cov, vendor ven, ven_rsrch_rpt_analyst_prof analyst, ven_rsrch_rpt_dtls details, users u, country country, security_types security, research_area rarea where u.username = ven.username and off.product_id = cov.product_id and off.product_id = analyst.product_id and ven.vendor_id = off.vendor_id and country.country_id = analyst.analyst_country and rarea.research_area_id = off.research_area and details.product_id = off.product_id ";
		StringBuilder searchSql = new StringBuilder(2000);
		searchSql.append(vendorSearchSql);
		
		if (dataForm.getRdAnalystRegionofIncorp() != null && 
				!dataForm.getRdAnalystRegionofIncorp().toString().trim().equals("") &&
				!dataForm.getRdAnalystRegionofIncorp().toString().trim().equalsIgnoreCase("Any")) {
			searchSql.append("and analyst.analyst_region in ( ");
			searchSql.append(dataForm.getRdAnalystRegionofIncorp());
			searchSql.append(" ) ");
		}
		
		if (dataForm.getRdAnalystCountryofIncorp() != null && 
				!dataForm.getRdAnalystCountryofIncorp().toString().trim().equals("") &&
				!dataForm.getRdAnalystCountryofIncorp().toString().trim().equalsIgnoreCase("Any")) {
			searchSql.append(" and analyst.analyst_country in ( ");
			searchSql.append(dataForm.getRdAnalystCountryofIncorp());
			searchSql.append(" ) ");
		}
		
		if (dataForm.getRdAnalystYearofExp() != null && 
				!dataForm.getRdAnalystYearofExp().toString().trim().equals("") &&
				!dataForm.getRdAnalystYearofExp().toString().trim().equalsIgnoreCase("Any")) {
			searchSql.append(" and analyst.analyst_year_of_exp = '");
			searchSql.append(dataForm.getRdAnalystYearofExp());
			searchSql.append("' ");
		}
		
		if (dataForm.getRdResearchAnalystWithCFA() != null && 
				!dataForm.getRdResearchAnalystWithCFA().toString().trim().equals("") &&
				dataForm.getRdResearchAnalystWithCFA().equals("on")) {
			searchSql.append(" and analyst.anayst_cfa_charter = 'Y' ");
		}
		
		if (dataForm.getRcExistingClientBase() != null && 
				!dataForm.getRcExistingClientBase().toString().trim().equals("") &&
				!dataForm.getRcExistingClientBase().toString().trim().equalsIgnoreCase("Any")) {
			searchSql.append(" and cov.existing_client_base = '");
			searchSql.append(dataForm.getRcExistingClientBase());
			searchSql.append("' ");
		}
		
		String assetClasses = (String)searchData.get("assetClassChk");
		String[] assetClassList = new String[0];
		
		if(assetClasses != null && !assetClasses.trim().equals("")) {
			assetClassList = assetClasses.split(",");
		}
						
		boolean firstAssetClass = false;
		
		for(String assetClass : assetClassList) {
			if(!firstAssetClass) {
				searchSql.append(" and (");
				firstAssetClass = true;
			}
			
			searchSql.append(" ( ");
			searchSql.append(" rarea.description = '");
			searchSql.append(assetClass);
			searchSql.append("'");
			
			assetClass = assetClass.replaceAll(" ", "_").toLowerCase() + "_research";
			
			Object coveragecountry = searchData.get(assetClass.toLowerCase() + "coveragecountry");
			populateFilterCondition(searchSql, coveragecountry, "cov.countries_covered");
			Object coverageregion = searchData.get(assetClass.toLowerCase() + "coverageregion");
			populateFilterCondition(searchSql, coverageregion, "cov.regions_covered");
			Object subarea = searchData.get(assetClass.toLowerCase() + "subarea");
			populateFilterCondition(searchSql, subarea, "off.research_sub_area");
			Object supportedby = searchData.get(assetClass.toLowerCase() + "supportedby");
			populateEqualFilterCondition(searchSql, supportedby, "off.stock_fund_issue_covered");
			Object applicableyear = searchData.get(assetClass.toLowerCase() + "applicableyear");
			populateEqualFilterCondition(searchSql, applicableyear, "details.res_period_year");
			Object applicablemonth = searchData.get(assetClass.toLowerCase() + "applicablemonth");
			populateEqualFilterCondition(searchSql, applicablemonth, "details.res_period_mon");
			searchSql.append(")");
			searchSql.append("or");
		}
		
		if(firstAssetClass) {
			searchSql.delete(searchSql.length() - 2, searchSql.length());
			searchSql.append(" ) ");
		}
		
		searchSql.append(" order by ven.vendor_id, off.research_area");
		logger.info("searchSql ################## QUERY == " + searchSql.toString());
		
		SQLQuery sqlQuery = currentSession.createSQLQuery(searchSql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> searchResultList = (List<Object[]>)sqlQuery.list();
		Set<VendorSearchResult> vendorSearchResultList = new HashSet<VendorSearchResult>();
		
		Map<String, Set<String>> assetCountries = new HashMap<String, Set<String>>();
		Map<String, Set<String>> assetRegions = new HashMap<String, Set<String>>();
		Map<String, Set<String>> researchSubAreas = new HashMap<String, Set<String>>();
		Map<String, List<VendorSearchResult>> awardsMap = new HashMap<String, List<VendorSearchResult>>();
		
		Map<String, Object> searchResultMap = new HashMap <String, Object>();
		
		StringBuilder vendorList = new StringBuilder();
		boolean vendorFound = false;
		int vendorCount = 0;
		for(Object[] searchRow : searchResultList) {
			vendorFound = true;
			vendorList.append("'");
			vendorList.append((String)searchRow[0]);
			vendorList.append("'");
			vendorCount++;
			if (vendorCount <= searchResultList.size() -1) {
				vendorList.append(",");
			}
		}
		
		List<Object[]> vendorAwardList = new ArrayList<Object[]>();
		if(vendorFound) {
			vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, rarea.description RESEARCH_AREA, off.product_name OFFERING_NAME, off.product_description OFFERING_DESCRIPTION, cov.regions_covered REGION, cov.countries_covered COUTRIES, off.launched_year YEAR, off.research_sub_area RESEARCH_SUB_AREA from ven_rsrch_rpt_offering off, ven_rsrch_rpt_covg cov, vendor ven, ven_rsrch_rpt_analyst_prof analyst, ven_rsrch_rpt_dtls details, users u, country country, security_types security, research_area rarea where u.username = ven.username and off.product_id = cov.product_id and off.product_id = analyst.product_id and ven.vendor_id = off.vendor_id and country.country_id = analyst.analyst_country and rarea.research_area_id = off.research_area and ven.vendor_id in (VENDOR_ID_LIST)";
			vendorSearchSql = vendorSearchSql.replaceAll("VENDOR_ID_LIST", vendorList.toString());
			sqlQuery = currentSession.createSQLQuery(vendorSearchSql);
			logger.info("vendorSearchSql ################## VENDOR SEARCH QUERY == " + vendorSearchSql.toString());
			searchResultList = (List<Object[]>)sqlQuery.list();
			String vendorAwardSql = "select vendor_id, Awardname, Awardsponsor, Awardedyear from vendor_awards where vendor_id in (" + vendorList.toString()  + ") order by vendor_id";
			sqlQuery = currentSession.createSQLQuery(vendorAwardSql);
			vendorAwardList = (List<Object[]>)sqlQuery.list();
		}
		
		for(Object[] searchRow : searchResultList) {
			
			VendorSearchResult vendorSearchRow = new VendorSearchResult();
			vendorSearchRow.setVendorId((String)searchRow[0]);
			vendorSearchRow.setVendorName((String)searchRow[1]);
			vendorSearchRow.setVendorCompany((String)searchRow[2]);
			vendorSearchRow.setVendorCountry((String)searchRow[3]);
			vendorSearchRow.setVendorType((String)searchRow[4]);
			
			String assetClass = (String)searchRow[5];	//Research Area
			String regions = (String)searchRow[8];
			String countries = (String)searchRow[9];
			String researchSubAreasList = (String)searchRow[11];
				
			String mapKey = (String)searchRow[0] + "_" + assetClass;
			
			if(countries != null && !countries.trim().equals("")) {
				String[] countryNames = countries.split(",");
				for(String countryName : countryNames) {
					Set<String> assetCountriesSet = assetCountries.get(mapKey);
					if(assetCountriesSet == null) {
						assetCountriesSet = new HashSet<String>();
						assetCountries.put(mapKey, assetCountriesSet);
						if(countryName != null && !countryName.trim().equals("")) {
							assetCountriesSet.add(countryName);
						}
					} else {
						if(countryName != null && !countryName.trim().equals("")) {
							assetCountriesSet.add(countryName);
						}
					}
				}
			}
			
			if(regions != null && !regions.trim().equals("")) {
				String[] regionNames = regions.split(",");
				for(String regionName : regionNames) {
					Set<String> assetRegionsSet = assetRegions.get(mapKey);
					if(assetRegionsSet == null) {
						assetRegionsSet = new HashSet<String>();
						assetRegions.put(mapKey, assetRegionsSet);
						if(regionName != null && !regionName.trim().equals("")) {
							assetRegionsSet.add(regionName);
						}
					} else {
						if(regionName != null && !regionName.trim().equals("")) {
							assetRegionsSet.add(regionName);
						}
					}
				}
			}
			
			if(researchSubAreasList != null && !researchSubAreasList.trim().equals("")) {
				String[] researchSubAreaNames = researchSubAreasList.split(",");
				for(String researchSubAreaName : researchSubAreaNames) {
					//String reasrchSubAreaMapKey = (String)searchRow[0] + "_" + assetClass + "_" + researchSubAreaName;
					Set<String> researchSubAreaSet = researchSubAreas.get(mapKey);
					if(researchSubAreaSet == null) {
						researchSubAreaSet = new HashSet<String>();
						researchSubAreas.put(mapKey, researchSubAreaSet);
						if(researchSubAreaName != null && !researchSubAreaName.trim().equals("")) {
							researchSubAreaSet.add(researchSubAreaName);
						}
					} else {
						if(researchSubAreaName != null && !researchSubAreaName.trim().equals("")) {
							researchSubAreaSet.add(researchSubAreaName);
						}
					}
				}
			}
			
			// 5 - Asset class, 8 - Region, 9 - Countries, 11 - Research Sub Area
			vendorSearchResultList.add(vendorSearchRow);
		}
		
		for(Object[] searchRow : vendorAwardList) {
			VendorSearchResult vendorSearchRow = new VendorSearchResult();
			vendorSearchRow.setVendorId((String)searchRow[0]);
			vendorSearchRow.setAwardName((String)searchRow[1]);
			vendorSearchRow.setAwardSponsor((String)searchRow[2]);
			vendorSearchRow.setAwardYear(searchRow[3].toString());
			
			List<VendorSearchResult> vendorSearchAwardList = awardsMap.get((String)searchRow[0]);
			
			if(vendorSearchAwardList == null) {
				vendorSearchAwardList = new ArrayList<VendorSearchResult>();
				vendorSearchAwardList.add(vendorSearchRow);
				awardsMap.put((String)searchRow[0], vendorSearchAwardList);
			}else {
				vendorSearchAwardList.add(vendorSearchRow);
			}
		}
		
		searchResultMap.put("vendorSearchResultList", vendorSearchResultList);
		searchResultMap.put("assetCountries", assetCountries);
		searchResultMap.put("assetRegions", assetRegions);
		searchResultMap.put("researchSubAreas", researchSubAreas);
		searchResultMap.put("awardsMap", awardsMap);
		
		logger.info("vendorSearchResultList == " + vendorSearchResultList);
		logger.info("assetCountries == " + assetCountries);
		logger.info("assetRegions == " + assetRegions);
		logger.info("researchSubAreas == " + researchSubAreas);
		logger.info("awardsMap == " + awardsMap);
		
		//logger.info("MAP == " + Arrays.toString(searchResultMap.entrySet().toArray()));
		
		return searchResultMap;
	}
	
	/* Trading Application Search */
	@Transactional
	@Override
	public Map<String, Object> getTradingApplicationVendorSearchResult(Map<Object, Object> searchData, 
			MarketDataAggregatorsVendorSearchForm dataForm) {
		
		Session currentSession = this.sessionFactory.getCurrentSession();
		
		String vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, asset.description ASSET_CLASS, off.product_name OFFERING_NAME, off.product_description OFFERING_DESCRIPTION, trad_capab.trad_region REGION, trad_capab.trad_country COUTRIES, off.launched_year YEAR, trad_capab.trad_exchange trad_exchange from ven_trad_app_offering off, ven_trad_app_soft_dtls soft_dtls, ven_trad_app_trad_capab trad_capab, asset_class asset, vendor ven, users u, country country, security_types security where u.username = ven.username and off.product_id = soft_dtls.product_id and off.product_id = trad_capab.product_id and asset.asset_class_id = off.asset_class_id and ven.vendor_id = off.vendor_id and country.country_id = ven.countryofincorp ";
		StringBuilder searchSql = new StringBuilder(2000);
		searchSql.append(vendorSearchSql);
		
		if (dataForm.getVendorregionofincorp() != null && !dataForm.getVendorregionofincorp().toString().trim().equals("")) {
			searchSql.append("and ven.regionofincorp in ( ");
			searchSql.append(dataForm.getVendorregionofincorp());
			searchSql.append(" ) ");
		}
		
		if (dataForm.getVendorcountryofincorp() != null && !dataForm.getVendorcountryofincorp().toString().trim().equals("")) {
			searchSql.append(" and ven.countryofincorp in ( ");
			searchSql.append(dataForm.getVendorcountryofincorp());
			searchSql.append(" ) ");
		}
		
		String assetClasses = (String)searchData.get("assetClassChk");
		String[] assetClassList = new String[0];
		
		if(assetClasses != null && !assetClasses.trim().equals("")) {
			assetClassList = assetClasses.split(",");
		}
						
		boolean firstAssetClass = false;
		
		for(String assetClass : assetClassList) {
			if(!firstAssetClass) {
				searchSql.append(" and (");
				firstAssetClass = true;
			}
			searchSql.append(" ( ");
			searchSql.append(" asset.description = '");
			searchSql.append(assetClass);
			searchSql.append("'");
			
			Object trad_region = searchData.get(assetClass.toLowerCase() + "tcsTradeCoverageRegion");
			populateFilterCondition(searchSql, trad_region, "trad_capab.trad_region");
			Object trad_country = searchData.get(assetClass.toLowerCase() + "tcsTradeCoverageCountry");
			populateFilterCondition(searchSql, trad_country, "trad_capab.trad_country");
			Object trad_exchange = searchData.get(assetClass.toLowerCase() + "tcsTradeCoverageExchange");
			populateFilterCondition(searchSql, trad_exchange, "trad_capab.trad_exchange");
			Object trad_capab_type = searchData.get(assetClass.toLowerCase() + "trad_capab_type");
			populateFilterCondition(searchSql, trad_capab_type, "trad_capab.trad_capab_type");
			Object trad_exec_type = searchData.get(assetClass.toLowerCase() + "tcsTradeExecutionsType");
			populateFilterCondition(searchSql, trad_exec_type, "trad_capab.trad_exec_type");
			Object security_types = searchData.get(assetClass.toLowerCase() + "securityTypes");
			populateFilterCondition(searchSql, security_types, "off.security_types");
						
			searchSql.append(")");
			searchSql.append("or");
		}
		
		if(firstAssetClass) {
			searchSql.delete(searchSql.length() - 2, searchSql.length());
			searchSql.append(" ) ");
		}
		
		searchSql.append(" order by vendor_id, asset.description");
		logger.info("Trading Application Search QUERY == " + searchSql.toString());
		
		SQLQuery sqlQuery = currentSession.createSQLQuery(searchSql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> searchResultList = (List<Object[]>)sqlQuery.list();
		Set<VendorSearchResult> vendorSearchResultList = new HashSet<VendorSearchResult>();
		
		Map<String, Set<String>> assetClassMap = new HashMap<String, Set<String>>();
		Map<String, Set<String>> assetRegions = new HashMap<String, Set<String>>();
		Map<String, Set<String>> assetExchanges = new HashMap<String, Set<String>>();
		Map<String, List<VendorSearchResult>> awardsMap = new HashMap<String, List<VendorSearchResult>>();
		
		Map<String, Object> searchResultMap = new HashMap <String, Object>();
		
		StringBuilder vendorList = new StringBuilder();
		boolean vendorFound = false;
		int vendorCount = 0;
		for(Object[] searchRow : searchResultList) {
			vendorFound = true;
			vendorList.append("'");
			vendorList.append((String)searchRow[0]);
			vendorList.append("'");
			vendorCount++;
			if (vendorCount <= searchResultList.size() -1) {
				vendorList.append(",");
			}
		}
		
		List<Object[]> vendorAwardList = new ArrayList<Object[]>();
		if(vendorFound) {
			vendorSearchSql = "select distinct ven.vendor_id, u.username, ven.company, country.name country, ven.companytype, asset.description ASSET_CLASS, off.product_name OFFERING_NAME, off.product_description OFFERING_DESCRIPTION, trad_capab.trad_region REGION, trad_capab.trad_country COUTRIES, off.launched_year YEAR, trad_capab.trad_exchange trad_exchange, off.security_types security_types, trad_capab.trad_exec_type trad_exec_type from ven_trad_app_offering off, ven_trad_app_soft_dtls soft_dtls, ven_trad_app_trad_capab trad_capab, asset_class asset, vendor ven, users u, country country, security_types security where u.username = ven.username and off.product_id = soft_dtls.product_id and off.product_id = trad_capab.product_id and asset.asset_class_id = off.asset_class_id and ven.vendor_id = off.vendor_id and country.country_id = ven.countryofincorp and ven.vendor_id in (VENDOR_ID_LIST)";
			vendorSearchSql = vendorSearchSql.replaceAll("VENDOR_ID_LIST", vendorList.toString());
			sqlQuery = currentSession.createSQLQuery(vendorSearchSql);
			logger.info("Trading Application VENDOR SEARCH QUERY == " + vendorSearchSql.toString());
			searchResultList = (List<Object[]>)sqlQuery.list();
			String vendorAwardSql = "select vendor_id, Awardname, Awardsponsor, Awardedyear from vendor_awards where vendor_id in (" + vendorList.toString()  + ") order by vendor_id";
			sqlQuery = currentSession.createSQLQuery(vendorAwardSql);
			vendorAwardList = (List<Object[]>)sqlQuery.list();
		}
		
		for(Object[] searchRow : searchResultList) {
			
			VendorSearchResult vendorSearchRow = new VendorSearchResult();
			vendorSearchRow.setVendorId((String)searchRow[0]);
			vendorSearchRow.setVendorName((String)searchRow[1]);
			vendorSearchRow.setVendorCompany((String)searchRow[2]);
			vendorSearchRow.setVendorCountry((String)searchRow[3]);
			vendorSearchRow.setVendorType((String)searchRow[4]);
			
			String assetClass = (String)searchRow[5];
			String regions = (String)searchRow[8];
			//String countries = (String)searchRow[9];
			String exchanges = (String)searchRow[11];
			//String securityNames = (String)searchRow[12];
			String tradeExecTypesValues = (String)searchRow[13];
			
			if(tradeExecTypesValues != null && !tradeExecTypesValues.trim().equals("")) {
				
				String[] tradeExecTypesList = tradeExecTypesValues.split(",");
				
				for(String tradeExecTypes : tradeExecTypesList) {
				
					if(tradeExecTypes != null && !tradeExecTypes.trim().equals("")) {
	
						if(tradeExecTypes.contains("DMA")) {
							tradeExecTypes = "Direct Market Access";
						} else if (tradeExecTypes.contains("SOR")) {
							tradeExecTypes = "Smart Order Routing";
						}
						
						String mapKey = (String)searchRow[0] + "_" + tradeExecTypes;
						
						if(assetClass != null && !assetClass.trim().equals("")) {
							Set<String> assetClassSet = assetClassMap.get(mapKey);
							if(assetClassSet == null) {
								assetClassSet = new HashSet<String>();
								assetClassMap.put(mapKey, assetClassSet);
								assetClassSet.add(assetClass);
							} else {
								assetClassSet.add(assetClass);
							}
						}
						
						if(regions != null && !regions.trim().equals("")) {
							String[] regionNames = regions.split(",");
							for(String regionName : regionNames) {
								Set<String> assetRegionsSet = assetRegions.get(mapKey);
								if(assetRegionsSet == null) {
									assetRegionsSet = new HashSet<String>();
									assetRegions.put(mapKey, assetRegionsSet);
									assetRegionsSet.add(regionName);
								} else {
									assetRegionsSet.add(regionName);
								}
							}
						}
						
						if(exchanges != null && !exchanges.trim().equals("")) {
							String[] exchangeNames = exchanges.split(",");
							for(String exchangeName : exchangeNames) {
								Set<String> assetExchangesSet = assetExchanges.get(mapKey);
								if(assetExchangesSet == null) {
									assetExchangesSet = new HashSet<String>();
									assetExchanges.put(mapKey, assetExchangesSet);
									assetExchangesSet.add(exchangeName);
								} else {
									assetExchangesSet.add(exchangeName);
								}
							}
						}
						
						// 5 - Asset class, 9 - Countries, 11 - Exchanges, 12- Security Types
						vendorSearchResultList.add(vendorSearchRow);
					}
				}
		
			}
		
		}
		
		for(Object[] searchRow : vendorAwardList) {
			VendorSearchResult vendorSearchRow = new VendorSearchResult();
			vendorSearchRow.setVendorId((String)searchRow[0]);
			vendorSearchRow.setAwardName((String)searchRow[1]);
			vendorSearchRow.setAwardSponsor((String)searchRow[2]);
			vendorSearchRow.setAwardYear(searchRow[3].toString());
			
			List<VendorSearchResult> vendorSearchAwardList = awardsMap.get((String)searchRow[0]);
			
			if(vendorSearchAwardList == null) {
				vendorSearchAwardList = new ArrayList<VendorSearchResult>();
				vendorSearchAwardList.add(vendorSearchRow);
				awardsMap.put((String)searchRow[0], vendorSearchAwardList);
			}else {
				vendorSearchAwardList.add(vendorSearchRow);
			}
		}
		
		searchResultMap.put("vendorSearchResultList", vendorSearchResultList);
		searchResultMap.put("assetClassMap", assetClassMap);
		searchResultMap.put("assetRegions", assetRegions);
		searchResultMap.put("assetExchanges", assetExchanges);
		searchResultMap.put("awardsMap", awardsMap);
		
		return searchResultMap;
	}
	
 }
