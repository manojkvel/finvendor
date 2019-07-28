package com.finvendor.api.markets.dao;

import com.finvendor.common.constant.AppConstant;
import com.finvendor.common.util.DateUtils;
import com.finvendor.model.Indice;
import com.finvendor.model.IndiceDetails;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Repository
public class NiftyIndicesFilePersist extends AbstractNiftyFilePersist<Indice> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NiftyIndicesFilePersist.class.getName());

    @Override
    @Transactional
    public Long persist(String niftyIndiceFilePath) {
        LOGGER.info("downloaded index filePath: {}", niftyIndiceFilePath);
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        int idCounter = 1;
        long totalBsePriceInserted = 0L;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);
            String indexDateAsPerFvFormat = formatter.format(Calendar.getInstance().getTime());
            br = new BufferedReader(new FileReader(niftyIndiceFilePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] niftyColumns = line.split(cvsSplitBy);
                String indexId = "";
                String indexName = niftyColumns[0];

                String openIndex = niftyColumns[2];
                String highIndex = niftyColumns[3];
                String lowIndex = niftyColumns[4];

                String closingIndex = "";
                if (!StringUtils.isEmpty(niftyColumns[5].trim()) && !niftyColumns[5].trim().equals("-")) {
                    closingIndex = String.valueOf(Double.parseDouble(niftyColumns[5].trim()));
                }

                String pointChange = "";
                if (!StringUtils.isEmpty(niftyColumns[6].trim()) && !niftyColumns[6].trim().equals("-")) {
                    pointChange = String.valueOf(Double.parseDouble(niftyColumns[6].trim()));
                }

                String percentChange = "";
                if (!StringUtils.isEmpty(niftyColumns[7].trim()) && !niftyColumns[7].trim().equals("-")) {
                    percentChange = String.valueOf(Double.parseDouble(niftyColumns[7].trim()));
                }

                String volume = niftyColumns[8];

                String turnoverInCrore = "";
                if (!StringUtils.isEmpty(niftyColumns[9].trim()) && !niftyColumns[9].trim().equals("-")) {
                    turnoverInCrore = String.valueOf(Double.parseDouble(niftyColumns[9].trim()));
                }

                String pe = "";
                if (!StringUtils.isEmpty(niftyColumns[10].trim()) && !niftyColumns[10].trim().equals("-")) {
                    pe = String.valueOf(Double.parseDouble(niftyColumns[10].trim()));
                }

                String pb = "";
                if (!StringUtils.isEmpty(niftyColumns[11].trim()) && !niftyColumns[11].trim().equals("-")) {
                    pb = String.valueOf(Double.parseDouble(niftyColumns[11].trim()));
                }

                String divYield = "";
                if (!StringUtils.isEmpty(niftyColumns[12].trim()) && !niftyColumns[12].trim().equals("-")) {
                    divYield = String.valueOf(Double.parseDouble(niftyColumns[12].trim()));
                }
                String type = "";
                String family = "NSE";
                Indice indice;
                indice = findById(idCounter);
                if (indice == null) {
                    indice = new Indice();
                    indice.setIndexId(indexId);
                    indice.setName(indexName);
                    indice.setType(type);
                    indice.setFamily(family);

                    IndiceDetails indiceDetails = new IndiceDetails();
                    indiceDetails.setDate(indexDateAsPerFvFormat);
                    indiceDetails.setOpen(openIndex);
                    indiceDetails.setHigh(highIndex);
                    indiceDetails.setLow(lowIndex);
                    indiceDetails.setClosing(closingIndex);
                    indiceDetails.setPointChange(pointChange);
                    indiceDetails.setPercentChange(percentChange);
                    indiceDetails.setVolume(volume);
                    indiceDetails.setTurnoverInCrore(turnoverInCrore);
                    indiceDetails.setPe(pe);
                    indiceDetails.setPb(pb);
                    indiceDetails.setDivYield(divYield);

                    indice.setIndiceDetails(indiceDetails);
                } else {
                    IndiceDetails indiceDetails = indice.getIndiceDetails();
                    indiceDetails.setDate(indexDateAsPerFvFormat);
                    indiceDetails.setOpen(openIndex);
                    indiceDetails.setHigh(highIndex);
                    indiceDetails.setLow(lowIndex);
                    indiceDetails.setClosing(closingIndex);
                    indiceDetails.setPointChange(pointChange);
                    indiceDetails.setPercentChange(percentChange);
                    indiceDetails.setVolume(volume);
                    indiceDetails.setTurnoverInCrore(turnoverInCrore);
                    indiceDetails.setPe(pe);
                    indiceDetails.setPb(pb);
                    indiceDetails.setDivYield(divYield);
                    indice.setIndiceDetails(indiceDetails);
                }
                save(indice);
                idCounter++;
                totalBsePriceInserted++;

                //Nifty50 price insertion into history table
                if ("Nifty 50".equals(indexName)) {
                    //let also store nifty50 price into nifty50History table after update new nifty 50 price
                    String currentDate = getDateForNifty50();
                    String insertQuery = "insert into nifty50_price_history(date,close) values(?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, currentDate);
                    sqlQuery.setParameter(1, closingIndex);
                    sqlQuery.executeUpdate();

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.warn("Unable to close buffered reader as it is null");
                }
            }
        }
        return totalBsePriceInserted;
    }

    private String getDateForNifty50() {
        String currentDay = DateUtils.getDayNumber();
        String currentMonth = DateUtils.getCurrentMonth();
        String currentYear = DateUtils.get2DigitCurrentYear();
        return currentDay + "-" + currentMonth + "-" + currentYear;
    }
}
