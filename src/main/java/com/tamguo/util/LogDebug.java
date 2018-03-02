package com.tamguo.util;

public class LogDebug {

	private final static String DEBUG_LOG_KEY = "-- LogHandler: ";

	public static void debug(String msg) {
		System.err.println(DEBUG_LOG_KEY + msg);
	}

	public static void debug(String msg, Throwable t) {
		System.err.println(DEBUG_LOG_KEY + msg);
		if (t != null)
			t.printStackTrace(System.err);
	}

}
