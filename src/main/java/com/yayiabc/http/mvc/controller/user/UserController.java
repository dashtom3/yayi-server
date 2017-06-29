package com.yayiabc.http.mvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserService;

@Controller
@RequestMapping("api/user")
public class UserController {
	   /*
	    *  action="/api/user/register"
	    */
	@Autowired
	private UserService userService;
	
	//获取验证码
	@RequestMapping("getVerifyCode")
	@ResponseBody
	public DataWrapper<Void> getVerifyCode(@RequestParam(value = "phone", required = true) String phone) {
	        return userService.getVerifyCode(phone);
	    
	}
	
	//用户注册
	@RequestMapping("register")
	@ResponseBody
	public DataWrapper<User> register(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "code", required = true) String code
			){
		return userService.register(phone,password,code);
	}
	
	//短信登录
	@RequestMapping("noteLogin")
	@ResponseBody
	public DataWrapper<User> noteLogin(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "code", required = true) String code
			){
		return userService.noteLogin(phone,code);
		
	}
	
	//密码登录
	@RequestMapping("pwdLogin")
	@ResponseBody
	public DataWrapper<User> pwdLogin(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "password", required = true) String password
			){
		return userService.pwdLogin(phone,password);
	}
	
	//退出登录
	@RequestMapping("reLogin")
	@ResponseBody
	public DataWrapper<Void> reLogin(
			@RequestParam(value = "token", required = true) String token
			){
		return userService.reLogin(token);
	}
	
	//忘记密码
	@RequestMapping("forgetPwd")
	@ResponseBody
	public DataWrapper<Void> forgetPwd(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "code", required = true) String code ,
			@RequestParam(value = "password", required = true) String password
			){
		return userService.forgetPwd(phone,code,password);
	}
	
}
