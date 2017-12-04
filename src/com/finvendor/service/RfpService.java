<<<<<<< HEAD
package com.finvendor.service;

import java.util.List;

import com.finvendor.model.Consumer;
import com.finvendor.model.RfpBean;
import com.finvendor.model.Vendor;

public interface RfpService {
	
	public RfpBean createRfp(Consumer consumer, RfpBean rfpBean, boolean update);
	public void closeRfp(Consumer consumer, RfpBean rfpBean);
	public boolean expresssRfpInterest(RfpBean rfpBean, Vendor vendor, Consumer consumer, boolean revoke);
	public void shortlistRfpVendors(RfpBean rfpBean, Consumer consumer, List<Vendor> vendorList, boolean finalize);
	public void requestRfpMoreInfo(RfpBean rfpBean, String moreInfoDetails, Consumer consumer, Vendor vendor);
	public void updateRfpMoreInfo(String id, RfpBean rfpBean, Consumer consumer, String moreInfoDetails, Vendor vendor); 
	public List<Object[]> selectMyRfpVendor(String vendorId);
	public List<Object[]> selectRfpDetails(String rfpId, String vendorId);
	public List<Object[]> selectMyRfpListVendor();
	public List<RfpBean> selectMyRfpConsumer(String consumerId);
}
=======
package com.finvendor.service;

import java.util.List;

import com.finvendor.model.Consumer;
import com.finvendor.model.RfpBean;
import com.finvendor.model.Vendor;

public interface RfpService {
	
	public RfpBean createRfp(Consumer consumer, RfpBean rfpBean, boolean update);
	public void closeRfp(Consumer consumer, RfpBean rfpBean);
	public boolean expresssRfpInterest(RfpBean rfpBean, Vendor vendor, Consumer consumer, boolean revoke);
	public void shortlistRfpVendors(RfpBean rfpBean, Consumer consumer, List<Vendor> vendorList, boolean finalize);
	public void requestRfpMoreInfo(RfpBean rfpBean, String moreInfoDetails, Consumer consumer, Vendor vendor);
	public void updateRfpMoreInfo(String id, RfpBean rfpBean, Consumer consumer, String moreInfoDetails, Vendor vendor); 
	public List<Object[]> selectMyRfpVendor(String vendorId);
	public List<Object[]> selectRfpDetails(String rfpId, String vendorId);
	public List<Object[]> selectMyRfpListVendor();
	public List<RfpBean> selectMyRfpConsumer(String consumerId);
}
>>>>>>> origin/master
