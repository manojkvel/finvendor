package com.finvendor.server.researchreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.service.ifc.AbsResearchReportService;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Service
public class EquityResearchService extends AbsResearchReportService {

	@Autowired
	@Qualifier(value = "equityResearchDao")
	IResearchReportDao equityReserachReportDao;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<EquityResearchResult> getResearchReport(EquityResearchFilter rrFilter) {
		try {
			return (List<EquityResearchResult>) equityReserachReportDao.findResearchResult(rrFilter);
		} catch (Exception e) {
			throw e;
		}
	}
}
