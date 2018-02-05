package com.finvendor.serverwebapi.resources.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.bean.CompanyDetails;
import com.finvendor.common.dao.ifc.ICommonDao;
import com.finvendor.serverwebapi.resources.exception.RestApiException;
import com.finvendor.serverwebapi.resources.ifc.IAdminDashboardRestApi;

@Controller
public class AdminDashboardRestApi implements IAdminDashboardRestApi{

	@Autowired
	private ICommonDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CompanyDetails> getResearchResult(String researchAreaId) throws RestApiException {
		return dao.getCompanyDetails(Integer.parseInt(researchAreaId));
	}
}
