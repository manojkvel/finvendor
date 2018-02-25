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
	public String getTableData(String type) throws WebApiException {
		Class<?> tableName = WebUtil.TABLE_NAME_MAP.get(type);
		String[] columns = WebUtil.TABLE_NAME_COLUMN_MAP.get(tableName);
		return commonDao.findAll(tableName, columns);
	}
}
