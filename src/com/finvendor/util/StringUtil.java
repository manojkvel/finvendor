package com.finvendor.util;

import java.io.File;

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

	public static String builtPath(String fileName, String... folderNames) {
		StringBuilder pathSB = new StringBuilder(100);
		for (String folderName : folderNames) {
			pathSB.append(folderName).append(File.separator);
		}
		pathSB.append(fileName);
		return pathSB.toString();
	}
}
