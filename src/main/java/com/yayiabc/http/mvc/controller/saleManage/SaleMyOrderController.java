package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.MyOrderVo;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleDataVo;
import com.yayiabc.http.mvc.service.SaleMyOrderService;

@Controller
@RequestMapping("api/saleMyOrder")
public class SaleMyOrderController {
	@Autowired
	SaleMyOrderService saleMyOrderService;
	
	/**
	 * 我的业绩统计数据
	 */
	@RequestMapping(value="myOrderData",method=RequestMethod.GET)
	@ResponseBody
	@SaleTokenValidate(description="销售员查询我的业绩")
	public DataWrapper<SaleDataVo> myOrderData(
			@RequestHeader(value="saleToken",required=true) String token
	){
		return saleMyOrderService.myOrderData(token);
	}
	
	/**
	 * 我的业绩订单列表
	 */
	@RequestMapping(value="myOrderList",method=RequestMethod.GET)
	@ResponseBody
	@SaleTokenValidate(description="销售员查询我的业绩")
	public DataWrapper<List<MyOrderVo>> myOrderList(
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="saleToken",required=true) String token
	){
		return saleMyOrderService.myOrderList(token, currentPage, numberPerPage);
	}
	
	/**
	 * 折线图
	 */
	@RequestMapping(value="chart",method=RequestMethod.GET)
	@ResponseBody
	@SaleTokenValidate(description="销售员查询折线图")
	public DataWrapper<List<SaleDataVo>> chart(
			@RequestParam(value="year",required=true) String year,
    		@RequestParam(value="month",required=true) String month,
    		@RequestHeader(value="saleToken",required=true) String token
	){
		return saleMyOrderService.chart(token, year, month);
	}
	
	
	/**
	 * 查看详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	@SaleTokenValidate(description="销售员查询我的业绩详情")
	public DataWrapper<OrderVo> detail(
    		@RequestParam(value="orderId",required=true) String orderId,
    		@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="saleToken",required=true) String token
	){
		return saleMyOrderService.detail(token, orderId, currentPage, numberPerPage);
	}
}
