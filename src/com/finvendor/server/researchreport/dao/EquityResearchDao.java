package com.finvendor.server.researchreport.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter) throws RuntimeException {
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
				String vendorLevelSQL = "SELECT distinct ven_rsrch_rpt_dtls.company_id,ven_rsrch_rpt_offering.product_id, vendor.company BROKER,broker_analyst.broker_rank BROKER_RANK,ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE, ven_rsrch_rpt_dtls.target_price TGT_PRICE, ven_rsrch_rpt_dtls.price_at_recomm PRICE_AT_RECOMM, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 UPSIDE, ven_rsrch_rpt_dtls.rsrch_upload_report RPT_NAME, ven_rsrch_rpt_dtls.rep_date REP_DT, ven_rsrch_rpt_analyst_prof.analyst_awards, ven_rsrch_rpt_analyst_prof.anayst_cfa_charter, ven_rsrch_rpt_analyst_prof.analyst_name,vendor.analystType,vendor.vendor_id,vendor.username,ven_rsrch_rpt_dtls.rsrch_report_desc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor,broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id=broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7 "; //7 means Equity Search as- Research Area
				String vendorLevelSQLWithAppliedFilter = applyVendorLevelFilter(vendorLevelSQL, equityFilter);
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
						
						equityResearchResultNew.setRecommType(row[4] != null ? row[4].toString() : "NA");
						equityResearchResultNew.setTargetPrice(row[5] != null ? row[5].toString() : "NA");
						equityResearchResultNew.setPriceAtRecomm(row[6] != null ? row[6].toString() : "NA");
						equityResearchResultNew.setUpside(row[7] != null ? row[7].toString() : "NA");
						String reportName = row[8] != null ? row[8].toString() : "NA";
						reportName = reportName.substring(reportName.lastIndexOf("/") + 1);
						equityResearchResultNew.setReport(reportName);
						String researchDate = row[9] != null ? row[9].toString() : "NA";
						long convertStringToTimestamp = convertStringToTimestamp(researchDate);
						equityResearchResultNew.setResearchDate(String.valueOf(convertStringToTimestamp));
						
						String awarded;
						if (row[10] != null) {
							if (row[10].toString().isEmpty()) {
								awarded = "N";
							} else {
								awarded = row[10].toString();
							}
						} else {
							awarded = "NA";
						}
						equityResearchResultNew.setAwarded(awarded);

						String researchedByCfa = row[11] != null ? row[11].toString() : "NA";
						if (row[11] != null) {
							if (row[11].toString().isEmpty()) {
								researchedByCfa = "N";
							} else {
								researchedByCfa = row[11].toString();
							}
						} else {
							researchedByCfa = "NA";
						}

						equityResearchResultNew.setResearchedByCfa(researchedByCfa);
						equityResearchResultNew.setAnalystName(row[12] != null ? row[12].toString() : "NA");
						equityResearchResultNew.setAnalystType(row[13] != null ? row[13].toString() : "NA");

						// Since
						String vendorId = row[14] != null ? row[14].toString() : "";
						Pair<String, String> calculatedSince = calculateSince(vendorId);
						String since = calculatedSince.getElement1();
						equityResearchResultNew.setSince(since);

						// Year Of InCorporation
						String yrOfInCorp = calculatedSince.getElement2();
						equityResearchResultNew.setYrOfInCorp(yrOfInCorp);

						// Vendor Name - will be used in Dashboard
						equityResearchResultNew.setVendorName(row[15] != null ? row[15].toString() : "");

						// Report desc - will be used in Dashboard
						equityResearchResultNew.setReportDesc(row[16] != null ? row[16].toString() : "");

						equityResearchResultNew.setCompanyId(companyId);
						
						//Broker Rank 
						String[] brokerRanks = getBrokerRank(vendorId);
						String largeRank=String.valueOf(brokerRanks[0].charAt(0));
						String midRank=String.valueOf(brokerRanks[1].charAt(0));
						String smallRank=String.valueOf(brokerRanks[2].charAt(0));
						
						equityResearchResultNew.setBrokerRankLargeCap(largeRank);
						equityResearchResultNew.setBrokerRankMidCap(midRank);
						equityResearchResultNew.setBrokerRankSmallCap(smallRank);
						
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
	private String[] getBrokerRank(String brokerId) {
		String[] brokerRanks=new String[3];
		String sqlQuery="select broker_analyst.broker_id, broker_analyst.broker_rank, market_cap_def.market_cap_name from broker_analyst,market_cap_def where broker_analyst.market_cap_id=market_cap_def.market_cap_id and broker_analyst.broker_id=?";
		SQLQuery query = commonDao.getSql(sqlQuery, new String[]{brokerId});
		List<Object[]> rows = query.list();
		
		for (Object[] row : rows) {
			String rank=row[1] != null ? row[1].toString() : "";
			String capName=row[2] != null ? row[2].toString() : "";
			if("Large Cap".equals(capName)) {
				brokerRanks[0]=rank;
			}
			
			if("Mid Cap".equals(capName)) {
				brokerRanks[1]=rank;
			}
			
			if("Small Cap".equals(capName)) {
				brokerRanks[2]=rank;
			}
		}
		return brokerRanks;
	}

	@SuppressWarnings("unchecked")
	private Map<String, EquityResearchResult> loadEquityResearchAreaCompanyDetails(EquityResearchFilter equityFilter) {
		Map<String, EquityResearchResult> equityResearchAreaCompanyDetails = new LinkedHashMap<>();
		SQLQuery query;
		List<Object[]> rows;
		String companyLevelSQL = "SELECT rsch_sub_area_company_dtls.company_id, rsch_sub_area_company_dtls.company_name, rsch_area_stock_class.stock_class_name STYLE, market_cap_def.market_cap_name M_CAP,research_sub_area.description SECTOR,stock_historial_prices.close_price AS CMP,stock_historial_prices.price_date AS PRC_DT,stock_current_info.pe AS PE,stock_current_info.3_yr_path_growth AS 3_YR_PAT_GRTH FROM rsch_sub_area_company_dtls,rsch_area_stock_class, market_cap_def,comp_mkt_cap_type,research_sub_area,stock_historial_prices,stock_current_info,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id=market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND research_sub_area.research_area_id=7 "; //7 means Equity Research Area
		String companyLevelSQLWithAppliedFilter = applyCompanyLevelFilter(companyLevelSQL, equityFilter);
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

		//BrokerRank filter applied
		if (equityFilter.getBrokerRank() != null) {
			List<String> rankList = new ArrayList<>();
			for (String rank : equityFilter.getBrokerRank()) {
				if (rank.contains("5 star")) {
					rankList.add("5star");
				}
				if (rank.contains("4 star")) {
					rankList.add("4star");
				}
				if (rank.contains("3 star")) {
					rankList.add("3star");
				}
				if (rank.contains("2 star")) {
					rankList.add("2star");
				}
				if (rank.contains("1 star")) {
					rankList.add("1star");
				}
			}
			appendFilterWithInClause(baseSqlSb, "broker_analyst.broker_rank", rankList, true);
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
		
		baseSqlSb.append(" ORDER BY vendor.company asc,ven_rsrch_rpt_dtls.rep_date DESC");
		return baseSqlSb.toString();
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("<0%");
		list.add("0-20%");
		list.add("20-50%");
		list.add("50-100%");

		for (String s : list) {
			if ("<0%".equals(s)) {
				System.out.println("List contains <0%");
			}
			if (list.contains("0-20%")) {
				System.out.println("List contains 0-20%");
			}
		}
	}
}
