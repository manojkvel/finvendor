package com.finvendor.server.common.infra.parser.service.impl;

import com.finvendor.server.common.infra.parser.StockPrice;
import com.finvendor.server.common.infra.parser.service.AbstractFileParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BhavCopyCSVFileParser extends AbstractFileParser {
    @Override
    public Map<String, StockPrice> parse(String fileName) throws Exception {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Map<String, StockPrice> stockPriceMap = new HashMap<>();
        try {

            br = new BufferedReader(new FileReader(fileName));
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

                stockPriceMap.put(isin, new StockPrice(symbol, series, open, high, low, close, last, prevClose, totTrdQty, totTrdVal, timestamp, totalTrades, isin));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stockPriceMap;
    }
}
