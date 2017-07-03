package com.yayiabc.http.mvc.controller.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;
import com.yayiabc.http.mvc.service.UserStatisticsService;

@Controller
@RequestMapping("api/userStatistics")
public class UserStatisticsController {
	
	@Autowired
	UserStatisticsService userStatisticsService;
	
	/**
	 * 电商用户统计
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<UserStatistics>> query(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="trueName",required=true)String trueName,
			@RequestParam(value="startDate",required=true)String startDate,
			@RequestParam(value="endDate",required=true)String endDate,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="token",required=true)String token
	){
		return userStatisticsService.query(phone, trueName, startDate, endDate,currentPage,numberPerPage);
	}
}
