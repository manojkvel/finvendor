//package com.finvendor.server.stockpriceupdate.dao.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.finvendor.common.util.Pair;
//import com.finvendor.server.common.infra.parser.StockPrice;
//import org.hibernate.SQLQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
//import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
//import com.finvendor.server.common.commondao.ICommonDao;
//import com.finvendor.server.stockpriceupdate.dao.IStockPriceUpdateDao;
//
//@Repository
//public class StockPriceUpdateDaoImpl implements IStockPriceUpdateDao {
//
//	@Autowired
//	private ICommonDao commonDao;
//
//	@Override
//	public boolean updatePrice(StockCurrentPriceDTO stockCurrentPricePojo) throws RuntimeException {
//		boolean priceUpdateStatus = false;
//		String insertIntoHistoricalTableQuery = "INSERT INTO `stock_historical_prices` SELECT d.* FROM stock_current_prices d WHERE stock_id = ?";
//		String updateLTPInCurrentPriceTableQuery = "update stock_current_prices as t1 inner join (select close_price from stock_current_prices where stock_id = ?) as t2 set t1.last_trade_price = t2.close_price where t1.stock_id=?";
//		String updateCMPInCurrentPriceTableQuery = "update stock_current_prices set price_date=? , open_price=? , high_price=? , low_price=? , close_price=? where stock_id=?";
//
//		String stock_id_str = String.valueOf(stockCurrentPricePojo.getStock_id());
//		String price_date = stockCurrentPricePojo.getPrice_date();
//		String open_price = stockCurrentPricePojo.getOpen_price();
//		String high_price = stockCurrentPricePojo.getHigh_price();
//		String low_price = stockCurrentPricePojo.getLow_price();
//		String close_price = stockCurrentPricePojo.getClose_price();
//
//		// TDB rollback if any update failed
//		SQLQuery insertIntoHistoricalTableSqlQuery = commonDao.getNativeQuery(insertIntoHistoricalTableQuery,
//				new String[] { stock_id_str });
//		int executeUpdate1 = insertIntoHistoricalTableSqlQuery.executeUpdate();
//		if (executeUpdate1 == 0) {
//			throw new RuntimeException("Unable to update stock_historial_prices table");
//		}
//
//		SQLQuery updateLTPInCurrentPriceTableSqlQuery = commonDao.getNativeQuery(updateLTPInCurrentPriceTableQuery,
//				new String[] { stock_id_str, stock_id_str });
//		int executeUpdate2 = updateLTPInCurrentPriceTableSqlQuery.executeUpdate();
//		if (executeUpdate2 == 0) {
//			throw new RuntimeException("Unable to update stock_historial_prices table");
//		}
//
//		SQLQuery updateCMPInCurrentPriceTableSqlQuery = commonDao.getNativeQuery(updateCMPInCurrentPriceTableQuery,
//				new String[] { price_date, open_price, high_price, low_price, close_price, stock_id_str });
//		int executeUpdate3 = updateCMPInCurrentPriceTableSqlQuery.executeUpdate();
//		if (executeUpdate3 == 0) {
//			throw new RuntimeException("Unable to update stock_historial_prices table");
//		}
//
//		if (executeUpdate1 > 0 & executeUpdate2 > 0 & executeUpdate3 > 0) {
//			priceUpdateStatus = true;
//		}
//
//		// get ltp
//		return priceUpdateStatus;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public Map<String, List<ConsumerPriceAlertDTO>> fetchAllConsumerPriceAlert() {
//		SQLQuery query = commonDao.getNativeQuery("select * from company_price_alert", null);
//		List<Object[]> rows = query.list();
//		Map<String, List<ConsumerPriceAlertDTO>> restultMap = new HashMap<>();
//
//		for (Object[] row : rows) {
//			String companyId = row[1] != null ? row[1].toString().trim() : "";
//			String companyName = row[2] != null ? row[2].toString().trim() : "";
//			String userName = row[3] != null ? row[3].toString().trim() : "";
//			String cmpWhenPriceAlertWasSet = row[4] != null ? row[4].toString().trim() : "";
//			String dayMinPrice = row[5] != null ? row[5].toString().trim() : "";
//			String dayMaxPrice = row[6] != null ? row[6].toString().trim() : "";
//
//			String weekMinPrice = row[7] != null ? row[7].toString().trim() : "";
//			String weekMaxPrice = row[8] != null ? row[8].toString().trim() : "";
//
//			String monthMinPrice = row[9] != null ? row[9].toString().trim() : "";
//			String monthMaxPrice = row[10] != null ? row[10].toString().trim() : "";
//
//			String isResearchReport = row[11] != null ? row[11].toString().trim() : "";
//			String noTimeFrameMinPrice = row[12] != null ? row[12].toString().trim() : "";
//			String noTimeFrameMaxPrice = row[13] != null ? row[13].toString().trim() : "";
//			String currDate = row[14] != null ? row[14].toString().trim() : "";
//			ConsumerPriceAlertDTO dto = new ConsumerPriceAlertDTO();
//			dto.setCompanyId(companyId);
//			dto.setCompanyName(companyName);
//			dto.setUserName(userName);
//			dto.setCmpWhenPriceAlertSet(cmpWhenPriceAlertWasSet);
//			dto.setDayMinPrice(dayMinPrice);
//			dto.setDayMaxPrice(dayMaxPrice);
//			dto.setWeekMinPrice(weekMinPrice);
//			dto.setWeekMaxPrice(weekMaxPrice);
//			dto.setMonthMinPrice(monthMinPrice);
//			dto.setMonthMaxPrice(monthMaxPrice);
//			dto.setIsResearchReport(Boolean.valueOf(isResearchReport));
//			dto.setNoTimeFrameMinPrice(noTimeFrameMinPrice);
//			dto.setNoTimeFrameMaxPrice(noTimeFrameMaxPrice);
//			dto.setCurrDate(currDate);
//
//			List<ConsumerPriceAlertDTO> list = restultMap.get(userName);
//			if (list == null) {
//				List<ConsumerPriceAlertDTO> newList = new ArrayList<>();
//				newList.add(dto);
//				restultMap.put(userName, newList);
//			} else {
//				// append
//				list.add(dto);
//				restultMap.put(userName, list);
//			}
//		}
//
//		return restultMap;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public Map<String, String> findAllTickerFromDb()  {
//		SQLQuery query = commonDao.getNativeQuery("select company_id, ticker from rsch_sub_area_company_dtls", null);
//		List<Object[]> rows = query.list();
//		Map<String, String> companyIdAndisinCodeMap = new HashMap<>();
//		for (Object[] row : rows) {
//			String companyId = row[0] != null ? row[0].toString().trim() : "";
//			String ticker = row[1] != null ? row[1].toString().trim() : "";
//			companyIdAndisinCodeMap.put(ticker, companyId);
//		}
//		return companyIdAndisinCodeMap;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public Map<String, StockCurrentPriceDTO> fetchAllStockCurrentPrice() throws RuntimeException {
//		try {
//			SQLQuery query = commonDao.getNativeQuery("SELECT * FROM stock_current_prices ORDER BY stock_id", null);
//			List<Object[]> rows = query.list();
//			Map<String, StockCurrentPriceDTO> stockPriceMap = new LinkedHashMap<>();
//			for (Object[] row : rows) {
//				String stockId = row[0] != null ? row[0].toString().trim() : "";
//				String priceDate = row[2] != null ? row[2].toString().trim() : "";
//				String closePrice = row[6] != null ? row[6].toString().trim() : "";
//				String ltp = row[7] != null ? row[7].toString().trim() : "";
//				StockCurrentPriceDTO pojo = new StockCurrentPriceDTO();
//				pojo.setStock_id(Integer.parseInt(stockId));
//				pojo.setPrice_date(priceDate);
//				pojo.setClose_price(closePrice);
//				pojo.setLast_traded_price(ltp);
//				stockPriceMap.put(stockId, pojo);
//			}
//
//			return stockPriceMap;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public StockCurrentPriceDTO getLastWeekPrice(String stockId) throws RuntimeException {
//		try {
//			SQLQuery query = commonDao.getNativeQuery(
//					"SELECT * FROM stock_historical_prices where stock_id=? ORDER BY price_date DESC limit 5 ,1",
//					new String[] { stockId });
//			List<Object[]> rows = query.list();
//			StockCurrentPriceDTO pojo = getStockPricePojo(rows);
//			return pojo;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public StockCurrentPriceDTO getLastMonthPrice(String stockId) throws RuntimeException {
//		try {
//			SQLQuery query = commonDao.getNativeQuery(
//					"SELECT * FROM stock_historical_prices where stock_id=? ORDER BY price_date DESC limit 30 ,1",
//					new String[] { stockId });
//			List<Object[]> rows = query.list();
//			StockCurrentPriceDTO pojo = getStockPricePojo(rows);
//			return pojo;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private StockCurrentPriceDTO getStockPricePojo(List<Object[]> rows) {
//		StockCurrentPriceDTO pojo = new StockCurrentPriceDTO();
//		for (Object[] row : rows) {
//			String stockId = row[0] != null ? row[0].toString().trim() : "";
//			String priceDate = row[2] != null ? row[2].toString().trim() : "";
//			String closePrice = row[6] != null ? row[6].toString().trim() : "";
//			pojo.setStock_id(Integer.parseInt(stockId));
//			pojo.setPrice_date(priceDate);
//			pojo.setClose_price(closePrice);
//		}
//		return pojo;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public StockCurrentPriceDTO fetchStockPrice() throws RuntimeException {
//		try {
//			SQLQuery query = commonDao
//					.getNativeQuery("SELECT * FROM stock_current_prices ORDER BY stock_id limit 1 offset 0", null);
//			List<Object[]> rows = query.list();
//			StockCurrentPriceDTO pojo = new StockCurrentPriceDTO();
//			for (Object[] row : rows) {
//				String stockId = row[0] != null ? row[0].toString().trim() : "";
//				String priceDate = row[2] != null ? row[2].toString().trim() : "";
//				String closePrice = row[6] != null ? row[6].toString().trim() : "";
//
//				pojo.setStock_id(Integer.parseInt(stockId));
//				pojo.setPrice_date(priceDate);
//				pojo.setClose_price(closePrice);
//				break;
//			}
//
//			return pojo;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public String findIsin(String companyId) throws Exception {
//		SQLQuery query = commonDao.getNativeQuery("select company_id, isin_code from rsch_sub_area_company_dtls where company_id=?", new String[] { companyId });
//		List<Object[]> rows = query.list();
//		for (Object[] row : rows) {
//			return  row[1] != null ? row[1].toString().trim() : "";
//		}
//		return "";
//	}
//
//
//	public List<Pair<String,String>> findAllIsin() throws Exception {
//		SQLQuery query = commonDao.getNativeQuery("select company_id, isin_code from rsch_sub_area_company_dtls", null);
//		List<Object[]> rows = query.list();
//		List<Pair<String,String>> isinList= new ArrayList<>();
//		for (Object[] row : rows) {
//			String companyId = row[0] != null ? row[0].toString().trim() : "";
//			String isin = row[1] != null ? row[1].toString().trim() : "";
//			isinList.add(new Pair<>(companyId,isin));
//		}
//		return isinList;
//	}
//
//	@Override
//	public int updateStockPrice(String stockId, StockPrice stockPrice1,String priceDate) throws RuntimeException {
//		String open_price=stockPrice1.getOpen();
//		String high_price=stockPrice1.getHigh();
//		String low_price=stockPrice1.getLow();
//		String close_price=stockPrice1.getClose();
//
//		String insertIntoHistoricalTableQuery = "INSERT INTO `stock_historical_prices` SELECT d.* FROM stock_current_prices d WHERE stock_id = ?";
//		String updateLTPInCurrentPriceTableQuery = "update stock_current_prices as t1 inner join (select close_price from stock_current_prices where stock_id = ?) as t2 set t1.last_trade_price = t2.close_price where t1.stock_id=?";
//		String updateCMPInCurrentPriceTableQuery = "update stock_current_prices set price_date=? , open_price=? , high_price=? , low_price=? , close_price=? where stock_id=?";
//
//		SQLQuery insertIntoHistoricalTableSqlQuery = commonDao.getNativeQuery(insertIntoHistoricalTableQuery, new String[] { stockId });
//		int executeUpdate1 = insertIntoHistoricalTableSqlQuery.executeUpdate();
//		if (executeUpdate1 == 0) {
//			throw new RuntimeException("Unable to update stock_historial_prices table");
//		}
//
//		SQLQuery updateLTPInCurrentPriceTableSqlQuery = commonDao.getNativeQuery(updateLTPInCurrentPriceTableQuery, new String[] { stockId, stockId });
//		int executeUpdate2 = updateLTPInCurrentPriceTableSqlQuery.executeUpdate();
//		if (executeUpdate2 == 0) {
//			throw new RuntimeException("Unable to update stock_current_prices table");
//		}
//
//		updateLTPInCurrentPriceTableSqlQuery = commonDao.getNativeQuery(updateCMPInCurrentPriceTableQuery,
//				new String[] { priceDate, open_price, high_price, low_price, close_price, stockId });
//
//		int executeUpdate3  = updateLTPInCurrentPriceTableSqlQuery.executeUpdate();
//		if (executeUpdate3 == 0) {
//			throw new RuntimeException("Unable to update stock)current_prices table");
//		}
//
//		return 1;
//	}
//
//	@Override
//	public void updateCompanyDesc(String isin,String desc) throws RuntimeException {
//		SQLQuery nativeQuery = commonDao.getNativeQuery("update rsch_sub_area_company_dtls set company_desc=? where isin_code=? ",
//				new String[]{desc, isin});
//
//		nativeQuery.executeUpdate();
//	}
//}
