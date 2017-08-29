package com.yayiabc.http.mvc.expand;

import com.yayiabc.common.utils.RedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedis {

    public  void orderClose(String orderId) {
        /*JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.80.130");

        Jedis jedis = pool.getResource();
        jedis.auth("123");*/
    	RedisClient redis=RedisClient.getInstance();
    	
    	Jedis jedis=redis.jedis;
    	
        jedis.lpush("list","1");
        jedis.expire("list",5);
   
    }
}