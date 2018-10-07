package com.finvendor.server.bhavprice.dto.impl;

import com.finvendor.server.bhavprice.dto.IBhavPriceDetailsDao;
import com.finvendor.server.common.infra.download.service.URLReader;
import com.finvendor.server.common.infra.parser.StockPrice;
import com.finvendor.server.common.infra.parser.service.IFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Repository
public class BhavPriceDetailsDao implements IBhavPriceDetailsDao {
    private static final Logger logger = LoggerFactory.getLogger(BhavPriceDetailsDao.class.getName());

    @Autowired
    private IFileParser fileParser;

    @Override
    public String getRecordStats(String type, String subType, String perPageMaxRecords) throws RuntimeException {
        return null;
    }

    @Override
    public String getBhavCopyPriceDetails(String type, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        String downloadDirectory = "/home/finvendo/dev";
//        String downloadDirectory = "d:\\ayush\\dev";
        try {
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
             * STEP-1 Download Bhav Copy from NSE
             */
            URLReader.copyURLToFile(url, new File(bhavZipFileDownloadPath));

            /**
             * STEP-2 Upzip Bhav Copy
             */
            URLReader.unzip(bhavZipFileDownloadPath, downloadDirectory);

            /**
             * STEP-3 Parse Bhav Copy Csv
             */
            String bhavCsvFilePath = downloadDirectory + "/" + bhavCsvFileName;
            Map<String, StockPrice> bhavCopyPriceDetailsMap = fileParser.parse(bhavCsvFilePath);

        } catch (Exception e) {

        }
        return null;
    }
}
