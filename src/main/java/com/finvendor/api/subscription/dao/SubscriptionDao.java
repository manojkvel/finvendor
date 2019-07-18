package com.finvendor.api.subscription.dao;

import com.finvendor.api.subscription.dto.PaymentDto;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.dto.UserPaymentDto;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.model.subscription.UserPayment;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class SubscriptionDao extends GenericDao<UserPayment> {

    public List<UserPaymentDto> findAllPayments() {
        try {
            List<UserPayment> all = findAll();
            List<UserPaymentDto> paymentDtoList = new ArrayList<>();

            for (UserPayment up : all) {
                UserPaymentDto dto=new UserPaymentDto(up.getSubscriptionRefId(), up.getTransactionId(),
                        null,up.getPaymentMode(),Long.parseLong(up.getTransactionDate()),Double.parseDouble(up.getAmoutTransferred()),
                        up.getBankName(),up.getBankHolderName(), (up.getPaymentVerified() != null && up.getPaymentVerified().equals("TRUE")));
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
