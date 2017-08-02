package com.yayiabc.http.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.log.UserLog;
import com.yayiabc.http.mvc.service.SystemControllerLogService;
import com.yayiabc.http.mvc.service.TokenValidateService;

/**
 * 用户操作日志的
 * @author 小月亮
 *
 */
@Aspect
@Component
public class UserLogAspect {
	
	
	//注入service,用于将日志保存进数据库
	@Autowired
	private SystemControllerLogService systemControllerLogService;
	
	
	//controller层切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.UserLog)")
	public void controllerAspect(){
		
	}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("我是日志通知");
		
		System.out.println(joinPoint.getArgs().toString());
		String token=request.getHeader("token");
		String userId =systemControllerLogService.getUserIdByToken(token);
		if(userId!=null){
			String operate=getControllerMethodDescription(joinPoint);
			/*String params = "";  
			if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {  
			   for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
			        params +=joinPoint.getArgs()[i] + ";";  
			   }  
			}*/
			UserLog userLog=new UserLog();
			userLog.setUserId(userId);
			userLog.setOperate(operate);
			/*userLog.setArguments(params);*/
			userLog.setCreated(new Date());
			systemControllerLogService.addLog(userLog);
		}
	}
	
	public  String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {  
		String targetName = joinPoint.getTarget().getClass().getName();  
		String methodName = joinPoint.getSignature().getName();  
		Object[] arguments = joinPoint.getArgs();  
		Class targetClass = Class.forName(targetName);  
		Method[] methods = targetClass.getMethods();  
		String description = "";  
		for (Method method : methods) {  
			
			if (method.getName().equals(methodName)) {  
				Class[] clazzs = method.getParameterTypes();  
				if (clazzs.length == arguments.length) {  
					description = method.getAnnotation(com.yayiabc.common.annotation.UserLog.class).description();  
					break;  
				}  
		    }  
		}  
		return description;  
	}	
}
