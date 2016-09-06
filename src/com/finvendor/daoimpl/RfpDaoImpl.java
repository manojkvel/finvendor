package com.finvendor.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finvendor.dao.RfpDao;
import com.finvendor.model.RfpBean;

public class RfpDaoImpl implements RfpDao {
	
	private static Logger logger = LoggerFactory.getLogger(RfpDaoImpl.class);
	
	private static final String INSERT_CONSUMER_RFP_RECORD = "insert into consumer_rfp (rfp_id, consumer_id, rfp_title, rfp_short_desc, rfp_detailed_desc, created_date, rfp_end_date) values (:rfp_id, :consumer_id, :rfp_title, :rfp_short_desc, :rfp_detailed_desc, CURRENT_TIMESTAMP(), :rfp_end_date)";
	private static final String UPDATE_CONSUMER_RFP_RECORD = "update consumer_rfp set rfp_short_desc = :rfp_short_desc, rfp_detailed_desc = :rfp_detailed_desc, rfp_end_date = :rfp_end_date where rfp_id = :rfp_id";
	private static final String SHORTLIST_RFP_VENDORS = "update vendor_rfp_interest set shortlisted ='Y', shortlisted_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
	private static final String FINALIZED_RFP_VENDORS = "update vendor_rfp_interest set finalized ='Y', finalized_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
	private static final String SELECT_MY_RFP_DETAILS = "select con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.rfp_short_desc, con_rfp.rfp_detailed_desc, con_rfp.created_date, con_rfp.rfp_end_date, con_rfp.rfp_closed, con_rfp.rfp_closed_date, ven.company, ven.lname + ',' + ven.fname ven_name, ven.vendor_id, ven_rfp.shortlisted, ven_rfp.shortlisted_date, ven_rfp.finalized, ven_rfp.finalized_date from consumer_rfp con_rfp, vendor_rfp_interest ven_rfp, vendor ven where con_rfp.rfp_id = ven_rfp.rfp_id and ven_rfp.vendor_id = ven.vendor_id and con_rfp.consumer_id = :consumer_id order by con_rfp.created_date desc, con_rfp.rfp_id ";
	private static final String CLOSE_RFP = "update consumer_rfp set rfp_closed = 'Y', rfp_closed_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id";
		
	private static final String EXPRESS_VENDOR_RFP_INTEREST = "INSERT into vendor_rfp_interest (rfp_id, vendor_id, interest_shown_date) values (:rfp_id, :vendor_id, CURRENT_TIMESTAMP())";
	private static final String REQUEST_VENDOR_RFP_MORE_INFO = "UPDATE vendor_rfp_interest set more_info_requested = 'Y', more_info_requested_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
	private static final String INSERT_VENDOR_RFP_MORE_INFO = "INSERT into rfp_more_info_details (id, rfp_id, vendor_id, more_info, more_info_requested_date) values (:id, :rfp_id, :vendor_id, :more_info, CURRENT_TIMESTAMP())";
	private static final String SELECT_VENDOR_RFP_LIST = "select con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.created_date, con_rfp.rfp_end_date, con.company, con.lname + ', ' + con.fname con_name, ven_rfp.interest_shown_date, ven_rfp.shortlisted, ven_rfp.finalized, ven_rfp.finalized_date from consumer_rfp con_rfp, vendor_rfp_interest ven_rfp, consumer con where con_rfp.rfp_id = ven_rfp.rfp_id and con_rfp.consumer_id = con.consumer_id and ven_rfp.rfp_id = :vendor_id order by con_rfp.created_date desc";
	
	
	@Override
	public RfpBean createRfp(RfpBean rfpBean, boolean update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean expresssRfpInterest(String rfpId, String vendorId, boolean revoke) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shortlistRfpVendors(String rfpId, List<String> vendorList, boolean finalize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestRfpMoreInfo(String rfpId, String moreInfoDetails, String vendorId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRfpMoreInfo(RfpBean rfpBean, String vendorId) {
		// TODO Auto-generated method stub

	}

}
