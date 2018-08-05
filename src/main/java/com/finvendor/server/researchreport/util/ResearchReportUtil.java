package com.finvendor.server.researchreport.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.finvendor.common.util.Pair;
import com.finvendor.model.VendorResearchReportsOffering;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.impl.EquityResearchResult;

public class ResearchReportUtil {
	public static String mainQuery = "select x.comapanyId,x.companyName,x.isinCode, x.style,x.mcap,x.sector,x.cmp,x.prcDt,x.pe,x.patGrth,y.companyId,y.prdId,y.broker,y.recommType,y.tgtPrice,y.prcAtRecomm,y.upside,y.rptName,y.rsrchDt,y.award,y.cfa,y.analystName,y.analystType,y.vendorId,y.ly,y.userName,y.rptDesc from(SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode,rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp,stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls,rsch_area_stock_class,market_cap_def,comp_mkt_cap_type,research_sub_area,stock_current_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId,ven_rsrch_rpt_offering.product_id prdId, vendor.company broker ,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType,ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm,((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', -1) rptName,ven_rsrch_rpt_dtls.rep_date rsrchDt,ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa, ven_rsrch_rpt_analyst_prof.analyst_name analystName,vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering,ven_rsrch_rpt_dtls,ven_rsrch_rpt_analyst_prof,vendor,broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y on x.comapanyId = y.companyId";
	public static String brokerRankQuery= "select broker_analyst.broker_id,broker_analyst.broker_rank,market_cap_def.market_cap_name from broker_analyst,market_cap_def where broker_analyst.market_cap_id = market_cap_def.market_cap_id order by broker_id asc, broker_rank ";
	
	public static void appendFilterWithInClause(StringBuffer sqlSb, String filterCondition, List<String> inValues,
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

	public static void appendFilterWithBetweenClause(StringBuffer sqlSb, String filterCondition, String firstValue,
			String secondValue) {
		StringBuffer betweenClauseSb = new StringBuffer(100);
		betweenClauseSb.append(" BETWEEN ").append(firstValue).append(" AND ").append(secondValue);
		sqlSb.append(filterCondition).append(betweenClauseSb);
	}

	public static void appendFilterLessThanClause(StringBuffer sqlSb, String filterCondition, String valueString) {
		StringBuffer lessThanSb = new StringBuffer(100);
		lessThanSb.append(" < ").append(valueString);
		sqlSb.append(filterCondition).append(lessThanSb);
	}

	public static void appendFilterGreaterThanClause(StringBuffer sqlSb, String filterCondition, String valueString) {
		StringBuffer greaterThanSb = new StringBuffer(100);
		greaterThanSb.append(" > ").append(valueString);
		sqlSb.append(filterCondition).append(greaterThanSb);
	}

	public static long convertStringToTimestamp(String str_date) throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = (Date) formatter.parse(str_date);
		return date.getTime();
	}

	public static class BrokerRankInfo {
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

	public static enum ColumnType {
		DECIMAL, DATE, STRING;
	}

	public static class ColumnNameWithType {
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
	public static Map<String, ColumnNameWithType> sortByColumnNameMap = new LinkedHashMap<String, ColumnNameWithType>() {
		{
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

		}
	};

	public static String applyOrderBy(String sortBy, String orderBy) {
		String queryOrderByClause;
		if ("asc".equals(orderBy)) {
			queryOrderByClause = " ASC, ";
		} else {
			queryOrderByClause = " DESC, ";
		}

		ColumnNameWithType columnNameWithType = sortByColumnNameMap.get(sortBy);
		if (columnNameWithType == null) {
			throw new RuntimeException("Invalid sortBy column name!!");
		}

		String columnName = columnNameWithType.getColumnName();
		ColumnType columnType = columnNameWithType.getColumnType();

		StringBuilder colNameSb = new StringBuilder(100);
		colNameSb.append(" ORDER BY ");

		if (columnType != null) {
			switch (columnType) {
			case STRING:
				colNameSb.append(" ").append(columnName).append(queryOrderByClause);
				break;
			case DECIMAL:
				colNameSb.append(" ").append("CONVERT(").append(columnName).append(", DECIMAL(10,3))")
						.append(queryOrderByClause);
				break;
			case DATE:
				colNameSb.append(" ").append("STR_TO_DATE(").append(columnName).append(", '%m/%d/%Y')")
						.append(queryOrderByClause);
				break;
			}
			colNameSb.deleteCharAt(colNameSb.toString().length() - 1);
		}
		return colNameSb.deleteCharAt(colNameSb.toString().length() - 1).toString();
	}

