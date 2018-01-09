package com.yayiabc.http.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.ExpireOrder;

public interface TimerChangeStateService{

	int timerQueryState(String key);

	int changeState(String key);
	 //根据订单id 查该订单里面的商品
	List<OrderItem> queryOrderItems(String orderId);
    //还给库存表
	//void stillItemValueNum(String itemSKU, Integer num);
	
	int stillItemValueNum(List<OrderItem> orderItemList);
    
	int closeOrder(List<String> temporaryList);

	List<OrderItem> queryOrderItemNums(List<String> temporaryList);

	int stillItemsListValueNum(List<OrderItem> orderItemNums);

	int closeOrder2(String orderId );

	int returnStackItemNum(ArrayList<ExpireOrder> expireOrderItemList);


	int updateDatabase(String id, Object browseNum);

	int updateDatabaseToBrowseNum(HashMap<String, Object> hashMap);

	Ordera queryOrder(String orderId);
}
