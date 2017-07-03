package com.yayiabc.http.mvc.controller.adminstrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;
import com.yayiabc.http.mvc.service.AdminstratorService;

@Controller
@RequestMapping("api/adminstrator")
public class AdminstratorController {
	@Autowired
	private AdminstratorService adminstratorService;
	
	/**
	 * 管理员增加
	 */
	@RequestMapping("add")
	@ResponseBody
	public DataWrapper<Void> addAdminstrator(
			@RequestParam(value="phone",required=true) String phone,
			@RequestParam(value="adminstratorPwd",required=true) String adminstratorPwd,
			@RequestParam(value="trueName",required=true) String trueName
			){
		return adminstratorService.addAdminstrator(phone,adminstratorPwd,trueName);
	}
	
	/**
	 * 管理员删除
	 */
	@RequestMapping("delete")
	@ResponseBody
	public DataWrapper<Void> deleteAdminstrator(
			@RequestParam(value="adminstratorId",required=true) Integer adminstratorId
			){
		return adminstratorService.deleteAdminstrator(adminstratorId);
	}
	
	/**
	 * 管理员修改
	 */
	@RequestMapping("update")
	@ResponseBody
	public DataWrapper<Void> updateAdminstrator(
			@RequestParam(value="adminstratorId",required=true) Integer adminstratorId,
			@RequestParam(value="phone",required=true) String phone,
			@RequestParam(value="adminstratorPwd",required=true) String adminstratorPwd,
			@RequestParam(value="trueName",required=true) String trueName
			){
		return adminstratorService.updateAdminstrator(adminstratorId,phone,adminstratorPwd,trueName);
	}
	
	/**
	 * 管理员查询
	 */
	@RequestMapping("query")
	@ResponseBody
	public DataWrapper<List<Adminstrator>> queryAdminstrator(
			@RequestParam(value="phone",required=false) String phone,
			@RequestParam(value="trueName",required=false) String trueName
			){
		return adminstratorService.queryAdminstrator(phone,trueName);
	}
	
	/**
	 * 管理员登录
	 */
	@RequestMapping("login")
	@ResponseBody
	public DataWrapper<Void> loginAdminstrator(
			@RequestParam(value="phone",required=true) String phone,
			@RequestParam(value="adminstratorPwd",required=true) String adminstratorPwd
			){
		return adminstratorService.loginAdminstrator(phone,adminstratorPwd);
	}
}
