package com.finvendor.serverwebapi.resources.markets.dao;

import com.finvendor.common.constant.AppConstant;
import com.finvendor.model.Markets;
import com.finvendor.server.common.commondao.ICommonDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * this class read data from bhav copy and persist information like prices into market table
 */
@Repository
@Transactional
public class MarketsFilePersist extends AbstractMarketsFilePersist<Markets> {

    @Autowired
    private ICommonDao commonDao;

    @Override
    public Long persist(String fromFilePath) throws RuntimeException {
        String query = "select a.company_id,a.company_name,b.52w_low,b.52w_high from rsch_sub_area_company_dtls a, stock_current_info b where a.company_id=b.stock_id and a.isin_code=?";
        String update52WeekLowQuery = "update stock_current_info set `52w_low`=? where stock_id=?";
        String update52WeekHighQuery = "update stock_current_info set `52w_high`=? where stock_id=?";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        long totalPriceInserted = 0L;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);
            String bhavPriceDateAsPerFvFormat = formatter.format(Calendar.getInstance().getTime());
            br = new BufferedReader(new FileReader(fromFilePath));
            br.readLine();
            long id = 1L;
            while ((line = br.readLine()) != null) {
                String[] bhavColumns = line.split(cvsSplitBy);
                String symbol = bhavColumns[0];
                String series = bhavColumns[1];

                String open = String.valueOf(Double.parseDouble(bhavColumns[2].trim()));

                double highAsDouble = Double.parseDouble(bhavColumns[3].trim());
                String high = String.valueOf(highAsDouble);

                double lowAsDouble = Double.parseDouble(bhavColumns[4].trim());
                String low = String.valueOf(lowAsDouble);

                String close = String.valueOf(Double.parseDouble(bhavColumns[5].trim()));
                String last = String.valueOf(Double.parseDouble(bhavColumns[6]));

                String prevClose = String.valueOf(Double.parseDouble(bhavColumns[7].trim()));
                String totTrdQty = bhavColumns[8].trim();

                String totTrdVal = bhavColumns[9];
                String timestamp = bhavColumns[10];
                String totalTrades = bhavColumns[11];
                String isin = bhavColumns[12].trim();

                //calculate priceChange & percentPriceChange
                double prevCloseAsDouble = Double.parseDouble(prevClose.trim());
                double closePriceAsDouble = Double.parseDouble(close.trim());
                double priceChange = closePriceAsDouble - prevCloseAsDouble;
                double priceChangeInPercent = (priceChange) * 100d / prevCloseAsDouble;


                //get company id and company name 52wLow, 52wHigh  from rsch_sub_area_company_details and stockcurrent info
                // table using bhav copy isin
                String companyId = "";
                String companyName = "";
                double _52wLowAsDoubleFromStockCurrenInfoTable = 0.0d;
                String _52wLowChangeStatus = "";
                double _52wHighAsDoubleFromStockCurrenInfoTable = 0.0d;
                String _52wHighChangeStatus = "";

                SQLQuery nativeQuery = commonDao.getNativeQuery(query, new String[]{isin.trim()});
                List<Object[]> rows = nativeQuery.list();

                if (rows.size() == 0) {
                    continue;
                }

                for (Object[] row : rows) {
                    companyId = row[0] != null ? row[0].toString().trim() : "";
                    companyName = row[1] != null ? row[1].toString().trim() : "";
                    if (!StringUtils.isEmpty(row[2].toString())) {
                        _52wLowAsDoubleFromStockCurrenInfoTable = Double.parseDouble(row[2].toString());

                    }
                    if (!StringUtils.isEmpty(row[3].toString())) {
                        _52wHighAsDoubleFromStockCurrenInfoTable = Double.parseDouble(row[3].toString());
                    }
                    break;
                }

                if (lowAsDouble > _52wLowAsDoubleFromStockCurrenInfoTable) {
                    _52wLowAsDoubleFromStockCurrenInfoTable = lowAsDouble;
                    _52wLowChangeStatus = "Y";
                    //update this 52wLow in stockCurrentInfo table
                    nativeQuery = commonDao.getNativeQuery(update52WeekLowQuery, new String[]{String.valueOf(_52wLowAsDoubleFromStockCurrenInfoTable), companyId.trim()});
                    nativeQuery.executeUpdate();
                } else {
                    _52wLowChangeStatus = "N";
                }

                if (highAsDouble > _52wHighAsDoubleFromStockCurrenInfoTable) {
                    _52wHighAsDoubleFromStockCurrenInfoTable = highAsDouble;
                    _52wHighChangeStatus="Y";
                    //update this 52wHigh in stockCurrentInfo table
                    nativeQuery = commonDao.getNativeQuery(update52WeekHighQuery, new String[]{String.valueOf(_52wHighAsDoubleFromStockCurrenInfoTable), companyId.trim()});
                    nativeQuery.executeUpdate();
                } else {
                    _52wHighChangeStatus="N";
                }

                Markets markets = findById(id);
                if (markets == null) {
                    markets = new Markets();
                    markets.setId(id);
                }
                markets.setCompanyId(companyId);
                markets.setCompanyName(companyName);
                markets.setIsin(isin);
                markets.setOpen(open);
                markets.setHigh(high);
                markets.setLow(low);
                markets.setClose(close);
                markets.setPrevColse(prevClose);
                markets.setPriceChange(priceChange);
                markets.setPricePercentChange(priceChangeInPercent);
                markets.set_52wLow(_52wLowAsDoubleFromStockCurrenInfoTable);
                markets.set_52wLowChange(_52wLowChangeStatus);
                markets.set_52wHigh(_52wHighAsDoubleFromStockCurrenInfoTable);
                markets.set_52wHighChange(_52wHighChangeStatus);
                markets.setTotalTradeQty(Integer.parseInt(totTrdQty));
                markets.setDate(bhavPriceDateAsPerFvFormat);
                saveOrUpdate(markets);
                id++;
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
