package com.finvendor.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.dao.ifc.ICommonDao;
import com.finvendor.model.Roles;

/**
 * Common dao for quick test
 * @author ayush
 *
 */
@Repository
public class CommonDao implements ICommonDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Roles> executeNamedQuery(String namedQueryname) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQueryname);
		return (List<Roles>) query.list();
	}

}
