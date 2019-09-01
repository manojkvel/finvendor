package com.finvendor.api.common.dao;

import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class ReferenceDataDaoImpl {//implements ReferenceDataDao {

    private static final Logger logger = LoggerFactory.getLogger(ReferenceDataDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    
    public Object getModelObjectById(Class<?> type, Serializable id) {
        Session session = this.sessionFactory.getCurrentSession();
        Object modelObject = session.get(type, id);
        return modelObject;
    }

    /* Asset Class */

    
    public List<AssetClass> getAllAssetClasses()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllAssetClasses");
        List<AssetClass> assetClassList = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(
                    AssetClass.class);
            assetClassList = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading AssetClass details", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllAssetClasses");
        return assetClassList;
    }

    
    public AssetClass getAssetClassByDescription(String assetClassDescription)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAssetClassByDescription for : {}",
                assetClassDescription);
        AssetClass assetClass = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(
                    AssetClass.class);
            criteria.add(Restrictions.sqlRestriction("lower(description) like '" +
                    assetClassDescription.toLowerCase() + "'"));
            assetClass = (AssetClass) criteria.uniqueResult();
        } catch (Exception exp) {
            logger.error("Error reading AssetClass details for {}",
                    assetClassDescription, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAssetClassByDescription for : {}",
                assetClassDescription);
        return assetClass;
    }

    /* Security Types */

    
    public List<SecurityType> getAllSecurityTypes()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllSecurityTypes");
        List<SecurityType> securityTypeList = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(
                    SecurityType.class);
            securityTypeList = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading SecurityType details", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllSecurityTypes");
        return securityTypeList;
    }

    
    public SecurityType getSecurityTypeByName(String securityName)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getSecurityTypeByName for : {}",
                securityName);
        SecurityType securityType = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(
                    SecurityType.class);
            criteria.add(Restrictions.sqlRestriction("lower(name) like '" +
                    securityName.toLowerCase() + "'"));
            securityType = (SecurityType) criteria.uniqueResult();
        } catch (Exception exp) {
            logger.error("Error reading SecurityTypes details for {}",
                    securityName, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getSecurityTypeByName for : {}",
                securityName);
        return securityType;
    }

    
    public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getSecurityTypesForAssetClassId for : {}",
                assetClassId);
        Criteria criteria = null;
        List<SecurityType> securityTypesList = null;
        try {
            criteria = this.sessionFactory.openSession().createCriteria(SecurityType.class);
            criteria.add(Restrictions.eq("assetClassId", assetClassId));
            securityTypesList = criteria.list();
            logger.debug("securityTypesList = " + securityTypesList.size());
        } catch (Exception exp) {
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
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Region.class);
            regions = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All getAllRegions", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllRegions");
        return regions;
    }


    
    public Region getRegionByName(String regionName)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getRegionByName for : {}",
                regionName);
        Region region = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(
                    Region.class, "region");
            criteria.add(Restrictions.sqlRestriction("lower({alias}.name) like '" +
                    regionName.toLowerCase() + "'"));
            region = (Region) criteria.uniqueResult();
        } catch (Exception exp) {
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
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
            countries = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All Countries", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllCountries");
        return countries;
    }

    
    public Country getCountryByName(String countryName)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getCountryByName for : {}",
                countryName);
        Country country = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class, "country");
            criteria.add(Restrictions.sqlRestriction("lower({alias}name) like '" + countryName.toLowerCase() + "'"));
            country = (Country) criteria.uniqueResult();
        } catch (Exception exp) {
            logger.error("Error reading Country details for {}",
                    countryName, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getCountryByName for : {}",
                countryName);
        return country;
    }

    
    public List<Country> getCountriesByRegionId(String regionId)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getCountriesByRegionId for : {}",
                regionId);
        List<Country> countries = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class, "country");
            criteria.add(Restrictions.sqlRestriction("{alias}.region_id = " + regionId));
            countries = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading Country details for Region {}",
                    regionId, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getCountriesByRegionId for : {}",
                regionId);
        return countries;
    }

    /* Exchange */

    
    public List<Exchange> getAllExchanges()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllExchanges");
        List<Exchange> exchanges = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Exchange.class);
            exchanges = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All Exchanges", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllExchanges");
        return exchanges;
    }

    
    public Exchange getExchangeByName(String exchangeName)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getExchangesByName for : {}",
                exchangeName);
        Exchange exchange = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(
                    Exchange.class, "exchange");
            criteria.add(Restrictions.sqlRestriction("lower({alias}name) like '" +
                    exchangeName.toLowerCase() + "'"));
            exchange = (Exchange) criteria.uniqueResult();
        } catch (Exception exp) {
            logger.error("Error reading Exchange details for {}",
                    exchangeName, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getExchangesByName for : {}",
                exchangeName);
        return exchange;
    }

    
    public List<Exchange> getExchangesByCountryId(String countryId)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getCountriesByRegionId for : {}",
                countryId);
        List<Exchange> exchanges = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Exchange.class, "exchange");
            criteria.add(Restrictions.sqlRestriction("{alias}.country_id = " + countryId));
            exchanges = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading Country details for Region {}",
                    countryId, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getCountriesByRegionId for : {}",
                countryId);
        return exchanges;
    }

    /* ResearchArea (Research Report Vendor Offering) */

    
    public List<ResearchArea> getAllResearchAreaForResearchReportVendorOffering()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllResearchAreaForResearchReportVendorOffering");
        List<ResearchArea> researchAreas = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(ResearchArea.class);
            researchAreas = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All ResearchAreaForResearchReportVendorOffering", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllResearchAreaForResearchReportVendorOffering");
        return researchAreas;
    }


    
    public List<ResearchSubArea> getAllResearchSubAreaForResearchReportVendorOffering()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllResearchSubAreaForResearchReportVendorOffering");
        List<ResearchSubArea> researchSubAreas = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(ResearchSubArea.class);
            researchSubAreas = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All ResearchAreaForResearchReportVendorOffering", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllResearchSubAreaForResearchReportVendorOffering");
        return researchSubAreas;
    }


    
    public List<ResearchSubArea> getResearchSubAreaForResearchReportVendorOfferingByResearchAreaId(String researchAreaId)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getResearchSubAreaForResearchReportVendorOfferingByResearchAreaId for : {}",
                researchAreaId);
        List<ResearchSubArea> researchSubAreas = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(ResearchSubArea.class, "researchSubArea");
            criteria.add(Restrictions.sqlRestriction("{alias}.research_area_id = " + researchAreaId));
            researchSubAreas = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading Research Sub Area details for Research Area {}",
                    researchAreaId, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getResearchSubAreaForResearchReportVendorOfferingByResearchAreaId for : {}",
                researchAreaId);
        return researchSubAreas;
    }

    
    public List<ResearchAreaStockClassification> getAllResearchAreaStockClassificationForResearchReportVendorOffering()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllResearchAreaStockClassificationForResearchReportVendorOffering");
        List<ResearchAreaStockClassification> researchAreaStockClassifications = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(ResearchAreaStockClassification.class);
            researchAreaStockClassifications = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All getAllResearchAreaStockClassificationForResearchReportVendorOffering", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllResearchAreaStockClassificationForResearchReportVendorOffering");
        return researchAreaStockClassifications;
    }

    
    public List<ResearchSubAreaCompanyDetails> getAllResearchAreaCompanyDetailsForResearchReportVendorOffering()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllResearchAreaCompanyDetailsForResearchReportVendorOffering");
        List<ResearchSubAreaCompanyDetails> researchAreaCompanyDetails = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(ResearchSubAreaCompanyDetails.class);
            researchAreaCompanyDetails = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All getAllResearchAreaCompanyDetailsForResearchReportVendorOffering", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllResearchAreaCompanyDetailsForResearchReportVendorOffering");
        return researchAreaCompanyDetails;
    }

    
    public List<ResearchSubAreaCompanyDetails> getResearchAreaCompanyDetailsResearchReportVendorOfferingByResearchAreaId(String researchAreaId)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getResearchAreaCompanyDetailsResearchReportVendorOfferingByResearchAreaId for : {}",
                researchAreaId);
        List<ResearchSubAreaCompanyDetails> researchAreaCompanyDetails = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(ResearchSubAreaCompanyDetails.class, "researchArea");
            criteria.add(Restrictions.sqlRestriction("{alias}.rsch_sub_area_id = " + researchAreaId));
            researchAreaCompanyDetails = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading Research Sub Area details for Reasearch Area {}",
                    researchAreaId, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getResearchAreaCompanyDetailsResearchReportVendorOfferingByResearchAreaId for : {}",
                researchAreaId);
        return researchAreaCompanyDetails;
    }



    /* AnalyticalSolutionType (Analytical Application Vendor Offering) */

    
    public List<AnalyticalSolutionType> getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering");
        List<AnalyticalSolutionType> analyticalSolutionTypes = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(AnalyticalSolutionType.class);
            analyticalSolutionTypes = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All AnalyticalSolutionType", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering");
        return analyticalSolutionTypes;
    }

    
    public List<AnalyticalSolutionSubType> getAllAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOffering()
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering");
        List<AnalyticalSolutionSubType> analyticalSolutionSubType = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(AnalyticalSolutionSubType.class);
            analyticalSolutionSubType = criteria.list();
        } catch (Exception exp) {
            logger.error("Error loading All AnalyticalSolutionType", exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering");
        return analyticalSolutionSubType;
    }

    
    public List<AnalyticalSolutionSubType> getAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOfferingByAnalyticalSolutionTypeId(String analyticalSolutionTypeId)
            throws ApplicationException {
        logger.debug("Entering : ReferenceDataDaoImpl - getAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOfferingByAnalyticalSolutionTypeId for : {}",
                analyticalSolutionTypeId);
        List<AnalyticalSolutionSubType> analyticalSolutionSubTypes = null;
        Criteria criteria = null;
        try {
            criteria = this.sessionFactory.getCurrentSession().createCriteria(AnalyticalSolutionSubType.class, "analyticalSolutionSubType");
            criteria.add(Restrictions.sqlRestriction("{alias}.analytical_solution_type_id = " + analyticalSolutionTypeId));
            analyticalSolutionSubTypes = criteria.list();
        } catch (Exception exp) {
            logger.error("Error reading Analytical Solution Sub Type details for Analytical Solution {}",
                    analyticalSolutionTypeId, exp);
            throw new ApplicationException("Error fetching reference data details");
        }
        logger.debug("Leaving : ReferenceDataDaoImpl - getAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOfferingByAnalyticalSolutionTypeId for : {}",
                analyticalSolutionTypeId);
        return analyticalSolutionSubTypes;
    }
}
