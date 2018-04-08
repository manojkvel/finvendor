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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.model.VendorResearchReportsOffering;
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
	
	private String mainQuery="select x.comapanyId,x.companyName,x.style,x.mcap,x.sector,x.cmp,x.prcDt,x.pe,x.patGrth,y.companyId,y.prdId,y.broker,y.recommType,y.tgtPrice,y.prcAtRecomm,y.upside,y.rptName,y.rsrchDt,y.award,y.cfa,y.analystName,y.analystType,y.vendorId,y.ly,y.userName,y.rptDesc from(SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_historial_prices.close_price cmp,stock_historial_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls,rsch_area_stock_class,market_cap_def,comp_mkt_cap_type,research_sub_area,stock_historial_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId,ven_rsrch_rpt_offering.product_id prdId, vendor.company broker ,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType,ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm,((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', -1) rptName,ven_rsrch_rpt_dtls.rep_date rsrchDt,ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa, ven_rsrch_rpt_analyst_prof.analyst_name analystName,vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering,ven_rsrch_rpt_dtls,ven_rsrch_rpt_analyst_prof,vendor,broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y on x.comapanyId = y.companyId";
	
	@SuppressWarnings("unchecked")
	@Override
	public String getRecordStatistics(ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		
		String queryWithAppliedFilter = applyFilter(mainQuery, getFilteredQueryPartNew(equityFilter));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		Map<String, EquityResearchResult> resultMap = new LinkedHashMap<>();
		try {
			//Prepare data for Since from db
			Map<String, Integer> vendorIdWithLaunchedYearDataMap = prepareVendorSinceData(sortBy, orderBy);
			
			//Prepare brokerRank data from db
			List<BrokerRankInfo> brokerRankData = getBrokerRankData(orderBy);
			
			//Apply filter in main query
			String queryWithAppliedFilter 	= applyFilter(mainQuery, getFilteredQueryPartNew(equityFilter));
			
			//Apply OrderBy
			String applyOrderBy 			= applyOrderBy(sortBy, orderBy);
			
			//Apply Pagination
			String applyPagination 			= applyPagination(pageNumber, perPageMaxRecords);
			
			//Prepare final query
			String mainQuery = new StringBuilder(500).append(queryWithAppliedFilter).append(applyOrderBy).append(applyPagination).toString();

			//Execute Query
			SQLQuery query = commonDao.getSql(mainQuery, new String[]{equityFilter.getGeo()});
			List<Object[]> rows = query.list();
			
			//Process Result
			for (Object[] row : rows) {
				EquityResearchResult equityResult = new EquityResearchResult();
				equityResult.setCompanyId(row[0] != null ? row[0].toString() : "");
				equityResult.setCompany(row[1] != null ? row[1].toString() : "");
				equityResult.setStyle(row[2] != null ? row[2].toString() : "");
				equityResult.setMcap(row[3] != null ? row[3].toString() : "");
				equityResult.setSector(row[4] != null ? row[4].toString() : "");
				equityResult.setCmp(row[5] != null ? row[5].toString() : "");
				
				equityResult.setPriceDate(String.valueOf(convertStringToTimestamp(row[6] != null ? row[6].toString() : "")));
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
				
				Pair<Integer, String> calculatedSince = calculateSinceAndYrOfIncorp(vendorIdWithLaunchedYearDataMap, vendorId);
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
			
			// #BrokerYear Of Incorporation filter applied
			if (equityFilter.getBrokerYrOfInCorp() != null) {
				Map<String, EquityResearchResult> filteredYrOfInCorpResultMap = applyFilterForYearOfInCorp(equityFilter, resultMap);
				return filteredYrOfInCorpResultMap;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return resultMap;
	}
	
	private Map<String, EquityResearchResult> applyFilterForYearOfInCorp(EquityResearchFilter equityFilter,
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
					filteredYrOfInCorpResultMap.put(entry.getKey(), entry.getValue());
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
					filteredYrOfInCorpResultMap.put(entry.getKey(), entry.getValue());
					removeProductIdList.add(entry.getKey());
				}
			}

			//release memory
			for (String prodKey : removeProductIdList) {
				resultMap.remove(prodKey);
			}
			removeProductIdList.clear();
		}
		
		if (filteredYrOfInCorpResultMap != null) {
			resultMap.clear(); //release memory
		}
		return filteredYrOfInCorpResultMap;
	}

	private Map<String, Integer> prepareVendorSinceData(String sortBy, String orderBy) {
		Map<String,Integer> sortedVendorIdWithLyMap	= new LinkedHashMap<>();
		Map<String, Integer> vendorIdWithLyMap 	= vendorIdWithLyMap(orderBy);
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
			
			for(Entry<String, Integer> item:list){
				sortedVendorIdWithLyMap.put(item.getKey(),item.getValue());
			}
		}
		if(sortedVendorIdWithLyMap.size()==0) {
			return vendorIdWithLyMap;
		}
		return sortedVendorIdWithLyMap;
	}
	
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
	
	private String getFilteredQueryPartNew(EquityResearchFilter equityFilter) {
		StringBuffer conditionSqlSb 	= 	new StringBuffer();
		List<String> conditionQueryList	=	new ArrayList<>();
		
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
		
		//AnalystType filter applied
		if (equityFilter.getAnalystType() != null) {
			conditionQueryList.add(getConditionQuery("y.analystType", equityFilter.getAnalystType()));
		}

		//ResearchBroker filter applied
		if (equityFilter.getResearchedBroker() != null) {
			conditionQueryList.add(getConditionQuery("y.broker", equityFilter.getResearchedBroker()));
		}

		//RecommType filter applied
		if (equityFilter.getRecommType() != null) {
			conditionQueryList.add(getConditionQuery("y.recommType", equityFilter.getRecommType()));
		}

		//Others filter applied "others":["Award Winning Analyst","Research Reports by CFA"]
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
			conditionQueryList.add(conditionSqlSb.toString());
		}
		return mergeConditionQuery(conditionQueryList);
	}
	
	private String getConditionQuery(String columnName, List<String> valueList){
		String inClauseItems = getInClauseItems(valueList);
		StringBuffer conditionQuerySb = new StringBuffer(100);
		String conditionQuery = conditionQuerySb.append(columnName).append(" in ").append("(").append(inClauseItems).append(")").toString();
		return conditionQuery;
	}
	
	private String mergeConditionQuery(List<String> conditionQueryList) {
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
	
	private List<String> getActualMCapList(List<String> mcapList){
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
	
	private String getFilteredQueryPart(EquityResearchFilter equityFilter) {
		StringBuffer conditionSqlSb = new StringBuffer();
		List<String> conditionQueryList=new ArrayList<>();
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
				conditionSqlSb.append("x.mcap in (").append(getInClauseItems(mcapActualList)).append(")");
				conditionQueryList.add(conditionSqlSb.toString());
				conditionSqlSb.setLength(0);
			}
		}

		// #Style filter applied
		if (equityFilter.getStyle() != null) {
			conditionSqlSb.append(" x.style in (").append(getInClauseItems(equityFilter.getStyle())).append(")");
			conditionQueryList.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}
		
		//AnalystType filter applied
		if (equityFilter.getAnalystType() != null) {
			conditionSqlSb.append(" y.analystType in (").append(getInClauseItems(equityFilter.getAnalystType())).append(")");
			conditionQueryList.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		//ResearchBroker filter applied
		if (equityFilter.getResearchedBroker() != null) {
			conditionSqlSb.append(" y.broker in (").append(getInClauseItems(equityFilter.getResearchedBroker())).append(")");
			conditionQueryList.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}

		//RecommType filter applied
		if (equityFilter.getRecommType() != null) {
			conditionSqlSb.append(" y.recommType in (").append(getInClauseItems(equityFilter.getRecommType())).append(")");
			conditionQueryList.add(conditionSqlSb.toString());
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
			conditionQueryList.add(conditionSqlSb.toString());
			conditionSqlSb.setLength(0);
		}
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
			conditionSqlSb.append(conditionSqlSb);
		}
		
		return conditionSqlSb.toString();
	}
	
	private String applyFilter(String mainQuery, String filteredQuery) {
		return  new StringBuffer(mainQuery).append(filteredQuery).toString();
	}

	private String getInClauseItems(List<String> options){
		StringBuilder getInClauseItemsSb=new StringBuilder(50);
		
		for(String option:options){
			getInClauseItemsSb.append("'").append(option).append("'").append(",");
		}
		getInClauseItemsSb.deleteCharAt(getInClauseItemsSb.toString().length()-1);
		return getInClauseItemsSb.toString();
	}
	
	/**
	 * Since is minimum launched year date of vendor
	 */
	private Pair<Integer, String> calculateSinceAndYrOfIncorp(Map<String, Integer> vendorIdMap, String vendorId) {
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

	private String convertCamelCase(String str){
		String replace = StringUtils.replace(str, " ", "");
		replace=replace.substring(0, 1).toLowerCase()+replace.substring(1);
		return replace;
	}
	private Map<String, String> getBrokerRank(List<BrokerRankInfo> brokerRankData, String brokerId,
			EquityResearchFilter equityFilter) {
		Map<String, String> brokerRankAndMcapMap = new LinkedHashMap<>();
		for (BrokerRankInfo brokerRankInfo : brokerRankData) {
			if (brokerRankInfo.getBrokerId().equals(brokerId)) {
				String rank = brokerRankInfo.getRank();
				String capName = convertCamelCase(brokerRankInfo.getCapName());
				brokerRankAndMcapMap.put(capName,rank.substring(0,1));
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
				
				//filter brokerRankAndMcapMap
				Map<String, String> filteredbrokerRankAndMcapMap = new LinkedHashMap<>();
				for(Map.Entry<String, String> entry:brokerRankAndMcapMap.entrySet()){
					String mcapName = entry.getKey();
					String rank = entry.getValue();
					if(mcapActualList.indexOf(mcapName) !=-1) {
						String string = brokerRankList.toString();
						if(string.contains(rank)){
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
