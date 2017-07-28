package com.yayiabc.http.mvc.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserCertificationListService;

@Controller
@RequestMapping("api/userCertificationList")
public class UserCertificationListController {

	@Autowired
	UserCertificationListService userCertificationListService;
	
	/**
	 * 获取用户资质信息列表
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate(description="管理员查询用户资质信息列表")
	public DataWrapper<List<User>> list(
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="companyName",required=false)String companyName,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="state",required=false)Integer state,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestParam(value="adminToken",required=true)String adminToken
	){
		
		return userCertificationListService.list(phone, trueName, companyName, type, state,currentPage,numberPerPage);
	}
	
	/**
	 * 审核用户资质认证信息
	 */
	@RequestMapping(value="verify",method=RequestMethod.POST)
	@ResponseBody
	@AdminTokenValidate(description="管理员审核用户资质认证信息")
	public DataWrapper<Void> verify(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="state",required=true)Integer state,
			@RequestParam(value="failReason",required=false)String failReason,
			@RequestParam(value="adminToken",required=true)String adminToken
	){
		return userCertificationListService.verify(phone, state, failReason);			
	}
}