	@SuppressWarnings("unchecked")
	public static List<BrokerRankInfo> getBrokerInfoList(SQLQuery query) {
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

	public static String getFilteredQueryPart(EquityResearchFilter equityFilter) {
		StringBuffer conditionSqlSb = new StringBuffer();
		List<String> conditionQueryList = new ArrayList<>();

		// MCap filter applied
		if (equityFilter.getBrokerRank() == null) {
			if (equityFilter.getMcap() != null) {
				conditionQueryList.add(getConditionQuery("x.mcap", getActualMCapList(equityFilter.getMcap())));
			}
		}

		// #Style filter applied
		if (equityFilter.getStyle() != null) {
			conditionQueryList.add(getConditionQuery("x.style", equityFilter.getStyle()));
		}

		// AnalystType filter applied
		if (equityFilter.getAnalystType() != null) {
			conditionQueryList.add(getConditionQuery("y.analystType", equityFilter.getAnalystType()));
		}

		// ResearchBroker filter applied
		if (equityFilter.getResearchedBroker() != null) {
			conditionQueryList.add(getConditionQuery("y.broker", equityFilter.getResearchedBroker()));
		}

		// RecommType filter applied
		if (equityFilter.getRecommType() != null) {
			conditionQueryList.add(getConditionQuery("y.recommType", equityFilter.getRecommType()));
		}

		// Others filter applied "others":["Award Winning Analyst","Research
		// Reports by CFA"]
		if (equityFilter.getOthers() != null) {
			List<String> others = equityFilter.getOthers();
			List<String> otherOptionList = new ArrayList<>();

			if (others.size() == 1) {
				String otherOption = others.get(0);
				if (otherOption.contains("Award")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" y.award in (").append(getInClauseItems(otherOptionList)).append(")");
				} else if (otherOption.contains("CFA")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" y.cfa in (").append(getInClauseItems(otherOptionList)).append(")");
				}
			} else if (others.size() == 2) {
				String otherOption1 = others.get(0);
				String otherOption2 = others.get(1);

				// expect following order for others option
				if (otherOption1 != null && otherOption1.contains("Award")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" y.award in (").append(getInClauseItems(otherOptionList)).append(")");
				}

				if (otherOption2 != null && otherOption2.contains("CFA")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" and y.cfa in (").append(getInClauseItems(otherOptionList)).append(")");
				}
			} else {
				// throw Invalid Filter Exception TODO
			}
			conditionQueryList.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		// Upside filter applied
		if (equityFilter.getUpside() != null && equityFilter.getUpside().size() > 0) {
			List<String> upsideValueList = equityFilter.getUpside();
			// Upside Formula
			String filterCondition = "y.upside";
			List<String> upsideConditions = new ArrayList<>();
			StringBuffer upsideStrSb = new StringBuffer();

			for (String upsideValue : upsideValueList) {
				if ("<0%".equals(upsideValue)) {
					upsideStrSb.append(filterCondition).append(" < ").append(" 0 ");
					upsideConditions.add(upsideStrSb.toString());
					upsideStrSb.setLength(0);
				}

				if ("0-20%".equals(upsideValue)) {
					upsideStrSb.append(filterCondition).append(" BETWEEN ").append("0").append(" AND ").append("20");
					upsideConditions.add(upsideStrSb.toString());
					upsideStrSb.setLength(0);
				}

				if ("20-50%".equals(upsideValue)) {
					upsideStrSb.append(filterCondition).append(" BETWEEN ").append("20").append(" AND ").append("50");
					upsideConditions.add(upsideStrSb.toString());
					upsideStrSb.setLength(0);
				}

				if ("50-100%".equals(upsideValue)) {
					upsideStrSb.append(filterCondition).append(" BETWEEN ").append("50").append(" AND ").append("100");
					upsideConditions.add(upsideStrSb.toString());
					upsideStrSb.setLength(0);
				}

				if (">100%".equals(upsideValue)) {
					upsideStrSb.append(filterCondition).append(" > ").append("100");
					upsideConditions.add(upsideStrSb.toString());
					upsideStrSb.setLength(0);
				}
			}

			for (int i = 0; i < upsideConditions.size(); i++) {
				if (i >= 1 && i < upsideConditions.size()) {
					conditionSqlSb.append(" OR " + upsideConditions.get(i));
				} else {
					conditionSqlSb.append(upsideConditions.get(i));
				}
			}
			conditionQueryList.add(conditionSqlSb.toString());
		}
		return mergeConditionQuery(conditionQueryList);
	}

