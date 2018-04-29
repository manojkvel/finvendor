package com.finvendor.common.util;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

/**
 * String Utility class
 * 
 * @author ayush
 * @since 07-Jan-2018
 */
public final class StringUtil {

	// forbidden instantiation
	private StringUtil() {

	}

	public static final String EMPTY_STRING = "";
	public static final String SLASH = "/";

	public static String builtPath(String fileName, String... folderNames) {
		StringBuilder pathSB = new StringBuilder(100);
		for (String folderName : folderNames) {
			pathSB.append(folderName).append(File.separator);
		}
		pathSB.append(fileName);
		return pathSB.toString();
	}

	public static Pair<String, String> getFileNameAndExtention(String fileNameWithExtension) {
		String fileNameWithoutExtension = FilenameUtils.removeExtension(fileNameWithExtension);
		String extension = fileNameWithExtension.substring(fileNameWithExtension.indexOf("."));
		return new Pair<String, String>(fileNameWithoutExtension, extension);
	}

	// filenamepath:/home/finvendo/amit_vendor/TestPdf1_7fbc6cb9-7ab4-41ba-bd88-83589c941fa2.pdf
	public static final String getFileNameWithoutProductId(String productId, String filenamepath) {
		String fileNameOnly = FilenameUtils.getName(filenamepath);
		String replace = StringUtils.replace(fileNameOnly, "_" + productId, EMPTY_STRING);
		return replace;
	}
	
	public static String toSentenseCase(String str) {
		String firstLetter = str.substring(0, 1).toUpperCase();
		String restLetters = str.substring(1);
		return firstLetter + restLetters;
	}

	public static String replaceString(String baseString, Map<String,String> keywords) {
		String newString=baseString;
		for(Map.Entry<String, String> keywordEntry:keywords.entrySet()) {
			newString = StringUtils.replace(newString, keywordEntry.getKey(), "'" + keywordEntry.getValue() + "'");
		}
		return newString;
	}
}
