package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class BenjaminGrahamFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_benjamin_graham values(?,?,?,?,?,?,?,?,?);";

    @Override
    public boolean processAndFeed() throws Exception {
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails earningPreview = findEarningPreview(companyId);
            if (earningPreview == null) {
                continue;
            }

            boolean is5yearEpsGrowthPositive = is5YearEpsGrowthPositive(companyDetails.getCompanyId());
            if (!is5yearEpsGrowthPositive) {
                continue;
            }
            if (evalCondition(companyDetails, earningPreview, is5yearEpsGrowthPositive)) {
                insertFeed(companyDetails, earningPreview, is5yearEpsGrowthPositive);
            }
        }
        return true;
    }

    private boolean evalCondition(CompanyDetails companyDetails, EarningPreviewDetails earningPreview, boolean is5yearEpsGrowthPositive) {
        float totalDebtFloat = earningPreview.getTotalDebtFloat();
        float currentAssetFloat = earningPreview.getCurrentAssetFloat();
        float currentLiabilitiesFloat = earningPreview.getCurrentLiabilitiesFloat();
        float peFloat = companyDetails.getPeFloat();
        float pbFloat = companyDetails.getPbFloat();
        float divYeildFloat = companyDetails.getDivYeildFloat();

        boolean con1 = (totalDebtFloat / currentAssetFloat) < 1.1F;
        boolean con2 = (currentAssetFloat / currentLiabilitiesFloat) > 1.5F;

        boolean con3 = is5yearEpsGrowthPositive;
        boolean con4 = peFloat < 9.0F;
        boolean con5 = pbFloat < 1.2F;
        boolean con6 = divYeildFloat > 0.0F;

        logger.info("Condition1:{}", con1);
        logger.info("Condition2:{}", con2);
        logger.info("Condition3:{}", con3);
        logger.info("Condition4:{}", con4);
        logger.info("Condition5:{}", con5);
        logger.info("Condition6:{}", con6);

        return con1 && con2 && con3 && con4 && con5 && con6;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails earningPreview, boolean is5yearEpsGrowthPositive) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();

        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);

        //CompanyId
        sqlQuery.setParameter(0, Integer.parseInt(companyId));
        sqlQuery.setParameter(1, companyName);
        sqlQuery.setParameter(2, earningPreview.getTotalDebt());
        sqlQuery.setParameter(3, earningPreview.getCurrentAsset());
        sqlQuery.setParameter(4, earningPreview.getCurrentLiabilities());
        sqlQuery.setParameter(5, companyDetails.getPe());
        sqlQuery.setParameter(6, companyDetails.getPb());
        sqlQuery.setParameter(7, companyDetails.getDivYeild());
        sqlQuery.setParameter(8, String.valueOf(is5yearEpsGrowthPositive));
        sqlQuery.executeUpdate();
    }
}
