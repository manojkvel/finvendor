
package com.finvendor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RedirectParameterHandler implements AuthenticationSuccessHandler{
	
	private static Logger logger = Logger.getLogger(RedirectParameterHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String redirectLink = request.getParameter("redirectLink");
        User loggedInUser = (User)authentication.getPrincipal();
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedInUser", loggedInUser);
        logger.info("onAuthenticationSuccess : redirectLink " + redirectLink);
        if(redirectLink != null && !redirectLink.trim().equals("")){
        	redirectStrategy.sendRedirect(request, response, "/" + redirectLink);
        }else{
        	redirectStrategy.sendRedirect(request, response, "/welcometodashboards");
        }
    }
}
=======
package com.finvendor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RedirectParameterHandler implements AuthenticationSuccessHandler{
	
	private static Logger logger = Logger.getLogger(RedirectParameterHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String redirectLink = request.getParameter("redirectLink");
        User loggedInUser = (User)authentication.getPrincipal();
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedInUser", loggedInUser);
        logger.info("onAuthenticationSuccess : redirectLink " + redirectLink);
        if(redirectLink != null && !redirectLink.trim().equals("")){
        	redirectStrategy.sendRedirect(request, response, "/" + redirectLink);
        }else{
        	redirectStrategy.sendRedirect(request, response, "/welcometodashboards");
        }
    }
}

