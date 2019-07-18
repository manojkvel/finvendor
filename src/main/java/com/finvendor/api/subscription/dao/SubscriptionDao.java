package com.finvendor.api.subscription.dao;

import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.model.subscription.UserPayment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SubscriptionDao extends GenericDao<UserPayment> {

    public List<UserPayment> findAllPayments() {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while finding user subscription details.", e);
        }
    }

    public boolean updatePayment(SubscriptionDto dto, String subscriptionRefId) {
        boolean updateStatus;
        try {
            UserPayment userPaymentEntity = findById(subscriptionRefId);
            if (userPaymentEntity != null) {
                setPaymentDetails(dto, userPaymentEntity);
                saveOrUpdate(userPaymentEntity);
                updateStatus = true;
            } else {
                updateStatus = false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while update user subscription details.", e);
        }
        return updateStatus;
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
        if (dto.getTransactionId() != null) {
            userPaymentEntity.setTransactionId(dto.getTransactionId());
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
