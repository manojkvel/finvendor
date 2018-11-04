package com.finvendor.serverwebapi.resources.researchreport.mf.dao;

import com.finvendor.model.MutualFundMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository
@Transactional
public class MfHistoricalNavFilePersist extends AbstractMfFilePersist<MutualFundMaster> {
    private static final Logger logger = LoggerFactory.getLogger(MfHistoricalNavFilePersist.class.getName());

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
                String schemeSubCategory = mfColumns[5];
                String schemeNavName = mfColumns[6];

                String schemeMinAmt = mfColumns[7];
                String launchDate = mfColumns[8];
                String closureDate = mfColumns[9];
                String isinDivPayout = mfColumns[10];
                String isinDivReInvest = mfColumns[11];

                MutualFundMaster mfMasterEntity = new MutualFundMaster();
                mfMasterEntity.setAmcName(amc);
                mfMasterEntity.setAmfiCode(code);
                mfMasterEntity.setSchemeName(schemeName);
                mfMasterEntity.setSchemeType(schemeType);
                mfMasterEntity.setSchemeCategory(schemeCategory);
                mfMasterEntity.setSchemeSubCategory(schemeSubCategory);
                mfMasterEntity.setSchemeNavName(schemeNavName);
                mfMasterEntity.setSchemeMinAmount(schemeMinAmt);
                mfMasterEntity.setSchemeLauchDate(launchDate);
                mfMasterEntity.setSchemeClosureDt(closureDate);
                mfMasterEntity.setIsinDivPayout(isinDivPayout);
                mfMasterEntity.setIsinDivReInvest(isinDivReInvest);

                save(mfMasterEntity);
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
