package com.finvendor.serverwebapi.resources.common.impl;

import static com.finvendor.common.exception.ExceptionEnum.COMPANY_DETAILS;
import static com.finvendor.common.exception.ExceptionEnum.EQUITY_RESEARCH_FILTER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.common.IWebCommon;
import com.finvendor.serverwebapi.webutil.WebUtil;
import com.finvendor.serverwebapi.webutil.WebUtil.SqlData;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
public class WebCommonImpl implements IWebCommon {

	@Autowired
	private ICommonDao commonDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getCompanyDetails(String researchAreaId) throws WebApiException {
		try {
			List<CompanyDetails> companyDetails = commonDao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(),
					researchAreaId);
			return new ResponseEntity<List<CompanyDetails>>(companyDetails, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebApiCommon -> getCompanyDetails(...) method", e);
			return ErrorUtil.getError(COMPANY_DETAILS.getCode(), COMPANY_DETAILS.getUserMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getResearchFilterData(String type) throws WebApiException {
		try {
			switch (type) {
			case "brokerYrOfInCorp":
				return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_BROKER_YR_OF_IN_CORP_JSON, HttpStatus.OK);
			case "brokerRank":
				return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_BROKER_RANK_JSON, HttpStatus.OK);
			case "others":
				return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_OTHERS_JSON, HttpStatus.OK);
			case "upside":
				return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_UPSIDE_JSON, HttpStatus.OK);
			}

			SqlData sqlData = WebUtil.typeMap.get(type);
			String jsonResult = commonDao.runSql(sqlData.getSql(), sqlData.getColumnNameAndNewValueMap(),
					sqlData.getConitionValue(), sqlData.getFirstDefaultParamsMap(), sqlData.getLastDefaultParamsMap(),
					sqlData.getColIndex());
			return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebApiCommon -> getResearchFilterData(...) method", e);
			return ErrorUtil.getError(EQUITY_RESEARCH_FILTER.getCode(), EQUITY_RESEARCH_FILTER.getUserMessage(), e);
		}
	}
}
