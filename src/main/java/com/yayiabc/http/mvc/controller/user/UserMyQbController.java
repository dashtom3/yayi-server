package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import com.yayiabc.common.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Controller
@RequestMapping("api/userMyQb")
public class UserMyQbController {
	@Autowired
	UserMyQbService userMyQbService;

	/**
	 *  添加我的乾币记录
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<QbRecord> add(
			@ModelAttribute QbRecord qbRecord,
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="token",required=true)String token
	){
		return userMyQbService.add(qbRecord,phone);
	}
	
	/**
	 * 获取乾币记录信息列表
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<QbRecord>> query(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="type",required=true)Integer type,
			@RequestParam(value="token",required=true)String token,
			@ModelAttribute Page page
	){
		return userMyQbService.query(phone,type,page);
	}
}
