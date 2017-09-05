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
      
	    //业务类型  
	    private String bizType;  
	    //业务代码  
	    private int bizCode;  
	    //错误信息  
	    private String message;  
	      
	  
	  
	    public String getBizType() {  
	        return bizType;  
	    }  
	  
	    public void setBizType(String bizType) {  
	        this.bizType = bizType;  
	    }  
	  
	    public int getBizCode() {  
	        return bizCode;  
	    }  
	  
	    public void setBizCode(int bizCode) {  
	        this.bizCode = bizCode;  
	    }  
	  
	    public String getMessage() {  
	        return message;  
	    }  
	  
	    public void setMessage(String message) {  
	        this.message = message;  
	    }  
}
