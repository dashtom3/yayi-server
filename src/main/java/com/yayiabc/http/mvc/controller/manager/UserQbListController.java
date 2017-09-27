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
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserQbListService;

@Controller
@RequestMapping("api/userQbList")
public class UserQbListController {

	@Autowired
	UserQbListService userQbListService;

	/**
	 * 获取用户乾币信息列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员获取用户乾币信息列表")
	public DataWrapper<List<QbRecord>> list(
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
			@RequestParam(value = "numberPerPage", required = false, defaultValue = "10") Integer numberPerPage,
			@RequestHeader(value="adminToken",required=true)String adminToken
			) {
		return userQbListService.list(phone, startDate, endDate, currentPage,numberPerPage);
	}

	/**
	 * 修改用户乾币
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员修改用户乾币")
	public DataWrapper<Void> update(
			@RequestHeader(value="adminToken",required=true)String adminToken,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "qbBalance", required = true,defaultValue="0") Integer qbBalance,
			@RequestParam(value = "qbType", required = true) String qbType,
			@RequestParam(value = "sign",required=true)String sign   //1是减少   2是增加 
			) {
		return userQbListService.update(qbBalance, phone,qbType,sign);
	}
	
	/**
	 * 查询具体某位用户的钱币余额
	 */
	@RequestMapping(value = "queryQb", method = RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<Map<String, Integer>> queryQb(
			@RequestParam(value = "userPhone", required = true) String userPhone,
			@RequestHeader(value="adminToken",required=true)String adminToken,
			@RequestParam(value = "qbType", required = true) String qbType
			) {
		return userQbListService.queryQb(userPhone,qbType);
	}
}
