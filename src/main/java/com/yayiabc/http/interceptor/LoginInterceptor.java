package com.yayiabc.http.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yayiabc.common.utils.CheckIsSignUtils;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/*
		 * 验证当前用户是否登录
		 */
		CheckIsSignUtils checkIsSignUtils= CheckIsSignUtils.getInstance();
		ArrayList al=checkIsSignUtils.getList();
		 String token=null;
		   String uri=request.getRequestURI();
		   if(al!=null){
			   HttpSession session=request.getSession();
			   token=(String)session.getAttribute("token");
			   
			   for(int i=0;i<al.size();i++){
				   if(al.get(i).equals(token)){
					   System.out.println("该账号已经登陆");
					   return false;
				   }
			   }
		   }
		   System.out.println(uri);
		   
		   if(token!=null||uri.indexOf("api/user/pwdLogin")!=-1||uri.indexOf("api/user/getVerifyCode")!=-1||
				   uri.indexOf("/api/user/register")!=-1||uri.indexOf("api/user/noteLogin")!=-1
				   ||uri.indexOf("api/user/reLogin")!=-1||uri.indexOf("api/user/forgetPwd")!=-1
				   //创客的登录注册拦截
				   ||uri.indexOf("api/saleLog/pwdLogin")!=-1||uri.indexOf("api/saleLog/getVerifyCode")!=-1||
				   uri.indexOf("/api/saleLog/register")!=-1||uri.indexOf("api/saleLog/noteLogin")!=-1
				   ||uri.indexOf("api/saleLog/reLogin")!=-1||uri.indexOf("api/saleLog/forgetPwd")!=-1
				   /*||uri.indexOf("api/page.htm")!=-1||uri.indexOf("logreg/log.htm")!=-1||uri.indexOf("logreg/validateCode.htm")!=-1
				   
				   ||uri.indexOf("logreg/log.htm")!=-1||uri.indexOf("sys/index.htm")!=-1||uri.indexOf("logreg/findUser")!=-1||uri.indexOf("logreg/reg")!=-1*/){
			
			 return true;
		}else{
			//request.getRequestDispatcher("/WEB-INF/views/logReg.jsp").forward(request, response);
			System.out.println("该用户没登录");
			return false;
		}	 
	}

}
