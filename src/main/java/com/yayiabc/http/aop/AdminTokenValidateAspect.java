package com.yayiabc.http.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.TokenValidateService;

@Aspect
@Component
public class AdminTokenValidateAspect {
	@Autowired
	private TokenValidateService tokenValidateService;
	
	//token验证层controller切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.AdminTokenValidate)")
	public void tokenAspect(){
		
	}
	
	/**
	 * 验证token
	 * @param joinpoint
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String loginToken=request.getParameter("adminToken");
		//判断登录表中是否包含此token;
		/*Long timeStamp=Long.valueOf(loginToken.substring(loginToken.length()-13,loginToken.length()));
		Long nowTime=System.currentTimeMillis();*/
		Object result=null;
		/**
		* 1.验证该用户是否已登录，通过是否包含此token来判断
		*/
		Integer adminCount=tokenValidateService.getAdminCountByLoginToken(loginToken);
		if(adminCount!=0){
			try {
				result=joinpoint.proceed();//放行
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
		}else {
			DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			result=dataWrapper;
		}
		return result;
	}	
}
