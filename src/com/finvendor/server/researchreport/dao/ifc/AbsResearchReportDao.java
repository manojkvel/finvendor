package com.finvendor.server.researchreport.dao.ifc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ResearchReportDao abstract interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class AbsResearchReportDao implements IResearchReportDao {
	protected static Logger logger = LoggerFactory.getLogger(AbsResearchReportDao.class);

	@Autowired
	protected SessionFactory sessionFactory;

	public AbsResearchReportDao() {
	}

	protected void appendFilterWithInClause(StringBuffer sqlSb, String filterCondition, List<String> inValues,
			boolean inClause) {
		StringBuffer inValueSb = new StringBuffer(100);
		inValueSb.append("(");
		for (String inValue : inValues) {
			inValueSb.append("'").append(inValue).append("'").append(",");
		}
		inValueSb.deleteCharAt(inValueSb.toString().length() - 1);
		inValueSb.append(")");
		if (inClause) {
			sqlSb.append(" AND ").append(filterCondition).append(" IN").append(inValueSb);
		} else {
			sqlSb.append(" AND ").append(filterCondition).append(" NOT IN").append(inValueSb);
		}
	}
	
	protected void appendFilterWithBetweenClause(StringBuffer sqlSb,String filterCondition, String firstValue,String secondValue) {
		StringBuffer betweenClauseSb = new StringBuffer(100);
		betweenClauseSb.append(" BETWEEN ").append(firstValue).append(" AND ").append(secondValue);
		sqlSb.append(filterCondition).append(betweenClauseSb);
	}
	
	protected void appendFilterLessThanClause(StringBuffer sqlSb,String filterCondition, String valueString) {
		StringBuffer lessThanSb = new StringBuffer(100);
		lessThanSb.append(" < ").append(valueString);
		sqlSb.append(filterCondition).append(lessThanSb);
	}
	
	protected void appendFilterGreaterThanClause(StringBuffer sqlSb,String filterCondition, String valueString) {
		StringBuffer greaterThanSb = new StringBuffer(100);
		greaterThanSb.append(" > ").append(valueString);
		sqlSb.append(filterCondition).append(greaterThanSb);
	}

	protected long convertStringToTimestamp(String str_date) throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = (Date) formatter.parse(str_date);
		return date.getTime();
	}

}
