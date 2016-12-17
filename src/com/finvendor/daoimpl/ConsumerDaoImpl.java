package com.finvendor.daoimpl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.ConsumerDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.form.FileDetails;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;
import com.finvendor.model.Vendor;

public class ConsumerDaoImpl implements ConsumerDao {

	private static Logger logger = LoggerFactory.
			getLogger(ConsumerDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Resource(name = "finvendorProperties")
	private Properties finvendorProperties;

	@Override
	public Consumer updateConsumerDetails(Consumer consumer) 
			throws ApplicationException {
		logger.debug("ConsumerDaoImpl : saveConsumerInfo");
		try {
			this.sessionFactory.getCurrentSession().saveOrUpdate(consumer);
		}catch(Exception exp) {
			logger.error("Error saving Consumer Details : {}", 
					consumer.getUser().getUserName(), exp);
			throw new ApplicationException("Error saving Consumer Details : " 
					+ consumer.getUser().getUserName());
		}
		return consumer;		
	}
	
	@Override
	public Consumer getConsumerInfoByEmail(String email) 
			throws ApplicationException {
		logger.debug("ConsumerDaoImpl : getConsumerInfoByEmail - {}", 
				email);
		Consumer consumer = null; 
		Criteria criteria = null;
		try{
			criteria = this.sessionFactory.getCurrentSession().
					createCriteria(Consumer.class);
			criteria.add(Restrictions.sqlRestriction("lower(email) like '" + 
					email.toLowerCase() + "'"));
			consumer = (Consumer) criteria.uniqueResult();
		}catch (Exception exp) {
			logger.error("Error reading Consumer details for email - {}", 
					email, exp);
			throw new ApplicationException("Error reading Consumer details for email - " 
					+ email);
		}
		return consumer;
	}
	
	@Override	
	public CompanySubType getCompanySubType(int id) 
			throws ApplicationException {
		logger.debug("ConsumerDaoImpl : getCompanySubType");
		CompanySubType companySubType = null; 
		try {
			companySubType = (CompanySubType) this.sessionFactory.
					getCurrentSession().get(CompanySubType.class, id);
		}catch (Exception exp) {
			logger.error("Error getCompanySubType : " + exp);
			throw new ApplicationException("Error Reading CompanySubType : " 
					+ id);
		}
		return companySubType;
	}
	
	@Override
	public List<Object[]> loadConsumerMyProfile(String consumerId, String tableKey) 
			throws ApplicationException {
		logger.debug("Entering : ConsumerDaoImpl - loadConsumerMyProfile for {}", consumerId);
		String tableMetadata = finvendorProperties.getProperty(tableKey);
		String [] tableDetails = tableMetadata.split(":");
		String tableName = tableDetails[0];
		String columnNames = tableDetails[1];
		StringBuilder query = new StringBuilder(50);
		query.append("select ");
		query.append(columnNames);
		query.append(" from ");
		query.append(tableName);
		query.append(" where consumer_id = '");
		query.append(consumerId);
		query.append("'");
		SQLQuery hibQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> tableData = hibQuery.list();
		logger.debug("Leaving : ConsumerDaoImpl - loadConsumerMyProfile for {}", consumerId);
		return tableData;
	}
	
	@Override
	public List<Object[]> updateConsumerProfileDetails(String consumerId, String tableKey, 
			List<Object[]> tableData) throws ApplicationException {
		String tableMetadata = finvendorProperties.getProperty(tableKey);
		String [] tableDetails = tableMetadata.split(":");
		String tableName = tableDetails[0];
		String columnNames = tableDetails[1];
		Session hibSession = this.sessionFactory.getCurrentSession();		
		StringBuilder query = new StringBuilder(25);
		query.append("delete from ");
		query.append(tableName);
		query.append(" where consumer_id = '");
		query.append(consumerId);
		query.append("'");
		SQLQuery hibQuery = hibSession.createSQLQuery(query.toString());
		hibQuery.executeUpdate();
		
		query = new StringBuilder(25);
		query.append("insert into ");
		query.append(tableName);
		query.append("( consumer_id, ");
		query.append(columnNames);
		query.append(") values (");
		StringBuilder sqlQuery = null;		
		/* CHANGE TO BATCH PROCESSING */		
		for(Object[] tableRow : tableData) {
			sqlQuery = new StringBuilder(50);
			sqlQuery.append(query.toString());
			sqlQuery.append("'");
			sqlQuery.append(consumerId);
			sqlQuery.append("',");
			for(Object tableColumn : tableRow) {
				sqlQuery.append("'");
				sqlQuery.append(tableColumn.toString());
				sqlQuery.append("',");
			}
			sqlQuery.replace(sqlQuery.length() - 1, sqlQuery.length(), ")");
			try{
				hibQuery = hibSession.createSQLQuery(sqlQuery.toString());
				hibQuery.executeUpdate();
			}catch(Exception exp) {
				logger.error("ConsumerDaoImpl - updateConsumerProfileDetails : Error Updating Consumer Profile", exp);
				throw new ApplicationException(exp.getMessage());
			}
		}
		return tableData;
	}
	
	@Override
	public Object updateConsumerLogo(FileDetails ufile, String username) {
		try{
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Consumer.class, "c");
		criteria.add(Restrictions.sqlRestriction("{alias}.username = '"+ username+"'"));
		Consumer consumer =(Consumer)criteria.uniqueResult();
		if(consumer != null){
			consumer.setLogoType(ufile.getType());
			consumer.setLogoBytes(ufile.getBlob());
			currentSession.update(consumer);
		}
		}catch(Exception exp){
			logger.error("Error updating Consumer logo file", exp);
		}
		return null;
	}
}
