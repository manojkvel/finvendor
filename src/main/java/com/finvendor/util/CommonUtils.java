package com.finvendor.util;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.Consumer;
import com.finvendor.model.UserRole;
import com.finvendor.model.Vendor;
import com.finvendor.service.ConsumerService;
import com.finvendor.service.VendorService;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtils {

	/**logger*/
	private static final Logger logger = LogManager.getLogger(CommonUtils.class.getName());

	public static String encrypt(String str) {
        logger.debug("CommonUtils : encrypt");
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		return new String(encodedBytes);
	}

	public static String decrypt(byte[] bytes) {
      logger.debug("CommonUtils : decrypt");
		byte[] decodedBytes = Base64.decodeBase64(bytes);
		return new String(decodedBytes).replaceAll("%20", " ");
	}
	
	public static String decrypt(String data) {		
		if (isValidStr(data))
			return decrypt(data.getBytes());
		else 
			return "";					
	}
	
	public static boolean isValidStr(String str) {
		if (str == null || str.isEmpty())
			return false;
		else
			return true;		
	}
	
	public static boolean isValidObj(Object obj) {
		if (obj == null  || obj.toString() == null) 
			return false;
		else
			return true;
	}
	
	public static boolean isNumeric(String str) {  
	    return (isValidStr(str)? str.matches("\\d+") : false);  
	}
	
	public static String getFileContentType(String fileType) {
		String contentType = "";
		if (fileType.equalsIgnoreCase("doc")
				|| fileType.equalsIgnoreCase("docx")) {
			contentType = "application/msword";
		} else if (fileType.equalsIgnoreCase("jpeg")
				|| fileType.equalsIgnoreCase("jpg")
				|| fileType.equalsIgnoreCase("png")
				|| fileType.equalsIgnoreCase("bmp")
				|| fileType.equalsIgnoreCase("tiff")) {
			contentType = "images/" + fileType;
		} else if (fileType.equalsIgnoreCase("pdf")) {
			contentType = "application/" + fileType;
		} else if (fileType.equalsIgnoreCase("xls")
				|| fileType.equalsIgnoreCase("xlsx")
				|| fileType.equalsIgnoreCase("xlsm")) {
			contentType = "application/vnd.ms-excel";
		} else if (fileType.equalsIgnoreCase("csv")) {
			contentType = "application/file.csv";
		}else if (fileType.equalsIgnoreCase("txt")
				|| fileType.equalsIgnoreCase("log")) {
			contentType = "text/plain";
		} else if (fileType.equalsIgnoreCase("zip") 
			|| fileType.equalsIgnoreCase("rar")) {
			contentType = "application/x-rar-compressed";
		}
		return contentType;

	}
	
	public static String resolveContextPath(String contextPath) {	
		if ((contextPath) == null || "".equals(contextPath))
			return "/";
		else 
			return contextPath;					
	}
	
	public static String replaceCharacter(String originalString, String oldChar, String newChar) {
		if (originalString != null) {
			return originalString.replaceAll(oldChar, newChar);
		}else {
			return originalString;
		} 
	}
	
	public static String rectifyCompanyUrl(String companyUrl) {
		if (companyUrl != null && (!companyUrl.startsWith("http:\\") || !companyUrl.startsWith("http://"))) {			
			return "http:\\\\" + companyUrl;
		}else {
			return companyUrl;
		}
	}
	
	public static String encodeUsingSHA512(String source) throws ApplicationException{
		MessageDigest sha512 = null;
		try {
			sha512 = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new ApplicationException("Error encoding source");
		}
		sha512.update(source.getBytes());
		return convertByteToHex(sha512.digest());		
	}
	
	public static String getRole(@SuppressWarnings("rawtypes") Set userRoles) {
		logger.debug("CommonUtils : getRole");
		StringBuilder roles = new StringBuilder(8);
		for (Object roleObject : userRoles) {
			UserRole role = (UserRole)roleObject;
			roles.append(role.getRoles().getRoleName().substring(5));
			roles.append(",");
		}
		if (roles.length() > 0) {
			return roles.substring(0, roles.length() -1);
		}else {
			return "";
		}
	}
	
	public static void populateConsumerProfileRequest(Consumer consumer, ConsumerService consumerService, 
			ModelAndView modelAndView) throws ApplicationException {
		String telephone = consumer.getTelephone();
   		if(telephone != null && !telephone.trim().equals("")) {
   			String[] phoneWithCode = telephone.split("-");
   			modelAndView.addObject("telephoneCode", phoneWithCode[0]);
   			modelAndView.addObject("telephoneNumber", phoneWithCode[1]);
   		}
   		String companyType = consumer.getCompanyType();
   		StringBuilder displayCompanyType = new StringBuilder();
   		boolean buy = false;
   		if(companyType != null) {
       		if(companyType.indexOf("Buy") > 0) {
       			displayCompanyType.append("Buy Side");
       			buy = true;
       		}
       		if(companyType.indexOf("Sell") > 0) {
       			if(buy) {
       				displayCompanyType.append(", Sell Side");
       			}else {
       				displayCompanyType.append("Sell Side");
       			}
       		}
       		String[] consumerCompnayType = companyType.split(",");
       		for (String company : consumerCompnayType) {
    			switch (company) {
    				case RequestConstans.Consumer.FIN_CONSUMER_COMPANY_SELL_SIDE : {
    					modelAndView.addObject("finConsumerCompanySellSide", company);
    					break;
    				}
    				case RequestConstans.Consumer.FIN_CONSUMER_COMPANY_BUY_SIDE : {
    					modelAndView.addObject("finConsumerCompanyBuySide", company);
    					break;
    				}
    				case RequestConstans.Consumer.FIN_CONSUMER_COMPANY_OTHERS : {
    					modelAndView.addObject("finConsumerCompanyOther", company);
    					break;
    				}
    				case RequestConstans.Consumer.CONSUMER_UNIVERSITY : {
    					modelAndView.addObject("consumerCompanyUniversity", company);
    					break;
    				}
    				case RequestConstans.Consumer.CONSUMER_OTHER_FIRM : {
    					modelAndView.addObject("consumerCompanyOtherFirm", company);
    					break;
    				}
    			}
    		} 
   		}   		
   		consumer.setVendorPreference();
   		modelAndView.addObject("displayCompanyType", displayCompanyType.toString());
   		List<Object[]> consumerMyProfileMyBusinessNeedsMarketData = 
   				consumerService.loadConsumerMyProfile(consumer.getId(), 
   						"consumerProfileMyBusinessNeedsMarketDataTable");
   		logger.debug("consumerMyProfileMyBusinessNeedsMarketData size for {} is {}", 
   				consumer.getId(), consumerMyProfileMyBusinessNeedsMarketData.size());
   		modelAndView.addObject("consumerMyProfileMyBusinessNeedsMarketData", 
   				consumerMyProfileMyBusinessNeedsMarketData);
	}
	
	public static void populateVendorProfileRequest(Vendor vendor, VendorService vendorService, 
			ModelAndView modelAndView) {
		String vendorCompanyTypes = vendor.getCompanyType();
		logger.debug("Company type for Vendor {} are {}", vendor.getUser().getUserName(), vendorCompanyTypes);
		String[] vendorOfferings = vendorCompanyTypes.split(",");
		for (String vendorOfferingType : vendorOfferings) {
			switch (vendorOfferingType) {
				case RequestConstans.Vendor.DATA_AGGREGATOR : {
					modelAndView.addObject("dataaggregator", vendorOfferingType);
					break;
				}
				case RequestConstans.Vendor.TRADING_APPLICATION : {
					modelAndView.addObject("tradingapplication", vendorOfferingType);
					break;
				}
				case RequestConstans.Vendor.ANALYTICS_APPLICATION : {
					modelAndView.addObject("analyticsapplication", vendorOfferingType);
					break;
				}
				case RequestConstans.Vendor.RESEARCH_REPORT : {
					modelAndView.addObject("researchreport", vendorOfferingType);
					break;
				}
			}
		} 
	}
	
	private static String convertByteToHex(byte data[]){
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));       
        return hexData.toString();
    }
	
	public static int getCount(String mapKey, Map<String, Set<String>> valueMap) {
		Set<String> mapValue = valueMap.get(mapKey);
		if (mapValue == null) {
			return 0;
		}
		return mapValue.size();
	}

}
