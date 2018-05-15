/**
 * 
 */
package com.finvendor.serviceimpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.finvendor.service.MailService;

/**
 * @author kvel
 *
 */
@Service
public class MailServiceImpl implements MailService {

	private JavaMailSender mailSender;  
	   
    public void setMailSender(JavaMailSender mailSender) {  
        this.mailSender = mailSender;  
      
        
    }  
	
	
	/* (non-Javadoc)
	 * @see com.finvendor.service.MailService#sendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(String from, String to, String subject, String msg) throws MessagingException {
		// TODO Auto-generated method stub
		 // SimpleMailMessage message = new SimpleMailMessage();  
	        
	        
	        
	        MimeMessage mimeMsg = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, false, "utf-8");
	        String htmlMsg = "<h3>Hello World!</h3>";
	        mimeMsg.setContent(msg, "text/html");
	        helper.setFrom(from);  
	        helper.setTo(to);  
	        helper.setSubject(subject);  
	        //helper.setText(msg);
	        //sending message  
	        mailSender.send(mimeMsg); 
	}

}
