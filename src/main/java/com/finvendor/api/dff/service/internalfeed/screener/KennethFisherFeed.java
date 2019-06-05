package com.finvendor.api.dff.service.internalfeed.screener;

import com.finvendor.api.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
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
public class KennethFisherFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_kenneth_fisher values(?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "delete from strategy_kenneth_fisher";

    private float inflationRate;

    @Override
    public boolean processAndFeed() throws Exception {
        deleteAllRecordsFromStrategyTable("KENNITH STRATEGY", DELETE_QUERY);
        int totalMatch = 0;
        int totalMisMatch = 0;
        inflationRate = findInflationRate();
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails latestEarningPreview = findLatestEarningPreview(companyId);
            if (latestEarningPreview == null) {
                totalMisMatch++;
                continue;
            }

            float _3YrAvgNetProfitMarginFloat = find3YearAverageNetProfitMargin(companyDetails.getCompanyId());
            if (evalKennethCondition(companyDetails, latestEarningPreview, _3YrAvgNetProfitMarginFloat)) {
                insertFeed(companyDetails, latestEarningPreview, _3YrAvgNetProfitMarginFloat);
                totalMatch++;
            }
            else {
                totalMisMatch++;
            }
        }
        logger.info("Kenneth Fisher's Strategy :: Total Stock Matched: {}", totalMatch);
        logger.info("Kenneth Fisher's Strategy :: Total Stock *** Miss Matched: {}", totalMisMatch);
        return true;
    }

    private boolean evalKennethCondition(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview,
            float _3YrAvgNetProfitMarginFloat) {

        String sector = companyDetails.getSector();
        float shareOutStandingFloat = companyDetails.getShareOutStandingFloat();
        float cmpFloat = companyDetails.getCmpFloat();
        float mcapFloat = (shareOutStandingFloat * cmpFloat);

        float revenueFloat = latestEarningPreview.getRevenueFloat();
        float psrFloat = revenueFloat != 0.0F ? (mcapFloat / revenueFloat) : 0.0F;
        float deFloat = latestEarningPreview.getDeFloat();
        float latestEpsGrowth_Percentage = latestEarningPreview.getLatestEpsGrowthInPercentageAsFloat();

        boolean psrCondition = psrFloat < 0.75f;
        boolean deCondition = FINANCIALS.equals(sector) || deFloat < 0.40F;


        boolean epsGrowthWithInflationRateCondition = (latestEpsGrowth_Percentage - inflationRate) > 15.0F;

        boolean netProfitMarginCondition = _3YrAvgNetProfitMarginFloat > 0.05F;
        boolean mcapRndExpenseCondition=false;

        boolean finalCondition;
        if (latestEarningPreview.getRndExpenseFloat() != 0.0F) {
            mcapRndExpenseCondition = (mcapFloat / latestEarningPreview.getRndExpenseFloat()) < 10.0F;
        }
        else {
            mcapRndExpenseCondition = true;
        }
        finalCondition =
                psrCondition
                        && deCondition
                        && epsGrowthWithInflationRateCondition
                        && netProfitMarginCondition
                        && mcapRndExpenseCondition;
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------");
        sb.append("\nStock: [").append(companyDetails.getCompanyId()).append("::").append(companyDetails.getTicker())
                .append("] - ").append("Condition Matched: ").append(finalCondition);
        sb.append("\n--------------------------------------------------------------------------");
        sb.append("\nShareOutStanding: ------------------------------------- (").append(shareOutStandingFloat);
        sb.append("\nCmp:--------------------------------------------------- (").append(cmpFloat);
        sb.append("\nMKTCap[ShareOutstanding * CMP]: ----------------------- (").append(mcapFloat);
        sb.append("\nAnnualRevenue: ---------------------------------------- (").append(revenueFloat);
        sb.append("\nPSR[MKTCapt/AnnualRevenue]: --------------------------- (").append(psrFloat);
        sb.append("\nDe: --------------------------------------------------- (").append(deFloat);
        sb.append("\nLatestEpsGrowth: ---------------------------------------(").append(latestEpsGrowth_Percentage).append("%");
        sb.append("\nInflationRate: ---------------------------------------- (").append(inflationRate);
        sb.append("\n_3YrAvgNetProfitMargin: ------------------------------- (").append(_3YrAvgNetProfitMarginFloat);
        sb.append("\n\n");
        sb.append("\nCondition-1: [PSR < 0.5 ] --------------------------------------------------------------- ").append(psrCondition?"TRUE":"FALSE");
        sb.append("\nCondition-2: [D/E < 40%] ---------------------------------------------------------------- ").append(deCondition?"TRUE":"FALSE");
        sb.append("\nCondition-3: [(1Y EPS Growth - inflation rate)> 15%] ------------------------------------ ").append(epsGrowthWithInflationRateCondition?"TRUE":"FALSE");
        sb.append("\nCondition-4: [(3-Y Average net profit margin)>=5%] -------------------------------------- ").append(netProfitMarginCondition?"TRUE":"FALSE");
        sb.append("\nCondition-5: [(mcap/R&D expenditures) <10] ---------------------------------------------- ").append(mcapRndExpenseCondition?"TRUE":"FALSE");

        logger.info(" Condition Attibutes{}", sb.toString());
        sb.setLength(0);
        return finalCondition;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails earningPreview, float _3YrAvgNetProfitMarginFloat) {
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

        //PSR
        sqlQuery.setParameter(2, psr);

        //MCap
        sqlQuery.setParameter(3, String.valueOf(mcapFloat));

        //Revenue
        sqlQuery.setParameter(4, StringUtils.isEmpty(earningPreview.getRevenue()) ? "-" : earningPreview.getRevenue());

        //De
        sqlQuery.setParameter(5, StringUtils.isEmpty(earningPreview.getDe()) ? "-" : earningPreview.getDe());

        //Annual EPS Growth
        sqlQuery.setParameter(6, earningPreview.getLatestEpsGrowthInPercentageAsFloat() == 0.0F ? "-" :
                String.valueOf(earningPreview.getLatestEpsGrowthInPercentageAsFloat() / 100.0F));

        //Inflation Rate
        sqlQuery.setParameter(7, String.valueOf(inflationRate));

        //3Year AVG Net Profit Margin
        sqlQuery.setParameter(8, String.valueOf(_3YrAvgNetProfitMarginFloat));

        //RnD Expenditure
        sqlQuery.setParameter(9, StringUtils.isEmpty(earningPreview.getRndExpense()) ? "-" : earningPreview.getRndExpense());
        sqlQuery.executeUpdate();
    }
}
