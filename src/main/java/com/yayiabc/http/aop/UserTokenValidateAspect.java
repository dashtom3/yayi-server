package com.yayiabc.http.aop;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.AuthException;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.TokenValidateService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class UserTokenValidateAspect {
	@Autowired
	private TokenValidateService tokenValidateService;
	
	//token验证层controller切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.UserTokenValidate)")
	public void tokenAspect(){
		
	}
	
	/**
	 * 验证token
	 * @param joinpoint
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String loginToken=request.getHeader("token");
		Object result=null;
		/**
		* 1.验证该用户是否已登录，通过是否包含此token来判断
		*/
		UserToken userToken =tokenValidateService.getUserTokenByLoginToken(loginToken);
		if(userToken!=null){
			if((System.currentTimeMillis()-userToken.getLoginDate().getTime())>30*60*1000){
				try {
					result=joinpoint.proceed();//放行
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}else{
				throw new AuthException(ErrorCodeEnum.RE_LOGIN);
			}
		}else{
			throw new AuthException(ErrorCodeEnum.RE_LOGIN);
		}
		return result;
	}	
}
