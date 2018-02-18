package com.finvendor.server.researchreport.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
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
	public List<EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter) throws RuntimeException {
		String sql = applyFilter(SqlEnum.EQUITY_RESEARCH.valueOf(), filter);
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setInteger(0, 1);
			List<Object[]> rows = query.list();
			List<EquityResearchResult> results = new ArrayList<>();
			for (Object[] row : rows) {
				EquityResearchResult equityRR = new EquityResearchResult();
				equityRR.setCompanyId(row[0] != null ? row[0].toString() : "");
				equityRR.setCompany(row[1] != null ? row[1].toString() : "");
				equityRR.setStyle(row[2] != null ? row[2].toString() : "");
				equityRR.setMcap(row[3] != null ? row[3].toString() : "");
				equityRR.setSector(row[4] != null ? row[4].toString() : "");
				equityRR.setSubSector("NA");

				equityRR.setCmp(row[5] != null ? row[5].toString() : "");
				equityRR.setPriceDate(row[6] != null ? row[6].toString() : "");
				equityRR.setPe(row[7] != null ? row[7].toString() : "");
				equityRR.set_3YrPatGrowth(row[8] != null ? row[8].toString() : "");

				equityRR.setBroker(row[10] != null ? row[10].toString() : "");
				equityRR.setSince(row[10] != null ? row[10].toString() : "");
				equityRR.setAwarded(row[11] != null ? row[11].toString() : "");
				equityRR.setResearchedByCfa(row[12] != null ? row[12].toString() : "");
				String brokerRank = "Undefined";
				if (row[13] != null) {
					brokerRank = "" + row[13].toString().toCharArray()[0];
				}
				equityRR.setBrokerRank(brokerRank);
				equityRR.setBrokerRankLargeCap("NA");
				equityRR.setBrokerRankMidCap("NA");
				equityRR.setBrokerRankSmallCap("NA");

				equityRR.setRecommType(row[14] != null ? row[14].toString() : "");
				equityRR.setPriceAtRecomm(row[15] != null ? row[15].toString() : "");
				equityRR.setTargetPrice(row[16] != null ? row[16].toString() : "");
				equityRR.setUpside(row[17] != null ? row[17].toString() : "");

				equityRR.setReport(row[18] != null ? row[17].toString() : "");
				equityRR.setResearchDate(row[19] != null ? row[19].toString() : "");
				equityRR.setAnalystName(row[20] != null ? row[20].toString() : "");
				results.add(equityRR);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
}
