package com.uaem.mex.login.exception;

/**
 * 
 * @author Abraham-H-V
 *
 */
public class AppLoginException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	public AppLoginException(String message) {
		super(message);
	}
	
	public AppLoginException(String message, Throwable exception) {
		super(message, exception);
	}

}
