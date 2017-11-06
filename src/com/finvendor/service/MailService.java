package com.finvendor.service;

import javax.mail.MessagingException;

public interface MailService {

	public void sendMail(String from,String to,String subject,String message) throws MessagingException;
}
