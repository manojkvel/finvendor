package com.finvendor.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finvendor.model.FinVendorUser;

public class EmailUtil {
	
	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	private static Properties mailProp = new Properties();
	static{
		mailProp.put("mail.smtp.auth", "true");
		mailProp.put("mail.smtp.host", "mail.finvendor.com");
		mailProp.put("mail.smtp.port", "2525");
	}
	public static final String EMAIL_USERNAME = "finvendo";
	public static final String EMAIL_PASSWORD = "amit_mv";
	public static final String REGISTRATION_LINK = "http://www.finvendor.com/validateRegistrationEmail";
	//public static final String REGISTRATION_LINK = "http://localhost:8080/validateRegistrationEmail";
	public static final String FROM_EMAIL = "support@finvendor.com";
	
	public static void main(){
		EmailUtil em = new EmailUtil();
		em.sendMail();
	}
	
	public static void sendRegistartionEmail(FinVendorUser user, String emailId, String registrationId) throws MessagingException {
		logger.debug("Entering EmailUtil:sendRegistartionEmail for {}", emailId);
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(emailId));
		message.setSubject("FinVendor Registration verification");
		StringBuilder content = new StringBuilder();
		content.append("Dear ");
		content.append(user.getUserName());
		content.append(", \n");
		content.append("Please click on below link to activate your FinVendor Account\n");
		content.append(REGISTRATION_LINK);
		content.append("?param=");
		content.append(registrationId);
		content.append("@");
		content.append(user.getUserName());
		content.append("\n\n");
		content.append("Please note that link will expire in " + RequestConstans.REGISTRATION_LINK_EXPIRY + " Hours");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendRegistartionEmail for {}", emailId);
	}
	
	public static void sendResetPasswordEmail(FinVendorUser user, String password) throws MessagingException {
		logger.debug("Entering EmailUtil:sendResetPasswordEmail for {}", user.getUserName());
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(user.getEmail()));
		message.setSubject("FinVendor Reset Password");
		StringBuilder content = new StringBuilder();
		content.append("Dear ");
		content.append(user.getUserName());
		content.append(", \n");
		content.append("Please find below new password for your FinVendor Account\n");
		content.append(password);
		content.append("\n\n");
		content.append("Please login to FinVendor and change your password");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendResetPasswordEmail for {}", user.getUserName());
	}
	
	private static Session getMailSession(){
		Session session = Session.getInstance(mailProp,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
			}
		  });
		return session;
	}

	public static void sendMail(){
		
		Session session = getMailSession();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sales@finvendor.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(""));
			message.setSubject("Testing Subject");
			message.setText("Test mail,"
				+ "\n\n Mochahost registration");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
