package com.finvendor.server.scheduler.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.scheduler.dao.ISchedulerDao;

@Repository
public class SchedulerDaoImpl implements ISchedulerDao {
	
	@Autowired
	private ICommonDao commonDao;

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
		
		//get ltp
		return priceUpdateStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<CompanyPriceAlertPojo>> fetchAllCompanyPriceAlert() {
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, StockCurrentPricePojo> fetchAllStockPrice() throws RuntimeException {
		try {
			SQLQuery query = commonDao.getNativeQuery(
					"SELECT * FROM stock_current_prices ORDER BY stock_id", null);
			List<Object[]> rows = query.list();
			Map<String, StockCurrentPricePojo> stockPriceMap = new LinkedHashMap<>();
			for (Object[] row : rows) {
				String stockId = row[0] != null ? row[0].toString().trim() : "";
				String priceDate = row[2] != null ? row[2].toString().trim() : "";
				String closePrice = row[6] != null ? row[6].toString().trim() : "";
				String ltp = row[7] != null ? row[7].toString().trim() : "";
				StockCurrentPricePojo pojo = new StockCurrentPricePojo();
				pojo.setStock_id(Integer.parseInt(stockId));
				pojo.setPrice_date(priceDate);
				pojo.setClose_price(closePrice);
				pojo.setLast_traded_price(ltp);
				stockPriceMap.put(stockId, pojo);
			}
			
			return stockPriceMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public StockCurrentPricePojo getLastWeekPrice(String stockId) throws RuntimeException {
		try {
			SQLQuery query = commonDao.getNativeQuery(
					"SELECT * FROM stock_historical_prices where stock_id=? ORDER BY price_date DESC limit 5 ,1", new String[] {stockId});
			List<Object[]> rows = query.list();
			StockCurrentPricePojo pojo = getStockPricePojo(rows);
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public StockCurrentPricePojo getLastMonthPrice(String stockId) throws RuntimeException {
		try {
			SQLQuery query = commonDao.getNativeQuery(
					"SELECT * FROM stock_historical_prices where stock_id=? ORDER BY price_date DESC limit 30 ,1",new String[] {stockId});
			List<Object[]> rows = query.list();
			StockCurrentPricePojo pojo = getStockPricePojo(rows);
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private StockCurrentPricePojo getStockPricePojo(List<Object[]> rows) {
		StockCurrentPricePojo pojo = new StockCurrentPricePojo();
		for (Object[] row : rows) {
			String stockId = row[0] != null ? row[0].toString().trim() : "";
			String priceDate = row[2] != null ? row[2].toString().trim() : "";
			String closePrice = row[6] != null ? row[6].toString().trim() : "";
			pojo.setStock_id(Integer.parseInt(stockId));
			pojo.setPrice_date(priceDate);
			pojo.setClose_price(closePrice);
		}
		return pojo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public StockCurrentPricePojo fetchStockPrice() throws RuntimeException {
		try {
			SQLQuery query = commonDao.getNativeQuery(
					"SELECT * FROM stock_current_prices ORDER BY stock_id limit 1 offset 0", null);
			List<Object[]> rows = query.list();
			StockCurrentPricePojo pojo = new StockCurrentPricePojo();
			for (Object[] row : rows) {
				String stockId = row[0] != null ? row[0].toString().trim() : "";
				String priceDate = row[2] != null ? row[2].toString().trim() : "";
				String closePrice = row[6] != null ? row[6].toString().trim() : "";
				
				pojo.setStock_id(Integer.parseInt(stockId));
				pojo.setPrice_date(priceDate);
				pojo.setClose_price(closePrice);
				break;
			}
			
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
