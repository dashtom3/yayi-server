package com.yayiabc.common.exceptionHandler;

import com.yayiabc.common.enums.ErrorCodeEnum;

public class AuthException extends RuntimeException {

	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

	public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCodeEnum errorCodeEnum;
	public  AuthException(ErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}

}
