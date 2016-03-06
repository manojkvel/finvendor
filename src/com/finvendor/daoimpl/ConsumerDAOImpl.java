package com.finvendor.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.ConsumerDAO;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;

public class ConsumerDAOImpl implements ConsumerDAO {

	private static Logger logger = LoggerFactory.
			getLogger(ConsumerDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Consumer updateConsumerDetails(Consumer consumer) 
			throws ApplicationException {
		logger.debug("ConsumerDAOImpl : saveConsumerInfo");
		try {
			this.sessionFactory.getCurrentSession().saveOrUpdate(consumer);
		}catch(Exception exp) {
			logger.error("Error saving Consumer Details : {}", 
					consumer.getUser().getUserName(), exp);
			throw new ApplicationException("Error saving Consumer Details : " 
					+ consumer.getUser().getUserName());
		}
		return consumer;		
	}
	
	@Override
	public Consumer getConsumerInfoByEmail(String email) 
			throws ApplicationException {
		logger.debug("ConsumerDAOImpl : getConsumerInfoByEmail - {}", 
				email);
		Consumer consumer = null; 
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().
					createCriteria(Consumer.class);
			criteria.add(Restrictions.sqlRestriction("lower(email) like '" + 
					email.toLowerCase() + "'"));
			consumer = (Consumer) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading Consumer details for email - {}", 
					email, exp);
			throw new ApplicationException("Error reading Consumer details for email - " 
					+ email);
		}
		return consumer;
	}
	
	@Override	
	public CompanySubType getCompanySubType(int id) 
			throws ApplicationException {
		logger.debug("ConsumerDAOImpl : getCompanySubType");
		CompanySubType companySubType = null; 
		try {
			companySubType = (CompanySubType) this.sessionFactory.
					getCurrentSession().get(CompanySubType.class, id);
		}catch (Exception exp) {
			logger.error("Error getCompanySubType : " + exp);
			throw new ApplicationException("Error Reading CompanySubType : " 
					+ id);
		}
		return companySubType;
	}
}
