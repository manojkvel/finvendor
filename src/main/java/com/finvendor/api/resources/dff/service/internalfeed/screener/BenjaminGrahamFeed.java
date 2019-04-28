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
    private static final String DELETE_QUERY = "delete from strategy_benjamin_graham";

    @Override
    public boolean processAndFeed() throws Exception {
        deleteAllRecordsFromStrategyTable("BENJAMIN GRAHAM STRATEGY", DELETE_QUERY);
        int totalMatch = 0;
        int totalMisMatch = 0;
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails earningPreview = findLatestEarningPreview(companyId);
            if (earningPreview == null) {
                continue;
            }

            boolean is5yearEpsGrowthPositive = isNYearEpsGrowthPositive(companyDetails.getCompanyId());
            if (!is5yearEpsGrowthPositive) {
                continue;
            }
            if (evalCondition(companyDetails, earningPreview, true)) {
                insertFeed(companyDetails, earningPreview, true);
                totalMatch++;
            }
            else {
                totalMisMatch++;
            }
        }
        logger.info("Benjamin Graham's Strategy :: Total Stock Matched: {}", totalMatch);
        logger.info("Benjamin Graham's Strategy :: Total Stock *** Miss Matched: {}", totalMisMatch);
        return true;
    }

    private boolean evalCondition(CompanyDetails companyDetails, EarningPreviewDetails earningPreview, boolean is5yearEpsGrowthPositive) {
        float totalDebtFloat = earningPreview.getTotalDebtFloat();
        float currentAssetFloat = earningPreview.getCurrentAssetFloat();
        float currentLiabilitiesFloat = earningPreview.getCurrentLiabilitiesFloat();
        float peFloat = companyDetails.getPeFloat();

        //This cmp is today's CMP
        float cmpFloat = companyDetails.getCmpFloat();

        //Make Sure you get BV Sharen from earningPreview_as_of_date table
        float bvShareFloat = earningPreview.getBvShareFloat();
        float pbFloat = bvShareFloat != 0.0F ? (cmpFloat / bvShareFloat) : 0.0F;

        float divYeildFloat = companyDetails.getDivYeildFloat();

        boolean con1 = (totalDebtFloat / currentAssetFloat) < 1.1F;
        boolean con2 = (currentAssetFloat / currentLiabilitiesFloat) > 1.5F;

        boolean con3 = is5yearEpsGrowthPositive;
        boolean con4 = peFloat < 9.0F;
        boolean con5 = pbFloat < 1.2F;
        boolean con6 = divYeildFloat > 0.0F;

        boolean finalCondition = con1 && con2 && con3 && con4 && con5 && con6;
        StringBuilder benjaminSB = new StringBuilder();
        benjaminSB.append("\n--------------------------------------------------------------------------");
        benjaminSB.append("\nStock: [").append(companyDetails.getCompanyId()).append("::").append(companyDetails.getTicker())
                .append("] - ").append("Condition Matched: ").append(finalCondition);
        benjaminSB.append("\n--------------------------------------------------------------------------");
        benjaminSB.append("\nTotalDebt: ----------------------------------- ").append(totalDebtFloat);
        benjaminSB.append("\nCurrentAsset:--------------------------------- ").append(currentAssetFloat);
        benjaminSB.append("\nCurrentLiabilities: -------------------------- ").append(currentLiabilitiesFloat);
        benjaminSB.append("\nPE: ------------------------------------------ ").append(peFloat);
        benjaminSB.append("\nCMP: ----------------------------------------- ").append(cmpFloat);
        benjaminSB.append("\nBVShare: ------------------------------------- ").append(bvShareFloat);
        benjaminSB.append("\nPB: ------------------------------------------ ").append(pbFloat);
        benjaminSB.append("\nDivYeild: ------------------------------------ ").append(divYeildFloat);
        benjaminSB.append("\n\n");
        benjaminSB.append("\nCondition-1: [Total debt/current assets < 1.1] ------------------------------------ ").append("(").append(totalDebtFloat).append("/").append(currentAssetFloat).append(") = ").append((totalDebtFloat / currentAssetFloat)).append(" | ").append((totalDebtFloat / currentAssetFloat)).append(" < 1.1 ").append("=> ").append(con1?"TRUE":"FALSE");
        benjaminSB.append("\nCondition-2: [current assets/current liabilities >1.5] ---------------------------- ").append("(").append(currentAssetFloat).append("/").append(currentLiabilitiesFloat).append(") = ").append((currentAssetFloat / currentLiabilitiesFloat)).append(" | ").append((currentAssetFloat / currentLiabilitiesFloat)).append(" > 1.5").append(" => ").append(con2?"TRUE":"FALSE");
        benjaminSB.append("\nCondition-3: [EPS growth +ve for past 5 years] ------------------------------------ ").append(" => ").append(con3? "TRUE": "FALSE");
        benjaminSB.append("\nCondition-4: [P/E <= 9] ----------------------------------------------------------- ").append(peFloat).append(" < 9").append(" => ").append(con4?"TRUE" : "FALSE");
        benjaminSB.append("\nCondition-5: [P/B ((cmpFloat / bvShareFloat)) <= 1.2] ----------------------------- ").append("(").append(cmpFloat).append("/").append(bvShareFloat).append(") = ").append((cmpFloat / bvShareFloat)).append(" | ").append((cmpFloat / bvShareFloat)).append(" < 1.2").append(" => ").append(con5?"TRUE":"FALSE");
        benjaminSB.append("\nCondition-6: [Dividend yield > 0] ------------------------------------------------- ").append(divYeildFloat).append(" > 0 ").append(" => ").append(con6? "TRUE": "FALSE");
        benjaminSB.append("\n\n");
        logger.info(" Condition Attibutes{}", benjaminSB.toString());
        benjaminSB.setLength(0);
        return finalCondition;
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
