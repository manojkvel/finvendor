package com.finvendor.util;

import java.util.List;
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

import com.finvendor.model.Consumer;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.RfpBean;
import com.finvendor.model.Vendor;

public class EmailUtil {

	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	private static Properties mailProp = new Properties();

	static {
		mailProp.put("mail.smtp.auth", "true");
		mailProp.put("mail.smtp.host", "mail.finvendor.com");
	}

	public static final String EMAIL_USERNAME = "sales@finvendor.com";
	public static final String EMAIL_PASSWORD = "FinVendor@dmin";

	public static final String REGISTRATION_LINK = "http://www.finvendor.com/validateRegistrationEmail";
	// public static final String REGISTRATION_LINK =
	// "http://localhost:8080/validateRegistrationEmail";
	public static final String FROM_EMAIL = "support@finvendor.com";
	public static final String SALES_EMAIL = "sales@finvendor.com";

	public static void sendRegistartionEmail(FinVendorUser user, String emailId, String registrationId)
			throws MessagingException {
		logger.debug("Entering EmailUtil:sendRegistartionEmail for {}", emailId);
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
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

	public static void sendNotificationEmail(String notificationType, String notificationMessage, FinVendorUser user,
			String userRoleName) throws MessagingException {
		logger.debug("Entering EmailUtil:sendNotificationEmail for {}", notificationType);
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SALES_EMAIL));
		message.setSubject(notificationType);
		StringBuilder content = new StringBuilder();
		content.append("FinVendor Sales Team, \n");
		content.append("Please note that " + user.getUserName() + " (" + user.getEmail() + ") " + notificationMessage);
		if (userRoleName != null) {
			content.append("\n Account Details :\n");
			content.append("Account Type :" + userRoleName);
		}
		content.append("\nCompany Name :"
				+ ((user.getVendor() != null) ? user.getVendor().getCompany() : user.getConsumer().getCompany()));
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendNotificationEmail for {}", notificationType);
	}

	public static void sendResetPasswordEmail(FinVendorUser user, String password) throws MessagingException {
		logger.debug("Entering EmailUtil:sendResetPasswordEmail for {}", user.getUserName());
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		message.setSubject("FinVendor Reset Password");
		StringBuilder content = new StringBuilder();
		content.append("Dear User, \n");
		content.append("Please find below User ID and new password of your FinVendor Account\n");
		content.append("User ID - " + user.getUserName());
		content.append("\n");
		content.append("Password - " + password);
		content.append("\n\n");
		content.append("Please login to FinVendor(http://www.finvendor.com) and change your password");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendResetPasswordEmail for {}", user.getUserName());
	}

	private static Session getMailSession() {
		Session session = Session.getInstance(mailProp, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
			}
		});
		return session;
	}

	public static void sendMail(String to, String subject, String content) {
		Session session = getMailSession();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SALES_EMAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			 message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendMail() {

		Session session = getMailSession();
		try {
			String to = "sample@gmail.com";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_USERNAME));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject("Testing Subject");
			message.setText("Test mail," + "\n\n Mochahost registration");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendRfpNotification(Consumer consumer, RfpBean rfpBean, List<String> vendorEmailList,
			boolean closed) throws MessagingException {
		logger.debug("Entering EmailUtil:sendRfpNotification for {}", consumer.getUser().getUserName());
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		StringBuilder vendorEmails = new StringBuilder();
		for (String vendorEmail : vendorEmailList) {
			vendorEmails.append(vendorEmail);
			vendorEmails.append(",");
		}
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(vendorEmails.substring(0, vendorEmails.length() - 1)));
		if (closed) {
			message.setSubject("RFP Closed Notification");
		} else {
			message.setSubject("New RFP Notification");
		}
		StringBuilder content = new StringBuilder();
		content.append("Dear User, \n");
		if (closed) {
			content.append("Please note that RFP " + rfpBean.getRfpTitle() + " has been closed by "
					+ consumer.getCompanyUrl() + "\n");
		} else {
			content.append("Please note that a new RFP " + rfpBean.getRfpTitle() + " has been created by "
					+ consumer.getCompanyUrl() + "\n");
		}
		content.append("\n\n");
		content.append("Please login to FinVendor(http://www.finvendor.com) for more details");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendRfpNotification for {}", consumer.getUser().getUserName());
	}

	public static void sendRfpInterestNotification(RfpBean rfpBean, Vendor vendor, Consumer consumer, boolean revoke)
			throws MessagingException {
		logger.debug("Entering EmailUtil:sendRfpInterestNotification for {}", vendor.getUser().getUserName());
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(consumer.getUser().getEmail()));
		if (revoke) {
			message.setSubject("Vendor RFP Interest Notification");
		} else {
			message.setSubject("Vendor RFP Interest Revoke Notification");
		}
		StringBuilder content = new StringBuilder();
		content.append("Dear User, \n");
		if (revoke) {
			content.append("Please note that Vendor " + vendor.getCompany() + " has revoked interest from RFP "
					+ rfpBean.getRfpTitle() + "\n");
		} else {
			content.append("Please note that Vendor " + vendor.getCompany() + " has expressed interest in "
					+ rfpBean.getRfpTitle() + "\n");
		}
		content.append("\n\n");
		content.append("Please login to FinVendor(http://www.finvendor.com) for more details");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendRfpInterestNotification for {}", vendor.getUser().getUserName());
	}

	public static void sendRfpVendorSelectionNotification(Consumer consumer, RfpBean rfpBean, List<Vendor> vendorList,
			boolean finalized) throws MessagingException {
		logger.debug("Entering EmailUtil:sendRfpVendorSelectionNotification for {}", consumer.getUser().getUserName());
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		StringBuilder vendorEmails = new StringBuilder();
		for (Vendor v : vendorList) {
			vendorEmails.append(v.getUser().getEmail());
			if (v.getSecondaryEmail() != null && !v.getSecondaryEmail().trim().equals("")) {
				vendorEmails.append(v.getSecondaryEmail());
			}
			vendorEmails.append(",");
		}
		message.setRecipients(Message.RecipientType.BCC,
				InternetAddress.parse(vendorEmails.substring(0, vendorEmails.length() - 1)));
		if (!finalized) {
			message.setSubject("RFP Vendor Shortlisting Notification");
		} else {
			message.setSubject("RFP Vendor Finalize Notification");
		}
		StringBuilder content = new StringBuilder();
		content.append("Dear User, \n");
		if (!finalized) {
			content.append("Please note that " + consumer.getCompanyUrl() + " has shortlisted you for RFP "
					+ rfpBean.getRfpTitle() + "\n");
		} else {
			content.append("Please note that " + consumer.getCompanyUrl() + " has finalized you for RFP "
					+ rfpBean.getRfpTitle() + "\n");
		}
		content.append("\n\n");
		content.append("Please login to FinVendor(http://www.finvendor.com) for more details");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendRfpVendorSelectionNotification for {}", consumer.getUser().getUserName());
	}

	public static void sendRfpMoreInfoNotification(RfpBean rfpBean, Vendor vendor, Consumer consumer, String moreInfo,
			boolean request) throws MessagingException {
		logger.debug("Entering EmailUtil:sendRfpMoreInfoNotification for {}, Consumer : {}", rfpBean.getRfpTitle(),
				consumer.getCompany());
		Session session = getMailSession();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_EMAIL));
		if (request) {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(consumer.getUser().getEmail()));
		} else {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(vendor.getUser().getEmail()));
		}
		if (request) {
			message.setSubject("Vendor RFP More Information Request Notification");
		} else {
			message.setSubject("Consumer RFP More Information Update Notification");
		}
		StringBuilder content = new StringBuilder();
		content.append("Dear User, \n");
		if (request) {
			content.append("Please note that Vendor " + vendor.getCompany() + " has requested more information for RFP "
					+ rfpBean.getRfpTitle() + "\n");
			content.append("Information requested : " + moreInfo);
		} else {
			content.append("Please note that Consumer " + consumer.getCompany()
					+ " has provided more infromation for RFP " + rfpBean.getRfpTitle() + "\n");
			content.append("Information Provided : " + moreInfo);
		}
		content.append("\n\n");
		content.append("Please login to FinVendor(http://www.finvendor.com) for more details");
		message.setText(content.toString());
		Transport.send(message);
		logger.debug("Leaving EmailUtil:sendRfpMoreInfoNotification for {}, Consumer : {}", rfpBean.getRfpTitle(),
				consumer.getCompany());
	}
}
