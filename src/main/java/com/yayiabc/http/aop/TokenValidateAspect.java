package com.yayiabc.http.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.TokenValidateService;

@Aspect
@Component
public class TokenValidateAspect {
	
	@Autowired
	private TokenValidateService tokenValidateService;
	
	//token验证层controller切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.TokenValidate)")
	public void tokenAspect(){
		
	}
	
	/**
	 * 验证token
	 * @param joinpoint
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String loginToken=request.getParameter("token");
		//判断登录表中是否包含此token;
		/*Long timeStamp=Long.valueOf(loginToken.substring(loginToken.length()-13,loginToken.length()));
		Long nowTime=System.currentTimeMillis();*/
		Object result=null;
		/**
		* 1.验证该用户是否已登录，通过是否包含此token来判断
		*/
		if(loginToken.indexOf("user")!=-1){
			/*Integer count=tokenValidateService.getUserCountByLoginToken(loginToken);
			if(count!=0){
				Integer state=tokenValidateService.getUserStateByLoginToken(loginToken);
				if(state==2){
				Long timeStamp=Long.valueOf(loginToken.substring(loginToken.length()-13,loginToken.length()));
				Long nowTime=System.currentTimeMillis();
					if((nowTime-timeStamp)<30*60*1000){
						try {
							Object object=joinpoint.proceed();//放行
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}else{
						tokenValidateService.updateUserLoginState(loginToken);//修改登录状态为未登录
					}
				}
			}*/
			try {
				result=joinpoint.proceed();//放行
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else if(loginToken.indexOf("sale")!=-1){
			/*Integer count=tokenValidateService.getSaleCountByLoginToken(loginToken);
			if(count!=0){
				Integer state=tokenValidateService.getSaleStateByLoginToken(loginToken);
				if(state==2){
				Long timeStamp=Long.valueOf(loginToken.substring(loginToken.length()-13,loginToken.length()));
				Long nowTime=System.currentTimeMillis();
					if((nowTime-timeStamp)<30*60*1000){
						try {
							Object object=joinpoint.proceed();//放行
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}else{
						tokenValidateService.updateSaleState(loginToken);//修改登录状态为未登录
					}
				}
			}*/
			try {
				result=joinpoint.proceed();//放行
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else if(loginToken.indexOf("admin")!=-1){
			/*Integer count=tokenValidateService.getAdminCountByLoginToken(loginToken);
			if(count!=0){
				try {
					Object object=joinpoint.proceed();//放行
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}*/
			try {
				result=joinpoint.proceed();//放行
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return result;
	}	
}
