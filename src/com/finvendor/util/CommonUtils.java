/**
 * 
 */
package com.finvendor.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.finvendor.exception.ApplicationException;




/**
 * @author rayulu vemula
 *
 */
public class CommonUtils {
	
	private static final Logger logger = Logger.getLogger(CommonUtils.class);
	
	/**
	 * Method to Encrypt given data.
	 * 
	 * @return String
	 */
	public static String encrypt(String str) {

        logger.debug("encrypt method..... ");

		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());

		return new String(encodedBytes);

	}

	/**
	 * ---------------------------------------------------------------------
	 */
	/**
	 * Method to Decrypt given data.
	 * 
	 * @param bytes
	 * @return String
	 */
	public static String decrypt(byte[] bytes) {

      logger.debug("decrypt method..... ");

		byte[] decodedBytes = Base64.decodeBase64(bytes);

		return new String(decodedBytes).replaceAll("%20", " ");

	}
	
	/* ---------------------------------------------------------------------- */
	/**
	 * Method used for getting the decrypted string for given data. 
	 * 
	 * @param data
	 * @return
	 */
	public static String decrypt(String data) {
		
		if (isValidStr(data))
			return decrypt(data.getBytes());
		else 
			return "";
					
	}
	/**
	 * Method used for string is valid.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidStr(String str) {
		if (str == null || str.isEmpty())
			return false;
		else
			return true;
		
	}
	/**
	 * Method used for object is valid.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isValidObj(Object obj) {
		if (obj == null  || obj.toString() == null) 
			return false;
		else
			return true;
	}
	
	/* ---------------------------------------------------------------------- */
	/**
	 * Method used to find the is Number character or not.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {  
	    return (isValidStr(str)? str.matches("\\d+") : false);  
	}
	
	/**
	 * ---------------------------------------------------------------------
	 */
	/**
	 * Method to get FileContentType
	 * 
	 * @param fileType
	 * @return String
	 */
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
	
	private static String convertByteToHex(byte data[]){
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));       
        return hexData.toString();
    }

}
