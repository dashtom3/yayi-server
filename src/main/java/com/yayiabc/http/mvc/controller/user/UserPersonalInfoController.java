package com.yayiabc.http.mvc.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserPersonalInfo;
import com.yayiabc.http.mvc.service.UserPersonalInfoService;


@Controller
@RequestMapping("api/userPersonalInfo")
public class UserPersonalInfoController {
	
	@Autowired
	UserPersonalInfoService userPersonalInfoService;
	
	/**
	 * 获取个人资料详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户获取个人资料详情")
	public DataWrapper<UserPersonalInfo> detail(
			@RequestHeader(value="token",required=true)String token
	){
		return userPersonalInfoService.detail(token);
	}
	
	/**
	 * 编辑个人资料个人信息
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户编辑个人资料个人信息")
	public DataWrapper<User> updateUser(
			@ModelAttribute User user,
			@RequestHeader(value = "token", required = true) String token
	){
		    return userPersonalInfoService.updateUser(user,token);
	}
	
	/**
	 * 编辑个人资料资质认证
	 */
	@RequestMapping(value = "updateCertification", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户编辑个人资料资质认证")
	public DataWrapper<Certification> updateCertification(
			@ModelAttribute Certification certification,
			@RequestHeader(value = "token", required = true) String token
	){
		return userPersonalInfoService.updateCertification(certification,token);
	}
	
	/**
	 * 查询用户是否已绑定销售员
	 */
	@RequestMapping(value="queryBind",method=RequestMethod.GET)
	@ResponseBody
	@UserTokenValidate
	public DataWrapper<Map<String, String>> queryBind(
			@RequestHeader(value="token",required=true)String token
	){
		return userPersonalInfoService.queryBind(token);
	}
}
