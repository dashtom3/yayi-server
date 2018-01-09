package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.TimerChangeStateDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.ExpireOrder;
import com.yayiabc.http.mvc.service.TimerChangeStateService;
@Service
public class TimerChangeStateServiceImpl implements TimerChangeStateService{
	@Autowired
	private TimerChangeStateDao timerChangeStateDao;
	@Override
	public int timerQueryState(String key) {
		// TODO Auto-generated method stub
		
		return timerChangeStateDao.timerQueryState(key);
	}
	@Override
	public int changeState(String key) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.timerChangeState(key);
	}
	 //根据订单id 查该订单里面的所有商品
	@Override
	public List<OrderItem> queryOrderItems(String orderId) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.queryOrderItems(orderId);
	}
	 //还给库存表
	@Override
	public int stillItemValueNum(@Param("orderItemList")List<OrderItem> orderItemList) {
		// TODO Auto-generated method stub
		//int NUM=placeOrderDao.queryItemInventNum(itemSKU);
		return timerChangeStateDao.stillItemValueNum(orderItemList);
	}
	@Override
	public int closeOrder(List<String> temporaryList) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.closeOrder(temporaryList);
	}
	@Override
	public List<OrderItem> queryOrderItemNums(List<String> temporaryList) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.queryOrderItemNums(temporaryList);
	}
	@Override
	public int stillItemsListValueNum(List<OrderItem> orderItemNums) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.stillItemsListValueNum(orderItemNums);
	}
	@Override
	public int closeOrder2(String orderId) {
		// TODO Auto-generated method stub
		return  timerChangeStateDao.closeOrder2(orderId);
	}
	@Override
	public int returnStackItemNum(ArrayList<ExpireOrder> expireOrderItemList) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.returnStackItemNum(expireOrderItemList);
	}
	@Override
	public int updateDatabase(String id, Object browseNum) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.updateDatabase(id,browseNum);
	}
	@Override
	public int updateDatabaseToBrowseNum(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.updateDatabaseToBrowseNum(hashMap);
	}
	@Override
	public Ordera queryOrder(String orderId) {
		// TODO Auto-generated method stub
		return timerChangeStateDao.queryOrder(orderId);
	}
}
