package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

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
	@Autowired
	private PlaceOrderDao placeOrderDao;
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
	public void stillItemValueNum(String itemSKU, Integer num) {
		// TODO Auto-generated method stub
		int NUM=placeOrderDao.queryItemInventNum(itemSKU);
		timerChangeStateDao.stillItemValueNum(itemSKU,String.valueOf(num+NUM));
	}
}
