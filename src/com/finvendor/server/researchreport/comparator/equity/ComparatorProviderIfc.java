package com.finvendor.server.researchreport.comparator.equity;

import java.util.Comparator;
import java.util.List;

import com.finvendor.server.common.infra.comparator.ChainComparator;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

public interface ComparatorProviderIfc<T> {
	ChainComparator<T> getComparator(List<Comparator<EquityResearchResult>> type);
}
