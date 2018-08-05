package com.finvendor.common.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author ayush on 9-May-2018
 */
public class LocaleUtil {

	private static Map<String, String> countryNameCodeMapping = new HashMap<String, String>() {
		{
			put("IN", "1");
			put("US", "2");
			put("GB", "3"); // GB is for UK
		}
	};

	// TBD - Ayush
	public static String getCurrentGeo() {
//		String[] locales = Locale.getISOCountries();
//		for (String countryCode : locales) {
//			Locale obj = new Locale("", countryCode);
//			String cCode = obj.getCountry();
//			if (cCode.equals("IN")) {
//				return countryNameCodeMapping.get(cCode);
//			}
//		}
		return "1";
	}

	public static void main(String[] args) {
		// Getting a default locale object
		Locale locale = Locale.getDefault();
//		System.out.println("Default Locale: " + locale);
//		System.out.println("Default Locale: " + locale.getCountry());
		LocaleUtil.getCurrentGeo();

	}
}
