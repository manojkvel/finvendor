package com.finvendor.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.AdminDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.ReferenceData;
import com.finvendor.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	@Resource(name="adminDao")
	private AdminDao adminDao;
	
	private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
		
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getReferenceData(ReferenceData refData) {
		return adminDao.getReferenceData(refData);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getReferenceDataRow(ReferenceData refData, 
			String primaryKeyValue) {
		return adminDao.getReferenceDataRow(refData, primaryKeyValue);
	}
	
	@Override
	@Transactional
	public int updateReferenceDataRow(ReferenceData refData, List<String>params, 
			List<Boolean> paramSelected) throws ApplicationException {
		StringBuilder query = new StringBuilder(25);
		StringBuilder sqlQuery = new StringBuilder(25);
		String[] columnNames = refData.getColumnNames().split(",");
		int numberOfColumns = columnNames.length;
		query.append("update ");
		query.append(refData.getTableName());
		query.append(" set ");
		for (int i=1; i<numberOfColumns; i++) {
			String columnName = columnNames[i];
			String paramValue = params.get(i);
			boolean isSelected = paramSelected.get(i);
			if (isSelected) {
				query.append(columnName);
				query.append("=");
				String columnType = refData.getFieldTypeMap().get(columnName).getColumnType();
				if(ReferenceData.COLUMNTYPE_VARCHAR.equals(columnType)) {
					query.append("'");
					query.append(paramValue);
					query.append("'");
				} else {
					query.append(paramValue);
				}				
				query.append(", ");
			}			
		}
		sqlQuery.append(query.substring(0, query.length() - 2));
		sqlQuery.append(" where ");
		sqlQuery.append(columnNames[0]);
		sqlQuery.append("=");
		String columnType = refData.getFieldTypeMap().get(columnNames[0]).getColumnType();
		if(ReferenceData.COLUMNTYPE_VARCHAR.equals(columnType)) {
			sqlQuery.append("'");
			sqlQuery.append(params.get(0));
			sqlQuery.append("'");
		} else {
			sqlQuery.append(params.get(0));
		}				
		logger.debug("AdminServiceImpl : updateReferenceDataRow - sqlQuery : {}", 
				sqlQuery.toString());
		try{
			int rowsUpdated =  adminDao.updateReferenceDataRow(sqlQuery.toString());
			return rowsUpdated;
		}catch (Exception exp) {
			logger.error("AdminServiceImpl : updateReferenceDataRow - Error Updaing Reference Data", exp);
			throw new ApplicationException(exp.getMessage());
		}
	}
	
	@Override
	@Transactional
	public int deleteReferenceDataRow(ReferenceData refData, List<String> primaryKeyName, 
			List<String> primaryKeyValue) throws ApplicationException {
		logger.debug("Entering AdminServiceImpl : deleteReferenceDataRow for {}",
				refData.getTableName());
		StringBuilder sqlQuery = new StringBuilder(25);
		sqlQuery.append("delete from ");
		sqlQuery.append(refData.getTableName());
		sqlQuery.append(" where ");
		int numberOfKeys = primaryKeyName.size();
		for (int i=0; i<numberOfKeys; i++) {
			sqlQuery.append(primaryKeyName.get(i));
			sqlQuery.append("=");
			sqlQuery.append(primaryKeyValue.get(i));			
			if (i != numberOfKeys - 1) {
				sqlQuery.append(" and ");
			}
		}
		logger.info("AdminServiceImpl : deleteReferenceDataRow - sqlQuery : {}", 
				sqlQuery.toString());
		try{
			int rowsDeleted = adminDao.deleteReferenceDataRow(sqlQuery.toString());
			logger.debug("AdminServiceImpl : deleteReferenceDataRow - Deleted {} rows", 
					rowsDeleted);
			logger.debug("Leaving AdminServiceImpl : deleteReferenceDataRow for {}",
					refData.getTableName());
			return rowsDeleted;		
		}catch (Exception exp) {
			logger.error("AdminServiceImpl : deleteReferenceDataRow - Error Deleting Reference Data", exp);
			throw new ApplicationException(exp.getMessage());
		}
	}
	
	@Override
	@Transactional
	public int addReferenceDataRow(ReferenceData refData, List<String>params, 
			List<Boolean> paramSelected) throws ApplicationException {
		StringBuilder query = new StringBuilder(25);
		StringBuilder sqlQuery = new StringBuilder(25);
		String[] columnNames = refData.getColumnNames().split(",");
		int numberOfColumns = columnNames.length;
		query.append("insert into ");
		query.append(refData.getTableName());
		query.append("(");
		for (int i=0; i<numberOfColumns; i++) {
			String columnName = columnNames[i];
			if(!refData.getFieldTypeMap().get(columnName).isAutoIncrement()) {
				query.append(columnName);
				if (i != numberOfColumns -1) {
					query.append(",");
				}
			}
		}
		query.append(") values (");
		for (int i=0; i<numberOfColumns; i++) {
			String columnName = columnNames[i];
			String paramValue = params.get(i);
			boolean isSelected = paramSelected.get(i);
			if (isSelected) {
				String columnType = refData.getFieldTypeMap().get(columnName).getColumnType();
				if(ReferenceData.COLUMNTYPE_VARCHAR.equals(columnType)) {
					if("".equals(paramValue)) {
						if(refData.getFieldTypeMap().get(columnName).getForeignKey()) {
							query.append("NULL");
						}else {
							query.append("'");
							query.append(paramValue);
							query.append("'");
						}
					}else {
						query.append("'");
						query.append(paramValue);
						query.append("'");
					}
				} else {
					if(refData.getFieldTypeMap().get(columnName).getForeignKey()) {
						if("".equals(paramValue)) {
							query.append("NULL");
						}else {
							query.append(paramValue);
						}
					}else {
						query.append(paramValue);
					}
				}				
				query.append(", ");
			}			
		}
		sqlQuery.append(query.substring(0, query.length() - 2));
		sqlQuery.append(")");
		logger.info("AdminServiceImpl : addReferenceDataRow - sqlQuery : {}", 
				sqlQuery.toString());
		try{
			int rowsUpdated =  adminDao.updateReferenceDataRow(sqlQuery.toString());
			return rowsUpdated;
		}catch (Exception exp) {
			logger.error("AdminServiceImpl : addReferenceDataRow - Error Adding Reference Data", exp);
			throw new ApplicationException(exp.getMessage());
		}
	}
}
