package com.finvendor.server.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.CompanyDetails;
import com.finvendor.modelpojo.staticpojo.VoVendorDetails;
import com.finvendor.server.common.dao.ifc.AbsCommonDao;

/**
 * Common Dao
 * @author ayush on Feb 17, 2018
 */
@Repository
public class CommonDao extends AbsCommonDao {

	@SuppressWarnings("unchecked")
	public List<Roles> executeNamedQuery(String namedQueryname) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQueryname);
		return (List<Roles>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) {
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setInteger(0, Integer.parseInt(rsrchAreaId));
		List<Object[]> rows = query.list();

		List<CompanyDetails> results = new ArrayList<>();
		for (Object[] row : rows) {
			results.add(new CompanyDetails(Integer.parseInt(row[0].toString()), row[1].toString()));
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoVendorDetails> getVoVendorDetails(String sql) throws RuntimeException {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> rows = query.list();
		
		List<VoVendorDetails> results = new ArrayList<>();
		for (Object[] row : rows) {
			results.add(new VoVendorDetails(row[0].toString(), row[1].toString()));
		}
		return results;
	}

}
