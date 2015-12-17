/**
 * 
 */
package com.finvendor.service;

import com.finvendor.model.Consumer;

/**
 * @author rayulu vemula
 *
 */
public interface ConsumerService {

	/** --------------------------------------------------------------------- */
	/**
	 * Method to save consumer info 
	 * 
	 * @param vendor
	 * @return 
	 */
	void saveConsumerInfo(Consumer consumer);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get consumer info by emailId 
	 * 
	 * @param email
	 * @return 
	 */
	Consumer getConsumerInfoByEmail(String email);

}
