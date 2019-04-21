package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.InflationRate;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.NetProfitMargin;
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
public class KennethFisherFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_kenneth_fisher values(?,?,?,?,?,?,?,?,?,?)";

    @Override
    public boolean processAndFeed() throws Exception {
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails earningPreview = findLatestEarningPreview(companyId);
            if (earningPreview == null) {
                continue;
            }

            InflationRate inflationRate = findInflationRate(companyId);
            if (inflationRate == null) {
                continue;
            }

            NetProfitMargin netProfitMargin = findAvgNetProfitMargin(companyId);
            if (netProfitMargin == null) {
                continue;
            }

            if (evalCondition(companyDetails, earningPreview, inflationRate, netProfitMargin)) {
                insertFeed(companyDetails, earningPreview, inflationRate, netProfitMargin);
            }
        }
        return true;
    }

    private boolean evalCondition(CompanyDetails companyDetails, EarningPreviewDetails earningPreview,
            InflationRate inflationRate, NetProfitMargin netProfitMargin) {
        float mcapFloat = companyDetails.getShareOutStandingFloat() * companyDetails.getCmpFloat();
        float psrFloat = earningPreview.getRevenueFloat() != 0.0F ? (mcapFloat / earningPreview.getRevenueFloat()) : 0.0F;
        boolean psrCondition = psrFloat < 0.75f;
        boolean deCondition = earningPreview.getDeFloat() < 0.40F;
        boolean epsGrowthWithInflationRateCondition = (earningPreview.getEpsGrowthFloat() - inflationRate.getInflationRateFloat()) > 0.15F;
        boolean netProfitMarginCondition = netProfitMargin.getAvgNetProfitMarginFloat() > 0.05F;
        boolean mcapRndExpenseCondition;

        boolean finalCondition;
        if (earningPreview.getRndExpenseFloat() != 0.0F) {
            mcapRndExpenseCondition = (mcapFloat / earningPreview.getRndExpenseFloat()) < 10.0F;
            finalCondition =
                    psrCondition
                            && deCondition
                            && epsGrowthWithInflationRateCondition
                            && netProfitMarginCondition
                            && mcapRndExpenseCondition;
        }
        else {
            finalCondition =
                    psrCondition
                            && deCondition
                            && epsGrowthWithInflationRateCondition
                            && netProfitMarginCondition;
        }
        return finalCondition;
    }

    private void insertFeed(CompanyDetails companyDetails,
            EarningPreviewDetails earningPreview, InflationRate inflationRate, NetProfitMargin profitMargin) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();
        String mcap = companyDetails.getMcap();

        float mcapFloat = companyDetails.getShareOutStandingFloat() * companyDetails.getCmpFloat();
        float psrFloat = earningPreview.getRevenueFloat() != 0.0F ? (mcapFloat / earningPreview.getRevenueFloat()) : 0.0F;
        String psr = String.valueOf(psrFloat);

        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);

        //CompanyId
        sqlQuery.setParameter(0, Integer.parseInt(companyId));

        //CompanyName
        sqlQuery.setParameter(1, companyName == null ? "-" : companyName);

        //PSR
        sqlQuery.setParameter(2, psr);

        //MCap
        sqlQuery.setParameter(3, mcap == null ? "-" : mcap);

        //Revenue
        sqlQuery.setParameter(4, earningPreview.getRevenue() == null ? "-" : earningPreview.getRevenue());

        //De
        sqlQuery.setParameter(5, earningPreview.getDe() == null ? "-" : earningPreview.getDe());

        //Annual EPS Growth
        sqlQuery.setParameter(6, earningPreview.getEpsGrowth() == null ? "-" : earningPreview.getEpsGrowth());

        //Inflation Rate
        sqlQuery.setParameter(7, inflationRate.getInflationRate() == null ? "-" : inflationRate.getInflationRate());

        //3Year AVG Net Profit Margin
        sqlQuery.setParameter(8,
                profitMargin.getAvgNetProfitMargin() == null ? "-" : profitMargin.getAvgNetProfitMargin());

        //RnD Expenditure
        sqlQuery.setParameter(9, earningPreview.getRndExpense() == null ? "-" : earningPreview.getRndExpense());
        sqlQuery.executeUpdate();
    }
}
