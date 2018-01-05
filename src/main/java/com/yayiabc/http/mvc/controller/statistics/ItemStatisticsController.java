package com.yayiabc.http.mvc.controller.statistics;

import java.util.List;

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
import com.yayiabc.http.mvc.pojo.model.ItemStatistics;
import com.yayiabc.http.mvc.service.ItemStatisticsService;

@Controller
@RequestMapping("api/itemStatistics")
public class ItemStatisticsController {

	@Autowired
	ItemStatisticsService itemStatisticsService;
	
	/**
	 * 商品统计
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员查询商品统计")
	public DataWrapper<List<ItemStatistics>> query(
			@RequestParam(value="itemName",required=false)String itemName,
			@RequestParam(value="itemId",required=false)String itemId,
			@RequestParam(value="itemSKU",required=false)String itemSKU,
			@RequestParam(value="itemBrandName",required=false)String itemBrandName,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestParam(value="state",required=false,defaultValue="1") Integer state,
    		@RequestHeader(value="adminToken",required=true)String adminToken
	){
		return itemStatisticsService.query(itemName, itemId, itemSKU, itemBrandName, startDate, endDate, currentPage, numberPerPage, state);
	}
}
