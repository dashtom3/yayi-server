package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.OrderManagement;
import com.yayiabc.http.mvc.service.OrderManagementService;

@Controller
@RequestMapping("api/showUserOrderManage")
public class OrderManagementController {
   @Autowired
   private OrderManagementService orderManagementService;
   
   //显示用户订单
   @RequestMapping("showOrder")
   @ResponseBody
   public DataWrapper<List<OrderManagement>> showOrder(
		   @RequestParam(value="orderId",required=false) String orderId,
		   @RequestParam(value="buyerInfo",required=false)String buyerInfo,
		   @RequestParam(value="orderState",required=false)String orderState,
		   @RequestParam(value="orderCTime",required=false)String orderCTime,
		   @RequestParam(value="orderETime",required=false)String orderETime,
		   @RequestParam(value="isRefund",required=false)String isRefund,
		   @RequestParam(value="currentPage",required=false)Integer currentPage,
		   @RequestParam(value="numberPerpage",required=false)Integer numberPerpage
		   ){
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
   public DataWrapper<Void> closeTrading(
		   @RequestParam(value="orderId") String orderId,
		   @RequestParam(value="flagBit") String flagBit
		   ){
	   return  orderManagementService.closeTrading(orderId,flagBit);
   }
   //显示退款处理
   @RequestMapping("showRefundProcessing")
   @ResponseBody
   public DataWrapper<List<Ordera>> refundProcessing(
		  @RequestParam(value="orderId") String orderId
		   ){
	   return  orderManagementService.refundProcessing(orderId);
   }
 //退款数据处理
   @RequestMapping("makeRefundData")
   @ResponseBody
   public DataWrapper<Void> makeRefundData(
//		   @RequestBody Ordera ordera,
		   @RequestBody  Map<String, String> map
		 /* @ModelAttribute Refund refund*/
		   ){
	   return  orderManagementService.makeRefundData(map);
   }
   //模拟失去焦点事件
   @RequestMapping("loseFocus")
   @ResponseBody
   public DataWrapper<Map<String, Integer>> loseFocus(
		   @RequestParam(value="itemId",required=true) String itemId,
		   @RequestParam(value="orderId",required=true) String orderId,
		   @RequestParam(value="num",required=true) Integer num  
		   ){
	   return  orderManagementService.loseFocus(num,itemId,orderId);
   }
   
   //仓库发货 Warehouse delivery warehouseDelivery
   @RequestMapping("warehouseDelivery")
   @ResponseBody
   public DataWrapper<Void> warehouseDelivery(
		   @RequestParam(value="orderId",required=true) String orderId,
		   @RequestParam(value="logisticsName",required=true) String logisticsName,
		   @RequestParam(value="logisticsCode",required=true) String logisticsCode
		   ){
	   return  orderManagementService.warehouseDelivery(orderId,logisticsName,logisticsCode);
   }
   //显示订单详情
   @RequestMapping("queryOrderDetails")
   @ResponseBody
   public DataWrapper<Ordera>  queryOrderDetails(
		   @RequestParam(value="orderId",required=true) String orderId
		   ){
	   return orderManagementService.queryOrderDetails(orderId);
   }
}
