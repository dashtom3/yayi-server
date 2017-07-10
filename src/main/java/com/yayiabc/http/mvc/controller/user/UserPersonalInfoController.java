package com.yayiabc.http.mvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public DataWrapper<UserPersonalInfo> detail(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="token",required=true)String token
	){
		return userPersonalInfoService.detail(phone,token);
	}
	
	/**
	 * 编辑个人资料个人信息
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<User> updateUser(
			@ModelAttribute User user,
			@RequestParam(value = "token", required = true) String token
	){
		    return userPersonalInfoService.updateUser(user,token);
	}
	
	/**
	 * 编辑个人资料资质认证
	 */
	@RequestMapping(value = "updateCertification", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Certification> updateCertification(
			@ModelAttribute Certification certification,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "token", required = true) String token
	){
		return userPersonalInfoService.updateCertification(certification, phone,token);
	}
}
