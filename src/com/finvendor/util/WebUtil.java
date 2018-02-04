package com.finvendor.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

	public static String getSessionUser(HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		String userName = loggedInUser.getUsername();
		return userName;
	}
	
	public static String buildReportPath(String productId, CommonsMultipartFile multiPartFile, String userName,
			String basePath) {
		// Upload filename suffix with productId
		// Reason: loggedIn user can upload multiple file while upload
		Pair<String, String> fileNameAndExtention = StringUtil
				.getFileNameAndExtention(multiPartFile.getOriginalFilename());

		String uploadFileNameOnly = fileNameAndExtention.getElement1() + "_" + productId
				+ fileNameAndExtention.getElement2();
		String reportResearchOfferingFilePath = StringUtil.builtPath(uploadFileNameOnly, basePath, userName);
		return reportResearchOfferingFilePath;
	}
}
