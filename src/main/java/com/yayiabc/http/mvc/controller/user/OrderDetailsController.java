package com.yayiabc.http.mvc.controller.user;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.OrderNums;
import com.yayiabc.http.mvc.pojo.model.itemIdList;
import com.yayiabc.http.mvc.service.LogisticsService;
import com.yayiabc.http.mvc.service.OrderDetailsService;
import com.yayiabc.http.mvc.service.OrderManagementService;

import net.sf.json.JSONArray;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("api/OrderDetails")
public class OrderDetailsController {
	@Autowired 
	private OrderDetailsService orderDetailsService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private OrderManagementService orderManagementService;
	
	@RequestMapping("show")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="前台订单显示")
	public DataWrapper<List<Ordera>> show(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerpage",required=false,defaultValue="10") Integer numberPerpage
			){
		HashMap<String,String>map=new HashMap<String,String>();
		map.put("state", state);
		DataWrapper<List<Ordera>>  l=orderDetailsService.orderDetailsShow(map,token,currentPage,numberPerpage);
		return l;
	}
	
	//取消订单
	@RequestMapping("cancel")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="前台取消订单")
	public DataWrapper<Void> cancel(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderId",required=true) String orderId
			){
		System.out.println(12312321);
		return orderDetailsService.cancel(orderId,token);
	}
	//查看物流
	@RequestMapping("seeLog")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="查看物流")
	public DataWrapper<String> seeLog(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderId",required=true) String orderId
			){
		return logisticsService.queryLog(orderId);
	}
	
	//确定收货
	@RequestMapping("confirmReceipt")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="确定收货")
	public DataWrapper<Void> confirmReceipt(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderId",required=true) String orderId
			){
		return orderDetailsService.confirmReceipt(orderId);
	}
	//显示评论相关内容
	@RequestMapping("showComItem")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="显示评论相关内容")
	public DataWrapper<Ordera> showComItem(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderId",required=true) String orderId
			){
		return orderDetailsService.showComItem(orderId);
	}
	//确定评论[{'num':1,'price':12,'itemSKU':'1707101359261'}]
	@RequestMapping("makeSureCom")
	@ResponseBody
	/**
	 *itemIdList [{'itemId':1,'score':12,'data':'1707101359261'}]
	 * @param token
	 * @param orderId
	 * @param itemIdList
	 * @return
	 */
	@UserTokenValidate
	@UserLog(description="前台确定评论")
	public DataWrapper<Void> makeSureCom(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderId",required=true) String orderId,
			@RequestParam(value="itemIdList",required=true) String itemIdList
			){
		JSONArray json = JSONArray.fromObject(itemIdList);
		ArrayList<itemIdList> itemIdListy = (ArrayList<itemIdList>)JSONArray.toCollection(json,itemIdList.class);
		return orderDetailsService.makeSureCom(token,orderId,itemIdListy);
	}
	/*@UserTokenValidate*/
	/*@UserLog(description="统计单数")*/
	@RequestMapping("queryOrderNums")
	@ResponseBody
	public DataWrapper<List<OrderNums>> queryOrderNums(
			@RequestHeader(value="token",required=true) String token
			){
		return orderDetailsService.queryOrderNums(token);
	}
	
	@RequestMapping("queryOrderInvoice")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="前台显示发票信息")
	public DataWrapper<Invoice>queryOrderInvoice(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderId",required=true) String orderId
			){
		return orderManagementService.queryOrderInvoice(orderId);
	}
	
}
