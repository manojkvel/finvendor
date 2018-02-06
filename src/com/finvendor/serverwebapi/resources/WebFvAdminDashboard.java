package com.finvendor.serverwebapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.bean.CompanyDetails;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.WebFvAdminDashboardIfc;

@Controller
public class WebFvAdminDashboard implements WebFvAdminDashboardIfc {

	@Autowired
	private ICommonDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CompanyDetails> getResearchResult(String researchAreaId) throws WebApiException {
		return dao.getCompanyDetails(Integer.parseInt(researchAreaId));
	}
}
