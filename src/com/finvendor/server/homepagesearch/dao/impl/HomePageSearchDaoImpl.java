package com.finvendor.server.homepagesearch.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.server.homepagesearch.dao.IHomePageSearchDao;
import com.finvendor.server.homepagesearch.dto.CompanyProfileData;
import com.finvendor.server.homepagesearch.dto.CompnyData;
import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

@Repository
public class HomePageSearchDaoImpl implements IHomePageSearchDao {
	protected static Logger logger = LoggerFactory.getLogger(HomePageSearchDaoImpl.class);

	@Autowired
	protected ICommonDao commonDao;

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier(value = "equityResearchDao")
	IResearchReportDao equityDao;


	@SuppressWarnings("unchecked")
	@Override
	public String getCompanyData(String hint) throws RuntimeException {
		SQLQuery query = commonDao.getSql(companyDataQuery.replace("?", "'" + hint + "%'"), null);
		List<Object[]> rows = query.list();
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		List<CompnyData> companyDataList = new ArrayList<>();
		for (Object[] row : rows) {
			String companyName = row[0] != null ? row[0].toString() : "";
			String isinCode = row[1] != null ? row[1].toString() : "";
			String ticker = row[2] != null ? row[2].toString() : "";
			companyDataList.add(new CompnyData(companyName, isinCode, ticker));
		}
		paramsMap.put("companySearchHint", companyDataList);
		try {
			return JsonUtil.createJsonFromParamsMap(paramsMap);
		} catch (IOException e) {
			throw new RuntimeException("Error has occured while creating json for company data");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCompanyProfileData(String mainQuery) throws RuntimeException {
		SQLQuery query = commonDao.getSql(mainQuery, null);
		List<Object[]> rows = query.list();
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		try {
			for (Object[] row : rows) {
				String companyName = row[0] != null ? row[0].toString() : "";
				String mcap = row[1] != null ? row[1].toString() : "";
				String industry = row[2] != null ? row[2].toString() : "";
				String cmp = row[3] != null ? row[3].toString() : "";
				paramsMap.put("companyProfileData", new CompanyProfileData(companyName, industry, mcap, cmp));

			}
			paramsMap.put("summary", "xxxxx");
			try {
				companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
			} catch (IOException e) {
				throw new RuntimeException("Error has occured while creating json for company data");
			}
			return companyProfile;
		} catch (Exception e) {
			throw new RuntimeException("Error has occured while creating json for company data");
		}
	}

	@Override
	public String getReasearchReportData(String isinCode, String mainQuery, ResearchReportFilter filter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		try {
			Map<String, ? extends AbsResearchReportResult> equityData = equityDao
					.findResearchReportTableData(mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);
			
			Collection<? extends AbsResearchReportResult> equityList = equityData.values();
			
			paramsMap.put("noOfAnalystReport", equityList.size());
			// Total Buy Recomm
			SQLQuery sqlQuery = commonDao.getSql(buyCountQuery, new String[] { "1", isinCode });
			Object object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalBuyRecomm", i);
			}

			// Total Sell Recomm
			sqlQuery = commonDao.getSql(sellCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalSellRecomm", i);
			}

			// Total Neutral Recomm
			sqlQuery = commonDao.getSql(neutralCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalNeutralRecomm", i);
			}

			// Average Target Price
			sqlQuery = commonDao.getSql(avgCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof Double) {
				Double i = (Double) object;
				paramsMap.put("averageTargetPrice", i);
			}

			paramsMap.put("equityResearch", equityList);
			companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
		} catch (Exception e) {
			throw new RuntimeException("Error has occured while creating json for company data");
		}
		return companyProfile;
	}

}