	public static String getConditionQuery(String columnName, List<String> valueList) {
		String inClauseItems = getInClauseItems(valueList);
		StringBuffer conditionQuerySb = new StringBuffer(100);
		String conditionQuery = conditionQuerySb.append(columnName).append(" in ").append("(").append(inClauseItems)
				.append(")").toString();
		return conditionQuery;
	}

	public static String mergeConditionQuery(List<String> conditionQueryList) {
		StringBuffer conditionSqlSb = new StringBuffer();
		if (conditionQueryList.size() != 0) {
			conditionSqlSb.append(" where ");
			if (conditionQueryList.size() == 1) {
				conditionSqlSb.append(conditionQueryList.get(0));
			} else {
				int i = 0;
				for (; i < conditionQueryList.size() - 1; i++) {
					conditionSqlSb.append(conditionQueryList.get(i)).append(" AND ");
				}
				conditionSqlSb.append(conditionQueryList.get(i));
			}
		}
		return conditionSqlSb.toString();
	}

	public static List<String> getActualMCapList(List<String> mcapList) {
		List<String> actualMcapList = new ArrayList<>();
		for (String mcap : mcapList) {
			if (mcap.contains("Large Cap")) {
				actualMcapList.add("Large Cap");
			}

			if (mcap.contains("Mid Cap")) {
				actualMcapList.add("Mid Cap");
			}

			if (mcap.contains("Small Cap")) {
				actualMcapList.add("Small Cap");
			}

			if (mcap.contains("Micro Cap")) {
				actualMcapList.add("Micro Cap");
			}

			if (mcap.contains("Nano Cap")) {
				actualMcapList.add("Nano Cap");
			}
		}
		return actualMcapList;
	}

	public static String applyFilter(String mainQuery, String filteredQuery) {
		return new StringBuffer(mainQuery).append(filteredQuery).toString();
	}

	public static String getInClauseItems(List<String> options) {
		StringBuilder getInClauseItemsSb = new StringBuilder(50);

		for (String option : options) {
			getInClauseItemsSb.append("'").append(option).append("'").append(",");
		}
		getInClauseItemsSb.deleteCharAt(getInClauseItemsSb.toString().length() - 1);
		return getInClauseItemsSb.toString();
	}

	/**
	 * Since is minimum launched year date of vendor
	 */
	public static Pair<Integer, String> calculateSinceAndYrOfIncorp(Map<String, Integer> vendorIdMap, String vendorId) {
		Pair<Integer, String> sinceWithYrOfInCorpPair = new Pair<>();
		Integer ly = vendorIdMap.get(vendorId);
		Integer since = ly;

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		// formula for Borker year of incorp=current_year - since
		int yearOfIncorp = currentYear - since;
		sinceWithYrOfInCorpPair.setElement1(since);
		sinceWithYrOfInCorpPair.setElement2(String.valueOf(yearOfIncorp));
		return sinceWithYrOfInCorpPair;
	}

