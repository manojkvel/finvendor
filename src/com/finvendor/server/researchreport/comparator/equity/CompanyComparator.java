package com.finvendor.server.researchreport.comparator.equity;

import java.util.Comparator;

import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

public class CompanyComparator implements Comparator<EquityResearchResult>{

	@Override
	public int compare(EquityResearchResult equityResearchResult1, EquityResearchResult equityResearchResult2) {
		return equityResearchResult1.getCompany().compareTo(equityResearchResult2.getCompany());
	}
}
