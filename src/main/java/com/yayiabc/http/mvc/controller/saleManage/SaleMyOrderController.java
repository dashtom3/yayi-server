package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleDataStatistics;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;
import com.yayiabc.http.mvc.service.SaleMyOrderService;

@Controller
@RequestMapping("api/saleMyOrder")
public class SaleMyOrderController {
	@Autowired
	SaleMyOrderService saleMyOrderService;
	
	/**
	 * 我的订单
	 */
	@RequestMapping(value="myOrder",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<SaleDataStatistics> myOrder(
			
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestParam(value="token",required=true,defaultValue="10") String token
	){
		return saleMyOrderService.myOrder(token, currentPage, numberPerPage);
	}
	
	/**
	 * 折线图
	 */
	@RequestMapping(value="chart",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<SaleDataStatistics>> chart(
			
			@RequestParam(value="year",required=true,defaultValue="1") String year,
    		@RequestParam(value="month",required=true,defaultValue="10") String month,
    		@RequestParam(value="token",required=true,defaultValue="10") String token
	){
		return saleMyOrderService.chart(token, year, month);
	}
	
	
	/**
	 * 查看详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<SaleIncomeVo> detail(
			
			@RequestParam(value="userPhone",required=true,defaultValue="1") String userPhone,
    		@RequestParam(value="orderId",required=true,defaultValue="10") String orderId,
    		@RequestParam(value="token",required=true,defaultValue="10") String token
	){
		return saleMyOrderService.detail(userPhone, orderId, token);
	}
}
