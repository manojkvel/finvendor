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
import com.finvendor.model.CompanyWatchList;
import com.finvendor.model.ResearchSubAreaCompanyDetails;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.common.dao.IStockCurrentPriceDao;
import com.finvendor.server.common.dao.infra.GenericDao;
import com.finvendor.server.common.dao.staticpojo.StockCurrentPricePojo;
import com.finvendor.server.homepagesearch.dao.IHomePageStockSearchDao;
import com.finvendor.server.homepagesearch.dto.CompanyProfileData;
import com.finvendor.server.homepagesearch.dto.CompnyData;
import com.finvendor.server.homepagesearch.dto.staticpojo.CompanyWatchListPojo;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;

@Repository
public class HomePageStockSearchDaoImpl extends GenericDao<CompanyWatchList> implements IHomePageStockSearchDao {
	protected static Logger logger = LoggerFactory.getLogger(HomePageStockSearchDaoImpl.class);

	@Autowired
	private ICommonDao commonDao;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "equityResearchDao")
	private IResearchReportDao equityDao;

	@Autowired
	private IStockCurrentPriceDao stockCurrentPriceDao;

	@SuppressWarnings({ "unchecked" })
	@Override
	public String getCompanyData(Map<Object, Object> paramMap) throws RuntimeException {
		try {
//			String hql="from com.finvendor.model.CompanyWatchList where user_name like:username";
//			Map<Object,Object> paramMap1=new LinkedHashMap<>();
//			
//			paramMap1.put("username", "amit");
//			org.hibernate.Query query1 = commonDao.getNamedQuery(CompanyWatchList.COMPANY_WATCHLIST_BY_USER_NQ,paramMap1);
//			List<CompanyWatchList> ll=query1.list();
			
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
			throw new RuntimeException("Error in DAO : ", e);
		}
	}

	private String convertObjectToJson(String key, Object companyDataList) throws IOException {
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		paramsMap.put(key, companyDataList);
		return JsonUtil.createJsonFromParamsMap(paramsMap);
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
				String companyId = row[0] != null ? row[0].toString() : "";
				String companyName = row[1] != null ? row[1].toString() : "";
				String mcap = row[2] != null ? row[2].toString() : "";
				String industry = row[3] != null ? row[3].toString() : "";
				String cmp = row[4] != null ? row[4].toString() : "";
				String ltp = row[5] != null ? row[5].toString() : "";// ltp=>Last Trade Price
				
				float cmpAsFloat = Float.parseFloat(cmp);
				float ltpAsFloat = Float.parseFloat(ltp);
				
				String absoluteLastChangedCmp = String.valueOf(cmpAsFloat - ltpAsFloat);
				String lastChangedCmpInPercentage = String.valueOf((cmpAsFloat - ltpAsFloat) / cmpAsFloat);
				
				paramsMap.put("companyProfileData", new CompanyProfileData(companyId, companyName, industry, mcap, cmp,
						absoluteLastChangedCmp, lastChangedCmpInPercentage));

			}
			paramsMap.put("summary", "xxxxx");
			companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
			return companyProfile;
		} catch (IOException e) {
			throw new RuntimeException("Error has occured while creating json for company data");
		}
	}

	@Override
	public int addCompanyWatchList(CompanyWatchList companyWatchList) throws Exception {
		return (int) save(companyWatchList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyWatchListPojo> getAllCompanyWatchList(Map<Object,Object> paramMap) {
		org.hibernate.Query query = commonDao.getNamedQuery(CompanyWatchList.COMPANY_WATCHLIST_BY_USER_NQ, paramMap);
		List<CompanyWatchList> companyWatchListList=query.list();
		
//		List<CompanyWatchList> findAll = findAll();
		List<CompanyWatchListPojo> companyWatchListPojoList = new ArrayList<>();

		for (CompanyWatchList companywatchListEntity : companyWatchListList) {
			Integer company_id = companywatchListEntity.getCompany_id();
			CompanyWatchListPojo companyWatchListPojo = new CompanyWatchListPojo();

			companyWatchListPojo.setCompanyId(company_id);
			companyWatchListPojo.setCompanyName(companywatchListEntity.getCompany_name());
			companyWatchListPojo.setUserName(companywatchListEntity.getUser_name());

			String closePriceAtTheTimeOfAddingWatchList = companywatchListEntity.getClose_price();
			
			StockCurrentPricePojo stockCurrentPricePojo = stockCurrentPriceDao
					.getStockCurrentPriceById(company_id);
			String new_close_price = stockCurrentPricePojo.getClose_price();
			
			String diff_close_price = String
					.valueOf(Float.parseFloat(new_close_price) - Float.parseFloat(closePriceAtTheTimeOfAddingWatchList));

			companyWatchListPojo.setOldCmp(closePriceAtTheTimeOfAddingWatchList);
			companyWatchListPojo.setNewCmp(new_close_price);
			companyWatchListPojo.setDiffCmp(diff_close_price);
			companyWatchListPojo.setCurrentDate(companywatchListEntity.getCurr_date());
			companyWatchListPojoList.add(companyWatchListPojo);
		}
		return companyWatchListPojoList;
	}

	@Override
	public String getReasearchReportData(String isinCode, String mainQuery, ResearchReportFilter filter,
			String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		try {
			Map<String, ? extends AbsResearchReportResult> equityData = equityDao.findResearchReportTableData(mainQuery,
					filter, pageNumber, perPageMaxRecords, sortBy, orderBy);

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
			throw new RuntimeException("Error has occured getReasearchReportData(), DAO Error:: " + e.getMessage());
		}
		return companyProfile;
	}
}
