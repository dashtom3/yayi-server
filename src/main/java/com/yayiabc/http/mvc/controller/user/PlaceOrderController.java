package com.yayiabc.http.mvc.controller.user;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.PlaceOrderService;

import net.sf.json.JSONArray;

//下订单
@Controller
@RequestMapping("api/po")
public class PlaceOrderController {
	@Autowired
	private PlaceOrderService placeOrderService;
	
	@Autowired
	private AliPayService aliPayService;
	//  使用钱币抵扣时  onChange
	@RequestMapping("Ded")
	@ResponseBody
	@UserTokenValidate
	 @UserLog(description="使用钱币抵扣")
	public DataWrapper<Integer> Ded(
			@RequestHeader(value="token") String token,
			@RequestParam(value="qbnum") Integer num
			){
		return placeOrderService.ded(token,num);
	}
	//选择地址
	@RequestMapping("upateAddress")
	@ResponseBody   //ipi需要文档修改
	@UserTokenValidate
	@UserLog(description="下单前 选择收货地址")
	public DataWrapper<HashMap<String, Object>> upateAddress(
			@RequestHeader(value="token") String token,
			@RequestParam(value="receiverId") Integer receiverId
			,@RequestParam(value="sumPrice") Double sumPrice,
			@RequestParam(value="itemSum") Integer itemSum
			){
		return placeOrderService.upateAddress(receiverId,sumPrice,itemSum);
	}

	/*//伪清空 购物车
	@ResponseBody
	@RequestMapping("emptyCart")
	 @AdminLog
	public DataWrapper<Void> emptyCart(@RequestParam(value="token")String token){
		return placeOrderService.emptyCart(token);
	}
*/
	//1234
	@RequestMapping("generaOrder")
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="提交订单")
	//@ExceptionHandler
	public DataWrapper<HashMap<String, Object>> generaOrder(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderItem",required=true) String  orderItem,
			@ModelAttribute Ordera order,
			@ModelAttribute Invoice  invoice
			){
		JSONArray json = JSONArray.fromObject(orderItem);
		ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>)JSONArray.toCollection(json,OrderItem.class);
		//接入 支付   订单号  商品名称    付款金额    商品描述
		return placeOrderService.generaOrder(token,orderItemList,order,invoice);
	}
	//提交订单前显示上次填写发票信息
	@RequestMapping("queryLastInvoice")
	@ResponseBody
	@UserTokenValidate
	 @UserLog(description="查询上次发票信息")
	public DataWrapper<Invoice>  queryLastInvoice(
			@RequestHeader(value="token",required=true) String token
			){
		return placeOrderService.queryLastInvoice(token);
	}
}
