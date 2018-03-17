package com.finvendor.server.common.infra.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ayush on 16-03-2018
 */
public class ChainComparator<T> implements Comparator<T> {

	private List<Comparator<T>> comparators;

	@SafeVarargs
	public ChainComparator(Comparator<T>... comparators) {
		this.comparators = Arrays.asList(comparators);
	}

	@Override
	public int compare(T equityResearchResult1, T equityResearchResult2) {
		for (Comparator<T> comparator : comparators) {
			int result = comparator.compare(equityResearchResult1, equityResearchResult2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

	public List<Comparator<T>> getComparators() {
		return comparators;
	}

	public void setComparators(List<Comparator<T>> comparators) {
		this.comparators = comparators;
	}
	
}
