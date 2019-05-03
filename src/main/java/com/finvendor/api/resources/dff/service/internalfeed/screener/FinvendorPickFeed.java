package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ayush
 * @since 7-April-2019
 */
@Service
@Transactional
public class FinvendorPickFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_finvendor_pick values(?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "delete from strategy_finvendor_pick";


    @Override
    public boolean processAndFeed() throws Exception {
        deleteAllRecordsFromStrategyTable("FINVENDOR PICK STRATEGY", DELETE_QUERY);
        int totalMatch = 0;
        int totalMisMatch = 0;
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails latestEarningPreview = findLatestEarningPreview(companyId);
            if (latestEarningPreview == null) {
                totalMisMatch++;
                continue;
            }

            if (evalFinvendorPickCondition(companyDetails, latestEarningPreview)) {
                insertFeed(companyDetails, latestEarningPreview);
                totalMatch++;
            }
            else {
                totalMisMatch++;
            }
        }
        logger.info("Finvendor Pick Strategy :: Total Stock Matched: {}", totalMatch);
        logger.info("Finvendor Pick Strategy :: Total Stock *** Miss Matched: {}", totalMisMatch);
        return true;
    }

    private boolean evalFinvendorPickCondition(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {
        String sector = companyDetails.getSector();
        String companyId = companyDetails.getCompanyId();
        float shareOutStandingFloat = companyDetails.getShareOutStandingFloat();
        float cmpFloat = companyDetails.getCmpFloat();
        float mcapFloat = (shareOutStandingFloat * cmpFloat);

        float totalFreeCashflowFloat = latestEarningPreview.getTotalFreeCashflowFloat();
        float _1YrEpsGrowthInPercentage = find1YrEpsGrowthInPercentage(companyId);
        float salesGrowth = findLatestRevenueGrowth(companyId);
        float currentAssetFloat = latestEarningPreview.getCurrentAssetFloat();
        float currentLiabilitiesFloat = latestEarningPreview.getCurrentLiabilitiesFloat();

        float currentRatio = currentLiabilitiesFloat != 0.0F ? (currentAssetFloat / currentLiabilitiesFloat) : 0.0F;
        float roeFloat = latestEarningPreview.getRoeFloat();
        float deFloat = latestEarningPreview.getDeFloat();
        boolean finalCondition=true;
        return finalCondition;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails earningPreview) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();

        float mcapFloat = (companyDetails.getShareOutStandingFloat() * companyDetails.getCmpFloat());
        float psrFloat = earningPreview.getRevenueFloat() != 0.0F ? (mcapFloat / earningPreview.getRevenueFloat()) : 0.0F;
        String psr = String.valueOf(psrFloat);

        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);

        //CompanyId
        sqlQuery.setParameter(0, Integer.parseInt(companyId));

        //CompanyName
        sqlQuery.setParameter(1, StringUtils.isEmpty(companyName) ? "-" : companyName);

        sqlQuery.executeUpdate();
    }
}
