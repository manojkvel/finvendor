package com.finvendor.server.researchreport.dao.ifc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

/**
 * ResearchReportDao abstract interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class AbsResearchReportDao implements IResearchReportDao {
	protected static Logger logger = LoggerFactory.getLogger(AbsResearchReportDao.class);

	@Autowired
	protected SessionFactory sessionFactory;

	public AbsResearchReportDao() {
	}

	protected void appendFilterWithInClause(StringBuffer sqlSb, String filterCondition, List<String> inValues) {
		StringBuffer inValueSb = new StringBuffer(100);
		inValueSb.append("(");
		for (String inValue : inValues) {
			inValueSb.append("'").append(inValue).append("'").append(",");
		}
		inValueSb.deleteCharAt(inValueSb.toString().length() - 1);
		inValueSb.append(")");
		sqlSb.append(" AND ").append(filterCondition).append(" IN").append(inValueSb);
	}
	
	// Dummy data
	protected List<EquityResearchResult> getEquityDummyData() {
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
