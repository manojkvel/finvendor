package com.finvendor.serverwebapi.resources.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.common.WebCommonApiIfc;
import com.finvendor.serverwebapi.utils.WebUtil;
import com.finvendor.serverwebapi.utils.WebUtil.SqlData;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
public class WebCommonApi implements WebCommonApiIfc {

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

		switch (type) {
		case "brokerYrOfInCorp":
			return WebUtil.brokerYrOfInCorpJson;
		case "others":
			return WebUtil.othersJson;
		case "upside":
			return WebUtil.upsideJson;
		}

		SqlData sqlData = WebUtil.typeMap.get(type);
		String jsonResult = commonDao.runSql(sqlData.getSql(), sqlData.getColumnNameMap(), sqlData.getConitionValue(),
				sqlData.getFirstDefaultParamsMap(), sqlData.getLastDefaultParamsMap(), sqlData.getColIndex());

		return jsonResult;
	}
}
