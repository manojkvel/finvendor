package com.finvendor.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.bean.CompanyDetails;
import com.finvendor.common.dao.ifc.ICommonDao;
import com.finvendor.model.Roles;

/**
 * Common dao for quick test
 * 
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getCompanyDetails(int rsrchAreaId) {
		String sql = "select company_id, company_name from rsch_sub_area_company_dtls where rsch_sub_area_id in( SELECT research_sub_area_id FROM finvendo_dev.research_sub_area where research_area_id=?)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setInteger(0, rsrchAreaId);
		List<Object[]> rows = query.list();
		List<CompanyDetails> results = new ArrayList<>();
		for (Object[] row : rows) {
			CompanyDetails companyDetail = new CompanyDetails(Integer.parseInt(row[0].toString()), row[1].toString());
			results.add(companyDetail);
		}
		return results;
	}

}
