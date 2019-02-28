package com.finvendor.api.webutil;

import com.finvendor.common.util.Pair;
import com.finvendor.common.util.StringUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Web Utility class
 *
 * @author ayush
 * @since 07-Jan-2018
 */
public final class WebUtil {

    private static final String LOGGED_IN_USER = "loggedInUser";

    /**
     * Equity Research Broker Year of Incorporation json constants
     */
    public static final String EQUITY_RESEARCH_FILTER_VALUE_RESEARCH_DATE_JSON = "[{\"researchDate\":\"< 3 months\"},{\"researchDate\":\"3 - 6 months\"},{\"researchDate\":\"6 - 12 months\"},{\"researchDate\":\"> 12 months\"}]";

    /**
     * Equity Research upside json contants
     */
    public static final String EQUITY_RESEARCH_FILTER_VALUE_BROKER_RANK_JSON = "[{\"broker_rank\":\"5 star (Success rate > 80%)\"},{\"broker_rank\":\"4 star (Success rate >= 65% & < 80%)\"},{\"broker_rank\":\"3 star (Success rate >= 50% & < 65%)\"},{\"broker_rank\":\"2 star (Success rate >= 40% & < 50%)\"},{\"broker_rank\":\"1 star (Success rate < 40%)\"}]";

    /**
     * Equity Research others json contants
     */
    public static final String EQUITY_RESEARCH_FILTER_VALUE_OTHERS_JSON = "[{\"others\":\"Award Winning Analyst\"},{\"others\":\"Research Reports by CFA\"}]";

    /**
     * Equity Research upside json constant
     */
    public static final String EQUITY_RESEARCH_FILTER_VALUE_UPSIDE_JSON = "[{\"upside\":\"<0%\"},{\"upside\":\"0-20%\"},{\"upside\":\"20-50%\"},{\"upside\":\"50-100%\"},{\"upside\":\">100%\"}]";

    /**
     * Equity Research AnalystType json constant
     */
    public static final String EQUITY_RESEARCH_FILTER_VALUE_ANALYST_TYPE_JSON = "[{\"analystType\":\"Independent Research Analyst\"},{\"analystType\":\"Research Broker\"}]";

    /**
     * Equity Research Market Capitalisation json constant
     */

    public static final String EQUITY_RESEARCH_FILTER_VALUE_MCAP_JSON = "[{\"mcap_name\":\"Large Cap: > $5Bn\"},{\"mcap_name\":\"Mid Cap: > $1Bn & < $5Bn\"},{\"mcap_name\":\"Small Cap: > $300M & < $1Bn\"},{\"mcap_name\":\"Micro Cap: > $50M  & < $300M\"}]";
    /**
     * Sql Data holder map
     * <p>
     * format: sqlQuery,columnNameAndNewValue Map-oldvalue-with-new-value,
     * conditionValue,firstDefaultMap,lastDefaultMap,colIndex
     */
    @SuppressWarnings("serial")
    public static final Map<String, SqlData> filterTypeMap = new LinkedHashMap<>();

