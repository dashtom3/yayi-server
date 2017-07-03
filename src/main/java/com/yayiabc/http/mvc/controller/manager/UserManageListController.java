package com.yayiabc.http.mvc.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.model.UserAllInfo;
import com.yayiabc.http.mvc.service.UserManageListService;

@Controller
@RequestMapping("api/userManageList")
public class UserManageListController {
	@Autowired
	UserManageListService userManageListService;
	
	
	/**
	 * 获取用户信息列表
	 */
	@RequestMapping(value="userlist",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<UserAllInfo>> userlist(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="trueName",required=true)String trueName,
			@RequestParam(value="companyName",required=true)String companyName,
			@RequestParam(value="isBindSale",required=true)Integer isBindSale,
			@RequestParam(value="type",required=true)Integer type,
			@RequestParam(value="saleName",required=true)String saleName,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="token",required=true)Integer token
	){
		return userManageListService.userlist(phone, trueName, companyName, isBindSale, type, saleName,currentPage,numberPerPage);
	}
	
	/**
	 * 获取简略销售员信息列表
	 */
	@RequestMapping(value="salelist",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<SaleInfo>> salelist(
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestParam(value="saleName",required=true)String saleName,
			@RequestParam(value="token",required=true)Integer token
	){
		return userManageListService.salelist(salePhone, saleName);
	}
	
	/**
	 * 绑定销售员
	 */
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> bind(
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestParam(value="userPhone",required=true)String userPhone,
			@RequestParam(value="token",required=true)Integer token
	){
		return userManageListService.bind(salePhone, userPhone);
	}
	
	/**
	 * 获取用户详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<UserAllInfo> detail(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="token",required=true)Integer token
	){
		return userManageListService.detail(phone);
	}
}
