package com.yayiabc.http.mvc.controller.weixin;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.yayiabc.common.annotation.SaleTokenValidate;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;

import com.yayiabc.common.utils.DataWrapper;

@Controller
public class TestController {
	
	
	
	@RequestMapping("api/test")
	@ResponseBody
	public DataWrapper<Void> test(
			@RequestParam("token") String token
			
			){
		System.out.println(token);
		System.out.println("helloWorld");
		return null;
	}
	
	@RequestMapping("api/demo")
	@ResponseBody
	@UserTokenValidate(description="dfjgioldkmng")
	public DataWrapper<Void> demo(
			@RequestParam(value="token")String token
			){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		dataWrapper.setMsg("helloWorld");
		return dataWrapper;
	}
	
	@RequestMapping("api/demoTwo")
	@ResponseBody
	@SaleTokenValidate(description="dfjgioldkmng")
	public DataWrapper<Void> demoTwo(
			@RequestParam(value="token")String token
			){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		dataWrapper.setMsg("helloWorld");
		return dataWrapper;
	}
	
	@RequestMapping("api/demoThree")
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<Void> demoThree(
			@RequestHeader(value="admintoken")String adminToken
			){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		dataWrapper.setMsg("helloWorld");
		return dataWrapper;
	}
	
	
	
	
	
}
