package com.finvendor.api.subscription.dao;

import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.model.subscription.UserPayment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionDao extends GenericDao<UserPayment> {

    public List<UserPayment> findAllPayments() {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while finding user subscription details.", e);
        }
    }

    public boolean updatePayment(SubscriptionDto dto, String id) {
        boolean updateStatus;
        try {
            UserPayment userPaymentEntity = findById(id);
            if (userPaymentEntity != null) {
                setPaymentDetails(dto, userPaymentEntity);
                saveOrUpdate(userPaymentEntity);
                updateStatus=true;
            }else{
                updateStatus=false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while update user subscription details.", e);
        }
        return updateStatus;
    }

    public void savePayment(String userName, SubscriptionDto dto) {
        try {
            UserPayment userPaymentEntity = new UserPayment();
            userPaymentEntity.setUserName(userName);
            setPaymentDetails(dto, userPaymentEntity);
            save(userPaymentEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while saving user subscription details.", e);
        }
    }

    private void setPaymentDetails(SubscriptionDto dto, UserPayment userPaymentEntity) {
        if (dto.getTransactionId() != null) {
            userPaymentEntity.setTransactionId(dto.getTransactionId());
        }

        if (dto.getTransactionDate() != null) {
            userPaymentEntity.setTransactionDate(String.valueOf(dto.getTransactionDate()));
        }
        if (dto.getSubscriptionType() != null) {
            userPaymentEntity.setTransactionFor(dto.getSubscriptionType().toString());
        }
        if (dto.getPaymentMode() != null) {
            userPaymentEntity.setPaymentMode(dto.getPaymentMode().toString());
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
        if (dto.getTransactionVerified() != null) {
            userPaymentEntity.setPaymentVerified(dto.getTransactionVerified() ? "TRUE" : "FALSE");
        }
    }
}
