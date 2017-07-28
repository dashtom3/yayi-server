package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.SaleListService;
import com.yayiabc.http.mvc.service.UserManageListService;

@Controller
@RequestMapping("api/saleList")
public class SaleListController {
	@Autowired
	SaleListService saleListService;
	@Autowired
	UserManageListService userManageListService;

	/**
	 * 销售员列表
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate(description="管理员查询销售员列表")
	public DataWrapper<List<SaleInfo>> query(
			@RequestParam(value="saleId",required=false)String saleId,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="isBindUser",required=false)Integer isBindUser,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="adminToken",required=true)String adminToken
	){
		return saleListService.query(saleId, phone, trueName,isBindUser, currentPage, numberPerPage);
	}
	
	/**
	 * 获取简略用户信息列表
	 */
	@RequestMapping(value="userlist",method=RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate(description="获取简略用户信息列表")
	public DataWrapper<List<User>> userlist(
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestParam(value="userPhone",required=false)String userPhone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="companyName",required=false)String companyName,
			@RequestParam(value="isBind",required=true)Integer isBind,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="adminToken",required=true)String adminToken
	){
		return saleListService.userlist(salePhone,userPhone, trueName, companyName, isBind, currentPage, numberPerPage);
	}
	
	/**
	 * 获取销售员详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate(description="管理员查询销售员详情")
	public DataWrapper<SaleInfo> datail(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="adminToken")String adminToken
	){
		return saleListService.detail(phone, currentPage, numberPerPage);
	}
	
	/**
	 * 绑定用户
	 */
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	@AdminTokenValidate(description="销售员绑定用户")
	public DataWrapper<Void> bind(
			@RequestParam(value="userPhone",required=true)String[] userPhone,
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestHeader(value="adminToken")String adminToken
	){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		for(int i=0;i<userPhone.length;i++){
			dataWrapper=userManageListService.bind(salePhone, userPhone[i]);	
			
		}
		return dataWrapper;
	}
	
	/**
	 * 取消绑定用户
	 */
	@RequestMapping(value="disBind",method=RequestMethod.POST)
	@ResponseBody
	@AdminTokenValidate(description="销售员取消绑定用户")
	public DataWrapper<Void> disBind(
			@RequestParam(value="salePhone",required=true)String salePhone,
			@RequestParam(value="userPhone",required=true)String[] userPhone,
			@RequestHeader(value="adminToken")String adminToken
	){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		for(int i=0;i<userPhone.length;i++){
			dataWrapper=userManageListService.disBind(salePhone, userPhone[i]);
		}
		return dataWrapper;
	}
}
