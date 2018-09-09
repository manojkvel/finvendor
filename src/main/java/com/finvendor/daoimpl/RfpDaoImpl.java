package com.finvendor.daoimpl;

import com.finvendor.dao.RfpDao;
import com.finvendor.model.RfpBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RfpDaoImpl implements RfpDao {

    private static final Logger logger = LogManager.getLogger(RfpDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    private static final String INSERT_CONSUMER_RFP_RECORD = "insert into consumer_rfp (rfp_id, consumer_id, rfp_title, rfp_short_desc, rfp_detailed_desc, created_date, rfp_end_date, target_vendor_type) values (:rfp_id, :consumer_id, :rfp_title, :rfp_short_desc, :rfp_detailed_desc, CURRENT_TIMESTAMP(), :rfp_end_date, :target_vendor_type)";
    private static final String UPDATE_CONSUMER_RFP_RECORD = "update consumer_rfp set rfp_short_desc = :rfp_short_desc, rfp_detailed_desc = :rfp_detailed_desc, rfp_end_date = :rfp_end_date, target_vendor_type = :target_vendor_type where rfp_id = :rfp_id";
    private static final String SHORTLIST_RFP_VENDORS = "update vendor_rfp_interest set shortlisted ='Y', shortlisted_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
    private static final String FINALIZED_RFP_VENDORS = "update vendor_rfp_interest set finalized ='Y', finalized_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
    private static final String SELECT_MY_RFP_DETAILS = "select con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.rfp_short_desc, con_rfp.rfp_detailed_desc, con_rfp.created_date, con_rfp.rfp_end_date, con_rfp.rfp_closed, con_rfp.rfp_closed_date, ven.lname + ',' + ven.fname ven_name, ven.vendor_id, ven_rfp.shortlisted, ven_rfp.shortlisted_date, ven_rfp.finalized, ven_rfp.finalized_date, ven_rfp.interest_shown_date, ven_rfp.interset_revoke_date, ven.company, ven.username from consumer_rfp con_rfp left outer join vendor_rfp_interest ven_rfp on (con_rfp.rfp_id = ven_rfp.rfp_id) left outer join vendor ven on (ven_rfp.vendor_id = ven.vendor_id) where con_rfp.consumer_id = :consumer_id order by con_rfp.created_date desc, con_rfp.rfp_id";
    private static final String SELECT_RFP_DETAILS = "select con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.rfp_short_desc, con_rfp.rfp_detailed_desc, con_rfp.created_date, con_rfp.rfp_end_date, con_rfp.rfp_closed, con_rfp.rfp_closed_date, ven.company, ven.lname + ',' + ven.fname ven_name, ven.vendor_id, ven_rfp.shortlisted, ven_rfp.shortlisted_date, ven_rfp.finalized, ven_rfp.finalized_date, ven_rfp.interest_shown_date, ven_rfp.interset_revoke_date, ven.company, ven_rfp.rfp_vendor_response, con.username from consumer_rfp con_rfp, vendor_rfp_interest ven_rfp, vendor ven, consumer con, users u where con_rfp.rfp_id = ven_rfp.rfp_id and ven_rfp.vendor_id = ven.vendor_id and con_rfp.rfp_id = :rfp_id and con.consumer_id = con_rfp.consumer_id and con.username = u.username";
    private static final String SELECT_RFP_DETAILS_FOR_VENDOR = "select con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.rfp_short_desc, con_rfp.rfp_detailed_desc, con_rfp.created_date, con_rfp.rfp_end_date, con_rfp.rfp_closed, con_rfp.rfp_closed_date, ven.company, ven.lname + ',' + ven.fname ven_name, ven.vendor_id, ven_rfp.shortlisted, ven_rfp.shortlisted_date, ven_rfp.finalized, ven_rfp.finalized_date, ven_rfp.interest_shown_date, ven_rfp.interset_revoke_date, ven.company, ven_rfp.rfp_vendor_response, con.username from consumer_rfp con_rfp, vendor_rfp_interest ven_rfp, vendor ven, consumer con, users u where con_rfp.rfp_id = ven_rfp.rfp_id and ven_rfp.vendor_id = ven.vendor_id and con_rfp.rfp_id = :rfp_id and ven_rfp.vendor_id = :vendor_id and con.consumer_id = con_rfp.consumer_id and con.username = u.username";
    private static final String CLOSE_RFP = "update consumer_rfp set rfp_closed = 'Y', rfp_closed_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id";

    private static final String EXPRESS_VENDOR_RFP_INTEREST = "insert into vendor_rfp_interest (rfp_id, vendor_id, interest_shown_date) values (:rfp_id, :vendor_id, CURRENT_TIMESTAMP())";
    private static final String REVOKE_VENDOR_RFP_INTEREST = "update vendor_rfp_interest set interset_revoke_date = CURRENT_TIMESTAMP(), interest_shown_date = NULL where rfp_id = :rfp_id and vendor_id = :vendor_id";
    private static final String REQUEST_VENDOR_RFP_MORE_INFO = "update vendor_rfp_interest set more_info_requested = 'Y', more_info_requested_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
    private static final String INSERT_VENDOR_RFP_MORE_INFO = "insert into rfp_more_info_details (id, rfp_id, vendor_id, more_info, more_info_requested_date) values (:id, :rfp_id, :vendor_id, :more_info, CURRENT_TIMESTAMP())";
    private static final String UPDATE_CONSUMER_RFP_MORE_INFO = "update vendor_rfp_interest set more_info_provided_date = CURRENT_TIMESTAMP() where rfp_id = :rfp_id and vendor_id = :vendor_id";
    private static final String UPDATE_CONSUMER_RFP_MORE_INFO_DETAILS = "update rfp_more_info_details set consumer_reply = :consumer_reply, more_info_provided_date = CURRENT_TIMESTAMP() where id = :id";
    private static final String SELECT_VENDOR_RFP_LIST = "select con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.created_date, con_rfp.rfp_end_date, con.company, con.lname + ', ' + con.fname con_name, ven_rfp.interest_shown_date, ven_rfp.shortlisted, ven_rfp.finalized, ven_rfp.finalized_date, ven_rfp.rfp_vendor_response from consumer_rfp con_rfp, vendor_rfp_interest ven_rfp, consumer con where con_rfp.rfp_id = ven_rfp.rfp_id and con_rfp.consumer_id = con.consumer_id and ven_rfp.rfp_id = :vendor_id order by con_rfp.created_date desc";
    private static final String SELECT_APPLICABLE_RFP_FOR_VENDOR = "select distinct con_rfp.rfp_id, con_rfp.consumer_id, con_rfp.rfp_title, con_rfp.created_date, con_rfp.rfp_end_date, con.company, con.lname + ', ' + con.fname con_name, con_rfp.target_vendor_type, con.username from consumer_rfp con_rfp inner join consumer con on (con_rfp.consumer_id = con.consumer_id) inner join vendor ven on (ven.companytype like concat ('%', con_rfp.target_vendor_type, '%')) order by con_rfp.created_date desc";

    private static final String SELECT_VENDOR_EMAIL = "select u.email, v.secondary_email from vendor v, users u where v.username = u.username and u.enabled = 1 and v.companytype like '%:target_vendor_type%'";

    @Override
    public RfpBean createRfp(RfpBean rfpBean, boolean update) {
        SQLQuery sqlQuery = null;
        if (update) {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_CONSUMER_RFP_RECORD);
            sqlQuery.setParameter("rfp_short_desc", rfpBean.getRfpShortDesc());
            sqlQuery.setParameter("rfp_detailed_desc", rfpBean.getRfpDetailedDesc());
            sqlQuery.setParameter("rfp_end_date", rfpBean.getRfpEndDate());
            sqlQuery.setParameter("rfp_id", rfpBean.getRfpId());
            sqlQuery.setParameter("target_vendor_type", rfpBean.getTargetVendorType());
            sqlQuery.executeUpdate();
        } else {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(INSERT_CONSUMER_RFP_RECORD);
            sqlQuery.setParameter("rfp_id", rfpBean.getRfpId());
            sqlQuery.setParameter("consumer_id", rfpBean.getConsumerId());
            sqlQuery.setParameter("rfp_title", rfpBean.getRfpTitle());
            sqlQuery.setParameter("rfp_short_desc", rfpBean.getRfpShortDesc());
            sqlQuery.setParameter("rfp_detailed_desc", rfpBean.getRfpDetailedDesc());
            sqlQuery.setParameter("rfp_end_date", rfpBean.getRfpEndDate());
            sqlQuery.setParameter("target_vendor_type", rfpBean.getTargetVendorType());
            sqlQuery.executeUpdate();
        }
        return rfpBean;
    }

    @Override
    public void closeRfp(String rfpId) {
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(CLOSE_RFP);
        sqlQuery.setParameter("rfp_id", rfpId);
        sqlQuery.executeUpdate();
    }

    @Override
    public List<Object[]> selectRfpDetails(String rfpId, String vendorId) {
        SQLQuery sqlQuery = null;
        if (vendorId != null) {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SELECT_RFP_DETAILS_FOR_VENDOR);
            sqlQuery.setParameter("rfp_id", rfpId);
            sqlQuery.setParameter("vendor_id", vendorId);
        } else {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SELECT_RFP_DETAILS);
            sqlQuery.setParameter("rfp_id", rfpId);
        }
        @SuppressWarnings("unchecked")
        List<Object[]> results = sqlQuery.list();
        return results;
    }

    @Override
    public List<RfpBean> selectMyRfpConsumer(String consumerId) {
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SELECT_MY_RFP_DETAILS);
        sqlQuery.setParameter("consumer_id", consumerId);
        @SuppressWarnings("unchecked")
        List<Object[]> results = sqlQuery.list();
        List<RfpBean> consumerRfpList = new ArrayList<RfpBean>();
        String currentRfpId = null;
        String prevRfpId = null;
        String consumer_id = null;
        String rfp_title = null;
        String rfp_short_desc = null;
        String rfp_detailed_desc = null;
        Timestamp created_date = null;
        Timestamp rfp_end_date = null;
        String rfp_closed = null;
        Timestamp rfp_closed_date = null;
        String ven_name = null;
        String vendor_id = null;
        String shortlisted = null;
        String shortlisted_date = null;
        String finalized = null;
        Timestamp finalized_date = null;
        Timestamp interest_shown_date = null;
        Timestamp interset_revoke_date = null;
        String company = null;
        String venUserName = null;
        List<String> shortListedVendor = null;
        List<String> finalizedVendor = null;
        List<String> interestedVendor = null;
        boolean firstTime = false;
        RfpBean rfpBean = null;
        for (Object[] rfpRow : results) {
            currentRfpId = rfpRow[0].toString();
            consumer_id = rfpRow[1].toString();
            rfp_title = rfpRow[2].toString();
            rfp_short_desc = rfpRow[3] != null ? rfpRow[3].toString() : "";
            rfp_detailed_desc = rfpRow[4] != null ? rfpRow[4].toString() : "";
            created_date = rfpRow[5] != null ? (Timestamp) rfpRow[5] : null;
            rfp_end_date = rfpRow[6] != null ? (Timestamp) rfpRow[6] : null;
            rfp_closed = rfpRow[7] != null ? rfpRow[7].toString() : "";
            rfp_closed_date = rfpRow[8] != null ? (Timestamp) rfpRow[8] : null;
            ven_name = rfpRow[9] != null ? rfpRow[9].toString() : "";
            vendor_id = rfpRow[10] != null ? rfpRow[10].toString() : "";
            shortlisted = rfpRow[11] != null ? rfpRow[11].toString() : "";
            shortlisted_date = rfpRow[12] != null ? rfpRow[12].toString() : "";
            finalized = rfpRow[13] != null ? rfpRow[13].toString() : "";
            finalized_date = rfpRow[14] != null ? (Timestamp) rfpRow[14] : null;
            interest_shown_date = rfpRow[15] != null ? (Timestamp) rfpRow[15] : null;
            interset_revoke_date = rfpRow[16] != null ? (Timestamp) rfpRow[16] : null;
            company = rfpRow[17] != null ? rfpRow[17].toString() : "";
            venUserName = rfpRow[18] != null ? rfpRow[18].toString() : "";
            if (!firstTime) {
                firstTime = true;
                prevRfpId = currentRfpId;
                rfpBean = new RfpBean();
                shortListedVendor = new ArrayList<String>();
                finalizedVendor = new ArrayList<String>();
                interestedVendor = new ArrayList<String>();
                rfpBean.setRfpId(currentRfpId);
                rfpBean.setConsumerId(consumer_id);
                rfpBean.setRfpTitle(rfp_title);
                rfpBean.setRfpShortDesc(rfp_short_desc);
                rfpBean.setRfpDetailedDesc(rfp_detailed_desc);
                rfpBean.setRfpCreateddate(created_date);
                rfpBean.setRfpEnddate(rfp_end_date);
                rfpBean.setRfpClosed("Y".equals(rfp_closed) ? true : false);
                rfpBean.setRfpClosedDate(rfp_closed_date);
            }
            if (currentRfpId.equals(prevRfpId)) {
                if ("Y".equals(shortlisted)) {
                    shortListedVendor.add(ven_name + "~" + vendor_id + "~" + company + "~" + venUserName);
                }
                if ("Y".equals(finalized)) {
                    finalizedVendor.add(ven_name + "~" + vendor_id + "~" + company + "~" + venUserName);
                }
                if (interest_shown_date != null) {
                    interestedVendor.add(ven_name + "~" + vendor_id + "~" + company + "~" + venUserName);
                }
            } else {
                rfpBean.setInterestedVendor(interestedVendor);
                rfpBean.setShortListedVendor(shortListedVendor);
                rfpBean.setFinalizedVendor(finalizedVendor);
                consumerRfpList.add(rfpBean);
                rfpBean = new RfpBean();
                shortListedVendor = new ArrayList<String>();
                finalizedVendor = new ArrayList<String>();
                interestedVendor = new ArrayList<String>();
                rfpBean.setRfpId(currentRfpId);
                rfpBean.setConsumerId(consumer_id);
                rfpBean.setRfpTitle(rfp_title);
                rfpBean.setRfpShortDesc(rfp_short_desc);
                rfpBean.setRfpDetailedDesc(rfp_detailed_desc);
                rfpBean.setRfpCreateddate(created_date);
                rfpBean.setRfpEnddate(rfp_end_date);
                rfpBean.setRfpClosed("Y".equals(rfp_closed) ? true : false);
                rfpBean.setRfpClosedDate(rfp_closed_date);
                if ("Y".equals(shortlisted)) {
                    shortListedVendor.add(ven_name + "~" + vendor_id + "~" + company + "~" + venUserName);
                }
                if ("Y".equals(finalized)) {
                    finalizedVendor.add(ven_name + "~" + vendor_id + "~" + company + "~" + venUserName);
                }
                if (interest_shown_date != null) {
                    interestedVendor.add(ven_name + "~" + vendor_id + "~" + company + "~" + venUserName);
                }
            }
            prevRfpId = currentRfpId;
        }
        if (rfpBean != null) {
            consumerRfpList.add(rfpBean);
        }
        return consumerRfpList;
    }

    @Override
    public boolean expresssRfpInterest(String rfpId, String vendorId, boolean revoke) {
        SQLQuery sqlQuery = null;
        if (revoke) {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(REVOKE_VENDOR_RFP_INTEREST);
        } else {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(EXPRESS_VENDOR_RFP_INTEREST);
        }
        sqlQuery.setParameter("rfp_id", rfpId);
        sqlQuery.setParameter("vendor_id", vendorId);
        sqlQuery.executeUpdate();
        return true;
    }

    @Override
    public void shortlistRfpVendors(String rfpId, List<String> vendorList, boolean finalize) {
        SQLQuery sqlQuery = null;
        if (finalize) {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(FINALIZED_RFP_VENDORS);
        } else {
            sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SHORTLIST_RFP_VENDORS);
        }
        for (String vendorId : vendorList) {
            sqlQuery.setParameter("rfp_id", rfpId);
            sqlQuery.setParameter("vendor_id", vendorId);
            sqlQuery.executeUpdate();
            if (finalize) {
                sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(FINALIZED_RFP_VENDORS);
            } else {
                sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SHORTLIST_RFP_VENDORS);
            }
        }

    }

    @Override
    public void requestRfpMoreInfo(String rfpId, String moreInfoDetails, String vendorId) {
        SQLQuery sqlQuery = null;
        sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(REQUEST_VENDOR_RFP_MORE_INFO);
        sqlQuery.setParameter("rfp_id", rfpId);
        sqlQuery.setParameter("vendor_id", vendorId);
        sqlQuery.executeUpdate();

        sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(INSERT_VENDOR_RFP_MORE_INFO);
        sqlQuery.setParameter("id", UUID.randomUUID());
        sqlQuery.setParameter("rfp_id", rfpId);
        sqlQuery.setParameter("vendor_id", vendorId);
        sqlQuery.setParameter("more_info", vendorId);

        sqlQuery.executeUpdate();
    }

    @Override
    public void updateRfpMoreInfo(String id, String rfpId, String moreInfoDetails, String vendorId) {
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_CONSUMER_RFP_MORE_INFO);
        sqlQuery.setParameter("rfp_id", rfpId);
        sqlQuery.setParameter("vendor_id", vendorId);
        sqlQuery.executeUpdate();

        sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(UPDATE_CONSUMER_RFP_MORE_INFO_DETAILS);
        sqlQuery.setParameter("consumer_reply", moreInfoDetails);
        sqlQuery.setParameter("id", id);
        sqlQuery.executeUpdate();
    }

    @Override
    public List<Object[]> selectMyRfpVendor(String vendorId) {
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SELECT_VENDOR_RFP_LIST);
        sqlQuery.setParameter("vendor_id", vendorId);
        @SuppressWarnings("unchecked")
        List<Object[]> results = sqlQuery.list();
        return results;
    }

    @Override
    public List<Object[]> selectMyRfpListVendor() {
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SELECT_APPLICABLE_RFP_FOR_VENDOR);
        @SuppressWarnings("unchecked")
        List<Object[]> results = sqlQuery.list();
        return results;
    }


    @Override
    public List<String> getVendorsEmail(String targetVendorType) {
        List<String> vendorEmailList = new ArrayList<String>();
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(SELECT_VENDOR_EMAIL);
        sqlQuery.setParameter("target_vendor_type", targetVendorType);
        @SuppressWarnings("unchecked")
        List<Object[]> results = sqlQuery.list();
        for (Object[] emailRow : results) {
            vendorEmailList.add(emailRow[0].toString());
            if (emailRow[1] != null) {
                vendorEmailList.add(emailRow[1].toString());
            }
        }
        return vendorEmailList;
    }

}
