package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
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
public class JoelGreenblattFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_kenneth_fisher values(?,?,?,?,?,?,?,?,?,?)";

    @Override
    public boolean processAndFeed() throws Exception {
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            EarningPreviewDetails latestEarningPreview = findLatestEarningPreview(companyDetails.getCompanyId());
            if (latestEarningPreview == null) {
                continue;
            }
            if (evalCondition(companyDetails, latestEarningPreview)) {
                insertFeed(companyDetails, latestEarningPreview);
            }
        }
        return true;
    }

    private boolean evalCondition(CompanyDetails companyDetails, EarningPreviewDetails earningPreview) {
        float revenueFloat = earningPreview.getRevenueFloat();
        float netOperatingCashFlowFloat = earningPreview.getNetOperatingCashFlowFloat();
        float mcapFloat = companyDetails.getShareOutStandingFloat() * companyDetails.getCmpFloat();
        float totalDebtFloat = earningPreview.getTotalDebtFloat();
        float cashAndCashEquivFloat = earningPreview.getCashAndCashEquivFloat();

        return false;
    }

    private void insertFeed(CompanyDetails companyDetails,
            EarningPreviewDetails earningPreview) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();

        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);


        sqlQuery.executeUpdate();
    }
}
