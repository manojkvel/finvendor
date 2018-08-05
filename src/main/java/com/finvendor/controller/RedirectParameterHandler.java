package com.finvendor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.model.FinVendorUser;
import com.finvendor.util.RequestConstans;

public class RedirectParameterHandler implements AuthenticationSuccessHandler {

	private static Logger logger = Logger.getLogger(RedirectParameterHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String redirectLink = request.getParameter("redirectLink");
		User loggedInUser = (User)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedInUser", loggedInUser);
		
		if (loggedInUser.getAuthorities().contains(new SimpleGrantedAuthority(RequestConstans.Roles.ROLE_ADMIN))) {
			session.setAttribute("loggedInRole", RequestConstans.Roles.ROLE_ADMIN);
		}
		if (loggedInUser.getAuthorities().contains(new SimpleGrantedAuthority(RequestConstans.Roles.ROLE_CONSUMER))) {
			session.setAttribute("loggedInRole", RequestConstans.Roles.ROLE_CONSUMER);
		}
		if (loggedInUser.getAuthorities().contains(new SimpleGrantedAuthority(RequestConstans.Roles.ROLE_VENDOR))) {
			session.setAttribute("loggedInRole", RequestConstans.Roles.ROLE_VENDOR);
		}
		logger.info("onAuthenticationSuccess : redirectLink " + redirectLink);
		if (redirectLink != null && !redirectLink.trim().equals("")) {
			redirectStrategy.sendRedirect(request, response, "/" + redirectLink);
		} else {
			redirectStrategy.sendRedirect(request, response, "/welcometodashboards");
		}
	}
}
