package com.yayiabc.http.mvc.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.SerializeUtil;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
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
	@AdminTokenValidate
	@AdminLog(description="后台订单显示")
	public DataWrapper<List<OrderManagement>> showOrder(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="orderId",required=false) String orderId,
			@RequestParam(value="buyerInfo",required=false)String buyerInfo,
			@RequestParam(value="orderState",required=false)String orderState,
			@RequestParam(value="orderCTime",required=false)String orderCTime,
			@RequestParam(value="orderETime",required=false)String orderETime,
			@RequestParam(value="isRefund",required=false)String isRefund,
			@RequestParam(value="currentPage",required=false,defaultValue="1")Integer currentPage,
			@RequestParam(value="numberPerpage",required=false,defaultValue="10")Integer numberPerpage
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
	@AdminTokenValidate
	@AdminLog(description="确定交易")
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
	@AdminTokenValidate
	@AdminLog(description="显示退款处理")
	public DataWrapper<Ordera> refundProcessing(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="orderId") String orderId
			){
		return  orderManagementService.refundProcessing(orderId);
	}
	//显示已经退款数据的订单信息
	@RequestMapping("showRefundOrderMessage")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="显示已经订单的退款信息")
	public DataWrapper<Ordera> showRefundOrderMessage(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="orderId") String orderId
			){
		return  orderManagementService.showRefundOrderMessage(orderId);
	}
	//退款数据处理
	@RequestMapping("makeRefundData")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="保存退款处理")
	public DataWrapper<HashMap<String, Object>> makeRefundData(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam("orderItem") String orderItem
			/**
			 * 一个 orderItem 对象需要传 退货数量 num，还有sku ，orderId
			 */
			){
		JSONArray json = JSONArray.fromObject(orderItem);
		ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>)JSONArray.toCollection(json,OrderItem.class);
		return  orderManagementService.makeRefundData(orderItemList);
	}

	//仓库发货 Warehouse delivery warehouseDelivery
	@RequestMapping("warehouseDelivery")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="仓库发货")
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
	@AdminTokenValidate
	@AdminLog(description="显示订单详情")
	public DataWrapper<Ordera>  queryOrderDetails(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="orderId",required=true) String orderId
			){
		return orderManagementService.queryOrderDetails(orderId);
	}
	//后台显示发票详情
	@RequestMapping("queryOrderInvoice")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="查看订单发票信息")
	public DataWrapper<Invoice>queryOrderInvoice(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="orderId",required=true) String orderId
			){
		return orderManagementService.queryOrderInvoice(orderId);
	}

	//后台订单列表导出Excel表格
	@RequestMapping("exportExcel")
	@ResponseBody
	//@AdminLog(description="后台订单列表导出Excel表格")
	public DataWrapper<Void>exportExcel(
			/*	@RequestHeader(value="adminToken",required=true) String adminToken,*/
			@RequestParam(value="orderId",required=false) String orderId,
			@RequestParam(value="buyerInfo",required=false)String buyerInfo,//卖家的姓名或者手机号码
			@RequestParam(value="orderState",required=false)String orderState,
			@RequestParam(value="orderCTime",required=false)String orderCTime,// yyyy-MM-dd HH:mm:ss
			@RequestParam(value="orderETime",required=false)String orderETime,
			@RequestParam(value="isRefund",required=false)String isRefund,
			HttpServletResponse response
			){

		return orderManagementService.exportExcel(orderId,buyerInfo,orderState,orderCTime,orderETime,isRefund,response);
	}
	//后台订单点击 显示该用户的充值记录
	@RequestMapping("queryUserQbList")
	@ResponseBody
	//@AdminLog(description="后台订单列表导出Excel表格")
	public DataWrapper<List<QbRecord>> queryUserQbList(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="phone",required=true) String phone,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerpage",required=false,defaultValue="10") Integer numberPerpage
			){

		return orderManagementService.queryUserQbList(phone,currentPage,numberPerpage);
	}
	
	@RequestMapping("electronicSheet ")
	@ResponseBody
	//@AdminLog(description="后台订单列表导出Excel表格")
	public  void electronicSheet(
			/*	@RequestHeader(value="adminToken",required=true) String adminToken,*/
			HttpServletResponse response
			){

		
		
		response.setCharacterEncoding("utf-8");
		response.setHeader("contentType", "text/html; charset=utf-8");
		response.setContentType("text/html;charset=utf-8");
		String str=orderManagementService.electronicSheet();
		JSONObject jsStr = JSONObject.parseObject(str);

		String ssss=(String) jsStr.get("PrintTemplate");
		System.out.println(ssss);
		try {
			response.getWriter().write(ssss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
