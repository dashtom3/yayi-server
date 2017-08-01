package com.yayiabc.http.mvc.controller.user;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
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
	 @AdminLog(description="使用钱币抵扣")
	public DataWrapper<Void> Ded(
			@RequestHeader(value="token") String token,
			@RequestParam(value="qbnum") Integer num
			){
		return placeOrderService.ded(token,num);
	}
	//选择地址
	@RequestMapping("upateAddress")
	@ResponseBody   //ipi需要文档修改
	@UserTokenValidate
	 @AdminLog(description="下单前 选择收货地址")
	public DataWrapper<HashMap<String, Object>> upateAddress(
			@RequestHeader(value="token") String token,
			@RequestParam(value="receiverId") Integer receiverId
			,@RequestParam(value="sumPrice") Double sumPrice,
			@RequestParam(value="itemSum") Integer itemSum
			){
		return placeOrderService.upateAddress(receiverId,sumPrice,itemSum);
	}

	//伪清空 购物车
	@ResponseBody
	@RequestMapping("emptyCart")
	 @AdminLog
	public DataWrapper<Void> emptyCart(@RequestParam(value="token")String token){
		return placeOrderService.emptyCart(token);
	}

	//1234
	@RequestMapping("generaOrder")
	@ResponseBody
	@UserTokenValidate
	 @AdminLog(description="提交订单")
	public DataWrapper<HashMap<String, Object>> generaOrder(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="orderItem",required=true) String  orderItem,
			@ModelAttribute Ordera order,
			@ModelAttribute Invoice  invoice
			){
		//System.out.println(token+"  orderItem:"+orderItem+" order:"+order);
		//return placeOrderService.generaOrder(token,orderItem,order);
		JSONArray json = JSONArray.fromObject(orderItem);
		System.out.println(token);
		ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>)JSONArray.toCollection(json,OrderItem.class);
		System.out.println(orderItemList);
		System.out.println(order);
		//接入 支付   订单号  商品名称    付款金额    商品描述
		/**
		 * hashMap.put("itemNames", sb.toString());
		hashMap.put("OrderId",orderId);
		hashMap.put("sumPrice",sumPrice);
		hashMap.put("giveQbNum", giveQbNum);
		hashMap.put("itemSum", itemSum);
		 */
		DataWrapper<HashMap<String, Object>> hm=placeOrderService.generaOrder(token,orderItemList,order,invoice);
		                return hm;                                                                                                             
		/*  这里先放下
		 * String  str=aliPayService.packingParameter((String)hm.get("OrderId"), (String)hm.get("itemNames"), 
				                                           //描述
				(String)hm.get("sumPrice"),(String)hm.get("itemMS") );
		try {
			response.getWriter().write(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//return str;
	}
	/***
	 * String personstr="[{'itemId':'170564878','num':1,'price':12,'itemSKU':123543543543'}]";
	  JSONArray json = JSONArray.fromObject(personstr);
	  List persons = (List) JSONArray.toCollection(json, OrderItem.class);
	  System.out.println(persons.get(0));
	 */
}
