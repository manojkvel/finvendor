package com.finvendor.api.subscription.service;

import com.finvendor.api.subscription.dao.SubscriptionDao;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.model.subscription.UserPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriptionService {

    @Autowired
    private SubscriptionDao dao;

    public void savePayment(String userName, SubscriptionDto dto) throws Exception {
        try {
            dao.savePayment(userName, dto);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public boolean updatePayment(SubscriptionDto dto, String id) throws Exception {
        try {
            return dao.updatePayment(dto,id);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<UserPayment> findSubscriptions() throws Exception {
        try {
           return dao.findAllPayments();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
