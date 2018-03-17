package com.finvendor.server.researchreport.comparator.equity;

import java.util.Comparator;
import java.util.List;

import com.finvendor.server.common.infra.comparator.ChainComparator;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

public class ComparatorProvider implements ComparatorProviderIfc<EquityResearchResult> {

	@Override
	public ChainComparator<EquityResearchResult> getComparator(List<Comparator<EquityResearchResult>> comparators) {
		ChainComparator<EquityResearchResult> chain = new ChainComparator<EquityResearchResult>();
		chain.setComparators(comparators);
		return chain;
	}
}
