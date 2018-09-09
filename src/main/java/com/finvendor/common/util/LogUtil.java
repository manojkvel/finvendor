package com.finvendor.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
	private static final Logger logger = LogManager.getLogger(LogUtil.class.getName());

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
