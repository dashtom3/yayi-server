package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.TimerChangeStateDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
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
}
