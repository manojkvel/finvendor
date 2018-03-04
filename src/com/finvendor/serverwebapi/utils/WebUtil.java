package com.finvendor.serverwebapi.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.finvendor.common.util.Pair;
import com.finvendor.common.util.StringUtil;

/**
 * Web Utility class
 * 
 * @author ayush
 * @since 07-Jan-2018
 */
public final class WebUtil {

	public static final String brokerYrOfInCorpJson="[{\"brokerYrOfInCorp\":\"<= 3 Yrs\"},{\"brokerYrOfInCorp\":\"3 - 5 Yrs\"},{\"brokerYrOfInCorp\":\"5 - 10 Yrs\"},{\"brokerYrOfInCorp\":\"> 10 Yrs\"}]";
	public static final String othersJson="[{\"others\":\"Award Winning Analyst\"},{\"others\":\"Research Reports by CFA\"}]";
	public static final String upsideJson="[{\"upside\":\"<0%\"},{\"upside\":\"0-20%\"},{\"upside\":\"20-50%\"},{\"upside\":\"50-100\"},{\"upside\":\">100%\"}]";

	// Forbidden instantiation
	private WebUtil() {
	}
		
	public static class ColVal {
		private String colName;
		private String[][] newValues;

		public ColVal(String colName, String[][] newValues) {
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

	public static class SqlData {
		private String sql;
		private Object[] conitionValue;
		Map<String, Map<String, String>> columnNameMap;
		Map<String, Object> firstDefaultParamsMap;
		private int colIndex;
		private LinkedHashMap<String, Object> lastDefaultParamsMap;

		public SqlData(String sql, ArrayList<ColVal> replaceColumnValueWithNewValue, Object[] conitionValue,
				LinkedHashMap<String, Object> firstDefaultParamsMap,LinkedHashMap<String, Object> lastDefaultParamsMap, int colIndex) {
			super();
			this.sql = sql;
			this.conitionValue = conitionValue;
			this.firstDefaultParamsMap = firstDefaultParamsMap;
			this.lastDefaultParamsMap = lastDefaultParamsMap;
			this.colIndex = colIndex;
			columnNameMap = new LinkedHashMap<>();
			for (ColVal colVal : replaceColumnValueWithNewValue) {
				String colName = colVal.getColName();
				String[][] newValues = colVal.getNewValues();

				if (newValues != null) {
					Map<String, String> replaceColumnValueWithNewValueMap = new HashMap<>();
					for (String[] newValuesStrArr : newValues) {
						replaceColumnValueWithNewValueMap.put(newValuesStrArr[0], newValuesStrArr[1]);
					}
					columnNameMap.put(colName, replaceColumnValueWithNewValueMap);
				} else {
					columnNameMap.put(colName, null);
				}

			}
		}

		public String getSql() {
			return sql;
		}

		public Map<String, Map<String, String>> getColumnNameMap() {
			return columnNameMap;
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

	@SuppressWarnings("serial")
	public static Map<String, SqlData> typeMap = new LinkedHashMap<String, SqlData>() {
		{
			// Country
			put("country",
					new SqlData("select country_id, name from country where name in (?,?,?)", new ArrayList<ColVal>() {
				{

					add(new ColVal("countryId", null));
					add(new ColVal("name", null));
					// add(new ColVal("countryName", new String[][] { {
					// "India","**India" }, { "UK", "##UK" } }));

				}
			}, new Object[] { "India", "USA", "UK" }, null,null, 0));

			// format: sqlQuery,columnName-oldvalue with new value,conditionValue,firstDefaultMap,lastDefaultMap,colIndex
			put("marketcapital",
					new SqlData(
							"SELECT company_id, mcap_name FROM market_cap_def group by mcap_name order by mcap_name",
							new ArrayList<ColVal>() {
				{
					add(new ColVal("mcap_name",
							new String[][] { { "Large Cap", "Large Cap: > $5Bn" },
									{ "Mid Cap", "Mid Cap: $1Bn < & < $5Bn" },
									{ "Small Cap", "Small Cap: $300M < & < $1Bn" },
									{ "Micro Cap", "Micro Cap: $50M < & < $300M" } }));

				}
			}, null, new LinkedHashMap<String, Object>() {
				{
					put("all", "All");
				}
			},null, 1));

			// Style
			put("style",
					new SqlData("SELECT stock_class_type_id,stock_class_name FROM rsch_area_stock_class",
							new ArrayList<ColVal>() {
				{
					add(new ColVal("stockClassificationName", null));

				}
			}, null, null, null, 1));

			//analystType
			put("analystType",
					new SqlData("SELECT vendor_id,analystType FROM vendor where analystType IS Not null",
							new ArrayList<ColVal>() {
				{
					add(new ColVal("analystType", null));

				}
			}, null, null, new LinkedHashMap<String, Object>() {
				{
					put("analystType", "Others");
				}
			}, 1));
			
			//CompanyName - earlier was User Name
			//researchBroker
			put("researchBroker",
					new SqlData("SELECT vendor_id,company FROM vendor where company IS Not null;",
							new ArrayList<ColVal>() {
				{
					add(new ColVal("companyName", null));

				}
			}, null, null,null, 1));
			
			//BrokerYrOfInCorp - Constant is defined, See at the top of class
			
			//brokerRank
			put("brokerRank",
					new SqlData(
							"SELECT broker_id,broker_rank FROM broker_analyst",
							new ArrayList<ColVal>() {
				{
					add(new ColVal("broker_rank",
							new String[][] { { "5star", "5 star (Success rate > 80%)" },
									{ "4star", "4 star (Success rate >= 65% & < 80%)" },
									{ "3star", "3 star (Success rate >= 50% & < 65%)" },
									{ "2star", "2 star (Success rate >= 40% & < 50%)" },
									{ "1star", "1 star (Success rate < 40%)" }
									}));

				}
			}, null, null,null, 1));
			
			//recommType
			put("recommType",
					new SqlData("SELECT product_id,rsrch_recomm_type FROM ven_rsrch_rpt_dtls group by rsrch_recomm_type",
							new ArrayList<ColVal>() {
				{
					add(new ColVal("rsrchRecommType", null));

				}
			}, null, null,null, 1));
			
			//Others - Constant is defined, See at the top of class
			//Upside - Constant is defined, See at the top of class

		}
	};

	public static String getLoggedInUser(HttpServletRequest request) throws Exception {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) {
			throw new Exception("Unable to find logged In user!!");
		}
		return loggedInUser.getUsername();
	}

	public static String buildReportPath(String productId, CommonsMultipartFile multiPartFile, String userName,
			String basePath) {
		// Upload filename suffix with productId
		// Reason: loggedIn user can upload multiple file while upload
		Pair<String, String> fileNameAndExtention = StringUtil
				.getFileNameAndExtention(multiPartFile.getOriginalFilename());

		String uploadFileNameOnly = fileNameAndExtention.getElement1() + "_" + productId
				+ fileNameAndExtention.getElement2();
		String reportResearchOfferingFilePath = StringUtil.builtPath(uploadFileNameOnly, basePath, userName);
		return reportResearchOfferingFilePath;
	}
}
