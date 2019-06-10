package com.finvendor.api.markets.dao;

import com.finvendor.common.commondao.ICommonDao;
import io.swagger.models.auth.In;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class _52wLowHigh {

    @Autowired
    private ICommonDao commonDao;

    private static final String FIND_STOCK_52W_HIGH__LOW_QUERY = "select a.stock_id, b.company_name, a.`52w_high`,a.`52w_low` from stock_current_info a, rsch_sub_area_company_dtls b where a.stock_id=b.company_id";

    public void findStocksWithPriceDates() {
        SQLQuery sqlQuery = commonDao.getNativeQuery(FIND_STOCK_52W_HIGH__LOW_QUERY, null);
        List<Object[]> rows = sqlQuery.list();
        for (Object[] row : rows) {
            String companyId = row[0] != null ? row[0].toString().trim() : "";
            String companyName = row[1] != null ? row[1].toString().trim() : "";
            String _52wLow = row[2] != null ? row[2].toString().trim() : "";
            sqlQuery = commonDao
                    .getNativeQuery("select count(a.price_date),count(a.price_date) from stock_historical_prices a where a.low_price=?",
                            new Object[] { _52wLow.trim() });
            BigInteger lowPriceDateCount = (BigInteger) ((List<Object[]>) sqlQuery.list()).get(0)[0];
            if (lowPriceDateCount.longValue() > 1) {
                System.out.println(companyId + "," + companyName + "," + _52wLow + "," + lowPriceDateCount);
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Object[] row : rows) {
            String companyId = row[0] != null ? row[0].toString().trim() : "";
            String companyName = row[1] != null ? row[1].toString().trim() : "";
            String _52wHigh = row[3] != null ? row[3].toString().trim() : "";

            sqlQuery = commonDao
                    .getNativeQuery("select count(a.price_date),count(a.price_date) from stock_historical_prices a where a.high_price=?",
                            new Object[] { _52wHigh.trim() });
            BigInteger highPriceDateCount = (BigInteger) ((List<Object[]>) sqlQuery.list()).get(0)[0];
            if (highPriceDateCount.longValue() > 1) {
                System.out.println(companyId + "," + companyName + "," + _52wHigh + "," + highPriceDateCount);
            }
        }
    }
}
