package com.tamguo.util;

import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractRunningLogHandler implements LogHandler {

	private static Method getStackTraceMethod;
	private static Method getClassNameMethod;
	private static Method getMethodNameMethod;
	private static Method getFileNameMethod;
	private static Method getLineNumberMethod;
	
	static {
		try {
			Class<?>[] noArgs = null;
			getStackTraceMethod = Throwable.class.getMethod("getStackTrace", noArgs);
			Class<?> stackTraceElementClass = Class.forName("java.lang.StackTraceElement");
			getClassNameMethod = stackTraceElementClass.getMethod("getClassName", noArgs);
			getMethodNameMethod = stackTraceElementClass.getMethod("getMethodName", noArgs);
			getFileNameMethod = stackTraceElementClass.getMethod("getFileName", noArgs);
			getLineNumberMethod = stackTraceElementClass.getMethod("getLineNumber", noArgs);
		} catch (ClassNotFoundException ex) {
			LogDebug.debug("will use pre-JDK 1.4 methods to determine location.");
		} catch (NoSuchMethodException ex) {
			LogDebug.debug("will use pre-JDK 1.4 methods to determine location.");
		}
	}
	
	/**
	 * 获得指定class的StackTraceElement
	 * 
	 * @param t
	 * @param fqnOfCallingClass
	 * 
	 * @return
	 */
	protected StackTraceElement getRunningStackTrace(Throwable t, String fqnOfCallingClass) {
		if (getLineNumberMethod != null) {
			try {
				Object[] noArgs = null;
				Object[] elements = (Object[]) getStackTraceMethod.invoke(t, noArgs);
				for (int i = elements.length - 1; i >= 0; i--) {
					String thisClass = (String) getClassNameMethod.invoke(elements[i], noArgs);
					if (fqnOfCallingClass.equals(thisClass)) {
						// 执行class名称
						String className = fqnOfCallingClass;
						// 执行方法名称
						String methodName = (String) getMethodNameMethod.invoke(elements[i], noArgs);
						// 执行class文件名称
						String fileName = (String) getFileNameMethod.invoke(elements[i], noArgs);
						// 执行到行号
						int lineNumber = ((Integer) getLineNumberMethod.invoke(elements[i], noArgs)).intValue();
						return new StackTraceElement(className, methodName, fileName, lineNumber);
					}
				}
			} catch (IllegalAccessException ex) {
				LogDebug.debug("failed using JDK 1.4 methods", ex);
			} catch (InvocationTargetException ex) {
				if (ex.getTargetException() instanceof InterruptedException
						|| ex.getTargetException() instanceof InterruptedIOException) {
					Thread.currentThread().interrupt();
				}
				LogDebug.debug("failed using JDK 1.4 methods", ex);
			} catch (RuntimeException ex) {
				LogDebug.debug("failed using JDK 1.4 methods", ex);
			}
		}
		return this.createDefaultStackTrace();
	}
	
	/**
	 * 创建默认StackTraceElement
	 * 
	 * @return
	 */
	private StackTraceElement createDefaultStackTrace() {
		return new StackTraceElement(this.getClass().getName(), "log", this.getClass().getName(), 0);
	}

	@Override
	public void info(String msg, String fqnOfCallingClass) {
	}

	@Override
	public void info(String msg, Throwable t, String fqnOfCallingClass) {
	}

	@Override
	public void error(String msg, String fqnOfCallingClass) {
	}

	@Override
	public void error(String msg, Throwable t, String fqnOfCallingClass) {
	}

	@Override
	public void debug(String msg, String fqnOfCallingClass) {
	}

	@Override
	public void debug(String msg, Throwable t, String fqnOfCallingClass) {
	}

	@Override
	public void warning(String msg, String fqnOfCallingClass) {
	}

	@Override
	public void warning(String msg, Throwable t, String fqnOfCallingClass) {
	}
	
}
