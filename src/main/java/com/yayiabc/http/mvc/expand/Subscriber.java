package com.yayiabc.http.mvc.expand;

import com.yayiabc.common.utils.RedisClient;

import redis.clients.jedis.Jedis;

public class Subscriber {  
	  
    public static void main(String[] args) {  
    	RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
        jedis.psubscribe(new KeyExpiredListener(),"__keyevent@1__:expired");  
  
    }  
  
}  
