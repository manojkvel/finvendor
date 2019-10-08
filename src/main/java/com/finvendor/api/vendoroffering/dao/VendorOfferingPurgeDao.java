package com.finvendor.api.vendoroffering.dao;

import com.finvendor.common.commondao.ICommonDao;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorOfferingPurgeDao {
    private static final Logger logger = LoggerFactory.getLogger(VendorOfferingPurgeDao.class.getName());

    private static final String FIND_LATEST_REPORT_DATE_SQL = "select a.product_id, a.report_date from vendor_report_data a order by STR_TO_DATE(a.report_date, '%d/%c/%Y') DESC limit 1 offset 0";
    private static final String FIND_1_YEAR_OLD_VENDOR_REPORT_DATA_SQL = "select a.product_id,a.product_name,a.report_date from vendor_report_data a where STR_TO_DATE(a.report_date, '%d/%c/%Y') < STR_TO_DATE(LATEST_REPORT_DATE, '%d/%c/%Y') order by STR_TO_DATE(a.report_date, '%d/%c/%Y') DESC";
    private static final String DELETE_PRODUCT_ID_FROM_VENDOR_REPORT_FILE_TABLE_SQL = "delete from vendor_report_file where product_id = ?";
    private static final String DELETE_PRODUCT_ID_FROM_VENDOR_REPORT_DATA_TABLE_SQL = "delete from vendor_report_data where product_id = ?";

    private final ICommonDao commonDao;

    @Autowired
    public VendorOfferingPurgeDao(ICommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public void purgeVendorReportData() {
        try {
            String newQuery = FIND_1_YEAR_OLD_VENDOR_REPORT_DATA_SQL.replace("LATEST_REPORT_DATE", findLatestReportDate());
            logger.info("## FIND_1_YEAR_OLD_VENDOR_REPORT_DATA_SQL: {}", newQuery);
            SQLQuery nativeQuery = commonDao.getNativeQuery(newQuery, null);
            List<Object[]> rows = nativeQuery.list();

            StringBuilder productIdSb = new StringBuilder(500);
            productIdSb.append("Purging following ProductIds").append("\n");
            StringBuilder unableToDeleteProductIdSb = new StringBuilder(500);
            unableToDeleteProductIdSb.append("Unable to Purge following ProductIds").append("\n");
            for (Object[] row : rows) {
                String productId = row[0] != null ? row[0].toString().trim() : "";
                String productName = row[1] != null ? row[1].toString().trim() : "";
                String reportDate = row[2] != null ? row[2].toString().trim() : "";
                //delete from vendor report file table for each productId
                SQLQuery deleteQuery = commonDao
                        .getNativeQuery(DELETE_PRODUCT_ID_FROM_VENDOR_REPORT_FILE_TABLE_SQL, new Object[] { productId });
                int count = deleteQuery.executeUpdate();
                if (count == 1) {
                    productIdSb.append("Deleted ProductId from vendor_report_file: ").append(productId).append(" ProductName: ")
                            .append(productName).append(" Report Date: ").append(reportDate).append("\n");

                    //delete from vendor report data table for each productId
                    deleteQuery = commonDao.getNativeQuery(DELETE_PRODUCT_ID_FROM_VENDOR_REPORT_DATA_TABLE_SQL, new Object[] { productId });
                    count = deleteQuery.executeUpdate();
                    if (count == 1) {
                        productIdSb.append("Deleted ProductId from vendor_report_data: ").append(productId).append(" ProductName: ")
                                .append(productName).append(" Report Date: ").append(reportDate).append("\n");
                    }
                    else {
                        unableToDeleteProductIdSb.append("Unable To Deleted ProductId from vendor_report_data: ").append(productId)
                                .append(" ProductName: ").append(productName).append(" Report Date: ").append(reportDate).append("\n");
                    }
                }
                else {
                    unableToDeleteProductIdSb.append("Unable To Delete ProductId from vendor_report_file: ").append(productId)
                            .append(" ProductName: ").append(productName).append(" Report Date: ").append(reportDate).append("\n");
                }
            }
            logger.info("{}", productIdSb.toString());
            logger.info("{}", unableToDeleteProductIdSb.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String findLatestReportDate() throws Exception {
        String _1YearOldDate = null;
        logger.info("## FIND_LATEST_REPORT_DATE_SQL: {}", FIND_LATEST_REPORT_DATE_SQL);
        SQLQuery nativeQuery = commonDao.getNativeQuery(FIND_LATEST_REPORT_DATE_SQL, null);
        List<Object[]> rows = nativeQuery.list();

        //Size of this loop is 1
        for (Object[] row : rows) {
            String reportDate;
            if (row[1] == null) {
                throw new Exception("### reportDate must not be null");
            }
            else {
                reportDate = row[1].toString().trim();
                _1YearOldDate = construct1YearOldDate(reportDate);
                logger.info("_1YearOldDate: {}", _1YearOldDate);
            }
        }
        return _1YearOldDate;
    }

    private String construct1YearOldDate(String reportDate) {
        String[] reportDate_day_month_year = reportDate.split("//"); // report date -> 18/06/2019
        String day = reportDate_day_month_year[0];
        String month = reportDate_day_month_year[1];
        String year = reportDate_day_month_year[2];
        int yearInt = Integer.parseInt(year);
        yearInt--;
        return day + "/" + month + "/" + yearInt;
    }
}