	/**
	 * Pagination Algorithm ~~~~~~~~~~~~~~~~~~~~~ IF maxRecordCountPerPage=2
	 * THEN IF pageNumber=1 THEN offset=(pageNumber - 1) * maxRecordCountPerPage
	 * //0
	 *
	 * IF pageNumber=2 THEN offset=(pageNumber - 1) * maxRecordCountPerPage //2
	 * 
	 * IF pageNumber=3 THEN offset=(pageNumber - 1) * maxRecordCountPerPage //4
	 * ENDIF ------- ------- ENDIF
	 **/
	public static String applyPagination(String pageNumber, String perPageMaxRecords) {
		StringBuffer baseSqlSb = new StringBuffer();
		int pageNumberAsInt = Integer.parseInt(pageNumber);
		int maxRecordCountPerPage_As_Limit = Integer.parseInt(perPageMaxRecords);
		int offset = (pageNumberAsInt - 1) * maxRecordCountPerPage_As_Limit;
		baseSqlSb.append(" limit " + maxRecordCountPerPage_As_Limit + " offset " + offset);
		return baseSqlSb.toString();
	}

	public static String convertCamelCase(String str) {
		String replace = StringUtils.replace(str, " ", "");
		replace = replace.substring(0, 1).toLowerCase() + replace.substring(1);
		return replace;
	}

	public static Map<String, String> getBrokerRank(List<ResearchReportUtil.BrokerRankInfo> brokerRankData,
			String brokerId, EquityResearchFilter equityFilter) {
		Map<String, String> brokerRankAndMcapMap = new LinkedHashMap<>();
		for (ResearchReportUtil.BrokerRankInfo brokerRankInfo : brokerRankData) {
			if (brokerRankInfo.getBrokerId().equals(brokerId)) {
				String rank = brokerRankInfo.getRank();
				String capName = convertCamelCase(brokerRankInfo.getCapName());
				brokerRankAndMcapMap.put(capName, rank.substring(0, 1));
			}
		}

		List<String> mcapActualList = new ArrayList<>();
		if (equityFilter.getMcap() != null) {
			List<String> mcapList = equityFilter.getMcap();
			for (String mcap : mcapList) {
				if (mcap.contains("Large Cap")) {
					mcapActualList.add("largeCap");
				}

				if (mcap.contains("Mid Cap")) {
					mcapActualList.add("midCap");
				}

				if (mcap.contains("Small Cap")) {
					mcapActualList.add("smallCap");
				}

				if (mcap.contains("Micro Cap")) {
					mcapActualList.add("microCap");
				}

				if (mcap.contains("Nano Cap")) {
					mcapActualList.add("nanoCap");
				}
			}
			if (equityFilter.getBrokerRank() != null) {
				List<String> brokerRankList = equityFilter.getBrokerRank();

				// filter brokerRankAndMcapMap
				Map<String, String> filteredbrokerRankAndMcapMap = new LinkedHashMap<>();
				for (Map.Entry<String, String> entry : brokerRankAndMcapMap.entrySet()) {
					String mcapName = entry.getKey();
					String rank = entry.getValue();
					if (mcapActualList.indexOf(mcapName) != -1) {
						String string = brokerRankList.toString();
						if (string.contains(rank)) {
							filteredbrokerRankAndMcapMap.put(entry.getKey(), entry.getValue());
						}
					}
				}
				return filteredbrokerRankAndMcapMap;
			}

		}
		return brokerRankAndMcapMap;
	}

