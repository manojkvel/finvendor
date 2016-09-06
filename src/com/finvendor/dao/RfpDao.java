package com.finvendor.dao;

import java.util.List;

import com.finvendor.model.RfpBean;

public interface RfpDao {

	public RfpBean createRfp(RfpBean rfpBean, boolean update);
	public boolean expresssRfpInterest(String rfpId, String vendorId, boolean revoke);
	public void shortlistRfpVendors(String rfpId, List<String> vendorList, boolean finalize);
	public void requestRfpMoreInfo(String rfpId, String moreInfoDetails, String vendorId);
	public void updateRfpMoreInfo(RfpBean rfpBean, String vendorId);
}
