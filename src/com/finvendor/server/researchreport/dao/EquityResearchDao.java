package com.finvendor.server.researchreport.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.util.Pair;
import com.finvendor.model.VendorResearchReportsOffering;
import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.server.researchreport.dto.result.dashboard.EquityResearchResultDashboard;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Repository
public class EquityResearchDao extends AbsResearchReportDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static Map<String, EquityResearchResult> equityResearchAreaCompanyDetailsCache = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	// Dirty Approach - need to improve later - Ayush
	public List<EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		SQLQuery query;
		List<Object[]> rows;
		// Map<String, EquityResearchResult> resultMap = new HashMap<>();
		List<EquityResearchResult> resultList = new ArrayList<>();

		try {
			// Step-1 Load Equity Research Area wise Company Details
			loadEquityResearchAreaCompanyDetails(equityFilter);

			// Step-2 Vendor Level SQL
			//String vendorLevelSQL = "SELECT distinct ven_rsrch_rpt_dtls.company_id,vendor.vendor_id,(vendor.company) BROKER,broker_analyst.broker_rank BROKER_RANK,ven_rsrch_rpt_offering.product_id, ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE, ven_rsrch_rpt_dtls.target_price TGT_PRICE, ven_rsrch_rpt_dtls.price_at_recomm PRICE_AT_RECOMM, (ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm / ven_rsrch_rpt_dtls.price_at_recomm) * 100 UPSIDE, ven_rsrch_rpt_dtls.rsrch_upload_report RPT_NAME, ven_rsrch_rpt_dtls.rep_date REP_DT, ven_rsrch_rpt_analyst_prof.analyst_awards, ven_rsrch_rpt_analyst_prof.anayst_cfa_charter, ven_rsrch_rpt_analyst_prof.analyst_name,vendor.analystType FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor,broker_analyst where ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id=broker_analyst.broker_id";
			String vendorLevelSQL = "SELECT distinct ven_rsrch_rpt_dtls.company_id,ven_rsrch_rpt_offering.product_id, vendor.company BROKER,broker_analyst.broker_rank BROKER_RANK,ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE, ven_rsrch_rpt_dtls.target_price TGT_PRICE, ven_rsrch_rpt_dtls.price_at_recomm PRICE_AT_RECOMM, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 UPSIDE, ven_rsrch_rpt_dtls.rsrch_upload_report RPT_NAME, ven_rsrch_rpt_dtls.rep_date REP_DT, ven_rsrch_rpt_analyst_prof.analyst_awards, ven_rsrch_rpt_analyst_prof.anayst_cfa_charter, ven_rsrch_rpt_analyst_prof.analyst_name,vendor.analystType,vendor.vendor_id FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor,broker_analyst where ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id=broker_analyst.broker_id";
			String vendorLevelSQLWithAppliedFilter = applyVendorLevelFilter(vendorLevelSQL, equityFilter);
			query = this.sessionFactory.getCurrentSession().createSQLQuery(vendorLevelSQLWithAppliedFilter);
			rows = query.list();

			EquityResearchResult equityResearchResult = null;
			for (Object[] row : rows) {
				String companyId = row[0] != null ? row[0].toString() : "";
				equityResearchResult = equityResearchAreaCompanyDetailsCache.get(companyId);
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

					equityResearchResultNew.setProductId(row[1] != null ? row[1].toString() : "NA");
					equityResearchResultNew.setBroker(row[2] != null ? row[2].toString() : "NA");
					String brokerRank = row[3] != null ? row[3].toString() : "NA";
					equityResearchResultNew.setBrokerRankLargeCap("5");
					equityResearchResultNew.setBrokerRankMidCap("4");
					equityResearchResultNew.setBrokerRankSmallCap("3");
					equityResearchResultNew.setRecommType(row[4] != null ? row[4].toString() : "NA");
					equityResearchResultNew.setTargetPrice(row[5] != null ? row[5].toString() : "NA");
					equityResearchResultNew.setPriceAtRecomm(row[6] != null ? row[6].toString() : "NA");
					equityResearchResultNew.setUpside(row[7] != null ? row[7].toString() : "NA");
					String reportName = row[8] != null ? row[8].toString() : "NA";
					reportName=reportName.substring(reportName.lastIndexOf("/")+1);
					equityResearchResultNew.setReport(reportName);
					String researchDate = row[9] != null ? row[9].toString() : "NA";
					long convertStringToTimestamp = convertStringToTimestamp(researchDate);
					equityResearchResultNew.setResearchDate(String.valueOf(convertStringToTimestamp));
					String awarded;
					if(row[10] != null){
						if(row[10].toString().isEmpty()){
							awarded="N";
						}else{
							awarded=row[10].toString();
						}
					}else{
						awarded="NA";
					}
					equityResearchResultNew.setAwarded(awarded);
					
					String researchedByCfa = row[11] != null ? row[11].toString() : "NA";
					if(row[11] != null){
						if(row[11].toString().isEmpty()){
							researchedByCfa="N";
						}else{
							researchedByCfa=row[11].toString();
						}
					}else{
						researchedByCfa="NA";
					}
					
					equityResearchResultNew.setResearchedByCfa(researchedByCfa);
					equityResearchResultNew.setAnalystName(row[12] != null ? row[12].toString() : "NA");
					equityResearchResultNew.setAnalystType(row[13] != null ? row[13].toString() : "NA");

					// Since
					String vendorId = row[14] != null ? row[14].toString() : "";
					String since = calculateSince(vendorId).getElement1();
					equityResearchResultNew.setSince(since);

					// Year Of InCorporation
					String yrOfInCorp = calculateSince(vendorId).getElement2();
					equityResearchResultNew.setYrOfInCorp(yrOfInCorp);

					equityResearchResultNew.setCompanyId(companyId);
					resultList.add(equityResearchResultNew);
				}
			}
			return resultList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadEquityResearchAreaCompanyDetails(EquityResearchFilter equityFilter) {
		SQLQuery query;
		List<Object[]> rows;
		if (equityResearchAreaCompanyDetailsCache.isEmpty()) {
			String companyLevelSQL = "select rsch_sub_area_company_dtls.company_id,rsch_sub_area_company_dtls.company_name,rsch_area_stock_class.stock_class_name STYLE, market_cap_def.mcap_name M_CAP,research_sub_area.description SECTOR,stock_historial_prices.close_price AS CMP,stock_historial_prices.price_date AS PRC_DT,stock_current_info.pe AS PE,stock_current_info.3_yr_path_growth AS 3_YR_PAT_GRTH from rsch_sub_area_company_dtls,rsch_area_stock_class,market_cap_def,research_sub_area,stock_historial_prices,stock_current_info,country where rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = market_cap_def.company_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id and rsch_sub_area_company_dtls.rsch_sub_area_id=research_sub_area.research_sub_area_id ";
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
				equityResearchAreaCompanyDetailsCache.put(companyId, equityRR);
			}
		}
	}

	/**
	 * Since is minimum launched year date of vendor
	 */
	@SuppressWarnings("unchecked")
	private Pair<String, String> calculateSince(String vendorId) {
		Pair<String, String> sinceWithYrOfInCorpPair = new Pair<>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VendorResearchReportsOffering.class);
		List<VendorResearchReportsOffering> list = criteria.list();
		Map<String, List<String>> vendorIdMap = new HashMap<>();
		for (VendorResearchReportsOffering vrro : list) {
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
		Collections.sort(vendorSpecificLaunchedYearList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String since = vendorSpecificLaunchedYearList.get(0);
		sinceWithYrOfInCorpPair.setElement1(since);
		sinceWithYrOfInCorpPair.setElement2(String.valueOf(vendorSpecificLaunchedYearList.size()));
		return sinceWithYrOfInCorpPair;
	}

	private String applyCompanyLevelFilter(String baseSql, EquityResearchFilter equityFilter) {
		StringBuffer baseSqlSb = new StringBuffer(baseSql);

		baseSqlSb.append(" and research_sub_area.research_area_id=7");
		if (equityFilter.getGeo() != null) {
			baseSqlSb.append(" and country.country_id = ").append(equityFilter.getGeo());
		}

		if (equityFilter.getMcap() != null) {
			List<String> mcapList = equityFilter.getMcap();
			List<String> mcapActualList = new ArrayList<>();
			for (String mcap : mcapList) {
				if (mcap.contains("Large Cap")) {
					mcapActualList.add("Large Cap");
				} else if (mcap.contains("Mid Cap")) {
					mcapActualList.add("Mid Cap");
				} else if (mcap.contains("Small Cap")) {
					mcapActualList.add("Small Cap");
				} else if (mcap.contains("Micro Cap")) {
					mcapActualList.add("Micro Cap");
				} else {
					mcapActualList.add("Nano Cap");
				}
			}
			appendFilterWithInClause(baseSqlSb, "market_cap_def.mcap_name", mcapActualList);
		}

		if (equityFilter.getStyle() != null) {
			appendFilterWithInClause(baseSqlSb, "rsch_area_stock_class.stock_class_name", equityFilter.getStyle());
		}

		baseSqlSb.append(" order by rsch_sub_area_company_dtls.company_id");
		return baseSqlSb.toString();
	}

	private String applyVendorLevelFilter(String baseSql, EquityResearchFilter equityFilter) {
		StringBuffer baseSqlSb = new StringBuffer(baseSql);
		baseSqlSb.append(" and ven_rsrch_rpt_offering.research_area = 7");
		if (equityFilter.getAnalystType() != null) {
			appendFilterWithInClause(baseSqlSb, "vendor.analystType", equityFilter.getAnalystType());
		}

		if (equityFilter.getResearchedBroker() != null) {
			appendFilterWithInClause(baseSqlSb, "vendor.username", equityFilter.getResearchedBroker());
		}

		if (equityFilter.getBrokerRank() != null) {
			List<String> rankList = new ArrayList<>();
			for (String rank : equityFilter.getBrokerRank()) {
				if (rank.contains("5 star")) {
					rankList.add("5star");
				} else if (rank.contains("4 star")) {
					rankList.add("4star");
				} else if (rank.contains("3 star")) {
					rankList.add("3star");
				} else if (rank.contains("2 star")) {
					rankList.add("2star");
				} else {
					rankList.add("1star");
				}
			}
			appendFilterWithInClause(baseSqlSb, "broker_analyst.broker_rank", rankList);
		}

		if (equityFilter.getRecommType() != null) {
			appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_dtls.rsrch_recomm_type", equityFilter.getRecommType());
		}

		if (equityFilter.getAwardWinnig() != null) {
			String awardValue = equityFilter.getAwardWinnig() ? "Y" : "N";
			List<String> awardList = new ArrayList<>();
			awardList.add(awardValue);
			appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_analyst_prof.analyst_awards", awardList);
		}

		if (equityFilter.getResearchCfa() != null) {
			String cfaValue = equityFilter.getResearchCfa() ? "Y" : "N";
			List<String> cfaList = new ArrayList<>();
			cfaList.add(cfaValue);
			appendFilterWithInClause(baseSqlSb, "ven_rsrch_rpt_analyst_prof.anayst_cfa_charter", cfaList);
		}

		baseSqlSb.append(" order by vendor.username asc,ven_rsrch_rpt_dtls.rep_date desc");
		return baseSqlSb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbsResearchReportDashboardResult findResearchReportDashboardData(String... params) throws RuntimeException {
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(SqlEnum.EQUITY_RESEARCH_DASHBOARD.valueOf());
			query.setString(0, params[0]);
			List<Object[]> rows = query.list();

			EquityResearchResultDashboard equityDashboradData = new EquityResearchResultDashboard();
			for (Object[] row : rows) {
				List<EquityResearchResultDashboard> results = new ArrayList<>();
				equityDashboradData.setCompanyName(row[0] != null ? row[0].toString() : "");
				equityDashboradData.setReportName(row[1] != null ? row[1].toString() : "");
				equityDashboradData.setReportSummary(row[2] != null ? row[2].toString() : "");
				if (row[3] != null) {
					String uploadReportPath = row[3].toString();
					String fileNameOnly = uploadReportPath.substring(uploadReportPath.lastIndexOf(File.separator) + 1);
					equityDashboradData.setReportFileName(fileNameOnly);
				} else {
					throw new RuntimeException("Report file must not be empty or null in DB !!");
				}
				results.add(equityDashboradData);
			}
			return equityDashboradData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResearchReportFilter getFilterValues() {
		return null;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 4, 3 };
		int min = 0;
		for (int i : arr) {
			min = i;
			if (i <= min) {
				min = i;
			}
		}
		System.out.println(min);
	}
}
