package com.finvendor.server.companyprofile.pricealert.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.CompanyPriceAlert;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.common.dao.infra.GenericDao;
import com.finvendor.server.companyprofile.pricealert.dao.ICompanyPriceAlertDao;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyPriceAlerDaoImpl extends GenericDao<CompanyPriceAlert> implements ICompanyPriceAlertDao {

	@Autowired
	private ICommonDao commonDao;

	@Override
	public boolean addCompanyPriceAlert(CompanyPriceAlertPojo priceAlertPojo) throws RuntimeException {
		boolean addStatus;
		try {
			CompanyPriceAlert findById = findById(Integer.parseInt(priceAlertPojo.getCompanyId()));
			if (findById == null) {
				CompanyPriceAlert companyPriceAlertEntity = new CompanyPriceAlert();
				companyPriceAlertEntity.setCompany_id(Integer.parseInt(priceAlertPojo.getCompanyId()));
				companyPriceAlertEntity.setCompany_name(priceAlertPojo.getCompanyName());

				companyPriceAlertEntity.setUser_name(priceAlertPojo.getUserName());

				companyPriceAlertEntity.setDay_min_price(priceAlertPojo.getDayMinPrice());
				companyPriceAlertEntity.setDay_max_price(priceAlertPojo.getDayMaxPrice());

				companyPriceAlertEntity.setWeek_min_price(priceAlertPojo.getWeekMinPrice());
				companyPriceAlertEntity.setWeek_max_price(priceAlertPojo.getWeekMaxPrice());

				companyPriceAlertEntity.setMonth_min_price(priceAlertPojo.getMonthMinPrice());
				companyPriceAlertEntity.setMonth_max_price(priceAlertPojo.getMonthMaxPrice());

				companyPriceAlertEntity.setIs_research_price(String.valueOf(priceAlertPojo.getIsResearchReport().booleanValue()));

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

	@Override
	public boolean updateCompanyPriceAlert(CompanyPriceAlertPojo companyPriceAlertPojo) throws RuntimeException {
		try {
			CompanyPriceAlert entity = findById(Integer.parseInt(companyPriceAlertPojo.getCompanyId()));
			
			if(companyPriceAlertPojo.getCompanyName()!=null) {
				entity.setCompany_name(companyPriceAlertPojo.getCompanyName());
			}
			
			if(companyPriceAlertPojo.getUserName()!=null) {
				entity.setUser_name(companyPriceAlertPojo.getUserName());
			}
			
			if(companyPriceAlertPojo.getDayMinPrice()!=null) {
				entity.setDay_min_price(companyPriceAlertPojo.getDayMinPrice());
			}
			
			if(companyPriceAlertPojo.getDayMaxPrice()!=null) {
				entity.setDay_max_price(companyPriceAlertPojo.getDayMaxPrice());
			}
	
			if(companyPriceAlertPojo.getWeekMinPrice()!=null) {
				entity.setWeek_min_price(companyPriceAlertPojo.getWeekMinPrice());
			}
			
			if(companyPriceAlertPojo.getWeekMaxPrice()!=null) {
				entity.setWeek_max_price(companyPriceAlertPojo.getWeekMaxPrice());
			}
	
			if(companyPriceAlertPojo.getMonthMinPrice()!=null) {
				entity.setMonth_min_price(companyPriceAlertPojo.getMonthMinPrice());
			}
			
			if(companyPriceAlertPojo.getMonthMaxPrice()!=null) {
				entity.setMonth_max_price(companyPriceAlertPojo.getMonthMaxPrice());
			}
	
			if(companyPriceAlertPojo.getIsResearchReport() !=null) {
				entity.setIs_research_price(String.valueOf(companyPriceAlertPojo.getIsResearchReport().booleanValue()));
			}
			entity.setCurr_date(String.valueOf(Calendar.getInstance().getTimeInMillis()));
			saveOrUpdate(entity);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCompanyPriceAlerts(List<CompanyPriceAlertPojo> pojoList) throws RuntimeException {
		boolean deleteStatus=true;
		try {
			for(CompanyPriceAlertPojo pojo: pojoList) {
				String companyId = pojo.getCompanyId();
				CompanyPriceAlert companyPriceAlertEntity = findById(Integer.parseInt(companyId));
				delete(companyPriceAlertEntity);
			}
		} catch(Exception e) {
			deleteStatus=false;
		}
		return deleteStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompanyPriceAlertPojo findCompanyPriceAlert(String companyIdInputParam, String userNameInputParam)
			throws RuntimeException {
		SQLQuery query = commonDao.getNativeQuery(
				"SELECT * FROM finvendo_dev.company_price_alert where company_id=? and user_name=?",
				new String[] { companyIdInputParam, userNameInputParam });
		List<Object[]> rows = query.list();
		CompanyPriceAlertPojo pojo = new CompanyPriceAlertPojo();
		if (rows.size() == 0) {
			pojo.setCurrDate(null);
		}
		for (Object[] row : rows) {
			String companyId = row[0] != null ? row[0].toString().trim() : "";
			String companyName = row[1] != null ? row[1].toString().trim() : "";
			String userName = row[2] != null ? row[2].toString().trim() : "";

			String dayMinPrice = row[3] != null ? row[3].toString().trim() : "";
			String dayMaxPrice = row[4] != null ? row[4].toString().trim() : "";

			String weekMinPrice = row[5] != null ? row[5].toString().trim() : "";
			String weekMaxPrice = row[6] != null ? row[6].toString().trim() : "";

			String monthMinPrice = row[7] != null ? row[7].toString().trim() : "";
			String monthMaxPrice = row[8] != null ? row[8].toString().trim() : "";

			String isResearchReport = row[9] != null ? row[9].toString().trim() : "";

			String currDate = row[10] != null ? row[10].toString().trim() : "";

			pojo.setCompanyId(companyId);
			pojo.setCompanyName(companyName);
			pojo.setUserName(userName);
			pojo.setDayMinPrice(dayMinPrice);
			pojo.setDayMaxPrice(dayMaxPrice);
			pojo.setWeekMinPrice(weekMinPrice);
			pojo.setWeekMaxPrice(weekMaxPrice);
			pojo.setMonthMinPrice(monthMinPrice);
			pojo.setMonthMaxPrice(monthMaxPrice);
			pojo.setIsResearchReport(Boolean.valueOf(isResearchReport));
			pojo.setCurrDate(currDate);
		}
		return pojo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyPriceAlertPojo> findAllCompanyPriceAlerts(Map<Object, Object> paramMap) throws RuntimeException {
		try {
			org.hibernate.Query query = commonDao.getNamedQuery(CompanyPriceAlert.COMPANY_PRICEALERT_BY_USER_NQ,
					paramMap);
			List<CompanyPriceAlert> companyPriceAlertEnityList = query.list();
			List<CompanyPriceAlertPojo> pojoList = new ArrayList<>();

			for (CompanyPriceAlert companyPriceAlertEntity : companyPriceAlertEnityList) {
				CompanyPriceAlertPojo pojo = new CompanyPriceAlertPojo();
				pojo.setCompanyId(String.valueOf(companyPriceAlertEntity.getCompany_id()));
				pojo.setCompanyName(companyPriceAlertEntity.getCompany_name());
				pojo.setUserName(companyPriceAlertEntity.getUser_name());

				pojo.setDayMinPrice(companyPriceAlertEntity.getDay_min_price());
				pojo.setDayMaxPrice(companyPriceAlertEntity.getDay_max_price());

				pojo.setWeekMinPrice(companyPriceAlertEntity.getWeek_min_price());
				pojo.setWeekMaxPrice(companyPriceAlertEntity.getWeek_max_price());

				pojo.setMonthMinPrice(companyPriceAlertEntity.getMonth_min_price());
				pojo.setMonthMaxPrice(companyPriceAlertEntity.getMonth_max_price());

				pojo.setIsResearchReport(Boolean.valueOf(companyPriceAlertEntity.getIs_research_price()));
				pojo.setCurrDate(companyPriceAlertEntity.getCurr_date());
				pojoList.add(pojo);
			}
			return pojoList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Scheduler related
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public boolean updatePrice(StockCurrentPricePojo stockCurrentPricePojo) throws RuntimeException {
		boolean priceUpdateStatus = false;
		String insertIntoHistoricalTableQuery = "INSERT INTO `stock_historical_prices` SELECT d.* FROM stock_current_prices d WHERE stock_id = ?";
		String updateLTPInCurrentPriceTableQuery = "update stock_current_prices as t1 inner join (select close_price from stock_current_prices where stock_id = ?) as t2 set t1.last_trade_price = t2.close_price where t1.stock_id=?";
		String updateCMPInCurrentPriceTableQuery = "update stock_current_prices set price_date=? , open_price=? , high_price=? , low_price=? , close_price=? where stock_id=?";

		String stock_id_str = String.valueOf(stockCurrentPricePojo.getStock_id());
		String price_date = stockCurrentPricePojo.getPrice_date();
		String open_price = stockCurrentPricePojo.getOpen_price();
		String high_price = stockCurrentPricePojo.getHigh_price();
		String low_price = stockCurrentPricePojo.getLow_price();
		String close_price = stockCurrentPricePojo.getClose_price();
		// TDB rollback if any update failed
		SQLQuery insertIntoHistoricalTableSqlQuery = commonDao.getNativeQuery(insertIntoHistoricalTableQuery,
				new String[] { stock_id_str });
		int executeUpdate1 = insertIntoHistoricalTableSqlQuery.executeUpdate();
		if (executeUpdate1 == 0) {
			throw new RuntimeException("Unable to update stock_historial_prices table");
		}

		SQLQuery updateLTPInCurrentPriceTableSqlQuery = commonDao.getNativeQuery(updateLTPInCurrentPriceTableQuery,
				new String[] { stock_id_str, stock_id_str });
		int executeUpdate2 = updateLTPInCurrentPriceTableSqlQuery.executeUpdate();
		if (executeUpdate2 == 0) {
			throw new RuntimeException("Unable to update stock_historial_prices table");
		}

		SQLQuery updateCMPInCurrentPriceTableSqlQuery = commonDao.getNativeQuery(updateCMPInCurrentPriceTableQuery,
				new String[] { price_date, open_price, high_price, low_price, close_price, stock_id_str });
		int executeUpdate3 = updateCMPInCurrentPriceTableSqlQuery.executeUpdate();
		if (executeUpdate3 == 0) {
			throw new RuntimeException("Unable to update stock_historial_prices table");
		}

		if (executeUpdate1 > 0 & executeUpdate2 > 0 & executeUpdate3 > 0) {
			priceUpdateStatus = true;
		}
		return priceUpdateStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<CompanyPriceAlertPojo>> fetchAllStockCurrentPrices() {
		SQLQuery query = commonDao.getNativeQuery("select * from company_price_alert", null);
		List<Object[]> rows = query.list();
		Map<String, List<CompanyPriceAlertPojo>> restultMap = new HashMap<>();

		for (Object[] row : rows) {
			String companyId = row[0] != null ? row[0].toString().trim() : "";
			String companyName = row[1] != null ? row[1].toString().trim() : "";
			String userName = row[2] != null ? row[2].toString().trim() : "";

			String dayMinPrice = row[3] != null ? row[3].toString().trim() : "";
			String dayMaxPrice = row[4] != null ? row[4].toString().trim() : "";

			String weekMinPrice = row[5] != null ? row[5].toString().trim() : "";
			String weekMaxPrice = row[6] != null ? row[6].toString().trim() : "";

			String monthMinPrice = row[7] != null ? row[7].toString().trim() : "";
			String monthMaxPrice = row[8] != null ? row[8].toString().trim() : "";

			String isResearchReport = row[9] != null ? row[9].toString().trim() : "";

			String currDate = row[10] != null ? row[10].toString().trim() : "";
			CompanyPriceAlertPojo pojo = new CompanyPriceAlertPojo();
			pojo.setCompanyId(companyId);
			pojo.setCompanyName(companyName);
			pojo.setUserName(userName);
			pojo.setDayMinPrice(dayMinPrice);
			pojo.setDayMaxPrice(dayMaxPrice);
			pojo.setWeekMinPrice(weekMinPrice);
			pojo.setWeekMaxPrice(weekMaxPrice);
			pojo.setMonthMinPrice(monthMinPrice);
			pojo.setMonthMaxPrice(monthMaxPrice);
			pojo.setIsResearchReport(Boolean.valueOf(isResearchReport));
			pojo.setCurrDate(currDate);

			List<CompanyPriceAlertPojo> list = restultMap.get(userName);
			if (list == null) {
				List<CompanyPriceAlertPojo> newList = new ArrayList<>();
				newList.add(pojo);
				restultMap.put(userName, newList);
			} else {
				// append
				list.add(pojo);
				restultMap.put(userName, list);
			}
		}

		return restultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findAllTickerFromDb() throws Exception {
		SQLQuery query = commonDao.getNativeQuery("select company_id, ticker from rsch_sub_area_company_dtls", null);
		List<Object[]> rows = query.list();
		Map<String, String> companyIdAndisinCodeMap = new HashMap<>();
		for (Object[] row : rows) {
			String companyId = row[0] != null ? row[0].toString().trim() : "";
			String ticker = row[1] != null ? row[1].toString().trim() : "";
			companyIdAndisinCodeMap.put(ticker, companyId);
		}
		return companyIdAndisinCodeMap;
	}

}
