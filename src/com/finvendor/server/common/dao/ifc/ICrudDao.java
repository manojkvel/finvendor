package com.finvendor.server.common.dao.ifc;

import java.io.Serializable;
import java.util.List;

public interface ICrudDao<T> {
	/**
	 * 
	 * @param entity:
	 *            entity to save
	 * @return Identifier of saved entity
	 */
	Serializable save(T entity);

	/**
	 * 
	 * @param entity:
	 *            entity to save or update
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 
	 * @param entity:
	 *            entity to delete
	 */
	void delete(T entity);

	/**
	 * Delete all records
	 */
	void deleteAll();

	/**
	 * Find all records
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * Find all records matching provided entity
	 * 
	 * @param entity:
	 *            entity object used for search
	 * @return
	 */
	List<T> findAllByExample(T entity);

	/**
	 * Find by primary key
	 * 
	 * @param id
	 * @return unique entity
	 */
	T findById(Serializable id);

	/**
	 * Clear session
	 */
	void clear();

	/**
	 * Flush session
	 */
	void flush();
}
