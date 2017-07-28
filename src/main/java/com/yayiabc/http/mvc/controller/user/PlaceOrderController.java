package com.yayiabc.http.mvc.controller.user;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	//多个商品时 
	@RequestMapping("buyNows")
	@ResponseBody
	public 	DataWrapper<HashMap<String, Object>> buyNows(
			@RequestParam(value="token") String token,
			@RequestParam(value="itemSKUs",required=true) String[] itemSKUs,
			/*@RequestParam(value="itemId") String itemId,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="receiverId") String receiver_id,
			@RequestParam(value="itemPropertyNamea") String itemPropertyNamea,
			@RequestParam(value="itemPropertyNameb") String itemPropertyNameb,
			@RequestParam(value="itemPropertyNamec") String itemPropertyNamec,
			@RequestParam(value="num") String num*/
			@ModelAttribute Ordera order
			){
		/*System.out.println(Arrays.toString(itemSKU));
		return null;*/
		return placeOrderService.buyNows(token,itemSKUs,order);
	}
	//  使用钱币抵扣时  onChange
	@RequestMapping("Ded")
	@ResponseBody
	public DataWrapper<Void> Ded(
			@RequestParam(value="token") String token,
			@RequestParam(value="qbnum") Integer num
			){
		return placeOrderService.ded(token,num);
	}
	//选择地址
	@RequestMapping("upateAddress")
	@ResponseBody   //ipi需要文档修改
	public DataWrapper<HashMap<String, Object>> upateAddress(
			@RequestParam(value="receiverId") Integer receiverId
			,@RequestParam(value="sumPrice") Double sumPrice,
			@RequestParam(value="itemSum") Integer itemSum
			){
		return placeOrderService.upateAddress(receiverId,sumPrice,itemSum);
	}
	//提交订单//将信息保存到订单表里
	@RequestMapping("saveMessage")
	@ResponseBody
	public DataWrapper<Void>  saveMessage(
			/*	@RequestParam(value="orderId")String orderId,
			@RequestParam(value="inHead",required=false)String inHead,
			@RequestParam(value="registerNum",required=false)String registerNum,
			@RequestParam(value="orderMessage",required=false)String orderMessage,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="actualPay",required=false)String actualPay,
			@RequestParam(value="actualPay",required=false)String actualPay*/
			@ModelAttribute Ordera order,
			@RequestParam(value="token",required=true)String token
			){
		//将信息保存到订单表里

		return placeOrderService.saveMessage(order,token);
	}
	//当用户点击商品图片购买时:
	@RequestMapping("buyNow")
	@ResponseBody
	public 	DataWrapper<HashMap<String, Object>> buyNow(
			@ModelAttribute OrderItem orderItem,
			@RequestParam(value="token",required=true) String token,
			@ModelAttribute Ordera order
			){

		//Integer receiverId= Integer.parseInt(receiverIds);
		return placeOrderService.buyNow(orderItem,token,order);
	}
	//伪清空 购物车
	@ResponseBody
	@RequestMapping("emptyCart")
	public DataWrapper<Void> emptyCart(@RequestParam(value="token")String token){
		return placeOrderService.emptyCart(token);
	}

	//1234
	@RequestMapping("generaOrder")
	@ResponseBody
	public DataWrapper<HashMap<String, Object>> generaOrder(
			@RequestParam(value="token",required=true) String token,
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
