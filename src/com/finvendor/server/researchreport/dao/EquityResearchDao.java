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
import com.finvendor.common.util.JsonUtil;
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		SQLQuery query;
		List<Object[]> rows;
		Map<String,EquityResearchResult> resultMap=new HashMap<>();
//		List<EquityResearchResult> results = new ArrayList<>();
		Map<String, EquityResearchResult> map1 = new HashMap<>();
		try {
			//Dirty Approach - need to improve later - Ayush
			// Company Level SQL
			String companyLevelSQL = "select rsch_sub_area_company_dtls.company_id,rsch_sub_area_company_dtls.company_name,rsch_area_stock_class.stock_class_name STYLE, market_cap_def.mcap_name M_CAP,research_sub_area.description SECTOR,stock_historial_prices.close_price AS CMP,stock_historial_prices.price_date AS PRC_DT,stock_current_info.pe AS PE,stock_current_info.3_yr_path_growth AS 3_YR_PAT_GRTH from rsch_sub_area_company_dtls,rsch_area_stock_class,market_cap_def,research_sub_area,stock_historial_prices,stock_current_info,country where rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = market_cap_def.company_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id and country.country_id = ? order by rsch_sub_area_company_dtls.company_id";
			query = this.sessionFactory.getCurrentSession().createSQLQuery(companyLevelSQL);
			applyFilter(query, equityFilter);
			rows = query.list();
			for (Object[] row : rows) {
				EquityResearchResult obj1 = new EquityResearchResult();
				String companyId = row[0] != null ? row[0].toString() : "";
				obj1.setCompany(row[1] != null ? row[1].toString() : "");
				obj1.setStyle(row[2] != null ? row[2].toString() : "");
				obj1.setMcap(row[3] != null ? row[3].toString() : "");
				obj1.setSector(row[4] != null ? row[4].toString() : "");
				obj1.setCmp(row[5] != null ? row[5].toString() : "");
				obj1.setPriceDate(row[6] != null ? row[6].toString() : "");
				obj1.setPe(row[7] != null ? row[7].toString() : "");
				obj1.set_3YrPatGrowth(row[8] != null ? row[8].toString() : "");
				map1.put(companyId, obj1);
			}

			// Vendor Level SQL // SqlEnum.EQUITY_RESEARCH.valueOf();
			String vendorLevelSQL = "SELECT distinct ven_rsrch_rpt_dtls.company_id,vendor.vendor_id,(vendor.username) BROKER,broker_analyst.broker_rank BROKER_RANK, ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE, ven_rsrch_rpt_dtls.target_price TGT_PRICE, ven_rsrch_rpt_dtls.price_at_recomm PRICE_AT_RECOMM, (ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm / ven_rsrch_rpt_dtls.price_at_recomm) * 100 UPSIDE, ven_rsrch_rpt_dtls.rsrch_upload_report RPT_NAME, ven_rsrch_rpt_dtls.rep_date REP_DT, ven_rsrch_rpt_analyst_prof.analyst_awards, ven_rsrch_rpt_analyst_prof.anayst_cfa_charter, ven_rsrch_rpt_analyst_prof.analyst_name,vendor.analystType FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor,broker_analyst where ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id=broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7 order by vendor.username,ven_rsrch_rpt_dtls.rep_date desc";
			query = this.sessionFactory.getCurrentSession().createSQLQuery(vendorLevelSQL);
			// applyFilter(query, equityFilter);
			rows = query.list();
			String tempVendorId = "";
			for (Object[] row : rows) {

				String vendorId = row[1] != null ? row[1].toString() : "";
				if (!tempVendorId.equals(vendorId)) {

					String companyId = row[0] != null ? row[0].toString() : "";
					EquityResearchResult equityResearchResult = map1.get(companyId);

					equityResearchResult.setVendorId(vendorId);
					equityResearchResult.setBroker(row[2] != null ? row[2].toString() : "");
					equityResearchResult.setBrokerRank(row[3] != null ? row[3].toString() : "");
					equityResearchResult.setRecommType(row[4] != null ? row[4].toString() : "");
					equityResearchResult.setTargetPrice(row[5] != null ? row[5].toString() : "");

					equityResearchResult.setPriceAtRecomm(row[6] != null ? row[6].toString() : "");
					equityResearchResult.setUpside(row[7] != null ? row[7].toString() : "");
					equityResearchResult.setReport(row[8] != null ? row[8].toString() : "");
					equityResearchResult.setResearchDate(row[9] != null ? row[9].toString() : "");
					equityResearchResult.setAwarded(row[10] != null ? row[10].toString() : "");
					equityResearchResult.setResearchedByCfa(row[11] != null ? row[11].toString() : "");

					equityResearchResult.setAnalystName(row[12] != null ? row[12].toString() : "");
					equityResearchResult.setAnalystType(row[13] != null ? row[13].toString() : "");
					
					Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VendorResearchReportsOffering.class);
					List<VendorResearchReportsOffering> list = criteria.list();
					Map<String,List<String>> vendorIdMap=new HashMap<>();
					for(VendorResearchReportsOffering vrro:list){
						
						String vId=vrro.getVendor().getId();
						List<String> exitsingLaunchedYrFromMap = vendorIdMap.get(vId);
						if(exitsingLaunchedYrFromMap==null){
							List<String> lYrList=new ArrayList<>();
							lYrList.add(vrro.getLaunchedYear());
							vendorIdMap.put(vId,lYrList);
						}else{
							exitsingLaunchedYrFromMap.add(vrro.getLaunchedYear());
						}
					}
					
					List<String> vendorSpecificLaunchedYearList = vendorIdMap.get(vendorId);
					Collections.sort(vendorSpecificLaunchedYearList,new Comparator<String>() {

						@Override
						public int compare(String o1, String o2) {
							return o1.compareTo(o2);
						}
					});
					//String sinceSQL = "select min(ven_rsrch_rpt_offering.launched_year) from ven_rsrch_rpt_offering where ven_rsrch_rpt_offering.vendor_id=?";
					String since = vendorSpecificLaunchedYearList.get(0);

					equityResearchResult.setSince(since);
					equityResearchResult.setCompanyId(companyId);
//					results.add(equityResearchResult);
					resultMap.put(companyId, equityResearchResult);
				}
				tempVendorId = vendorId;
			}
			return resultMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String preapreSql(String baseSql, EquityResearchFilter equityFilter) {
		StringBuffer sqlSb = new StringBuffer(baseSql);
		if (equityFilter.getGeo() != null) {
			sqlSb.append(" AND rsch_sub_area_company_dtls.country_id = ?");
		}

		// MCap Filter
		// if(equityFilter.getMcap()!=null) {
		// sqlSb.append("AND market_cap_def.mcap_name in ('Large Cap')");
		// }
		return sqlSb.toString();
	}

	private void applyFilter(SQLQuery query, EquityResearchFilter equityFilter) {

		// Filter GEO
		String geo = equityFilter.getGeo();
		if (geo != null) {
			query.setInteger(0, Integer.parseInt(equityFilter.getGeo()));
		}

		// Other filter
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
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(SqlEnum.EQUITY_RESEARCH_DASHBOARD.valueOf());
		// query.setString(0, params[0]);
		List<Object[]> rows = query.list();
		return null;
	}
}
