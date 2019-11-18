package com.finvendor.api.subscription.dao;

import com.finvendor.api.common.dto.RecordStats;
import com.finvendor.api.exception.ApiBadRequestException;
import com.finvendor.api.subscription.dto.SubscriptionDetails;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.dto.SubscriptionFilter;
import com.finvendor.api.subscription.dto.UsersSubscriptionDto;
import com.finvendor.api.subscription.enums.SubscriptionStatesEnum;
import com.finvendor.api.subscription.enums.SubscriptionTypeEnum;
import com.finvendor.api.user.dao.UserDao;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.Pair;
import com.finvendor.model.subscription.UsersSubscription;
import com.finvendor.model.subscription.UsersSubscriptionHistory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Repository
public class SubscriptionDao extends GenericDao<UsersSubscription> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SubscriptionDao.class.getName());

    private static final String USERS = "users";
    private final UserDao userDao;
    private final ICommonDao commonDao;
    private SubscriptionHistoryDao subscriptionHistoryDao;

    @Autowired
    public SubscriptionDao(ICommonDao commonDao, UserDao userDao,
            SubscriptionHistoryDao subscriptionHistoryDao) {
        this.commonDao = commonDao;
        this.userDao = userDao;
        this.subscriptionHistoryDao = subscriptionHistoryDao;
    }

    public RecordStats getSubscriptionsRecordStats(String state, String perPageMaxRecords, SubscriptionFilter filter)
            throws IOException {
        String findAllSubscriptionSql = getFindAllSubscriptionSql(state, filter);
        SQLQuery sqlQuery = commonDao.getNativeQuery(findAllSubscriptionSql, null);
        List<Object[]> rows = sqlQuery.list();

        int totalRecords = rows.size();

        // Calculate Last page number
        long lastPageNumber = CommonCodeUtils.calculatePaginationLastPage(perPageMaxRecords, totalRecords);

        // Prepare Json result
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("firstPageNumber", 1);
        paramsMap.put("lastPageNumber", lastPageNumber);
        paramsMap.put("totalRecords", totalRecords);
        return new RecordStats(1, lastPageNumber, totalRecords);
        //return new Pair<>(JsonUtil.createJsonFromObject(paramsMap), totalRecords);
    }

    public List<UsersSubscriptionDto> findAllUsersSubscription(String state, String pageNumber, String perPageMaxRecords, String sortBy,
            String orderBy, SubscriptionFilter filter) {
        try {
            String finalSql;
            finalSql = getFindAllSubscriptionSql(state, filter) + getSortBySql(sortBy, orderBy);
            LOG.info("## findAllSubscription - finalSql: {}", finalSql);
            List<UsersSubscriptionDto> paymentDtoList = new ArrayList<>();
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
            String findAllSubscriptionQuery = finalSql + applyPagination;

            SQLQuery query1 = commonDao.getNativeQuery(findAllSubscriptionQuery, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : "";

                String subscriptionDateStr = row[1] != null ? row[1].toString().trim() : "";
                long subscriptionDate = StringUtils.isNotEmpty(subscriptionDateStr) ?
                        DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionDateStr) :
                        0L;

                String subscriptionType = row[2] != null ? row[2].toString().trim() : "";
                String subscriptionState = row[3] != null ? row[3].toString().trim() : "";
                String subscriptionStartTime = row[4] != null ? row[4].toString().trim() : "";
                long subscriptionStartTimeMs = StringUtils.isNotEmpty(subscriptionStartTime) ?
                        DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionStartTime) : 0L;

                String subscriptionEndTime = row[5] != null ? row[5].toString().trim() : "";
                long subscriptionEndTimeMs = StringUtils.isNotEmpty(subscriptionEndTime) ?
                        DateUtils.get_Timestamp_From_DD_MMM_YYYY_hh_Format(subscriptionEndTime) : 0L;

                String subscriptionId = row[6] != null ? row[6].toString().trim() : "";
                String transactionRefNumber = row[7] != null ? row[7].toString().trim() : "";

                String transactionDateStr = row[8] != null ? row[8].toString().trim() : "";
                long transactionDate = StringUtils.isNotEmpty(transactionDateStr) ? Long.parseLong(transactionDateStr) : 0L;

                String transactionFor = row[9] != null ? row[9].toString().trim() : "";
                String paymentMode = row[10] != null ? row[10].toString().trim() : "";

                String amtTransferredStr = row[11] != null ? row[11].toString().trim() : "";
                double amtTransferred = StringUtils.isNotEmpty(amtTransferredStr) ? Double.parseDouble(amtTransferredStr) : 0.0D;

                String bankName = row[12] != null ? row[12].toString().trim() : "";
                String bankHolderName = row[13] != null ? row[13].toString().trim() : "";
                String paymentVerified = row[14] != null ? row[14].toString().trim() : "FALSE";

                UsersSubscriptionDto dto = new UsersSubscriptionDto(userName, subscriptionDate, subscriptionType, subscriptionState,
                        subscriptionStartTimeMs, subscriptionEndTimeMs, subscriptionId, transactionRefNumber,
                        transactionDate, transactionFor, paymentMode, amtTransferred, bankName, bankHolderName, paymentVerified);
                paymentDtoList.add(dto);
            }
            return paymentDtoList;
        } catch (Exception e) {
            throw new RuntimeException("## Error has occurred while finding user subscription details.", e);
        }
    }

    private String getSortBySql(String sortBy, String orderBy) {
        return "order by " + sortBy + ("desc".equalsIgnoreCase(orderBy) ? " desc" : " asc");
    }

    private String getFindAllSubscriptionSql(String state, SubscriptionFilter filter) {
        String finalSql;
        if (filter != null) {
            if ("all".equals(state)) {
                finalSql =
                        "select a.username,a.subscription_date,a.subscription_type,a.subscription_state, a.subscription_start_time,a.subscription_end_time, a.subscription_id,a.transaction_ref_number, a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from users_subscription a where a.username=a.username and a.subscription_type not in('FREE') "
                                + " and (cast(a.subscription_date as UNSIGNED INTEGER)>=" + filter.getFrom()
                                + " and cast(a.subscription_date as UNSIGNED INTEGER)<= " + filter.getTo() + ")";
            }
            else {
                finalSql =
                        "select a.username,a.subscription_date,a.subscription_type,a.subscription_state, a.subscription_start_time,a.subscription_end_time, a.subscription_id,a.transaction_ref_number, a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from users_subscription a where a.username=a.username and a.subscription_type not in('FREE') and a.subscription_state='"
                                + state + "' and (cast(a.subscription_date as UNSIGNED INTEGER)>=" + filter.getFrom()
                                + " and cast(a.subscription_date as UNSIGNED INTEGER)<= " + filter.getTo() + ")";
            }
        }
        else {
            if ("all".equals(state)) {
                finalSql = "select a.username,a.subscription_date,a.subscription_type,a.subscription_state, a.subscription_start_time,a.subscription_end_time, a.subscription_id,a.transaction_ref_number, a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from users_subscription a where a.username=a.username and a.subscription_type not in('FREE') ";
            }
            else {
                finalSql =
                        "select a.username,a.subscription_date,a.subscription_type,a.subscription_state, a.subscription_start_time,a.subscription_end_time, a.subscription_id,a.transaction_ref_number, a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from users_subscription a where a.username=a.username  and a.subscription_type not in('FREE') and a.subscription_state='"
                                + state + "'";
            }
        }
        return finalSql;
    }

    public ApiMessageEnum update_UsersSubscription(SubscriptionDto subscriptionDto, SubscriptionDetails subscriptionDetails) {
        LOG.info("## update_UsersSubscription - START");
        ApiMessageEnum apiMessageEnum;
        try {
            UsersSubscription usersSubscriptionEntity = findById(subscriptionDetails.getSubscriptionId());
            if (usersSubscriptionEntity != null) {
                if ("TRUE".equals(usersSubscriptionEntity.getPaymentVerified())) {
                    apiMessageEnum = ApiMessageEnum.PAYMENT_ALREADY_VERIFIED;
                }
                else {
                    setSubscriptionStartAndEndTime(usersSubscriptionEntity, 30);
                    setPaymentVerified(usersSubscriptionEntity, subscriptionDto);
                    setSubscriptionState(usersSubscriptionEntity, subscriptionDto.getPaymentVerified());
                    String subscriptionType = usersSubscriptionEntity.getSubscriptionType();
                    //Handle Renew case
                    if (subscriptionType.contains("_RENEW")) {
                        LOG.info("## Old Subscription Type: {}", subscriptionType);
                        subscriptionType = subscriptionType.substring(0, subscriptionType.lastIndexOf("_"));
                        LOG.info("## New Subscription Type: {}", subscriptionType);
                        usersSubscriptionEntity.setSubscriptionType(subscriptionType);
                    }
                    updateUsersSubscription(usersSubscriptionEntity);
                    apiMessageEnum = ApiMessageEnum.UPDATE_SUBSCRIPTION_SUCCESS;
                    //userService.sendActivationOrTerminationMail(usersSubscriptionEntity);
                }
            }
            else {
                apiMessageEnum = ApiMessageEnum.SUBSCRIPTION_ID_NOT_EXIST;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while update user subscription details.", e);
        }
        return apiMessageEnum;
    }

    private void setPaymentVerified(UsersSubscription usersSubscriptionEntity, SubscriptionDto subscriptionDto) {
        usersSubscriptionEntity.setPaymentVerified(subscriptionDto.getPaymentVerified() ? "TRUE" : "FALSE");
    }

    public boolean saveUserSubscription(String userName, SubscriptionDto subscriptionDto) throws Exception {
        Boolean renewSubscription = subscriptionDto.getRenewSubscription();
        LOG.info("## saveUserSubscription - START userName: {}", userDao);
        LOG.info("## renewSubscription: {}", renewSubscription);
        //Handle Renew
        if(renewSubscription != null) {
            UsersSubscription existingUserSubscription = findUsersSubscription(userName);
            String existingSubscriptionType = existingUserSubscription.getSubscriptionType();
            String existingSubscriptionState = existingUserSubscription.getSubscriptionState();

            //if subscription type smart or sage active then renewSubscription must be true
            if ((SubscriptionTypeEnum.SMART.name().equals(existingSubscriptionType)
                    || SubscriptionTypeEnum.SAGE.name().equals(existingSubscriptionType)
            ) && SubscriptionStatesEnum.ACTIVE.name().equals(existingSubscriptionState)) {
                if (!renewSubscription) {
                    LOG.info("## Subscription type smart or sage active then renewSubscription must be true - CASE");
                    LOG.info("## existingSubscriptionType: {}", existingSubscriptionType);
                    LOG.info("## existingSubscriptionState: {}", existingSubscriptionState);
                    LOG.info("## renewSubscription: {}", false);
                    throw new ApiBadRequestException(
                            "\"renewSubscription\" attribute value must be true in api request if Subscription Type: SMART or SAGE and "
                                    + "Subscription State: ACTIVE");
                }
            }
            if (renewSubscription) {
                if (subscriptionDto.getSubscriptionType().equals(SubscriptionTypeEnum.SMART.name())) {
                    subscriptionDto.setSubscriptionType(SubscriptionTypeEnum.SMART_RENEW.name());
                }
                else {
                    subscriptionDto.setSubscriptionType(SubscriptionTypeEnum.SAGE_RENEW.name());
                }
            }
        }
        UsersSubscription usersSubscriptionEntity = new UsersSubscription();
        String subscriptionId;
        try {
            SQLQuery query1 = commonDao
                    .getNativeQuery("select a.subscription_id,a.username from users_subscription a where a.username=?",
                            new Object[] { userName });
            List<Object[]> rows = query1.list();
            if (rows.size() != 0) {
                //size of loop is 1
                for (Object[] row : rows) {
                    subscriptionId = row[0] != null ? row[0].toString().trim() : "";
                    usersSubscriptionEntity = findById(subscriptionId);
                    usersSubscriptionEntity.setUserName(userName);
                    usersSubscriptionEntity
                            .setSubscriptionDate(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(Calendar.getInstance().getTimeInMillis()));
                    usersSubscriptionEntity.setTrialPeriodStartTime(null);
                    usersSubscriptionEntity.setTrialPeriodEndTime(null);
                    usersSubscriptionEntity.setSubscriptionStartTime(null);
                    usersSubscriptionEntity.setSubscriptionEndTime(null);
                    usersSubscriptionEntity.setSubscriptionState(SubscriptionStatesEnum.PENDING.name());
                    setUsersSubscriptionEntity(subscriptionDto, usersSubscriptionEntity);
                }
            }
            else {
                //This else block will be call from Registration time
                subscriptionId = String.valueOf(UUID.randomUUID());
                usersSubscriptionEntity.setSubscriptionId(subscriptionId);
                usersSubscriptionEntity.setUserName(userName);
                //setUsersSubscriptionEntity(subscriptionDto, usersSubscriptionEntity);
                usersSubscriptionEntity.setSubscriptionDate(null);
                usersSubscriptionEntity.setSubscriptionType(SubscriptionTypeEnum.FREE.name());
                usersSubscriptionEntity.setTrialPeriodStartTime(null);
                usersSubscriptionEntity.setTrialPeriodEndTime(null);
                usersSubscriptionEntity.setSubscriptionStartTime(null);
                usersSubscriptionEntity.setSubscriptionEndTime(null);
                usersSubscriptionEntity.setSubscriptionState(null);
                usersSubscriptionEntity.setTransactionRefNumber(null);
                usersSubscriptionEntity.setTransactionDate(null);
                usersSubscriptionEntity.setTransactionFor(null);
                usersSubscriptionEntity.setPaymentMode(null);
                usersSubscriptionEntity.setAmountTransferred(null);
                usersSubscriptionEntity.setBankName(null);
                usersSubscriptionEntity.setBankHolderName(null);
                usersSubscriptionEntity.setPaymentVerified(null);
            }
            saveOrUpdate(usersSubscriptionEntity);
            subscriptionHistoryDao.saveSubscriptionHistory(usersSubscriptionEntity);
        } catch (Exception e) {
            throw new RuntimeException("## Error has occurred while saving user subscription details.", e);
        }
        return true;
    }

    private void setSubscriptionHistory(UsersSubscription UsersSubscriptionEntity, UsersSubscriptionHistory usersSubscriptionHistory) {
        if (UsersSubscriptionEntity.getSubscriptionDate() != null) {
            usersSubscriptionHistory.setSubscriptionDate(UsersSubscriptionEntity.getSubscriptionDate());
        }
        if (UsersSubscriptionEntity.getSubscriptionType() != null) {
            usersSubscriptionHistory.setSubscriptionType(UsersSubscriptionEntity.getSubscriptionType());
        }
        if (UsersSubscriptionEntity.getTrialPeriodStartTime() != null) {
            usersSubscriptionHistory.setTrialPeriodStartTime(UsersSubscriptionEntity.getTrialPeriodStartTime());
        }
        if (UsersSubscriptionEntity.getTrialPeriodEndTime() != null) {
            usersSubscriptionHistory.setTrialPeriodEndTime(UsersSubscriptionEntity.getTrialPeriodEndTime());
        }
        if (UsersSubscriptionEntity.getSubscriptionStartTime() != null) {
            usersSubscriptionHistory.setSubscriptionStartTime(UsersSubscriptionEntity.getSubscriptionStartTime());
        }
        if (UsersSubscriptionEntity.getSubscriptionEndTime() != null) {
            usersSubscriptionHistory.setSubscriptionEndTime(UsersSubscriptionEntity.getSubscriptionEndTime());
        }
        //
        if (UsersSubscriptionEntity.getSubscriptionState() != null) {
            usersSubscriptionHistory.setSubscriptionState(UsersSubscriptionEntity.getSubscriptionState());
        }
        if (UsersSubscriptionEntity.getTransactionRefNumber() != null) {
            usersSubscriptionHistory.setTransactionRefNumber(UsersSubscriptionEntity.getTransactionRefNumber());
        }
        if (UsersSubscriptionEntity.getTransactionDate() != null) {
            usersSubscriptionHistory.setTransactionDate(UsersSubscriptionEntity.getTransactionDate());
        }
        if (UsersSubscriptionEntity.getSubscriptionType() != null) {
            usersSubscriptionHistory.setTransactionFor(UsersSubscriptionEntity.getSubscriptionType());
        }
        if (UsersSubscriptionEntity.getPaymentMode() != null) {
            usersSubscriptionHistory.setPaymentMode(UsersSubscriptionEntity.getPaymentMode());
        }
        if (UsersSubscriptionEntity.getAmountTransferred() != null) {
            usersSubscriptionHistory.setAmountTransferred(UsersSubscriptionEntity.getAmountTransferred());
        }
        if (UsersSubscriptionEntity.getBankName() != null) {
            usersSubscriptionHistory.setBankName(UsersSubscriptionEntity.getBankName());
        }
        if (UsersSubscriptionEntity.getBankHolderName() != null) {
            usersSubscriptionHistory.setBankHolderName(UsersSubscriptionEntity.getBankHolderName());
        }
        if (UsersSubscriptionEntity.getPaymentVerified() != null) {
            usersSubscriptionHistory.setPaymentVerified(UsersSubscriptionEntity.getPaymentVerified());
        }
        else {
            usersSubscriptionHistory.setPaymentVerified(UsersSubscriptionEntity.getPaymentVerified());
        }
        usersSubscriptionHistory.setInventoryDate(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(Calendar.getInstance().getTimeInMillis()));
    }

    private void setUsersSubscriptionEntity(SubscriptionDto dto, UsersSubscription usersSubscriptionEntity) {
        if (dto.getSubscriptionType() != null) {
            usersSubscriptionEntity.setSubscriptionType(dto.getSubscriptionType());
        }
        if (dto.getTransactionRefNumber() != null) {
            usersSubscriptionEntity.setTransactionRefNumber(dto.getTransactionRefNumber());
        }
        if (dto.getTransactionDate() != null) {
            usersSubscriptionEntity.setTransactionDate(String.valueOf(dto.getTransactionDate()));
        }
        if (dto.getSubscriptionType() != null) {
            usersSubscriptionEntity.setTransactionFor(dto.getSubscriptionType());
        }
        if (dto.getPaymentMode() != null) {
            usersSubscriptionEntity.setPaymentMode(dto.getPaymentMode());
        }
        if (dto.getAmountTransferred() != null) {
            usersSubscriptionEntity.setAmountTransferred(String.valueOf(dto.getAmountTransferred()));
        }
        if (dto.getBankName() != null) {
            usersSubscriptionEntity.setBankName(dto.getBankName());
        }
        if (dto.getBankHolderName() != null) {
            usersSubscriptionEntity.setBankHolderName(dto.getBankHolderName());
        }
        if (dto.getPaymentVerified() != null) {
            setPaymentVerified(usersSubscriptionEntity, dto);
        }
        else {
            usersSubscriptionEntity.setPaymentVerified("FALSE");
        }
    }

    public void saveUsersSubscription(UsersSubscription usersSubscription) {
        LOG.debug("## saveUsersSubscription -START");
        try {
            this.commonDao.getSessionFactory().getCurrentSession().save(usersSubscription);
            this.commonDao.getSessionFactory().getCurrentSession().flush();
        } catch (Exception exp) {
            throw new RuntimeException("User already exist", exp);
        }
        LOG.debug("## saveUsersSubscription -END");
    }

    public void updateUsersSubscription(UsersSubscription usersSubscription) {
        LOG.debug("## updateUsersSubscription - START");
        try {
            this.commonDao.getSessionFactory().getCurrentSession().update(usersSubscription);
        } catch (Exception exp) {
            throw new RuntimeException("Failed to update usersSubscription", exp);
        }
        LOG.debug("## updateUsersSubscription - END");
    }

    public UsersSubscription findUsersSubscription(String userName) throws ApplicationException {
        try {
            Criteria criteria = this.commonDao.getSessionFactory().getCurrentSession().createCriteria(UsersSubscription.class);
            criteria.add(Restrictions.eq("userName", userName));
            List list = criteria.list();
            return list.size() > 0 ? (UsersSubscription) list.get(0) : null;
        } catch (Exception exp) {
            LOG.error("Error findUsersSubscription : " + exp);
            throw new RuntimeException("Error Reading Users Subscription Details : " + userName);
        }
    }

    private void setSubscriptionStartAndEndTime(UsersSubscription usersSubscriptionEntity, int numberOfDays) {
        Pair<String, String> subscriptionStartAndEndDate = DateUtils.getSubscriptionStartAndEndDateInHumanDate(numberOfDays);
        String subscriptionStartTime = subscriptionStartAndEndDate.getElement1();
        String subscriptionEndTime = subscriptionStartAndEndDate.getElement2();
        LOG.info("## Subscription start time: {}", subscriptionStartTime);
        LOG.info("## Subscription end time: {}", subscriptionStartTime);
        //set subscription time period
        usersSubscriptionEntity.setSubscriptionStartTime(subscriptionStartTime);
        usersSubscriptionEntity.setSubscriptionEndTime(subscriptionEndTime);
    }

    private void setSubscriptionState(UsersSubscription usersSubscriptionEntity, Boolean paymentVerified) {
        //Set subscription STATE
        if (paymentVerified) {
            usersSubscriptionEntity.setSubscriptionState(SubscriptionStatesEnum.ACTIVE.name());
        }
        else {
            usersSubscriptionEntity.setSubscriptionState(SubscriptionStatesEnum.TERMINATE.name());
        }
    }

    public void updateUserSubscriptionToFree(UsersSubscription usersSubscription) {
        try {
            usersSubscription.setSubscriptionDate(null);
            usersSubscription.setSubscriptionType(SubscriptionTypeEnum.FREE.name());
            usersSubscription.setTrialPeriodStartTime(null);
            usersSubscription.setTrialPeriodEndTime(null);
            usersSubscription.setSubscriptionStartTime(null);
            usersSubscription.setSubscriptionEndTime(null);
            usersSubscription.setSubscriptionState(null);
            usersSubscription.setTransactionRefNumber(null);
            usersSubscription.setTransactionDate(null);
            usersSubscription.setTransactionFor(null);
            usersSubscription.setPaymentMode(null);
            usersSubscription.setAmountTransferred(null);
            usersSubscription.setBankName(null);
            usersSubscription.setBankHolderName(null);
            usersSubscription.setPaymentVerified(null);
            saveOrUpdate(usersSubscription);
        } catch (Exception exp) {
            throw new RuntimeException("Failed to update user", exp);
        }
    }

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;
    public static final String NA = "NA";

    public Pair<Long, InputStream> downloadSubscriptions() throws RuntimeException {
        String mainQuery = "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time,b.subscription_end_time,a.subscription_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from users_subscription a, users b  where b.username=a.username";
        SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query1.list();
        String fileName = finvendorProperties.getProperty("finvendo_tmp_path") + File.separator + "subscriptions.csv";

        File csvFile1 = new File(fileName);
        FileWriter fw1 = null;
        try {
            fw1 = new FileWriter(csvFile1);
            fw1.append("UserName");
            fw1.append(',');
            fw1.append("SubscriptionDate");
            fw1.append(',');
            fw1.append("SubscriptionType");
            fw1.append(',');
            fw1.append("SubscriptionState");
            fw1.append(',');
            fw1.append("SubscriptionStartTime");
            fw1.append(',');
            fw1.append("SubscriptionEndTime");
            fw1.append(',');
            fw1.append("SubscriptionRefId");
            fw1.append(',');
            fw1.append("TransactionRefNumber");
            fw1.append(',');
            fw1.append("TransactionDate");
            fw1.append(',');
            fw1.append("TransactionFor");
            fw1.append(',');
            fw1.append("PaymentMode");
            fw1.append(',');
            fw1.append("AmountTransferred");
            fw1.append(',');
            fw1.append("BankName");
            fw1.append(',');
            fw1.append("BankHolderName");
            fw1.append(',');
            fw1.append("PaymentVerified");

            fw1.append('\n');

            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : NA;
                String subscriptionDate = row[1] != null ? row[1].toString().trim() : NA;
                String subscriptionType = row[2] != null ? row[2].toString().trim() : NA;
                String subscriptionState = row[3] != null ? row[3].toString().trim() : NA;
                String subscriptionStartTimeMs = row[4] != null ? row[4].toString().trim() : NA;
                String subscriptionEndTimeMs = row[5] != null ? row[5].toString().trim() : NA;
                String subscriptionRefId = row[6] != null ? row[6].toString().trim() : NA;
                String transactionRefNumber = row[7] != null ? row[7].toString().trim() : NA;
                String transactionDate = row[8] != null ? row[8].toString().trim() : NA;
                String transactionFor = row[9] != null ? row[9].toString().trim() : NA;
                String paymentMode = row[10] != null ? row[10].toString().trim() : NA;
                String amountTransferred = row[11] != null ? row[11].toString().trim() : NA;
                String bankName = row[12] != null ? row[12].toString().trim() : NA;
                String bankHolderName = row[13] != null ? row[13].toString().trim() : NA;
                String paymentVerified = row[14] != null ? row[14].toString().trim() : NA;
                fw1.append(userName);
                fw1.append(',');
                fw1.append(DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(subscriptionDate));
                fw1.append(',');
                fw1.append(subscriptionType);
                fw1.append(',');
                fw1.append(subscriptionState);
                fw1.append(',');
                fw1.append(subscriptionStartTimeMs);
                fw1.append(',');
                fw1.append(subscriptionEndTimeMs);
                fw1.append(',');
                fw1.append(subscriptionRefId);
                fw1.append(',');
                fw1.append(transactionRefNumber);
                fw1.append(',');
                fw1.append(transactionDate);
                fw1.append(',');
                fw1.append(transactionFor);
                fw1.append(',');
                fw1.append(paymentMode);
                fw1.append(',');
                fw1.append(amountTransferred);
                fw1.append(',');
                fw1.append(bankName);
                fw1.append(',');
                fw1.append(bankHolderName);
                fw1.append(',');
                fw1.append(paymentVerified);
                fw1.append('\n');
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while downloading subscription data", e);
        } finally {
            if (fw1 != null) {
                try {
                    fw1.close();
                } catch (IOException e) {
                    LOG.error("Unable to close FileWriter while downloading subscription data");
                }
            }
            else {
                LOG.error("FileWriter found null while downloading subscription data");
            }
        }
        try {
            long length = csvFile1.length();
            InputStream inputStream = FileUtils.openInputStream(csvFile1);
            return new Pair<>(length, inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error has occurred while downloading subscription data", e);
        }
    }
}
