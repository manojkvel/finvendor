package com.finvendor.server.alert.rsrcharea.stock.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.CompanyPriceAlert;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.alert.rsrcharea.stock.dao.IRsrchAreaStockPriceAlertDao;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.common.dao.infra.GenericDao;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class RsrchAreaStockPriceAlerDaoImpl extends GenericDao<CompanyPriceAlert>
		implements IRsrchAreaStockPriceAlertDao {

	@Autowired
	private ICommonDao commonDao;

	@Override
	public boolean addPriceAlert(CompanyPriceAlertPojo priceAlertPojo) throws RuntimeException {
		boolean addStatus;
		try {
			CompanyPriceAlert findById = findById(priceAlertPojo.getCompanyId());
			if (findById == null) {
				CompanyPriceAlert companyPriceAlertEntity = new CompanyPriceAlert();
				companyPriceAlertEntity.setCompany_id(priceAlertPojo.getCompanyId());
				companyPriceAlertEntity.setCompany_name(priceAlertPojo.getCompanyName());

				companyPriceAlertEntity.setUser_name(priceAlertPojo.getUserName());

				companyPriceAlertEntity.setMin_price(priceAlertPojo.getMinPrice());
				companyPriceAlertEntity.setMax_price(priceAlertPojo.getMaxPrice());

				companyPriceAlertEntity.setDay_price(priceAlertPojo.getDayPrice());
				companyPriceAlertEntity.setWeek_price(priceAlertPojo.getWeekPrice());
				companyPriceAlertEntity.setMonth_price(priceAlertPojo.getMonthPrice());

				companyPriceAlertEntity.setEnable_rsrch_price(priceAlertPojo.getEnableRsrchPrice());
				companyPriceAlertEntity.setCurr_date(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				saveOrUpdate(companyPriceAlertEntity);
				addStatus = true;
			} else {
				addStatus = false;
			}
			return addStatus;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyPriceAlertPojo> findAllRsrchAreaStockPriceAlerts(Map<Object, Object> paramMap)
			throws RuntimeException {
		try {
			org.hibernate.Query query = commonDao.getNamedQuery(CompanyPriceAlert.COMPANY_PRICEALERT_BY_USER_NQ, paramMap);
			List<CompanyPriceAlert> companyPriceAlertEnityList = query.list();
			List<CompanyPriceAlertPojo> pojoList = new ArrayList<>();

			for (CompanyPriceAlert companyPriceAlertEntity : companyPriceAlertEnityList) {
				CompanyPriceAlertPojo pojo=new CompanyPriceAlertPojo();
				pojo.setCompanyId(companyPriceAlertEntity.getCompany_id());
				pojo.setCompanyName(companyPriceAlertEntity.getCompany_name());
				pojo.setUserName(companyPriceAlertEntity.getUser_name());

				pojo.setMinPrice(companyPriceAlertEntity.getMin_price());
				pojo.setMaxPrice(companyPriceAlertEntity.getMax_price());

				pojo.setDayPrice(companyPriceAlertEntity.getDay_price());
				pojo.setWeekPrice(companyPriceAlertEntity.getWeek_price());
				pojo.setMonthPrice(companyPriceAlertEntity.getMonth_price());

				pojo.setEnableRsrchPrice(companyPriceAlertEntity.getEnable_rsrch_price());
				pojo.setCurrentDate(companyPriceAlertEntity.getCurr_date());
				pojoList.add(pojo);
			}
			return pojoList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
