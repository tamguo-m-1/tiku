package com.tamguo.util;

public class CException extends RuntimeException {

	private static final long serialVersionUID = 6401592364022805815L;

	public CException() {
		super();
	}

	public CException(String message, Throwable cause) {
		super(message, cause);
	}

	public CException(String message) {
		super(message);
	}

	public CException(Throwable cause) {
		super(cause);
	}
	
}
