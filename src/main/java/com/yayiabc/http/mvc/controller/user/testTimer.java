package com.yayiabc.http.mvc.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.expand.KeyExpiredListener;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.AdvManageService;
import com.yayiabc.http.mvc.service.TimerChangeStateService;

import redis.clients.jedis.Jedis;
public class testTimer extends Thread {

	@Autowired 
	private AdvManageService  advManageService;
	List<Ordera> orderList =null;
	public void init(){

		//Date d=new Date();
		System.err.println("未付款订单加载到缓存中..................");
		Map<String, Date> map=CacheUtils.getInstance().getCacheMap();
		//查询数据库订单的state=1的数据

		if(advManageService!=null){
			orderList =advManageService.queryNowOrder();
			if(orderList!=null){
				if(!orderList.isEmpty()){
					for(int i=0;i<orderList.size();i++){
						map.put(orderList.get(i).getOrderId(), orderList.get(i).getCreated());
					}
				}
			}
		}
	}
	private void initTwo(){
		// TODO Auto-generated method stub
		Thread thread=new  Thread(new testTimer());
		thread.start();
	}
	@Override
	public void run(){
		RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
		jedis.psubscribe(new KeyExpiredListener(),"__keyevent@1__:expired");  
	}


	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		//init();
		inits();
		System.err.println("窝草");
		initTwo();
	}
	private void inits(){
		RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
		Set<String> fangZhiOrderIdSet=jedis.smembers("fangzhi");
		if(!fangZhiOrderIdSet.isEmpty()){
			List<String> list=new ArrayList<String>(fangZhiOrderIdSet);
           
			
			TimerChangeStateService timerChangeStateService=BeanUtil.getBean("TimerChangeStateServiceImpl");
			int a=timerChangeStateService.closeOrder(list);
			/*还原库存操作
			 * 1查询订单下面的所有商品 主要获取商品的购买个数
			 * 2一一还原到item_value的库存字段上 
			 */

			List<OrderItem> OrderItemNums=timerChangeStateService.queryOrderItemNums(list);
			int q=timerChangeStateService.stillItemsListValueNum(OrderItemNums);
		}
		for (String string : fangZhiOrderIdSet) {
			jedis.srem("fangzhi", string);
		}
	}
}


