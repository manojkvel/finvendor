package com.finvendor.server.stockpriceupdate.service.impl;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.finvendor.common.util.LogUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.infra.download.service.URLReader;
import com.finvendor.server.common.infra.parser.StockPrice;
import com.finvendor.server.common.infra.parser.service.IFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.server.stockpriceupdate.dao.IStockPriceUpdateDao;
import com.finvendor.server.stockpriceupdate.service.IStockPriceUpdateService;

@Service
public class StockPriceUpdateService implements IStockPriceUpdateService {
	private static Logger logger = LoggerFactory.getLogger(StockPriceUpdateService.class);
	@Autowired
	private IStockPriceUpdateDao dao;

	@Autowired
	private IFileParser fileParser;

	@Override
	@Transactional(readOnly = false)
	public boolean updatePrice(StockCurrentPriceDTO stockCurrentPricePojo) throws Exception {
		try {
			return dao.updatePrice(stockCurrentPricePojo);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, String> findAllTickerFromDb() throws Exception {
		try {
			return dao.findAllTickerFromDb();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, List<ConsumerPriceAlertDTO>> fetchConsumerPriceAlert() throws Exception {
		try {
			return dao.fetchAllConsumerPriceAlert();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, StockCurrentPriceDTO> fetchAllStockCurrentPrice() throws Exception {
		try {
			return dao.fetchAllStockCurrentPrice();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPriceDTO getLastWeekPrice(String stockId) throws Exception {
		try {
			return dao.getLastWeekPrice(stockId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPriceDTO getLastMonthPrice(String stockId) throws Exception {
		try {
			return dao.getLastMonthPrice(stockId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPriceDTO fetchStockPrice() throws Exception {
		try {
			return dao.fetchStockPrice();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public String findIsinFromDb(String companyId) throws Exception {
		try {
			return dao.findIsin(companyId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateStockPrice() throws Exception {
		//String downloadDirectory="/home/finvendo/dev";
		String downloadDirectory="d:\\ayush\\dev";
		long startTime=System.currentTimeMillis();
		logger.info("**********************************************************************************");
		logger.info("******* STOCK PRICE UPDATE - START");
		logger.info("**********************************************************************************");

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String dayString="";
		if(String.valueOf(day).length()==1){
			dayString="0"+day;
		}else{
			dayString=String.valueOf(day);
		}
		String priceDate=month+1+"/"+day+"/18";

		cal.set(year,month,day);
		java.util.Date d = new java.util.Date(cal.getTimeInMillis());
		String mmm = new SimpleDateFormat("MMM").format(d).toUpperCase();


		String bhavCsvFileName="cm"+dayString+""+mmm.toUpperCase()+""+year+"bhav.csv";
		String bhavCsvZipFileName="cm"+dayString+""+mmm.toUpperCase()+""+year+"bhav.csv.zip";

		//Example:https://www.nseindia.com/content/historical/EQUITIES/2018/AUG/cm07AUG2018bhav.csv.zip
		String sUrl = "https://www.nseindia.com/content/historical/EQUITIES/"+year+"/"+mmm.toUpperCase()+"/"+bhavCsvZipFileName;
		logger.info("******* STOCK PRICE UPDATE - BhavCopy NSE URL:"+sUrl);
		URL url = new URL(sUrl);


		String bhavZipFileName="bhav.zip";
		String bhavZipFileDownloadPath=downloadDirectory+"/"+bhavZipFileName;
		logger.info("******* STOCK PRICE UPDATE - bhavZipCopyDownloadPath="+sUrl);

		/**
		 * Download BhavCopy from NSE
		 */
		URLReader.copyURLToFile(url, new File(bhavZipFileDownloadPath));

		/**
		 * Upzip Bhav Copy
		 */
		URLReader.unzip(bhavZipFileDownloadPath,downloadDirectory);
		logger.info("******* STOCK PRICE UPDATE - bhavZip File us UnZipped successfully...");

        List<Pair<String, String>> allIsin = dao.findAllIsin();
		logger.info("******* STOCK PRICE UPDATE - Fetched all ISIN from db successfully...");

		String bhavCsvFilePath=downloadDirectory+"/"+bhavCsvFileName;
        Map<String, StockPrice> priceMap= fileParser.parse(bhavCsvFilePath);
		logger.info("******* STOCK PRICE UPDATE - Load price from BhavCopy successfully...");

		/**
		 * UPDATE price in db
		 */
        for (int i=0;i<allIsin.size();i++){
            Pair<String, String> stringStringPair = allIsin.get(i);
            String stockId=stringStringPair.getElement1();
            String isin=stringStringPair.getElement2();
            StockPrice stockPrice = priceMap.get(isin);
            if(stockPrice!=null){
                int update=dao.updateStockPrice(stockId,stockPrice,priceDate);
                if(update==1) {
					logger.info("******** SUCCESS - Update price for this isin =" + isin + " and stockId=" + stringStringPair.getElement1());
                }else{
					logger.info("******** FAILED - Update price for this isin =" + isin + " and stockId=" + stringStringPair.getElement1());
                }
            }else{
				logger.info("^^^^^^isin="+isin+" does not found in Bhav file");
            }
        }
		logger.info("**********************************************************************************");
		logger.info("******* STOCK PRICE UPDATE - all price updated successfully...");
		logger.info("**********************************************************************************");
		logger.info("****STOCK PRICE UPDATE -  Time taken="+(System.currentTimeMillis()-startTime)/1000L+" secs");
	}
}
