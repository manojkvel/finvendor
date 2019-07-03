package com.finvendor.api.fvreport.service.email;

import com.finvendor.api.fvreport.dto.EmailData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService {
    public boolean sendMail(EmailData emailData){
        return false;
    }
}
