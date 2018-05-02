package com.finvendor.server.watchlist.companywatchlist.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.CompanyWatchList;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.common.dao.IStockCurrentPriceDao;
import com.finvendor.server.common.dao.infra.GenericDao;
import com.finvendor.server.watchlist.companywatchlist.dao.IWatchListDao;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyWatchListDaoImpl extends GenericDao<CompanyWatchList> implements IWatchListDao {

	@Autowired
	private IStockCurrentPriceDao stockCurrentPriceDao;

	@Autowired
	private ICommonDao commonDao;

	@Override
	public boolean addWatchList(CompanyWatchListPojo companyWatchListPojo) throws RuntimeException {
		boolean addStatus;
		try {
			CompanyWatchList findById = findById(companyWatchListPojo.getCompanyId());
			if (findById == null) {
				CompanyWatchList companyWatchListEntity = new CompanyWatchList();
				companyWatchListEntity.setCompany_id(companyWatchListPojo.getCompanyId());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyWatchListPojo> findAllWatchList(Map<Object, Object> paramMap) throws RuntimeException {
		List<CompanyWatchList> companyWatchListList = null;
		List<CompanyWatchListPojo> companyWatchListPojoList = null;
		try {
			org.hibernate.Query query = commonDao.getNamedQuery(CompanyWatchList.COMPANY_WATCHLIST_BY_USER_NQ,
					paramMap);
			companyWatchListList = query.list();
			companyWatchListPojoList = new ArrayList<>();

			for (CompanyWatchList companywatchListEntity : companyWatchListList) {
				Integer company_id = companywatchListEntity.getCompany_id();
				CompanyWatchListPojo companyWatchListPojo = new CompanyWatchListPojo();

				companyWatchListPojo.setCompanyId(company_id);
				companyWatchListPojo.setCompanyName(companywatchListEntity.getCompany_name());
				companyWatchListPojo.setUserName(companywatchListEntity.getUser_name());

				String closePriceAtTheTimeOfAddingWatchList = companywatchListEntity.getClose_price();

				StockCurrentPricePojo stockCurrentPricePojo = stockCurrentPriceDao.getStockCurrentPriceById(company_id);
				String new_close_price = stockCurrentPricePojo.getClose_price();

				String diff_close_price = String.valueOf(
						Float.parseFloat(new_close_price) - Float.parseFloat(closePriceAtTheTimeOfAddingWatchList));

				companyWatchListPojo.setCmp(closePriceAtTheTimeOfAddingWatchList);
				companyWatchListPojo.setNewCmp(new_close_price);
				companyWatchListPojo.setDiffCmp(diff_close_price);
				companyWatchListPojo.setCurrentDate(companywatchListEntity.getCurr_date());
				companyWatchListPojoList.add(companyWatchListPojo);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return companyWatchListPojoList;
	}
}
