package com.finvendor.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finvendor.exception.ApplicationException;
import com.finvendor.service.UserService;

@Controller
public class ValidationController {
	
	private static Logger logger = Logger.getLogger(ValidationController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="checkExistingEmail", method=RequestMethod.POST)
	public String checkExistingEmail(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("param");
		logger.info("Validate existing Email : " + email);		
		try{
			if(userService.getUserDetailsByEmailId(email) != null){
				response.getWriter().print("Email is already registered !");
			}
		}catch (IOException exp) {
			logger.info("Error checking Email id : " + exp);
		}catch (ApplicationException exp) {
			logger.info("Error checking Email id : " + exp);
			handleExceptionMessage(response, "Error validating Email id");
		}
		return null;
	}
	
	@RequestMapping(value="checkExistingUser", method=RequestMethod.POST)
	public String checkExistingUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("param");
		logger.info("Validate existing User : " + username);		
		try{
			if(userService.validateUsername(username)){
				response.getWriter().print("Username is already registered !");
			}
		}catch (IOException exp) {
			logger.info("Error checking Username : " + exp);
		}catch (ApplicationException exp) {
			logger.info("Error checking Username : " + exp);
			handleExceptionMessage(response, "Error validating Username");
		}
		return null;
	}
	
	private void handleExceptionMessage(HttpServletResponse response, String message) {
		try{
			response.getWriter().print(message);
		}catch (IOException exp) {
			logger.error(message + " : " + exp);			
		}
	}
}
