package com.finvendor.serverwebapi.resources.researchreport.mf.dao;

import com.finvendor.model.MutualFundDailyNav;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository
@Transactional
public class MfDailyNavFilePersist extends AbstractMfFilePersist<MutualFundDailyNav> {
    private static final Logger logger = LoggerFactory.getLogger(MfDailyNavFilePersist.class.getName());

    @Override
    public Long persist(String filePath) throws RuntimeException {
        logger.info("filePath: {}",filePath);
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        long totalRecordInserted = 0L;
        try {
            br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] mfColumns = line.split(cvsSplitBy);
                String amc = mfColumns[0];
                String code = mfColumns[1];

                String schemeName = mfColumns[2];
                String schemeType = mfColumns[3];
                String schemeCategory = mfColumns[4];

                MutualFundDailyNav mutualFundDailyNav = new MutualFundDailyNav();
                save(mutualFundDailyNav);
                totalRecordInserted++;
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
        logger.info("totalRecordInserted: {}",totalRecordInserted);
        return totalRecordInserted;
    }
}
