package com.yayiabc.common.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;




@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(OrderException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(HttpServletRequest request,OrderException ex) {  
		ex.printStackTrace();
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		dataWrapper.setData(ex.getMessage());
        return dataWrapper;
    }

	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(RuntimeException ex) {
		ex.printStackTrace();
		
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		dataWrapper.setData(ex.getMessage());
        return dataWrapper;
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(AuthException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(AuthException ex) {  
		return getReturns1(ex.getErrorCodeEnum());
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DBException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(DBException ex) {  
		ex.printStackTrace();
		
		return getReturns(ErrorCodeEnum.Error,ex.getMessage());
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ParameterException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(ParameterException ex) {  
		
		return getReturns(ErrorCodeEnum.Error,ex.getMessage());
    }
	public DataWrapper<String> getReturns1(ErrorCodeEnum errorCodeEnum) {
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setErrorCode(errorCodeEnum);
		dataWrapper.setData(errorCodeEnum.getLabel());
		return dataWrapper;
	}
	public DataWrapper<String> getReturns(ErrorCodeEnum errorCodeEnum, String message) {
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setErrorCode(errorCodeEnum);
		dataWrapper.setData(message);
        return dataWrapper;
	}

}
