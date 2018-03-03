package com.finvendor.serverwebapi.resources.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.common.WebCommonApiIfc;
import com.finvendor.serverwebapi.utils.WebUtil;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
public class WebCommonApi implements WebCommonApiIfc {
	private static Logger logger = LoggerFactory.getLogger(WebCommonApi.class);

	@Autowired
	private ICommonDao commonDao;

	@Override
	@Transactional(readOnly = true)
	public List<CompanyDetails> getCompanyDetails(String researchAreaId) throws WebApiException {
		return commonDao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(), researchAreaId);
	}

	@Override
	@Transactional(readOnly = true)
	public String getResearchFilterData(String type) throws WebApiException {
		// TODO Ugly code, need to think to make it clean - but it is temporary
		String json = "";
		try {
			if ("brokerYrOfInCorp".endsWith(type)) {
				return "[{\"brokerYrOfInCorp\":\"<= 3 Yrs\"},{\"brokerYrOfInCorp\":\"3 - 5 Yrs\"},{\"brokerYrOfInCorp\":\"5 - 10 Yrs\"},{\"brokerYrOfInCorp\":\"> 10 Yrs\"}]";
			} else if ("others".equals(type)) {
				List<String> ll = new ArrayList<>();
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("Award Winning Analyst", "false");
				data.put("Research Reports by CFA", "false");
				json = JsonUtil.createJsonFromParamsMap(data);
				ll.add(json);
				json = Arrays.toString(ll.toArray(new String[1]));
				return json;
			} else if ("upside".equals(type)) {
				return "[{\"upside\":\"<0%\"},{\"upside\":\"0-20%\"},{\"upside\":\"20-50%\"},{\"upside\":\"50-100\"},{\"upside\":\">100 %\"}]";
			} else {
				Class<?> tableName = WebUtil.TABLE_NAME_MAP.get(type);
				String[] columns = WebUtil.TABLE_NAME_COLUMN_MAP.get(tableName);
				String[] conditionColumns = WebUtil.TABLE_NAME_COLUMN__CONDITION_MAP.get(tableName);
				json = commonDao.findAll(tableName, columns, conditionColumns);
				String newJson = "";
				if (json.contains("broker_rank")) {
					if (json.contains("5star")) {
						newJson = StringUtils.replace(json, "5star", "5 star (Success rate > 80%)");
					}
					if (json.contains("3star")) {
						newJson = StringUtils.replace(newJson, "3star", "3 star (Success rate >= 50% & < 65%)");
					}

				} else if (json.contains("mcap_name")) {
					if (json.contains("Large Cap")) {
						newJson = StringUtils.replace(json, "Large Cap", "Large Cap: > $5Bn");
					}
					if (json.contains("Mid Cap")) {
						newJson = StringUtils.replace(newJson, "Mid Cap", "Mid Cap: $1Bn < & < $5Bn");
					}
					if (json.contains("Small Cap")) {
						newJson = StringUtils.replace(newJson, "Small Cap", "Small Cap: $300M < & < $1Bn");
					}
					if (json.contains("Micro Cap")) {
						newJson = StringUtils.replace(newJson, "Micro Cap", "Micro Cap: $50M < & < $300M");
					}
					if (json.contains("Nano Cap")) {
						newJson = StringUtils.replace(newJson, "Nano Cap", "Nano Cap: < $50M");
					}
					newJson = JsonUtil.addNodeInJsonArray(newJson, "all", "All");
				} else if (json.contains("stockClassificationName")) {
					newJson = JsonUtil.addNodeInJsonArray(json, "all", "All");
				}
				if (!newJson.isEmpty()) {
					json = newJson;
				}
				return json;
			}
		} catch (IOException e) {
			logger.error("Web API Error: ", e.getMessage(), e);
			throw new WebApiException(
					"Error has occurred in WebCommonApi -> getResearchFilterData(...) method, Root Cause:: "
							+ ExceptionUtil.getRootCause(e));
		}
	}
}
