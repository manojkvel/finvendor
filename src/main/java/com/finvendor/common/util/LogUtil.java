package com.finvendor.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class.getName());

	public static void logInfo(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	public static void logError(String message) {
		logger.error(message);
	}

	public static void logWarn(String message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message);
		}
	}
}
