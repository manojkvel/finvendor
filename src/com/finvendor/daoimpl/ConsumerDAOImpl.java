/**
 * 
 */
package com.finvendor.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.ConsumerDAO;
import com.finvendor.model.Consumer;
import com.finvendor.model.Vendor;

/**
 * @author rayulu vemula
 *
 */
public class ConsumerDAOImpl implements ConsumerDAO{

	private static Logger logger = Logger.getLogger(ConsumerDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.ConsumerDAOImpl#saveConsumerInfo(com.finvendor.model.Consumer)
	 */
	@Transactional
	@Override
	public void saveConsumerInfo(Consumer consumer) {
		logger.info("saveConsumerInfo Method....");
		try{
			 this.sessionFactory.getCurrentSession().save(consumer);
			 this.sessionFactory.getCurrentSession().flush();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Error in saveConsumerInfo---- " + ex);
		}
		
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.ConsumerDAOImpl#getConsumerInfoByEmail(com.finvendor.model.Consumer)
	 */
	@Transactional
	@Override
	public Consumer getConsumerInfoByEmail(String email) {
		logger.info("getConsumerInfoByEmail method---");
		Consumer consumer=null; Criteria criteria=null;
		try{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Consumer.class);
			criteria.add(Restrictions.sqlRestriction("lower(email) like '" + email.toLowerCase() + "'"));
			consumer = (Consumer) criteria.uniqueResult();
		}catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error in getConsumerInfoByEmail---- " + ex);
		}
		return consumer;
	}
}
