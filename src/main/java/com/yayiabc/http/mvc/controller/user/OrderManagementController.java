package com.yayiabc.http.mvc.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.OrderManagement;
import com.yayiabc.http.mvc.service.OrderManagementService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("api/showUserOrderManage")
public class OrderManagementController {
   @Autowired
   private OrderManagementService orderManagementService;
   
   //显示用户订单
   @RequestMapping("showOrder")
   @ResponseBody
   @AdminTokenValidate(description="后台订单显示")
   public DataWrapper<List<OrderManagement>> showOrder(
		   @RequestHeader(value="adminToken",required=false) String adminToken,
		   @RequestParam(value="orderId",required=false) String orderId,
		   @RequestParam(value="buyerInfo",required=false)String buyerInfo,
		   @RequestParam(value="orderState",required=false)String orderState,
		   @RequestParam(value="orderCTime",required=false)String orderCTime,
		   @RequestParam(value="orderETime",required=false)String orderETime,
		   @RequestParam(value="isRefund",required=false)String isRefund,
		   @RequestParam(value="currentPage",required=false)Integer currentPage,
		   @RequestParam(value="numberPerpage",required=false)Integer numberPerpage
		   ){
	   if("".equals(orderState)){
		   orderState=null;  
	   }
	   
	       HashMap<String, Object> hMap=new HashMap<String,Object>();
	       hMap.put("orderId", orderId);
	       hMap.put("buyerInfo", buyerInfo);
	       hMap.put("orderState", orderState);
	       hMap.put("orderCTime", orderCTime);
	       hMap.put("orderETime", orderETime);
	       hMap.put("isRefund", isRefund);
	   return  orderManagementService.showOrder(hMap,currentPage,numberPerpage);
   }
   //关闭交易or确定交易
   @RequestMapping("closeTrading")
   @ResponseBody
   @AdminTokenValidate(description="确定交易")
   public DataWrapper<Void> closeTrading(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="orderId") String orderId,
		   @RequestParam(value="flagBit") String flagBit
		   ){
	   return  orderManagementService.closeTrading(orderId,flagBit);
   }
   //显示退款处理
   @RequestMapping("showRefundProcessing")
   @ResponseBody
   @AdminTokenValidate(description="显示退款处理")
   public DataWrapper<Ordera> refundProcessing(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		  @RequestParam(value="orderId") String orderId
		   ){
	   return  orderManagementService.refundProcessing(orderId);
   }
   //显示已经退款数据的订单信息
   @RequestMapping("showRefundOrderMessage")
   @ResponseBody
   @AdminTokenValidate(description="显示已经订单的退款信息")
   public DataWrapper<Ordera> showRefundOrderMessage(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		  @RequestParam(value="orderId") String orderId
		   ){
	   return  orderManagementService.showRefundOrderMessage(orderId);
   }
 //退款数据处理
   @RequestMapping("makeRefundData")
   @ResponseBody
   @AdminTokenValidate(description="保存退款处理")
   public DataWrapper<HashMap<String, Object>> makeRefundData(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
            @RequestParam("orderItem") String orderItem
            /**
             * 一个 orderItem 对象需要传 退货数量 num，还有sku ，orderId
             */
		   ){
	   JSONArray json = JSONArray.fromObject(orderItem);
		ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>)JSONArray.toCollection(json,OrderItem.class);
		System.out.println(orderItemList);
	   return  orderManagementService.makeRefundData(orderItemList);
   }
   //模拟失去焦点事件
   @RequestMapping("loseFocus")
   @ResponseBody
   public DataWrapper<Map<String, Object>> loseFocus(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="itemId",required=true) String itemId,
		   @RequestParam(value="orderId",required=true) String orderId,
		   @RequestParam(value="num",required=true) Integer num  
		   ){
	   return  orderManagementService.loseFocus(num,orderId,itemId);
   }
   
   //仓库发货 Warehouse delivery warehouseDelivery
   @RequestMapping("warehouseDelivery")
   @ResponseBody
   @AdminTokenValidate(description="仓库发货")
   public DataWrapper<Void> warehouseDelivery(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="orderId",required=true) String orderId,
		   @RequestParam(value="logisticsName",required=true) String logisticsName,
		   @RequestParam(value="logisticsCode",required=true) String logisticsCode
		   ){
	   return  orderManagementService.warehouseDelivery(orderId,logisticsName,logisticsCode);
   }
   //显示订单详情
   @RequestMapping("queryOrderDetails")
   @ResponseBody
   @AdminTokenValidate(description="显示订单详情")
   public DataWrapper<Ordera>  queryOrderDetails(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="orderId",required=true) String orderId
		   ){
	   return orderManagementService.queryOrderDetails(orderId);
   }
}
