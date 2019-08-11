package com.finvendor.api.subscription.dao;

import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.dto.UserPaymentDto;
import com.finvendor.api.subscription.dto.UserSubscriptionDto;
import com.finvendor.api.user.dao.UserDao;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.model.subscription.UserPayment;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SubscriptionDao extends GenericDao<UserPayment> {

    public static final String USERS = "users";
    private final UserDao userDao;
    private final ICommonDao commonDao;

    @Autowired
    public SubscriptionDao(ICommonDao commonDao, UserDao userDao) {
        this.commonDao = commonDao;
        this.userDao = userDao;
    }

    public List<UserPaymentDto> findAllPayments() {
        try {
            List<UserPaymentDto> paymentDtoList = new ArrayList<>();
            SQLQuery query1 = commonDao.getNativeQuery(
                    "select b.username,b.subscription_date,b.subscription_type,b.subscription_state, b.subscription_start_time_ms,b.subscription_end_time_ms,a.subscription_ref_id,a.transaction_ref_number,a.transaction_date,a.transaction_for,a.payment_mode,a.amt_transferred,a.bank_name,a.bank_holder_name,a.payment_verified from user_payment a, users b  where b.username=a.username group by cast(a.amt_transferred as decimal) order by a.username",
                    null);
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
            String[] cols = { "username", "subscription_type" };
            List<Object[]> user_payment = findByColumnAndCondition(USERS, cols, condition);
            Object existingSubscriptionType = user_payment.get(0)[1];
            return new UserSubscriptionDto(existingSubscriptionType.toString());
        } catch (Exception e) {
            return null;
        }
    }
}
