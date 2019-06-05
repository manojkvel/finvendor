package com.finvendor.api.dff.service.internalfeed.screener;

import com.finvendor.api.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
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
    private static final String DELETE_QUERY = "delete from  strategy_james_shaughnessy";

    @Override
    public boolean processAndFeed() throws Exception {
        deleteAllRecordsFromStrategyTable("JAMES SHAUGHNESSY STRATEGY", DELETE_QUERY);
        List<CompanyDetails> companyDetailsList = findCompanyDetails();
        int totalMatch = 0;
        int totalMisMatch = 0;
        for (CompanyDetails companyDetails : companyDetailsList) {
            String companyId = companyDetails.getCompanyId();

            EarningPreviewDetails earningPreview = findLatestEarningPreview(companyId);
            if (earningPreview == null) {
                continue;
            }

            if (evalCondition(companyDetails, earningPreview)) {
                insertFeed(companyDetails, earningPreview);
                totalMatch++;
            }
            else {
                totalMisMatch++;
            }
        }
        logger.info("James O'Shaughnessy's Strategy :: Total Stock Matched: {}", totalMatch);
        logger.info("James O'Shaughnessy's Strategy :: Total Stock *** Miss Matched: {}", totalMisMatch);
        return true;
    }

    private boolean evalCondition(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {
        //sector=Research SubArea
        String sector = companyDetails.getSector();
        float totalFreeCashflowFloat = latestEarningPreview.getTotalFreeCashflowFloat();

        //(FINANCIALS.equals(sector) && totalFreeCashflowFloat == 0.0F)
        //This cmp is today's CMP
        float cmpFloat = companyDetails.getCmpFloat();
        float revenueFloat = latestEarningPreview.getRevenueFloat();
        float epsFloat = latestEarningPreview.getEpsFloat();

        //Make Sure you get BV Sharen from earningPreview_as_of_date table
        float bvShareFloat = latestEarningPreview.getBvShareFloat();

        float pbFloat = bvShareFloat != 0.0F ? cmpFloat / bvShareFloat : 0.0F;

        float shareOutStandingFloat = companyDetails.getShareOutStandingFloat();
        float mcapFloat = shareOutStandingFloat * cmpFloat;

        float netOperatingCashFlowFloat = latestEarningPreview.getNetOperatingCashFlowFloat();

        boolean condition1 = (cmpFloat / revenueFloat) < 1.5F;
        boolean condition2 = (epsFloat / cmpFloat) > 5.0F;
        boolean condition3 = pbFloat < 1.0F;
        boolean condition4 = mcapFloat > (150.0F * 7.0F);

        //Strategy exceptional condition
        //b) if [(company's "Research SubArea" = "Financials") AND ("TOTAL Free Cash flow" = "-" )] then cash flow/share condition is True.
        boolean condition5 = (FINANCIALS.equals(sector) && totalFreeCashflowFloat == 0.0F) || netOperatingCashFlowFloat > 0.0F;

        boolean finalCondition = condition1 && condition2 && condition3 && condition4 && condition5;
        StringBuilder jamesSB = new StringBuilder();
        jamesSB.append("\n--------------------------------------------------------------------------");
        jamesSB.append("\nStock: [").append(companyDetails.getCompanyId()).append("::").append(companyDetails.getTicker())
                .append("] - ").append("Condition Matched: ").append(finalCondition);
        jamesSB.append("\n--------------------------------------------------------------------------");
        jamesSB.append("\nCmp: ----------------------------------------- (").append(cmpFloat);
        jamesSB.append("\nRevenue:-------------------------------------- (").append(revenueFloat);
        jamesSB.append("\nEPS: ----------------------------------------- (").append(epsFloat);
        jamesSB.append("\nBVShare: ------------------------------------- (").append(bvShareFloat);
        jamesSB.append("\nPB: ------------------------------------------ (").append(pbFloat);
        jamesSB.append("\nShareOutStanding: ---------------------------- (").append(shareOutStandingFloat);
        jamesSB.append("\nMcap: ---------------------------------------- (").append(mcapFloat);
        jamesSB.append("\nNetOperatingCashFlow: ------------------------ (").append(netOperatingCashFlowFloat);
        jamesSB.append("\n\n\n");
        logger.info(" Condition Attibutes{}", jamesSB.toString());
        jamesSB.setLength(0);
        return finalCondition;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {
        //sector=Research SubArea
        String sector = companyDetails.getSector();
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
