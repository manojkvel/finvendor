/**
 *
 */
package com.finvendor.controller;

import com.finvendor.service.MailService;
import com.finvendor.util.EmailUtil;
import com.finvendor.util.RequestConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author kvel
 */
@Controller
public class MailController {


    @Autowired
    private MailService mailService;

    /**
     * method to send mails
     *
     * @return modelAndView
     * @throws Exception the exception
     * @RequestParam(value = "contact_us_name", required = false) String name,
     * @RequestParam(value = "contact_us_phone", required = false) String phone,
     * @RequestParam(value = "contact_us_email", required = false) String email,
     * @RequestParam(value = "contact_us_message", required = false) String message
     */
    @RequestMapping(value = RequestConstans.MAIL.MAIL_SEND, method = RequestMethod.POST)
    public @ResponseBody
    String sendMail(@RequestBody String message

    ) {
        try {
            message = UriUtils.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] strand = message.split("&");

        String name = "", email = "", phone = "", msg = "";
        for (String str : strand) {
            System.out.println(str);
            String[] substr = str.split("=");

            if (substr[0].equals("contact_us_name")) name = substr[1];
            else if (substr[0].equals("contact_us_phone")) phone = substr[1];
            else if (substr[0].equals("contact_us_email")) email = substr[1];
            if (substr[0].equals("contact_us_message")) msg = substr[1];
        }

        String htmlMsg = "<h3> Enquiry from " + name + "( " + email + " )" + "</h3> <br> Name: " + name + "<br> Phone: " + phone + "<br> Email: " + email + "<br><br><br> Message: " + msg +
                "<br><br><h3>This is an Automated Mail from www.finvendor.com</h3>";
        EmailUtil.sendMail(email, "Enquiry - " + name + " - " + email, htmlMsg);
        return "Your Mail has been sent. Thank you for taking interest in our services.";
    }
}
