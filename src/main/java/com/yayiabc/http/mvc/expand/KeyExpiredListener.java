package com.yayiabc.http.mvc.expand;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.OrderManagementDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.ExpireOrder;
import com.yayiabc.http.mvc.service.TimerChangeStateService;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;  

/**  
 * Created by denglinjie on 2017/8/28.
 * 类  
    /**  
 * Created by lihui  
 */ 
@Component
public class KeyExpiredListener extends JedisPubSub {  
     
	@Autowired
	private OrderManagementDao orderManagementDao;
	
	
	@Autowired
	private UserMyQbDao userMyQbDao;
	

	@Override  
	public void onPSubscribe(String pattern, int subscribedChannels) {  
		System.out.println("到这里了");
		
		System.out.println("onPSubscribe "  
				+ pattern + " " + subscribedChannels);  
	}  

	@Override 
	@Transactional
	public void onPMessage(String pattern, String channel, String message) { 
		TimerChangeStateService timerChangeStateService=BeanUtil.getBean("TimerChangeStateServiceImpl");
		RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
		jedis.select(1);
		/**
		 * 防止因意外宕机照成的失效订单永久不关闭
		 */
		 jedis.sadd("fangZhi", message);
		 
		 System.err.println(message);
		
		//获取失效订单里的主要内容  [{"itemId":"170719105543","itemNum":1,"itemSKU":"1707191055431","orderId":"100015868863920151"}]
		String invalidOrder=jedis.hget("expireOrder1", message);
		//将失效订单json字符串 转为list集合
		JSONArray json = JSONArray.fromObject(invalidOrder);
		ArrayList<ExpireOrder> expireOrderItemList = (ArrayList<ExpireOrder>)JSONArray.toCollection(json,ExpireOrder.class);
		String orderId=expireOrderItemList.get(0).getOrderId();
		//关闭订单  
		int sign=timerChangeStateService.closeOrder2(orderId);
		if(sign>0){
			//返还库存
			timerChangeStateService.returnStackItemNum(expireOrderItemList);
			//返回钱币
			PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
			payAfterOrderUtil.returnQbMed(orderId);
			//删除  防止队列中的订单
			jedis.srem("fangzhi", message);
		}
		System.out.println(expireOrderItemList);
		jedis.hdel("expireOrder", message);
		jedis.close();
	}
    
	String ut(int i){
		switch (i) {
		case 0:
			return "\"赠\"：";
		case 1:
			return "\"8.0折\"：";
		case 2:
			return "\"9.5折 \"：";
		}
		return null;
	}
	@Override
	public void onMessage(String channel, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub

	}
}  
