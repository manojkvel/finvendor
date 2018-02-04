package com.finvendor.dao;

import java.util.List;

import com.finvendor.model.RfpBean;

public interface RfpDao {

	public RfpBean createRfp(RfpBean rfpBean, boolean update);
	public void closeRfp(String rfpId);
	public List<RfpBean> selectMyRfpConsumer(String consumerId);
	public boolean expresssRfpInterest(String rfpId, String vendorId, boolean revoke);
	public void shortlistRfpVendors(String rfpId, List<String> vendorList, boolean finalize);
	public void requestRfpMoreInfo(String rfpId, String moreInfoDetails, String vendorId);
	public void updateRfpMoreInfo(String id, String rfpId, String moreInfoDetails, String vendorId);
	public List<Object[]> selectMyRfpVendor(String vendorId);
	public List<Object[]> selectRfpDetails(String rfpId, String vendorId);
	public List<String> getVendorsEmail(String targetVendorType);
	public List<Object[]> selectMyRfpListVendor();
}
