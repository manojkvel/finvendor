package com.finvendor.server.researchreport.dao.ifc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.server.common.dao.ifc.ICommonDao;

/**
 * ResearchReportDao abstract interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class AbsResearchReportDao implements IResearchReportDao {
	protected static Logger logger = LoggerFactory.getLogger(AbsResearchReportDao.class);
	
	@Autowired
	protected ICommonDao commonDao;
	
	@Autowired
	protected SessionFactory sessionFactory;

	public AbsResearchReportDao() {
	}

	protected void appendFilterWithInClause(StringBuffer sqlSb, String filterCondition, List<String> inValues, boolean inClause) {
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
	
	protected class BrokerRankInfo {
		private String brokerId;
		private String rank;
		private String capName;

		public BrokerRankInfo(String brokerId, String rank, String capName) {
			super();
			this.brokerId = brokerId;
			this.rank = rank;
			this.capName = capName;
		}

		public String getBrokerId() {
			return brokerId;
		}

		public void setBrokerId(String brokerId) {
			this.brokerId = brokerId;
		}

		public String getRank() {
			return rank;
		}

		public void setRank(String rank) {
			this.rank = rank;
		}

		public String getCapName() {
			return capName;
		}

		public void setCapName(String capName) {
			this.capName = capName;
		}

		@Override
		public String toString() {
			return "BrokerRankInfo [brokerId=" + brokerId + ", rank=" + rank + ", capName=" + capName + "]";
		}
	}
	
	protected enum ColumnType {
		DECIMAL, DATE, STRING;
	}
	
	protected class ColumnNameWithType {
		private String columnName;
		private ColumnType columnType;
		public ColumnNameWithType(String columnName, ColumnType columnType) {
			super();
			this.columnName = columnName;
			this.columnType = columnType;
		}
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public ColumnType getColumnType() {
			return columnType;
		}
		public void setColumnType(ColumnType columnType) {
			this.columnType = columnType;
		}
	}
	
	
	@SuppressWarnings("serial")
	protected Map<String,ColumnNameWithType> sortByColumnNameMap=new LinkedHashMap<String,ColumnNameWithType>(){{
		put("company", new ColumnNameWithType("x.companyName", ColumnType.STRING));
		put("style", new ColumnNameWithType("x.style", ColumnType.STRING));
		put("mcap", new ColumnNameWithType("x.mcap", ColumnType.STRING));
		put("sector", new ColumnNameWithType("x.sector", ColumnType.STRING));
		put("broker", new ColumnNameWithType("y.broker", ColumnType.STRING));
		put("brokerRank", new ColumnNameWithType("y.vendorId", ColumnType.STRING));
		put("since", new ColumnNameWithType("y.ly", ColumnType.STRING));
		put("awarded", new ColumnNameWithType("y.award", ColumnType.STRING));
		put("researchedByCfa", new ColumnNameWithType("y.cfa", ColumnType.STRING));
		put("cmp", new ColumnNameWithType("x.cmp", ColumnType.DECIMAL));
		put("priceDate", new ColumnNameWithType("x.prcDt", ColumnType.DATE));
		put("pe", new ColumnNameWithType("x.pe", ColumnType.DECIMAL));
		put("_3YrPatGrowth", new ColumnNameWithType("x.patGrth", ColumnType.DECIMAL));
		put("recommType", new ColumnNameWithType("y.recommType", ColumnType.STRING));
		put("targetPrice", new ColumnNameWithType("y.tgtPrice", ColumnType.DECIMAL));
		put("priceAtRecomm", new ColumnNameWithType("y.prcAtRecomm", ColumnType.DECIMAL));
		put("upside", new ColumnNameWithType("y.upside", ColumnType.DECIMAL));
		put("report", new ColumnNameWithType("y.rptName", ColumnType.STRING));
		put("researchDate", new ColumnNameWithType("y.rsrchDt", ColumnType.DATE));
		put("analystName", new ColumnNameWithType("y.analystName", ColumnType.STRING));
		
	}};

	protected String applyOrderBy(String sortBy, String orderBy) {
		String queryOrderByClause;
		if("asc".equals(orderBy)){
			queryOrderByClause=" ASC, ";
		} else {
			queryOrderByClause=" DESC, ";
		}
		
		ColumnNameWithType columnNameWithType = sortByColumnNameMap.get(sortBy);
		if (columnNameWithType == null) {
			throw new RuntimeException("Invalid sortBy column name!!");
		}
		
		String columnName 		= columnNameWithType.getColumnName();
		ColumnType columnType 	= columnNameWithType.getColumnType();
		
		StringBuilder colNameSb = new StringBuilder(100);
		colNameSb.append(" ORDER BY ");
		
		if (columnType != null) {
			switch (columnType) {
			case STRING:
				colNameSb.append(" ").append(columnName).append(queryOrderByClause);
				break;
			case DECIMAL:
				colNameSb.append(" ").append("CONVERT(").append(columnName).append(", DECIMAL(10,3))").append(queryOrderByClause);
				break;
			case DATE:
				colNameSb.append(" ").append("STR_TO_DATE(").append(columnName).append(", '%m/%d/%Y')").append(queryOrderByClause);
				break;
			}
			colNameSb.deleteCharAt(colNameSb.toString().length() - 1);
		}
		return colNameSb.deleteCharAt(colNameSb.toString().length()-1).toString();
	}
	
	@SuppressWarnings("unchecked")
	protected List<BrokerRankInfo> getBrokerRankData(String orderBy) {
		String orderByClause;
		if("asc".equals(orderBy)){
			orderByClause = "ASC";
		} else {
			orderByClause = "DESC";
		}
		String sqlQuery = "select broker_analyst.broker_id,broker_analyst.broker_rank,market_cap_def.market_cap_name from broker_analyst,market_cap_def where broker_analyst.market_cap_id = market_cap_def.market_cap_id order by broker_id asc, broker_rank " + orderByClause;
		
		SQLQuery query 		= commonDao.getSql(sqlQuery, null);
		List<Object[]> rows = query.list();
		List<BrokerRankInfo> brokerInfoList = new ArrayList<>();
		for (Object[] row : rows) {
			String brokerId = row[0] != null ? row[0].toString() : "";
			String brokerRank = row[1] != null ? row[1].toString() : "";
			String mcapName = row[2] != null ? row[2].toString() : "";
			brokerInfoList.add(new BrokerRankInfo(brokerId, brokerRank, mcapName));
		}
		return brokerInfoList;
	}
}
