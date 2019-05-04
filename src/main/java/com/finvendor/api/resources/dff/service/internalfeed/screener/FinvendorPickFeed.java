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
    private static final String INSERT_QUERY = "insert into strategy_finvendor_pick values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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

    private boolean evalFinvendorPickCondition(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) throws Exception {
        String sector = companyDetails.getSector();
        String companyId = companyDetails.getCompanyId();
        float epsFloat = latestEarningPreview.getEpsFloat();
        float shareOutStandingFloat = companyDetails.getShareOutStandingFloat();
        float cmpFloat = companyDetails.getCmpFloat();
        float mcapFloat = (shareOutStandingFloat * cmpFloat);

        float peFloat = companyDetails.getPeFloat();
        String nifty50Pe = findIndexPe("Nifty 50");
        float nifty50PeFloat = !StringUtils.isEmpty(nifty50Pe) ? Float.parseFloat(nifty50Pe) : 0.0F;
        float totalFreeCashflowFloat = latestEarningPreview.getTotalFreeCashflowFloat();
        float _1YrEpsGrowthInPercentage = find1YrEpsGrowthInPercentage(companyId);
        float salesGrowth = findLatestRevenueGrowth(companyId);
        float currentAssetFloat = latestEarningPreview.getCurrentAssetFloat();
        float currentLiabilitiesFloat = latestEarningPreview.getCurrentLiabilitiesFloat();
        float currentRatioFloat = currentLiabilitiesFloat != 0.0F ? (currentAssetFloat / currentLiabilitiesFloat) : 0.0F;
        float roeFloatInPercent = latestEarningPreview.getRoeFloat();
        float operatingProfitMarginFloatInPercent = latestEarningPreview.getOperatingProfitMarginFloat();
        float deFloatInAbsolute = latestEarningPreview.getDeFloat();
        float longTermDebtFloat = latestEarningPreview.getLongTermDebtFloat();
        float allYearRoeAvg = findAllYearRoeAvg(companyId);
        float _10YrBondYieldInAbsolute = _10Yr_BOND_YIELD_PERCENTAGE / 100.0F;
        float patFloat = latestEarningPreview.getPatFloat();
        float totalCapitalFloat = latestEarningPreview.getTotalCapitalFloat();
        float retainedEarningFloat = latestEarningPreview.getRetainedEarningFloat();
        float totalOutstandigShareFloat = mcapFloat / cmpFloat;

        //Condition-1: Total free cash flow > 0
        // Exceptional Condition: if [(company's "Research SubArea" = "Financials") AND ("TOTAL Free Cash flow" = "-" )] then cash flow/share condition is True.
        boolean totalFreeCashFlowConditionCondition = (FINANCIALS.equals(sector) && totalFreeCashflowFloat == 0.0F) || totalFreeCashflowFloat > 0.0F;

        //Condition-2: [(sales growth >= 70% of EPS growth)  or (sales growth > 7%)]
        boolean salesGrowthComparisionWithEpsGrowthCondition = ((salesGrowth >= .70F * epsFloat ) || salesGrowth > 7.0F);

        //Condition-3: 1 Y EPS growth (between 7-20%)
        boolean _1YearEpsGrowthCondition = (_1YrEpsGrowthInPercentage >= 7 && _1YrEpsGrowthInPercentage <= 20);

        //Condition-4: P/E ratio between 40-60% of Nifty50 index p/e
        boolean peWithNifty50Condition = peFloat >= (.40F * nifty50PeFloat) && peFloat <= (.60F * nifty50PeFloat);

        //Condition-5: current ratio > 2
        //Exceptional Condition:  if company's "Research SubArea" = "Financials" THEN "current ratio" CONDITION is TRUE
        boolean currentRatio = FINANCIALS.equals(sector) || currentRatioFloat > 2.0F;

        //Condition-6: CURRENT ROE >27%
        boolean roeCondition = roeFloatInPercent > 27.0F;

        //Condition-7: current OPERATING PROFIT MARGIN Between 8-20%
        boolean opmCondition = (operatingProfitMarginFloatInPercent >= 8 && operatingProfitMarginFloatInPercent <= 20);

        //Condition-8: (D/E between 0-.20)
        boolean deCondition = (deFloatInAbsolute >= 0.0 && deFloatInAbsolute <= .20F);

        //Condition-9: All period avg  of ROE > 15% (past 10 yrs)
        boolean allYearRoeCondition = allYearRoeAvg > .15F;

        //Condition-10: Long term debt < 5*annual PAT (latest pat)
        boolean longTermDebtWithPatCondition = longTermDebtFloat < 5 * patFloat;

        //Condition-11: EPS GROWTH>0 for past 10 yrs
        boolean isAllYearEpsGrowthPositive = isAllYearEpsGrowthPositive(companyId);

        //Condition-12: ROTC (EQUITY + Debt) > 12%
        //Exceptional condition: if [(company's "Research SubArea" = "Financials") AND ("TOTAL capital" = "-" )] then ROTC condition is TRUE
        boolean rotcCondition = (FINANCIALS.equals(sector) && totalCapitalFloat == 0.0F) || (patFloat / totalCapitalFloat) > .12F;

        //Condition-13: Earning yield ((latest)EPS/SHARE PRICE(cmp)) > 10y BOND Yields
        boolean epsPerShareCondition=(epsFloat/cmpFloat)> _10YrBondYieldInAbsolute;

        //Condition-14: Retained earnings per share > 12 (past 10 yrs)
        //Exceptional condition: if "retained earnings" = "-" then "return using retained earnings per share" condition is TRUE.
        boolean retainedEarningPerShare = retainedEarningFloat == 0.0F || (retainedEarningFloat/totalOutstandigShareFloat) > 12.0F;

        boolean finalCondition = totalFreeCashFlowConditionCondition && salesGrowthComparisionWithEpsGrowthCondition && _1YearEpsGrowthCondition && peWithNifty50Condition && currentRatio
                && roeCondition && opmCondition && deCondition && allYearRoeCondition && longTermDebtWithPatCondition && isAllYearEpsGrowthPositive
                && rotcCondition && epsPerShareCondition && retainedEarningPerShare;

        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------");
        sb.append("\nStock: [").append(companyDetails.getCompanyId()).append("::").append(companyDetails.getTicker()).append("] - ").append("Condition Matched: ").append(finalCondition);
        sb.append("\n--------------------------------------------------------------------------");
        sb.append("\nepsFloat: ----------------------------------------------------- (").append(epsFloat);
        sb.append("\nCmp:----------------------------------------------------------- (").append(cmpFloat);
        sb.append("\nmcap[ShareOutstanding * CMP]: --------------------------------- (").append(mcapFloat);
        sb.append("\npe: ----------------------------------------------------------- (").append(peFloat);
        sb.append("\nnifty50Pe: ---------------------------------------------------- (").append(nifty50Pe);
        sb.append("\ntotalFreeCashflowFloat: --------------------------------------- (").append(totalFreeCashflowFloat);
        sb.append("\n_1YrEpsGrowthInPercentage: ------------------------------------ (").append(_1YrEpsGrowthInPercentage).append("%");
        sb.append("\nsalesGrowth: -------------------------------------------------- (").append(salesGrowth);
        sb.append("\ncurrentAsset: ------------------------------------------------- (").append(currentAssetFloat);
        sb.append("\ncurrentLiabilities: ------------------------------------------- (").append(currentLiabilitiesFloat);
        sb.append("\ncurrentRatio: ------------------------------------------------- (").append(currentRatioFloat);
        sb.append("\nroe(%): ------------------------------------------------------- (").append(roeFloatInPercent);
        sb.append("\noperatingProfitMargin(%): ------------------------------------- (").append(operatingProfitMarginFloatInPercent);
        sb.append("\nde(InAbsolute): ----------------------------------------------- (").append(deFloatInAbsolute);
        sb.append("\nlongTermDebt: ------------------------------------------------- (").append(longTermDebtFloat);
        sb.append("\nAvg Roe(All years): ------------------------------------------- (").append(allYearRoeAvg);
        sb.append("\n10Yr-Bond Yield(%): ------------------------------------------- (").append(_10Yr_BOND_YIELD_PERCENTAGE);
        sb.append("\npat: ---------------------------------------------------------- (").append(patFloat);
        sb.append("\ntotalCapital: ------------------------------------------------- (").append(totalCapitalFloat);
        sb.append("\nretainedEarning: ---------------------------------------------- (").append(retainedEarningFloat);
        sb.append("\ntotalOutstandigShare (mcap/cmp): ------------------------------ (").append(totalOutstandigShareFloat);
        sb.append("\n\n");
        sb.append("\nCondition-1:  [Total free cash flow > 0 ] -------------------------------------------------------- ").append(totalFreeCashFlowConditionCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-2:  [(sales growth >= 70% of EPS growth)  or (sales growth > 7%)] ---------------------- ").append(salesGrowthComparisionWithEpsGrowthCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-3:  [1 Y EPS growth (between 7-20%)]  -------------------------------------------------- ").append(_1YearEpsGrowthCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-4:  [P/E ratio between 40-60% of Nifty50 index p/e] ------------------------------------ ").append(peWithNifty50Condition ? "TRUE" : "FALSE");
        sb.append("\nCondition-5:  [current ratio >2] ----------------------------------------------------------------- ").append(currentRatio ? "TRUE" : "FALSE");
        sb.append("\nCondition-6:  (CURRENT ROE >27%) ----------------------------------------------------------------- ").append(roeCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-7:  (current OPERATING PROFIT MARGIN Between 8-20%) ------------------------------------ ").append(opmCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-8:  (D/E between 0-.20)----------------------------------------------------------------- ").append(deCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-9:  [all period avg  of ROE > 15% (past 10 yrs)]---------------------------------------- ").append(allYearRoeCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-10: [Long term debt < 5*annual PAT (latest pat)] --------------------------------------- ").append(longTermDebtWithPatCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-11: [EPS GROWTH>0 for past 10 yrs] ----------------------------------------------------- ").append(isAllYearEpsGrowthPositive ? "TRUE" : "FALSE");
        sb.append("\nCondition-12: [ROTC (EQUITY + Debt) > 12%] ------------------------------------------------------- ").append(rotcCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-13: [Earning yield ((latest)EPS/SHARE PRICE(cmp)) > 10y BOND Yields] ------------------- ").append(epsPerShareCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-14: [retained earnings per share > 12 (past 10 yrs)] ----------------------------------- ").append(retainedEarningPerShare ? "TRUE" : "FALSE");

        logger.info(" Condition Attibutes{}", sb.toString());
        sb.setLength(0);

        return finalCondition;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();
        float cmpFloat = companyDetails.getCmpFloat();

        String totalFreeCashflow = latestEarningPreview.getTotalFreeCashflow();
        String salesGrowth = String.valueOf(findLatestRevenueGrowth(companyId));
        String _1YrEpsGrowth = String.valueOf(find1YrEpsGrowthInPercentage(companyId));
        String peRatio = companyDetails.getPe();
        String currentRatio = String.valueOf(latestEarningPreview.getCurrentLiabilitiesFloat() != 0.0F ? (latestEarningPreview.getCurrentAssetFloat() / latestEarningPreview.getCurrentLiabilitiesFloat()) : 0.0F);
        String currentRoe = String.valueOf(latestEarningPreview.getRoeFloat());
        String currenitOperatingProfitMarginInPercent = String.valueOf(latestEarningPreview.getOperatingProfitMarginFloat());
        String de = latestEarningPreview.getDe();
        String roeAvg = String.valueOf(findAllYearRoeAvg(companyId));
        String longTermDebt = latestEarningPreview.getLongTermDebt();
        String annualPat = latestEarningPreview.getPat();
        String isAllYrEpsGrowthPositive = String.valueOf(isAllYearEpsGrowthPositive(companyId));
        String rotc = String.valueOf(latestEarningPreview.getPatFloat() / latestEarningPreview.getTotalCapitalFloat());
        String earningYield = String.valueOf(latestEarningPreview.getEpsFloat() / cmpFloat);
        String retainedEarning = String.valueOf(latestEarningPreview.getRetainedEarningFloat());
        String totalOutstandigShare = String.valueOf((companyDetails.getShareOutStandingFloat() * cmpFloat) / cmpFloat);


        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);

        //CompanyId
        sqlQuery.setParameter(0, Integer.parseInt(companyId));
        sqlQuery.setParameter(1, StringUtils.isEmpty(companyName) ? "-" : companyName);
        sqlQuery.setParameter(2, StringUtils.isEmpty(totalFreeCashflow) ? "-" : totalFreeCashflow);
        sqlQuery.setParameter(3, StringUtils.isEmpty(salesGrowth) ? "-" : salesGrowth);
        sqlQuery.setParameter(4, StringUtils.isEmpty(_1YrEpsGrowth) ? "-" : _1YrEpsGrowth);
        sqlQuery.setParameter(5, StringUtils.isEmpty(peRatio) ? "-" : peRatio);
        sqlQuery.setParameter(6, StringUtils.isEmpty(currentRatio) ? "-" : currentRatio);
        sqlQuery.setParameter(7, StringUtils.isEmpty(currentRoe) ? "-" : currentRoe);
        sqlQuery.setParameter(8, StringUtils.isEmpty(currenitOperatingProfitMarginInPercent) ? "-" : currenitOperatingProfitMarginInPercent);
        sqlQuery.setParameter(9, StringUtils.isEmpty(de) ? "-" : de);
        sqlQuery.setParameter(10, StringUtils.isEmpty(roeAvg) ? "-" : roeAvg);
        sqlQuery.setParameter(11, StringUtils.isEmpty(longTermDebt) ? "-" : longTermDebt);
        sqlQuery.setParameter(12, StringUtils.isEmpty(annualPat) ? "-" : annualPat);
        sqlQuery.setParameter(13, StringUtils.isEmpty(isAllYrEpsGrowthPositive) ? "-" : isAllYrEpsGrowthPositive);
        sqlQuery.setParameter(14, StringUtils.isEmpty(rotc) ? "-" : rotc);
        sqlQuery.setParameter(15, StringUtils.isEmpty(earningYield) ? "-" : earningYield);
        sqlQuery.setParameter(16, StringUtils.isEmpty(retainedEarning) ? "-" : retainedEarning);
        sqlQuery.setParameter(17, StringUtils.isEmpty(totalOutstandigShare) ? "-" : totalOutstandigShare);
        sqlQuery.executeUpdate();
    }
}
