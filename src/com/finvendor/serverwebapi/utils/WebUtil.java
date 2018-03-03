package com.finvendor.serverwebapi.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.finvendor.common.util.Pair;
import com.finvendor.common.util.StringUtil;
import com.finvendor.model.BrokerAnalyst;
import com.finvendor.model.Country;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.MarketCapDef;
import com.finvendor.model.ResearchAreaStockClassification;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorResearchReportsResearchDetails;

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

	@SuppressWarnings({ "serial" })
	public static final Map<String, Class<?>> TABLE_NAME_MAP = new HashMap<String, Class<?>>() {{
		put("country", Country.class);
		put("marketcapital", MarketCapDef.class);
		put("style", ResearchAreaStockClassification.class);
		put("analystType", Vendor.class);
		put("researchBroker",FinVendorUser.class);
		// put("brokerYrOfInCorp",BrokerAnalyst.class);
		put("brokerRank", BrokerAnalyst.class);
		put("recommType", VendorResearchReportsResearchDetails.class);
	}};
	
	@SuppressWarnings({ "serial" })
	public static final Map<Class<?>, String[]> TABLE_NAME_COLUMN_MAP = new HashMap<Class<?>, String[]>() {{
		put(Country.class, new String[] { "country_id", "name" });
		put(MarketCapDef.class, new String[] { "mcap_name" });
		put(ResearchAreaStockClassification.class, new String[] { "stockClassificationName" });
		put(Vendor.class, new String[] { "analystType" });
		put(FinVendorUser.class, new String[] { "userName" });
		// put(MarketCapDef.class, new String[]{"mcap_name"});
		put(BrokerAnalyst.class, new String[] { "broker_rank" });
		put(VendorResearchReportsResearchDetails.class, new String[] { "rsrchRecommType" });
	}};
	
	@SuppressWarnings({ "serial" })
	public static final Map<Class<?>, String[]> TABLE_NAME_COLUMN__CONDITION_MAP = new HashMap<Class<?>, String[]>() {{
		put(Country.class, new String[] { "name", "India","UK","USA" });
	}};

	public static String getLoggedInUser(HttpServletRequest request) throws Exception {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "amit_vendor";
			// throw new Exception("Unable to find logged In user!!");
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
