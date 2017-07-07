package com.yayiabc.http.mvc.controller.saleManage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.CusResoService;
import com.yayiabc.http.mvc.service.FindCusService;

@Controller
@RequestMapping("api/findCus")
public class FindCusController {
	@Autowired
	private  FindCusService findCusService;
	@Autowired
	private CusResoService cusResoService;
	//未注册客户
	@RequestMapping("unregistered")
	public DataWrapper<List<CusResources>>  unregistered(
			@RequestParam(value="cusName",required=false)String cusName,     		  
			@RequestParam(value="phone",required=false)String phone,   
			@RequestParam(value="cusAddress",required=false)String cusAddress
			){
		HashMap<String, String> hashMap=new HashMap<String,String>();
		hashMap.put("cusName", cusName);
		hashMap.put("phone", phone);
		hashMap.put("cusAddress", cusAddress);
		return  cusResoService.show(hashMap);
	}
	//已注册客户 待绑定
	@RequestMapping("registered")
	public DataWrapper<List<SaleInfo>>  registered(
			@RequestParam(value="cusName",required=false)String cusName,     		  
			@RequestParam(value="phone",required=false)String phone,   
			@RequestParam(value="cusAddress",required=false)String cusAddress
			){
		HashMap<String, String> hashMap=new HashMap<String,String>();
		hashMap.put("cusName", cusName);
		hashMap.put("phone", phone);
		hashMap.put("cusAddress", cusAddress);
		return  findCusService.show(hashMap);
	}
	//我已经绑定的客户
	//调用张鹏接口
	@RequestMapping("me")
	public DataWrapper<List<User>>  me(
			@RequestParam(value="saleToken",required=true)String saleToken     		  
			
			){
		return  findCusService.showMyCus(saleToken);
	}
}
