package com.finvendor.model;

import java.util.List;
import java.util.Map;

public class ReferenceData {
	
	public static final String COLUMNTYPE_VARCHAR = "VARCHAR";
	public static final String COLUMNTYPE_INTEGER = "INT";
	public static final String PRIMARY_KEY = "PK";
	public static final String FOREIGN_KEY = "FK";
	
	private String tableName;
	private String columnNames;
	private List<TableColumn> columnList;
	private Map<String, TableColumn> fieldTypeMap;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}
	public List<TableColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<TableColumn> columnList) {
		this.columnList = columnList;
	}
	public Map<String, TableColumn> getFieldTypeMap() {
		return fieldTypeMap;
	}
	public void setFieldTypeMap(Map<String, TableColumn> fieldTypeMap) {
		this.fieldTypeMap = fieldTypeMap;
	}
}
