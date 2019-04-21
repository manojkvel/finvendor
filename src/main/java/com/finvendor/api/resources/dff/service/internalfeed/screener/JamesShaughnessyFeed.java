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
public class JamesShaughnessyFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_james_shaughnessy values(?,?,?,?,?,?,?,?)";

    @Override
    public boolean processAndFeed() throws Exception {
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails earningPreview = findLatestEarningPreview(companyId);
            if (earningPreview == null) {
                continue;
            }

            if (evalCondition(companyDetails, earningPreview)) {
                insertFeed(companyDetails, earningPreview);
            }
        }
        return true;
    }

    private boolean evalCondition(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {
        float cmpFloat = companyDetails.getCmpFloat();
        float revenueFloat = latestEarningPreview.getRevenueFloat();
        float epsFloat = latestEarningPreview.getEpsFloat();
        float bvShareFloat = companyDetails.getBvShareFloat();
        float pbFloat = bvShareFloat != 0.0F ? cmpFloat / bvShareFloat : 0.0F;
        float mcapFloat = companyDetails.getShareOutStandingFloat() * companyDetails.getCmpFloat();
        float netOperatingCashFlowFloat = latestEarningPreview.getNetOperatingCashFlowFloat();

        boolean condition1 = cmpFloat / revenueFloat < 1.5F;
        boolean condition2 = epsFloat / cmpFloat > 5.0F;
        boolean condition3 = pbFloat < 1.0F;
        boolean condition4 = mcapFloat > 150.0F * 7.0F;
        boolean condition5 = netOperatingCashFlowFloat > 0.0F;

        return condition1 && condition2 && condition3 && condition4 && condition5;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();
        String mcapStr = String.valueOf(companyDetails.getShareOutStandingFloat() * companyDetails.getCmpFloat());


        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);
        sqlQuery.setParameter(0, Integer.parseInt(companyId));
        sqlQuery.setParameter(1, companyName);
        sqlQuery.setParameter(2, companyDetails.getCmp());
        sqlQuery.setParameter(3, latestEarningPreview.getRevenue());
        sqlQuery.setParameter(4, latestEarningPreview.getEps());
        sqlQuery.setParameter(5, companyDetails.getPb());
        sqlQuery.setParameter(6, mcapStr);
        sqlQuery.setParameter(7, latestEarningPreview.getNetOperatingCashFlow());
        sqlQuery.executeUpdate();
    }
}
