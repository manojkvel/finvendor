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
public class CsvFileParser extends AbstractFileParser {
    @Override
    public Map parse(String fileName) throws Exception {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Map<String, StockPrice> stockPriceMap = new HashMap<>();
        try {

            br = new BufferedReader(new FileReader(fileName));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                String open = country[2];
                String high = country[3];
                String low = country[4];
                String close = country[5];
                String ltp = country[6];
                String isin = country[12];
                stockPriceMap.put(isin, new StockPrice(open, high, low, close, ltp));
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
