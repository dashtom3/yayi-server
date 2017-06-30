package com.yayiabc.http.mvc.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ParamUtil;
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
	public DataWrapper<List<User>> list(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="trueName",required=true)String trueName,
			@RequestParam(value="companyName",required=true)String companyName,
			@RequestParam(value="type",required=true)Integer type,
			@RequestParam(value="state",required=true)Integer state,
			@RequestParam(value="token",required=true)String token
	){
		return userCertificationListService.list(phone, trueName, companyName, type, state);
	}
	
	/**
	 * 审核用户资质认证信息123
	 */
	@RequestMapping(value="verify",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> verify(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="state",required=true)Integer state,
			@RequestParam(value="failReason",required=true)String failReason,
			@RequestParam(value="token",required=true)String token
	){
		return userCertificationListService.verify(phone, state, failReason);			
	}
}
