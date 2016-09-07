package com.finvendor.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.RfpDao;
import com.finvendor.model.RfpBean;
import com.finvendor.service.RfpService;

public class RfpServiceImpl implements RfpService {
	
	@Autowired
	private RfpDao rfpDao;

	@Override
	public RfpBean createRfp(RfpBean rfpBean, boolean update) {
		rfpDao.createRfp(rfpBean, update);
		return rfpBean;
	}

	@Override
	public boolean expresssRfpInterest(String rfpId, String vendorId, boolean revoke) {
		rfpDao.expresssRfpInterest(rfpId, vendorId, revoke);
		return false;
	}

	@Override
	public void shortlistRfpVendors(String rfpId, List<String> vendorList, boolean finalize) {
		rfpDao.shortlistRfpVendors(rfpId, vendorList, finalize);
	}

	@Override
	public void requestRfpMoreInfo(String rfpId, String moreInfoDetails, String vendorId) {
		rfpDao.requestRfpMoreInfo(rfpId, moreInfoDetails, vendorId);
	}

	@Override
	public void updateRfpMoreInfo(String id, String rfpId, String moreInfoDetails, String vendorId) {
		rfpDao.updateRfpMoreInfo(id, rfpId, moreInfoDetails, vendorId);
	}

}
