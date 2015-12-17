/**
 * 
 */
package com.finvendor.dao;

import com.finvendor.model.Consumer;

/**
 * @author rayulu vemula
 *
 */
public interface ConsumerDAO {

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to save consumer info
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.ConsumerDAO#saveConsumerInfo()
	 */
	void saveConsumerInfo(Consumer consumer);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get consumer info
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.ConsumerDAO#getConsumerInfoByEmail()
	 */
	Consumer getConsumerInfoByEmail(String email);

}
