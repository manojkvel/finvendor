package com.finvendor.server.common.commondao.impl;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.server.common.commondao.AbstractCommonDao;

/**
 * @author ayush on Feb 17, 2018
 */
@Repository
public class CommonDaoImpl extends AbstractCommonDao {

	@SuppressWarnings("unchecked")
	public List<Roles> executeNamedQuery(String namedQueryname) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQueryname);
		return (List<Roles>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) {
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setInteger(0, Integer.parseInt(rsrchAreaId));
			List<Object[]> rows = query.list();

			List<CompanyDetails> results = new ArrayList<>();
			for (Object[] row : rows) {
				results.add(new CompanyDetails(Integer.parseInt(row[0].toString()), row[1].toString()));
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Query getNamedQuery(String namedQuery, Map<Object, Object> paramMap) {
		try {
			org.hibernate.Query query = sessionFactory.openSession().getNamedQuery(namedQuery);
			for (Map.Entry<Object, Object> paramEntry : paramMap.entrySet()) {
				query.setParameter((String) paramEntry.getKey(), paramEntry.getValue());
			}
			return query;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insert(String sql, Map<Integer, Object> params) throws RuntimeException {
		try {
			SQLQuery insertQuery = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			for (Map.Entry<Integer, Object> entry : params.entrySet()) {
				Integer index = entry.getKey();
				Object value = entry.getValue();
				insertQuery.setParameter(index, value);
			}
			return insertQuery.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
