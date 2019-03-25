//package com.finvendor.api.resources.admin.dao;
//
//import com.finvendor.model.IndexCompDetails;
//import com.finvendor.common.commondao.GenericDao;
//import com.finvendor.common.commondao.ICommonDao;
//import org.apache.commons.lang.StringUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.hibernate.SQLQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.*;
//
//@Repository
//@Transactional
//public class DataFeedDao extends GenericDao<IndexCompDetails> {
//
//    @Autowired
//    private ICommonDao commonDao;
//
//    public int insertIndexCompanyDetails(String filePath) {
//        Map<String, List<String>> companyIdWithIndexMap = new LinkedHashMap<>();
//        try {
//            File excelFile = new File(filePath);
//            FileInputStream fis = new FileInputStream(excelFile);
//
//            // we create an XSSF Workbook object for our XLSX Excel File
//            XSSFWorkbook workbook = new XSSFWorkbook(fis);
//            // we get first sheet
//            XSSFSheet sheet = workbook.getSheetAt(0);
//
//            // we iterate on rows
//            Iterator<Row> rowIt = sheet.iterator();
//            if (rowIt.hasNext()) {
//                rowIt.next();
//            }
//
//            while (rowIt.hasNext()) {
//                Row row = rowIt.next();
//
//                // iterate on cells for the current row
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                String isin = "";
//                String index = "";
//                if (cellIterator.hasNext()) {
//                    cellIterator.next();
//                }
//                if (cellIterator.hasNext()) {
//                    cellIterator.next();
//                }
//                if (cellIterator.hasNext()) {
//                    isin = cellIterator.next().toString().trim();
//                }
//                if (cellIterator.hasNext()) {
//                    SQLQuery query1 = commonDao.getNativeQuery("select a.company_id,a.isin_code from rsch_sub_area_company_dtls a where a.isin_code=?", new String[]{isin});
//                    List<Object[]> rows = query1.list();
//                    String companyId = "";
//                    for (Object[] tblRow : rows) {
//                        companyId = tblRow[0] != null ? tblRow[0].toString().trim() : "";
//                        break;
//                    }
//
//                    index = cellIterator.next().toString().trim();
//                    if (! StringUtils.isEmpty(index)) {
//                        String[] indexArr = index.split(",");
//                        List<String> indexList = new ArrayList<>();
//                        for (String indexName : indexArr) {
//                            indexList.add(indexName.trim());
//                        }
//                        companyIdWithIndexMap.put(companyId, indexList);
//                        System.out.println("isin: " + isin + " index: " + index);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        Set<Map.Entry<String, List<String>>> entries = companyIdWithIndexMap.entrySet();
//        for (Map.Entry<String, List<String>> entry : entries) {
//            String companyId = entry.getKey();
//            List<String> indexIds = entry.getValue();
//            for (String indexId : indexIds) {
//                IndexCompDetails indexCompDetails = new IndexCompDetails();
//                indexCompDetails.setIndexId(indexId);
//                indexCompDetails.setCompanyId(companyId);
//                indexCompDetails.setTimestamp(String.valueOf(Calendar.getInstance().getTimeInMillis()));
//                save(indexCompDetails);
//            }
//        }
//
//
//        return 0;
//    }
//}
