package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.PlaceOrderService;


//下订单
@Controller
@RequestMapping("api/po")
public class PlaceOrderController {
	@Autowired
	private PlaceOrderService placeOrderService;
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
			,@RequestParam(value="sumPrice") Integer sumPrice,
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
	@ResponseBody
	@RequestMapping("generaOrder")
	public DataWrapper<HashMap<String, Object>> generaOrder(
			//@RequestHeader String token,
			@RequestParam(value="token",required=false) String token,
			//@RequestParam(required=true) List<OrderItem>  orderItem,
			@RequestParam(required=true) String  orderItem,
			@ModelAttribute Ordera order
			){
		System.out.println(token+"  orderItem:"+orderItem+" order:"+order);
		//return placeOrderService.generaOrder(token,orderItem,order);
		return null;
	}
}