    static {
        final Object[] conitionValueAsNull = null;
        final LinkedHashMap<String, Object> firstDefaultParamsMapAsNull = null;
        final LinkedHashMap<String, Object> lastDefaultParamsMapAsNull = null;
        final int columnIndex_0 = 0;
        final int columnIndex_1 = 1;

        /*
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * 			Equity Search :: F I L T E R - V A L U E (SQL QUERY Data)
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         *
         * */
        // Country
        filterTypeMap.put("country",
                new SqlData("select country_id, name from country where name in (?,?)",
                        new ArrayList<ColumnNameAndNewValue>() {{
                            add(new ColumnNameAndNewValue("countryId", null));
                            add(new ColumnNameAndNewValue("name", null));
                        }},
                        new Object[]{"India","Singapore"},
                        firstDefaultParamsMapAsNull,
                        lastDefaultParamsMapAsNull,
                        columnIndex_0));

        //marketcapital
        filterTypeMap.put("marketcapital",
                new SqlData("select comp_mkt_cap_type.company_id,market_cap_def.market_cap_name from comp_mkt_cap_type,market_cap_def where comp_mkt_cap_type.market_cap_id=market_cap_def.market_cap_id group by market_cap_def.market_cap_name order by comp_mkt_cap_type.company_id",
                        new ArrayList<ColumnNameAndNewValue>() {{
                            add(new ColumnNameAndNewValue("mcap_name",
                                    new String[][]{{"Large Cap", "Large Cap: > $5Bn"},
                                            {"Mid Cap", "Mid Cap: $1Bn < & < $5Bn"},
                                            {"Small Cap", "Small Cap: $300M < & < $1Bn"},
                                            {"Micro Cap", "Micro Cap: $50M < & < $300M"}}));
                        }},
                        conitionValueAsNull,
                        firstDefaultParamsMapAsNull,
                        lastDefaultParamsMapAsNull,
                        columnIndex_1));

        // Style
        filterTypeMap.put("style",
                new SqlData("SELECT stock_class_type_id,stock_class_name FROM rsch_area_stock_class  ORDER BY stock_class_name",
                        new ArrayList<ColumnNameAndNewValue>() {{
                            add(new ColumnNameAndNewValue("stockClassificationName", null));
                        }},
                        conitionValueAsNull,
                        firstDefaultParamsMapAsNull,
                        lastDefaultParamsMapAsNull,
                        columnIndex_1));

        //researchBroker as CompanyName - earlier was "UserName"
        filterTypeMap.put("researchBroker",
                new SqlData("select a.vendor_id,a.vendor_company from vendor_report_data a group by a.vendor_id order by a.vendor_company",
                        new ArrayList<ColumnNameAndNewValue>() {{
                            add(new ColumnNameAndNewValue("companyName", null));
                        }},
                        conitionValueAsNull,
                        firstDefaultParamsMapAsNull,
                        lastDefaultParamsMapAsNull,
                        columnIndex_1));

        //BrokerYrOfInCorp - Constant is defined, See at the top of class

        //brokerRank Constant is defined, See at the top of class

        //recommType
        filterTypeMap.put("recommType",
                new SqlData("select a.product_id,a.rsrch_recomm_type from vendor_report_data a group by a.rsrch_recomm_type order by a.rsrch_recomm_type",
                        new ArrayList<ColumnNameAndNewValue>() {{
                            add(new ColumnNameAndNewValue("rsrchRecommType", null));
                        }},
                        conitionValueAsNull,
                        firstDefaultParamsMapAsNull,
                        lastDefaultParamsMapAsNull,
                        columnIndex_1));

        //Research Sub Area (Sub Sector or Sub-Industry)
        filterTypeMap.put("industry",
                new SqlData("select research_area_id, description from  research_sub_area where research_area_id='7' and description not like 'All Sectors' order by description",
                        new ArrayList<ColumnNameAndNewValue>() {{
                            add(new ColumnNameAndNewValue("description", null));
                        }},
                        conitionValueAsNull,
                        firstDefaultParamsMapAsNull,
                        lastDefaultParamsMapAsNull,
                        columnIndex_1));

        //Others - Constant is defined, See at the top of class

        //Upside - Constant is defined, See at the top of class

        /*
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * 			Sector Search :: F I L T E R - V A L U E (SQL QUERY Data)
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         *
         * */

        /*
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * 			Macro Search :: F I L T E R - V A L U E (SQL QUERY Data)
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         *
         * */
    }

    // Forbidden instantiation
    private WebUtil() {
    }

    /**
     * Holder for Table's column name and it new value
     */
    public static class ColumnNameAndNewValue {
        private String colName;
        private String[][] newValues;

