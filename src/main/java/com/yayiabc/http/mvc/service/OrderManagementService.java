package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.sf.json.JSONObject;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.Refund;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface OrderManagementService {
	DataWrapper<List<User>>  showOrder( HashMap<String, Object> hMap);
   //关闭交易
	DataWrapper<Void> closeTrading(String orderId,String flagBits);
	//显示退款处理
    DataWrapper<List<Ordera>> refundProcessing(String orderId);
    
    //操作退款数据
    DataWrapper<Void> makeRefundData(Map<String, String> map);
    
    //模拟失去焦点事件
    public  DataWrapper<Map<String, Integer>> loseFocus(int refundNum,String OrederId,String itemId);
    
    
    //仓库发货
	DataWrapper<Void> warehouseDelivery(@Param("orderId")String orderId,@Param("logisticsName")String logisticsName,
			@Param("logisticsCode")String  logisticsCode);
}
