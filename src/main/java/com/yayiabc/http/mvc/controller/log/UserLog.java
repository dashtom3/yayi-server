package com.yayiabc.http.mvc.controller.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.LogService;

@Controller
@RequestMapping("api/log")
public class UserLog {
	@Autowired
	private LogService logService;
	
	@RequestMapping("showUserLogList")
	@ResponseBody
	@AdminTokenValidate(description="管理员查看电商用户操作日志")
	public DataWrapper<List<com.yayiabc.http.mvc.pojo.log.UserLog>> showUserLogList(
			@RequestHeader(value="admintoken",required=true)String adminToken,
			@RequestParam(value="operate",required=false)String operate,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		if("".equals(operate)){
			operate=null;
		}
		if("".equals(phone)){
			phone=null;
		}
		return logService.showUserLogList(operate,phone,currentPage,numberPerPage);
	}
}
