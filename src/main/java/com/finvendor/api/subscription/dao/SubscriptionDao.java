package com.finvendor.api.subscription.dao;

import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.dto.SubscriptionFilter;
import com.finvendor.api.subscription.dto.UserPaymentDto;
import com.finvendor.api.subscription.dto.UserSubscriptionDto;
import com.finvendor.api.user.dao.UserDao;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.model.subscription.UserPayment;
import org.apache.commons.io.FileUtils;
import org.hibernate.SQLQuery;
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
public class SubscriptionDao extends GenericDao<UserPayment> {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SubscriptionDao.class.getName());

    private static final String USERS = "users";
    private final UserDao userDao;
    private final ICommonDao commonDao;

    @Autowired
    public SubscriptionDao(ICommonDao commonDao, UserDao userDao) {
        this.commonDao = commonDao;
        this.userDao = userDao;
    }

    public String getSubscriptionsRecordStats(String state, String perPageMaxRecords, SubscriptionFilter filter) throws IOException {
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

        return JsonUtil.createJsonFromObject(paramsMap);
    }

    public List<UserPaymentDto> findAllPayments(String state, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy,
            SubscriptionFilter filter) {
        try {
            String finalSql;
            finalSql = getFindAllSubscriptionSql(state, filter) + getSortBySql(sortBy, orderBy);
            LOGGER.info("findAllSubscription - finalSql: {}", finalSql);
            List<UserPaymentDto> paymentDtoList = new ArrayList<>();
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);
            String findAllSubscriptionQuery = finalSql + applyPagination;

            SQLQuery query1 = commonDao.getNativeQuery(findAllSubscriptionQuery, null);
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : "";

                String subscriptionDateStr = row[1] != null ? row[1].toString().trim() : "";
                long subscriptionDate = Long.parseLong(subscriptionDateStr);

                String subscriptionType = row[2] != null ? row[2].toString().trim() : "";
                String subscriptionState = row[3] != null ? row[3].toString().trim() : "";
                String subscriptionStartTimeMsStr = row[4] != null ? row[4].toString().trim() : "";
                long subscriptionStartTimeMs = (!"N/A".equalsIgnoreCase(subscriptionStartTimeMsStr)) ?
                        Long.parseLong(subscriptionStartTimeMsStr) :
                        0L;

                String subscriptionEndTimeMsStr = row[5] != null ? row[5].toString().trim() : "";
                long subscriptionEndTimeMs = (!"N/A".equalsIgnoreCase(subscriptionEndTimeMsStr)) ?
                        Long.parseLong(subscriptionEndTimeMsStr) :
                        0L;

                String subscriptionRefId = row[6] != null ? row[6].toString().trim() : "";
                String transactionRefNumber = row[7] != null ? row[7].toString().trim() : "";

                String transactionDateStr = row[8] != null ? row[8].toString().trim() : "";
                long transactionDate = Long.parseLong(transactionDateStr);

                String transactionFor = row[9] != null ? row[9].toString().trim() : "";
                String paymentMode = row[10] != null ? row[10].toString().trim() : "";

                String amtTransferredStr = row[11] != null ? row[11].toString().trim() : "";
                double amtTransferred = Double.parseDouble(amtTransferredStr);

                String bankName = row[12] != null ? row[12].toString().trim() : "";
                String bankHolderName = row[13] != null ? row[13].toString().trim() : "";
                String paymentVerified = row[14] != null ? row[14].toString().trim() : "FALSE";

                UserPaymentDto dto = new UserPaymentDto(userName, subscriptionDate, subscriptionType, subscriptionState,
                        subscriptionStartTimeMs, subscriptionEndTimeMs, subscriptionRefId, transactionRefNumber,
                        transactionDate, transactionFor, paymentMode, amtTransferred, bankName, bankHolderName, paymentVerified);
                paymentDtoList.add(dto);
            }
            return paymentDtoList;
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while finding user subscription details.", e);
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
                        "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time_ms,b.subscription_end_time_ms,a.subscription_ref_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from user_payment a, users b  where b.username=a.username "
                                + " and (cast(subscription_date as UNSIGNED INTEGER)>=" + filter.getFrom()
                                + " and cast(subscription_date as UNSIGNED INTEGER)<= " + filter.getTo() + ")";
            }
            else {
                finalSql =
                        "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time_ms,b.subscription_end_time_ms,a.subscription_ref_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from user_payment a, users b  where b.username=a.username and b.subscription_state='"
                                + state + "' and (cast(subscription_date as UNSIGNED INTEGER)>=" + filter.getFrom()
                                + " and cast(subscription_date as UNSIGNED INTEGER)<= " + filter.getTo() + ")";
            }
        }
        else {
            if ("all".equals(state)) {
                finalSql = "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time_ms,b.subscription_end_time_ms,a.subscription_ref_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from user_payment a, users b  where b.username=a.username ";
            }
            else {
                finalSql =
                        "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time_ms,b.subscription_end_time_ms,a.subscription_ref_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from user_payment a, users b  where b.username=a.username and b.subscription_state='"
                                + state + "'";
            }
        }
        return finalSql;
    }

    public ApiMessageEnum updatePayment(SubscriptionDto dto, String subscriptionRefId) {
        ApiMessageEnum apiMessageEnum;
        try {
            UserPayment userPaymentEntity = findById(subscriptionRefId);
            if (userPaymentEntity != null) {
                if ("TRUE".equals(userPaymentEntity.getPaymentVerified())) {
                    apiMessageEnum = ApiMessageEnum.PAYMENT_ALREADY_VERIFIED;
                }
                else {
                    setPaymentDetails(dto, userPaymentEntity);
                    saveOrUpdate(userPaymentEntity);
                    apiMessageEnum = ApiMessageEnum.UPDATE_SUBSCRIPTION_SUCCESS;
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

    public String savePayment(String userName, SubscriptionDto dto) {
        String subscriptionRefId = null;
        UserPayment userPaymentEntity = new UserPayment();
        try {
            SQLQuery query1 = commonDao
                    .getNativeQuery("select a.subscription_ref_id,a.username from user_payment a where a.username=?",
                            new Object[] { userName });
            List<Object[]> rows = query1.list();
            if (rows.size() != 0) {
                for (Object[] row : rows) {
                    subscriptionRefId = row[0] != null ? row[0].toString().trim() : "";
                    userPaymentEntity = findById(subscriptionRefId);
                    setPaymentDetails(dto, userPaymentEntity);
                }
            }
            else {
                subscriptionRefId = String.valueOf(UUID.randomUUID());
                userPaymentEntity.setUserName(userName);
                userPaymentEntity.setSubscriptionRefId(subscriptionRefId);
                setPaymentDetails(dto, userPaymentEntity);

            }
            saveOrUpdate(userPaymentEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while saving user subscription details.", e);
        }
        return subscriptionRefId;
    }

    private void setPaymentDetails(SubscriptionDto dto, UserPayment userPaymentEntity) {
        if (dto.getSubscriptionType() != null) {
            userPaymentEntity.setSubscriptionType(dto.getSubscriptionType());
        }
        if (dto.getTransactionRefNumber() != null) {
            userPaymentEntity.setTransactionRefNumber(dto.getTransactionRefNumber());
        }
        if (dto.getTransactionDate() != null) {
            userPaymentEntity.setTransactionDate(String.valueOf(dto.getTransactionDate()));
        }
        if (dto.getSubscriptionType() != null) {
            userPaymentEntity.setTransactionFor(dto.getSubscriptionType());
        }
        if (dto.getPaymentMode() != null) {
            userPaymentEntity.setPaymentMode(dto.getPaymentMode());
        }
        if (dto.getAmountTransferred() != null) {
            userPaymentEntity.setAmoutTransferred(String.valueOf(dto.getAmountTransferred()));
        }
        if (dto.getBankName() != null) {
            userPaymentEntity.setBankName(dto.getBankName());
        }
        if (dto.getBankHolderName() != null) {
            userPaymentEntity.setBankHolderName(dto.getBankHolderName());
        }
        if (dto.getPaymentVerified() != null) {
            userPaymentEntity.setPaymentVerified(dto.getPaymentVerified() ? "TRUE" : "FALSE");
        }
        else {
            userPaymentEntity.setPaymentVerified("FALSE");
        }
    }

    public UserSubscriptionDto findUserSubscription(String userName) {
        try {
            Map<String, Object> condition = new LinkedHashMap<>();
            condition.put("username", userName);
            String[] cols = { "username", "subscription_type", "subscription_state" };
            List<Object[]> user_payment = findByColumnAndCondition(USERS, cols, condition);
            Object existingSubscriptionType = user_payment.get(0)[1];
            Object existingSubscriptionState = user_payment.get(0)[2];
            return new UserSubscriptionDto(existingSubscriptionType.toString(), existingSubscriptionState.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;
    public static final String NA = "NA";

    public Pair<Long, InputStream> downloadSubscriptions() throws RuntimeException {
        String mainQuery = "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time_ms,b.subscription_end_time_ms,a.subscription_ref_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from user_payment a, users b  where b.username=a.username";
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
            fw1.append("SubscriptionStartTimeMs");
            fw1.append(',');
            fw1.append("SubscriptionEndTimeMs");
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
                fw1.append(DateUtils.getCurrentDate(subscriptionDate));
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
                    LOGGER.error("Unable to close FileWriter while downloading subscription data");
                }
            }
            else {
                LOGGER.error("FileWriter found null while downloading subscription data");
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
