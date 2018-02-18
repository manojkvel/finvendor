package com.finvendor.serverwebapi.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.finvendor.common.util.Pair;
import com.finvendor.common.util.StringUtil;

/**
 * Web Utility class
 * 
 * @author ayush
 * @since 07-Jan-2018
 */
public final class WebUtil {

	// Forbidden instantiation
	private WebUtil() {

	}

	public static String getLoggedInUser(HttpServletRequest request) throws Exception {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "amit_vendor";
			//throw new Exception("Unable to find logged In user!!");
		}
		return loggedInUser.getUsername();
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
