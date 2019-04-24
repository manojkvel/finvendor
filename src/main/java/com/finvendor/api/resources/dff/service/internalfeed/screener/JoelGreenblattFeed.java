package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.CompanyDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.EarningPreviewDetails;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.JoelEbitAndEnterpriseDto;
import com.finvendor.api.resources.dff.service.internalfeed.screener.dto.JoelRotcDto;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Map<Integer, JoelRotcDto> top_50_rotc = find_top_50_Rotc();
        Map<Integer, JoelEbitAndEnterpriseDto> top_50_ebit = find_top_50_EBIT();
        Set<Integer> top_50_rotc_stock_id = top_50_rotc.keySet();
        Set<Integer> top_50_ebit_stock_id = top_50_ebit.keySet();
        top_50_rotc_stock_id.retainAll(top_50_ebit_stock_id);


        //        List<CompanyDetails> companyDetailsList = findCompanyDetails();
//        for (CompanyDetails companyDetails : companyDetailsList) {
//            EarningPreviewDetails latestEarningPreview = findLatestEarningPreview(companyDetails.getCompanyId());
//            if (latestEarningPreview == null) {
//                continue;
//            }
//            if (evalCondition(companyDetails, latestEarningPreview)) {
//                insertFeed(companyDetails, latestEarningPreview);
//            }
//        }
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

    public static void main(String[] args) {
        Set<Integer> s1=new HashSet<>();
        s1.add(1);
        s1.add(2);
        s1.add(3);
        Set<Integer> s2=new HashSet<>();
        s2.add(2);
        s2.add(3);
        s1.retainAll(s2);
        System.out.println(s1);
    }
}
