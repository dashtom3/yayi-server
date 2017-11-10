package com.yayiabc.http.mvc.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.expand.KeyExpiredListener;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.AdvManageService;

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
		System.err.println("窝草");
		initTwo();
	}
}


