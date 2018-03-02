package com.tamguo.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jHandler extends AbstractRunningLogHandler {

	private static final Logger logger = Logger.getLogger(Log4jHandler.class);

	@Override
	public void info(String msg, String fqnOfCallingClass) {
		logger.log(fqnOfCallingClass, Level.INFO, msg, null);
	}

	@Override
	public void info(String msg, Throwable t, String fqnOfCallingClass) {
		logger.log(fqnOfCallingClass, Level.INFO, msg, t);
	}

	@Override
	public void error(String msg, String fqnOfCallingClass) {
		logger.log(fqnOfCallingClass, Level.ERROR, msg, null);
	}

	@Override
	public void error(String msg, Throwable t, String fqnOfCallingClass) {
		logger.log(fqnOfCallingClass, Level.ERROR, msg, t);
	}

}
