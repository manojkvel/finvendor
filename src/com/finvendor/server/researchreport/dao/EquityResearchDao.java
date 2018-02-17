package com.finvendor.server.researchreport.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.enums.SqlQueryEnum;
import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EquityResearchResult> findResearchResult(ResearchReportFilter filter) throws RuntimeException {
		String sql = applyFilter(SqlQueryEnum.EQUITY_RESEARCH.getSqlQuery(), filter);
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setInteger(0, 1);
			List<Object[]> rows = query.list();
			List<EquityResearchResult> results = new ArrayList<>();
			for (Object[] row : rows) {
				EquityResearchResult equityRR = new EquityResearchResult();
				equityRR.setCompany(row[0] != null ? row[0].toString() : "");
				equityRR.setStyle(row[1] != null ? row[1].toString() : "");
				equityRR.setMcap(row[2] != null ? row[2].toString() : "");
				equityRR.setSector(row[3] != null ? row[3].toString() : "");
				equityRR.setSubSector("NA");

				equityRR.setCmp(row[4] != null ? row[4].toString() : "");
				equityRR.setPriceDate(row[5] != null ? row[5].toString() : "");
				equityRR.setPe(row[6] != null ? row[6].toString() : "");
				equityRR.set_3YrPatGrowth(row[7] != null ? row[7].toString() : "");

				equityRR.setBroker(row[8] != null ? row[8].toString() : "");
				equityRR.setSince(row[9] != null ? row[9].toString() : "");
				equityRR.setAwarded(row[10] != null ? row[10].toString() : "");
				equityRR.setResearchedByCfa(row[11] != null ? row[11].toString() : "");

				equityRR.setBrokerRank("NA");
				equityRR.setBrokerRankLargeCap("NA");
				equityRR.setBrokerRankMidCap("NA");
				equityRR.setBrokerRankSmallCap("NA");

				equityRR.setRecommType(row[12] != null ? row[12].toString() : "");
				equityRR.setTargetPrice(row[13] != null ? row[13].toString() : "");
				equityRR.setPriceAtRecomm("NA");
				equityRR.setUpside(row[14] != null ? row[14].toString() : "");

				equityRR.setReport(row[15] != null ? row[15].toString() : "");
				equityRR.setResearchDate(row[16] != null ? row[16].toString() : "");
				equityRR.setAnalystName(row[17] != null ? row[17].toString() : "");
				results.add(equityRR);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unused")
	private List<EquityResearchResult> getEquityDummyData() {
		List<EquityResearchResult> results = new ArrayList<>();
		EquityResearchResult equityResearchResult1 = new EquityResearchResult();
		EquityResearchResult equityResearchResult2 = new EquityResearchResult();
		equityResearchResult1.setCompany("company1");
		equityResearchResult1.setStyle("Growth");
		equityResearchResult1.setMcap("largeCap");
		equityResearchResult1.setSector("Finance");
		equityResearchResult1.setSubSector("subSector1");

		equityResearchResult1.setBroker("broker1");
		equityResearchResult1.setSince("2001");
		equityResearchResult1.setAwarded("Y");
		equityResearchResult1.setResearchedByCfa("Y");

		equityResearchResult1.setBrokerRank("3Star");
		equityResearchResult1.setBrokerRankLargeCap("largeCap1");
		equityResearchResult1.setBrokerRankMidCap("midCap1");
		equityResearchResult1.setBrokerRankSmallCap("smallCap1");

		equityResearchResult1.setCmp("cmp1");
		equityResearchResult1.setPriceDate("priceDate1");
		equityResearchResult1.setPe("PE1");
		equityResearchResult1.set_3YrPatGrowth("_3YrPatGrowth1");

		equityResearchResult1.setRecommType("recommType1");
		equityResearchResult1.setTargetPrice("targetPrice1");
		equityResearchResult1.setPriceAtRecomm("priceAtRecomm1");
		equityResearchResult1.setUpside("upside1");

		equityResearchResult1.setReport("report1");
		equityResearchResult1.setResearchDate("researchDate1");
		equityResearchResult1.setAnalystName("analyst1,analyst2");

		results.add(equityResearchResult1);

		// 2
		equityResearchResult2.setCompany("company1");
		equityResearchResult2.setStyle("Growth");
		equityResearchResult2.setMcap("largeCap");
		equityResearchResult2.setSector("finance");
		equityResearchResult2.setSubSector("subSector1");

		equityResearchResult2.setBroker("broker2");
		equityResearchResult2.setSince("2001");
		equityResearchResult2.setAwarded("Y");
		equityResearchResult2.setResearchedByCfa("Y");

		equityResearchResult2.setBrokerRank("4star");
		equityResearchResult2.setBrokerRankLargeCap("brokerRankLargeCap1");
		equityResearchResult2.setBrokerRankMidCap("brokerRankMidCap1");
		equityResearchResult2.setBrokerRankSmallCap("brokerRankSmallCap1");

		equityResearchResult2.setCmp("526.2");
		equityResearchResult2.setPriceDate("10/Oct/2017");
		equityResearchResult2.setPe("PE2");
		equityResearchResult2.set_3YrPatGrowth("_3YrPatGrowth2");

		equityResearchResult2.setRecommType("Buy");
		equityResearchResult2.setTargetPrice("564.0");
		equityResearchResult2.setPriceAtRecomm("priceAtRecomm1");
		equityResearchResult2.setUpside("568.64");

		equityResearchResult2.setReport("report2.pdf");
		equityResearchResult2.setResearchDate("15/Sep/2017");
		equityResearchResult2.setAnalystName("analyst1,analyst2");

		results.add(equityResearchResult2);
		return results;
	}
}
