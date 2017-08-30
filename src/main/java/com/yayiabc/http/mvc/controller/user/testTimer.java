package com.yayiabc.http.mvc.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.expand.KeyExpiredListener;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.AdvManageService;
import com.yayiabc.http.mvc.service.TimerChangeStateService;
import com.yayiabc.http.mvc.service.Impl.AdvManageServiceImpl;

import redis.clients.jedis.Jedis;
@Component
public class testTimer extends Thread implements InitializingBean{

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
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		init();
		//initTwo();
	}
	private void initTwo() {
		// TODO Auto-generated method stub
		Thread thread=new  Thread(new testTimer());
		thread.start();
	}
	/*@Override
	public void run(){
		 RedisClient rs=RedisClient.getInstance();
		 Jedis jedis=rs.jedis;
		 jedis.auth("123");
		 jedis.psubscribe(new KeyExpiredListener(), "__keyevent@0__:expired"); 
	}*/
}