	public static Map<String, EquityResearchResult> applyFilterForYearOfInCorp(EquityResearchFilter equityFilter,
			Map<String, EquityResearchResult> resultMap) {
		Map<String, EquityResearchResult> filteredYrOfInCorpResultMap = new LinkedHashMap<>();
		List<String> brokerYearOfInCorpValueList = equityFilter.getBrokerYrOfInCorp();

		List<String> removeProductIdList = new ArrayList<>();
		if (brokerYearOfInCorpValueList.contains("<= 3 Yrs")) {
			for (Entry<String, EquityResearchResult> entry : resultMap.entrySet()) {
				int yrOfInCorp = Integer.parseInt(entry.getValue().getYrOfInCorp());
				if ((yrOfInCorp <= 3)) {
					filteredYrOfInCorpResultMap.put(entry.getKey(), entry.getValue());
					removeProductIdList.add(entry.getKey());
				}
			}

			// release memory
			for (String prodKey : removeProductIdList) {
				resultMap.remove(prodKey);
			}
			removeProductIdList.clear();
		}

		if (brokerYearOfInCorpValueList.contains("3 - 5 Yrs")) {
			for (Entry<String, EquityResearchResult> entry : resultMap.entrySet()) {
				int yrOfInCorp = Integer.parseInt(entry.getValue().getYrOfInCorp());
				if (yrOfInCorp >= 3 && yrOfInCorp <= 5) {
					filteredYrOfInCorpResultMap.put(entry.getKey(), entry.getValue());
					removeProductIdList.add(entry.getKey());
				}
			}

			// release memory
			for (String prodKey : removeProductIdList) {
				resultMap.remove(prodKey);
			}
			removeProductIdList.clear();
		}

		if (brokerYearOfInCorpValueList.contains("> 10 Yrs")) {
			for (Entry<String, EquityResearchResult> entry : resultMap.entrySet()) {
				int yrOfInCorp = Integer.parseInt(entry.getValue().getYrOfInCorp());
				if (yrOfInCorp > 10) {
					filteredYrOfInCorpResultMap.put(entry.getKey(), entry.getValue());
					removeProductIdList.add(entry.getKey());
				}
			}

			// release memory
			for (String prodKey : removeProductIdList) {
				resultMap.remove(prodKey);
			}
			removeProductIdList.clear();
		}

		if (filteredYrOfInCorpResultMap != null) {
			resultMap.clear(); // release memory
		}
		return filteredYrOfInCorpResultMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Integer> getVendorIdMap(SessionFactory sessionFactory, String orderBy) {
		Map<String, Integer> vendorIdMap;
		if ("asc".equals(orderBy)) {
			vendorIdMap = new TreeMap<>();
		} else {
			vendorIdMap = new TreeMap<>(Collections.reverseOrder());
		}
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VendorResearchReportsOffering.class);
		List<VendorResearchReportsOffering> list = criteria.list();
		if (list != null && list.size() > 0) {
			for (int index = 0, listSize = list.size(); index < listSize; index++) {
				VendorResearchReportsOffering vrro = list.get(index);
				String vId = vrro.getVendor().getId();
				Integer exitsingLaunchedYrListFromMap = vendorIdMap.get(vId);
				Integer lYr = null;
				int newLy = Integer.parseInt(vrro.getLaunchedYear());
				if (exitsingLaunchedYrListFromMap == null) {
					lYr = new Integer(newLy);
					vendorIdMap.put(vId, lYr);
				} else {
					lYr = vendorIdMap.get(vId);
					if (newLy < lYr) {
						lYr = newLy;
						vendorIdMap.put(vId, lYr);
					}
				}
			}
		}
		return vendorIdMap;
	}

	public static Map<String, Integer> prepareVendorSinceData(SessionFactory sessionFactory, String sortBy,
			String orderBy) {
		Map<String, Integer> sortedVendorIdWithLyMap = new LinkedHashMap<>();
		Map<String, Integer> vendorIdWithLyMap = getVendorIdMap(sessionFactory, orderBy);
		if ("since".equals(sortBy)) {
			List<Entry<String, Integer>> list = new ArrayList<>(vendorIdWithLyMap.entrySet());

			if ("asc".equalsIgnoreCase(orderBy)) {
				Collections.sort(list, new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
						return obj1.getValue().compareTo(obj2.getValue());
					}
				});
			} else {
				Collections.sort(list, new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
						return obj2.getValue().compareTo(obj1.getValue());
					}

				});
			}

			for (Entry<String, Integer> item : list) {
				sortedVendorIdWithLyMap.put(item.getKey(), item.getValue());
			}
		}
		if (sortedVendorIdWithLyMap.size() == 0) {
			return vendorIdWithLyMap;
		}
		return sortedVendorIdWithLyMap;
	}

	public static List<ResearchReportUtil.BrokerRankInfo> getBrokerRankData(ICommonDao commonDao, String query,
			String orderBy) {
		String orderByClause = "asc".equals(orderBy) ? "ASC" : "DESC";
		SQLQuery sqlQuery = commonDao.getNativeQuery(query + orderByClause, null);
		return ResearchReportUtil.getBrokerInfoList(sqlQuery);
	}
}
