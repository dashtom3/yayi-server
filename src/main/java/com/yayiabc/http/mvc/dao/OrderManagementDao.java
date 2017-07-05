package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface OrderManagementDao {
   List<User> showOrder(HashMap<String, Object> hMap);

    int  closeTrading(@Param("orderId")String orderId,@Param("flagBit") Integer flagBit);
   
    //显示退款处理
    List<Ordera> refundProcessing(@Param("orderId")String orderId);
    
    //操作退款数据
    int makeRefundData(Map<String, String> map);
    
    //显示订单详情
    List<Ordera> orderitemList(String orderId);
    
    //查询下单商品数  SELECT num FROM order_item WHERE item_id=3 AND order_id=2
    int queryMaxNum(@Param("orderId")String orderId,@Param("itemId")String itemId);
    
    
    //模拟失去焦点
    List<OrderItem> showFund(@Param("orderId")String orderId,@Param("itemId")String itemId);
    //根据订单查询  用户
	String queryUser(String orederId);
    
	//
	int userBalance(String userId); //
	
    //仓库发货
	int warehouseDelivery(@Param("userId")String userId,
			@Param("logisticsName")String logisticsName,
			@Param("logisticsCode")String  logisticsCode);
    //根据//通过orderId查找到userId
	String queryUserId(@Param("orderId")String orderId);

	int queryCount(HashMap<String, Object> hMap);
}
