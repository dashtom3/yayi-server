package com.yayiabc.http.mvc.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleStatistics;
import com.yayiabc.http.mvc.service.SaleStatisticsService;

@Controller
@RequestMapping("api/saleStatistics")
public class SaleStatisticsController {
	
	@Autowired
	SaleStatisticsService saleStatisticsService;
	
	/**
	 * 电商用户统计
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<SaleStatistics>> query(
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
	){
		return saleStatisticsService.query(phone, trueName, currentPage, numberPerPage);
	}
}
