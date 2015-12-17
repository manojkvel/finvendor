/**
 * 
 */
package com.finvendor.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.ConsumerDAO;
import com.finvendor.model.Consumer;
import com.finvendor.service.ConsumerService;

/**
 * @author rayulu vemula
 *
 */
public class ConsumerServiceImpl implements ConsumerService{

	private static Logger logger = Logger.getLogger(ConsumerServiceImpl.class);

	@Autowired
	private ConsumerDAO consumerDAO;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.ConsumerServiceImpl#saveConsumerInfo(com.finvendor.model.Consumer)
	 */
	@Override
	public void saveConsumerInfo(Consumer consumer) {
		logger.info("saveConsumerInfo method---:");
		consumerDAO.saveConsumerInfo(consumer);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.ConsumerServiceImpl#getConsumerInfoByEmail(com.finvendor.model.Consumer)
	 */
	@Override
	public Consumer getConsumerInfoByEmail(String email) {
		logger.info("getConsumerInfoByEmail method---:");
		return consumerDAO.getConsumerInfoByEmail(email);
	}
	
}
