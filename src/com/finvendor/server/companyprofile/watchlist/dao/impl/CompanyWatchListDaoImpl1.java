package com.finvendor.server.companyprofile.watchlist.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.CompanyWatchList;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.common.commondao.IStockCurrentPriceDao;
import com.finvendor.server.companyprofile.watchlist.dao.ICompanyWatchListDao1;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyWatchListDaoImpl1 extends com.finvendor.server.common.commondao.GenericDao<CompanyWatchList>
		implements ICompanyWatchListDao1 {

	@Autowired
	private IStockCurrentPriceDao stockCurrentPriceDao;

	@Autowired
	private ICommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	public boolean addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws RuntimeException {
		boolean addStatus;
		try {
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("username", companyWatchListPojo.getUserName());
			paramMap.put("companyId", Integer.parseInt(companyWatchListPojo.getCompanyId()));
			org.hibernate.Query query = getEntityByNamedQuery(CompanyWatchList.COMPANY_ID__AND_USER_NAME_NAMED_QUERY,
					paramMap);

			List<CompanyWatchList> companyWatchListEntityList = query.list();

			if (companyWatchListEntityList.size() == 0) {
				CompanyWatchList companyWatchListEntity = new CompanyWatchList();
				companyWatchListEntity.setCompany_id(Integer.parseInt(companyWatchListPojo.getCompanyId()));
				companyWatchListEntity.setCompany_name(companyWatchListPojo.getCompanyName());
				companyWatchListEntity.setUser_name(companyWatchListPojo.getUserName());
				companyWatchListEntity.setClose_price(companyWatchListPojo.getCmp());
				companyWatchListEntity.setCurr_date((String.valueOf(Calendar.getInstance().getTimeInMillis())));
				save(companyWatchListEntity);
				addStatus = true;
			} else {
				addStatus = false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return addStatus;
	}

	private org.hibernate.Query getEntityByNamedQuery(String namedQuery, Map<Object, Object> paramMap) {
		org.hibernate.Query query = commonDao.getNamedQuery(namedQuery, paramMap);
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyWatchListPojo> findAllCompanyWatchList(Map<Object, Object> paramMap) throws RuntimeException {
		List<CompanyWatchList> companyWatchListList = null;
		List<CompanyWatchListPojo> companyWatchListPojoList = null;
		try {
			org.hibernate.Query query = commonDao.getNamedQuery(CompanyWatchList.COMPANY_WATCHLIST_BY_USER_NAMED_QUERY,
					paramMap);
			companyWatchListList = query.list();
			companyWatchListPojoList = new ArrayList<>();

			for (CompanyWatchList companywatchListEntity : companyWatchListList) {
				Integer company_id = companywatchListEntity.getCompany_id();
				CompanyWatchListPojo companyWatchListPojo = new CompanyWatchListPojo();

				companyWatchListPojo.setCompanyId(String.valueOf(company_id));
				companyWatchListPojo.setCompanyName(companywatchListEntity.getCompany_name());
				companyWatchListPojo.setUserName(companywatchListEntity.getUser_name());

				String closePriceAtTheTimeOfAddingWatchList = companywatchListEntity.getClose_price();

				StockCurrentPriceDTO stockCurrentPricePojo = stockCurrentPriceDao.getStockCurrentPriceById(company_id);

				String todaysCmpStr = stockCurrentPricePojo.getClose_price();
				float todaysCmp = Float.parseFloat(todaysCmpStr);
				String yesterdayCmpStr = stockCurrentPricePojo.getLast_traded_price();
				float yesterdayCmp = Float.parseFloat(yesterdayCmpStr);

				float cmpWhenWasAdded = Float.parseFloat(closePriceAtTheTimeOfAddingWatchList);

				float percentageChangeSinceAdded = (todaysCmp - cmpWhenWasAdded) * 100.0f / cmpWhenWasAdded;
				String percentageChangeSinceAddedStr = String.valueOf(percentageChangeSinceAdded);
				companyWatchListPojo.setCmp(closePriceAtTheTimeOfAddingWatchList);
				companyWatchListPojo.setNewCmp(todaysCmpStr);
				companyWatchListPojo.setPercentageChangeSinceAdded(percentageChangeSinceAddedStr);

				// Set todaysChange & todaysChangeInPercentage
				float todaysChange = todaysCmp - yesterdayCmp;
				String todaysChangeStr = String.valueOf(todaysChange);

				float todaysChangeInPercentage = (todaysCmp - yesterdayCmp) * 100.0f / yesterdayCmp;
				String todaysChangeInPercentageStr = String.valueOf(todaysChangeInPercentage);

				companyWatchListPojo.setTodaysChange(todaysChangeStr);
				companyWatchListPojo.setTodaysChangeInPercentage(todaysChangeInPercentageStr);

				companyWatchListPojo.setCurrDate(companywatchListEntity.getCurr_date());
				companyWatchListPojoList.add(companyWatchListPojo);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return companyWatchListPojoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteCompanyWatchList(List<CompanyWatchListPojo> pojoList) throws RuntimeException {
		boolean deleteStatus = true;
		try {
			Map<Object, Object> paramMap = new HashMap<>();
			for (CompanyWatchListPojo pojo : pojoList) {
				paramMap.put("username", pojo.getUserName());
				paramMap.put("companyId", Integer.parseInt(pojo.getCompanyId()));
				org.hibernate.Query query = getEntityByNamedQuery(
						CompanyWatchList.COMPANY_ID__AND_USER_NAME_NAMED_QUERY, paramMap);
				List<CompanyWatchList> companyWatchListEntityList = query.list();
				if (companyWatchListEntityList != null && companyWatchListEntityList.size() == 1) {
					delete(companyWatchListEntityList.get(0));
					paramMap.clear();
				} else {
					throw new Exception("Unable to delete company wath list!!");
				}
			}
		} catch (Exception e) {
			deleteStatus = false;
		}
		return deleteStatus;
	}
}
