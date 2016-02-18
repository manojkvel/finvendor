package com.finvendor.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.UserRole;

public class CommonUtils {
	
	private static final Logger logger = Logger.getLogger(CommonUtils.class);
	
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
	
	private static String convertByteToHex(byte data[]){
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));       
        return hexData.toString();
    }

}
