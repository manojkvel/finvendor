package com.finvendor.api.common.controller;

import com.finvendor.api.user.service.UserService;
import com.finvendor.common.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ValidationController {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "checkExistingEmail", method = RequestMethod.POST)
    public String checkExistingEmail(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("param");
        logger.info("Validate existing Email : " + email);
        try {
            if (email != null && !"".equals(email) && userService.getUserDetailsByEmailId(email) != null) {
                response.getWriter().print("Email is already registered !");
            }
        } catch (IOException exp) {
            logger.error("Error checking Email id : " + exp);
        } catch (ApplicationException exp) {
            logger.error("Error checking Email id : " + exp);
            handleExceptionMessage(response, "Error validating Email id");
        }
        return null;
    }

    @RequestMapping(value = "checkExistingUser", method = RequestMethod.POST)
    public String checkExistingUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("param");
        logger.info("Validate existing User : " + username);
        try {
            if (userService.validateUsername(username)) {
                response.getWriter().print("");
            }
        } catch (IOException exp) {
            logger.error("Error checking Username : " + exp);
        } catch (ApplicationException exp) {
            logger.error("Error checking Username : " + exp);
            handleExceptionMessage(response, "Error validating Username");
        }
        return null;
    }

    private void handleExceptionMessage(HttpServletResponse response, String message) {
        try {
            response.getWriter().print(message);
        } catch (IOException exp) {
            logger.error(message + " : " + exp);
        }
    }
}
