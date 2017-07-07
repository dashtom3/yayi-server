package com.yayiabc.http.mvc.controller.saleManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleLogService;

@Controller
@RequestMapping("api/saleLog")
public class SaleLogController {

	@Autowired
	SaleLogService saleLogService;
	
	
	// 获取验证码
	 
	@RequestMapping("getVerifyCode")
	@ResponseBody
	public DataWrapper<Void> getVerifyCode(
		@RequestParam(value="phone",required=true)String phone	
	){
		return saleLogService.getVerifyCode(phone);
	}
	
	
	 //用户注册
	 
	@RequestMapping("register")
	@ResponseBody
	public DataWrapper<SaleInfo> register(
		@RequestParam(value="phone",required=true)String phone,
		@RequestParam(value="password",required=true)String password,
		@RequestParam(value="code",required=true)String code
	){
		return saleLogService.register(phone, password, code);
	}
	
	
	 //短信登录
	 
	@RequestMapping("noteLogin")
	@ResponseBody
	public DataWrapper<SaleInfo> noteLogin(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="code",required=true)String code
	){
		return saleLogService.noteLogin(phone, code);
	}
	
	//密码登录
	@RequestMapping("pwdLogin")
	@ResponseBody
	public DataWrapper<SaleInfo> pwdLogin(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "password", required = true) String password
	){
		return saleLogService.pwdLogin(phone, password);
	}
	
	//退出登录
	@RequestMapping("reLogin")
	@ResponseBody
	public DataWrapper<Void> reLogin(
			@RequestParam(value = "token", required = true) String token
	){
		return saleLogService.reLogin(token);
	}
	
	//忘记密码
	@RequestMapping("forgetPwd")
	@ResponseBody
	public DataWrapper<Void> forgetPwd(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "code", required = true) String code ,
			@RequestParam(value = "password", required = true) String password
	){
		return saleLogService.forgetPwd(phone, code, password);
	}
}
