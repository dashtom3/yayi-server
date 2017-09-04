package com.yayiabc.common.exceptionHandler;

import com.yayiabc.common.enums.ErrorCodeEnum;

public class OrderException extends RuntimeException {

	/**
	 * 自定义异常 <code>time</code>
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCodeEnum errorCodeEnum;
	public  OrderException(ErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}
	
	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

	public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}

}
