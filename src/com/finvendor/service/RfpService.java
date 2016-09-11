package com.finvendor.service;

import java.util.List;

import com.finvendor.model.Consumer;
import com.finvendor.model.RfpBean;

public interface RfpService {
	
	public RfpBean createRfp(Consumer consumer, RfpBean rfpBean, boolean update);
	public void closeRfp(Consumer consumer, RfpBean rfpBean);
	public boolean expresssRfpInterest(String rfpId, String vendorId, boolean revoke);
	public void shortlistRfpVendors(String rfpId, List<String> vendorList, boolean finalize);
	public void requestRfpMoreInfo(String rfpId, String moreInfoDetails, String vendorId);
	public void updateRfpMoreInfo(String id, String rfpId, String moreInfoDetails, String vendorId); 
	public List<Object[]> selectMyRfpVendor(String vendorId);
	public List<Object[]> selectRfpDetails(String rfpId, String vendorId);
	public List<Object[]> selectMyRfpListVendor();

}
