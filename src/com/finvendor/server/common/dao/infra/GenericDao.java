package com.finvendor.server.common.dao.infra;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author ayush on 28/Apr/2018
 *
 * @param <E>
 */
public abstract class GenericDao<E> {
	
	@Autowired
	private SessionFactory sessionFactory;
	private final Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public E findById(final Serializable id) {
		return (E) getSession().get(this.entityClass, id);
	}

	public Serializable save(E entity) {
		return getSession().save(entity);
	}

	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(E entity) {
		getSession().delete(entity);
	}

	public void deleteAll() {
		List<E> entities = findAll();
		for (E entity : entities) {
			getSession().delete(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return getSession().createCriteria(this.entityClass).list();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAllByExample(E entity) {
		Example example = Example.create(entity).ignoreCase().enableLike().excludeZeroes();
		return getSession().createCriteria(this.entityClass).add(example).list();
	}

	public void clear() {
		getSession().clear();

	}

	public void flush() {
		getSession().flush();
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