        public ColumnNameAndNewValue(String colName, String[][] newValues) {
            super();
            this.colName = colName;
            this.newValues = newValues;
        }

        public String getColName() {
            return colName;
        }

        public String[][] getNewValues() {
            return newValues;
        }
    }

    /**
     * Hold sql information like query, column name, where clause condition value, column index
     * and fist and last default value
     *
     * @return array of json format where each element is corresponding to column name and value
     */
    public static class SqlData {
        private String sql;
        private Object[] conitionValue;
        Map<String, Map<String, String>> columnNameAndNewValueMap;
        Map<String, Object> firstDefaultParamsMap;
        private int colIndex;
        private LinkedHashMap<String, Object> lastDefaultParamsMap;

        public SqlData(String sql,
                       ArrayList<ColumnNameAndNewValue> replaceColumnValueWithNewValue,
                       Object[] conitionValue,
                       LinkedHashMap<String, Object> firstDefaultParamsMap,
                       LinkedHashMap<String, Object> lastDefaultParamsMap,
                       int colIndex) {
            super();
            this.sql = sql;
            this.conitionValue = conitionValue;
            this.firstDefaultParamsMap = firstDefaultParamsMap;
            this.lastDefaultParamsMap = lastDefaultParamsMap;
            this.colIndex = colIndex;
            columnNameAndNewValueMap = new LinkedHashMap<>();
            for (ColumnNameAndNewValue colVal : replaceColumnValueWithNewValue) {
                final String colName = colVal.getColName();
                String[][] newValues = colVal.getNewValues();

                if (newValues != null) {
                    final Map<String, String> replaceColumnValueWithNewValueMap = new HashMap<>();
                    for (final String[] newValuesStrArr : newValues) {
                        replaceColumnValueWithNewValueMap.put(newValuesStrArr[0], newValuesStrArr[1]);
                    }
                    columnNameAndNewValueMap.put(colName, replaceColumnValueWithNewValueMap);
                } else {
                    columnNameAndNewValueMap.put(colName, null);
                }

            }
        }

        public String getSql() {
            return sql;
        }

        public Map<String, Map<String, String>> getColumnNameAndNewValueMap() {
            return columnNameAndNewValueMap;
        }

        public Object[] getConitionValue() {
            return conitionValue;
        }

        public Map<String, Object> getFirstDefaultParamsMap() {
            return firstDefaultParamsMap;
        }

        public LinkedHashMap<String, Object> getLastDefaultParamsMap() {
            return lastDefaultParamsMap;
        }

        public int getColIndex() {
            return colIndex;
        }
    }

    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 			O T H E R - Utilities Methods
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * */
    public static String getLoggedInUser(HttpServletRequest request) throws Exception {
        final User loggedInUser = (User) request.getSession().getAttribute(LOGGED_IN_USER);
        if (loggedInUser == null) {
            throw new Exception("Unable to find logged In user!!");
        }
        return loggedInUser.getUsername();
    }

    public static String buildReportPath(String productId, CommonsMultipartFile multiPartFile, String userName,
                                         String basePath) {
        // Upload filename suffix with productId
        // Reason: loggedIn user can upload multiple file while upload
        final Pair<String, String> fileNameAndExtention = StringUtil
                .getFileNameAndExtention(multiPartFile.getOriginalFilename());

        final String uploadFileNameOnly = fileNameAndExtention.getElement1() + "_" + productId
                + fileNameAndExtention.getElement2();
        final String reportResearchOfferingFilePath = StringUtil.builtPath(uploadFileNameOnly, basePath, userName);
        return reportResearchOfferingFilePath;
    }


    public static void processDownload(HttpServletResponse response, String productId,
                                       String fileName, Pair<Long, InputStream> download) throws Exception {
        InputStream fileInputStream = download.getElement2();
        Long fileLength = download.getElement1();
        if (fileInputStream == null) {
            throw new Exception("File does not exist for id=" + productId);
        } else {
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }
}
