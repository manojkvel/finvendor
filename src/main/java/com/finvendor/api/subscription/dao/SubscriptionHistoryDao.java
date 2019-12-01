package com.finvendor.api.subscription.dao;

import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.Pair;
import com.finvendor.model.subscription.UsersSubscription;
import com.finvendor.model.subscription.UsersSubscriptionHistory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class SubscriptionHistoryDao extends GenericDao<UsersSubscriptionHistory> {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionHistoryDao.class.getName());
    private final ICommonDao commonDao;

    @Autowired
    public SubscriptionHistoryDao(ICommonDao commonDao) {
        this.commonDao = commonDao;
    }

    /**
     * Update UsersSubscriptionHistory table for payment verified column if user made payment
     */
    public void saveSubscriptionHistory(UsersSubscription usersSubscription) {
        logger.info("## SubscriptionHistoryDao - saveSubscriptionHistory - START ");
        UsersSubscriptionHistory usersSubscriptionHistory = new UsersSubscriptionHistory();
        usersSubscriptionHistory.setUserName(usersSubscription.getUserName());
        usersSubscriptionHistory.setSubscriptionDate(usersSubscription.getSubscriptionDate());
        usersSubscriptionHistory.setSubscriptionType(usersSubscription.getSubscriptionType());
        usersSubscriptionHistory.setTrialPeriodStartTime(usersSubscription.getTrialPeriodStartTime());
        usersSubscriptionHistory.setTrialPeriodEndTime(usersSubscription.getTrialPeriodEndTime());
        usersSubscriptionHistory.setSubscriptionStartTime(usersSubscription.getSubscriptionStartTime());
        usersSubscriptionHistory.setSubscriptionEndTime(usersSubscription.getSubscriptionEndTime());
        usersSubscriptionHistory.setSubscriptionState(usersSubscription.getSubscriptionState());
        usersSubscriptionHistory.setTransactionRefNumber(usersSubscription.getTransactionRefNumber());
        usersSubscriptionHistory.setTransactionDate(usersSubscription.getTransactionDate());
        usersSubscriptionHistory.setTransactionFor(usersSubscription.getTransactionFor());
        usersSubscriptionHistory.setPaymentMode(usersSubscription.getPaymentMode());
        usersSubscriptionHistory.setAmountTransferred(usersSubscription.getAmountTransferred());
        usersSubscriptionHistory.setBankName(usersSubscription.getBankName());
        usersSubscriptionHistory.setBankHolderName(usersSubscription.getBankHolderName());
        usersSubscriptionHistory.setPaymentVerified(usersSubscription.getPaymentVerified());
        usersSubscriptionHistory.setUpdatedOn(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(Calendar.getInstance().getTimeInMillis()));
        usersSubscriptionHistory.setSubscriptionId(usersSubscription.getSubscriptionId());
        saveOrUpdate(usersSubscriptionHistory);
    }

    public boolean isFreeTrialAlreadyChosen(String userName) {
        Criteria criteria = this.commonDao.getSessionFactory().getCurrentSession().createCriteria(UsersSubscriptionHistory.class);
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("subscriptionState", "TRIAL"));
        List list = criteria.list();
        return list.size() > 0;
    }

    public UsersSubscriptionHistory find_PreviousSubscription(String subscriptionType) {
        List<UsersSubscriptionHistory> all = findAll();
        all.sort((o1, o2) -> o2.getRowId().compareTo(o1.getRowId()));
        for (UsersSubscriptionHistory ush : all) {
            if (!subscriptionType.equals(ush.getSubscriptionType())) {
                return ush;
            }
        }
        return null;
    }

    public Pair<UsersSubscriptionHistory, UsersSubscriptionHistory> find_top2Subscription() {
        List<UsersSubscriptionHistory> all = findAll();
        all.sort((o1, o2) -> o2.getRowId().compareTo(o1.getRowId()));

        return all.size() >= 2 ? new Pair<>(all.get(0), all.get(1)) : null;

    }

}
