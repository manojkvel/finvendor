package com.finvendor.server.researchreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.SectorResearchResult;
import com.finvendor.server.researchreport.service.ifc.AbsResearchReportService;

/**
 * 
 * @author ayush
 * @since 04-Feb-2018
 */
@Service
public class MacroResearchService extends AbsResearchReportService {

	@Autowired
	@Qualifier(value="macroResearchDao")
	IResearchReportDao macroReserachReportDao;
	
	@Override
	public List<SectorResearchResult> getResearchReport(EquityResearchFilter rrfilter) {
		return null;
	}
}
