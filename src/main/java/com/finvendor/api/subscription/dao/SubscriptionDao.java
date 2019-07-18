package com.finvendor.api.subscription.dao;

import com.finvendor.api.subscription.dto.PaymentDto;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.dto.UserPaymentDto;
import com.finvendor.api.user.dao.UserDao;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.subscription.UserPayment;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class SubscriptionDao extends GenericDao<UserPayment> {

    @Autowired
    private UserDao userDao;

    public List<UserPaymentDto> findAllPayments() {
        try {
            List<UserPayment> all = findAll();
            List<UserPaymentDto> paymentDtoList = new ArrayList<>();

            for (UserPayment up : all) {
                String subscriptionId = up.getSubscriptionRefId();
                String userName = up.getUserName();
                FinVendorUser existingUser = userDao.getUserDetailsByUsername(userName);
                String subscriptionType = existingUser.getSubscriptionType();
                String subscriptionStartTimeInMillis = "N/A".equals(existingUser.getSubscriptionStartTimeInMillis().trim()) ? "-" : existingUser.getSubscriptionStartTimeInMillis();
                String subscriptionEndTimeInMillis = "N/A".equals(existingUser.getSubscriptionEndTimeInMillis().trim()) ? "-" : existingUser.getSubscriptionEndTimeInMillis();
                String transactionRefNumber = up.getTransactionRefNumber();
                Long transactionDate = Long.parseLong(up.getTransactionDate());
                String paymentMode = up.getPaymentMode();
                Double amountTransferred = Double.parseDouble(up.getAmoutTransferred());
                String bankName = up.getBankName();
                String bankHolderName = up.getBankHolderName();
                String paymentVerified = up.getPaymentVerified();

                UserPaymentDto dto = new UserPaymentDto(subscriptionId, subscriptionType, subscriptionStartTimeInMillis,
                        subscriptionEndTimeInMillis, userName, transactionRefNumber, paymentMode, transactionDate,
                        amountTransferred, bankName, bankHolderName, (paymentVerified != null && up.getPaymentVerified().equals("TRUE")));
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
                } else {
                    setPaymentDetails(dto, userPaymentEntity);
                    saveOrUpdate(userPaymentEntity);
                    apiMessageEnum = ApiMessageEnum.UPDATE_SUBSCRIPTION_SUCCESS;
                }
            } else {
                apiMessageEnum = ApiMessageEnum.SUBSCRIPTION_ID_NOT_EXIST;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while update user subscription details.", e);
        }
        return apiMessageEnum;
    }

    public String savePayment(String userName, SubscriptionDto dto) {
        String subscriptionRefId = String.valueOf(UUID.randomUUID());
        try {
            UserPayment userPaymentEntity = new UserPayment();
            userPaymentEntity.setUserName(userName);
            userPaymentEntity.setSubscriptionRefId(subscriptionRefId);
            setPaymentDetails(dto, userPaymentEntity);
            save(userPaymentEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while saving user subscription details.", e);
        }
        return subscriptionRefId;
    }

    private void setPaymentDetails(SubscriptionDto dto, UserPayment userPaymentEntity) {
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
    }
}
