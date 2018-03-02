package com.tamguo.util;

/**
 * 统一异常处理 日志处理
 *
 */
public class ExceptionSupport {
	
	private final static Result failResult = Result.failResult("500");
	private static LogHandler handler = new Log4jHandler();
	
	private final static String LOG_INFO_PREFIX = "--info>> ";
	private final static String LOG_ERROR_PREFIX = "--error>> ";
	
	public static Result resolverResult(String methodInfo, Class<?> clazz, Exception e) {
		if(e instanceof CException) {
			handler.info(formatInfoLevelMsg(methodInfo, e.getMessage()), clazz.getName());
			return Result.failResult(e.getMessage());
		}
		handler.error(formatErrorLevelMsg(methodInfo), e, clazz.getName());
		return failResult;
	}
	
	private static String formatInfoLevelMsg(String methodInfo, String infoMsg) {
		return LOG_INFO_PREFIX + methodInfo + ": " + infoMsg;
	}
	
	private static String formatErrorLevelMsg(String methodInfo) {
		return LOG_ERROR_PREFIX + methodInfo;
	}
	
}
