package com.yayiabc.common.exceptionHandler;

public class DBException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7527401587858967187L;

	public  DBException(String message,Exception e) {
		super(message,e);
	}

}
