package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.FindCusService;

@Controller
@RequestMapping("api/findCus")
public class findCusController {
	@Autowired
	private  FindCusService findCusService;
	//未注册客户
	@RequestMapping("unregistered")
	@ResponseBody
	public DataWrapper<List<CusResources>>  unregistered(
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value = "currentPage",required=false) Integer currentPage,//当前页
  		  @RequestParam(value = "numberPerPage",required=false) Integer numberPerPage//取多少
			){
		return  findCusService.shows(state,currentPage,numberPerPage);
	}
	//已注册客户 待绑定
	@RequestMapping("registered")
	@ResponseBody
	public DataWrapper<List<User>>  registered(
		@RequestParam(value="state",required=false)String state,
		@RequestParam(value = "currentPage",required=false) Integer currentPage,//当前页
		  @RequestParam(value = "numberPerPage",required=false) Integer numberPerPage//取多少
			){
		
		return  findCusService.show(state,currentPage,numberPerPage);
	}
	//我已经绑定的客户
	//调用张鹏接口
	@RequestMapping("me")
	@ResponseBody
	public DataWrapper<List<User>>  me(
			@RequestParam(value="saleToken",required=true)String saleToken     		  
			){
		return  findCusService.showMyCus(saleToken);
	}
}
