package com.yayiabc.http.mvc.controller.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
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
	@AdminTokenValidate
	public DataWrapper<List<Map<String,String>>> userlist(
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="companyName",required=false)String companyName,
			@RequestParam(value="isBindSale",required=false)Integer isBindSale,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="saleName",required=false)String saleName,
			@RequestParam(value="salePhone",required=false)String salePhone,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="adminToken",required=true)String adminToken
	){
		return userManageListService.userlist(phone, trueName, companyName, isBindSale, type, saleName, salePhone, currentPage, numberPerPage);
	}
	
	/**
	 * 获取简略销售员信息列表
	 */
	@RequestMapping(value="salelist",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<SaleInfo>> salelist(
			@RequestParam(value="salePhone",required=false)String salePhone,
			@RequestParam(value="saleName",required=false)String saleName,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
	){
		return userManageListService.salelist(salePhone, saleName,currentPage,numberPerPage);
	}
	
	/**
	 * 绑定销售员
	 */
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> bind(
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestParam(value="userPhone",required=true)String userPhone
	){
		return userManageListService.bind(salePhone, userPhone);
	}
	
	
	/**
	 * 取消绑定销售员
	 */
	@RequestMapping(value="disBind",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> disBind(
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestParam(value="userPhone",required=true)String userPhone
	){
		return userManageListService.disBind(salePhone, userPhone);
	}
	
	/**
	 * 获取用户详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员查询用户详情")
	public DataWrapper<UserAllInfo> detail(
			@RequestParam(value="phone",required=true)String phone,
			@RequestHeader(value="adminToken",required=true)String adminToken
	){
		return userManageListService.detail(phone);
	}
}
