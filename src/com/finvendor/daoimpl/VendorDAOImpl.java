package com.finvendor.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.finvendor.dao.VendorDAO;
import com.finvendor.form.FileDetails;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.model.SolutionTypes;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAnalystProfile;
import com.finvendor.model.VendorAnalyticsSoftwareDetails;
import com.finvendor.model.VendorAnalyticsfeaturesSupported;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorDataCoverage;
import com.finvendor.model.VendorDistribution;
import com.finvendor.model.VendorOffering;
import com.finvendor.model.VendorRegionCountryExchangeMap;
import com.finvendor.model.VendorResearchCoverage;
import com.finvendor.model.VendorResearchDetails;
import com.finvendor.model.VendorSolution;
import com.finvendor.model.VendorSupport;
import com.finvendor.model.VendorTradingCapabilitiesSupported;
import com.finvendor.model.VendorTradingSoftwareDetails;

public class VendorDAOImpl implements VendorDAO{
	
	private static Logger logger = Logger.getLogger(VendorDAOImpl.class);	
	private static String recordExist = "Record Already Exist";
		
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void saveVendorInfo(Vendor vendor) {
		logger.info("saveVendorInfo method---");
		try{
			this.sessionFactory.getCurrentSession().save(vendor);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Error in saveVendorInfo---- " + ex);
		}
		
	}
	
