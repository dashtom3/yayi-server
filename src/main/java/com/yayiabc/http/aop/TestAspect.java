package com.yayiabc.http.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {
	@Before(value="* com.yayiabc.http.mvc.controller.user.*Controller.*(..))")
	public void before(){
		System.out.println("我是前置通知");
	}
}
