package com.yayiabc.http.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.service.SystemControllerLogService;

/**
 * 用户操作日志的
 * @author 小月亮
 *
 */
@Aspect
@Component
public class AdminstratorLogAspect {
	
	
	//注入service,用于将日志保存进数据库
	@Autowired
	private SystemControllerLogService systemControllerLogService;
	
	
	//controller层切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.AdminLog)")
	public void controllerAspect(){
		
	}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("我是日志通知");
		
		System.out.println(joinPoint.getArgs().toString());
		String adminstratorToken=request.getHeader("admintoken");
		String adminstratorId =systemControllerLogService.getAdminstratorIdByAdminstratorToken(adminstratorToken);
		if(adminstratorId!=null){
			String operate=getControllerMethodDescription(joinPoint);
			String params = "";  
			if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {  
			   for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
			        params +=joinPoint.getArgs()[i] + ";";  
			   }  
			}
			AdminstratorLog adminstratorLog=new AdminstratorLog();
			adminstratorLog.setAdminstratorId(adminstratorId);
			adminstratorLog.setOperate(operate);
			adminstratorLog.setArguments(params);
			adminstratorLog.setCreated(new Date());
			systemControllerLogService.addAdminstratorLog(adminstratorLog);
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
					description = method.getAnnotation(com.yayiabc.common.annotation.AdminLog.class).description();  
					break;  
				}  
		    }  
		}  
		return description;  
	}	
}
