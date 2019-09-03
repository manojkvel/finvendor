package com.finvendor.api.admin.dao;

import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.ReferenceData;
import com.finvendor.model.TableColumn;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminDao {

    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public List<Object[]> getReferenceData(ReferenceData refData) {
        logger.debug("Entering : AdminDaoImpl - getReferenceData for {}", refData.getTableName());
        String sql = "SELECT " + refData.getColumnNames() + " FROM " + refData.getTableName();
        logger.debug("AdminDaoImpl - getReferenceData : sql - ", sql);
        SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.list();
        logger.debug(logTableData(results));
        logger.debug("Leaving : AdminDaoImpl - getReferenceData for {}", refData.getTableName());
        return results;
    }

    public List<Object[]> getReferenceDataRow(ReferenceData refData, String primaryKeyValue) {
        logger.debug("Entering : AdminDaoImpl - getReferenceDataRow for {}", refData.getTableName());
        String sql = "SELECT " + refData.getColumnNames() + " FROM " + refData.getTableName() + getFilterWithPrimaryKey(refData, primaryKeyValue);
        logger.info("AdminDaoImpl - getReferenceDataRow : sql - ", sql);
        SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.list();
        logger.debug("Leaving : AdminDaoImpl - getReferenceDataRow for {}", refData.getTableName());
        return results;
    }

    public int updateReferenceDataRow(String query) throws ApplicationException {
        try {
            SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
            return sqlQuery.executeUpdate();
        } catch (Exception exp) {
            logger.error("AdminDaoImpl - updateReferenceDataRow : Error Updating Reference Data", exp);
            throw new ApplicationException(exp.getMessage());
        }
    }

    private String getFilterWithPrimaryKey(ReferenceData refData, String primaryKeyValue) {
        StringBuilder filter = new StringBuilder(25);
        List<TableColumn> primKeys = new ArrayList<TableColumn>();
        for (TableColumn column : refData.getColumnList()) {
            if (column.getPrimaryKey()) {
                primKeys.add(column);
            }
        }
        String[] filterValues = primaryKeyValue.split(";");
        int indexCounter = 0;
        filter.append(" where ");
        int primKeySize = primKeys.size();
        for (TableColumn column : primKeys) {
            filter.append(column.getColumnName());
            filter.append("=");
            if (column.getColumnType().equals("VARCHAR")) {
                filter.append("'");
                filter.append(filterValues[indexCounter++]);
                filter.append("'");
            } else {
                filter.append(filterValues[indexCounter++]);
            }
            if (indexCounter != primKeySize) {
                filter.append(" and ");
            }
        }
        return filter.toString();
    }

    public int deleteReferenceDataRow(String query) throws ApplicationException {
        try {
            SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
            return sqlQuery.executeUpdate();
        } catch (Exception exp) {
            logger.error("AdminDaoImpl - deleteReferenceDataRow : Error Deleting Reference Data", exp);
            throw new ApplicationException(exp.getMessage());
        }
    }

    private String logTableData(List<Object[]> results) {
        StringBuilder log = new StringBuilder();
        for (Object[] row : results) {
            log.append("Row:");
            for (Object column : row) {
                log.append(column == null ? "" : column.toString());
                log.append(", ");
            }
            log.append("\n");
        }
        return log.toString();
    }
}
