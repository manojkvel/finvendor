package com.finvendor.serverwebapi.resources.markets.dao;

import com.finvendor.model.Markets;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository
public class MarketsFilePersist extends AbstractMarketsFilePersist<Markets> {

    @Override
    @Transactional(readOnly = false)
    public Long persist(String fromFilePath) throws RuntimeException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        long totalPriceInserted=0L;
        try {
            br = new BufferedReader(new FileReader(fromFilePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] bhavColumns = line.split(cvsSplitBy);
                String symbol = bhavColumns[0];
                String series = bhavColumns[1];

                String open = bhavColumns[2];
                String high = bhavColumns[3];
                String low = bhavColumns[4];
                String close = bhavColumns[5];
                String last = bhavColumns[6];

                String prevClose = bhavColumns[7];
                String totTrdQty = bhavColumns[8];
                String totTrdVal = bhavColumns[9];
                String timestamp = bhavColumns[10];
                String totalTrades = bhavColumns[11];
                String isin = bhavColumns[12];

                Markets bhavPrice=new Markets();
                bhavPrice.setSymbol(symbol);
                bhavPrice.setSeries(series);
                bhavPrice.setOpen(open);
                bhavPrice.setHigh(high);
                bhavPrice.setLow(low);
                bhavPrice.setClose(close);
                bhavPrice.setLast(last);
                bhavPrice.setPrevColse(prevClose);
                bhavPrice.setTotalTradeQuantity(totTrdQty);
                bhavPrice.setTotalTradeValue(totTrdVal);
                bhavPrice.setTimeStamp(timestamp);
                bhavPrice.setTotalTrades(totalTrades);
                bhavPrice.setIsin(isin);
                save(bhavPrice);
                totalPriceInserted++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {

                }
            }
        }
        return totalPriceInserted;
    }
}
