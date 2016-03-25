package com.finvendor.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.ReferenceDataDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.SecurityType;

public class ReferenceDataDaoImpl implements ReferenceDataDao {

	private static Logger logger = LoggerFactory.getLogger(ReferenceDataDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId)
			throws ApplicationException {		
		logger.debug("Entering : ReferenceDataDaoImpl - getSecurityTypesForAssetClassId for : {}", assetClassId);
		Criteria criteria = null;
		List<SecurityType> securityTypesList = null;
		try {
			criteria = this.sessionFactory.openSession().createCriteria(SecurityType.class);
			criteria.add(Restrictions.eq("assetClassId", assetClassId));
			securityTypesList = criteria.list();
			logger.info("securityTypesList = " + securityTypesList.size());
		}catch (Exception exp) {
			logger.error("Error reding SecurityTypes for asset class id : {}", assetClassId, exp);
			throw new ApplicationException("Error reding SecurityTypes for asset class id : " + assetClassId);
		}
		logger.debug("Leaving : ReferenceDataDaoImpl - getSecurityTypesForAssetClassId for {}", assetClassId);
		return securityTypesList;
	}
}
