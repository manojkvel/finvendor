package com.finvendor.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.RfpDao;
import com.finvendor.model.Consumer;
import com.finvendor.model.RfpBean;
import com.finvendor.service.RfpService;
import com.finvendor.util.EmailUtil;

public class RfpServiceImpl implements RfpService {
	
	private static Logger logger = LoggerFactory.getLogger(RfpServiceImpl.class);
		
	@Autowired
	private RfpDao rfpDao;

	@Override
	public RfpBean createRfp(Consumer consumer, RfpBean rfpBean, boolean update) {
		rfpDao.createRfp(rfpBean, update);
		List<String> vendorEmailList = rfpDao.getVendorsEmail(rfpBean.getTargetVendorType());
		try {
			EmailUtil.sendRfpNotification(consumer, rfpBean, vendorEmailList, false);			
		}catch (Exception exp) {
			logger.error("Error sending RFP notification", exp);
		}
		return rfpBean;
	}
	
	@Override
	public void closeRfp(Consumer consumer, RfpBean rfpBean) {
		rfpDao.closeRfp(rfpBean.getRfpId());
		List<String> vendorEmailList = rfpDao.getVendorsEmail(rfpBean.getTargetVendorType());
		try {
			EmailUtil.sendRfpNotification(consumer, rfpBean, vendorEmailList, true);			
		}catch (Exception exp) {
			logger.error("Error sending RFP notification", exp);
		}
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
	
	@Override
	public List<Object[]> selectMyRfpVendor(String vendorId) {
		return rfpDao.selectMyRfpVendor(vendorId);
	}
	
	@Override
	public List<Object[]> selectRfpDetails(String rfpId, String vendorId) {
		return rfpDao.selectRfpDetails(rfpId, vendorId);
	}
	
	@Override
	public List<Object[]> selectMyRfpListVendor() {
		return rfpDao.selectMyRfpListVendor();
	}

}
