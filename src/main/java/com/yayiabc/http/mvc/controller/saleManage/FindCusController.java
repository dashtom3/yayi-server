package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.SaleLog;
import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.FindCusService;
@Controller
@RequestMapping("api/findCus")
public class FindCusController {
	@Autowired
	private  FindCusService findCusService;
	//未注册客户
	@RequestMapping("unregistered")
	@ResponseBody
	@SaleLog(description="发现客户资源:未注册客户")
	public DataWrapper<List<CusResources>>  unregistered(
			@RequestHeader(value="saleToken",required=true)String saleToken,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value = "currentPage",required=false,defaultValue="1") Integer currentPage,//当前页
  		    @RequestParam(value = "numberPerPage",required=false,defaultValue="10") Integer numberPerPage//取多少
			){
		return  findCusService.shows(state,currentPage,numberPerPage);
	}
	//已注册客户待绑定
	@RequestMapping("registered")
	@ResponseBody
	@SaleLog(description="发现客户资源:已注册客户 待绑定")
	public DataWrapper<List<User>>  registered(
			@RequestHeader(value="saleToken",required=true)String saleToken,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value = "currentPage",required=false,defaultValue="1") Integer currentPage,//当前页
		    @RequestParam(value = "numberPerPage",required=false,defaultValue="10") Integer numberPerPage//取多少
			){
		return  findCusService.show(state,currentPage,numberPerPage);
	}
	//我已经绑定的客户
	//调用张鹏接口
	@RequestMapping("me")
	@ResponseBody
	@SaleTokenValidate
	@SaleLog(description="发现客户资源:我已绑定客户 ")
	public DataWrapper<List<User>>  me(
			@RequestHeader(value="saleToken",required=true)String saleToken     		  
			){
		return  findCusService.showMyCus(saleToken);
	}
}
