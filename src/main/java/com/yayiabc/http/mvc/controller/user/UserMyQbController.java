package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserMyQbService;



@Controller
@RequestMapping("api/userMyQb")
public class UserMyQbController {
	@Autowired
	UserMyQbService userMyQbService;

	/**
	 * 添加我的乾币记录
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="添加我的乾币记录")
	public DataWrapper<Void> add(
			@ModelAttribute QbRecord qbRecord,
			@RequestHeader(value = "token", required = true) String token) {
		return userMyQbService.add(qbRecord, token);
	}

	/**
	 * 获取乾币记录信息列表
	 */
	@RequestMapping(value = "query", method = RequestMethod.GET)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户获取乾币记录信息列表")
	public DataWrapper<List<QbRecord>> query(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
			@RequestParam(value = "numberPerPage", required = false, defaultValue = "10") Integer numberPerPage,
			@RequestHeader(value = "token", required = true) String token){
		return userMyQbService.query(token, currentPage, numberPerPage);
	}
}
