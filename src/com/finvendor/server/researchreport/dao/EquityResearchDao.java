package com.finvendor.server.researchreport.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.annotation.Resource;

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
	
	@Resource(name = "finvendorProperties")
	private Properties finvendorProperties;

	private int maxRecordCountPerPage_As_Limit;
	
	/**USED IN MAIN QUERY*/
	private String companyLevelFullColumnNames="rsch_sub_area_company_dtls.company_id, rsch_sub_area_company_dtls.company_name, rsch_area_stock_class.stock_class_name STYLE, market_cap_def.market_cap_name M_CAP,research_sub_area.description SECTOR,stock_historial_prices.close_price AS CMP,stock_historial_prices.price_date AS PRC_DT,stock_current_info.pe AS PE,stock_current_info.3_yr_path_growth AS 3_YR_PAT_GRTH";
	private String vendorLevelFullColumnNames="distinct ven_rsrch_rpt_dtls.company_id,ven_rsrch_rpt_offering.product_id, vendor.company BROKER,ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE, ven_rsrch_rpt_dtls.target_price TGT_PRICE, ven_rsrch_rpt_dtls.price_at_recomm PRICE_AT_RECOMM, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 UPSIDE, ven_rsrch_rpt_dtls.rsrch_upload_report RPT_NAME, ven_rsrch_rpt_dtls.rep_date REP_DT, ven_rsrch_rpt_analyst_prof.analyst_awards, ven_rsrch_rpt_analyst_prof.anayst_cfa_charter, ven_rsrch_rpt_analyst_prof.analyst_name,vendor.analystType,vendor.vendor_id,vendor.username,ven_rsrch_rpt_dtls.rsrch_report_desc";
	
	/**USED IN CALCULATION OF TOTAL RECORDS COUNT*/
	private String companyLevelPartialColumnNames="rsch_sub_area_company_dtls.company_id, rsch_sub_area_company_dtls.company_name";
	private String vendorLevelPartialColumnNames="distinct ven_rsrch_rpt_offering.product_id, ven_rsrch_rpt_dtls.company_id";
	
	/**
	 * Get Record stats
	 */
	//TODO Need to think to improve later - Ayush
	@SuppressWarnings("unchecked")
	public String getRecordStatistics(ResearchReportFilter filter) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		
		//Query-Processing - 1 Company Level
		String companyQueryWithAppliedFilter = companyQueryWithAppliedFilter(companyLevelPartialColumnNames, equityFilter);
		SQLQuery query = commonDao.getSql(companyQueryWithAppliedFilter, null);
		List<Object[]> rows = query.list();
		List<String> companyLevelCompanyIdList=new ArrayList<>();
		for (Object[] row : rows) {
			String companyLevelCompanyId=row[0] != null ? row[0].toString() : "";
			companyLevelCompanyIdList.add(companyLevelCompanyId);
		}

		//Query-Processing - 2 Vendor Level
		String vendorQueryWithAppliedFilter = vendorQueryWithAppliedFilter(vendorLevelPartialColumnNames, equityFilter);
		query = commonDao.getSql(vendorQueryWithAppliedFilter, null);
		rows = query.list();
		List<String> vendorLevelCompanyIdList=new ArrayList<>();
		for (Object[] row : rows) {
			String vendorLevelcompanyId=row[1] != null ? row[1].toString() : "";
			vendorLevelCompanyIdList.add(vendorLevelcompanyId);
		}
		
		//Calculare Total Records
		int totalRecords = 0;
		for(String vendorLevelCompanyId:vendorLevelCompanyIdList){
			if(companyLevelCompanyIdList.contains(vendorLevelCompanyId)){
				totalRecords++;	
			}
		}
		
		//Calculate Last page number
		int remainder = totalRecords % 2;
		int lastPageNumber=0;
		if (remainder == 0) {
			lastPageNumber = totalRecords / 2;
		} else {
			lastPageNumber = (totalRecords / 2) + remainder;
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
	public Map<String, EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter, String pageNumber) throws RuntimeException {
		maxRecordCountPerPage_As_Limit = Integer.parseInt(finvendorProperties.getProperty("per_page_max_record_count"));
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		SQLQuery query;
		List<Object[]> rows;
		Map<String, EquityResearchResult> resultMap = new LinkedHashMap<>();
		Map<String, EquityResearchResult> filteredResultMap = null;
		try {
			// Step-1 Load Equity Research Area wise Company Details
			Map<String, EquityResearchResult> equityResearchAreaCompanyDetails = loadEquityResearchAreaCompanyDetails(equityFilter);
			
			if (!equityResearchAreaCompanyDetails.isEmpty()) {
				// Step-2
				String vendorLevelSQLWithAppliedFilter = vendorQueryWithAppliedFilter(vendorLevelFullColumnNames, equityFilter);
				vendorLevelSQLWithAppliedFilter = applyPagination(pageNumber, vendorLevelSQLWithAppliedFilter);
				
 				query = this.sessionFactory.getCurrentSession().createSQLQuery(vendorLevelSQLWithAppliedFilter);
				rows = query.list();
				EquityResearchResult equityResearchResult = null;

				for (Object[] row : rows) {
 					String companyId = row[0] != null ? row[0].toString() : "";
					equityResearchResult = equityResearchAreaCompanyDetails.get(companyId);
					if (equityResearchResult != null) {
						EquityResearchResult equityResearchResultNew = new EquityResearchResult();
						equityResearchResultNew.setCompany(equityResearchResult.getCompany());
						equityResearchResultNew.setStyle(equityResearchResult.getStyle());
						equityResearchResultNew.setMcap(equityResearchResult.getMcap());
						equityResearchResultNew.setSector(equityResearchResult.getSector());
						equityResearchResultNew.setCmp(equityResearchResult.getCmp());
						String priceDate = equityResearchResult.getPriceDate();
						long priceDateToTimestamp = convertStringToTimestamp(priceDate);
						equityResearchResultNew.setPriceDate(String.valueOf(priceDateToTimestamp));
						equityResearchResultNew.setPe(equityResearchResult.getPe());
						equityResearchResultNew.set_3YrPatGrowth(equityResearchResult.get_3YrPatGrowth());

						String productId = row[1] != null ? row[1].toString() : "NA";
						equityResearchResultNew.setProductId(productId);
						equityResearchResultNew.setBroker(row[2] != null ? row[2].toString() : "NA");
						//String brokerRank = row[3] != null ? row[3].toString() : "NA";
						
						equityResearchResultNew.setRecommType(row[3] != null ? row[3].toString() : "NA");
						equityResearchResultNew.setTargetPrice(row[4] != null ? row[4].toString() : "NA");
						equityResearchResultNew.setPriceAtRecomm(row[5] != null ? row[5].toString() : "NA");
						equityResearchResultNew.setUpside(row[6] != null ? row[6].toString() : "NA");
						String reportName = row[7] != null ? row[7].toString() : "NA";
						reportName = reportName.substring(reportName.lastIndexOf("/") + 1);
						equityResearchResultNew.setReport(reportName);
						String researchDate = row[8] != null ? row[8].toString() : "NA";
						long convertStringToTimestamp = convertStringToTimestamp(researchDate);
						equityResearchResultNew.setResearchDate(String.valueOf(convertStringToTimestamp));
						
						String awarded;
						if (row[9] != null) {
							if (row[9].toString().isEmpty()) {
								awarded = "N";
							} else {
								awarded = row[9].toString();
							}
						} else {
							awarded = "NA";
						}
						equityResearchResultNew.setAwarded(awarded);

						String researchedByCfa = row[10] != null ? row[10].toString() : "NA";
						if (row[10] != null) {
							if (row[10].toString().isEmpty()) {
								researchedByCfa = "N";
							} else {
								researchedByCfa = row[10].toString();
							}
						} else {
							researchedByCfa = "NA";
						}

						equityResearchResultNew.setResearchedByCfa(researchedByCfa);
						equityResearchResultNew.setAnalystName(row[11] != null ? row[11].toString() : "NA");
						equityResearchResultNew.setAnalystType(row[12] != null ? row[12].toString() : "NA");

						// Since
						String vendorId = row[13] != null ? row[13].toString() : "";
						Pair<String, String> calculatedSince = calculateSince(vendorId);
						String since = calculatedSince.getElement1();
						equityResearchResultNew.setSince(since);

						// Year Of InCorporation
						String yrOfInCorp = calculatedSince.getElement2();
						equityResearchResultNew.setYrOfInCorp(yrOfInCorp);

						// Vendor Name - will be used in Dashboard
						equityResearchResultNew.setVendorName(row[14] != null ? row[14].toString() : "");

						// Report desc - will be used in Dashboard
						equityResearchResultNew.setReportDesc(row[15] != null ? row[15].toString() : "");

						equityResearchResultNew.setCompanyId(companyId);
						
						//Broker Rank 
						String[] brokerRanks = getBrokerRank(vendorId, equityFilter);
						String largeRank = String.valueOf("".equals(brokerRanks[0]) ? "" : brokerRanks[0].charAt(0));
						String midRank = String.valueOf("".equals(brokerRanks[1]) ? "" : brokerRanks[1].charAt(0));
						String smallRank = String.valueOf("".equals(brokerRanks[2]) ? "" : brokerRanks[2].charAt(0));
						
						equityResearchResultNew.setBrokerRankLargeCap(largeRank);
						equityResearchResultNew.setBrokerRankMidCap(midRank);
						equityResearchResultNew.setBrokerRankSmallCap(smallRank);
						
						//Set Current Page number
						equityResearchResultNew.setPageNumber(pageNumber);
						
						resultMap.put(productId, equityResearchResultNew);
					}
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
			} else {
				logger.info("!! No companies found for given GEO !!");
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
	private Map<String, EquityResearchResult> loadEquityResearchAreaCompanyDetails(EquityResearchFilter equityFilter) {
		Map<String, EquityResearchResult> equityResearchAreaCompanyDetails = new LinkedHashMap<>();
		SQLQuery query;
		List<Object[]> rows;
		String companyLevelSQLWithAppliedFilter = companyQueryWithAppliedFilter(companyLevelFullColumnNames, equityFilter);
		query = this.sessionFactory.getCurrentSession().createSQLQuery(companyLevelSQLWithAppliedFilter);
		rows = query.list();
		for (Object[] row : rows) {
			EquityResearchResult equityRR = new EquityResearchResult();
			String companyId = row[0] != null ? row[0].toString() : "NA";
			equityRR.setCompany(row[1] != null ? row[1].toString() : "NA");
			equityRR.setStyle(row[2] != null ? row[2].toString() : "NA");
			equityRR.setMcap(row[3] != null ? row[3].toString() : "NA");
			equityRR.setSector(row[4] != null ? row[4].toString() : "NA");
			equityRR.setCmp(row[5] != null ? row[5].toString() : "NA");
			equityRR.setPriceDate(row[6] != null ? row[6].toString() : "NA");
			equityRR.setPe(row[7] != null ? row[7].toString() : "NA");
			equityRR.set_3YrPatGrowth(row[8] != null ? row[8].toString() : "NA");
			equityResearchAreaCompanyDetails.put(companyId, equityRR);
		}
		return equityResearchAreaCompanyDetails;
	}

	private String companyQueryWithAppliedFilter(String companyLevelFullColumnNames, EquityResearchFilter equityFilter) {
		String companyLevelSQL = "SELECT " + companyLevelFullColumnNames + "  FROM rsch_sub_area_company_dtls,rsch_area_stock_class, market_cap_def,comp_mkt_cap_type,research_sub_area,stock_historial_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id=market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND research_sub_area.research_area_id=7 "; //7 means Equity Research Area
		String companyLevelSQLWithAppliedFilter = applyCompanyLevelFilter(companyLevelSQL, equityFilter);
		return companyLevelSQLWithAppliedFilter;
	}

	private String vendorQueryWithAppliedFilter(String vendorLevelFullColumnNames, EquityResearchFilter equityFilter) {
		String vendorLevelSQL = "SELECT " + vendorLevelFullColumnNames + "  FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor,broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id=broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7 "; //7 means Equity Search as- Research Area
		String vendorLevelSQLWithAppliedFilter = applyVendorLevelFilter(vendorLevelSQL, equityFilter);
		return vendorLevelSQLWithAppliedFilter;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~										F I L T E R - C O D E															~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private String applyCompanyLevelFilter(String baseSql, EquityResearchFilter equityFilter) {
		StringBuffer baseSqlSb = new StringBuffer(baseSql);

		//GEO filter applied
		if (equityFilter.getGeo() != null) {
			baseSqlSb.append(" AND country.country_id = ").append(equityFilter.getGeo());
		}

		//MCap filter applied
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
			appendFilterWithInClause(baseSqlSb, "market_cap_def.market_cap_name", mcapActualList, true);
		}

		// #Style filter applied
		if (equityFilter.getStyle() != null) {
			appendFilterWithInClause(baseSqlSb, "rsch_area_stock_class.stock_class_name", equityFilter.getStyle(),
					true);
		}

		baseSqlSb.append(" ORDER BY rsch_sub_area_company_dtls.company_id");
		return baseSqlSb.toString();
	}

	private String applyVendorLevelFilter(String baseSql, EquityResearchFilter equityFilter) {
		StringBuffer baseSqlSb = new StringBuffer(baseSql);

		//AnalystType filter applied
		if (equityFilter.getAnalystType() != null) {
			appendFilterWithInClause(baseSqlSb, "vendor.analystType", equityFilter.getAnalystType(), true);
		}

		//ResearchBroker filter applied
		if (equityFilter.getResearchedBroker() != null) {
			appendFilterWithInClause(baseSqlSb, "vendor.company", equityFilter.getResearchedBroker(), true);
		}

		//RecommType filter applied
		if (equityFilter.getRecommType() != null) {
			appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_dtls.rsrch_recomm_type", equityFilter.getRecommType(), true);
		}

		//Others filter applied
		if (equityFilter.getOthers() != null) {
			List<String> others = equityFilter.getOthers();
			List<String> otherOptionList = new ArrayList<>();

			if (others.size() == 1) {
				String otherOption = others.get(0);
				if (otherOption.contains("Award")) {
					otherOptionList.add("Y");
					appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_analyst_prof.analyst_awards", otherOptionList,true);
				} else if (otherOption.contains("CFA")) {
					otherOptionList.add("Y");
					appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_analyst_prof.anayst_cfa_charter",otherOptionList, true);
				}
			} else if (others.size() == 2) {
				String otherOption1 = others.get(0);
				String otherOption2 = others.get(1);

				// expect following order for others option
				if (otherOption1 != null && otherOption1.contains("Award")) {
					otherOptionList.add("Y");
					appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_analyst_prof.analyst_awards", otherOptionList,true);
				}

				if (otherOption2 != null && otherOption2.contains("CFA")) {
					otherOptionList.add("Y");
					appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_analyst_prof.anayst_cfa_charter",otherOptionList, true);
				}
			} else {
				// throw Invalid Filter Exception TODO
			}
		}

		//Upside filter applied
		if (equityFilter.getUpside() != null && equityFilter.getUpside().size() > 0) {
			List<String> upsideValueList = equityFilter.getUpside();
			//Upside Formula
			String filterCondition = "((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100";
			baseSqlSb.append(" AND (");
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
					baseSqlSb.append(" OR " + upsideConditions.get(i));
				} else {
					baseSqlSb.append(upsideConditions.get(i));
				}
			}
			baseSqlSb.append(")");
		}
		baseSqlSb.append(" ORDER BY ven_rsrch_rpt_dtls.company_id ASC,ven_rsrch_rpt_dtls.rep_date DESC");
		return baseSqlSb.toString();
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~										O T H E R - C O D E																~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Since is minimum launched year date of vendor
	 */
	@SuppressWarnings("unchecked")
	private Pair<String, String> calculateSince(String vendorId) {
		Pair<String, String> sinceWithYrOfInCorpPair = new Pair<>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VendorResearchReportsOffering.class);
		List<VendorResearchReportsOffering> list = criteria.list();
		if (list != null && list.size() > 0) {
			Map<String, List<String>> vendorIdMap = new HashMap<>();

			for (int index = 0, listSize = list.size(); index < listSize; index++) {
				VendorResearchReportsOffering vrro = list.get(index);
				String vId = vrro.getVendor().getId();
				List<String> exitsingLaunchedYrFromMap = vendorIdMap.get(vId);
				if (exitsingLaunchedYrFromMap == null) {
					List<String> lYrList = new ArrayList<>();
					lYrList.add(vrro.getLaunchedYear());
					vendorIdMap.put(vId, lYrList);
				} else {
					exitsingLaunchedYrFromMap.add(vrro.getLaunchedYear());
				}
			}

			List<String> vendorSpecificLaunchedYearList = vendorIdMap.get(vendorId);
			Collections.sort(vendorSpecificLaunchedYearList);
			String since = vendorSpecificLaunchedYearList.get(0);
			int sinceAsInt = Integer.parseInt(since);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			
			//formula for Borker year of incorp=current_year - since
			int yearOfIncorp=currentYear-sinceAsInt;
			sinceWithYrOfInCorpPair.setElement1(since);
			sinceWithYrOfInCorpPair.setElement2(String.valueOf(yearOfIncorp));
		}
		return sinceWithYrOfInCorpPair;
	}
	
	private String applyPagination(String pageNumber, String vendorLevelSQLWithAppliedFilter) {
		StringBuffer baseSqlSb = new StringBuffer(vendorLevelSQLWithAppliedFilter);
		/*
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
		int pageNumberAsInt = Integer.parseInt(pageNumber);
		int offset = (pageNumberAsInt - 1) * maxRecordCountPerPage_As_Limit;
		baseSqlSb.append(" limit " + maxRecordCountPerPage_As_Limit + " offset " + offset);
		vendorLevelSQLWithAppliedFilter=baseSqlSb.toString();
		return vendorLevelSQLWithAppliedFilter;
	}

	@SuppressWarnings("unchecked")
	private String[] getBrokerRank(String brokerId,EquityResearchFilter equityFilter) {
		
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
		}
		
		String[] brokerRanks=new String[3];
		String sqlQuery="select broker_analyst.broker_id, broker_analyst.broker_rank, market_cap_def.market_cap_name from broker_analyst,market_cap_def where broker_analyst.market_cap_id=market_cap_def.market_cap_id and broker_analyst.broker_id=?";
		SQLQuery query = commonDao.getSql(sqlQuery, new String[]{brokerId});
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			String rank=row[1] != null ? row[1].toString() : "";
			String capName=row[2] != null ? row[2].toString() : "";
			
			if (mcapActualList.size() > 0) {
				if (mcapActualList.contains("Large Cap")) {
					brokerRanks[0] = rank;
				} else {
					brokerRanks[0] = "";
				}

				if (mcapActualList.contains("Mid Cap")) {
					brokerRanks[1] = rank;
				} else {
					brokerRanks[1] = "";
				}

				if (mcapActualList.contains("Small Cap")) {
					brokerRanks[2] = rank;
				} else {
					brokerRanks[2] = "";
				}
			} else {
				if ("Large Cap".equals(capName)) {
					brokerRanks[0] = rank;
				}

				if ("Mid Cap".equals(capName)) {
					brokerRanks[1] = rank;
				}

				if ("Small Cap".equals(capName)) {
					brokerRanks[2] = rank;
				}
			}
		}
		return brokerRanks;
	}

}
