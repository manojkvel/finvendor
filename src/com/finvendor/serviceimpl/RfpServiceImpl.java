package com.finvendor.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.RfpDao;
import com.finvendor.model.Consumer;
import com.finvendor.model.RfpBean;
import com.finvendor.model.Vendor;
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
	public boolean expresssRfpInterest(RfpBean rfpBean, Vendor vendor, Consumer consumer, boolean revoke) {
		rfpDao.expresssRfpInterest(rfpBean.getRfpId(), vendor.getId(), revoke);
		try {
			EmailUtil.sendRfpInterestNotification(rfpBean, vendor, consumer, revoke);			
		}catch (Exception exp) {
			logger.error("Error sending RFP notification", exp);
		}
		return false;
	}

	@Override
	public void shortlistRfpVendors(RfpBean rfpBean, Consumer consumer, List<Vendor> vendorList, boolean finalize) {
		List<String> vendorIdList = new ArrayList<String>();
		for(Vendor v : vendorList) {
			vendorIdList.add(v.getId());
		}
		rfpDao.shortlistRfpVendors(rfpBean.getRfpId(), vendorIdList, finalize);
		try {
			if(finalize) {
				EmailUtil.sendRfpVendorSelectionNotification(consumer, rfpBean, vendorList, false, true);	
			}else {
				EmailUtil.sendRfpVendorSelectionNotification(consumer, rfpBean, vendorList, true, false);	
			}					
		}catch (Exception exp) {
			logger.error("Error sending RFP notification", exp);
		}		
	}

	@Override
	public void requestRfpMoreInfo(RfpBean rfpBean, String moreInfoDetails, Consumer consumer, Vendor vendor) {
		rfpDao.requestRfpMoreInfo(rfpBean.getRfpId(), moreInfoDetails, vendor.getId());
		try {
			EmailUtil.sendRfpMoreInfoNotification(rfpBean, vendor, consumer, moreInfoDetails, true);
		}catch (Exception exp) {
			logger.error("Error sending RFP notification", exp);
		}
	}

	@Override
	public void updateRfpMoreInfo(String id, RfpBean rfpBean, Consumer consumer, String moreInfoDetails, Vendor vendor) {
		rfpDao.updateRfpMoreInfo(id, rfpBean.getRfpId(), moreInfoDetails, vendor.getId());
		try {
			EmailUtil.sendRfpMoreInfoNotification(rfpBean, vendor, consumer, moreInfoDetails, false);
		}catch (Exception exp) {
			logger.error("Error sending RFP notification", exp);
		}
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
