package com.finvendor.common.util;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.finvendor.common.exception.FvTechnicalException;

/**
 * @author ayush
 */
public final class ExceptionUtil {

	private ExceptionUtil() throws FvTechnicalException {
		throw new FvTechnicalException("ExceptionUtil class instantiation via Reflection is forbidden!");
	}

	public static final String getRootCause(Throwable th) {
 		Throwable rootCause = ExceptionUtils.getRootCause(th);
		if (rootCause != null) {
			return rootCause.getMessage();
		} else {
			return th.getMessage();
		}
	}
	
	public static  String buildErrorMessage(String contextMsg, Exception e) {
		String errorStackTrace=org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e);
		StringBuffer errOutputSb=new StringBuffer(500);
		errOutputSb.append(System.lineSeparator());
		errOutputSb.append("User Error - ").append(contextMsg);
		errOutputSb.append(System.lineSeparator());
		errOutputSb.append("Error stack trace -> ");
		errOutputSb.append(System.lineSeparator());
		errOutputSb.append(errorStackTrace);
		errOutputSb.append(System.lineSeparator());
		errOutputSb.append("Please contact Finvendor Admin for support !!");
		errOutputSb.append(System.lineSeparator());
		String apiErrorMessage=errOutputSb.toString();
		return apiErrorMessage;
	}
}