	@Transactional
	@Override
	public Vendor getVendorInfoByEmail(String email) {
		logger.info("getVendorInfoByEmail method---");
		Vendor vendor=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Vendor.class);
			criteria.add(Restrictions.sqlRestriction("lower(email) like '" + email.toLowerCase() + "'"));
			vendor = (Vendor) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getVendorInfoByEmail---- " + ex);
		}
		return vendor;
	}
	
	@Transactional
	@Override
	public Vendor getVendorInfoByUserName(String userName) {
		logger.info("getVendorInfoByEmail method---");
		String sqlQuery = "select vendor_id from vendor where username='"+userName+"'" ;
		Query query= sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
		Object object = query.uniqueResult();
		object = (Vendor)sessionFactory.getCurrentSession().get(Vendor.class, (Serializable) object);
		return (Vendor)object;
	}
	
	@Transactional
	@Override
	public void updateVendorPersonalInfoTab(Vendor vendor, String username) {
		logger.info("updateVendorPersonalInfoTab method---");
		String sqlQuery = null;
		Query query = null;
		try{
			Session currentSession = sessionFactory.getCurrentSession();
			/*	sqlQuery = " update vendor set fname='"+ vendor.getFirstName() +"', lname='"+vendor.getLastName()+"', designation='"+vendor.getDesignation()+"',company='"+vendor.getCompany()+"', secondary_email='"+vendor.getSecondaryEmail()+"'," +
					" telephone='"+vendor.getTelephone()+"',company_url='"+vendor.getCompanyUrl()+"',company_info='"+vendor.getCompanyInfo()+"', regionofincorp='"+vendor.getRegionofincorp()+"', " +
							"countryofincorp='"+vendor.getCountryofincorp()+"', logotype='"+vendor.getLogoType()+"', logoname='"+vendor.getLogoName()+"', logolength="+vendor.getLogoLength()+" where username= '"+username+"'" ;
			query= currentSession.createSQLQuery(sqlQuery);
			int executeUpdate = query.executeUpdate();
		*/			
			// System.out.println("unique... "+executeUpdate);
			sqlQuery = "select vendor_id from vendor where username='"+username+"'" ;
			query= currentSession.createSQLQuery(sqlQuery);
			Object uniqueResult = query.uniqueResult();
			Vendor vendorFromDB = (Vendor)currentSession.get(Vendor.class, (Serializable) uniqueResult);
			 //Transaction beginTransaction = currentSession.beginTransaction();
			 vendorFromDB.setFirstName(vendor.getFirstName());
			 vendorFromDB.setLastName(vendor.getLastName());
			 vendorFromDB.setDesignation(vendor.getDesignation());
			 vendorFromDB.setCompany(vendor.getCompany());
			 vendorFromDB.setCompanyUrl(vendor.getCompanyUrl());
			 vendorFromDB.setSecondaryEmail(vendor.getSecondaryEmail());
			 vendorFromDB.setTelephone(vendor.getTelephone());
			 vendorFromDB.setCompanyInfo(vendor.getCompanyInfo());
			 vendorFromDB.setRegionofincorp(vendor.getRegionofincorp());
			 vendorFromDB.setCountryofincorp(vendor.getCountryofincorp());
			 vendorFromDB.setVendorSupport(vendor.getVendorSupport());
			 currentSession.saveOrUpdate(vendorFromDB);
			// beginTransaction.commit();
			
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in updateVendorPersonalInfoTab---- " + ex);
		}
	}
	
	/*-------------------------------------------------------------------
	@Transactional
	@Override
	public AssetClass getAssetClassDetails(String asset_class) {
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
	*/
	
	/*---------------------------------------------------------------------------
	@Transactional
	@Override
	public SecurityType getSecurityTypes(String securities) {
		logger.info("getSecurityTypes method---");
		SecurityType securityType=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(SecurityType.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + securities.toLowerCase() + "'"));
			securityType = (SecurityType) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getSecurityTypes---- " + ex);
		}
		return securityType;
	}
	*/
	
	@Transactional
	@Override
	public void updateVendorOfferingDetails(VendorOffering vendorOffering) {
		logger.info("updateVendorOfferingDetails method---");
		try{
				this.sessionFactory.getCurrentSession().save(vendorOffering);	
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in updateVendorOfferingDetails---- " + ex);
		}
	}
	
	/*------------------------------------------------------------
	@Transactional
	@Override
	public Region getRegionsByName(String regionsName) {
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
	*/
	
	/*--------------------------------------------------------------
	@Transactional
	@Override
	public Country getCountryByName(String countryName) {
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
	*/
	
	/*-------------------------------------------------------------
	@Transactional
	@Override
	public Exchange getExchangesByName(String exchangeName) {
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
	*/
	
	
	@Transactional
	@Override
	public void updateVendorRegionCountryExchangeInfos(
			VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap) {
		logger.info("updateVendorRegionCountryExchangeInfos method---");
		try{
				this.sessionFactory.getCurrentSession().save(vendorRegionCountryExchangeMap);	
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in updateVendorRegionCountryExchangeInfos---- " + ex);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public Awards saveAwardDetails(Awards awards) {
		logger.info("saveAwardDetails method---");
		Awards awardsDetails= null;
		try{
			 awardsDetails= (Awards) this.sessionFactory.getCurrentSession().saveOrUpdateCopy(awards);
			 
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in saveAwardDetails---- " + ex);
		}
		return awardsDetails;
	}
	
	@Transactional
	@Override
	public void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap) {
		logger.info("updateVendorAwardDetails method---");
		try{
				this.sessionFactory.getCurrentSession().save(vendorAwardsMap);	
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in updateVendorAwardDetails---- " + ex);
		}
	}
	
	
	@Transactional
	@Override
	public Cost getCostInfo(String costNames) {
		logger.info("getCostInfo method---");
		Cost cost=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Cost.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + costNames.toLowerCase() + "'"));
			cost = (Cost) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getCostInfo---- " + ex);
		}
		return cost;
	}
	
	
	@Transactional
	@Override
	public Solutions getSolutionsInfo(String solutionId) {
		logger.info("getSolutionsInfo method---");
		Solutions solutions = null;
		try{
			 solutions = (Solutions)this.sessionFactory.getCurrentSession().get(Solutions.class, Integer.parseInt(solutionId));
			
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getSolutionsInfo---- " + ex);
		}
		return solutions;
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public VendorSolution updateVendorSolutionDetails(
			VendorSolution vendorSolution) {
		logger.info("updateVendorSolutionDetails method---");
		VendorSolution vendorSolutionDetails= null;
		try{
			this.sessionFactory.getCurrentSession().saveOrUpdate(vendorSolution);
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in updateVendorSolutionDetails---- " + ex);
		}
		return vendorSolutionDetails;
	}
	
	@Transactional
	@Override
	public Support getSupportInfo(String supportname) {
		logger.info("getSupportInfo method---");
		Support support=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Support.class);
			criteria.add(Restrictions.sqlRestriction("lower(name) like '" + supportname.toLowerCase() + "'"));
			support = (Support) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getSupportInfo---- " + ex);
		}
		return support;
	}
	
	@Transactional
	@Override
	public void updateVendorSupportInfo(VendorSupport vendorSupport) {
		logger.info("updateVendorSupportInfo method---");
		try{
				this.sessionFactory.getCurrentSession().save(vendorSupport);	
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in updateVendorSupportInfo---- " + ex);
		}	
	}
	
	@Transactional
	@Override
	public List<VendorOffering> getVendorOfferingDetails(String id) {
		logger.info("getVendorOfferingDetails method---");
		Session currentSession = this.sessionFactory.getCurrentSession();
		Set<VendorOffering> vendorOffering = null;
		try{
			Solutions solutions = (Solutions)currentSession.get(Solutions.class, Integer.parseInt(id));
			vendorOffering = solutions.getVendorOffering();
			 
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getVendorOfferingDetails---- " + ex);
		}
		
		return new ArrayList<VendorOffering>(vendorOffering);
		
	}
	
	@Transactional
	@Override
	public List<Solutions> listVednorSolution(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, id);
		Set<Solutions> solution = vendor.getSolution();
		return new ArrayList<Solutions>(solution);
	}
	
	@Transactional
	@Override
	public Solutions deleteVendorSolution(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Solutions vendorSolution = (Solutions)currentSession.get(Solutions.class, Integer.parseInt(id));
		if(vendorSolution != null)
			currentSession.delete(vendorSolution);
		
		return null;
	}
	
	@Transactional
	@Override
	public SolutionTypes getSolutionTypes(String name) {

		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = currentSession.createCriteria(SolutionTypes.class);
		 criteria.add(Restrictions.ilike("name", name+"%"));
		 SolutionTypes solutionTypes =(SolutionTypes)criteria.uniqueResult();
		 
		return solutionTypes;
	}
	
	@Transactional
	@Override
	public Solutions addSolutionsInfo(Solutions solutions) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(solutions);
		return solutions;
	}
	
	@Transactional
	@Override
	public List<Solutions> getSolutionsBasedOnOfferingTypes(SolutionTypes solutionTypes) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		List<Solutions> list = null;
		try{
		criteria = currentSession.createCriteria(Solutions.class);
		 criteria.add(Restrictions.eq("solutionTypes", solutionTypes));
		 list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@Transactional
	@Override
	public String addVendorDataCoverage(VendorDataCoverage vendorDataCoverage) {
		Session currentSession = sessionFactory.getCurrentSession();
		try{
		String hql = "Select vdc.dataCoverageId from VendorDataCoverage vdc join vdc.solution sln join vdc.vendorOffering vo where sln.solution_id=:solutionId and vo.vendor_offering_id=:vendorOfferingId and vdc.region=:regionId";      

		       Query query = currentSession.createQuery(hql);
	            query.setParameter("solutionId", vendorDataCoverage.getSolution().getSolution_id());
	            query.setParameter("vendorOfferingId", vendorDataCoverage.getVendorOffering().getVendor_offering_id());
	            query.setParameter("regionId", vendorDataCoverage.getRegion());
	            List result = query.list();
	            if(result != null && result.size() > 0)
	            	return recordExist;
	           currentSession.save(vendorDataCoverage);
		}catch(Exception e){
			e.printStackTrace();
		}   
	         return null;  
	}
	
	@Transactional
	@Override
	public List<VendorDataCoverage> listVendorDataCoverage(String id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, id);
		Set<VendorDataCoverage> vendorDataCoverage = vendor.getVendorDataCoverage();
		
		return new ArrayList<VendorDataCoverage>(vendorDataCoverage);
	}
	
	@Transactional
	@Override
	public String addVendorDistribution(VendorDistribution vendorDistribution) {
		Session currentSession = sessionFactory.getCurrentSession(); 
		
		try{
		String hql = "Select vdc.vendorDistributionId from VendorDistribution vdc join vdc.solution sln join vdc.vendorOffering vo  join vdc.offeringFiles ofile where sln.solution_id=:solutionId and vo.vendor_offering_id=:vendorOfferingId and ofile.offeringFilesId=:fieldId";      

		       Query query = currentSession.createQuery(hql);
	            query.setParameter("solutionId", vendorDistribution.getSolution().getSolution_id());
	            query.setParameter("vendorOfferingId", vendorDistribution.getVendorOffering().getVendor_offering_id());
	            query.setParameter("fieldId", vendorDistribution.getOfferingFiles().getOfferingFilesId());
	            List result = query.list();
	            if(result != null && result.size() > 0)
	            	return recordExist;
	            currentSession.save(vendorDistribution);
		}catch(Exception e){
			e.printStackTrace();
		} 
		
		return null;
		
	}
	@Transactional
	@Override
	public List<VendorDistribution> listVendorDistribution(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, id);
		Set<VendorDistribution> vendorDistribution = vendor.getVendorDistribution();
		return new ArrayList<VendorDistribution>(vendorDistribution);
	}
	
	@Transactional
	@Override
	public void deleteVendorDataCoverage(String selectedId) {
		Session currentSession = sessionFactory.getCurrentSession();
		VendorDataCoverage vendorDataCoverage = (VendorDataCoverage)currentSession.get(VendorDataCoverage.class, Integer.parseInt(selectedId));
		if(vendorDataCoverage != null)
			currentSession.delete(vendorDataCoverage);
	}
	
	@Transactional
	@Override
	public void deleteVendorDistribution(String selectedId) {
		Session currentSession = sessionFactory.getCurrentSession();
		VendorDistribution vendorDistribution = (VendorDistribution)currentSession.get(VendorDistribution.class, Integer.parseInt(selectedId));
		if(vendorDistribution != null)
			currentSession.delete(vendorDistribution);

		
	}
	
	@Transactional
	@Override
	public String addTradingCapabilitiesSupported(VendorTradingCapabilitiesSupported tradingCapabilitiesSupported) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession(); 
		
	try{
		currentSession.save(tradingCapabilitiesSupported);
	}catch(Exception e){
		e.printStackTrace();
	}
		return null;
	}
	
	@Transactional
	@Override
	public void addTradingSoftwareDetails(VendorTradingSoftwareDetails tradingSoftwareDetails) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession(); 
		currentSession.save(tradingSoftwareDetails);
	}
	
	@Transactional
	@Override
	public void addAnalyticsfeaturesSupported(VendorAnalyticsfeaturesSupported analyticsfeaturesSupported) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession(); 
		currentSession.save(analyticsfeaturesSupported);
	}
	
	@Transactional
	@Override
	public void addAnalyticsSoftwareDetails(VendorAnalyticsSoftwareDetails analyticsSoftwareDetails) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession(); 
		currentSession.save(analyticsSoftwareDetails);
	}
	
	@Transactional
	@Override
	public void addResearchCoverage(VendorResearchCoverage researchCoverage) {
		// TODO Auto-generated method stub
		try{
		Session currentSession = sessionFactory.getCurrentSession(); 
		currentSession.save(researchCoverage);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Transactional
	@Override
	public void addResearchDetails(VendorResearchDetails researchDetails) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession(); 
		currentSession.save(researchDetails);
	}
	
	@Transactional
	@Override
	public void addAnalystProfile(VendorAnalystProfile analystProfile) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession(); 
		currentSession.save(analystProfile);
	}
	
	@Transactional
	@Override
	public List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorTradingCapabilitiesSupported> vendorTradingCapabilitiesSupported = vendor.getVendorTradingCapabilitiesSupported();
		return new ArrayList<VendorTradingCapabilitiesSupported>(vendorTradingCapabilitiesSupported);
	}
	
	@Transactional
	@Override
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetails(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails = vendor.getVendorTradingSoftwareDetails();
		return new ArrayList<VendorTradingSoftwareDetails>(vendorTradingSoftwareDetails);
	}
	
	@Transactional
	@Override
	public List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorAnalyticsfeaturesSupported> vendorAnalyticsfeaturesSupported = vendor.getVendorAnalyticsfeaturesSupported();
		return new ArrayList<VendorAnalyticsfeaturesSupported>(vendorAnalyticsfeaturesSupported);
	}
	
	@Transactional
	@Override
	public List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorAnalyticsSoftwareDetails> vendorAnalyticsSoftwareDetails = vendor.getVendorAnalyticsSoftwareDetails();
		return new ArrayList<VendorAnalyticsSoftwareDetails>(vendorAnalyticsSoftwareDetails);
	}
	
	@Transactional
	@Override
	public List<VendorResearchCoverage> listResearchCoverage(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorResearchCoverage> vendorResearchCoverage = vendor.getVendorResearchCoverage();
		return new ArrayList<VendorResearchCoverage>(vendorResearchCoverage);
	}
	@Transactional
	@Override
	public List<VendorResearchDetails> listResearchDetails(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorResearchDetails> vendorResearchDetails = vendor.getVendorResearchDetails();
		return new ArrayList<VendorResearchDetails>(vendorResearchDetails);
	}
	@Transactional
	@Override
	public List<VendorAnalystProfile> listAnalystProfile(String objectId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, objectId);
		Set<VendorAnalystProfile> vendorAnalystProfile = vendor.getVendorAnalystProfile();
		return new ArrayList<VendorAnalystProfile>(vendorAnalystProfile);
	}
	@Transactional
	@Override
	public void deleteTradingCapabilitiesSupported(String objectId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		VendorTradingCapabilitiesSupported vendorTradingCapabilitiesSupported = (VendorTradingCapabilitiesSupported)currentSession.get(VendorTradingCapabilitiesSupported.class, Integer.parseInt(objectId));
		if(vendorTradingCapabilitiesSupported != null)
			currentSession.delete(vendorTradingCapabilitiesSupported);
	}
	@Transactional
	@Override
	public void deleteTradingSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		VendorTradingSoftwareDetails vendorTradingSoftwareDetails = (VendorTradingSoftwareDetails)currentSession.get(VendorTradingSoftwareDetails.class, Integer.parseInt(objectId));
		if(vendorTradingSoftwareDetails != null)
			currentSession.delete(vendorTradingSoftwareDetails);

	}
	@Transactional
	@Override
	public void deleteAnalyticsfeaturesSupported(String objectId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		VendorAnalyticsfeaturesSupported vendorAnalyticsfeaturesSupported = (VendorAnalyticsfeaturesSupported)currentSession.get(VendorAnalyticsfeaturesSupported.class, Integer.parseInt(objectId));
		if(vendorAnalyticsfeaturesSupported != null)
			currentSession.delete(vendorAnalyticsfeaturesSupported);

		
	}
	@Transactional
	@Override
	public void deleteAnalyticsSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		VendorAnalyticsSoftwareDetails vendorAnalyticsSoftwareDetails = (VendorAnalyticsSoftwareDetails)currentSession.get(VendorAnalyticsSoftwareDetails.class, Integer.parseInt(objectId));
		if(vendorAnalyticsSoftwareDetails != null)
			currentSession.delete(vendorAnalyticsSoftwareDetails);

		
	}
	@Transactional
	@Override
	public void deleteResearchCoverage(String objectId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		VendorResearchCoverage vendorResearchCoverage = (VendorResearchCoverage)currentSession.get(VendorResearchCoverage.class, Integer.parseInt(objectId));
		if(vendorResearchCoverage != null)
			currentSession.delete(vendorResearchCoverage);

		
	}
	@Transactional
	@Override
	public void deleteResearchDetails(String objectId) {
		// TODO Auto-generated method stub

		Session currentSession = sessionFactory.getCurrentSession();
		VendorResearchDetails vendorResearchDetails = (VendorResearchDetails)currentSession.get(VendorResearchDetails.class, Integer.parseInt(objectId));
		if(vendorResearchDetails != null)
			currentSession.delete(vendorResearchDetails);

		
	}
	@Transactional
	@Override
	public void deleteAnalystProfile(String objectId) {
		// TODO Auto-generated method stub

		Session currentSession = sessionFactory.getCurrentSession();
		VendorAnalystProfile vendorAnalystProfile = (VendorAnalystProfile)currentSession.get(VendorAnalystProfile.class, Integer.parseInt(objectId));
		if(vendorAnalystProfile != null)
			currentSession.delete(vendorAnalystProfile);
	
	}
	@Transactional
	@Override
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetailsBasedOnSolutionId(String solutionId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Solutions solution = (Solutions)currentSession.get(Solutions.class, Integer.parseInt(solutionId));
		Set<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails = solution.getVendorTradingSoftwareDetails();
		return new ArrayList<VendorTradingSoftwareDetails>(vendorTradingSoftwareDetails);
	}
	@Transactional
	@Override
	public List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId(String solutionId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Solutions solution = (Solutions)currentSession.get(Solutions.class, Integer.parseInt(solutionId));
		Set<VendorResearchCoverage> vendorResearchCoverage = solution.getVendorResearchCoverage();
		return new ArrayList<VendorResearchCoverage>(vendorResearchCoverage);
	 
	}
	
	
	/*-------------------------------------------------------------------------
	@Transactional
	@Override
	public String getRegion(String countryId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(countryId));
		Region region = country.getRegion();
		return region.getName();
	}
	*/
	
	/*---------------------------------------------------------------------------------------------
	@Transactional
	@Override
	public Country getCountryById(String countryId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Country country = (Country)currentSession.get(Country.class, Integer.parseInt(countryId));
		return country;
	}
	*/
	
	@Transactional
	@Override
	public List<VendorAwardsMap> listVendorAwardDetails(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor)currentSession.get(Vendor.class, id);
		Set<VendorAwardsMap> vendorAwardsMaps = vendor.getVendorAwardsMaps();
		return new ArrayList<VendorAwardsMap>(vendorAwardsMaps);
	}
	@Transactional
	@Override
	public Boolean isAwardAlreadyExist(String value) {
		
		String val[] = value != null? value.split(","): null; 
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(VendorAwardsMap.class);
			criteria.add(Restrictions.sqlRestriction("lower(awardname) like '" + val[0] + "'"));
			criteria.add(Restrictions.sqlRestriction(" and awardedyear=" + val[1]));
			 List list = criteria.list();
		if(list != null && list.size()>0)
		     return true;
		return false;
	}
	@Transactional
	@Override
	public Boolean isSolutionAlreadyExist(String value) {

		Query query = this.sessionFactory.getCurrentSession().createQuery("from Solutions s where s.name = '"+value+"'");
		List list = query.list();
		if(list != null && list.size()>0)
		       return true;
		return false;
	}
	@Transactional
	@Override
	public void deleteAwardDetails(String objectVar) {

		Session currentSession = sessionFactory.getCurrentSession();
		VendorAwardsMap vendorAwardsMap = (VendorAwardsMap)currentSession.get(VendorAwardsMap.class, Integer.parseInt(objectVar));
		if(vendorAwardsMap != null)
			currentSession.delete(vendorAwardsMap);
	}
	@Transactional
	@Override
	public Boolean isTradingSoftwareDetailsOfferingExist(String value) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from VendorTradingSoftwareDetails s where s.offering = '"+value+"'");
		List list = query.list();
		if(list != null && list.size()>0)
		       return true;
		return false;
	}
	@Transactional
	@Override
	public Object updateVendorLogo(FileDetails ufile, String username) {
		try{
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Vendor.class,"v");
		criteria.add(Restrictions.sqlRestriction("{alias}.username = '"+ username+"'"));
		Vendor vendor =(Vendor)criteria.uniqueResult();
		if(vendor != null){
			vendor.setLogoType(ufile.getType());
			vendor.setLogoName(ufile.getName());
			vendor.setLogoLength(ufile.getLength());
			vendor.setLogoBytes(ufile.getBlob());
			currentSession.update(vendor);
		}
		}catch(Exception e){
			
		}
		return null;
	}
	
	private final static String MARKET_DATA_VENDOR_OFFERINGS = "select asset.description ASSET_CLASS, off.name OFFERING_NAME, off.description OFFERING_DESCRIPTION, cov.region_ids REGION, cov.country_ids COUTRIES, off.LaunchedYear YEAR from vendor_offering off, vendor_datacoverage cov, asset_class asset where off.vendor_offering_id = cov.vendor_offering_id and asset.asset_class_id = off.asset_class_id and off.vendor_id = cov.vendor_id and off.vendor_id = :vendorId";
	private final static String TRADING_APPLICATION_VENDOR_OFFERINGS = "select asset.description ASSET_CLASS, soft.LaunchedYear LAUNCH_YEAR, soft.Offering OFFERING, soft.Offering_Desc OFFERING_DESC, capa.TradeCoverageRegion TRADE_COVERAGE_REGION, capa.TradableMarkets TRADEABLE_MARKETS, capa.TradingCapabilitiesType TRADING_CAPABILITY_TYPE, capa.TradeExecutionsType TRADE_EXECUTION_TYPE from vendor_tradingsoftwaredetails soft, vendor_tradingcapabilitiesupported capa, asset_class asset where soft.solution_id = capa.solution_id and soft.Offering = capa.Offering and soft.vendor_id = capa.vendor_id and soft.asset_class_id = asset.asset_class_id and soft.vendor_id = :vendorId order by ASSET_CLASS, LAUNCH_YEAR";
	private final static String ANALYTICS_APPLICATION_VENDOR_OFFERINGS = "select soft.AnalyticsSolutionsType OFFERING, soft.ApplicationBriefDesc OFFERING_DESCRIPTION, soft.AnalyticsSolutionsSubType SUB_TYPE, soft.LaunchedYear LAUNCH_YEAR, soft.ApplicationSubscriptionCost SUBSCRIPTION_COST from vendor_analyticssoftwaredetails soft where soft.vendor_id = :vendorId order by OFFERING, LAUNCH_YEAR";
	private final static String RESEARCH_REPORT_VENDOR_OFFERINGS = "select cov.ResearchArea RESERACH_AREA, cov.Offering OFFERING, cov.OfferingDesc OFFERING_DESCRIPTION, cov.ResearchSubArea RESERACH_SUB_AREA, cov.RegionsCovered REGIONS_COVERED, det.ResearchApplicableMonth + '-' + det.ResearchApplicableYear REASEARCH_PERIOD, prof.AnalystName ANALYST_NAME, det.subsriptionCostUSDperannum COST_PER_ANNUM from vendor_researchcoverage cov, vendor_researchdetails det, vendor_analystprofile prof where cov.solution_id = det.solution_id and det.solution_id = prof.solution_id and cov.Offering = det.Offering and det.Offering = prof.offering and cov.vendor_id = det.vendor_id and det.vendor_id = prof.vendor_id and cov.vendor_id = :vendorId order by RESERACH_AREA";
	private final static String VENDOR_AWARD_DETAILS = "select Awardname AWARD_NAME, Awardsponsor AWARD_SPONSER, Awardedyear AWARD_YEAR, awardVendorType VENDOR_TYPE, case when awardVendorType = 'Data Aggregator vendor' then awardAssetclass when awardVendorType = 'Trading Application vendor' then awardAssetclass when awardVendorType = 'Analytics Application vendor' then awardAnalyticsSolutionsType when awardVendorType = 'Research Reporting vendor' then awardResearchArea end ASSET_CLASS, case when awardVendorType = 'Data Aggregator vendor' then 1 when awardVendorType = 'Trading Application vendor' then 2 when awardVendorType = 'Analytics Application vendor' then 3 when awardVendorType = 'Research Reporting vendor' then 4 end VENDOR_ORDER from vendor_awards where vendor_id = :vendorId order by VENDOR_ORDER, awardAssetclass";
	
	@Override
	public List<Object[]> getMarketDataVendorOfferingsForProfile(String vendorId) {
		SQLQuery hibQuery = this.sessionFactory.getCurrentSession().createSQLQuery(MARKET_DATA_VENDOR_OFFERINGS);
		hibQuery.setParameter("vendorId", vendorId);
		@SuppressWarnings("unchecked")
		List<Object[]> tableData = hibQuery.list();
		return tableData;
	}
	
	@Override
	public List<Object[]> getTradingApplicationOfferingsForProfile(String vendorId) {
		SQLQuery hibQuery = this.sessionFactory.getCurrentSession().createSQLQuery(TRADING_APPLICATION_VENDOR_OFFERINGS);
		hibQuery.setParameter("vendorId", vendorId);
		@SuppressWarnings("unchecked")
		List<Object[]> tableData = hibQuery.list();
		return tableData;
	}
	
	@Override
	public List<Object[]> getAnalyticsApplicationOfferingsForProfile(String vendorId) {
		SQLQuery hibQuery = this.sessionFactory.getCurrentSession().createSQLQuery(ANALYTICS_APPLICATION_VENDOR_OFFERINGS);
		hibQuery.setParameter("vendorId", vendorId);
		@SuppressWarnings("unchecked")
		List<Object[]> tableData = hibQuery.list();
		return tableData;
	}
	
	@Override
	public List<Object[]> getResearchReportOfferingsForProfile(String vendorId) {
		SQLQuery hibQuery = this.sessionFactory.getCurrentSession().createSQLQuery(RESEARCH_REPORT_VENDOR_OFFERINGS);
		hibQuery.setParameter("vendorId", vendorId);
		@SuppressWarnings("unchecked")
		List<Object[]> tableData = hibQuery.list();
		return tableData;
	}
	
	@Override
	public List<Object[]> getVendorAwardDetailsForProfile(String vendorId) {
		SQLQuery hibQuery = this.sessionFactory.getCurrentSession().createSQLQuery(VENDOR_AWARD_DETAILS);
		hibQuery.setParameter("vendorId", vendorId);
		@SuppressWarnings("unchecked")
		List<Object[]> tableData = hibQuery.list();
		return tableData;
	}
}