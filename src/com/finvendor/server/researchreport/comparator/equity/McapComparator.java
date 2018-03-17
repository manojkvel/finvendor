package com.finvendor.server.researchreport.comparator.equity;

import java.util.Comparator;

import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

public class McapComparator implements Comparator<EquityResearchResult>{

	@Override
	public int compare(EquityResearchResult equityResearchResult1, EquityResearchResult equityResearchResult2) {
		return equityResearchResult1.getMcap().compareTo(equityResearchResult2.getMcap());
	}

}
