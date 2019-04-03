package com.finvendor.api.resources.screener.mf.dao.filedao;

import com.finvendor.model.MutualFundHistoricalNav;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MfHistoricalDataPersist extends AbstractMfFilePersist<MutualFundHistoricalNav> {
    private static final Logger logger = LoggerFactory.getLogger(MfHistoricalDataPersist.class.getName());
    private static String CHECK_SCHEME_CODE_EXISTENCE_QUERY = "select a.amc_name, a.amfi_code from mutual_fund_master a";

    @Override
    public Long persist(String filePath) throws RuntimeException {
        logger.debug("filePath: {}", filePath);


        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        long totalRecordInserted = 0L;
        long id = 1L;
        try {
            Map<String, String> schemeCodeMap = new LinkedHashMap<>();
            SQLQuery nativeQuery = commonDao.getNativeQuery(CHECK_SCHEME_CODE_EXISTENCE_QUERY, null);
            List<Object[]> rows = nativeQuery.list();

            for (Object[] row : rows) {
                String schemeName = row[0] != null ? row[0].toString().trim() : "";
                String schemeCode = row[1] != null ? row[1].toString().trim() : "";
                schemeCodeMap.put(schemeCode, schemeName);
            }

            br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] mfColumns = line.split(cvsSplitBy);
                String schemeCode = mfColumns[0].trim();
                //String schemeName = mfColumns[1].trim();
                String netAssetValue = mfColumns[2].trim();
                String repurchasePrice = mfColumns[3].trim();
                String salePrice = mfColumns[4].trim();
                String date = mfColumns[5].trim();
                MutualFundHistoricalNav mutualFundHistoricalNav = new MutualFundHistoricalNav();
                if (schemeCodeMap.get(schemeCode) != null) {
                    mutualFundHistoricalNav.setId(id);
                    mutualFundHistoricalNav.setSchemeCode(schemeCode);
                    mutualFundHistoricalNav.setNetAssetValue(netAssetValue);
                    mutualFundHistoricalNav.setRePurchasePrice(repurchasePrice);
                    mutualFundHistoricalNav.setSalePrice(salePrice);
                    mutualFundHistoricalNav.setDate(date);
                    save(mutualFundHistoricalNav);
                    id++;
                    totalRecordInserted++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.warn("Unable to close  buffer reader while inserting MF Static data " +
                            "to mutual_fund_master table");
                }
            }
        }
        logger.info("totalRecordInserted: {}", totalRecordInserted);
        return totalRecordInserted;
    }
}
