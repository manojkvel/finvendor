package com.finvendor.server.stockpriceupdate.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import com.finvendor.common.constant.AppConstant;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.infra.download.service.URLReader;
import com.finvendor.server.common.infra.parser.StockPrice;
import com.finvendor.server.common.infra.parser.service.IFileParser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
        String downloadDirectory = "/home/finvendo/dev";
//        String downloadDirectory = "d:\\ayush\\dev";
        long startTime = System.currentTimeMillis();
        System.out.println("**********************************************************************************");
        System.out.println("******* STOCK PRICE UPDATE - START");
        System.out.println("**********************************************************************************");

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayString = "";
        if (String.valueOf(day).length() == 1) {
            dayString = "0" + day;
        } else {
            dayString = String.valueOf(day);
        }
        SimpleDateFormat formatter = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);
        String priceDate = formatter.format(Calendar.getInstance().getTime());

        cal.set(year, month, day);
        java.util.Date d = new java.util.Date(cal.getTimeInMillis());
        String mmm = new SimpleDateFormat("MMM").format(d).toUpperCase();


        String bhavCsvFileName = "cm" + dayString + "" + mmm.toUpperCase() + "" + year + "bhav.csv";
        String bhavCsvZipFileName = "cm" + dayString + "" + mmm.toUpperCase() + "" + year + "bhav.csv.zip";

        //https://www.nseindia.com/content/historical/EQUITIES/2018/AUG/cm21AUG2018bhav.csv.zip
        String sUrl = "https://www.nseindia.com/content/historical/EQUITIES/" + year + "/" + mmm.toUpperCase() + "/" + bhavCsvZipFileName;
        System.out.println("******* STOCK PRICE UPDATE - BhavCopy NSE URL:" + sUrl);
        URL url = new URL(sUrl);


        String bhavZipFileName = "bhav.zip";
        String bhavZipFileDownloadPath = downloadDirectory + "/" + bhavZipFileName;
        System.out.println("******* STOCK PRICE UPDATE - bhavZipCopyDownloadPath=" + sUrl);

        /**
         * Download BhavCopy from NSE
         */
        URLReader.copyURLToFile(url, new File(bhavZipFileDownloadPath));

        /**
         * Upzip Bhav Copy
         */
        URLReader.unzip(bhavZipFileDownloadPath, downloadDirectory);
        System.out.println("******* STOCK PRICE UPDATE - bhavZip File us UnZipped successfully...");

        List<Pair<String, String>> allIsin = dao.findAllIsin();
        System.out.println("******* STOCK PRICE UPDATE - Fetched all ISIN from db successfully...");

        String bhavCsvFilePath = downloadDirectory + "/" + bhavCsvFileName;
        Map<String, StockPrice> priceMap = fileParser.parse(bhavCsvFilePath);
        System.out.println("******* STOCK PRICE UPDATE - Load price from BhavCopy successfully...");

        /**
         * UPDATE price in db
         */
        for (int i = 0; i < allIsin.size(); i++) {
            Pair<String, String> stringStringPair = allIsin.get(i);
            String stockId = stringStringPair.getElement1();
            String isin = stringStringPair.getElement2();
            StockPrice stockPrice = priceMap.get(isin);
            if (stockPrice != null) {
                int update = dao.updateStockPrice(stockId, stockPrice, priceDate);
                if (update == 1) {
                    logger.info("******** SUCCESS - Update price for this isin =" + isin + " and stockId=" + stringStringPair.getElement1());
                } else {
                    System.out.println("******** FAILED - Update price for this isin =" + isin + " and stockId=" + stringStringPair.getElement1());
                }
            } else {
                System.out.println("++++++ isin=" + isin + " does not found in Bhav file");
            }
        }
        System.out.println("**********************************************************************************");
        System.out.println("******* STOCK PRICE UPDATE - all price updated successfully...");
        System.out.println("**********************************************************************************");
        System.out.println("****STOCK PRICE UPDATE -  Time taken=" + (System.currentTimeMillis() - startTime) / 1000L + " secs");
    }

    @Override
    @Transactional(readOnly = false)
    public void updateCompanyDescription() throws Exception {
//        Map<String, String> descMap = new LinkedHashMap<>();
//        FileInputStream file=null;
//        HSSFWorkbook wb=null;
//        try {
////            String pathname="D:\\ayush\\1-Fin-vendor\\feature\\desc.xls";
//            String pathname="/home/finvendo/dev/desc.xls";
//            file = new FileInputStream(new File(pathname));
//
//            //Create Workbook instance holding reference to .xlsx file
//            wb = new HSSFWorkbook(file);
//
//            HSSFSheet sheet=wb.getSheetAt(0);
//            HSSFRow row;
//
//            Iterator<Row> rows = sheet.iterator();
//            while (rows.hasNext()) {
//                row =(HSSFRow) rows.next();
//                //For each row, iterate through all the columns
//                Iterator cells = row.cellIterator();
//
//                while (cells.hasNext()) {
//                    HSSFCell cell3 = (HSSFCell)cells.next();
//                    HSSFCell cell5 = (HSSFCell)cells.next();
//
//                    String desc ="";
//                    if (cell3.getCellType() == HSSFCell.CELL_TYPE_STRING)
//                    {
//                        desc = cell3.getStringCellValue();
//                    }
//                    else if(cell3.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
//                    {
//                        desc = cell3.getNumericCellValue()+"";
//                    }
//
//                    String isin="";
//                    if (cell5.getCellType() == HSSFCell.CELL_TYPE_STRING)
//                    {
//                        isin = cell5.getStringCellValue();
//                    }
//                    else if(cell5.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
//                    {
//                        isin = cell5.getNumericCellValue()+"";
//                    }
//
//                    if(desc.equals("Company Desc")){
//                       continue;
//                    }
//
//                    if(! "".equals(desc)) {
//                        descMap.put(isin, desc);
//                    }
//                }
//            }
//            List<Pair<String, String>> allIsin = dao.findAllIsin();
//            for (int i = 0; i < allIsin.size(); i++) {
//                Pair<String, String> stringStringPair = allIsin.get(i);
//                String isinFromDb = stringStringPair.getElement2();
//                String desc = descMap.get(isinFromDb);
//                if (desc != null) {
//                    dao.updateCompanyDesc(isinFromDb, desc);
//                }else{
//                    System.out.println("~~~~~~~~no desc found in xls file for isin="+isinFromDb);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(file!=null){
//                file.close();
//            }
//            if(wb!=null){
//                wb.close();
//            }
//        }
    }
}
