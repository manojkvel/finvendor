package com.finvendor.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;

/**
 * Web Utility class
 * 
 * @author ayush
 * @since 07-Jan-2018
 */
public final class WebUtil {

	//Forbidden instantiation
	private WebUtil() {

	}

	public static String getLoggedInUserName(HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		String userName = loggedInUser.getUsername();
		return userName;
	}
}
