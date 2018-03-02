package com.tamguo.util;

public interface LogHandler {
	
	public void info(String msg, String fqnOfCallingClass);
	
	public void info(String msg, Throwable t, String fqnOfCallingClass);
	
	public void error(String msg, String fqnOfCallingClass);

	public void error(String msg, Throwable t, String fqnOfCallingClass);
	
	public void debug(String msg, String fqnOfCallingClass);
	
	public void debug(String msg, Throwable t, String fqnOfCallingClass);
	
	public void warning(String msg, String fqnOfCallingClass);
	
	public void warning(String msg, Throwable t, String fqnOfCallingClass);
	
}
