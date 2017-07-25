package com.yayiabc.http.aop;



import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




import com.yayiabc.http.mvc.pojo.log.UserLog;
import com.yayiabc.http.mvc.service.SystemControllerLogService;


@Aspect
@Component
public class SystemControllerLog {
	//注入service,用于将日志保存进数据库
	@Autowired
	private SystemControllerLogService systemControllerLogService;
	
	
	//controller层切入点
	@Pointcut("@annotation(com.yayiabc.common.annotation.SystemControllerLog)")
	public void controllerAspect(){
		
	}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println(joinPoint.getSignature().getName());
		String operate=getControllerMethodDescription(joinPoint);
		System.out.println(operate);
		System.out.println(joinPoint.getArgs().toString());
		System.out.println("我是前置通知");
		String params = "";  
		if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {  
		   for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
		        params +=joinPoint.getArgs()[i] + ";";  
		   }  
		}
		UserLog userLog=new UserLog();
		userLog.setAdministrator("叶虎");
		userLog.setCreated(new Date());
		userLog.setOperate(operate);
		userLog.setUserName("星云");
		userLog.setArguments(params);
		System.out.println(params);
		systemControllerLogService.addLog(userLog);
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
					description = method.getAnnotation(com.yayiabc.common.annotation.SystemControllerLog.class).description();  
					break;  
				}  
		    }  
		}  
		return description;  
	}  
}  

	

