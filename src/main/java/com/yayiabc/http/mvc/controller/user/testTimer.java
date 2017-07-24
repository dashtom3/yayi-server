package com.yayiabc.http.mvc.controller.user;

import java.util.Date;
import java.util.Timer;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Component;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.http.mvc.service.TimerChangeStateService;



@Component
public class testTimer extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private TimerChangeStateService timerChangeStateService; 
	public void init(){
		//Date d=new Date();
		System.err.println("我这个时候被加载了：");
		//CacheUtils.getInstance().addCache("f7598780-2bc1-4e8f-87d3-0cd88c900a630053", d);
		Timer timer = new Timer();
		timer.schedule(new MyTask(),1000,9000);
	}
}


