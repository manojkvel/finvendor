package com.finvendor.server.homepagesearch.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.StringUtil;
import com.finvendor.model.CompanyWatchList;
import com.finvendor.server.homepagesearch.dao.IHomePageStockSearchDao;
import com.finvendor.server.homepagesearch.dto.staticpojo.CompanyWatchListPojo;
import com.finvendor.server.homepagesearch.service.IHomePageStockSearchService;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

@Service
public class HomePageStockSearchServiceImpl implements IHomePageStockSearchService {

	@Autowired
	IHomePageStockSearchDao dao;

	@Autowired
	@Qualifier(value = "equityResearchDao")
	IResearchReportDao equityDao;

	@Override
	@Transactional(readOnly = true)
	public String getHomePageSearchData(String searchKeyword, EquityResearchFilter filter) throws Exception {
		String companyData = "NA";
		String geo = ((EquityResearchFilter) filter).getGeo();
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			searchKeyword = searchKeyword + "%";
			paramMap.put("cid", Integer.parseInt(geo));
			paramMap.put("cname", searchKeyword);
			paramMap.put("isincode", searchKeyword);
			paramMap.put("ticker", searchKeyword);
			companyData = dao.getCompanyData(paramMap);
		} catch (RuntimeException e) {
			throw new Exception("Erro in service :: ", e);
		}
		return companyData;
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getCompanyProfileData(final String isinCode, final EquityResearchFilter filter) throws Exception {
		String mainQuery = StringUtil.replaceString(IHomePageStockSearchDao.companyProfileDataQuery,
				new HashMap<String, String>() {
					{
						put("COUNTRYID", ((EquityResearchFilter) filter).getGeo());
						put("ISINCODE", isinCode);
					}
				});

		return dao.getCompanyProfileData(mainQuery);
	}

	@Override
	@Transactional(readOnly = false)
	public int addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception {
		CompanyWatchList companyWatchListEntity = new CompanyWatchList();
		companyWatchListEntity.setCompany_id(companyWatchListPojo.getCompanyId());
		companyWatchListEntity.setCompany_name(companyWatchListPojo.getCompanyName());
		companyWatchListEntity.setUser_name(companyWatchListPojo.getUserName());
		companyWatchListEntity.setClose_price(companyWatchListPojo.getOldCmp());
		companyWatchListEntity.setCurr_date((String.valueOf(Calendar.getInstance().getTimeInMillis())));
		return dao.addCompanyWatchList(companyWatchListEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyWatchListPojo> findAllCompanyWatchListByUser(String userName) throws Exception {
		Map<Object, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("username", userName);
		return dao.getAllCompanyWatchList(paramMap);
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getCompanyRecordStatistics(final String isinCode, final ResearchReportFilter filter,
			String perPageMaxRecords) {
		String mainQuery = StringUtil.replaceString(IHomePageStockSearchDao.companyResearchReportQuery,
				new HashMap<String, String>() {
					{
						put("COUNTRYID", ((EquityResearchFilter) filter).getGeo());
						put("ISINCODE", isinCode);
					}
				});
		return equityDao.getRecordStatistics(mainQuery, filter, perPageMaxRecords);
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getCompanyResearchReportData(final String isinCode, final ResearchReportFilter filter,
			String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
		String mainQuery = StringUtil.replaceString(IHomePageStockSearchDao.companyResearchReportQuery,
				new HashMap<String, String>() {
					{
						put("COUNTRYID", ((EquityResearchFilter) filter).getGeo());
						put("ISINCODE", isinCode);
					}
				});
		return dao.getReasearchReportData(isinCode, mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);
	}

}
