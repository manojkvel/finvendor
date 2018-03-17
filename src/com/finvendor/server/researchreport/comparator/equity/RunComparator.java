package com.finvendor.server.researchreport.comparator.equity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.finvendor.server.common.infra.comparator.ChainComparator;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

public class RunComparator {
	public static void main(String[] args) {
		EquityResearchResult res1 = new EquityResearchResult();
		res1.setCompany("company1");
		res1.setStyle("Blend");
		res1.setMcap("Mid");

		EquityResearchResult res2 = new EquityResearchResult();
		res2.setCompany("company3");
		res2.setStyle("Growth");
		res2.setMcap("Mid");

		EquityResearchResult res3 = new EquityResearchResult();
		res3.setCompany("company2");
		res3.setStyle("Income");
		res3.setMcap("Large");

		EquityResearchResult res4 = new EquityResearchResult();
		res4.setCompany("company1");
		res4.setStyle("Blend");
		res4.setMcap("Value");

		List<EquityResearchResult> list = new ArrayList<>();
		list.add(res1);
		list.add(res2);
		list.add(res3);
		list.add(res4);
		System.out.println("*** Before sorting:");

		for (EquityResearchResult equityResearchResult : list) {
			System.out.println(equityResearchResult.getCompany() + ":" + equityResearchResult.getStyle() + ":"
					+ equityResearchResult.getMcap());
		}
		ComparatorProviderIfc<EquityResearchResult> chain = new ComparatorProvider();
		List<Comparator<EquityResearchResult>> compatorList = new ArrayList<>();
		String[] type = new String[] { "company", "style", "mcap" };
		for (String str : type) {
			switch (str) {
				case "comapny":
					compatorList.add(new CompanyComparator());
					break;
				case "style":
					compatorList.add(new StyleComparator());
					break;
				case "mcap":
					compatorList.add(new McapComparator());
					break;
			}
		}
		ChainComparator<EquityResearchResult> chainComparator = chain.getComparator(compatorList);
		// ChainComparator<EquityResearchResult> chainComparator = new
		// ChainComparator<EquityResearchResult>(
		// new CompanyComparator()
		// ,new StyleComparator()
		// ,new McapComparator()
		// );
		Collections.sort(list, chainComparator);

		System.out.println("\n*** After sorting:");

		for (EquityResearchResult equityResearchResult : list) {
			System.out.println(equityResearchResult.getCompany() + ":" + equityResearchResult.getStyle() + ":"
					+ equityResearchResult.getMcap());
		}
	}

}
