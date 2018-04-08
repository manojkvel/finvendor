package com.finvendor.server.researchreport.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.model.VendorResearchReportsOffering;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Repository
public class EquityResearchDao extends AbsResearchReportDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ICommonDao commonDao;
	
	private String mainQuery="select x.comapanyId,x.companyName,x.style,x.mcap,x.sector,x.cmp,x.prcDt,x.pe,x.patGrth,y.companyId,y.prdId,y.broker,y.recommType,y.tgtPrice,y.prcAtRecomm,y.upside,y.rptName,y.rsrchDt,y.award,y.cfa,y.analystName,y.analystType,y.vendorId,y.ly,y.userName,y.rptDesc from(SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_historial_prices.close_price cmp,stock_historial_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls,rsch_area_stock_class,market_cap_def,comp_mkt_cap_type,research_sub_area,stock_historial_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId,ven_rsrch_rpt_offering.product_id prdId, vendor.company broker ,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType,ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm,((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', -1) rptName,ven_rsrch_rpt_dtls.rep_date rsrchDt,ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa, ven_rsrch_rpt_analyst_prof.analyst_name analystName,vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering,ven_rsrch_rpt_dtls,ven_rsrch_rpt_analyst_prof,vendor,broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y on x.comapanyId = y.companyId";
	
	enum ColumnType {
		DECIMAL, DATE, STRING;
	}

	static class ColumnNameWithType {
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
	private static final Map<String,ColumnNameWithType> sortByColumnNameMap=new LinkedHashMap<String,ColumnNameWithType>(){{
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
	
	static class BrokerRankInfo {
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
	
	private String getOrderByColumnNamesForVendor(String sortBy, String orderBy) {
		String queryOrderByClause;
		if("asc".equals(orderBy)){
			queryOrderByClause=" ASC, ";
		}else{
			queryOrderByClause=" DESC, ";
		}
		
		ColumnNameWithType columnNameWithType = sortByColumnNameMap.get(sortBy);
		if (columnNameWithType == null) {
			throw new RuntimeException("Invalid sortBy column name!!!");
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
	
	/**
	 * Get Record stats
	 */
	@SuppressWarnings("unchecked")
	public String getRecordStatistics(ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		
		String queryWithAppliedFilter = applyFilter(mainQuery, equityFilter);
		SQLQuery query = commonDao.getSql(queryWithAppliedFilter,  new String[]{equityFilter.getGeo()});
		List<Object[]> rows = query.list();
		int totalRecords=rows.size();
		
		//Calculate Last page number
		int lastPageNumber = 0;
		int maxRecordCountPerPage = Integer.parseInt(perPageMaxRecords);
		if (maxRecordCountPerPage <= totalRecords) {
			int remainder = totalRecords % maxRecordCountPerPage;
			if (remainder == 0) {
				lastPageNumber = totalRecords / maxRecordCountPerPage;
			} else {
				lastPageNumber = (totalRecords / maxRecordCountPerPage) + 1;
			}
		} else {
			lastPageNumber=1;
		}
		
		//Prepare Json result
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		paramsMap.put("firstPageNumber", 1);
		paramsMap.put("lastPageNumber", lastPageNumber);
		paramsMap.put("totalRecords", totalRecords);
		try {
			return JsonUtil.createJsonFromObject(paramsMap);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Map<String, EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		 return findEquityResult(filter,pageNumber,perPageMaxRecords, sortBy, orderBy);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, EquityResearchResult> findEquityResult(ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		Map<String, EquityResearchResult> resultMap = new LinkedHashMap<>();
		Map<String, EquityResearchResult> filteredResultMap = null;
		try {
			
			//style sorting
			Map<String, Integer> vendorIdWithLyMap = vendorIdWithLyMap(orderBy);
			if ("since".equals(sortBy)) {
				List<Entry<String, Integer>> list=new ArrayList<>(vendorIdWithLyMap.entrySet());
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
				Map<String,Integer> newMap=new LinkedHashMap<>();
				for(Entry<String, Integer> item:list){
					newMap.put(item.getKey(),item.getValue());
				}
			}
			
			//brokerRank Sorting
			List<BrokerRankInfo> brokerRankData = getBrokerRankData(orderBy);
			
			String queryWithAppliedFilter = applyFilter(mainQuery, equityFilter);
			String applyOrderBy = getOrderByColumnNamesForVendor(sortBy, orderBy);
			String applyPagination = applyPagination(pageNumber, perPageMaxRecords);
			String mainQuery = new StringBuilder(500).append(queryWithAppliedFilter).append(applyOrderBy).append(applyPagination).toString();
			
			SQLQuery query = commonDao.getSql(mainQuery, new String[]{equityFilter.getGeo()});
			List<Object[]> rows = query.list();			
			for (Object[] row : rows) {
				EquityResearchResult equityResult = new EquityResearchResult();
				equityResult.setCompanyId(row[0] != null ? row[0].toString() : "");
				equityResult.setCompany(row[1] != null ? row[1].toString() : "");
				equityResult.setStyle(row[2] != null ? row[2].toString() : "");
				equityResult.setMcap(row[3] != null ? row[3].toString() : "");
				equityResult.setSector(row[4] != null ? row[4].toString() : "");
				equityResult.setCmp(row[5] != null ? row[5].toString() : "");
				equityResult.setPriceDate(row[6] != null ? row[6].toString() : "");
				equityResult.setPe(row[7] != null ? row[7].toString() : "");
				equityResult.set_3YrPatGrowth(row[8] != null ? row[8].toString() : "");

				String productId = row[10] != null ? row[10].toString() : "";
				equityResult.setProductId(productId);

				equityResult.setBroker(row[11] != null ? row[11].toString() : "");
				equityResult.setRecommType(row[12] != null ? row[12].toString() : "");

				equityResult.setTargetPrice(row[13] != null ? row[13].toString() : "");
				equityResult.setPriceAtRecomm(row[14] != null ? row[14].toString() : "");
				equityResult.setUpside(row[15] != null ? row[15].toString() : "");

				String reportName = row[16] != null ? row[16].toString() : "";
				reportName = reportName.substring(reportName.lastIndexOf("/") + 1);
				equityResult.setReport(reportName);

				String researchDate = row[17] != null ? row[17].toString() : "";
				long researchDateAsTimeStamp = convertStringToTimestamp(researchDate);
				equityResult.setResearchDate(String.valueOf(researchDateAsTimeStamp));

				String awarded;
				if (row[18] != null) {
					if (row[18].toString().isEmpty()) {
						awarded = "N";
					} else {
						awarded = row[18].toString();
					}
				} else {
					awarded = "NA";
				}
				equityResult.setAwarded(awarded);

				String researchedByCfa = "";
				if (row[19] != null) {
					if (row[19].toString().isEmpty()) {
						researchedByCfa = "N";
					} else {
						researchedByCfa = row[19].toString();
					}
				} else {
					researchedByCfa = "NA";
				}
				equityResult.setResearchedByCfa(researchedByCfa);

				equityResult.setAnalystName(row[20] != null ? row[20].toString() : "");
				equityResult.setAnalystType(row[21] != null ? row[21].toString() : "");

				// Since
				String vendorId = row[22] != null ? row[22].toString() : "";
				
				Pair<Integer, String> calculatedSince = calculateSinceAndYrOfIncorp(vendorIdWithLyMap, vendorId);
				Integer since = calculatedSince.getElement1();
				equityResult.setSince(String.valueOf(since));

				// Year Of InCorporation
				String yrOfInCorp = calculatedSince.getElement2();
				equityResult.setYrOfInCorp(yrOfInCorp);

				equityResult.setVendorName(row[24] != null ? row[24].toString() : "");
				equityResult.setReportDesc(row[25] != null ? row[25].toString() : "");

				// Broker Rank
				Map<String,String> brokerRanks = getBrokerRank(brokerRankData, vendorId, equityFilter);
				equityResult.setBrokerRank(brokerRanks);

				// Set Current Page number
				equityResult.setPageNumber(pageNumber);
				resultMap.put(productId, equityResult);
			}
			
			// #BrokerYear Of Incorporation filer applied
			if (equityFilter.getBrokerYrOfInCorp() != null) {
				filteredResultMap = new LinkedHashMap<>();
				List<String> brokerYearOfInCorpValueList = equityFilter.getBrokerYrOfInCorp();

				List<String> removeProductIdList = new ArrayList<>();
				if (brokerYearOfInCorpValueList.contains("<= 3 Yrs")) {
					for (Entry<String, EquityResearchResult> entry : resultMap.entrySet()) {
						int yrOfInCorp = Integer.parseInt(entry.getValue().getYrOfInCorp());
						if ((yrOfInCorp <= 3)) {
							filteredResultMap.put(entry.getKey(), entry.getValue());
							removeProductIdList.add(entry.getKey());
						}
					}
					
					//release memory
					for (String prodKey : removeProductIdList) {
						resultMap.remove(prodKey);
					}
					removeProductIdList.clear();
				}

				if (brokerYearOfInCorpValueList.contains("3 - 5 Yrs")) {
					for (Entry<String, EquityResearchResult> entry : resultMap.entrySet()) {
						int yrOfInCorp = Integer.parseInt(entry.getValue().getYrOfInCorp());
						if (yrOfInCorp >= 3 && yrOfInCorp <= 5) {
							filteredResultMap.put(entry.getKey(), entry.getValue());
							removeProductIdList.add(entry.getKey());
						}
					}
					
					//release memory
					for (String prodKey : removeProductIdList) {
						resultMap.remove(prodKey);
					}
					removeProductIdList.clear();
				}

				if (brokerYearOfInCorpValueList.contains("> 10 Yrs")) {
					for (Entry<String, EquityResearchResult> entry : resultMap.entrySet()) {
						int yrOfInCorp = Integer.parseInt(entry.getValue().getYrOfInCorp());
						if (yrOfInCorp > 10) {
							filteredResultMap.put(entry.getKey(), entry.getValue());
							removeProductIdList.add(entry.getKey());
						}
					}

					//release memory
					for (String prodKey : removeProductIdList) {
						resultMap.remove(prodKey);
					}
					removeProductIdList.clear();
				}
			}
			
			if (filteredResultMap != null) {
				//release memory
				resultMap.clear();
				return filteredResultMap;
			} else {
				return resultMap;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<BrokerRankInfo> getBrokerRankData(String orderBy) {
		SQLQuery query;
		List<Object[]> rows;
		List<BrokerRankInfo> brokerInfoList = new ArrayList<>();
		String orderByClause;
		if("asc".equals(orderBy)){
			orderByClause = "ASC";
		}else{
			orderByClause = "DESC";
		}
		String sqlQuery = "select broker_analyst.broker_id,broker_analyst.broker_rank,market_cap_def.market_cap_name from broker_analyst,market_cap_def where broker_analyst.market_cap_id = market_cap_def.market_cap_id order by broker_id asc, broker_rank " + orderByClause;
		query = commonDao.getSql(sqlQuery, null);
		rows = query.list();
		for (Object[] row : rows) {
			String brokerId = row[0] != null ? row[0].toString() : "";
			String brokerRank = row[1] != null ? row[1].toString() : "";
			String mcapName = row[2] != null ? row[2].toString() : "";
			brokerInfoList.add(new BrokerRankInfo(brokerId, brokerRank, mcapName));
		}
		return brokerInfoList;
	}
	
	private String applyFilter(String baseSql, EquityResearchFilter equityFilter) {
		StringBuffer baseSqlSb = new StringBuffer(baseSql);
		StringBuffer conditionSqlSb = new StringBuffer();
		List<String> conditionQuery=new ArrayList<>();
		if (equityFilter.getBrokerRank() == null) {
			// MCap filter applied
			if (equityFilter.getMcap() != null) {
				List<String> mcapList = equityFilter.getMcap();
				List<String> mcapActualList = new ArrayList<>();
				for (String mcap : mcapList) {
					if (mcap.contains("Large Cap")) {
						mcapActualList.add("Large Cap");
					}

					if (mcap.contains("Mid Cap")) {
						mcapActualList.add("Mid Cap");
					}

					if (mcap.contains("Small Cap")) {
						mcapActualList.add("Small Cap");
					}

					if (mcap.contains("Micro Cap")) {
						mcapActualList.add("Micro Cap");
					}

					if (mcap.contains("Nano Cap")) {
						mcapActualList.add("Nano Cap");
					}
				}
				conditionSqlSb.append("x.mcap in (").append(getItems(mcapActualList)).append(")");
				conditionQuery.add(conditionSqlSb.toString());
				conditionSqlSb.setLength(0);
			}
		}

		// #Style filter applied
		if (equityFilter.getStyle() != null) {
			conditionSqlSb.append(" x.style in (").append(getItems(equityFilter.getStyle())).append(")");
			conditionQuery.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}
		
		//AnalystType filter applied
		if (equityFilter.getAnalystType() != null) {
			conditionSqlSb.append(" y.analystType in (").append(getItems(equityFilter.getAnalystType())).append(")");
			conditionQuery.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		//ResearchBroker filter applied
		if (equityFilter.getResearchedBroker() != null) {
			conditionSqlSb.append(" y.broker in (").append(getItems(equityFilter.getResearchedBroker())).append(")");
			conditionQuery.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		//RecommType filter applied
		if (equityFilter.getRecommType() != null) {
			conditionSqlSb.append(" y.recommType in (").append(getItems(equityFilter.getRecommType())).append(")");
			conditionQuery.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		//Others filter applied
		if (equityFilter.getOthers() != null) {
			List<String> others = equityFilter.getOthers();
			List<String> otherOptionList = new ArrayList<>();

			if (others.size() == 1) {
				String otherOption = others.get(0);
				if (otherOption.contains("Award")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" y.award in (").append(getItems(otherOptionList)).append(")");
				} else if (otherOption.contains("CFA")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" y.cfa in (").append(getItems(otherOptionList)).append(")");
				}
			} else if (others.size() == 2) {
				String otherOption1 = others.get(0);
				String otherOption2 = others.get(1);

				// expect following order for others option
				if (otherOption1 != null && otherOption1.contains("Award")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" y.award in (").append(getItems(otherOptionList)).append(")");
				}

				if (otherOption2 != null && otherOption2.contains("CFA")) {
					otherOptionList.add("Y");
					conditionSqlSb.append(" and y.cfa in (").append(getItems(otherOptionList)).append(")");
				}
			} else {
				// throw Invalid Filter Exception TODO
			}
			conditionQuery.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		//Upside filter applied
		if (equityFilter.getUpside() != null && equityFilter.getUpside().size() > 0) {
			List<String> upsideValueList = equityFilter.getUpside();
			//Upside Formula
			String filterCondition = "y.upside";
			List<String> upsideConditions=new ArrayList<>();
			StringBuffer upsideStrSb=new StringBuffer();

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
			conditionQuery.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}
		if (conditionQuery.size() != 0) {
			conditionSqlSb.append(" where ");
			if (conditionQuery.size() == 1) {
				conditionSqlSb.append(conditionQuery.get(0));
			} else {
				int i = 0;
				for (; i < conditionQuery.size() - 1; i++) {
					conditionSqlSb.append(conditionQuery.get(i)).append(" AND ");
				}
				conditionSqlSb.append(conditionQuery.get(i));

			}
			baseSqlSb.append(conditionSqlSb);
		}
		return baseSqlSb.toString();
	}

	private String getItems(List<String> options){
		StringBuilder getInClauseItemsSb=new StringBuilder(50);
		
		for(String option:options){
			getInClauseItemsSb.append("'").append(option).append("'").append(",");
		}
		getInClauseItemsSb.deleteCharAt(getInClauseItemsSb.toString().length()-1);
		return getInClauseItemsSb.toString();
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~										O T H E R - C O D E																~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@SuppressWarnings("unchecked")
	private Map<String, Integer> vendorIdWithLyMap(String orderBy) {
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
						lYr=newLy;
						vendorIdMap.put(vId, lYr);
					}
				}
			}
		}
		return vendorIdMap;
	}
	
	private Map<String, Integer> sortLyByVendorId(Map<String, Integer> vendorIdWithLyMap, String order) {
		List<Entry<String, Integer>> list=new ArrayList<>(vendorIdWithLyMap.entrySet());
		
		
			if ("asc".equals(order)) {
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
			Map<String,Integer> newMap=new LinkedHashMap<>();
			for(Entry<String, Integer> item:list){
				newMap.put(item.getKey(),item.getValue());
			}
			return newMap;
	}
	
	/**
	 * Since is minimum launched year date of vendor
	 */
	private Pair<Integer, String> calculateSinceAndYrOfIncorp(Map<String, Integer> vendorIdMap, String vendorId) {
		Pair<Integer, String> sinceWithYrOfInCorpPair = new Pair<>();
		Integer ly = vendorIdMap.get(vendorId);
		//Collections.sort(vendorSpecificLaunchedYearList);
		Integer since = ly;
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		// formula for Borker year of incorp=current_year - since
		int yearOfIncorp = currentYear - since;
		sinceWithYrOfInCorpPair.setElement1(since);
		sinceWithYrOfInCorpPair.setElement2(String.valueOf(yearOfIncorp));
		// }
		return sinceWithYrOfInCorpPair;
	}
	
	/**
	 * Pagination Algorithm
	 * ~~~~~~~~~~~~~~~~~~~~~
	 * 	IF maxRecordCountPerPage=2 THEN
	 *		IF pageNumber=1 THEN 
	 * 			offset=(pageNumber - 1) * maxRecordCountPerPage //0
	 *
	 *		IF pageNumber=2 THEN 
	 * 			offset=(pageNumber - 1) * maxRecordCountPerPage //2
	 * 
	 *		IF pageNumber=3 THEN 
	 * 			offset=(pageNumber - 1) * maxRecordCountPerPage //4
	 * 		ENDIF
	 * 		-------
	 * 		-------
	 * 	ENDIF
	 **/
	private String applyPagination(String pageNumber, String perPageMaxRecords) {
		StringBuffer baseSqlSb = new StringBuffer();
		int pageNumberAsInt = Integer.parseInt(pageNumber);
		int maxRecordCountPerPage_As_Limit = Integer.parseInt(perPageMaxRecords);
		int offset = (pageNumberAsInt - 1) * maxRecordCountPerPage_As_Limit;
		baseSqlSb.append(" limit " + maxRecordCountPerPage_As_Limit + " offset " + offset);
		return baseSqlSb.toString();
	}

	private Map<String, String> getBrokerRank(List<BrokerRankInfo> brokerRankData, String brokerId,
			EquityResearchFilter equityFilter) {
		Map<String, String> brokerRankAndMcapMap = new LinkedHashMap<>();
		for (BrokerRankInfo brokerRankInfo : brokerRankData) {
			if (brokerRankInfo.getBrokerId().equals(brokerId)) {
				String rank = brokerRankInfo.getRank();
				String capName = brokerRankInfo.getCapName();
				brokerRankAndMcapMap.put(rank, capName);
			}
		}
		
		List<String> mcapActualList = new ArrayList<>();
		if (equityFilter.getMcap() != null) {
			List<String> mcapList = equityFilter.getMcap();
			for (String mcap : mcapList) {
				if (mcap.contains("Large Cap")) {
					mcapActualList.add("Large Cap");
				}

				if (mcap.contains("Mid Cap")) {
					mcapActualList.add("Mid Cap");
				}

				if (mcap.contains("Small Cap")) {
					mcapActualList.add("Small Cap");
				}

				if (mcap.contains("Micro Cap")) {
					mcapActualList.add("Micro Cap");
				}

				if (mcap.contains("Nano Cap")) {
					mcapActualList.add("Nano Cap");
				}
			}
			if (equityFilter.getBrokerRank() != null) {
				List<String> brokerRankList = equityFilter.getBrokerRank();
				//filter brokerRankAndMcapMap
				Map<String, String> filteredbrokerRankAndMcapMap = new LinkedHashMap<>();
				for(Map.Entry<String, String> entry:brokerRankAndMcapMap.entrySet()){
					String mcapName = entry.getValue();
					String rank = entry.getKey();
					if(mcapActualList.indexOf(mcapName) !=-1) {
						String string = brokerRankList.toString();
						if(string.contains(""+rank.charAt(0))){
							filteredbrokerRankAndMcapMap.put(entry.getKey(),entry.getValue());
						}
					}
				}
				return filteredbrokerRankAndMcapMap;
			}
			
		}
		return brokerRankAndMcapMap;
		
	}
}
