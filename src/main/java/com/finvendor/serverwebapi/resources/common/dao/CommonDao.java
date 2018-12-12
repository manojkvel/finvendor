package com.finvendor.serverwebapi.resources.common.dao;

import com.finvendor.server.common.commondao.ICommonDao;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDao {

    @Autowired
    private ICommonDao commonDao;

    public void saveVo() {
        try {
            for (int i = 0; i < 647;i++) {
                String query = "select * from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b,ven_rsrch_rpt_analyst_prof c where a.product_id=b.product_id and b.product_id=c.product_id limit 1 offset "+i;
                SQLQuery sqlQuery = commonDao.getNativeQuery(query, null);
                List<Object[]> rows = sqlQuery.list();
                for (Object[] row : rows) {
                    String productId = row[0] != null ? row[0].toString().trim() : "";
                    String productName = row[1] != null ? row[1].toString().trim() : "";
                    String productDescription = row[2] != null ? row[2].toString().trim() : "";
                    String vendorId = row[3] != null ? row[3].toString().trim() : "";
                    String launchedYear = row[4] != null ? row[4].toString().trim() : "";
                    String researchArea = row[5] != null ? row[5].toString().trim() : "";
                    String researchSubArea = row[6] != null ? row[6].toString().trim() : "";
                    String stockFundIssueCovered = row[7] != null ? row[7].toString().trim() : "";
                    String accessibility = row[9] != null ? row[9].toString().trim() : "";
                    String suitability = row[10] != null ? row[10].toString().trim() : "";
                    String subCostPy = row[11] != null ? row[11].toString().trim() : "";
                    String repFormat = row[12] != null ? row[12].toString().trim() : "";
                    String companyId = row[13] != null ? row[13].toString().trim() : "";
                    String repDate = row[14] != null ? row[14].toString().trim() : "";
                    String targetPrice = row[15] != null ? row[15].toString().trim() : "";
                    String rsrchRcommType = row[16] != null ? row[16].toString().trim() : "";
                    String priceAtRecomm = row[17] != null ? row[17].toString().trim() : "";
                    String rsrchReportDesc = row[18] != null ? row[18].toString().trim() : "";
                    String rsrchReportAccess = row[19] != null ? row[19].toString().trim() : "";
                    //String rsrchUploadReport = row[20] != null ? row[20].toString().trim() : "";
                    String reportName = row[21] != null ? row[21].toString().trim() : "";
                    String analystName = row[23] != null ? row[23].toString().trim() : "";
                    String analystAwards = row[24] != null ? row[24].toString().trim() : "";
                    String analystCfaCharter = row[25] != null ? row[25].toString().trim() : "";

                    //Insert into vendor_report_data table
                    String insertQuery = "insert into vendor_report_data values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, productId);
                    sqlQuery.setParameter(1, productName);
                    sqlQuery.setParameter(2, productDescription);

                    String vendorQuery = "select a.vendor_id,a.username,a.company,a.analystType from vendor a where a.vendor_id=?";
                    SQLQuery sqlQuery1 = commonDao.getNativeQuery(vendorQuery, new String[]{vendorId});
                    List<Object[]> rows1 = sqlQuery1.list();
                    String vendorName = "";
                    String vendorCompany = "";
                    String vendorAnalystType = "";
                    for (Object[] row1 : rows1) {
                        vendorName = row1[1] != null ? row1[1].toString().trim() : "";
                        vendorCompany = row1[2] != null ? row1[2].toString().trim() : "";
                        vendorAnalystType = row1[3] != null ? row1[3].toString().trim() : "";
                    }

                    sqlQuery.setParameter(3, vendorId);
                    sqlQuery.setParameter(4, vendorName);
                    sqlQuery.setParameter(5, vendorCompany);
                    sqlQuery.setParameter(6, vendorAnalystType);

                    sqlQuery.setParameter(7, launchedYear);
                    sqlQuery.setParameter(8, researchArea);
                    sqlQuery.setParameter(9, researchSubArea);
                    sqlQuery.setParameter(10, stockFundIssueCovered);
                    sqlQuery.setParameter(11, accessibility);
                    sqlQuery.setParameter(12, suitability);
                    sqlQuery.setParameter(13, subCostPy);
                    sqlQuery.setParameter(14, repFormat);
                    sqlQuery.setParameter(15, companyId);
                    sqlQuery.setParameter(16, repDate);
                    sqlQuery.setParameter(17, targetPrice);
                    sqlQuery.setParameter(18, rsrchRcommType);
                    sqlQuery.setParameter(19, priceAtRecomm);
                    sqlQuery.setParameter(20, rsrchReportDesc);
                    sqlQuery.setParameter(21, rsrchReportAccess);
                    sqlQuery.setParameter(22, reportName);
                    sqlQuery.setParameter(23, analystName);
                    sqlQuery.setParameter(24, analystAwards);
                    sqlQuery.setParameter(25, analystCfaCharter);
                    sqlQuery.executeUpdate();

                    //Insert into vendor_report_file table
                    insertQuery = "insert into vendor_report_file values (?,?)";
                    sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, productId);
                    Object o = row[20];
                    sqlQuery.setParameter(1, o);
                    sqlQuery.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
