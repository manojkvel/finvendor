package com.finvendor.server.homepage.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.CompanyWatchList;
import com.finvendor.model.ResearchSubAreaCompanyDetails;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.common.dao.infra.GenericDao;
import com.finvendor.server.homepage.dao.IHomePageSearchDao;
import com.finvendor.server.homepage.dto.CompnyData;
import com.finvendor.server.researchreport.dao.IResearchReportDao;

@Repository
public class HomePageSearchDaoImpl extends GenericDao<CompanyWatchList> implements IHomePageSearchDao {
	protected static Logger logger = LoggerFactory.getLogger(HomePageSearchDaoImpl.class);

	@Autowired
	private ICommonDao commonDao;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	private IResearchReportDao equityDao;

	@SuppressWarnings({ "unchecked" })
	@Override
	public String getHomePageSearchHint(Map<Object, Object> paramMap) throws RuntimeException {
		try {
			// String hql="from com.finvendor.model.CompanyWatchList where
			// user_name like:username";
			// Map<Object,Object> paramMap1=new LinkedHashMap<>();
			//
			// paramMap1.put("username", "amit");
			// org.hibernate.Query query1 =
			// commonDao.getNamedQuery(CompanyWatchList.COMPANY_WATCHLIST_BY_USER_NQ,paramMap1);
			// List<CompanyWatchList> ll=query1.list();

			org.hibernate.Query query = commonDao.getNamedQuery(ResearchSubAreaCompanyDetails.HOME_PAGE_SEARCH_NQ,
					paramMap);
			List<ResearchSubAreaCompanyDetails> researchSubAreaCompanyDetailsList = query.list();

			List<CompnyData> companyDataList = new ArrayList<>();
			for (ResearchSubAreaCompanyDetails researchSubAreaCompanyDetails : researchSubAreaCompanyDetailsList) {
				String companyName = researchSubAreaCompanyDetails.getCompanyName();
				String isinCode = researchSubAreaCompanyDetails.getIsinCode();
				String ticker = researchSubAreaCompanyDetails.getTicker();
				companyDataList.add(new CompnyData(companyName, isinCode, ticker));
			}
			return convertObjectToJson("searchOutput", companyDataList);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String convertObjectToJson(String key, Object companyDataList) throws IOException {
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		paramsMap.put(key, companyDataList);
		return JsonUtil.createJsonFromParamsMap(paramsMap);
	}
}
