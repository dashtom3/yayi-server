package com.yayiabc.http.mvc.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;
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
	public DataWrapper<List<QbRecord>> list(
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
			@RequestParam(value = "numberPerPage", required = false, defaultValue = "10") Integer numberPerPage,
			@RequestParam(value = "token", required = true) String token) {
		return userQbListService.list(phone, startDate, endDate, currentPage,
				numberPerPage);
	}

	/**
	 * 修改用户乾币
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> update(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "qbBalance", required = true) Integer qbBalance,
			@RequestParam(value = "token", required = true) String token) {
		return userQbListService.update(qbBalance, phone);
	}
}
