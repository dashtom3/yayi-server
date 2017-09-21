package com.yayiabc.http.aop;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
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
public class SaleTokenValidateAspect {
	@Autowired
	private TokenValidateService tokenValidateService;
	
	//token验证层controller切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.SaleTokenValidate)")
	public void tokenAspect(){
		
	}
	
	/**
	 * 验证token
	 * @param joinpoint
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String loginToken=request.getHeader("saletoken");
		Object result=null;
		/**
		* 1.验证该用户是否已登录，通过是否包含此token来判断
		*/
		/*SaleToken saleToken=tokenValidateService.getSaleTokenByLoginToken(loginToken);
		if(saleToken!=null){
			if((System.currentTimeMillis()-saleToken.getLoginTime().getTime())<30*60*1000){
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
		}*/
		Integer saleCount=tokenValidateService.getSaleCountByLoginToken(loginToken);
		if(saleCount!=0){
			try {
				result=joinpoint.proceed();//放行
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else {
			DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
			dataWrapper.setErrorCode(ErrorCodeEnum.RE_LOGIN_SALE);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			result=dataWrapper;
		}
		return result;
	}	
}
