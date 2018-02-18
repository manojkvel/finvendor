package com.finvendor.serverwebapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.modelpojo.staticpojo.CompanyDetails;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.WebAdminDashboardIfc;

@Controller
public class WebAdminDashboard implements WebAdminDashboardIfc {

	@Autowired
	private ICommonDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CompanyDetails> getCompanyDetails(String researchAreaId) throws WebApiException {
		return dao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(), researchAreaId);
	}
}
