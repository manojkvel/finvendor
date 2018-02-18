package com.finvendor.serverwebapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.modelpojo.staticpojo.VoVendorDetails;
import com.finvendor.server.common.dao.ifc.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.WebCommonApiIfc;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
public class WebCommonApi implements WebCommonApiIfc {
	@Autowired
	private ICommonDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<VoVendorDetails> getVoVendorDetails() {
		try {
			return dao.getVoVendorDetails(SqlEnum.VO_VENDOR_DETAILS.valueOf());
		} catch (Exception e) {
			throw new WebApiException("Error in WebCommonApi - getVoVendorDetails(), RootCause- "+ExceptionUtil.getRootCauseMessage(e));
		}
	}

}
