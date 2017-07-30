package com.yayiabc.http.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.OrderManagement;

public interface OrderManagementService {
	DataWrapper<List<OrderManagement>>  showOrder( HashMap<String, Object> hMap,
			Integer currentPage,Integer numberPerpage
			);
   //关闭交易
	DataWrapper<Void> closeTrading(String orderId,String flagBits);
	//显示退款处理
	DataWrapper<Ordera> refundProcessing(String orderId);
    
    //操作退款数据
	DataWrapper<HashMap<String, Object>> makeRefundData(ArrayList<OrderItem> list);
    
    //模拟失去焦点事件
    public  DataWrapper<Map<String, Object>> loseFocus(int refundNum,String OrederId,String itemId);
    
    
    //仓库发货
	DataWrapper<Void> warehouseDelivery(@Param("orderId")String orderId,@Param("logisticsName")String logisticsName,
			@Param("logisticsCode")String  logisticsCode);
	//显示订单详情
	DataWrapper<Ordera> queryOrderDetails(String orderId);
	//   //显示已经退款数据的订单信息
	DataWrapper<Ordera> showRefundOrderMessage(String orderId);
}
