package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MartinZweigFeed extends AbstractScreenerFeed {
    private static final String INSERT_QUERY = "insert into strategy_martin_zweig values(?,?,?,?,?,?,?,?);";
    private static final String DELETE_QUERY = "delete from strategy_martin_zweig";

    @Override
    public boolean processAndFeed() throws Exception {
        deleteAllRecordsFromStrategyTable("MARTIN ZWEIG STRATEGY", DELETE_QUERY);
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


            if (evalMartinZweigCondition(companyDetails, latestEarningPreview)) {
                String nifty50Pe="0.0";
                insertFeed(companyDetails, latestEarningPreview, nifty50Pe);
                totalMatch++;
            }
            else {
                totalMisMatch++;
            }
        }
        logger.info("Martin Zweig strategy :: Total Stock Matched: {}", totalMatch);
        logger.info("Martin Zweig strategy :: Total Stock *** Mis-Matched: {}", totalMisMatch);
        return true;
    }

    private boolean evalMartinZweigCondition(CompanyDetails companyDetails, EarningPreviewDetails latestEarningPreview) {

        String stockId = companyDetails.getCompanyId();

        float peFloat = companyDetails.getPeFloat();
        float latestRevenueGrowth = findLatestRevenueGrowth(stockId);
        float allYearEpsGrowth = findAllYearEpsGrowth(stockId);
        float deFloat = latestEarningPreview.getDeFloat();

        boolean everyEpsGrowthGreaterThanPrevioudEpsGrowth = isEveryEpsGrowthGreaterThanPrevioudEpsGrowth(stockId);
        boolean finalCondition;
        boolean peCondition1 = peFloat >= 5 && peFloat <= 43;
        boolean peCondition2 = peFloat <= 3 * peFloat;
        boolean latestRevenueGrowthCondition = latestRevenueGrowth >= 85 || latestRevenueGrowth >= 30;
        boolean epsGrowthCondition = everyEpsGrowthGreaterThanPrevioudEpsGrowth;
        boolean allYearEpsGrowthCondition = allYearEpsGrowth > 15;
        boolean deCondition = deFloat < 2;
        finalCondition = peCondition1 && peCondition2 && latestRevenueGrowthCondition && epsGrowthCondition && allYearEpsGrowthCondition && deCondition;
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------");
        sb.append("\nStock: [").append(companyDetails.getCompanyId()).append("::").append(companyDetails.getTicker())
                .append("] - ").append("Condition Matched: ").append(finalCondition);
        sb.append("\n--------------------------------------------------------------------------");
        sb.append("\npe: ---------------------------------------------------- ").append(peFloat);
        sb.append("\nlatestRevenueGrowth:------------------------------------ ").append(latestRevenueGrowth);
        sb.append("\nallYearEpsGrowth: -------------------------------------- ").append(allYearEpsGrowth);
        sb.append("\ndeFloat: ----------------------------------------------- ").append(deFloat);
        sb.append("\neveryEpsGrowthGreaterThanPrevioudEpsGrowth: ------------ ").append(everyEpsGrowthGreaterThanPrevioudEpsGrowth);

        sb.append("\n\n");
        sb.append("\nCondition-1: [P/E between 5-43 ] ---------------------------------------------------------------------- ").append(peCondition1 ? "TRUE" : "FALSE");
        sb.append("\nCondition-2: [P/E <= 3 * Nifty50 P/E] ----------------------------------------------------------------- ").append(peCondition2? "TRUE" : "FALSE");
        sb.append("\nCondition-3: [(latest Revenue Growth >= 85% of EPS growth) or  (latest Revenue Growth >=30% YOY)] ----- ").append(latestRevenueGrowthCondition ? "TRUE" : "FALSE");
        sb.append("\nCondition-4: [eps growth Y1>Y2>Y3>Y4>Y5] -------------------------------------------------------------- ").append(epsGrowthCondition? "TRUE" : "FALSE");
        sb.append("\nCondition-5: [AllYr eps growth > =15%] ---------------------------------------------------------------- ").append(allYearEpsGrowthCondition? "TRUE" : "FALSE");
        sb.append("\nCondition-6: [D/E RATIO < 2] -------------------------------------------------------------------------- ").append(deCondition? "TRUE" : "FALSE");

        logger.info(" Condition Attibutes{}", sb.toString());
        sb.setLength(0);
        return finalCondition;
    }

    private void insertFeed(CompanyDetails companyDetails, EarningPreviewDetails earningPreview,
            String nifty50Pe ) {
        String companyId = companyDetails.getCompanyId();
        String companyName = companyDetails.getCompanyName();
        String pe = companyDetails.getPe();
        float latestRevenueGrowth = findLatestRevenueGrowth(companyId);
        boolean yearWiseEpsGrowth = isEveryEpsGrowthGreaterThanPrevioudEpsGrowth(companyId);
        float allYearEpsGrowth = findAllYearEpsGrowth(companyId);

        SQLQuery sqlQuery = commonDao.getNativeQuery(INSERT_QUERY, null);
        sqlQuery.setParameter(0, Integer.parseInt(companyId));
        sqlQuery.setParameter(1, StringUtils.isEmpty(companyName) ? "-" : companyName);
        sqlQuery.setParameter(2, StringUtils.isEmpty(pe) ? "-" : earningPreview.getDe());
        sqlQuery.setParameter(3, StringUtils.isEmpty(nifty50Pe) ? "-" : nifty50Pe);
        sqlQuery.setParameter(4, String.valueOf(latestRevenueGrowth));
        sqlQuery.setParameter(5, String.valueOf(yearWiseEpsGrowth));
        sqlQuery.setParameter(6, String.valueOf(allYearEpsGrowth));
        sqlQuery.setParameter(7, String.valueOf(earningPreview.getDe()));
        sqlQuery.executeUpdate();
    }

}
