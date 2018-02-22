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
		return ExceptionUtils.getRootCause(th).getMessage();
	}
}
