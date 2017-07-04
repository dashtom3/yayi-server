package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;

import com.yayiabc.http.mvc.service.LogisticsService;
import com.yayiabc.http.mvc.service.OrderDetailsService;
import com.yayiabc.http.mvc.service.PlaceOrderService;
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
     private PlaceOrderService placeOrderService;
     @Autowired
     private LogisticsService logisticsService;
     @RequestMapping("show")
     @ResponseBody
     public DataWrapper<List<User>> show(
    		  @RequestParam(value="token",required=false) String token,
    		  @RequestParam(value="state",required=false) String state,
    		  @RequestParam(value="currentPage",required=false) Integer currentPage,
     		  @RequestParam(value="numberPerpage",required=false) Integer numberPerpage
    		 ){
    	    HashMap<String,String>map=new HashMap<String,String>();
    	    map.put("state", state);
    	 return orderDetailsService.orderDetailsShow(map,token,currentPage,numberPerpage);
     }
     //orderItemNum  订单里面的商品数
     //付款
     @RequestMapping("payment")
     @ResponseBody
     public DataWrapper<HashMap<String, Object>> payment(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderItemNum",required=true) String orderItemNum,
    		  @RequestParam(value="receiverId",required=true) Integer receiverId,
    		  @ModelAttribute OrderItem orderItem
    		 ){
    	  //如果orderItemNum>1  表示 是从购物车发出的结算请求
    	     if(Integer.parseInt(orderItemNum)>1){
    	    	 return placeOrderService.buyNows(token, receiverId);
    	     }else{
    	    	 return placeOrderService.buyNow(orderItem,token, receiverId);
    	     }
     }
     //取消订单
     @RequestMapping("cancel")
     @ResponseBody
     public DataWrapper<Void> cancel(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderId",required=true) String orderId
    		 ){
    	 return orderDetailsService.cancel(orderId);
     }
     //查看物流
     @RequestMapping("seeLog")
     @ResponseBody
     public DataWrapper<String> seeLog(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderId",required=true) String orderId
    		 ){
    	 return logisticsService.queryLog(token, orderId);
     }
     //确定收货  提示获得钱币数
     @RequestMapping("definiteHints")
     @ResponseBody
     public DataWrapper<HashMap<String, Object>> definiteHints(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderItemNum",required=true) String orderItemNum,
    		  @RequestParam(value="receiverId",required=true) Integer receiverId,
    		  @ModelAttribute OrderItem orderItem
    		 ){
    	  //如果orderItemNum>1  表示 是从购物车发出的结算请求
    	     if(Integer.parseInt(orderItemNum)>1){
    	    	 return placeOrderService.buyNows(token, receiverId);
    	     }else{
    	    	 return placeOrderService.buyNow(orderItem,token, receiverId);
    	     }
     }
     //确定收货
     @RequestMapping("confirmReceipt")
     @ResponseBody
     public DataWrapper<Void> confirmReceipt(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderId",required=true) String orderId
    		 ){
    	 return orderDetailsService.confirmReceipt(orderId);
     }
     //显示评论相关内容
     @RequestMapping("showComItem")
     @ResponseBody
     public DataWrapper<Ordera> showComItem(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderId",required=true) String orderId
    		 ){
    	 return orderDetailsService.showComItem(orderId);
     }
     //确定评论
     @RequestMapping("makeSureCom")
     @ResponseBody
     public DataWrapper<Void> makeSureCom(
    		  @RequestParam(value="token",required=true) String token,
    		  @RequestParam(value="orderId",required=true) String orderId,
    		  @RequestParam(value="itemId",required=true) String itemId,
    		  @RequestParam(value="score",required=true) String score,
    		  @RequestParam(value="data",required=true) String data
    		 ){
    	 HashMap<String, String> hashMap=new HashMap<String, String>();
    	 hashMap.put("token", token);
    	 hashMap.put("orderId", orderId);
    	 hashMap.put("itemId", itemId);
    	 hashMap.put("score", score);
    	 hashMap.put("data", data);
    	 return orderDetailsService.makeSureCom(hashMap);
     }
}
