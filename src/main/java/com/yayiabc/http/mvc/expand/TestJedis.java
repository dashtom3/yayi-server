package com.yayiabc.http.mvc.expand;

import com.yayiabc.common.utils.RedisClient;

import redis.clients.jedis.Jedis;

public class TestJedis {
	public static void main(String[] args) {  
		RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
		jedis.select(1);
		jedis.set("haha", "新浪微博：小叶子一点也不逗");  
		//jedis.expire("haha", 10);  
		jedis.close();
	}  
}
