package com.yayiabc.common.utils;

import java.util.HashMap;
import java.util.Set;

import com.yayiabc.http.mvc.service.TimerChangeStateService;

import redis.clients.jedis.Jedis;

public class DatabaseUtil {

	/**
	 * 每天0晨定时  从redis里读取资料库浏览数插入到mysql中
	 */
	public void kk(){
		HashMap<String,Object> hashMap=new HashMap<String,Object>();
		
		TimerChangeStateService timerChangeService=BeanUtil.getBean("TimerChangeStateServiceImpl");
		Jedis jedis=RedisClient.getInstance().getJedis();
		Set<String> set=jedis.zrange("Master_Browse_Num", 0, -1);
		for(String i:set){
			hashMap.put(i,jedis.zscore("Master_Browse_Num",i));
		}
		//这里就遍历map更新吧
//		for(String key:hashMap.keySet()){
//			timerChangeService.updateDatabase(key,hashMap.get(key));
//		}
		timerChangeService.updateDatabaseToBrowseNum(hashMap);
		System.out.println("每日0晨资料库浏览量入库操作........");
		System.out.println(hashMap);
		jedis.close();
		
	}
}
