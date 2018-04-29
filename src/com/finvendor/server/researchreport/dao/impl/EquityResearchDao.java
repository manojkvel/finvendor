package com.finvendor.server.researchreport.dao.impl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.impl.EquityResearchResult;
import com.finvendor.server.researchreport.util.ResearchReportUtil;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Repository
public class EquityResearchDao implements IResearchReportDao {
	protected static Logger logger = LoggerFactory.getLogger(EquityResearchDao.class);

	@Autowired
	private ICommonDao commonDao;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public String getRecordStatistics(String mainQuery, ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;

		String queryWithAppliedFilter = ResearchReportUtil.applyFilter(mainQuery, ResearchReportUtil.getFilteredQueryPart(equityFilter));
		SQLQuery query = commonDao.getSql(queryWithAppliedFilter, null);
		List<Object[]> rows = query.list();
		int totalRecords = rows.size();

		// Calculate Last page number
		int lastPageNumber = 0;
		int maxRecordCountPerPage = Integer.parseInt(perPageMaxRecords);
		if (maxRecordCountPerPage <= totalRecords) {
			int remainder = totalRecords % maxRecordCountPerPage;
			if (remainder == 0) {
				lastPageNumber = totalRecords / maxRecordCountPerPage;
			} else {
				lastPageNumber = (totalRecords / maxRecordCountPerPage) + 1;
			}
		} else {
			lastPageNumber = 1;
		}

		// Prepare Json result
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		paramsMap.put("firstPageNumber", 1);
		paramsMap.put("lastPageNumber", lastPageNumber);
		paramsMap.put("totalRecords", totalRecords);
		try {
			return JsonUtil.createJsonFromObject(paramsMap);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, EquityResearchResult> findResearchReportTableData(String mainQuery, ResearchReportFilter filter,
			String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
		Map<String, EquityResearchResult> resultMap = new LinkedHashMap<>();
		try {
			// Prepare data for Since from db
			Map<String, Integer> vendorIdWithLaunchedYearDataMap = ResearchReportUtil.prepareVendorSinceData(sessionFactory, sortBy, orderBy);

			// Prepare brokerRank data from db
			List<ResearchReportUtil.BrokerRankInfo> brokerRankData = ResearchReportUtil.getBrokerRankData(commonDao, ResearchReportUtil.brokerRankQuery, orderBy);

			// Apply filter in main query
			String queryWithAppliedFilter = ResearchReportUtil.applyFilter(mainQuery, ResearchReportUtil.getFilteredQueryPart(equityFilter));

			// Apply OrderBy
			String applyOrderBy = ResearchReportUtil.applyOrderBy(sortBy, orderBy);

			// Apply Pagination
			String applyPagination = ResearchReportUtil.applyPagination(pageNumber, perPageMaxRecords);

			// Prepare final query
			String finalMainQuery = new StringBuilder(500).append(queryWithAppliedFilter).append(applyOrderBy).append(applyPagination).toString();

			// Execute Query
			SQLQuery query = commonDao.getSql(finalMainQuery, null);
			List<Object[]> rows = query.list();

			// Process Result
			for (Object[] row : rows) {
				EquityResearchResult equityResult = new EquityResearchResult();
				equityResult.setCompanyId(row[0] != null ? row[0].toString() : "");
				equityResult.setCompany(row[1] != null ? row[1].toString() : "");
				equityResult.setStyle(row[3] != null ? row[3].toString() : "");
				equityResult.setMcap(row[4] != null ? row[4].toString() : "");
				equityResult.setSector(row[5] != null ? row[5].toString() : "");
				equityResult.setCmp(row[6] != null ? row[6].toString() : "");

				equityResult.setPriceDate(String.valueOf(ResearchReportUtil.convertStringToTimestamp(row[7] != null ? row[7].toString() : "")));
				equityResult.setPe(row[8] != null ? row[8].toString() : "");
				equityResult.set_3YrPatGrowth(row[9] != null ? row[9].toString() : "");

				String productId = row[11] != null ? row[11].toString() : "";
				equityResult.setProductId(productId);

				equityResult.setBroker(row[12] != null ? row[12].toString() : "");
				equityResult.setRecommType(row[13] != null ? row[13].toString() : "");

				equityResult.setTargetPrice(row[14] != null ? row[14].toString() : "");
				equityResult.setPriceAtRecomm(row[15] != null ? row[15].toString() : "");
				equityResult.setUpside(row[16] != null ? row[16].toString() : "");

				String reportName = row[17] != null ? row[17].toString() : "";
				reportName = reportName.substring(reportName.lastIndexOf("/") + 1);
				equityResult.setReport(reportName);

				String researchDate = row[18] != null ? row[18].toString() : "";
				long researchDateAsTimeStamp = ResearchReportUtil.convertStringToTimestamp(researchDate);
				equityResult.setResearchDate(String.valueOf(researchDateAsTimeStamp));

				String awarded;
				if (row[19] != null) {
					if (row[19].toString().isEmpty()) {
						awarded = "N";
					} else {
						awarded = row[19].toString();
					}
				} else {
					awarded = "NA";
				}
				equityResult.setAwarded(awarded);

				String researchedByCfa = "";
				if (row[20] != null) {
					if (row[20].toString().isEmpty()) {
						researchedByCfa = "N";
					} else {
						researchedByCfa = row[20].toString();
					}
				} else {
					researchedByCfa = "NA";
				}
				equityResult.setResearchedByCfa(researchedByCfa);

				equityResult.setAnalystName(row[21] != null ? row[21].toString() : "");
				equityResult.setAnalystType(row[22] != null ? row[22].toString() : "");

				// Since
				String vendorId = row[23] != null ? row[23].toString() : "";

				Pair<Integer, String> calculatedSince = ResearchReportUtil.calculateSinceAndYrOfIncorp(vendorIdWithLaunchedYearDataMap, vendorId);
				Integer since = calculatedSince.getElement1();
				equityResult.setSince(String.valueOf(since));

				// Year Of InCorporation
				String yrOfInCorp = calculatedSince.getElement2();
				equityResult.setYrOfInCorp(yrOfInCorp);

				equityResult.setVendorName(row[25] != null ? row[25].toString() : "");
				equityResult.setReportDesc(row[26] != null ? row[26].toString() : "");

				// Broker Rank
				Map<String, String> brokerRanks = ResearchReportUtil.getBrokerRank(brokerRankData, vendorId, equityFilter);
				equityResult.setBrokerRank(brokerRanks);

				// Set Current Page number
				equityResult.setPageNumber(pageNumber);
				resultMap.put(productId, equityResult);
			}

			// #BrokerYear Of Incorporation filter applied
			if (equityFilter.getBrokerYrOfInCorp() != null) {
				Map<String, EquityResearchResult> filteredYrOfInCorpResultMap = ResearchReportUtil.applyFilterForYearOfInCorp(equityFilter, resultMap);
				return filteredYrOfInCorpResultMap;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return resultMap;
	}
}
